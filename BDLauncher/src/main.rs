extern crate clap;

use clap::{App, arg};
use std::path::Path;
use std::process::exit;
use subprocess::{Exec, Redirection};
use path_absolutize::*;

#[cfg(target_os = "macos")]
static CP_SPLIT: &str = ":";
#[cfg(target_os = "macos")]
static EXE_SUFFIX: &str = "";
#[cfg(target_os = "linux")]
static CP_SPLIT: &str = ":";
#[cfg(target_os = "linux")]
static EXE_SUFFIX: &str = "";
#[cfg(target_os = "windows")]
static CP_SPLIT: &str = ";";
#[cfg(target_os = "windows")]
static EXE_SUFFIX: &str = ".exe";

//noinspection DuplicatedCode
fn main() {
    let matches = App::new("BDLauncher")
        .version("0.0.1")
        .author("Superice666")
        .about("BedrockLang launcher.")
        .subcommand(App::new("compile").alias("cp")
            .about("Compile a bedrockLang source code file.")
            .arg(arg!([SOURCE_FILE] "The path or filename of the source file.").required(true))
            .arg(arg!(-j --toJar "Whether to package the outputs as a jar.").required(false))
            .arg(arg!(-o --output <FILE> "Set the main file name of the output file. No ext name.").required(false))
            .arg(arg!(--classpath <CLASSPATH> "Set the classpath. Usually used for include the dependencies.").required(false)))
        .subcommand(App::new("run")
            .about("Run a bedrockLang application in the format of .class or .jar.")
            .arg(arg!(--classpath <CLASSPATH> "Set the classpath. Usually used for include the dependencies.").required(false))
            .arg(arg!([FILE] "The path of the application file.").required(true))
            .arg(arg!([ARGS]... "The arguments to be passed to your application.").required(false)))
        .get_matches();

    let java: String;
    if let Some(tmp) = find_java_path() {
        java = tmp;
    } else {
        eprintln!("Java not found.");
        exit(-1);
    }

    if let Some(sub_matches) = matches.subcommand_matches("compile") {
        let mut classpath = String::from("./compiler/*");
        let compiler_folder = Path::new("./compiler");
        if compiler_folder.exists() && compiler_folder.is_dir() {
            if let Some(custom_classpath) = sub_matches.value_of("classpath") {
                classpath = classpath + CP_SPLIT + custom_classpath;
            }
        } else {
            eprintln!("BedrockLang compiler not found.");
            exit(-1);
        }
        if let Some(file_name) = sub_matches.value_of("SOURCE_FILE") {
            let mut process = Exec::cmd(java.as_str()).arg("-Xshareclasses").arg("-cp").arg(classpath)
                .arg("com.blocklynukkit.bedrockLang.compiler.app.Launcher")
                .arg("mode").arg("COMPILE").arg("file").arg(file_name);
            if sub_matches.is_present("toJar") {
                process = process.arg("-toJar");
            }
            if let Some(output_name) = sub_matches.value_of("output") {
                process = process.arg("out").arg(output_name);
            }
            let res = process.stdout(Redirection::Merge).stderr(Redirection::Merge).join();
            match res {
                Ok(_status) => println!("Compilation finished."),
                Err(err) => std::panic::panic_any(String::from("Failed to compile. Reason: ") + &err.to_string())
            }
        } else {
            eprintln!("File not found.");
            exit(-1);
        }
    } else if let Some(sub_matches) = matches.subcommand_matches("run") {
        if let Some(file_name) = sub_matches.value_of("FILE") {
            let mut process = Exec::cmd(java.as_str()).arg("-Xshareclasses");
            if file_name.ends_with(".jar") {
                if let Some(classpath) = sub_matches.value_of("classpath") {
                    process = process.arg("-cp").arg(classpath);
                }
                process = process.arg("-jar").arg(file_name);
                if let Some(arguments) = sub_matches.values_of_lossy("ARGS") {
                    process = process.args(&arguments);
                }
                let res = process.stdout(Redirection::Merge).stderr(Redirection::Merge).stdin(Redirection::Pipe).join();
                match res {
                    Ok(status) => println!("Application exits {:?}", status),
                    Err(err) => std::panic::panic_any(String::from("Failed to run. Reason: ") + &err.to_string())
                }
            } else {
                let mut classpath = String::from("./compiler/*");
                let compiler_folder = Path::new("./compiler");
                if compiler_folder.exists() && compiler_folder.is_dir() {
                    if let Some(custom_classpath) = sub_matches.value_of("classpath") {
                        classpath = classpath + CP_SPLIT + custom_classpath;
                    }
                } else {
                    eprintln!("BedrockLang compiler not found.");
                    exit(-1);
                }
                process = Exec::cmd(java.as_str()).arg("-Xshareclasses").arg("-cp").arg(classpath)
                    .arg("com.blocklynukkit.bedrockLang.compiler.app.Launcher")
                    .arg("mode").arg("RUN").arg("file").arg(file_name);
                let res = process.stdout(Redirection::Merge).stderr(Redirection::Merge).join();
                match res {
                    Ok(status) => println!("Application exits {:?}", status),
                    Err(err) => std::panic::panic_any(String::from("Failed to run. Reason: ") + &err.to_string())
                }
            }
        } else {
            eprintln!("File not found.");
            exit(-1);
        }
    }
}

fn find_java_path() -> Option<String> {
    if let Some(tmp) = check_java_exist(&(String::from("./jdk/bin/java") + EXE_SUFFIX)) {
        return Some(tmp);
    }
    if let Some(tmp) = check_java_exist(&(String::from("./jre/bin/java") + EXE_SUFFIX)) {
        return Some(tmp);
    }
    if let Some(home) = option_env!("JAVA_HOME") {
        if let Some(tmp) = check_java_exist(&(String::from(home) + "/bin/java" + EXE_SUFFIX)) {
            return Some(tmp);
        }
    }
    None
}

fn check_java_exist(path_str: &String) -> Option<String> {
    let path = Path::new(path_str);
    if path.exists() {
        let tmp = path.absolutize();
        match tmp {
            Ok(y) => Some(String::from(y.as_os_str().to_str().unwrap())),
            Err(_) => None
        }
    } else {
        None
    }
}

extern crate clap;

use clap::{App, arg};
use std::path::Path;
use std::process::exit;
use subprocess::{Exec, Redirection};

#[cfg(target_os = "macos")]
static CP_SPLIT: &str = ":";
#[cfg(target_os = "linux")]
static CP_SPLIT: &str = ":";
#[cfg(target_os = "windows")]
static CP_SPLIT: &str = ";";

fn main() {
    let matches = App::new("BDLauncher")
        .version("0.0.1")
        .author("Superice666")
        .about("BedrockLang launcher.")
        .arg(arg!([FILE] "Execute a file.").required(false))
        .subcommand(App::new("compile").alias("cp")
            .about("Compile a bedrockLang source code file.")
            .arg(arg!([SOURCE_FILE] "The path or filename of the source file.").required(true))
            .arg(arg!(-tj -toJar "Whether to package the outputs as a jar.").required(false))
            .arg(arg!(-o -output "Set the main file name of the output file. No ext name.").required(false))
            .arg(arg!(-cp -classpath "Set the classpath. Usually used for include the dependencies.").required(false)))
        .get_matches();

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
            let mut process = Exec::cmd("java").arg("-cp").arg(classpath)
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
    }

    if let Some(file_name) = matches.value_of("FILE") {
        println!("File name is: {}", file_name);
    }
}

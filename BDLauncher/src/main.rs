extern crate clap;

use clap::{App, arg};

fn main() {
    let matches = App::new("BDLauncher")
        .version("0.0.1")
        .author("Superice666")
        .about("BedrockLang launcher.")
        .arg(arg!([FILE]))
        .get_matches();

    if let Some(file_name) = matches.value_of("FILE") {
        println!("File name is: {}", file_name);
    }
}

## File diff tool

This console app allows to compare 2 files and output the differences. 
Supported formats are '.java', '.yaml' and '.json'.
App shows changes (if any) in values and properties specified in given files. 
Three output formats are available: stylish(default), plain, json.


[![Maintainability](https://api.codeclimate.com/v1/badges/486235e1928faf00551b/maintainability)](https://codeclimate.com/github/Pendalf2004/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/486235e1928faf00551b/test_coverage)](https://codeclimate.com/github/Pendalf2004/java-project-71/test_coverage)

`make run-dist` to launch

`make help` to show help

`make version` to show version

### Output variants:
Plain format ( -f=plain)
[![asciicast](https://asciinema.org/a/ONWBbFor8SdKTePbCeDZDeJ7s.svg)](https://asciinema.org/a/ONWBbFor8SdKTePbCeDZDeJ7s)

Stylish format ( -f=stylish)
[![asciicast](https://asciinema.org/a/qR4x6ocKCOVKfCHl2LZpkku01.svg)](https://asciinema.org/a/qR4x6ocKCOVKfCHl2LZpkku01)

JSON format ( -f=json)
[![asciicast](https://asciinema.org/a/evJiRwB7LWSEnCjGEB18GEW0u.svg)](https://asciinema.org/a/evJiRwB7LWSEnCjGEB18GEW0u)

### Mandatory arguments 
file paths to comparable files
<filepath1> <filepath2>
### Optional parameters - 
-f, --format=<format>   output format [default: stylish]

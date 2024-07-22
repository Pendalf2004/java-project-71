package hexlet.code;
//import java.util.HashMap;
//import java.util.Map;

public class Differ {

    public static String generate(String firstFile, String secondFile, String format) throws Exception {

    //read from files
        String firstFileData = GetData.readFile(firstFile);
        String secondFileData = GetData.readFile(secondFile);
    //parse files data
        var firstFileMap = Parser.parseData(firstFileData, GetData.getExtension(firstFile));
        var secondFileMap = Parser.parseData(secondFileData, GetData.getExtension(secondFile));
    //compare two files and receive resulting map with differences
        var diffMap = DataCompare.getDiff(firstFileMap, secondFileMap);
    //return formated string
        switch (format) {
            case "stylish" -> {
                return Formator.stylish(diffMap);
            }
            case "plain" -> {
                return Formator.plain(diffMap);
            }
            default -> {
                return Formator.stylish(diffMap);
            }
        }
    }
}

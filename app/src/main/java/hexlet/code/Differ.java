package hexlet.code;

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
        return Formator.getString(diffMap, format);
    }
//    public static String generate(String firstFile, String secondFile) throws Exception {
//        return generate(firstFile, secondFile, "stylish");
//    }
}

package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Set;

public class Differ {

    public static String generate(String firstFile, String secondFile, String format) throws Exception {
        String firstFileData = readFile(firstFile);
        String secondFileData = readFile(secondFile);
        var firstFileMap = Parser.parseData(firstFileData, getExtension(firstFile));
        var secondFileMap = Parser.parseData(secondFileData, getExtension(secondFile));
        var diffMap = DataCompare.getDiff(firstFileMap, secondFileMap);
        return Formator.getString(diffMap, format);
    }

    public static String generate(String firstFile, String secondFile) throws Exception {
        return generate(firstFile, secondFile, "stylish");
    }

    public static String readFile(String filePath) throws Exception {
        if (!isValidFile(filePath)) {
            throw new Exception("Invalid file path");
        }
        Path path = Path.of(filePath);
        String normiliseFirstPath = String.valueOf(path.toAbsolutePath().normalize());
        File file1 = new File(normiliseFirstPath);
        if (!file1.isFile()) {
            throw new IOException("File does not exist");
        }
        return Files.readString(path);
    }

    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    public static boolean isValidFile(Object input) {
        var supportedExt = Set.of("java", "yml", "yaml", "json");
        try {
            return ((Path.of(input.toString()).toFile().getAbsoluteFile().exists())
                    && (supportedExt.contains(getExtension(input.toString()))));
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
    }
}

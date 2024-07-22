package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Set;

//read file class
public class GetData {

    public static String readFile(String filePath) throws Exception {
    //check if valid file name
        if (!isValidFile(filePath)) {
            throw new Exception("Invalid file path");
        }
    //normalizing received file paths
        Path path = Path.of(filePath);
        String normiliseFirstPath = String.valueOf(path.toAbsolutePath().normalize());
    //check if file exists
        File file1 = new File(normiliseFirstPath);
        if (!file1.isFile()) {
            throw new IOException("File does not exist");
        }
    //read the file content into string, depending on file type and returns result
        return Files.readString(path);
    }

    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    public static boolean isValidFile(Object input) {
        var supportedExt = Set.of("java", "yml", "yaml");
        try {
            return ((Path.of(input.toString()).toFile().exists())
                    && (supportedExt.contains(GetData.getExtension(input.toString()))));
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
    }
}

package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Differ {

    public static String generate(String firstFile, String secondFile) throws Exception {

        //normalizing recived file paths
        String normiliseFirstPath = String.valueOf(Paths.get(firstFile).toAbsolutePath().normalize());
        String normiliseSecondPath = String.valueOf(Paths.get(secondFile).toAbsolutePath().normalize());

        //checking if both files exists
        File file1 = new File(normiliseFirstPath);
        File file2 = new File(normiliseSecondPath);
        if (!file1.isFile()||!file2.isFile()) {
            throw new IOException("File does not exist");
        }

        //Parsing files to Map
        Map<String, Object> firstFileData = Parser.parseFile(normiliseFirstPath);
        Map<String, Object> secondFileData = Parser.parseFile(normiliseSecondPath);

        //combining both files data into one map
        HashMap<String, Object> resultMap = new HashMap<>(firstFileData);
        resultMap.putAll(secondFileData);

        //sorting map and form a resulting string
        String[] resultString = new String[1];
        resultString[0] = "{\n";
        resultMap.keySet()
                .stream()
                .sorted()
                .forEach((key) -> {
                    if (firstFileData.containsKey(key) && (secondFileData.containsKey(key))
                            && (firstFileData.get(key).equals(secondFileData.get(key)))) {
                        resultString[0] += "    " + key + ": " + firstFileData.get(key) + "\n";
                    } else if ((firstFileData.containsKey(key) && (secondFileData.containsKey(key)))) {
                        resultString[0] += "  - " + key + ": " + firstFileData.get(key) + "\n";
                        resultString[0] += "  + " + key + ": " + secondFileData.get(key) + "\n";
                    } else if (firstFileData.containsKey(key)) {
                        resultString[0] += "  - " + key + ": " + firstFileData.get(key) + "\n";
                    } else if (secondFileData.containsKey(key)) {
                        resultString[0] += "  + " + key + ": " + secondFileData.get(key) + "\n";
                    }
                });
        resultString[0] += "}";

        //returning resulting string
        return resultString[0];
    }

}

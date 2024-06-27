package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Differ {

    public static String generate(String firstFile, String secondFile) throws Exception {
        Map<String, Object> firstFileData = parseFile(firstFile);
        Map<String, Object> secondFileData = parseFile(secondFile);

        HashMap<String, Object> resultMap = new HashMap<>(firstFileData);
        resultMap.putAll(secondFileData);

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
        return resultString[0];
    }

    public static Map<String, Object> parseFile(String filePath) throws Exception {
        var mapper = new ObjectMapper();
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        File file = new File(path.toUri());
        if (file.length() == 0) {
            return new HashMap<String, Object>();
        }
        return mapper.readValue(Files.readAllBytes(path), Map.class);
    }
}

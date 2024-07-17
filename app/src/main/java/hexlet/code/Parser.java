package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseFile(String filePath) throws Exception {

        //creating tmp variables for easier work with file
        Path path = Path.of(filePath);
        File file = new File(path.toUri());

        //checking if a file is empty. Returning an empty map if so
        if (file.length() == 0) {
            return new HashMap<String, Object>();
        }

        //getting file extension
        String ext = filePath.substring(filePath.lastIndexOf('.') + 1);

        //parse file content into map, depending on file type and returns result
        switch (ext) {
            case "java":
                var mapper = new ObjectMapper();
                return mapper.readValue(Files.readAllBytes(path), Map.class);
            case "yaml":
                var mapperYAML = new YAMLMapper();
                return mapperYAML.readValue(Files.readAllBytes(path), Map.class);
            default:
                System.out.println("Unsupported format");
        }
        //mandatory return for method. returning empty map
        return new HashMap<>();
    }
}

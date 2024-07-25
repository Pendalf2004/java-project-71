package hexlet.code;

import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseData(String fileContent, String fileType) throws Exception {
    //checking if a string is empty. Returning an empty map if so
        if (fileContent.isEmpty()) {
            return new HashMap<String, Object>();
        }
        TypeReference<HashMap<String, Object>> stringObjectMap
                = new TypeReference<HashMap<String, Object>>() { };
        switch (fileType) {
            case "java", "json" -> {
                var mapper = new ObjectMapper();
                return mapper.readValue(fileContent, stringObjectMap);
            }
            case "yaml", "yml" -> {
                var mapperYAML = new YAMLMapper();
                return mapperYAML.readValue(fileContent, stringObjectMap);
            }
            default -> throw new Exception("Unsupported format");
        }
    }
}

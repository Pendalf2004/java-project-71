package hexlet.code.formats;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Fields;

import java.io.IOException;
import java.util.Map;

public class JSON {
    public static String form(Map<String, Fields> inputMap) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(inputMap);
    }
}

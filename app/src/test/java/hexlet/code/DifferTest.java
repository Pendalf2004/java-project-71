package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
// import static org.junit.jupiter.api.Assertions.*;

class DifferTest {
    private final String emptyFilePath = "empty.java";
    private final String file2Path = "file2.java";

    @Test
    void generateEmptyTest() throws Exception {
        assertThat(Differ.generate(emptyFilePath, emptyFilePath)).isEqualTo("{\n}");
    }

    @Test
    void generateWith1Empty() throws Exception {
        String assertionWithEmptyFile1 = "{\n  - host: hexlet.io\n  - timeout: 20\n  - verbose: true\n}";
        assertThat(Differ.generate(file2Path, emptyFilePath)).isEqualTo(assertionWithEmptyFile1);
    }

    @Test
    void generateWith2Empty() throws Exception {
        String assertionWithEmptyFile2 = "{\n  + host: hexlet.io\n  + timeout: 20\n  + verbose: true\n}";
        assertThat(Differ.generate(emptyFilePath, file2Path)).isEqualTo(assertionWithEmptyFile2);

    }

    @Test
    void parseFile() throws Exception {
//      assertThat(Differ.parseFile(emptyFilePath)).isEqualTo(new HashMap<String, Object>());
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> Differ.parseFile(""));
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("timeout", 20);
        testMap.put("verbose", true);
        testMap.put("host", "hexlet.io");
        assertThat(Differ.parseFile(file2Path)).isEqualTo(testMap);
    }
}

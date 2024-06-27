package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
// import static org.junit.jupiter.api.Assertions.*;

class DifferTest {
    private final String emptyFilePath = "/home/john/gitDir/java-project-71/app/src/test/resources/empty.java";
//    private final String file1Path = "/home/john/gitDir/java-project-71/app/src/test/resources/file1.java";
    private final String file2Path = "/home/john/gitDir/java-project-71/app/src/test/resources/file2.java";

    @Test
    void generate() throws Exception {
        assertThat(Differ.generate(emptyFilePath, emptyFilePath)).isEqualTo("{\n}");
        String assertionWithEmptyFole1 = "{\n  - host: hexlet.io\n  - timeout: 20\n  - verbose: true\n}";
        assertThat(Differ.generate(file2Path, emptyFilePath)).isEqualTo(assertionWithEmptyFole1);
        assertionWithEmptyFole1 = "{\n  + host: hexlet.io\n  + timeout: 20\n  + verbose: true\n}";
        assertThat(Differ.generate(emptyFilePath, file2Path)).isEqualTo(assertionWithEmptyFole1);

    }

    @Test
    void parseFile() throws Exception {
//      Files.createFile(Path.of("/home/john/gitDir/java-project-71/app/src/test/resources/empty.java"));
        assertThat(Differ.parseFile(emptyFilePath)).isEqualTo(new HashMap<String, Object>());
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> Differ.parseFile(""));
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("timeout", 20);
        testMap.put("verbose", true);
        testMap.put("host", "hexlet.io");
        assertThat(Differ.parseFile(file2Path)).isEqualTo(testMap);
    }
}

package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
// import static org.junit.jupiter.api.Assertions.*;

class DifferTest {
    private static final String emptyFileName = "empty.java";
    private static final String dataFileName = "file2.java";
    private static final String resDir = "src/test/resources";
    private static Map<String, Object> testMap = new HashMap<>();

    @BeforeAll
    static void beforeAll() throws IOException {
        File emptyFile = new File(resDir + emptyFileName);
        File dataFile = new File(resDir + dataFileName);
        emptyFile.createNewFile();
        dataFile.createNewFile();
        testMap.put("timeout", 20);
        testMap.put("verbose", true);
        testMap.put("host", "hexlet.io");
        var mapper = new ObjectMapper();
        mapper.writeValue(dataFile, testMap);
    }

    @AfterAll
    static void afterAll() {
        Path empty = Path.of(resDir + emptyFileName);
        empty.toFile().delete();
        Path data = Path.of(resDir + dataFileName);
        data.toFile().delete();
    }

    @Test
    void generateEmptyTest() throws Exception {
        assertThat(Differ.generate(resDir + emptyFileName, resDir + emptyFileName)).isEqualTo("{\n}");
    }

    @Test
    void generateWith1Empty() throws Exception {
        String assertionWithEmptyFile1 = "{\n  - host: hexlet.io\n  - timeout: 20\n  - verbose: true\n}";
        assertThat(Differ.generate(resDir + dataFileName, resDir + emptyFileName)).isEqualTo(assertionWithEmptyFile1);
    }

    @Test
    void generateWith2Empty() throws Exception {
        String assertionWithEmptyFile2 = "{\n  + host: hexlet.io\n  + timeout: 20\n  + verbose: true\n}";
        assertThat(Differ.generate(resDir + emptyFileName, resDir + dataFileName)).isEqualTo(assertionWithEmptyFile2);

    }

    @Test
    void parseFile() throws Exception {
//      assertThat(Differ.parseFile(emptyFilePath)).isEqualTo(new HashMap<String, Object>());
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> Differ.parseFile(""));
        assertThat(Differ.parseFile(resDir + dataFileName)).isEqualTo(testMap);
    }
}
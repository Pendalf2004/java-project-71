package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
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
    private static final String RES_FOLDER = "src/test/resources/";
    private static Map<String, Object> testMap = new HashMap<>();

    @BeforeAll
    static void beforeAll() throws IOException {

        //creating files for test
        //empty java file
        File emptyFileJAVA = new File(RES_FOLDER + "empty.java");
        emptyFileJAVA.createNewFile();
        //java data file
        File dataFileJAVA = new File(RES_FOLDER + "data.java");
        dataFileJAVA.createNewFile();
        //empty yaml file
        File emptyFileYML = new File(RES_FOLDER + "empty.yaml");
        emptyFileYML.createNewFile();
        //data yaml file
        File dataFileYML = new File(RES_FOLDER + "data.yaml");
        dataFileYML.createNewFile();

        //creating test map
        testMap.put("timeout", 20);
        testMap.put("verbose", true);
        testMap.put("host", "hexlet.io");

        //putting test map to java data file
        var mapper = new ObjectMapper();
        mapper.writeValue(dataFileJAVA, testMap);

        //putting test map to yaml data file
        var mapperYAML = new YAMLMapper();
        mapperYAML.writeValue(dataFileYML, testMap);
    }

    @AfterAll
    static void afterAll() {

        //deleting test files
        Path emptyJava = Path.of(RES_FOLDER + "empty.java");
        emptyJava.toFile().delete();
        Path dataJava = Path.of(RES_FOLDER + "data.java");
        dataJava.toFile().delete();
        Path emptyYaml = Path.of(RES_FOLDER + "empty.yaml");
        emptyYaml.toFile().delete();
        Path dataYaml = Path.of(RES_FOLDER + "data.yaml");
        dataYaml.toFile().delete();
    }

    @Test
    void generateEmptyTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "empty.java", RES_FOLDER + "empty.java")).isEqualTo("{\n}");
        assertThat(Differ.generate(RES_FOLDER + "empty.yaml", RES_FOLDER + "empty.yaml")).isEqualTo("{\n}");
    }

    @Test
    void generateWith1Empty() throws Exception {
        String assertionWithEmptyFile1 = "{\n  - host: hexlet.io\n  - timeout: 20\n  - verbose: true\n}";
        assertThat(Differ.generate(RES_FOLDER + "data.java", RES_FOLDER + "empty.java")).
                isEqualTo(assertionWithEmptyFile1);
        assertThat(Differ.generate(RES_FOLDER + "data.yaml", RES_FOLDER + "empty.yaml")).
                isEqualTo(assertionWithEmptyFile1);

    }

    @Test
    void generateWith2Empty() throws Exception {
        String assertionWithEmptyFile2 = "{\n  + host: hexlet.io\n  + timeout: 20\n  + verbose: true\n}";
        assertThat(Differ.generate(RES_FOLDER + "empty.java", RES_FOLDER + "data.java")).
                isEqualTo(assertionWithEmptyFile2);
        assertThat(Differ.generate(RES_FOLDER + "empty.yaml", RES_FOLDER + "data.yaml")).
                isEqualTo(assertionWithEmptyFile2);
    }

    @Test
    void parseFile() throws Exception {
//      assertThat(Differ.parseFile(emptyFilePath)).isEqualTo(new HashMap<String, Object>());
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> Differ.generate("", ""));
        assertThat(Parser.parseFile(RES_FOLDER + "data.java")).isEqualTo(testMap);
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> Differ.generate("", "empty.yaml"));
        assertThat(Parser.parseFile(RES_FOLDER + "data.yaml")).isEqualTo(testMap);
    }
}
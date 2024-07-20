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
    //file for using as a map entry and to compare data files in some tests
        File mapEntryFile = new File(RES_FOLDER + "data2.java");
        dataFileJAVA.createNewFile();

    //creating test map
        int[] intArray = new int[]{1, 2, 3};
        testMap.put("numbers", intArray);
        testMap.put("timeout", 20);
        testMap.put("verbose", true);
        testMap.put("host", "hexlet.io");

    //putting test map to data file
        var mapper = new ObjectMapper();
        mapper.writeValue(mapEntryFile, testMap);

    //altering test map for the second data file
        testMap.put("file", mapEntryFile);
        testMap.put("verbose", false);
        testMap.remove("timeout", 20);

    //putting test map with file entry to java data file
        mapper.writeValue(dataFileJAVA, testMap);

    //putting test map with file to yaml data file
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
        Path data2 = Path.of(RES_FOLDER + "data2.java");
        data2.toFile().delete();
    }

    @Test
    void generateEmptyTest() throws Exception {

    //comparing empty files
        assertThat(Differ.generate(RES_FOLDER + "empty.java", RES_FOLDER + "empty.java", "stylish")).isEqualTo("{\n}");
        assertThat(Differ.generate(RES_FOLDER + "empty.yaml", RES_FOLDER + "empty.yaml", "stylish")).isEqualTo("{\n}");
    }

    @Test
    void generateWithSecondEmpty() throws Exception {

    //comparing with second file empty
        String assertionWithEmptyFile1 =
            "{\n  - host: hexlet.io\n  - numbers: [1, 2, 3]\n  - timeout: 20\n  - verbose: true\n}";
        assertThat(Differ.generate(RES_FOLDER + "data.java", RES_FOLDER + "empty.java", "stylish")).
                isEqualTo(assertionWithEmptyFile1);
        assertThat(Differ.generate(RES_FOLDER + "data.yaml", RES_FOLDER + "empty.yaml", "stylish")).
                isEqualTo(assertionWithEmptyFile1);

    }

    @Test
    void generateWithFirstEmpty() throws Exception {

    //comparing two data files
        String assertionWithTwoDataFiles =
            "{\n    host: hexlet.io\n    numbers: [1, 2, 3]\n  - timeout: 20\n  - verbose: true\n  + verbose: true\n}";
        assertThat(Differ.generate(RES_FOLDER + "data.java", RES_FOLDER + "data2.java", "stylish")).
                isEqualTo(assertionWithTwoDataFiles);
    }

    @Test
    void parseFile() throws Exception {

    //sending empty filename
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> Differ.generate("", "", "stylish"));
    }
}

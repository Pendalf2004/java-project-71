package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DifferTest {
    private static final String RES_FOLDER = System.getProperty("user.dir") + "/src/test/resources/";
    private static final int INT_VALUE = 20;
    private static final int[] ARRAY_VALUE = new int[]{1, 2, 3};
    @BeforeAll
    static void generateExpected() throws Exception {
        File dataFileJAVA = new File(RES_FOLDER + "file1.yaml");
        dataFileJAVA.createNewFile();
        File anotherDataFileJAVA = new File(RES_FOLDER + "file2.yaml");
        anotherDataFileJAVA.createNewFile();
        File dataFileYML = new File(RES_FOLDER + "file1.yaml");
        dataFileYML.createNewFile();
        File anotherDataFileYML = new File(RES_FOLDER + "file2.yaml");
        anotherDataFileYML.createNewFile();
        File mapEntryFile = new File(RES_FOLDER + "file3.yaml");
        anotherDataFileJAVA.createNewFile();

        var testMap = new HashMap<String, Object>();
        testMap.put("numbers", ARRAY_VALUE);
        testMap.put("timeout", INT_VALUE);
        testMap.put("verbose", true);
        testMap.put("host", "hexlet.io");

        var mapper = new ObjectMapper();
        mapper.writeValue(mapEntryFile, testMap);

        testMap.put("file", mapEntryFile);
        testMap.put("verbose", false);
        testMap.remove("timeout", INT_VALUE);

        mapper.writeValue(anotherDataFileJAVA, testMap);

        var mapperYAML = new YAMLMapper();
        mapperYAML.writeValue(anotherDataFileYML, testMap);
    }

    @AfterAll
    static void afterAll() {
        Path emptyJava = Path.of(RES_FOLDER + "file1.yaml");
        emptyJava.toFile().delete();
        Path dataJava = Path.of(RES_FOLDER + "file2.yaml");
        dataJava.toFile().delete();
        Path emptyYaml = Path.of(RES_FOLDER + "file1.yaml");
        emptyYaml.toFile().delete();
        Path dataYaml = Path.of(RES_FOLDER + "file2.yaml");
        dataYaml.toFile().delete();
        Path data2 = Path.of(RES_FOLDER + "file3.yaml");
        data2.toFile().delete();
    }

    @Test
    void emptyFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "stylish.tst")));
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "stylish.tst")));
    }

    @Test
    void plainFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml", "plain")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "plain.tst")));
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml", "plain")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "plain.tst")));
    }

    @Test
    void stylishFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml", "stylish")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "stylish.tst")));
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml", "stylish")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "stylish.tst")));
    }

    @Test
    void jsonFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml", "json")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "json.tst")));
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml", "json")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "json.tst")));
    }
}

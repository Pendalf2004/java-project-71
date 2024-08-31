package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DifferTest {
    private static final String RES_FOLDER = System.getProperty("user.dir") + "/src/test/resources/";
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    @BeforeAll
    static void generateExpected() throws Exception {
        resultStylish = readFile("stylish.tst");
        resultPlain = readFile("plain.tst");
        resultJson = readFile("json.tst");
    }

    private static Path getPath(String fileName) {
        return Paths.get("src", "test", "resources", "references", fileName)
                .toAbsolutePath().normalize();
    }
    private static String readFile(String fileName) throws Exception {
        Path filePath = getPath(fileName);
        return Files.readString(filePath).trim();
    }

    @Test
    void emptyFormatYamlTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml")).
                isEqualTo(resultStylish);
    }

    @Test
    void emptyFormatJsonTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.json", RES_FOLDER + "file2.json")).
                isEqualTo(resultStylish);
    }


    @Test
    void plainFormatYamlTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml", "plain")).
                isEqualTo(resultPlain);
    }
    @Test
    void plainFormatJsonTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.json", RES_FOLDER + "file2.json", "plain")).
                isEqualTo(resultPlain);
    }

    @Test
    void stylishFormatYamlTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml", "stylish")).
                isEqualTo(resultStylish);
    }

    @Test
    void stylishFormatJsonTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.json", RES_FOLDER + "file2.json", "stylish")).
                isEqualTo(resultStylish);
    }

    @Test
    void jsonFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.json", RES_FOLDER + "file2.json", "json")).
                isEqualTo(resultJson);
    }
    @Test
    void jsonFormatYamlTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "file1.yaml", RES_FOLDER + "file2.yaml", "json")).
                isEqualTo(resultJson);
    }
}

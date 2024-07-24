package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DifferTest {
    private static final String RES_FOLDER = System.getProperty("user.dir") + "/src/test/resources/";

    @Test
    void stylishFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "data.java", RES_FOLDER + "data2.java", "stylish")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "stylish.tst")));
        assertThat(Differ.generate(RES_FOLDER + "data.yaml", RES_FOLDER + "data2.yaml", "stylish")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "stylish.tst")));
    }

    @Test
    void plainFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "data.java", RES_FOLDER + "data2.java", "plain")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "plain.tst")));
        assertThat(Differ.generate(RES_FOLDER + "data.yaml", RES_FOLDER + "data2.yaml", "plain")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "plain.tst")));
    }
    @Test
    void jsonFormatTest() throws Exception {
        Differ.generate(RES_FOLDER + "data.java", RES_FOLDER + "data2.java", "json");
        var pathToJson = Path.of(System.getProperty("user.dir") + "/src/main/resources/output.json");
        assertThat(Files.exists(pathToJson));
        assertThat(Files.size(pathToJson) > 0);
        Files.deleteIfExists(pathToJson);
    }

}

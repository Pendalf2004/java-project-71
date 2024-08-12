package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DifferTest {
    private static final String RES_FOLDER = System.getProperty("user.dir") + "/src/test/resources/";

    @Test
    void plainFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "data.json", RES_FOLDER + "data2.json", "plain")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "plain.tst")));
        assertThat(Differ.generate(RES_FOLDER + "data.yaml", RES_FOLDER + "data2.yaml", "plain")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "plain.tst")));
    }
/*    @Test
    void stylishFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "data.json", RES_FOLDER + "data2.json", "stylish")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "stylish.tst")));
        assertThat(Differ.generate(RES_FOLDER + "data.yaml", RES_FOLDER + "data2.yaml", "stylish")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "stylish.tst")));
    }*/

    @Test
    void jsonFormatTest() throws Exception {
        assertThat(Differ.generate(RES_FOLDER + "data.json", RES_FOLDER + "data2.json", "json")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "json.tst")));
        assertThat(Differ.generate(RES_FOLDER + "data.yaml", RES_FOLDER + "data2.yaml", "json")).
                isEqualTo(Files.readString(Path.of(RES_FOLDER + "json.tst")));
    }
}

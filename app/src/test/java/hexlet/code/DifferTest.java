package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

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
    void parseFile() {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> Differ.generate("", "", "stylish"));
    }
}

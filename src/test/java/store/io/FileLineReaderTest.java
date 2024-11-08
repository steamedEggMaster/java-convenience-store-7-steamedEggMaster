package store.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileLineReaderTest {
    private File tempFile;
    private FileLineReader fileLineReader;

    @BeforeEach
    void setUp() throws IOException {
        fileLineReader = new FileLineReader();
        tempFile = File.createTempFile("products", ".md");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("name,price,quantity,promotion");
            writer.newLine();
            writer.write("콜라,1000,10,탄산2+1");
            writer.newLine();
            writer.write("콜라,1000,10,null");
        }
    }

    @AfterEach
    void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void 파일_읽기를_제대로_수행하는지_테스트() {
        List<String> lines = fileLineReader.readLines(tempFile.getAbsolutePath());

        assertEquals(2, lines.size());
        assertEquals("콜라,1000,10,탄산2+1", lines.get(0));
        assertEquals("콜라,1000,10,null", lines.get(1));
    }
}

package store.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLineReader {
    private static final String FILE_READ_ERROR = "[ERROR] 파일이 유효하지 않습니다.";

    public List<String> readLines(String filePath) {
        try {
            BufferedReader br = openFile(filePath);
            return readAllLines(br);
        } catch (IOException e) {
            throw new IllegalArgumentException(FILE_READ_ERROR);
        }
    }

    private BufferedReader openFile(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }

    private List<String> readAllLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        br.readLine();

        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}

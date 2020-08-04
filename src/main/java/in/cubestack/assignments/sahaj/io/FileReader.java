package in.cubestack.assignments.sahaj.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {

    public List<String> readContent(String relativePath) throws IOException, URISyntaxException {
        Path filePath = Path.of(getClass().getResource(relativePath).toURI());
        return Files.readAllLines(filePath);
    }
}

package nio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;

public class Utils {

    public static void main(String[] args) throws IOException {

        Files.write(
                Paths.get("server", "serverFiles", "1.txt"),
                "Hello world".getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.APPEND
        );

        Files.walkFileTree(
                Paths.get("./"),
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        System.out.println(file.toString());
                        return super.visitFile(file, attrs);
                    }
                });
    }
}

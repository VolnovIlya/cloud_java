package streamApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FilesTest {
    public static void main(String[] args) throws IOException {

        List<String> list = Files.list(Paths.get("./"))
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());

        System.out.println(list);

        List<Map.Entry<String, Integer>> entryList = Files.lines(Paths.get("data.txt"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .flatMap(s -> Arrays.stream(s.split(" +")))
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("[^a-zA-Z]+", ""))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toMap(
                        Function.identity(),
                        value -> 1,
                        Integer::sum
                )).entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .collect(Collectors.toList());



        System.out.println(entryList);
    }
}

package streamApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DigitStream {

    public static void main(String[] args) {

        // filter
        // map
        // reduce - terminal

        // sorted
        // distinct

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(arg -> arg % 2 == 0)
                .map(arg -> arg * 2)
                .forEach(System.out::println);

        List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(arg -> arg % 2 == 0)
                .map(arg -> arg * 2)
                .collect(Collectors.toList());

        String string = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(arg -> arg % 2 == 0)
                .map(arg -> String.valueOf(arg * 2))
                .collect(Collectors.joining(", "));

        System.out.println(list);
        System.out.println(string);

        Optional<Integer> res = Stream.of(1, 2, 3, 4, 5)
                .reduce(Integer::sum);

        Integer mul = Stream.of(1, 2, 3, 4, 5)
                .reduce(1, (a, b) -> a * b);

        ArrayList<Integer> result = Stream.of(1, 2, 3, 4, 5)
                .reduce(
                        new ArrayList<>(),
                        (l, integer) -> {
                            l.add(integer);
                            return l;
                        },
                        (l1, l2) -> {
                            l1.addAll(l2);
                            return l1;
                        }
                );

        System.out.println(result);

        TreeMap<Integer, Integer> map1 = Stream.of(1, 1, 1, 2, 2, 3)
                .reduce(
                        new TreeMap<>(),
                        (map, arg) -> {
                            map.put(arg, map.getOrDefault(arg, 0) + 1);
                            return map;
                        },
                        (m1, m2) -> m1
                );

        System.out.println(map1);

        Map<Integer, Integer> map = Stream.of(1, 1, 1, 2, 2, 3)
                .collect(Collectors.toMap(
                        Function.identity(),
                        x -> 1,
                        Integer::sum
                ));



        // 500
        List<User> users = Stream.of(
                new User(1, "Ivan"),
                new User(2, "Oleg")
        ).collect(Collectors.toList());


        // O(1)
        Map<Integer, User> userMap = users.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        Function.identity()
                ));

        System.out.println(userMap);

        System.out.println(map);

        Optional<Integer> optional = Stream.of(1, 2, 3, 4)
                .filter(x -> x > 4)
                .findFirst();

        Integer valuePresent = optional.orElse(-1);
        System.out.println(valuePresent);


    }
}

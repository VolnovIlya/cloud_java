package streamApi;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTest {

    static void process(Callback callback, int arg) {
        callback.call(arg);
    }

    public static void main(String[] args) {

        process(System.out::println, 234);


        Callback callback = arg -> {
            System.out.println(arg);
        };

        Callback callRef = System.out::println;

        System.out.println(callback.getClass());


        Consumer<String> consumer = System.out::println;

        Predicate<String> predicate = String::isEmpty;

        System.out.println(predicate.test(""));
        System.out.println(predicate.test("12324"));

        Function<String, Integer> function = String::length;

        System.out.println(function.andThen(x -> x + 5)
                .apply("123"));

        Supplier<ArrayList<Integer>> supplier = ArrayList::new;

        supplier.get();
    }
}

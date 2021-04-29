package streamApi;

@FunctionalInterface
public interface Callback {

    void call(Object arg);

    default void log(String arg) {
        System.out.println(arg);
    }
}

package nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class Watchers {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("server", "serverFiles");
        System.out.println(path.toString());
        System.out.println(path.toAbsolutePath().toString());

        WatchService watchService = FileSystems.getDefault().newWatchService();

        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    WatchKey watchKey = watchService.take(); //
                    if (watchKey.isValid()) {

                        List<WatchEvent<?>> events = watchKey.pollEvents();

                        for (WatchEvent<?> event : events) {
                            System.out.println(event.kind().name());
                            System.out.println(event.context());
                        }

                        watchKey.reset();
                    }
                }
            } catch (Exception ignored) {}
        });
        // thread.setDaemon(true);
        thread.start();

        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

    }
}

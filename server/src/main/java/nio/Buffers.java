package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Buffers {

    public static void main(String[] args) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(5);

        buffer.put((byte) 65);
        buffer.put((byte) 66);
        buffer.put((byte) 67);

        buffer.flip();

        Files.newByteChannel(
                Paths.get("server", "serverFiles", "1.txt"),
                StandardOpenOption.APPEND)
                .write(buffer);

        RandomAccessFile raf = new RandomAccessFile("server/serverFiles/1.txt", "rw");
        FileChannel channel = raf.getChannel();

    }

}

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;
import model.Message;

public class ChatControllerV2 implements Initializable {

    public ListView<String> listView;
    public TextField input;
    private NettyNetwork network;
    private ObjectDecoderInputStream is;
    private ObjectEncoderOutputStream os;

    public void send(ActionEvent actionEvent) throws IOException {
        os.writeObject(Message.builder()
                .createdAt(LocalDateTime.now())
                .text(input.getText())
                .author("user")
                .build());
        input.clear();
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Socket socket = new Socket("localhost", 8189);

        os = new ObjectEncoderOutputStream(socket.getOutputStream());
        is = new ObjectDecoderInputStream(socket.getInputStream());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        Thread service = new Thread(() -> {
            try {
                while (true) {
                    Message message = (Message) is.readObject();
                    String s = String.format("[%s] %s: %s",
                            message.getCreatedAt().format(formatter), message.getAuthor(), message.getText());
                    Platform.runLater(() -> listView.getItems().add(s));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.setDaemon(true);
        service.start();
    }
}

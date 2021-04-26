package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Message implements Serializable {

    private String text;
    private String author;
    private LocalDateTime createdAt;

}

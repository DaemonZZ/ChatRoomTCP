package common;

import server.ServerGUI;

import java.io.Serial;
import java.io.Serializable;

public class Message implements Serializable {

    @Serial
    private static final long serialVersionUID = -253674478922670590L;
    private String Sender;
    private String content;

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message() {
    }

    public Message(String sender, String content) {
        Sender = sender;
        this.content = content;
    }
}

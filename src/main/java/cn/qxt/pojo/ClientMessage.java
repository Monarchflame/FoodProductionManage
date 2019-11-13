package cn.qxt.pojo;

public class ClientMessage extends ClientMessageKey {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}
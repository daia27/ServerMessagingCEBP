package demo;

public abstract class Message {
    protected String message;
    protected MessageType messageType;

    public Message(MessageType messageType, String message){
        this.messageType = messageType;
        this.message = message;
    }
    public  String getMessage(){
        return  message;
    };
    public MessageType getType() {
        return messageType;
    }

}

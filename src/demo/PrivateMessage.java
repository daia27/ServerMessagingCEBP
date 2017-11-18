package demo;

public class PrivateMessage extends Message {
    private Client sender, receiver;

    public PrivateMessage(Client sender, Client receiver, MessageType messageType, String message) {
        super(messageType, message);
        this.sender= sender;
        this.receiver = receiver;
    }

	public Client getSender() {
		return sender;
	}

	public Client getReceiver() {
		return receiver;
	}
	
	public String getMessage() {
		return message;
	}
}

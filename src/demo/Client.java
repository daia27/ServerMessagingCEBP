package demo;

public class Client {
    private String id;
    private Server server = Server.getInstance();

    public Client(String id){
        this.id = id;
    }

    public void connect() {
        try {
            server.accept(this);
        } catch (ConnectionException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void disconnect() {
        try {
            server.ejectClient(this);
        } catch (ClientNotFound e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public String getId(){
        return id;
    }

    public void sendMessage(Client destination, MessageType messageType, String message) {
        Message msg = new PrivateMessage(this, destination, messageType, message);
        server.enQueue(msg);
    }

    public void receiveMessage(PrivateMessage msg) {
        System.out.println(this.toString() + " received " + msg.getMessage() + " from " + msg.getSender().toString() + ".");
    }

    public void publishTopic(MessageType messageType, String message) {
        Message msg = new PublicMessage(messageType, message);
        server.addTopic(msg);
    }
    
    public String toString() {
    		return "Client@" + this.id;
    }
}

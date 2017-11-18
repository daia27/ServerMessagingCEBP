package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Server {
    private static Server self = new Server();
    private static boolean locked = false;
    private List<Client> clientList = new ArrayList<Client>();
    private List<Message> privateQueue = new ArrayList<Message>();
    private List<Message> publicTopics = new ArrayList<Message>();


    public static Server getInstance() {
        return self;
    }

    private Server() {
    }

    public void start(){
        // TODO should start listening for incoming clients
    }

    public void accept(Client client) throws ConnectionException {
        // System.out.println(clientList);
        if(clientList.size()<5){
            clientList.add(client);
        } else {
            throw new ConnectionException();
        }
    }

    public void ejectClient(Client client) throws ClientNotFound{
        if(clientList.contains(client)){
            clientList.remove(client);
        } else {
            throw new ClientNotFound();
        }

    }

    public void enQueue(Message msg) {
        privateQueue.add(msg);

        self.processQueue();
    }


    // If the semaphore is not locked, we can forward the first message in the
    // queue to the receiver
    //
    public void processQueue() {
    		System.out.println("Verifying queue.." + privateQueue.toString());
    		
        if (!locked && privateQueue.size() > 0) {
            PrivateMessage msg = (PrivateMessage)privateQueue.get(0);

            // Lock server message processing
            locked = true;

            // Here, the receiver gets the message from the server.
            msg.getReceiver().receiveMessage(msg);

            // Need to remove message from the message queue because it has been
            // processed.
            // @TODO I'm not sure what the Java way to do this is.
            privateQueue.remove(msg);

            // Unlock server message processing
            locked = false;


            // Now we can try to process the message queue again, because the
            // processing has been unlocked
            self.processQueue();
        }
    }


    public void addTopic(Message msg) {
        publicTopics.add(msg);
    }

    public void getAllMessages(){
        for(Message msg: privateQueue){
            System.out.println(msg.getMessage());
        }

        for(Message msg: publicTopics){
            System.out.println(msg.getMessage());
        }
    }
}

package demo;

public class Main {
    public static void main(String[] args){
        Server server = Server.getInstance();
        Client c1 = new Client("1");
        Client c2 = new Client("2");
        Client c3 = new Client("3");

        c1.connect();
        c2.connect();
        c3.connect();

        c1.sendMessage(c2,MessageType.INFO, "messaj1");
        c3.sendMessage(c1,MessageType.INFO, "messaj2");
        c1.sendMessage(c3,MessageType.WARNING, "messaj3");
        c2.sendMessage(c3,MessageType.INFO, "messaj4");

        c1.publishTopic(MessageType.DEBUG, "public messaj");


        c1.disconnect();
        c2.disconnect();
        c3.disconnect();
    }
}

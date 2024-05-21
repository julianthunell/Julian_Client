import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // Default port number we are going to use
        int portnumber = 1234;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        //create a MulticastSocket
        MulticastSocket chatMulticastSocket = new MulticastSocket(1234);

        //Determine the IP adress of host
        InetAddress group = InetAddress.getByName("225.4.5.6");

        //Joins a multicast group
        chatMulticastSocket.joinGroup(group);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";
        //Prompt a user to enter a message
        while (true) {
            System.out.println("Message for server: ");

            msg = br.readLine();
            if(msg.equalsIgnoreCase("exit")){
                break;
            }

            DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, portnumber);
            chatMulticastSocket.send(data);

        }
        //close
        chatMulticastSocket.close();
    }
}
// Program 2: Super Robot
import java.net.*;
import java.io.*;
 public class Program2 {
    private static final int ROBOT_PORT = 5001;
    private static final int KAMAL_PORT = 5000;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(ROBOT_PORT);

            // Receive a packet with payload "STOP" from Dr Kamal's computer
            byte[] buffer = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePacket);
            String receivedPayload = new String(receivePacket.getData(), 0, receivePacket.getLength());

            if (receivedPayload.equals("STOP")) {
                // Reply with a packet containing payload "OK"
                String okMessage = "OK";
                InetAddress kamalAddress = receivePacket.getAddress();
                int kamalPort = receivePacket.getPort();
                DatagramPacket replyPacket = new DatagramPacket(okMessage.getBytes(), okMessage.length(), kamalAddress, kamalPort);
                socket.send(replyPacket);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
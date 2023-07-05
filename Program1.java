import java.net.*;
import java.io.*;
// Program 1: Dr Kamal's computer
public class Program1 {
    private static final int KAMAL_PORT = 5001;
    private static final int ROBOT_PORT = 6000;
    private static final int HUSAM_PORT = 7000;

    private static final String ROBOT_IP = "193.188.40.1";

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(KAMAL_PORT);

            // Ask Super Robot for its IP address
            InetAddress robotAddress = InetAddress.getByName(ROBOT_IP);

            // Send a packet with payload "STOP" to the Super Robot
            String stopCommand = "STOP";
            DatagramPacket stopPacket = new DatagramPacket(stopCommand.getBytes(), stopCommand.length(), robotAddress, ROBOT_PORT);
            socket.send(stopPacket);

            // Receive the reply packet with payload "OK" from the Super Robot
            byte[] buffer = new byte[256];
            DatagramPacket replyPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(replyPacket);
            String replyPayload = new String(replyPacket.getData(), 0, replyPacket.getLength());

            // Calculate r1 based on received RSSI
            int receivedRSSI = Integer.parseInt(replyPayload);
            double C = 56.0; // Example value, adjust as needed
            double N = 2.5; // Example value, adjust as needed
            double r1 = Math.pow(10, (C - receivedRSSI) / (10 * N));


            // Send r1, x1, and y1 to Husam's computer
            double x1 = 1; // assumtion
            double y1 = 1; // assumtion
            String dataToSend = r1 + "," + x1 + "," + y1; // Replace x1 and y1 with actual values
            InetAddress husamAddress = InetAddress.getByName("193.188.41.1");
            DatagramPacket sendPacket = new DatagramPacket(dataToSend.getBytes(), dataToSend.length(), husamAddress, HUSAM_PORT);
            socket.send(sendPacket);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
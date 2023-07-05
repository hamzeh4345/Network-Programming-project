// Program 4: Dr Kamal's computer and LAB's computer
import java.net.*;
 import java.io.*;
public class Program4 {
    private static final int KAMAL_PORT = 5001;
    private static final int LAB_PORT = 8000;

    public static void main(String[] args) {
        try {
            // Dr Kamal's computer
            DatagramSocket kamalSocket = new DatagramSocket(KAMAL_PORT);
            byte[] kamalBuffer = new byte[1024];
            DatagramPacket kamalPacket = new DatagramPacket(kamalBuffer, kamalBuffer.length);
            kamalSocket.receive(kamalPacket);
            String kamalData = new String(kamalPacket.getData(), 0, kamalPacket.getLength());
            System.out.println("Dr Kamal's computer received position: " + kamalData);

            // LAB's computer
            DatagramSocket labSocket = new DatagramSocket(LAB_PORT);
            byte[] labBuffer = new byte[1024];
            DatagramPacket labPacket = new DatagramPacket(labBuffer, labBuffer.length);
            labSocket.receive(labPacket);
            String labData = new String(labPacket.getData(), 0, labPacket.getLength());
            System.out.println("LAB's computer received position: " + labData);

            kamalSocket.close();
            labSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
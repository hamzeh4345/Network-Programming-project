// Program 3: Husam's computer
import java.net.*;
 import java.io.*;
 public class Program3 {
    private static final int HUSAM_PORT = 7001;
    private static final int KAMAL_PORT = 5000;
    private static final int LAB_PORT = 8000;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(HUSAM_PORT);

            // Receive r1, x1, and y1 from Dr Kamal's computer
            byte[] buffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePacket);
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] dataArr = receivedData.split(",");
             double r1 = Double.parseDouble(dataArr[0]);
             double x1 = Double.parseDouble(dataArr[1]);
            double y1 = Double.parseDouble(dataArr[2]);

            // Receive r2, x2, and y2 from Husam's computer
            byte[] buffer2 = new byte[1024];
            DatagramPacket receivePacket2 = new DatagramPacket(buffer2, buffer2.length);
            socket.receive(receivePacket2);
            String receivedData2 = new String(receivePacket2.getData(), 0, receivePacket2.getLength());
            String[] dataArr2 = receivedData2.split(",");
            double r2 = Double.parseDouble(dataArr2[0]);
            double x2 = Double.parseDouble(dataArr2[1]);
            double y2 = Double.parseDouble(dataArr2[2]);

            // Receive r3, x3, and y3 from LAB's computer
            byte[] buffer3 = new byte[1024];
            DatagramPacket receivePacket3 = new DatagramPacket(buffer3, buffer3.length);
            socket.receive(receivePacket3);
            String receivedData3 = new String(receivePacket3.getData(), 0, receivePacket3.getLength());
            String[] dataArr3 = receivedData3.split(",");
            double r3 = Double.parseDouble(dataArr3[0]);
            double x3 = Double.parseDouble(dataArr3[1]);
            double y3 = Double.parseDouble(dataArr3[2]);

            // Solve location equations to determine the position of the Super Robot
            double A = 2 * (x1 - x3);
            double B = 2 * (y1 - y3);
            double C = Math.pow(r3, 2) - Math.pow(r1, 2) - Math.pow(x3, 2) + Math.pow(x1, 2) - Math.pow(y3, 2) + Math.pow(y1, 2);

            double D = 2 * (x2 - x3);
            double E = 2 * (y2 - y3);
            double F = Math.pow(r3, 2) - Math.pow(r2, 2) - Math.pow(x3, 2) + Math.pow(x2, 2) - Math.pow(y3, 2) + Math.pow(y2, 2);

            double x = (C * E - F * B) / (A * E - D * B);
            double y = (C * D - A * F) / (B * D - E * A);

            // Send Super Robot position to Dr Kamal's computer
            String robotPosition = x + "," + y;
            InetAddress kamalAddress = InetAddress.getByName("193.188.43.1");
            DatagramPacket kamalPacket = new DatagramPacket(robotPosition.getBytes(), robotPosition.length(), kamalAddress, KAMAL_PORT);
            socket.send(kamalPacket);

            // Send Super Robot position to LAB's computer
            InetAddress labAddress = InetAddress.getByName("193.188.42.1");
            DatagramPacket labPacket = new DatagramPacket(robotPosition.getBytes(), robotPosition.length(), labAddress, LAB_PORT);
            socket.send(labPacket);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

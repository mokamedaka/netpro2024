import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();

            // サーバのアドレスとポートを指定
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            // 送信メッセージを作成
            String message = "abc";
            byte[] sendBuffer = message.getBytes();

            // メッセージをサーバに送信
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);
            System.out.println("送信メッセージ: " + message);

            // サーバからの返信メッセージを受信
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(recePacket);

            String responseMessage = new String(recePacket.getData(), 0, receivePacket.getLength());
            System.out.println("返信メッセージ: " + responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

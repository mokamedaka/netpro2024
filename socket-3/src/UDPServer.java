import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9876); // サーバのポートを指定

            while (true) {
                // クライアントからのメッセージを受信する
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(recePacket);

                String message = new String(recePacket.getData(), 0, receivePacket.getLength());
                System.out.println("受信メッセージ: " + message);

                // 受信メッセージを大文字に変換
                String responseMessage = message.toUpperCase();

                // クライアントのアドレスとポートを取得
                String clientAddress = receivePacket.getAddress().getHostAddress();
                int clientPort = receivePacket.getPort();

                // 変換したメッセージをクライアントに返信
                byte[] sendBuffer = responseMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getSocketAddress());
                socket.send(sendPacket);

                System.out.println("送信メッセージ: " + responseMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
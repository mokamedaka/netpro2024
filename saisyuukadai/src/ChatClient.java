import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter your name:");
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name is required.");
            System.exit(1);
        }

        JFrame frame = new JFrame("Chat Client - CRT Style");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK); // Black background

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(Color.BLACK); // Black background
        textPane.setForeground(Color.GREEN); // Green text
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Monospaced font
        StyledDocument doc = textPane.getStyledDocument();
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK); // Black background
        JTextField textField = new JTextField();
        textField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textField.setForeground(Color.GREEN); // Green text
        textField.setBackground(Color.BLACK); // Black background
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(Color.BLACK); // Black background
        sendButton.setForeground(Color.GREEN); // Green text
        panel.add(textField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);
        frame.add(panel, BorderLayout.SOUTH);

        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            ActionListener sendAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String message = name + ": " + textField.getText();
                    out.println(message);
                    textField.setText("");
                }
            };
            textField.addActionListener(sendAction);
            sendButton.addActionListener(sendAction);

            new Thread(new Runnable() {
                public void run() {
                    String message;
                    try {
                        while ((message = in.readLine()) != null) {
                            boolean isSender = message.startsWith(name + ": ");
                            appendMessage(doc, message, isSender ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendMessage(StyledDocument doc, String message, int alignment) {
        try {
            SimpleAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setAlignment(set, alignment);
            StyleConstants.setForeground(set, Color.GREEN); // Green text
            StyleConstants.setFontFamily(set, "Monospaced");
            StyleConstants.setFontSize(set, 14);

            int length = doc.getLength();
            doc.insertString(length, message + "\n", set);
            doc.setParagraphAttributes(length, message.length(), set, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}

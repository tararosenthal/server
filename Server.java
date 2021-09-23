package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;
    private final int backlog;
    private final Application application;
    private boolean exit = false;

    public Server(int port, int backlog, Application application) {
        this.port = port;
        this.backlog = backlog;
        this.application = application;
    }

    public void interact() {
        String address = "127.0.0.1";
        try (ServerSocket server = new ServerSocket(port, backlog, InetAddress.getByName(address))) {
            System.out.println("Server started!");

            while (!exit) {
                try (Socket socket = server.accept();
                     DataInputStream input = new DataInputStream(socket.getInputStream());
                     DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

                    String commandInput = input.readUTF();
                    System.out.printf("Received: %s\n", commandInput);

                    String commandOutput = getCommandOutputFromCommandInput(commandInput);
                    output.writeUTF(commandOutput);
                    System.out.printf("Sent: %s\n", commandOutput);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getCommandOutputFromCommandInput(String commandInput) {
        if ("exit".equals(commandInput)) {
            exit = true;
            return "OK";
        } else {
            return application.executeCommandAndReturnOutput(commandInput);
        }
    }
}
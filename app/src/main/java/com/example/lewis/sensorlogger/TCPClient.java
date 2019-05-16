package com.example.lewis.sensorlogger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    private Socket socket;
    private OutputStream outputStream;

    public void send(final String message) {
        try {
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {e.printStackTrace();}
    }

    public void connectToIPWithPort(InetAddress serverAddress, int serverPort) throws IOException {
        if (isConnected()) disconnect();
        socket = new Socket(serverAddress, serverPort);
        outputStream = socket.getOutputStream();
    }

    public void disconnect() {
        if (socket != null) {
            closeSocket();
            socket = null;
        }
    }

    private void closeSocket() {
        try {socket.close();}
        catch (IOException e) {e.printStackTrace();}
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public String getConnectionMessage() {
        return isConnected()? "Connected to " + socket.getInetAddress(): "Not Connected";
    }
}


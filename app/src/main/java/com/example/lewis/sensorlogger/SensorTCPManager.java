package com.example.lewis.sensorlogger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SensorTCPManager {
    TCPClient client;

    public SensorTCPManager() {
        client = new TCPClient();
        SensorLogTime.setStartMillis(System.currentTimeMillis());
    }

    public void connectToIPWithPort(InetAddress ip, int port) {
        try {client.connectToIPWithPort(ip, port);}
        catch (UnknownHostException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    public void disconnect() {
        client.disconnect();
    }

    public void insert(SensorSample sample, String tableName) {
        StringBuilder output = new StringBuilder(tableName).append(", ");

        for (String i: sample.getAll())
            output.append(i).append(", ");

        output.deleteCharAt(output.length()-2);
        if (client.isConnected()) client.send(output.toString());
    }

    public String getConnectionMessage() {
        return client.getConnectionMessage();
    }
}


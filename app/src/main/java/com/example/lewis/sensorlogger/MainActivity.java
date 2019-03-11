package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.InetAddress;

public class MainActivity extends Activity {
    private SensorTCPManager sensorTCPManager;
    private AccelerationSensorLogger accelerationSensorLogger;
    private RotationSensorLogger rotationSensorLogger;
    ProgressDialog progressDialog;
    EditText serverIp;
    EditText serverPort;
    Button connect;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorTCPManager = new SensorTCPManager();

        accelerationSensorLogger = new AccelerationSensorLogger(this, sensorTCPManager);
        rotationSensorLogger = new RotationSensorLogger(this, sensorTCPManager);

        accelerationSensorLogger.startUp();
        rotationSensorLogger.startUp();

        serverIp = findViewById(R.id.serverIp);
        serverPort = findViewById(R.id.serverPort);
        connect = findViewById(R.id.connect);
        connect.setOnClickListener(new ConnectButton());
    }

    private class ConnectButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            new SocketTask().execute();
            progressDialog = ProgressDialog.show(MainActivity.this, "Loading..", "Please Wait...", true, false);

        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        accelerationSensorLogger.startUp();
        rotationSensorLogger.startUp();
    }

    @Override
    protected void onPause(){
        super.onPause();
        accelerationSensorLogger.shutDown();
        rotationSensorLogger.shutDown();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        accelerationSensorLogger.shutDown();
        rotationSensorLogger.shutDown();
        sensorTCPManager.disconnect();
    }

    private class SocketTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                initializeClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
            accelerationSensorLogger.start();
            rotationSensorLogger.start();
            return null;
        }

        private void initializeClient() throws Exception {
            InetAddress ip = InetAddress.getByName(serverIp.getText().toString());
            int port = Integer.parseInt(serverPort.getText().toString());
            sensorTCPManager.connectToIPWithPort(ip, port);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (MainActivity.this.progressDialog != null) {
                MainActivity.this.progressDialog.dismiss();
            }

            Toast.makeText(MainActivity.this, sensorTCPManager.getConnectionMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

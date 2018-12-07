package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//TODO DataViewActivity needs major extract method and extract class refactoring.
public class DataViewActivity extends Activity {
    private static String TAG = DataViewActivity.class.getName();
    private SensorLogManager sensorLogManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataview);
        //ScrollView verticalScrollView = findViewById(R.id.dataVerticalScroll);
        HorizontalScrollView horizontalScrollView = findViewById(R.id.dataHorizontalScroll);

        sensorLogManager = new SensorLogManager(this);
        ArrayList<SensorSample> sensorSamples = new ArrayList<>();
        sensorSamples.addAll(sensorLogManager.selectAllFromTable(AccelerationSensorTable.name, AccelerationSensorTable.columns));
        sensorSamples.addAll(sensorLogManager.selectAllFromTable(SwipeSensorTable.name, SwipeSensorTable.columns));
        sensorSamples.addAll(sensorLogManager.selectAllFromTable(TapSensorTable.name, TapSensorTable.columns));

        Collections.sort(sensorSamples, new Comparator<SensorSample>() {
            @Override
            public int compare(SensorSample sample1, SensorSample sample2) {
                return Long.compare(sample1.timeStamp, sample2.timeStamp);
            }
        });

        int totalNumberColumns = SensorLogInformation.totalColumns;
        int totalNumberRows = sensorSamples.size();
        GridLayout grid = new GridLayout(this);
        grid.setRowCount(totalNumberRows);
        grid.setColumnCount(totalNumberColumns);

        TextView[][] dataToDisplay = new TextView[totalNumberRows][totalNumberColumns];


        for (int row = 0; row < totalNumberRows; ++row) {
            String[] sensorSample = sensorSamples.get(row).getAll();
            int columnOffset = totalNumberColumns - sensorSample.length;

            for (int column = 0; column < totalNumberColumns; ++column) {
                dataToDisplay[row][column]= new TextView(this);
                if (column < sensorSample.length) {
                    dataToDisplay[row][column].setText(sensorSample[column]);
                }
                else {
                    dataToDisplay[row][column].setText("");
                }
                grid.addView(dataToDisplay[row][column], 300, 50);
            }
        }

        horizontalScrollView.addView(grid);
    }
}

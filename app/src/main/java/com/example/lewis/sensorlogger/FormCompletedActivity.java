package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FormCompletedActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcompleted);
        findViewById(R.id.dataButton).setOnClickListener(new DataButton());
    }

    private class DataButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent formCompletedIntent = new Intent(FormCompletedActivity.this, DataViewActivity.class);
            FormCompletedActivity.this.startActivity(formCompletedIntent);
        }
    }
}

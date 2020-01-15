package com.example.android.fastsaveimplementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appizona.yehiahd.fastsave.FastSave;

public class SettingsActivity extends AppCompatActivity {

    String minimum;
    String maximum;
    public EditText mMin;
    public EditText mMax;
    public static final String MIN = "1";
    public static final String MAX = "10";

    @Override
    public void onBackPressed() {
        String minString = mMin.getText().toString();
        minimum = minString;
        FastSave.getInstance().saveString(MIN, minimum);

        String maxString = mMax.getText().toString();
        maximum = maxString;
        FastSave.getInstance().saveString(MAX, maximum);
        Toast.makeText(getBaseContext(), "Settings saved", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        setContentView(R.layout.activity_settings);

        mMin = findViewById(R.id.edittext_min);
        mMax = findViewById(R.id.edittext_max);

        String minEditText = FastSave.getInstance().getString(MIN, "1");
        String maxEditText = FastSave.getInstance().getString(MAX, "10");
        mMin.setText(minEditText);
        mMax.setText(maxEditText);

        FrameLayout backArrow = findViewById(R.id.settings_back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView aboutApp = findViewById(R.id.textview_about_app);
        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchAboutActivity = new Intent(SettingsActivity.this, AboutActivity.class);
                startActivity(launchAboutActivity);
            }
        });
    }
}

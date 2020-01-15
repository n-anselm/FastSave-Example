package com.example.android.fastsaveimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appizona.yehiahd.fastsave.FastSave;

import java.util.Random;

import static com.example.android.fastsaveimplementation.SettingsActivity.MAX;
import static com.example.android.fastsaveimplementation.SettingsActivity.MIN;

public class MainActivity extends AppCompatActivity {

    String min;
    String max;
    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        setContentView(R.layout.activity_main);

        FrameLayout settings = findViewById(R.id.settings_icon);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startSettingsActivity = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(startSettingsActivity);
            }
        });

        number = findViewById(R.id.textview_number);
        TextView run = findViewById(R.id.textview_run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                min = FastSave.getInstance().getString(MIN, "1");
                max = FastSave.getInstance().getString(MAX, "10");

                int minn = Integer.parseInt(min);
                int maxx = Integer.parseInt(max);

                // Generate a random number
                if (maxx > minn) {
                    Random randomNum = new Random();
                    int randomNumber = randomNum.nextInt(maxx - minn + 1) + minn; // Max and min inputs determine the range
                    number.setText(Integer.toString(randomNumber));
                }
                if (minn > maxx) {
                    Toast.makeText(getBaseContext(), "Maximum is less than minimum", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

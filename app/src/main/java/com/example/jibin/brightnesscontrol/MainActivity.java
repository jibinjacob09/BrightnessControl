package com.example.jibin.brightnesscontrol;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.provider.Settings.SettingNotFoundException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


        private SeekBar seekBar;
        private TextView txtview;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //Window window = getWindow();
         //   window.addFlags(FLAG_NOT_TOUCHABLE);

            setContentView(R.layout.activity_main);
            seekBar = (SeekBar) findViewById(R.id.seekBar1);
            seekBar.setMax(255);

            txtview = (TextView) findViewById(R.id.valtxt);

            float curBrightnessValue = 0;
            try {
                curBrightnessValue = android.provider.Settings.System.getInt(
                        getContentResolver(),
                        android.provider.Settings.System.SCREEN_BRIGHTNESS);
            } catch (SettingNotFoundException e) {
                e.printStackTrace();
            }

            int screen_brightness = (int) curBrightnessValue;


            seekBar.setProgress(screen_brightness);
            seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                int progress = 0;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progresValue,
                                              boolean fromUser) {
                    progress = progresValue;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // Do something here,
                    // if you want to do anything at the start of
                    // touching the seekbar
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    android.provider.Settings.System.putInt(getContentResolver(),
                            android.provider.Settings.System.SCREEN_BRIGHTNESS,
                            (progress));
                    txtview.setText(Integer.toString(progress));
                }
            });
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {


            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.


            return super.onOptionsItemSelected(item);
        }
    }



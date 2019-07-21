package ru.itx.simpleclock;

import android.graphics.Point;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Minimalistic style clock. May be used by as home clock, or teacher clock,
 * or astronomer clock and so on...
 *
 * Created by d.yacenko on 04/09/2018
 * @author d.yacenko
 *
 */

public class MainActivity extends AppCompatActivity {
    TextView clock_text;
    public static int colorNum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        clock_text=findViewById(R.id.clock_text);
        new MyCountDownTimer(Long.MAX_VALUE,200).start();
        clock_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(MainActivity.colorNum) {
                    case 0: clock_text.setTextColor(0xFFFF0000); colorNum++;break;
                    case 1: clock_text.setTextColor(0xFFAA0808); colorNum++;break;
                    case 2: clock_text.setTextColor(0xFF0F0000); colorNum++;break;
                    case 3: clock_text.setTextColor(0xFF00FF00); colorNum++;break;
                    case 4: clock_text.setTextColor(0xFF08AA08); colorNum++;break;
                    case 5: clock_text.setTextColor(0xFF000C00); colorNum++;break;
                    case 6: clock_text.setTextColor(0xFFFFFFFF); colorNum++;break;
                    case 7: clock_text.setTextColor(0xFFAAAAAA); colorNum++;break;
                    case 8: clock_text.setTextColor(0xFF0A0A0A); colorNum=0;break;
                }
            }
        });
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        clock_text.setTextSize(TypedValue.COMPLEX_UNIT_PX,(int)(width*0.2));
        hideSystemUI();
    }
    class MyCountDownTimer extends CountDownTimer{

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            long uptime = System.currentTimeMillis();
            long days = TimeUnit.MILLISECONDS.toDays(uptime);
            uptime -= TimeUnit.DAYS.toMillis(days);
            long hours = TimeUnit.MILLISECONDS.toHours(uptime);
            uptime -= TimeUnit.HOURS.toMillis(hours);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(uptime);
            uptime -= TimeUnit.MINUTES.toMillis(minutes);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(uptime);
            hours+=3;
            clock_text.setText(""+String.format("%02d", hours)+":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds));
        }

        @Override
        public void onFinish() {

        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }

}

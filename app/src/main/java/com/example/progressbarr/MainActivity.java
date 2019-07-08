package com.example.progressbarr;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog myProgress;
    int progressStatus;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view) {
        myProgress = new ProgressDialog(view.getContext());
        myProgress.setCancelable(true);
        myProgress.setMessage("File downloading ...");
        myProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        myProgress.setProgress(0);
        myProgress.setMax(100);
        myProgress.show();

        progressStatus = 0;
        handler = new Handler();

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            myProgress.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);

                        if (progressStatus >= 100)
                            myProgress.dismiss();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}

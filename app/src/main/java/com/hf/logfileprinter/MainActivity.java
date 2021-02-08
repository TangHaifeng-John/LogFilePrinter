package com.hf.logfileprinter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.annotation.Nullable;

import com.hf.library.FileLog;
import com.hf.library.LogConfig;

import java.io.File;

public class MainActivity extends Activity {


    private  int index=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        LogConfig logConfig = new LogConfig.Builder()
                .absolutePath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "filelog" + File.separator + "test.log")
                .fileSize(1 * 1024 * 1024)
                .build();
        FileLog.init(logConfig);
        findViewById(R.id.print).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){

                            try {
                                FileLog.e("test", new Exception());
                                FileLog.i("testtesttesttesttesttesttesttesttesttest"+index++);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });
    }
}

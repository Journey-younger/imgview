package com.example.journey.imgview;

import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private TimePicker YhnTime;
    private Button yhnbutton, yhnbutton2, yhnbutton3;
    private TextView yhnView;
    private android.os.Handler handler;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YhnTime = (TimePicker) findViewById(R.id.yhntime);
        yhnView = (TextView) findViewById(R.id.firstView);


        TimePickerListener timePickerListener = new TimePickerListener();
        YhnTime.setOnTimeChangedListener(timePickerListener);
        yhnbutton = (Button) findViewById(R.id.runbutton);
        ButtonListenter buttonListenter = new ButtonListenter();
        yhnbutton.setOnClickListener(buttonListenter);

        yhnbutton2 = (Button) findViewById(R.id.runbutton2);
        ButtonListenter2 buttonListenter2 = new ButtonListenter2();
        yhnbutton2.setOnClickListener(buttonListenter2);

        yhnbutton3 = (Button) findViewById(R.id.runbutton3);
        yhnbutton3.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
//启动线程
                                              Thread thread = new YhnThread1();
                                              thread.start();
                                          }
                                      }

        );
        handler = new myHandler();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.journey.imgview/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.journey.imgview/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }


    class ButtonListenter implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            //setclass的第一个参数是一个Context对象
            //Context是一个类，Activity是Context类的子类，也就是说所有的Activity对象都可以向上转型为Context对象
            //setClass对象的第二个类是一个Class对象，应该传入需要启动的Activity类的class对象
            intent.setClass(MainActivity.this, newactivity.class);
            //存入Intent数据
            intent.putExtra("com.example.journey.imgview.age", 30);

            startActivity(intent);
        }
    }

    //在一个应用程序当中，主线程通常用于接收用户的输入，以及将运算结果反馈给用户
    //所以说对于一些可能会产生阻塞的操作，必须防止在work Thred当中
    class ButtonListenter2 implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            //启动线程
            Thread thread = new YhnThred();
            thread.start();
        }
    }

    class TimePickerListener implements TimePicker.OnTimeChangedListener {

        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            yhnView.setText("小时：" + hourOfDay + "分钟：" + minute);
        }
    }

    class myHandler extends android.os.Handler {

        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            String s = (String) msg.obj;
            yhnView.setText(s);
        }
    }

    //线程类
    class YhnThred extends Thread {

        @Override
        public void run() {
            try {
                //休眠2秒
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String s = "模拟网络数据";
            Message message = handler.obtainMessage();
            message.obj = s;
            //向消息队列发送费消息
            //sendMessage()方法，无论线程当中发送的市Work Thread当中发送都可以。
            handler.sendMessage(message);

        }
    }

    class YhnThread1 extends Thread {
        String ss="测试一下！";
        @Override
        public void run() {
            try {
                Thread.sleep(2 * 1000);
                ss="走到这改一点再改！";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //以下方法可以在WorkerThread中操作MainThread的控件
            //原理：Runnable Run方法将代码块放到MainThread中执行。
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    yhnView.setText(ss);
                }
            };
            handler.post(runnable);

        }
    }
}

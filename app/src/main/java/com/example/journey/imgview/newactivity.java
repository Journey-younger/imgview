package com.example.journey.imgview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by journey on 16/1/8.
 */
public class newactivity extends Activity{
    private TextView yhnview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity);
        //取出Intent数据
        Intent intent=getIntent();
        int age=intent.getIntExtra("com.example.journey.imgview.age",10);

        yhnview1=(TextView)findViewById(R.id.yhnView1);
        yhnview1.setText(Integer.toString(age));


    }

}

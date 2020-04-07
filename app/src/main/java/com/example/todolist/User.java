
package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class User extends AppCompatActivity {

    EditText et1;
    Button bt1;
    CheckBox cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        et1=(EditText)findViewById(R.id.et1);
        bt1=(Button)findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIten();
            }
        });
    }
    public void addIten()
    {
        Intent intent=new Intent(User.this,MainActivity.class);
        intent.putExtra("work",et1.getText().toString());
        intent.putExtra("checkbox",cb.isChecked());
        startActivity(intent);
    }
}

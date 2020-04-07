package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Task> task;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = ((MyApplication)this.getApplication()).getTask();

        Bundle i=getIntent().getExtras();
        if(i!=null) {

            ToDoTask adapter = new ToDoTask(this, R.layout.task_list, task);

            String s="",st = "";
            st = i.getString("work");

            Calendar calendar = Calendar.getInstance();

            s = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

            task.add(new Task(s, st));



            listView = (ListView) findViewById(R.id.listView);

            listView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(MainActivity.this,User.class);
        startActivity(intent);

        return true;
    }

    class ToDoTask extends ArrayAdapter<Task>
    {
        List<Task> task;
        Context context;
        int resource;

        public ToDoTask(Context context, int resource, List<Task> task)
        {
            super(context,resource,task);
            this.context=context;
            this.resource=resource;
            this.task=task;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            SharedPreferences sp=getPreferences(Context.MODE_PRIVATE);
            boolean ch=sp.getBoolean("checkbox",false);
            LayoutInflater layoutInflater=LayoutInflater.from(context);

            final View view= layoutInflater.inflate(resource,null,false);

            final TextView textView=view.findViewById(R.id.date);
            final TextView textView1=view.findViewById(R.id.desc);
            final ConstraintLayout bgcolor=view.findViewById(R.id.bgcolor);
            final Button button=view.findViewById(R.id.del);
            final CheckBox done=view.findViewById(R.id.done);
            final TextView status=view.findViewById(R.id.status);

            Task tasks=task.get(position);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(position);
                }
            });




            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(done.isChecked())
                    {
                        SharedPreferences sp=getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putBoolean("checkbox",done.isChecked());
                        editor.commit();
                        int color=view.getResources().getColor(R.color.green);
                        bgcolor.setBackgroundColor(color);
                        status.setText(R.string.completed);
                    }
                    else
                    {
                        int color=view.getResources().getColor(R.color.red);
                        bgcolor.setBackgroundColor(color);
                        status.setText(R.string.blank);
                    }
                }
            });

            textView.setText(tasks.getDate());
            textView1.setText(tasks.getDescription());
            return view;
        }


        private void deleteItem(int i)
        {
            task.remove(i);
            notifyDataSetChanged();
        }
    }
}

package com.example.myapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView1, imageView2,imageView3;
    TextView textView1, textView2,txt;
    ListView listView1;



    String[] ListElements = new String[]{};


    Integer[] imgid = {R.drawable.play};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("My Music");


        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        txt = (TextView)findViewById(R.id.txt);
        listView1 = (ListView)findViewById(R.id.listView1);


        final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (Main2Activity.this, android.R.layout.simple_list_item_1,ListElementsArrayList);





        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

          txt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  final AlertDialog.Builder alert  = new AlertDialog.Builder(Main2Activity.this);
                  View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);
                  final  EditText text_inputText = (EditText)mView.findViewById(R.id.txt_input);
                  Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
                  Button btn_ok = (Button)mView.findViewById(R.id.btn_ok);

                  alert.setView(mView);
                  final AlertDialog alertDialog = alert.create();
                  btn_cancel.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          alertDialog.dismiss();
                      }
                  });
                  btn_ok.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (text_inputText.getText().toString().isEmpty()){
                              Toast.makeText(Main2Activity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                          }
                          else {
                              Toast.makeText(Main2Activity.this, "Playlist Created", Toast.LENGTH_SHORT).show();
                               String s = text_inputText.getText().toString();
                               ListElementsArrayList.add(s);
                            adapter.notifyDataSetChanged();
                              listView1.setAdapter(adapter);
                              alertDialog.dismiss();

                          }

                      }

                  });

                  alertDialog.show();

              }
          });


    }



}

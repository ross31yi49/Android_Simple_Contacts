package com.example.supi.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ContactActivity extends AppCompatActivity {

    //Const
    private static final int CHANGE_TO_ADD = 0;
    private static final int CHANGE_TO_Detail = 1;


    private ListView  listView;
    private String []  list ;
    private ArrayAdapter<String> listAdapter;
    private Button addBtn;
    private HashMap<String,personData> listmap;

    //Demo
    //private OrientationEventListener oel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        load();

        /*oel = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {

                Log.w("螢幕","~~~~~~~~~~~~~");
            }
        };

        oel.enable();*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        createList();
        listView = (ListView)findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(listAdapter);


        //Add function
        addBtn = (Button)findViewById(R.id.addbtn);
        addBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                changeToAdd();
            }
        });

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContactActivity.this, Detail.class);
                intent.putExtra("clickName", parent.getItemAtPosition(position).toString());
                intent.putExtra("detailListMap", listmap);
                startActivityForResult(intent,CHANGE_TO_Detail);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == CHANGE_TO_ADD){

                HashMap<String,personData> changedList;
                changedList = (HashMap<String,personData>)data.getSerializableExtra("changedList");
                listmap = changedList;
            }
            if(requestCode == CHANGE_TO_Detail){
                HashMap<String,personData> changedList;
                changedList = (HashMap<String,personData>)data.getSerializableExtra("changedList");
                listmap = changedList;
            }
        }
    }

    private void load(){

        String dataGetLine;
        personData readIn;
        String [] cutData;
        InputStreamReader inputStreamReader = null;

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.data);
            inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufReader = new BufferedReader(inputStreamReader);
            listmap = new HashMap();

            while((dataGetLine = bufReader.readLine()) != null){
                cutData = dataGetLine.split(" ");
                readIn = new personData(cutData);
                listmap.put(cutData[0], readIn);
            }
        } catch (FileNotFoundException e) {
            Log.e("IO Error","Can't find this File");
        } catch (IOException e){
            Log.e("IO Error","ReadFile Error");
        }
    }

    private void createList(){
        int counter = 0;
        list = new String[listmap.size()];
        personData setList;

        for(Object Key:listmap.keySet()){
            setList = listmap.get(Key);
            list[counter] = new String(setList.getName());
            counter++;
        }
    }

    private void changeToAdd(){
        Intent intent = new Intent();
        intent.setClass(ContactActivity.this, Add.class);
        intent.putExtra("listmap", listmap);
        startActivityForResult(intent, CHANGE_TO_ADD);
    }
}



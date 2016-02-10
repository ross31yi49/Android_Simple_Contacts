package com.example.supi.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Detail extends AppCompatActivity {

    private HashMap<String,personData> detailListMap = new HashMap();
    private TextView nameView;
    private TextView phoneView;
    private TextView emailView;
    private Button returnBtn;
    private Button deleteBtn;
    private Button resetBtn;
    private static final int CHANG_TO_RESET = 0;
    private String detailName;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        intent = getIntent();
        detailListMap = (HashMap<String,personData>)intent.getSerializableExtra("detailListMap");
        detailName = intent.getStringExtra("clickName");

    }

    @Override
    protected void onResume() {
        super.onResume();


        //show person detail data
        nameView = (TextView)findViewById(R.id.detailTitle);
        phoneView = (TextView)findViewById(R.id.detailphone);
        emailView = (TextView)findViewById(R.id.detailemail);
        setALLText(detailListMap,detailName);


        //reSet function
        resetBtn = (Button) findViewById(R.id.setbtn);
        resetBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reset = new Intent(Detail.this, Reset.class);
                reset.putExtra("sendListMap", detailListMap);
                reset.putExtra("setNameKey", detailName);
                startActivityForResult(reset, CHANG_TO_RESET);
            }
        });



        //delete function
        deleteBtn = (Button) findViewById(R.id.deletebtn);
        deleteBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailListMap.remove(detailName);
                returnToContact();
            }
        });


        //retrun ContactActivity
        returnBtn = (Button) findViewById(R.id.detailReturnBtn);
        returnBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                returnToContact();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == CHANG_TO_RESET){
                Toast.makeText(this,"修改完成",Toast.LENGTH_LONG);
                detailListMap = (HashMap<String,personData>)data.getSerializableExtra("changedList");
            }
        }
    }

    private void setALLText(HashMap<String,personData> map,String Key){

        nameView.setText(map.get(Key).getName());
        phoneView.setText(map.get(Key).getPhoneNumber());
        emailView.setText(map.get(Key).getEmail());

    }

    private void returnToContact(){
        intent.putExtra("changedList", detailListMap);
        intent.setClass(Detail.this, ContactActivity.class);
        Detail.this.setResult(RESULT_OK, intent);
        Detail.this.finish();
    }
}

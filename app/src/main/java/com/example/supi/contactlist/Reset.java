package com.example.supi.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Reset extends AppCompatActivity {

    private EditText phoneEdt;
    private EditText mailEdt;
    private Button cancleBtn;
    private Button resetBtn;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        title = (TextView) findViewById(R.id.textView6);
        phoneEdt = (EditText) findViewById(R.id.resetNameEditText);
        mailEdt = (EditText) findViewById(R.id.resetMailEditText);
        cancleBtn = (Button) findViewById(R.id.resetCancle);
        resetBtn = (Button) findViewById(R.id.resetYes);
        Intent intent = getIntent();
        final HashMap<String,personData> Sendmap = (HashMap<String,personData>)intent.getSerializableExtra("sendListMap");
        final String mapKey = intent.getStringExtra("setNameKey");


        title.setText(mapKey);

        phoneEdt.setText(Sendmap.get(mapKey).getPhoneNumber());
        mailEdt.setText(Sendmap.get(mapKey).getEmail());

        cancleBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Reset.this.finish();
            }
        });

        resetBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                startToSet(Sendmap,mapKey);
            }
        });

    }
    private void startToSet(HashMap<String,personData> map,String key){

        Intent returnIntent = new Intent(Reset.this,Detail.class);

        String changedPhone = phoneEdt.getText().toString();
        String changedMail = mailEdt.getText().toString();


        if("".equals(changedPhone.trim()) || "".equals(changedMail.trim())){
            Toast.makeText(this,"修改內容不能為空",Toast.LENGTH_LONG);
        }else{

            map.get(key).setPhoneNumber(changedPhone);
            map.get(key).setEmail(changedMail);

            returnIntent.putExtra("changedList", map);
            Reset.this.setResult(RESULT_OK, returnIntent);
            Reset.this.finish();
        }

    }

}

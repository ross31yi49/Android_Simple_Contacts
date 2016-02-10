package com.example.supi.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Add extends AppCompatActivity {

    private EditText nameEdit;
    private EditText phoneEdit;
    private EditText emailEdit;
    private Button returnBtn;
    private Button addBtn;
    private HashMap<String,personData> listmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameEdit = (EditText)findViewById(R.id.nameEdit);
        phoneEdit = (EditText)findViewById(R.id.phoneEdit);
        emailEdit = (EditText)findViewById(R.id.emailEdit);

        Intent intent = getIntent();
        listmap = (HashMap<String, personData>) intent.getSerializableExtra("listmap");


        addBtn = (Button)findViewById(R.id.addCheck);
        addBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNewPerson();
            }
        });

        returnBtn = (Button) findViewById(R.id.addReturn);
        returnBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                returnMainActivity();
            }
        });

    }

    private void returnMainActivity(){
        Intent intent = new Intent(Add.this,ContactActivity.class);
        intent.putExtra("changedList", listmap);
        //startActivity(intent);
        Add.this.setResult(RESULT_OK,intent);
        Add.this.finish();
    }

    private void addNewPerson(){
        personData newData;
        String newName;
        String newPhone;
        String newEmail ;

        newName = nameEdit.getText().toString();
        newPhone = phoneEdit.getText().toString();
        newEmail = emailEdit.getText().toString();

        if("".equals(newName.trim()) || "".equals(newPhone.trim()) || "".equals(newEmail.trim())){
            Toast.makeText(this,"請填寫完整資料",Toast.LENGTH_LONG).show();
        } else{
            newData = new personData(newName,newPhone,newEmail);
            listmap.put(newName,newData);
            Toast.makeText(this,"新增完成",Toast.LENGTH_LONG).show();
            nameEdit.setText("");
            phoneEdit.setText("");
            emailEdit.setText("");
        }
    }
}

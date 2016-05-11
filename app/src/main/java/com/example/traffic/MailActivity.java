package com.example.traffic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MailActivity extends Activity implements View.OnClickListener {

    //Declaring EditText
    private EditText editTextEmail;
    private EditText editTextSubject;
    private EditText editTextMessage;

    //Send button
    private Button buttonSend;
    String s1,s2,s3,s4,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);




        Intent i = getIntent();
        s1 = i.getStringExtra("email");

        s2 = i.getStringExtra("ss");
        s3=i.getStringExtra("subject");

        Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();
        //Initializing the views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        editTextEmail.setText(s1);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextSubject.setText(s3);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        editTextMessage.setText(s2);

        buttonSend = (Button) findViewById(R.id.buttonSend);

        //Adding click listener
        buttonSend.setOnClickListener(this);
    }


    private void sendEmail() {
        //Getting content for email
        String email = editTextEmail.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }
}

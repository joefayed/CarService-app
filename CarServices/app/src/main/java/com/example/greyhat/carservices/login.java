package com.example.greyhat.carservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    Database database = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(login.this, register.class);
                login.this.startActivity(registerIntent);
            }
        });
    }

    public void onbLoginClick(View view) {

        final EditText username = (EditText) findViewById(R.id.etUsername);
        String user = username.getText().toString();
        final EditText password = (EditText) findViewById(R.id.etPassword);
        String pass = password.getText().toString();

        String passcheck = database.searchPass(user);

        if(pass.equals(passcheck))
        {
            Toast success = Toast.makeText(login.this, "Welcome", Toast.LENGTH_SHORT);
            success.show();

            Intent Intent = new Intent(login.this, carSelect.class);
            login.this.startActivity(Intent);
        }
        else
        {
            Toast fail = Toast.makeText(login.this, "Wrong credentials. Please try again.", Toast.LENGTH_SHORT);
            fail.show();

            /*Intent Intent = new Intent(login.this, carSelect.class);
            login.this.startActivity(Intent);*/
        }

    }
}

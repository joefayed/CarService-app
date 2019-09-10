package com.example.greyhat.carservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.String;

public class register extends AppCompatActivity {

    Database database = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button register = (Button) findViewById(R.id.bRegister);
    }


    public void onbRegisterCLick(View view)
    {
        final EditText etname = (EditText) findViewById(R.id.etName);
        final EditText etusername = (EditText) findViewById(R.id.etUsername);
        final EditText etpassword = (EditText) findViewById(R.id.etPassword);
        final EditText etcar = (EditText) findViewById(R.id.etCar);

        String name = etname.getText().toString();
        String username = etusername.getText().toString();
        String pass = etpassword.getText().toString();
        String car = etcar.getText().toString();

        Contact c = new Contact();
        c.setName(name);
        c.setUsername(username);
        c.setPassword(pass);
        c.setCar(car);

        database.insertContact(c);

        String uname = database.retrieveInfo();

        Toast toast = Toast.makeText(register.this, uname, Toast.LENGTH_SHORT);
        toast.show();


        Intent login = new Intent(register.this, login.class);
        register.this.startActivity(login);
    }
}

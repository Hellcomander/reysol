package com.example.reysol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reysol.Classes.Clientes;
import com.example.reysol.Classes.Usuarios;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button btnLogin;
    Usuarios usuarios[];
    Clientes clientes[];

    TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarios = new Usuarios[50];
        clientes = new Clientes[50];

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnRegister = (TextView) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            Intent openRegister = new Intent(this, RegisterActivity.class);
            startActivity(openRegister);
        });

        btnLogin.setOnClickListener(v -> {
            String password = txtPassword.getText().toString();
            String email = txtEmail.getText().toString();

            if(password.equals("") || email.equals("")){
                Toast.makeText(getApplicationContext(), "INGRESA TODOS LOS DATOS", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}
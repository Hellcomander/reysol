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
import com.example.reysol.Models.UsuariosModel;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button btnLogin;
    TextView btnRegister;
    UsuariosModel usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarios = new UsuariosModel(getApplicationContext());

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (TextView) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = txtPassword.getText().toString();
                String email = txtEmail.getText().toString();

                if(password.equals("") || email.equals("")){
                    Toast.makeText(getApplicationContext(), "INGRESA TODOS LOS DATOS", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!usuarios.login(email, password)){
                    Toast.makeText(getApplicationContext(), "CREDENCIALES INCORRECTAS", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(getApplicationContext(), MainViewActivity.class);
                startActivity(i);
            }
        });

        btnRegister.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        usuarios = new UsuariosModel(getApplicationContext());
    }
}
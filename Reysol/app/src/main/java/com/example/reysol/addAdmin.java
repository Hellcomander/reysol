package com.example.reysol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reysol.Models.UsuariosModel;

public class addAdmin extends AppCompatActivity {
    private EditText txtName, txtEmail, txtPassword, txtId;
    private Button btnSign;
    UsuariosModel usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        usuarios = new UsuariosModel(getApplicationContext());

        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtId = (EditText) findViewById(R.id.txtId);
        btnSign = (Button) findViewById(R.id.btnSign);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtName.equals("") || txtEmail.equals("") || txtPassword.equals("") || txtId.equals("")) {
                    Toast.makeText(getApplicationContext(), "INGRESA TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
                    return;
                }
                usuarios.agregarUsuario(
                        Integer.parseInt(txtId.getText().toString()),
                        txtName.getText().toString(),
                        txtEmail.getText().toString(),
                        txtPassword.getText().toString(),
                        1,
                        true
                );
                Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                limpiar();
            }
        });
    }

    public void limpiar(){
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }
}
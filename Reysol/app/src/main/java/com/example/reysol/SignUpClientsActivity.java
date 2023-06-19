package com.example.reysol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reysol.Models.UsuariosModel;

import java.util.logging.Logger;

public class SignUpClientsActivity extends AppCompatActivity {
    private EditText txtName, txtEmail, txtPassword, txtAge, txtPhone, txtAddress, txtCp, txtRfc;
    private Button btnSign;
    UsuariosModel usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_clients);

        usuarios = new UsuariosModel(getApplicationContext());

        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtCp = (EditText) findViewById(R.id.txtPC);
        txtRfc = (EditText) findViewById(R.id.txtRFC);
        btnSign = (Button) findViewById(R.id.btnAddUser);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Register button", Toast.LENGTH_LONG).show();
                if(txtName.equals("") || txtEmail.equals("") || txtPassword.equals("") || txtAge.equals("")
                || txtPhone.equals("") || txtAddress.equals("") || txtCp.equals("") || txtRfc.equals("")){
                    Toast.makeText(getApplicationContext(), "INGRESA TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
                    return;
                }
                usuarios.agregarCliente(
                        txtName.getText().toString(),
                        txtEmail.getText().toString(),
                        txtPassword.getText().toString(),
                        Integer.parseInt(txtAge.getText().toString()),
                        txtPhone.getText().toString(),
                        txtAddress.getText().toString(),
                        txtCp.getText().toString(),
                        txtRfc.getText().toString(),
                        2,
                        true
                );
                Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                limpiar();
            }
        });
    }

    public void limpiar(){
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtAddress.setText("");
        txtAge.setText("");
        txtPhone.setText("");
        txtCp.setText("");
        txtRfc.setText("");
    }
}
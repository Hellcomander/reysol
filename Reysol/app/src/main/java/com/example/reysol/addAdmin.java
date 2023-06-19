package com.example.reysol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reysol.Classes.Paqueterias;
import com.example.reysol.Classes.Usuarios;
import com.example.reysol.Models.UsuariosModel;

public class addAdmin extends AppCompatActivity {
    private EditText txtName, txtEmail, txtPassword, txtId, txtSearch;
    private Button btnSign, btnUpdate, btnDelete, btnSearch;
    UsuariosModel usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        usuarios = new UsuariosModel(getApplicationContext());

        txtSearch = findViewById(R.id.txtSearch);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSearch = findViewById(R.id.btnSearch);
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtId = (EditText) findViewById(R.id.txtId);
        btnSign = (Button) findViewById(R.id.btnSign);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSearch.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "INGRESA UN ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                Usuarios usuario = usuarios.buscar(Integer.parseInt(txtSearch.getText().toString()));
                if (usuario == null){
                    Toast.makeText(getApplicationContext(), "NO SE ENCONTRO", Toast.LENGTH_SHORT).show();
                    return;
                }

                txtId.setText(usuario.getId());
                txtName.setText(usuario.getNombre());
                txtEmail.setText(usuario.getCorreo());
                txtPassword.setText(usuario.getPassword());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
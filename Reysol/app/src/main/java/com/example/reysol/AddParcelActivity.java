package com.example.reysol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reysol.Classes.Paqueterias;
import com.example.reysol.Classes.Productos;
import com.example.reysol.Models.PaqueteriasModel;

public class AddParcelActivity extends AppCompatActivity {
    private TextView txtId, txtName, txtAddress, txtPhone, txtSchedule, txtSearch;
    private Button btnAdd, btnSearch, btnUpdate, btnDelete;
    PaqueteriasModel paqueterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parcel);

        paqueterias = new PaqueteriasModel(getApplicationContext());

        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
        txtSchedule = findViewById(R.id.txtSchedule);
        btnAdd = findViewById(R.id.btnAdd);
        btnSearch = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        txtSearch = findViewById(R.id.txtSearch);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paqueterias.agregar(
                        Integer.parseInt(txtId.getText().toString()),
                        txtName.getText().toString(),
                        txtAddress.getText().toString(),
                        txtPhone.getText().toString(),
                        txtSchedule.getText().toString()
                );
                Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                limpiar();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSearch.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "INGRESA UN ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                Paqueterias paqueteria = paqueterias.buscar(Integer.parseInt(txtSearch.getText().toString()));
                if (paqueteria == null){
                    Toast.makeText(getApplicationContext(), "NO SE ENCONTRO", Toast.LENGTH_SHORT).show();
                    return;
                }
                txtId.setText(paqueteria.getId() + "");
                txtName.setText(paqueteria.getNombre());
                txtAddress.setText(paqueteria.getDireccion());
                txtPhone.setText(paqueteria.getNumeroContacto());
                txtSchedule.setText(paqueteria.getHorarioEntrega());
                //Toast.makeText(getApplicationContext(), "si", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSearch.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "INGRESA UN ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(paqueterias.borrar(Integer.parseInt(txtSearch.getText().toString())))
                {
                    Toast.makeText(getApplicationContext(), "BORRADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                    return;
                }
                else
                    Toast.makeText(getApplicationContext(), "NO SE ENCONTRO", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSearch.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "INGRESA UN ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean u = paqueterias.actualizar(
                        Integer.parseInt(txtSearch.getText().toString()),
                        txtName.getText().toString(),
                        txtAddress.getText().toString(),
                        txtPhone.getText().toString(),
                        txtSchedule.getText().toString()
                );
                if(u)
                    Toast.makeText(getApplicationContext(), "ACTUALIZADO", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "NO SE ENCONTRO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void limpiar(){
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtSchedule.setText("");
    }
}
package com.example.reysol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reysol.Models.PaqueteriasModel;

public class AddParcelActivity extends AppCompatActivity {
    private TextView txtId, txtName, txtAddress, txtPhone, txtSchedule;
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
    }

    public void limpiar(){
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtSchedule.setText("");
    }
}
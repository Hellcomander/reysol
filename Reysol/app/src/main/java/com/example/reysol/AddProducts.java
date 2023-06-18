package com.example.reysol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reysol.Models.ProductosModel;

public class AddProducts extends AppCompatActivity {
    EditText txtId, txtName, txtDescription, txtPrice, txtWeight, txtSizes, txtAddress, txtUrl;
    Button btnSearch, btnUpdate, btnDelete, btnAdd;
    ProductosModel productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        productos = new ProductosModel(getApplicationContext());
        txtId = (EditText) findViewById(R.id.txtId);
        txtName = (EditText) findViewById(R.id.txtName);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        txtWeight = (EditText) findViewById(R.id.txtWeight);
        txtSizes = (EditText) findViewById(R.id.txtSizes);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtUrl = (EditText) findViewById(R.id.txtImg);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos.agregar(
                        Integer.parseInt(txtId.getText().toString()),
                        txtName.getText().toString(),
                        txtDescription.getText().toString(),
                        Double.parseDouble(txtPrice.getText().toString()),
                        Double.parseDouble(txtWeight.getText().toString()),
                        txtSizes.getText().toString(),
                        txtUrl.getText().toString()
                );
                Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
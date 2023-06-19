package com.example.reysol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reysol.Classes.Paqueterias;
import com.example.reysol.Classes.Productos;
import com.example.reysol.Models.ProductosModel;

public class AddProducts extends AppCompatActivity {
    EditText txtId, txtName, txtDescription, txtPrice, txtWeight, txtSizes, txtAddress, txtUrl, txtSearch;
    Button btnSearch, btnUpdate, btnDelete, btnAdd;
    public ProductosModel productos;

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
        txtSearch = findViewById(R.id.txtSearch);
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
                Productos producto = productos.buscar(Integer.parseInt(txtSearch.getText().toString()));
                if (producto == null){
                    Toast.makeText(getApplicationContext(), "NO SE ENCONTRO", Toast.LENGTH_SHORT).show();
                    return;
                }
                txtId.setText(producto.getId() + "");
                txtName.setText(producto.getNombre());
                txtDescription.setText(producto.getDescripcion());
                txtPrice.setText(producto.getPrecio() + "");
                txtWeight.setText(producto.getPeso() + "");
                txtUrl.setText(producto.getUrlImagen());
                txtSizes.setText(producto.getMedidas());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean u = productos.actualizar(
                        Integer.parseInt(txtId.getText().toString()),
                        txtName.getText().toString(),
                        txtDescription.getText().toString(),
                        Double.parseDouble(txtPrice.getText().toString()),
                        Double.parseDouble(txtWeight.getText().toString()),
                        txtSizes.getText().toString(),
                        txtUrl.getText().toString()
                );
                if(u){
                    Toast.makeText(getApplicationContext(), "ACTUALIZADO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos.eliminar(Integer.parseInt(txtSearch.getText().toString()));
                Toast.makeText(getApplicationContext(), "BORRADO", Toast.LENGTH_SHORT).show();
                limpiar();
            }
        });
    }

    public void limpiar(){
        txtId.setText("");
        txtName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        txtWeight.setText("");
        txtUrl.setText("");
        txtSizes.setText("");
    }
}
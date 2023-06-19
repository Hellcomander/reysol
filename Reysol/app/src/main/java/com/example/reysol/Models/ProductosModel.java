package com.example.reysol.Models;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.reysol.Classes.Clientes;
import com.example.reysol.Classes.Productos;
import com.example.reysol.Classes.Usuarios;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductosModel {
    Context context;
    Gson gson;
    int cant;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Productos producto[];

    public ProductosModel(Context context){
        this.context = context;
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("datos", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cant = 0;
        producto = new Productos[20];
        this.rellenar();
    }

    public void agregar(int id, String nombre, String descripcion, double precio, double peso, String medidas, String url){
        producto[cant] = new Productos();
        producto[cant].setId(id);
        producto[cant].setNombre(nombre);
        producto[cant].setDescripcion(descripcion);
        producto[cant].setPeso(peso);
        producto[cant].setPrecio(precio);
        producto[cant].setMedidas(medidas);
        producto[cant].setUrlImagen(url);
        cant++;

        String json = gson.toJson(producto);

        Log.e("PRODUCTOS", json);

        editor.putString("productos", json);
        editor.apply();
    }

    public void rellenar() {
        String json = sharedPreferences.getString("productos", "");
        Log.e("PRODUCTOS ACTUALES", json);
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                producto[i] = new Productos();
                producto[i].setNombre(jsonObject.getString("nombre"));
                producto[i].setId(jsonObject.getInt("id"));
                producto[i].setDescripcion(jsonObject.getString("descripcion"));
                producto[i].setMedidas(jsonObject.getString("medidas"));
                producto[i].setPeso(jsonObject.getDouble("peso"));
                producto[i].setPrecio(jsonObject.getDouble("precio"));
                producto[i].setUrlImagen(jsonObject.getString("urlImagen"));

                cant++;
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }
}

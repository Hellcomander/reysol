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

import java.util.ArrayList;

public class ProductosModel {
    Context context;
    Gson gson;
    int cant;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Productos producto[];

    ComprasModel comprasModel;

    public ProductosModel(Context context){
        this.context = context;
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("datos", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cant = 0;
        producto = new Productos[20];
        this.rellenar();

    }

    public ArrayList<Productos> listar(){
        ArrayList<Productos> productos = new ArrayList<>();
        for(int i = 0; i < cant; i++){
            productos.add(producto[i]);
        }
        return productos;
    }

    public Productos[] listar(){
        return producto;
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

    public String ObtenerNombre(int id_producto){
        for (int i = 0; i < cant; i++){
            if(producto[i].getId() == id_producto) return producto[i].getNombre();
        }
        return "";
    }

    public Productos buscar(int id){
        for(int i = 0; i < cant; i++){
            if(producto[i].getId() == id) return producto[i];
        }
        return null;
    }

    public boolean eliminar(int id){
        boolean encontrado = false;
        int c = 0;
        Productos temp[] = new Productos[20];
        for(int i = 0; i < cant; i++){
            if(producto[i].getId() == id){
                encontrado = true;
                continue;
            }
            temp[c].setNombre(producto[i].getNombre());
            temp[c].setId(producto[i].getId());
            temp[c].setDescripcion(producto[i].getDescripcion());
            temp[c].setMedidas(producto[i].getMedidas());
            temp[c].setPeso(producto[i].getPeso());
            temp[c].setPrecio(producto[i].getPrecio());
            temp[c].setUrlImagen(producto[i].getUrlImagen());
        }

        if(!encontrado) return false;
        cant--;
        producto = temp;

        String json = gson.toJson(producto);

        Log.e("PRODUCTOS", json);

        editor.putString("productos", json);
        editor.apply();
        return true;
    }

    public boolean actualizar(int id, String nombre, String descripcion, double precio, double peso, String medidas, String url){
        for(int i = 0; i < cant; i++){
            if(producto[i].getId() == id){
                producto[i].setId(id);
                producto[i].setNombre(nombre);
                producto[i].setDescripcion(descripcion);
                producto[i].setPeso(peso);
                producto[i].setPrecio(precio);
                producto[i].setMedidas(medidas);
                producto[i].setUrlImagen(url);
                String json = gson.toJson(producto);

                Log.e("PRODUCTOS", json);

                editor.putString("productos", json);
                editor.apply();
                return true;
            }
        }
        return false;
    }
}

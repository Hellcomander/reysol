package com.example.reysol.Models;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.reysol.Classes.Carrito;
import com.example.reysol.Classes.Compras;
import com.example.reysol.Classes.Paqueterias;
import com.example.reysol.Classes.Productos;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ComprasModel {
    Context context;
    Gson gson;
    int cant;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Compras compra[];
    ProductosModel productosModel;

    public  ComprasModel(Context context){
        this.context = context;
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("datos", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cant = 0;
        compra = new Compras[20];
        productosModel = new ProductosModel(context);
        this.rellenar();
    }

    public  void agregar(int id, int id_usuario, int id_carrito, String direccion, double total, int id_producto, int cantidad){
        compra[cant] = new Compras();
        compra[cant].setId(id);
        compra[cant].setIdUsuario(id_usuario);
        compra[cant].setIdCarrito(id_carrito);
        compra[cant].setDireccionEntrega(direccion);
        compra[cant].setTotal(total);
        compra[cant].setIdProducto(id_producto);
        compra[cant].setCantidad(cantidad);
        cant++;

        String json = gson.toJson(compra);

        Log.e("COMPRA", json);

        editor.putString("compras", json);
        editor.apply();
    }

    public ArrayList<String> obtenerCompras(int id_carrito){
        ArrayList<String> compras = new ArrayList<>();
        String nombre = "";
        for (int i = 0; i < cant; i++){
            if(compra[i].getIdCarrito() == id_carrito ){
                nombre = productosModel.ObtenerNombre(compra[i].getIdProducto());
                compras.add(nombre + " x" + compra[i].getCantidad() + " $" + compra[i].getTotal());
            }
        }
        return compras;
    }

    public void rellenar() {
        String json = sharedPreferences.getString("compras", "");
        Log.e("COMPRAS ACTUALES", json);
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                compra[i] = new Compras();
                compra[i].setId(jsonObject.getInt("id"));
                compra[i].setIdUsuario(jsonObject.getInt("idUsuario"));
                compra[i].setIdCarrito(jsonObject.getInt("idCarrito"));
                compra[i].setDireccionEntrega(jsonObject.getString("direccionEntrega"));
                compra[i].setTotal(jsonObject.getDouble("total"));
                compra[i].setIdProducto(jsonObject.getInt("idProducto"));
                compra[i].setCantidad(jsonObject.getInt("cantidad"));

                cant++;
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }
}

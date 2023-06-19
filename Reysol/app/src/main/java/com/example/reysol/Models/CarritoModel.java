package com.example.reysol.Models;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.reysol.Classes.Carrito;
import com.example.reysol.Classes.Compras;
import com.example.reysol.Classes.Paqueterias;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class CarritoModel {
    Context context;
    Gson gson;
    int cant;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Carrito carrito[];

    public CarritoModel(Context context){
        this.context = context;
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("datos", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cant = 0;
        carrito = new Carrito[20];
        this.rellenar();
    }

    public int obtenerCarrito(int id_usuario){
        for(int i = 0; i < cant; i++){
            Toast.makeText(context.getApplicationContext(), String.valueOf(carrito[i].isFinalizado()), Toast.LENGTH_LONG);
            if(carrito[i].getIdUsuario() == id_usuario){
                if(!carrito[i].isFinalizado())
                return carrito[i].getId();
            }
        }
        return agregar(id_usuario, 0, false, "");
    }

    public int agregar(int id_usuario, double total, boolean finalizado, String id_paqueteria){
        int id = (int)(Math.random()*10000000+1);
        carrito[cant] = new Carrito();
        carrito[cant].setId(id);
        carrito[cant].setIdUsuario(id_usuario);
        carrito[cant].setTotal(total);
        carrito[cant].setFinalizado(finalizado);
        carrito[cant].setIdPaqueteria(id_paqueteria);
        cant++;

        String json = gson.toJson(carrito);

        Log.e("CARRITO", json);

        editor.putString("carritos", json);
        editor.apply();

        return id;
    }

    public void finalizar(int id_carrito, String paqueteria){
        for(int i = 0; i < cant; i++){
            carrito[i].setIdPaqueteria(paqueteria);
            carrito[i].setFinalizado(true);
            //if(carrito[i].getId() == id_carrito){
            //    carrito[i].setIdPaqueteria(paqueteria);
             //   carrito[i].setFinalizado(true);
            //}
        }
    }

    public void rellenar() {
        Log.e("XD", "XD");
        String json = sharedPreferences.getString("carritos", "");
        Log.e("CARRITOS ACTUALES", json);
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                carrito[i] = new Carrito();
                carrito[i].setId(jsonObject.getInt("id"));
                carrito[i].setIdUsuario(jsonObject.getInt("idUsuario"));
                carrito[i].setTotal(jsonObject.getDouble("total"));
                carrito[i].setFinalizado(jsonObject.getBoolean("finalizado"));
                carrito[i].setIdPaqueteria(jsonObject.getString("idPaqueteria"));
                cant++;
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }
}

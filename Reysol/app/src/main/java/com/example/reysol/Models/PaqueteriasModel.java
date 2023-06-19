package com.example.reysol.Models;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.reysol.Classes.Paqueterias;
import com.example.reysol.Classes.Productos;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PaqueteriasModel {
    Context context;
    Gson gson;
    int cant;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Paqueterias paqueteria[];

    public PaqueteriasModel(Context context){
        this.context = context;
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("datos", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cant = 0;
        paqueteria = new Paqueterias[20];
        this.rellenar();
    }

    public void agregar(int id, String nombre, String direccion, String numero, String horario){
        paqueteria[cant] = new Paqueterias();
        paqueteria[cant].setId(id);
        paqueteria[cant].setNombre(nombre);
        paqueteria[cant].setDireccion(direccion);
        paqueteria[cant].setNumeroContacto(numero);
        paqueteria[cant].setHorarioEntrega(horario);
        cant++;

        String json = gson.toJson(paqueteria);

        Log.e("PAQUETERIAS", json);

        editor.putString("paqueterias", json);
        editor.apply();
    }

    public String[] obtenerPaqueterias(){
        ArrayList<String> paqueterias = new ArrayList();
        for (int i = 0; i < cant; i++){
            paqueterias.add(paqueteria[i].getNombre());
        }
        //Object[] array = paqueterias.toArray();
        return (paqueterias.toArray(new String[paqueterias.size()]));
    }

    public void rellenar() {
        String json = sharedPreferences.getString("paqueterias", "");
        Log.e("PAQUETERIAS ACTUALES", json);
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.e("OBJETO", jsonObject.toString());
                paqueteria[i] = new Paqueterias();
                paqueteria[i].setNombre(jsonObject.getString("nombre"));
                paqueteria[i].setId(jsonObject.getInt("id"));
                paqueteria[i].setHorarioEntrega(jsonObject.getString("horarioEntrega"));
                paqueteria[i].setDireccion(jsonObject.getString("direccion"));
                paqueteria[i].setNumeroContacto(jsonObject.getString("numeroContacto"));

                cant++;
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }
}

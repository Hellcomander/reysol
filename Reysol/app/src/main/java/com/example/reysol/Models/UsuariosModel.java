package com.example.reysol.Models;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.reysol.Classes.Clientes;
import com.example.reysol.Classes.Paqueterias;
import com.example.reysol.Classes.Usuarios;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsuariosModel {
    Clientes cliente[];
    Usuarios usuario[];
    Context context;
    Gson gson;
    int cant_u;
    int cant_c;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public UsuariosModel(Context context){
        this.context = context;
        gson = new Gson();
        cliente = new Clientes[20];
        usuario = new Usuarios[20];
        sharedPreferences = context.getSharedPreferences("datos", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cant_u = 0;
        cant_c = 0;
        this.rellenar();
    }

    public void agregarUsuario(int id, String nombre, String correo, String password, int nivel, boolean autorizado){
        usuario[cant_u] = new Usuarios();
        usuario[cant_u].setNombre(nombre);
        usuario[cant_u].setCorreo(correo);
        usuario[cant_u].setPassword(password);
        usuario[cant_u].setId(id);
        usuario[cant_u].setNivel(nivel);
        usuario[cant_u].setAutorizado(autorizado);
        cant_u++;

        String json = gson.toJson(usuario);

        Log.e("USUARIOS", json);

        editor.putString("usuarios", json);
        editor.putInt("cant", cant_u);
        editor.apply();
    }

    public void agregarCliente(String nombre, String correo, String password, int edad, String telefono, String direccion, String cp, String rfc, int nivel, boolean autorizado){
        cliente[cant_c] = new Clientes();
        cliente[cant_c].setNombre(nombre);
        cliente[cant_c].setCorreo(correo);
        cliente[cant_c].setPassword(password);
        cliente[cant_c].setId(cant_c);
        cliente[cant_c].setNivel(nivel);
        cliente[cant_c].setAutorizado(autorizado);
        cliente[cant_c].setEdad(edad);
        cliente[cant_c].setTelefono(telefono);
        cliente[cant_c].setDireccion(direccion);
        cliente[cant_c].setCp(cp);
        cliente[cant_c].setRfc(rfc);
        cant_c++;

        String json = gson.toJson(cliente);

        Log.e("CLIENTES", json);

        editor.putString("clientes", json);
        editor.putInt("cant", cant_c);
        editor.apply();
    }

    public void obtenerId(){

    }

    public boolean login(String correo, String password){
        for(int i = 0; i < cant_c; i++){
            if(cliente[i].getCorreo().equals(correo) && cliente[i].getPassword().equals(password)) {
                editor.putInt("id", cliente[i].getId());
                editor.putInt("nivel", cliente[i].getNivel());
                editor.putString("nombre_usuario", cliente[i].getNombre());
                editor.putString("correo_usuario", cliente[i].getCorreo());
                editor.putString("direccion", cliente[i].getDireccion());
                editor.apply();
                return true;
            }
        }
        for(int i = 0; i < cant_u; i++){
            if(usuario[i].getCorreo().equals(correo) && cliente[i].getPassword().equals(password)) {
                if(!usuario[i].isAutorizado()) return  false;
                editor.putInt("id", usuario[i].getId());
                editor.putInt("nivel", usuario[i].getNivel());
                editor.putString("nombre_usuario", usuario[i].getNombre());
                editor.putString("correo_usuario", usuario[i].getCorreo());
                editor.apply();
                return true;
            }
        }
        return false;
    }

    public boolean isLogin(){
        return sharedPreferences.contains("nombre_usuario");
    }

    public int getLevel(){
        return sharedPreferences.getInt("nivel", 0);
    }

    public int getIdUser(){
        return sharedPreferences.getInt("id", 0);
    }
    public void destroySession(){
        editor.remove("nombre_usuario");
        editor.commit();
    }

    public void rellenar(){
        String json = sharedPreferences.getString("clientes", "");
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                cliente[i] = new Clientes();
                cliente[i].setNombre(jsonObject.getString("nombre"));
                cliente[i].setCorreo(jsonObject.getString("correo"));
                cliente[i].setPassword(jsonObject.getString("password"));
                cliente[i].setId(jsonObject.getInt("id"));
                cliente[i].setNivel(jsonObject.getInt("nivel"));
                cliente[i].setAutorizado(jsonObject.getBoolean("autorizado"));
                cliente[i].setEdad(jsonObject.getInt("edad"));
                cliente[i].setTelefono(jsonObject.getString("telefono"));
                cliente[i].setDireccion(jsonObject.getString("direccion"));
                cliente[i].setCp(jsonObject.getString("cp"));
                cliente[i].setRfc(jsonObject.getString("rfc"));

                cant_c++;
            }
        } catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }

        String json2 = sharedPreferences.getString("usuarios", "");
        try {
            JSONArray jsonArray = new JSONArray(json2);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                usuario[i] = new Usuarios();
                usuario[i].setNombre(jsonObject.getString("nombre"));
                usuario[i].setCorreo(jsonObject.getString("correo"));
                usuario[i].setPassword(jsonObject.getString("password"));
                usuario[i].setId(jsonObject.getInt("id"));
                usuario[i].setNivel(jsonObject.getInt("nivel"));
                usuario[i].setAutorizado(jsonObject.getBoolean("autorizado"));
                cant_u++;
            }
        } catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public Usuarios buscar(int id){
        for (int i = 0; i < cant_u; i++){
            if(usuario[i].getId() == id) return usuario[i];
        }
        return null;
    }

    public boolean actualizar(int id, String nombre, String correo, String password){
        for (int i = 0; i < cant_u; i++){
            if(usuario[i].getId() == id){
                usuario[i].setId(id);
                usuario[i].setNombre(nombre);
                usuario[i].setCorreo(correo);
                usuario[i].setPassword(password);
                String json = gson.toJson(usuario);

                Log.e("USUARIOS", json);

                editor.putString("usuarios", json);
                editor.apply();
                return true;
            }
        }
        return false;
    }

    public void eliminar(int id){
        Usuarios temp[] = new Usuarios[20];
        boolean encontrado = false;
        int c = 0;
        for (int i = 0; i < cant_u; i ++){
            if(usuario[i].getId() == id){
                encontrado = true;
                continue;
            }
            temp[c].setId(usuario[i].getId());
            temp[c].setPassword(usuario[i].getPassword());
            temp[c].setCorreo(usuario[i].getCorreo());
            temp[c].setNombre(usuario[i].getNombre());
            temp[c].setAutorizado(usuario[i].isAutorizado());
            temp[c].setNivel(usuario[i].getNivel());
            c++;
        }
    }
}

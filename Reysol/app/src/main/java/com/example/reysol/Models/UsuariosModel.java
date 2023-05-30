package com.example.reysol.Models;

import com.example.reysol.Classes.Usuarios;

public class UsuariosModel {
    UsuariosModel(){

    }

    public void rellenar(Usuarios usuarios[]){

    }

    public void agregar(Usuarios usuario, String nombre, String correo, String password, int id, int nivel, boolean autorizado){
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        usuario.setId(id);
        usuario.setNivel(nivel);
        usuario.setAutorizado(autorizado);
    }
}

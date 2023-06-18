package com.example.reysol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.reysol.Models.UsuariosModel;

public class MainActivity extends AppCompatActivity {
    UsuariosModel usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        usuarios = new UsuariosModel(getApplicationContext());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        super.onCreate(savedInstanceState);
        Intent i;
        if(usuarios.isLogin())
            i = new Intent(this, MainViewActivity.class);
        else
            i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
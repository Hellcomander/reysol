package com.example.reysol;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.reysol.Fragments.CartFragment;
import com.example.reysol.Fragments.ProductsFragment;
import com.example.reysol.Models.ProductosModel;
import com.example.reysol.Models.UsuariosModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainViewActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    UsuariosModel usuarios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);


        // Aqui es donde ocupo rescatar los productos
        usuarios = new UsuariosModel(getApplicationContext());
        toolbar = findViewById(R.id.toolbarMap);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        navigationView.bringToFront();
        if(usuarios.getLevel() == 1)
            navigationView.inflateMenu(R.menu.drawer_menu_admin);
        else
            navigationView.inflateMenu(R.menu.drawer_menu_admin);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                int id = item.getItemId();
                Fragment temp = null;
                Intent i;

                if (id == R.id.mProductos){
                    temp = new ProductsFragment();
                } else if(id == R.id.mAgregarProductos){
                    i = new Intent(getApplicationContext(), AddProducts.class);
                    startActivity(i);
                    return true;
                } else if(id == R.id.mCerrar){
                    usuarios.destroySession();
                    finish();
                    return true;
                } else if(id == R.id.mAgregarAdministradores){
                    i = new Intent(getApplicationContext(), addAdmin.class);
                    startActivity(i);
                    return true;
                }
                else if(id == R.id.mCarrito){
                    temp = new CartFragment();
                } else if(id == R.id.mAgregarPaqueterias){
                    i = new Intent(getApplicationContext(), AddParcelActivity.class);
                    startActivity(i);
                    return true;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,temp).commit();
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,new ProductsFragment()).commit();
    }

    @Override
    public void onBackPressed(){

    }
}
package com.example.reysol;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.reysol.Fragments.ProductsFragment;
import com.example.reysol.Models.UsuariosModel;
import com.google.android.material.navigation.NavigationView;

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
        if(false)
            navigationView.inflateMenu(R.menu.drawer_menu_admin);
        else
            navigationView.inflateMenu(R.menu.drawer_menu);
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
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,temp).commit();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(false)
            inflater.inflate(R.menu.drawer_menu_admin, menu);
        else
            inflater.inflate(R.menu.drawer_menu_admin, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public void onBackPressed(){

    }
}
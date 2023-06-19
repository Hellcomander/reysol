package com.example.reysol;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reysol.Classes.Productos;
import com.example.reysol.Models.ProductosModel;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<Productos> productosList;

    public AdapterDatos(ArrayList<Productos> productosList) {
        this.productosList = productosList;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView productTitle;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            productTitle = (TextView) itemView.findViewById(R.id.productTitle);
        }

        public void asignarDatos(Productos producto) {
            productTitle.setText(producto.getNombre());
        }
    }
    @NonNull
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolderDatos holder, int position) {
        holder.asignarDatos(productosList.get(position));
    }

    @Override
    public int getItemCount() {
        return productosList.size();
    }
}

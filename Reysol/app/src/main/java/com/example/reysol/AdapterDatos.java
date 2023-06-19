package com.example.reysol;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reysol.Classes.Productos;
import com.example.reysol.Models.CarritoModel;
import com.example.reysol.Models.ComprasModel;
import com.example.reysol.Models.ProductosModel;
import com.example.reysol.Models.UsuariosModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<Productos> productosList;
    ComprasModel comprasModel;
    UsuariosModel usuariosModel;
    CarritoModel carritoModel;
    public AdapterDatos(ArrayList<Productos> productosList) {
        this.productosList = productosList;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView productTitle, showPrice, moreInfo, showPeso, showMedidas;
        Button btnAddCar;
        ImageView img;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            productTitle = (TextView) itemView.findViewById(R.id.productTitle);
            showPrice = (TextView) itemView.findViewById(R.id.showPrice);
            moreInfo = (TextView) itemView.findViewById(R.id.moreInfo);
            showPeso = (TextView) itemView.findViewById(R.id.showPeso);
            showMedidas = (TextView) itemView.findViewById(R.id.showMedidas);
            btnAddCar = (Button) itemView.findViewById(R.id.btnAddCar);
            img = itemView.findViewById(R.id.img);


        }

        public void asignarDatos(Productos producto) {
            productTitle.setText(producto.getNombre());

            showPrice.setText("$" + producto.getPrecio());
            moreInfo.setText(producto.getDescripcion());
            showPeso.setText("Peso: " + producto.getPeso());
            showMedidas.setText("Medidas: " + producto.getMedidas());
            Picasso.get().load(producto.getUrlImagen()).into(img);

            btnAddCar.setOnClickListener(v -> {
                    comprasModel.agregar(producto.getId(), usuariosModel.getIdUser(), carritoModel.obtenerCarrito(usuariosModel.getIdUser()), "", producto.getPrecio(), producto.getId(), 1 );
                    Toast.makeText(v.getContext(), "Se ha guardado en el carrito", Toast.LENGTH_LONG).show();
            });
        }
    }
    @NonNull
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        comprasModel = new ComprasModel(parent.getContext());
        carritoModel = new CarritoModel(parent.getContext());
        usuariosModel = new UsuariosModel(parent.getContext());
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

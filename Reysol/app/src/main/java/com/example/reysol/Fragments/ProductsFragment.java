package com.example.reysol.Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.reysol.AdapterDatos;
import com.example.reysol.Classes.Productos;
import com.example.reysol.Models.ProductosModel;
import com.example.reysol.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ArrayList<Productos> productosList;
    RecyclerView productList;

    ProductosModel productosModel;

    public ProductsFragment() {

    }

    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products, container, false);
        productList = (RecyclerView) view.findViewById(R.id.productList);
        productList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        productosList = new ArrayList<Productos>();

        productosModel = new ProductosModel(getContext());
        productosList.addAll(Arrays.asList(productosModel.listar()));

        AdapterDatos adapterDatos = new AdapterDatos(productosList);
        productList.setAdapter(adapterDatos);
        return view;
    }

}
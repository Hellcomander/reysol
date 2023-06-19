package com.example.reysol.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.reysol.Models.CarritoModel;
import com.example.reysol.Models.ComprasModel;
import com.example.reysol.Models.PaqueteriasModel;
import com.example.reysol.Models.UsuariosModel;
import com.example.reysol.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Spinner spinner;
    PaqueteriasModel paqueteria;
    ComprasModel comprasModel;
    CarritoModel carritoModel;
    UsuariosModel usuariosModel;
    TextView sales;

    public CartFragment() {

    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        comprasModel = new ComprasModel(getContext());
        paqueteria = new PaqueteriasModel(getContext());
        carritoModel = new CarritoModel(getContext());
        usuariosModel = new UsuariosModel(getContext());
        spinner = view.findViewById(R.id.spinner);
        sales = view.findViewById(R.id.sales);
        ArrayAdapter<CharSequence> adapter;
        adapter = new ArrayAdapter<CharSequence>(getContext(), android.R.layout.simple_spinner_dropdown_item, paqueteria.obtenerPaqueterias());
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayList<String> com = comprasModel.obtenerCompras(usuariosModel.getIdUser());

        for (String c : com){
            sales.setText(sales.getText() + "\n" + c);
        }
        return view;
    }
}
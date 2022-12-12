package com.example.db_tp2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentAffichage extends Fragment {

    private View vue;
    TextView tv_pays, tv_dateNais, tv_gender;

    public FragmentAffichage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       vue = inflater.inflate(R.layout.fragment_affichage, container, false);

        tv_pays = vue.findViewById(R.id.tv_pays);
        tv_dateNais = vue.findViewById(R.id.tv_dateNais);
        tv_gender = vue.findViewById(R.id.tv_gender);

        String pays, dateNais,gender;

        pays = this.getArguments().getString("pays");
        dateNais = this.getArguments().getString("date");
        gender = this.getArguments().getString("gender");

        tv_pays.setText(pays);
        tv_dateNais.setText(dateNais);
        tv_gender.setText(gender);


        return vue ;
    }
}

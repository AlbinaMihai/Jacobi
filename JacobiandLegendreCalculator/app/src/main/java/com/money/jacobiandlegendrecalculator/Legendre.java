package com.money.jacobiandlegendrecalculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Legendre#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Legendre extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Legendre() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Legendre.
     */
    // TODO: Rename and change types and number of parameters
    public static Legendre newInstance(String param1, String param2) {
        Legendre fragment = new Legendre();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private int getJLegendre(int a, int p) {
        a %= p;
        int legendre = 1;
        while (a > 0) {
            while (a % 2 == 0) {
                a /= 2;
                int r = p % 8;
                if (r == 3 || r == 5) {
                    legendre = -legendre;
                }
            }
            int temp = p;
            p = a;
            a = temp;
            if (a % 4 == 3 && p % 4 == 3) {
                legendre = -legendre;
            }
            a %= p;
        }
        if (p == 1) {
            return legendre;
        }
        return 0;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView Raspuns = getActivity().findViewById(R.id.LegendreRaspuns);
        final EditText JacobiK = getActivity().findViewById(R.id.LegendreA);
        final EditText JacobiN = getActivity().findViewById(R.id.LegendreB);
        Button JacobiButton = getActivity().findViewById(R.id.LegendreButton);

        JacobiButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (JacobiK.getText().toString().equals("")) {
                    Raspuns.setTextColor(Color.RED);
                    Raspuns.setText("K neintrodus");
                }else
                if (JacobiN.getText().toString().equals("")) {
                    Raspuns.setTextColor(Color.RED);
                    Raspuns.setText("N neintrodus");
                } else
                if (Integer.parseInt(JacobiK.getText().toString()) < 0) {
                    Raspuns.setTextColor(Color.RED);
                    Raspuns.setText("K trebuie să fie pozitiv");
                } else if (Integer.parseInt(JacobiN.getText().toString()) % 2 == 0) {
                    Raspuns.setTextColor(Color.RED);
                    Raspuns.setText("N trebuie să fie impar");
                } else   {
                    Raspuns.setTextColor(Color.BLACK);
                    Raspuns.setText("Rezultat: " + getJLegendre(Integer.parseInt(JacobiK.getText().toString()), Integer.parseInt(JacobiN.getText().toString())));
                }
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_legendre, container, false);
    }
}
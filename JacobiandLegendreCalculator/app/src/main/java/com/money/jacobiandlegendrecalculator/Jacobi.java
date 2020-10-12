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
 * Use the {@link Jacobi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Jacobi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Jacobi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Jacobi.
     */
    // TODO: Rename and change types and number of parameters
    public static Jacobi newInstance(String param1, String param2) {
        Jacobi fragment = new Jacobi();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private static int getJAcobi(int k, int n) {
        k %= n;
        int jacobi = 1;
        while (k > 0) {
            while (k % 2 == 0) {
                k /= 2;
                int r = n % 8;
                if (r == 3 || r == 5) {
                    jacobi = -jacobi;
                }
            }
            int temp = n;
            n = k;
            k = temp;
            if (k % 4 == 3 && n % 4 == 3) {
                jacobi = -jacobi;
            }
            k %= n;
        }
        if (n == 1) {
            return jacobi;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jacobi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView Raspuns = getActivity().findViewById(R.id.RaspunsJAcobi);
        final EditText JacobiK = getActivity().findViewById(R.id.JacobiA);
        final EditText JacobiN = getActivity().findViewById(R.id.JacobiB);
        Button JacobiButton = getActivity().findViewById(R.id.JacobiButton);

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
                    Raspuns.setText("Rezultat: " + getJAcobi(Integer.parseInt(JacobiK.getText().toString()), Integer.parseInt(JacobiN.getText().toString())));
                }
            }
        });



    }
}
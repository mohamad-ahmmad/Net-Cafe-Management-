package com.example.idressstation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Queue#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Queue extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Queue() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Queue.
     */
    // TODO: Rename and change types and number of parameters
    public static Queue newInstance(String param1, String param2) {
        Queue fragment = new Queue();
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
        View view = inflater.inflate(R.layout.fragment_queue, container, false);

        Button btnAdd = view.findViewById(R.id.add);

        btnAdd.setOnClickListener(e-> {
            creatTable(view);});
            return view;

    }
    public void creatTable(View view){
        TableLayout table1 = getView().findViewById(R.id.playersTable);

        TableRow row = new TableRow(getContext());

        EditText nameValue = view.findViewById(R.id.name);
        TextView name = new TextView(getContext());
        name.setText(nameValue.getText().toString());
        row.addView(name);


        EditText gameValue = view.findViewById(R.id.game);
        TextView game = new TextView(getContext());
        game.setText(gameValue.getText().toString());
        row.addView(game);


        EditText psValue = view.findViewById(R.id.ps);
        TextView ps = new TextView(getContext());
        ps.setText(psValue.getText().toString());
        row.addView(ps);

        table1.addView(row);
    }
}
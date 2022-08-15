package com.example.idressstation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.idressstation.listeners.HomeProfitListener;
import com.example.idressstation.listeners.timerClick;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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

        InitializeData();


    }

    private void InitializeData() {



    }
    private static timerClick timer1 ;
    private static timerClick timer2 ;
    private static boolean started=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        if (!started) {
            timer2 = new timerClick(view, 2);
            timer1 = new timerClick(view, 1);

            started=!started;
        }
        timer2.refresh(view);
        timer1.refresh(view);
        String str[] = {"Winston", "Chips Type A", "Sandwich"};
        //getView().findViewById().
        Spinner spn = view.findViewById(R.id.spnTypes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext() , android.R.layout.simple_spinner_item, str);
        spn.setAdapter(adapter);

        //Setting listeners
        HomeProfitListener handler = new HomeProfitListener(view);

        ((Button)view.findViewById(R.id.btnAdd)).setOnClickListener(handler);
        ((Button)view.findViewById(R.id.btnRemove)).setOnClickListener(handler);
        Button start = view.findViewById(R.id.btnStart);


        start.setOnClickListener(timer1);
        Log.d("HASLISTENER" ,String.valueOf(start.hasOnClickListeners()) );
        Button start2 = view.findViewById(R.id.btnStart2);

        start2.setOnClickListener(timer2);

        Button pause = view.findViewById(R.id.btnStop);
        pause.setOnClickListener(timer1);

        Button pause2 = view.findViewById(R.id.btnStop2);
        pause2.setOnClickListener(timer2);
        return view;


    }


}
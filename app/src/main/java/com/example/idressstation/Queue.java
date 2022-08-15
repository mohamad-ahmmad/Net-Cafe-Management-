package com.example.idressstation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.idressstation.util.Player;

import java.util.ArrayList;

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
            Log.d("VIEW",mParam1);
            Log.d("VIEW",mParam2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_queue, container, false);

        //RELOAD DATA
        if( arrPlayer.size()!=0) {
            for (Player temp : arrPlayer)
                addRowArray(view, temp.getName(), temp.getGame(), temp.getPs());

        }



        Button btnAdd = view.findViewById(R.id.add);
        btnAdd.setOnClickListener(e-> {
            addQueue(view);

        });

        Button btnRemove = (Button) view.findViewById(R.id.btnRemoveQueue);
        btnRemove.setOnClickListener(e->{

            EditText nameValue = view.findViewById(R.id.name);
            String strName=nameValue.getText().toString();

            deleteRow(view ,strName);

        });

        RemoveAlltext handler = new RemoveAlltext();


        ((EditText)view.findViewById(R.id.name)).setOnTouchListener(handler);
        ((EditText)view.findViewById(R.id.game)).setOnTouchListener(handler);
        ((EditText)view.findViewById(R.id.ps)).setOnTouchListener(handler);


            return view;

    }
    static ArrayList<Player> arrPlayer = new ArrayList<>();



    public void addQueue(View view){


        addRow(view);

    }

    private void addRow(View view){

        TableLayout table1 = view.findViewById(R.id.playersTable);

        TableRow row = new TableRow(getContext());

        EditText nameValue = view.findViewById(R.id.name);
        TextView name = new TextView(getContext());
        String strName=nameValue.getText().toString();
        name.setText(strName);
        row.addView(name);


        EditText gameValue = view.findViewById(R.id.game);
        TextView game = new TextView(getContext());
        String strGame = gameValue.getText().toString();
        game.setText(strGame);
        row.addView(game);


        EditText psValue = view.findViewById(R.id.ps);
        TextView ps = new TextView(getContext());
        String strPs = psValue.getText().toString();
        ps.setText(strPs);
        row.addView(ps);


        arrPlayer.add(new Player(strName, strGame, strPs));
        table1.addView(row);

    }

    private void addRowArray(View view, String strName, String strGame, String strPs){

        TableLayout table1 = view.findViewById(R.id.playersTable);

        TableRow row = new TableRow(getContext());


        TextView name = new TextView(getContext());

        name.setText(strName);
        row.addView(name);



        TextView game = new TextView(getContext());

        game.setText(strGame);
        row.addView(game);



        TextView ps = new TextView(getContext());

        ps.setText(strPs);
        row.addView(ps);



        table1.addView(row);

    }

    private void deleteRow(View view ,String name){
        Log.d("HERE2" , "HEY");
         String strName = name.trim();
        Log.d("HERE3" , "HEY");

        if(arrPlayer.size()==0)
            return;

        Log.d("HERE1" , "HEY");
        for(Player temp : arrPlayer){
         if(strName.equals(temp.getName())){
             arrPlayer.remove(temp);

             updateTable(view);
             return;
         }
        }

    }

    private void updateTable(View view) {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentComp, this.getClass(), null);
        fragmentTransaction.commit();
    }

    class RemoveAlltext implements View.OnTouchListener{



        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Log.d("HERE","hereee");
            ((EditText) view).setText("");
            return false;
        }
    }
}
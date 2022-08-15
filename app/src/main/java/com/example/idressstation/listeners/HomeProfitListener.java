package com.example.idressstation.listeners;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeProfitListener implements View.OnClickListener {

    private View view;
    private String type;



    public HomeProfitListener(View view){
       this.view=view;
    }


    @Override
    public void onClick(View view) {
        type=((Button)view).getText().toString();
        Log.d("Type", type);
        if(type.equals("+ Add")){
            addClicked();
        }else{
            removeClicked();
        }

    }

    private void addClicked(){

    }

    private void removeClicked(){

    }




}

package com.example.idressstation.listeners;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.idressstation.R;

public class timerClick implements View.OnClickListener {
    private View view;
    private String type;
    private int ps;
    private EditText hours;
    private EditText min;
    private EditText sec;
    private EditText amount;
    private int amountValue;
    public int TimerCounter;

    public timerClick(View v ,int ps){
        view=v;
        this.ps=ps;
        if(ps == 1){

            amount = view.findViewById(R.id.fieldAmount);
            hours = view.findViewById(R.id.hourField);
            min = view.findViewById(R.id.minField);
            sec = view.findViewById(R.id.secField);



        }else{
            amount = view.findViewById(R.id.fieldAmount2);
            hours = view.findViewById(R.id.hourField2);
            min = view.findViewById(R.id.minField3);
            sec = view.findViewById(R.id.secField3);
        }
    }



    @Override
    public void onClick(View view) {
        type = ((Button) view).getText().toString();
        Log.d("Type", type);
        if (type.equals("start")) {
            amountValue = Integer.parseInt(amount.getText().toString());
            Long time = ((long)amountValue) * 900000;
            startTimer(time);
        }
        if (type.equals("pause")) {
            pauseTimer();
        }

    }

    public void startTimer(Long time) {



        new CountDownTimer(time, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                double hours1 = millisUntilFinished/3600000.0;

                int IntHours = ((int)hours1);
                Log.d("Type",String.valueOf(hours1 - IntHours));
                double mins = 60 * (hours1 - IntHours);
                int IntMins = ((int) mins);
                double secs = (mins-IntMins) * 60;
                int IntSec = ((int)secs);
                Log.d("Type", IntHours+" "+IntMins + " " +IntSec);
                hours.setText(String.valueOf(IntHours));
                min.setText(String.valueOf(IntMins));
                sec.setText(String.valueOf(IntSec));
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    public void pauseTimer() {

    }
}




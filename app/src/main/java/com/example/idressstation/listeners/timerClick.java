package com.example.idressstation.listeners;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.idressstation.MainActivity;
import com.example.idressstation.R;

public class timerClick implements View.OnClickListener {
    private View view;
    private String type;
    private int ps;
    private EditText hours;
    private EditText min;
    private EditText sec;
    private EditText amount;
    private Button start;
    private Button pause;
    private long amountValue;
    private boolean pauseFlag = false;

    private String currentSec="SEC", currentMin="MIN",currentHour="HR";

    String amountOnRefresh;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void refresh(View view){
        this.view = view;
        if(ps == 1){

            amount = view.findViewById(R.id.fieldAmount);
            hours = view.findViewById(R.id.hourField);
            min = view.findViewById(R.id.minField);
            sec = view.findViewById(R.id.secField);
            pause = view.findViewById(R.id.btnStop);
            start = view.findViewById(R.id.btnStart);
            Log.d("FLAG",String.valueOf(pauseFlag));
            if(pauseFlag) {
                pause.setText("stop");
                start.setText("resume");
            }
            amount.setText(amountOnRefresh);
            sec.setText(currentSec);
            min.setText(currentMin);
            hours.setText(currentHour);

        }else{
            amount = view.findViewById(R.id.fieldAmount2);
            hours = view.findViewById(R.id.hourField2);
            min = view.findViewById(R.id.minField3);
            sec = view.findViewById(R.id.secField3);
            pause = view.findViewById(R.id.btnStop2);
            start = view.findViewById(R.id.btnStart2);
            if(pauseFlag) {
                pause.setText("stop");
                start.setText("resume");
            }
            amount.setText(amountOnRefresh);
            sec.setText(currentSec);
            min.setText(currentMin);
            hours.setText(currentHour);
        }
    }
    public timerClick(View v , int ps){
        view=v;
        this.ps=ps;

        if(ps == 1){

            amount = view.findViewById(R.id.fieldAmount);
            hours = view.findViewById(R.id.hourField);
            min = view.findViewById(R.id.minField);
            sec = view.findViewById(R.id.secField);
            pause = view.findViewById(R.id.btnStop);
            start = view.findViewById(R.id.btnStart);

        }else{
            amount = view.findViewById(R.id.fieldAmount2);
            hours = view.findViewById(R.id.hourField2);
            min = view.findViewById(R.id.minField3);
            sec = view.findViewById(R.id.secField3);
            pause = view.findViewById(R.id.btnStop2);
            start = view.findViewById(R.id.btnStart2);
        }
    }


    Long time;
    @Override
    public void onClick(View view) {
        type = ((Button) view).getText().toString();
       // Log.d("Type", type);
        if (type.equals("start")) {
            amountValue = Integer.parseInt(amount.getText().toString());
             time = ((long)amountValue) * 900000;
            startTimer();
            amountOnRefresh=String.valueOf(amountValue);
        }
       else if (type.equals("pause")) {
            Log.d("Type1", "iam in pause");
            pauseTimer();
        }
       else if(type.equals("stop")){


        }
        else if(type.equals("resume")){
            pauseFlag=false;
            pause.setText("pause");
            start.setText("start");
            time = (Long.parseLong(currentHour)*3600 + Long.parseLong(currentMin)*60+Long.parseLong(currentSec))*1000;
            startTimer();
        }

    }

    private CountDownTimer count ;

    public void startTimer() {

       count =   new CountDownTimer(time, 1000){

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
                currentSec = String.valueOf(IntSec);
                currentMin = String.valueOf(IntMins);
                currentHour = String.valueOf(IntHours);

            }

            @Override
            public void onFinish() {

            }
        }.start() ;

    }

    public void pauseTimer() {
        pause.setText("stop");
        start.setText("resume");
        pauseFlag = true;
        count.cancel();


    }



}




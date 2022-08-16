package com.example.idressstation.listeners;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

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
    private Boolean state =true;
    private Button start;
    private Button pause;
    private double amountValue;
    private boolean pauseFlag = false;
    private long infinitTime = 1000000000;
    private int timeCountS = 0;
    private int timeCountM = 0;
    private int timeCountH = 0;

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
            start.setEnabled(state);


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
            }else{
                pause.setText("pause");
                start.setText("start");
               
            }
            amount.setText(amountOnRefresh);
            sec.setText(currentSec);
            min.setText(currentMin);
            hours.setText(currentHour);
            start.setEnabled(state);
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


    long time;
    @Override
    public void onClick(View view) {


//        try {
//            Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//            RingtoneManager.setActualDefaultRingtoneUri(
//                    getView().getContext(), RingtoneManager.TYPE_RINGTONE, path
//            );
//            Ringtone r = RingtoneManager.getRingtone(getView().getContext(), path);
//            r.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        type = ((Button) view).getText().toString();

        if (type.equals("start") && !amount.getText().toString().equals("")) {
           try{
               amountValue = Double.parseDouble(amount.getText().toString());
           }catch(Exception e) {
               Toast.makeText(getView().getContext(), "Invalid number", Toast.LENGTH_LONG ).show();
               return;
           }
             time = (long)(amountValue * 900000);

                 startTimer();

        }
       else if (type.equals("pause")) {
            Log.d("Type1", "iam in pause");
            pauseTimer();
        }
       else if(type.equals("stop")){
           stopTimer();
        } else if(type.equals("resume") && amount.getText().toString().equals("")){
            pauseFlag=false;
            pause.setText("pause");
            start.setText("start");
            time = (Long.parseLong(currentHour)*3600 + Long.parseLong(currentMin)*60+Long.parseLong(currentSec))*1000;

                startOpenTime(infinitTime);

        }
        else if(type.equals("resume") && !amount.getText().toString().equals("")){
            pauseFlag=false;
            pause.setText("pause");
            start.setText("start");
            time = (Long.parseLong(currentHour)*3600 + Long.parseLong(currentMin)*60+Long.parseLong(currentSec))*1000;

                startTimer();

        }
        else if(type.equals("start") && amount.getText().toString().equals("")){

                startOpenTime(infinitTime);


        }

    }

    private CountDownTimer count ;

    public void startTimer() {
        state=false;
        start.setEnabled(state);

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
                MediaPlayer player = MediaPlayer.create(getView().getContext(), R.raw.song);
                player.start();
            }
        }.start() ;

    }

    public void pauseTimer() {
        state=true;
        start.setEnabled(state);
        pause.setText("stop");
        start.setText("resume");
        pauseFlag = true;
        count.cancel();


    }
    public void stopTimer(){
        state=true;
        start.setEnabled(state);
        double hoursR = Integer.parseInt(hours.getText().toString());
        double minR = Integer.parseInt(min.getText().toString());
           double remain = (hoursR * 60 + minR) / 15;
           Log.d("remain",String.valueOf(remain));
           amount.setText("change = " + String.valueOf(remain));
        hours.setText("HR");
        min.setText("MIN");
        sec.setText("SEC");
        start.setText("start");
        pause.setText("pause");
        timeCountS = 0;
        timeCountM = 0;
        timeCountH = 0;
        pauseFlag = false;

    }
    public void startOpenTime(long openTime){
        state=false;
        start.setEnabled(state);
        count =   new CountDownTimer(openTime, 1000){

            @Override
            public void onTick(long millisUntilFinished) {


               if(timeCountS == 60){
                   timeCountS = 0;
                   timeCountM++;

               }
               else if(timeCountM == 60){
                   timeCountM = 0;
                   timeCountH++;
               }
                hours.setText(String.valueOf(timeCountH));
                min.setText(String.valueOf(timeCountM));
                sec.setText(String.valueOf(timeCountS));
                currentSec = String.valueOf(timeCountS);
                currentMin = String.valueOf(timeCountM);
                currentHour = String.valueOf(timeCountH);
              timeCountS++;
            }

            @Override
            public void onFinish() {

            }
        }.start() ;
    }

}




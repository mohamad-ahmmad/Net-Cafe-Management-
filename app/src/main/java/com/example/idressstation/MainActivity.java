package com.example.idressstation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.idressstation.databinding.ActivityMainBinding;
import com.example.idressstation.util.DBHelper;

public class MainActivity extends AppCompatActivity {








    ActivityMainBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Log.d("ONCREAT", "HELLO");



        bind.bottomNavigationView2.setOnItemSelectedListener(e -> {
            Log.d("GENERALSCOPE", "HELLO");
            switch(e.getItemId()){
                case R.id.btnHome:
                    replaceFragment(new Home());
                    break;
                case R.id.btnAnalysis:
                    Log.d("INSWITCH", "HELLO");
                    replaceFragment(new Analysis());
                    break;
                case R.id.btnProfits:
                     replaceFragment(new Profits());
                    break;
                case R.id.btnQueue:
                     replaceFragment(new Queue());
                    break;

            }

          return true;
        });


    }

    private void replaceFragment(Fragment fragment){
          FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentComp, fragment, null);
        fragmentTransaction.commit();
    }
}
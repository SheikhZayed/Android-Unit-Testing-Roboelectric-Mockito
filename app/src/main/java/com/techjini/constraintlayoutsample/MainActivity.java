package com.techjini.constraintlayoutsample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.techjini.constraintlayoutsample.databinding.ActivityMainBinding;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String RESTART_WORK = "restart_work";
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Toast.makeText(getApplicationContext(), "thanks,for clicking button", Toast.LENGTH_LONG).show();
        }
    }

    public int someMethod(int a, int b) {
        return a + b;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onrestart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //this will fail the onPause() test
        Toast.makeText(getApplicationContext(), "onpause", Toast.LENGTH_LONG).show();

    }

    public String restartWork() {
        return RESTART_WORK;
    }


}

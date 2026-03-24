package com.example.exercise3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercise3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "InputActivityLog";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());



        setContentView(binding.getRoot());

        Log.d(TAG, "onCreate() Method started successfully");

        binding.btnSend.setOnClickListener( v -> sendMessage());

    }


    private void sendMessage(){
        String message = binding.messageField.getText().toString();
        Log.i(TAG, "Send Button Pressed");


        Intent echoAct = new Intent(MainActivity.this, EchoActivity.class);
        echoAct.putExtra("MESSAGE", message);

        startActivity(echoAct);
    }
}
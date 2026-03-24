package com.example.exercise3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.exercise3.databinding.ActivityEchoBinding;

public class EchoActivity extends AppCompatActivity {
    final static String TAG = "EchoActivityLog";
    private ActivityEchoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEchoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.d(TAG, "OnCreate() method executed successfully");

        Intent receivedIntent = getIntent();
        String received = receivedIntent.getStringExtra("MESSAGE");
        TextView label = binding.receivedMessage;

        if(received != null){
            label.setText(received);
            Log.d(TAG, "Message Successfully Displayed");
        }else{
            label.setText("No Message");
            Log.w(TAG, "Received message is null");
        }
    }
}
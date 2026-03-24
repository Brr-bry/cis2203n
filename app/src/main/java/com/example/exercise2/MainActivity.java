package com.example.exercise2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.exercise2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener( v -> validateLogin());
    }

    private void validateLogin(){

        String studentID = binding.idField.getText().toString();
        String pass = binding.passwordField.getText().toString();

        String lastTwo = studentID.substring(studentID.length() - 2);

        String correctPass = "yellow" + lastTwo;

        TextView res = binding.result;

        if(pass.equals(correctPass)){
            res.setText("Login Successfull.");
        }else{
            res.setText("Incorrect Password.");
        }

    }
}
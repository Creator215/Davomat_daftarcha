package com.example.davomatdaftarcha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public static final String talaba_royxat = "talabalar.txt";
    public FrameLayout frameLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            if(read_file(talaba_royxat).length() == 0){
                enter_royxat();
            }else {
                enter_asosiy();
            }
        }catch (Exception ignored){
            write_file(talaba_royxat,"");
            enter_royxat();
        }


    }
    private String read_file(String file_name){
        FileInputStream fis = null;
        String data = "";
        try {
            fis = openFileInput(file_name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text = "";
            while ((text = br.readLine()) != null){
                sb.append(text);
            }
            data = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return data;
    }
    private void write_file(String file_name,String data) throws RuntimeException {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(file_name,MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private void enter_royxat(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),new Royxat());
        fragmentTransaction.commit();
    }
    private void enter_asosiy(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),new Asosiy());
        fragmentTransaction.commit();
    }
}
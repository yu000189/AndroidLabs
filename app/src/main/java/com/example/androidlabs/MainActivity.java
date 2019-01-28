package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String ACTIVITY_NAME = "MAIN_ACTIVITY";
    SharedPreferences sp;
    EditText userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_main);
        //setContentView(R.layout.activity_main_linear);
        //setContentView(R.layout.activity_main_grid);
        //setContentView(R.layout.activity_main_relative);

        //this.getDelegate().
        userEmail = (EditText) findViewById(R.id.type_email);
        sp = getSharedPreferences("FileUserName", Context.MODE_PRIVATE);
        String savedString = sp.getString("UserEmail", "");
        userEmail.setText(savedString);

        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener( ocl -> {
            Intent profilePage = new Intent(MainActivity.this, ProfileActivity.class);
            //Give directions to go from this page, to ProfileActivity
            EditText et = (EditText)findViewById(R.id.type_email);
            profilePage.putExtra("ItemOne", "Some text");
            profilePage.putExtra("ItemTwo", 500);
            profilePage.putExtra("typed", et.getText().toString());
            //Now make the transition:
            startActivityForResult( profilePage, 345);
        });
    }

    @Override
    protected void onPause() {// save
        super.onPause();
        //get an editor object
        //sharedPre = getSharedPreferences("FileUserName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //save what was typed under the name "ReserveName"
        String whatWasTyped = userEmail.getText().toString();
        editor.putString("UserEmail", whatWasTyped);

        //write it to disk:
        editor.commit();
        Log.e(ACTIVITY_NAME, "In function:" + "onPause");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int i = 0;
        i++;
        //if request code is 345, then we are coming back from ProfileActivity
        if(requestCode == 345)
        {
            // resultCode will only be 60 if the user clicks on the back button on page 2
            if(resultCode == 60)
            {
                EditText et = (EditText)findViewById(R.id.type_email);
                String fromPageThree = data.getStringExtra("NextPageTyped");
                et.setText(fromPageThree);
                Log.i("Back", "Message");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "In function:" + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "In function:" + "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME, "In function:" + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "In function:" + "onDestroy");
    }

}
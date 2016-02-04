package itech.app.meliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    SharedPrefHandler sharedPrefHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(2*1000);

                    // After 5 seconds redirect to another intent

                    sharedPrefHandler = new SharedPrefHandler(getApplicationContext());

                    if(sharedPrefHandler.getSharedPreferences("login").equals("NF")){
                        //Toast.makeText(getApplicationContext(),"NF",Toast.LENGTH_LONG).show();
                        sharedPrefHandler.setSharedPreferences("login","false");
                        Intent login = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(login);
                    }else if(sharedPrefHandler.getSharedPreferences("login").equals("false")){
                        //Toast.makeText(getApplicationContext(),"F",Toast.LENGTH_LONG).show();
                        Intent login = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(login);
                    }else if(sharedPrefHandler.getSharedPreferences("login").equals("true")){
                        //Toast.makeText(getApplicationContext(),"T",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                        startActivity(i);
                    }
                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();

    }

}

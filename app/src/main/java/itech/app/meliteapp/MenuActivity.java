package itech.app.meliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button raBt,tBt,rcBt,lBt,itBt;
    private SharedPrefHandler sharedPrefHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        raBt = (Button) findViewById(R.id.btn_ra);
        tBt = (Button)findViewById(R.id.btn_t);
        rcBt = (Button)findViewById(R.id.btn_rc);
        lBt = (Button) findViewById(R.id.btn_logout);
        itBt = (Button) findViewById(R.id.btn_it);

        raBt.setOnClickListener(this);
        tBt.setOnClickListener(this);
        rcBt.setOnClickListener(this);
        lBt.setOnClickListener(this);
        itBt.setOnClickListener(this);

        sharedPrefHandler = new SharedPrefHandler(MenuActivity.this);
    }

    @Override
    public void onClick(View view) {

        Intent webv = new Intent(MenuActivity.this,MainActivity.class);

        if(view.getId() == R.id.btn_ra){
            webv.putExtra("action","ra");
            startActivity(webv);
        }
        if(view.getId() == R.id.btn_t){
            webv.putExtra("action","t");
            startActivity(webv);
        }
        if(view.getId() == R.id.btn_rc){
            webv.putExtra("action","rc");
            startActivity(webv);
        }
        if(view.getId() == R.id.btn_logout){
            sharedPrefHandler.setSharedPreferences("login", "false");
            finish();
            Intent i = new Intent(MenuActivity.this,LoginActivity.class);
            startActivity(i);
        }
        if(view.getId() == R.id.btn_it){
            webv.putExtra("action","it");
            startActivity(webv);
        }

    }

}

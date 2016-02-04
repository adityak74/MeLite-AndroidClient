package itech.app.meliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity  {

    private SharedPrefHandler sharedPrefHandler;
    private EditText regnoEt,passwordEt;
    private Button loginBt;
    private TextView melite_tv;
    private TextInputLayout inputLayoutRegno, inputLayoutPassword;
    private CheckBox checkBox;
    private Button raBt,tBt,rcBt,lBt,itBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputLayoutRegno = (TextInputLayout) findViewById(R.id.input_layout_regno);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        checkBox = (CheckBox) findViewById(R.id.checkBox1);

        melite_tv = (TextView) findViewById(R.id.melite_tv);
        String udata="Enter MeLite Login";
        SpannableString content = new SpannableString(udata);
        content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);//where first 0 shows the starting and udata.length() shows the ending span.if you want to span only part of it than you can change these values like 5,8 then it will underline part of it.
        melite_tv.setText(content);


        regnoEt = (EditText)findViewById(R.id.txtRegno);
        passwordEt = (EditText)findViewById(R.id.txtPassword);
        loginBt = (Button) findViewById(R.id.btnLogin);

        sharedPrefHandler = new SharedPrefHandler(LoginActivity.this);

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (regnoEt.getText().toString().trim().length() > 0 && passwordEt.getText().toString().trim().length() > 0) {

                    sharedPrefHandler.setSharedPreferences("regno", regnoEt.getText().toString());
                    sharedPrefHandler.setSharedPreferences("pass", passwordEt.getText().toString());
                    sharedPrefHandler.setSharedPreferences("login", "true");
                    Intent menu = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(menu);

                } else {
                    if (regnoEt.getText().toString().trim().isEmpty()) {
                        inputLayoutRegno.setError("Enter Reg No");
                        regnoEt.requestFocus();
                    } else {
                        inputLayoutRegno.setErrorEnabled(false);
                    }

                    if (passwordEt.getText().toString().trim().isEmpty()) {
                        inputLayoutPassword.setError("Enter Password");
                        passwordEt.requestFocus();
                    } else {
                        inputLayoutPassword.setErrorEnabled(false);
                    }
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    passwordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    passwordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

    }


}

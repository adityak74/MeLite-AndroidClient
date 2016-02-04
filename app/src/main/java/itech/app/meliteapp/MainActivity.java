package itech.app.meliteapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.apache.http.util.EncodingUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private ServiceHandler serviceHandler;
    private WebView webView;
    private SharedPrefHandler sharedPrefHandler;
    private ProgressDialog progressDialog;
    private AlertDialogManager alertDialogManager;
    private ConnectionDetector connectionDetector;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        connectionDetector = new ConnectionDetector(MainActivity.this);
        if(connectionDetector.isConnectingToInternet()) {

            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loading...");

            sharedPrefHandler = new SharedPrefHandler(MainActivity.this);
            webView = (WebView) findViewById(R.id.webView);
            String url = "http://27.251.102.149/melite/project_portfolio/loginprocess.php";
            String regno = sharedPrefHandler.getSharedPreferences("regno");
            String pass = sharedPrefHandler.getSharedPreferences("pass");
            String postData = "mail=" + regno + "&pass=" + pass;
            action = getIntent().getExtras().getString("action");

            webView.setVisibility(View.INVISIBLE);

            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                    return super.onJsAlert(view, url, message, result);
                }
            });

            webView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    if (url.equals("http://27.251.102.149/melite/project_portfolio/login.php?zase=zeon")) {
                        Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_LONG).show();


                        if(action.equals("ra")){
                            webView.loadUrl("http://27.251.102.149/melite/project_portfolio/student/addActivity.php");
                            webView.setVisibility(View.VISIBLE);
                        }
                        if(action.equals("t")){
                            webView.loadUrl("http://27.251.102.149/melite/project_portfolio/student/timeline.php");
                            webView.setVisibility(View.VISIBLE);
                        }
                        if(action.equals("rc")){
                            webView.loadUrl("http://27.251.102.149/melite/project_portfolio/student/review_comments.php");
                            webView.setVisibility(View.VISIBLE);
                        }




                    } else if (url.equals("http://27.251.102.149/melite/project_portfolio/login.php")) {
                        Toast.makeText(getApplicationContext(), "Login Failed.Please check your regno and pass.", Toast.LENGTH_LONG).show();
                        sharedPrefHandler.setSharedPreferences("login","false");
                        Intent i = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                    }
                }


                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    if (!progressDialog.isShowing())
                        progressDialog.show();
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

            if(action.equals("it")){
                webView.loadUrl("http://itechnospot.com/blog");
                webView.setVisibility(View.VISIBLE);
            }else {
                webView.postUrl(url, EncodingUtils.getBytes(postData, "UTF-8"));
            }
        }else{

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

            // Setting Dialog Title
            alertDialog.setTitle("No Internet");

            // Setting Dialog Message
            alertDialog.setMessage("Please switch on your Data/WiFi to login");


                // Setting alert dialog icon
                alertDialog.setIcon(R.drawable.fail);

            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            // Showing Alert Message
            alertDialog.show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

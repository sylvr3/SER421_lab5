package edu.asu.brantsylvia.task1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;


public class GameActivity extends Activity {
    private WebView webView;
    private Button button;
    private String name;
    private WebAppInterface webInterface;
    private TextView historyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game);

        webView = (WebView)findViewById(R.id.webView);
        historyText = (TextView)findViewById(R.id.historyText);
        webInterface = new WebAppInterface(this); // Define Javascript interface
        name = getIntent().getStringExtra("name"); // Player name from main activity
        String state = getIntent().getStringExtra("state");
        if (getIntent().getStringExtra("state").equals("true")) {
            readState();
        }

        // Enable javascript and load game logic
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(webInterface, "Android");
        webView.loadUrl("file:///android_asset/cluedo.html");

        Toast.makeText(GameActivity.this, "Game loading...", Toast.LENGTH_SHORT).show();

        // On page load initiate a new game
        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url){
                newGame(name);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            webView.loadUrl("javascript:displayRecord();");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newGame(String playerName){
        webView.loadUrl("javascript:startGame('" + name + "');");
    }

    public void writeHistory(String string) {
        historyText.setText(string);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFilesDir() + "state.txt"));
            bw.write((String)historyText.getText());
            bw.close();
            System.out.println("Writing");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readState() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.getFilesDir() + "state.txt"));

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                try {
                    historyText.append(sCurrentLine.toString() + '\n');
                }
                catch (Exception ex) { }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

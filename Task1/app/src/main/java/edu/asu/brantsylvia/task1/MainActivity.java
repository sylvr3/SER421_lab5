package edu.asu.brantsylvia.task1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {
    private static Button gameButton;
    private static Button resumeButton;
    private static TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);


        gameButton = (Button)findViewById(R.id.newGame);
        resumeButton = (Button)findViewById(R.id.resumeGame);
        name = (EditText)(findViewById(R.id.name));

        gameButton.setOnClickListener(this);
        resumeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        if(view.getId()==R.id.newGame) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("state", "false");
            startActivity(intent);
        }
        // If resume game
        if(view.getId()==R.id.resumeGame) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("state","true");
            startActivity(intent);
        }

    }
}

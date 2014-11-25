package com.example.user.lab5;



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


public class MainActivity extends Activity implements View.OnClickListener,EditText.OnKeyListener{
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

        name.setOnKeyListener(this);
        gameButton.setOnClickListener(this);
        resumeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        if(view.getId()==R.id.newGame) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("name", name.getText().toString());
            startActivity(intent);
        }
        // If resume game
        if(view.getId()==R.id.resumeGame) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("name", name.getText().toString());
            startActivity(intent);
        }

    }
    @Override
    public boolean onKey(View view,int keyPressed, KeyEvent event){

        if(keyPressed==KeyEvent.KEYCODE_ENTER){


        }
        return false;
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

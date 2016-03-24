package com.example.ekta.notes_taking;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void perform_action(View v)
    {
        TextView tv1 = (TextView) findViewById(R.id.text_view1);
        //set text style bold on current font
        tv1.setTypeface(tv1.getTypeface(), Typeface.BOLD);
        tv1.setText("Bold text on current default font.");

        TextView tv2 = (TextView) findViewById(R.id.text_view2);
        //set text style bold and italic and font monospace
        tv2.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
        tv2.setText("Bold and italic text on monospace font.");

        TextView tv3 = (TextView) findViewById(R.id.text_view3);
        //set text style italic and font serif
        tv3.setTypeface(Typeface.SERIF, Typeface.ITALIC);
        tv3.setText("Italic text on serif font by setTypeface() method.");



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







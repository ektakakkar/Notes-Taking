package com.example.ekta.notes_taking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewNoteActivity extends AppCompatActivity {

    TextView title;
    TextView detail;

    long id = -1;
    Notes note = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (TextView) findViewById(R.id.view_title);
        detail = (TextView) findViewById(R.id.view_detail);

        if (savedInstanceState == null) {

            Bundle extra = getIntent().getExtras();

            if (extra != null) {

                id = extra.getLong("ID");

                note = Notes.getNote(id);

                if(note!=null){
                    detail.setText(note.detail);
                    title.setText(note.title);

                }else {

                    Toast.makeText(getApplicationContext(), "Error: Nothing to show", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address[] = new String[1];
                address[0] = "ektakakkar8993@gmail.com";
                Intent intentemail = new Intent(Intent.ACTION_SEND);
                intentemail.setType("*/*");
                intentemail.putExtra(Intent.EXTRA_EMAIL, address);

                intentemail.putExtra(Intent.EXTRA_SUBJECT, "Title: " + title.getText().toString());
                intentemail.putExtra(Intent.EXTRA_TEXT,  "Details: " + detail.getText().toString());
                if (intentemail.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentemail);
                }
            }
        });
    }

}

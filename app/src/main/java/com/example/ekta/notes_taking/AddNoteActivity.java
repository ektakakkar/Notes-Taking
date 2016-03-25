package com.example.ekta.notes_taking;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {



    EditText title, detail;
    Button buttonAdd;
    String notesval;

    Button delete;
    long id = -1;
    Notes note = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (EditText) findViewById(R.id.title);
        detail = (EditText) findViewById(R.id.detail);
        buttonAdd = (Button) findViewById(R.id.buttonadd);

        if (savedInstanceState == null) {

            Bundle extra = getIntent().getExtras();

            if (extra != null) {

                id = extra.getLong("ID");

                note = Notes.getNote(id);

                if(note!=null){
                    detail.setText(note.detail);
                    title.setText(note.title);
                    buttonAdd.setText("Update the Note");
                }
            }
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(id == -1) {
                    notesval = title.getText().toString();
                    Notes note = new Notes();
                    note.title = notesval;
                    note.detail = detail.getText().toString();
                    note.save();
                    Toast.makeText(getApplicationContext(), notesval + " is saved", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    note.detail = detail.getText().toString();
                    note.title = title.getText().toString();
                    note.save();
                    //Notes.update(note);
                    finish();
                }
            }
        });

    }

}

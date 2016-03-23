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



    EditText notes, detail;
    Button buttonAdd;
    String notesval;

    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notes = (EditText) findViewById(R.id.notes);
        detail = (EditText) findViewById(R.id.detail);
        buttonAdd = (Button) findViewById(R.id.buttonadd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notesval = notes.getText().toString();
                Toast.makeText(getApplicationContext(), notesval + " is saved", Toast.LENGTH_LONG).show();
                Notes note = new Notes();
                note.title = notesval;
                note.detail = detail.getText().toString();
                note.save();





                //Notes N = Notes.getNotes();
                //Log.v("Note", N.title);
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                finish();
            }
        });

    }

}

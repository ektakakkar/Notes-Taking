package com.example.ekta.notes_taking;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {

    EditText notes;
    Button buttonAdd;
    String notesval;
    EditText details;

    Button delete;
    ImageView imageview;
    RecyclerView Delete;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    NotesAdapter notesAdapter;

    ArrayList<Notes> allNotesFromDB;
    String TAG = "MainActivity";

    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = (RecyclerView) findViewById(R.id.note_recycler_view);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        setDataset();
        notesAdapter = new NotesAdapter(allNotesFromDB);
        recyclerView.setAdapter(notesAdapter);


        registerForContextMenu(recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Notes note;
                setPosition(position);
                note  = allNotesFromDB.get(position);
                long id = note.getId();
                Intent intent = new Intent(getApplicationContext(), ViewNoteActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                setPosition(position);
                view.showContextMenu();
            }
        }));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivity(intent);

            }
        });

    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setDataset() {
        this.allNotesFromDB = (ArrayList<Notes>) Notes.getNotes();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_popup, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Notes note;
        switch (item.getItemId()) {

            case R.id.Edit:
                note  = allNotesFromDB.get(position);
                long id = note.getId();
                Intent intent = new Intent(this, AddNoteActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
                break;

            case R.id.Delete:
                note = allNotesFromDB.get(position);
                Notes.delete(note);
                allNotesFromDB.remove(position);
                notesAdapter.notifyDataSetChanged();
                //deleteNote(info.id);
                break;

            case R.id.Share:
                note = allNotesFromDB.get(position);
/*                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Title: " + note.title + "\n" + "Details: " + note.detail);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);*/

                String address[] = new String[1];
                address[0] = "ektakakkar8993@gmail.com";
                Intent intentemail = new Intent(Intent.ACTION_SEND);
                intentemail.setType("*/*");
                intentemail.putExtra(Intent.EXTRA_EMAIL, address);

                intentemail.putExtra(Intent.EXTRA_SUBJECT, "Title: " + note.title);
                intentemail.putExtra(Intent.EXTRA_TEXT,  "Details: " + note.detail);
                if (intentemail.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentemail);
                }
            break;
        }

            return true;


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int pos;
        MenuItem Bold;
        MenuItem Italics;

        Bold =(MenuItem) findViewById(R.id.Bold);
        Italics =(MenuItem) findViewById(R.id.Italics);


        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,"Settings selected",Toast.LENGTH_LONG).show();


            case R.id.action_bold:

                Toast.makeText(this,"change to bold",Toast.LENGTH_LONG).show();


            case R.id.action_italic:

                Toast.makeText(this,"change to italics",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }*/
    }
}
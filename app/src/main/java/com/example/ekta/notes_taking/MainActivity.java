package com.example.ekta.notes_taking;

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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener{

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


        recyclerView = (RecyclerView)findViewById(R.id.note_recycler_view);
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
                Log.d(TAG, "onClick");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d(TAG, position + "");
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

    public void setPosition(int position){
        this.position = position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setDataset(){
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
        switch (item.getItemId()) {
            case R.id.Open:
             Notes.getNotes();
                return true;

            case R.id.Edit:
              Notes note2= allNotesFromDB.get(position);
                int startpos;
                int endpos;

                
                Notes.update(note2);
                allNotesFromDB.add(position,note2);
                notesAdapter.notifyDataSetChanged();
                return true;
            case R.id.Delete:
                Notes note = allNotesFromDB.get(position);
                Notes.delete(note);
                allNotesFromDB.remove(position);
                notesAdapter.notifyDataSetChanged();
                //deleteNote(info.id);
                return true;
            case  R.id.Share:


                Log.i("Send email", "");
                String[] TO = {""};
                String[] CC = {""};

                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending email...", "");
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

                startActivity(Intent.createChooser(emailIntent, "Send Email"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, notesval);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }




    @Override

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
    }
}

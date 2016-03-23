package com.example.ekta.notes_taking;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
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

public class MainActivity extends AppCompatActivity {

    EditText notes;
    Button buttonAdd;
    String notesval;

    Button delete;
    ImageView imageview;
    RecyclerView Delete;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    NotesAdapter notesAdapter;

    ArrayList<Notes> allNotesFromDB;
    String TAG = "MainActivity";





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

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "onClick");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d(TAG, "onLongClick");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setDataset(){
        this.allNotesFromDB = (ArrayList<Notes>) Notes.getNotes();
    }





/*
public boolean OnLongClick(MenuItem item){

    RecyclerView Edit;
    RecyclerView Delete;

    Edit=(RecyclerView) findViewById(R.id.Edit);
    Delete=(RecyclerView) findViewById(R.id.Delete);



    switch (item.getItemId()) {
        case R.id.Edit :

             Toast.makeText(this,"Note Edited",Toast.LENGTH_LONG).show();
            return true;



        case R.id.Delete :

            Toast.makeText(this,"Note Deleted Permanently",Toast.LENGTH_LONG).show();
            return true;


        default:
            return true;
    }


}
*/



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

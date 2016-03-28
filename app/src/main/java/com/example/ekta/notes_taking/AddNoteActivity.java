package com.example.ekta.notes_taking;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class AddNoteActivity extends AppCompatActivity {



    EditText title, detail;
    ImageView image;
    Button buttonAdd;
    String notesval;
    String picPath = "";
    Button delete;
    long id = -1;
    Notes note = null;
    static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (EditText) findViewById(R.id.title);
        detail = (EditText) findViewById(R.id.detail);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        image=(ImageView)findViewById(R.id.note_img);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i , RESULT_LOAD_IMAGE);                 //On clicking the default image it takes to the photo gallery.
            }
        });

        if (savedInstanceState == null) {

            Bundle extra = getIntent().getExtras();

            if (extra != null) {

                id = extra.getLong("ID");

                note = Notes.getNote(id);                         // On clicking the edit option the contents of the notes are available

                if(note!=null){
                    detail.setText(note.detail);
                    title.setText(note.title);

                    File imgFile = new  File(note.image);           //opens the image file

                    if(imgFile.exists()){                          //if the image exixts then we set the image to  Imageview

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                        image.setImageBitmap(myBitmap);

                    }

                    buttonAdd.setText("Update the Note");
                }
            }
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(id == -1) {
                    notesval = title.getText().toString();                      //to save the note
                    Notes note = new Notes();
                    note.title = notesval;
                    note.detail = detail.getText().toString();
                    note.image = picPath;
                    //Log.d("ADDNOTE" , picPath);
                    note.save();
                    Toast.makeText(getApplicationContext(), notesval + " is saved", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    note.detail = detail.getText().toString();                //to edit the note
                    note.title = title.getText().toString();
                    note.image = picPath;

                    note.save();
                    //Notes.update(note);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(RESULT_LOAD_IMAGE == requestCode && resultCode == RESULT_OK && data !=null){
            Uri selectedImage = data.getData();

            String[] filePathCol = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathCol, null, null, null);

            cursor.moveToFirst();

            int colIndex = cursor.getColumnIndex(filePathCol[0]);
            picPath = cursor.getString(colIndex);

            cursor.close();
                                                    // sets the image to Imageview and works database
            Bitmap bit = null;

            try {
                bit = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            image.setImageBitmap(bit);
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =                          //get the actual bitmap image from the uri
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
}

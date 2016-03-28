package com.example.ekta.notes_taking;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EKTA on 20/03/16.
 */

@Table(name="notes")
public class Notes extends Model {       //active android model

    @Column(name = "title")               //column names of the database are title, detail and image
    public String title;

    @Column(name = "detail")
    public String detail;

    @Column(name = "image")
    public String image;

    public Notes(){
        super();
    }

    public Notes(String title , String detail, String image){
        super();

        this.title = title;
        this.detail = detail;
        this.image = image;
    }



    public static Notes getRandom() {
        return new Select().from(Notes.class).orderBy("Title").executeSingle();
    }


    public static Notes getNote(long id){
        return new Select().from(Notes.class).where("id = ?", id)          // to get the note from the id
                .executeSingle();
    }

    public static List<Notes> getNotes() {
        return new Select().from(Notes.class).execute();
    }      // get all the notes



    public static void delete(Notes notes){

        notes.delete();   //deleting the notes
   }

}


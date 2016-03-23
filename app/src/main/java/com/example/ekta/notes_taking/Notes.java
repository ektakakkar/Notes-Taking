package com.example.ekta.notes_taking;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EKTA on 20/03/16.
 */

@Table(name="notes")
public class Notes extends Model {

    @Column(name = "title")
    public String title;

    @Column(name = "detail")
    public String detail;

    public Notes(){
        super();
    }

    public Notes(String title , String detail){
        super();

        this.title = title;
        this.detail = detail;
    }



    public static Notes getRandom() {
        return new Select().from(Notes.class).orderBy("Title").executeSingle();
    }


    public static Notes getNote(int id){
        return new Select().from(Notes.class).where("id = ?", id)
                .executeSingle();
    }

    public static List<Notes> getNotes() {
        return new Select().from(Notes.class).execute();
    }


    public static void update(Notes notes) {
        getNotes().add(1, notes);
    }

    public static void delete(Notes notes){

        notes.delete();
   }

}


package com.example.ekta.notes_taking;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
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

    public static List<Notes> getNotes() {
        return new Select().from(Notes.class).execute();
    }




}


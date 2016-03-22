package com.example.ekta.notes_taking;

/**
 * Created by EKTA on 21/03/16.
 */
public class NoteItem {
    private String title;
    private String details;


    public NoteItem(String title, String details){
        this.title=title;
        this.details=details;

    }

    public String getTitle(){
        return title;
    }

    public String getDetails(){
        return details;
    }
}

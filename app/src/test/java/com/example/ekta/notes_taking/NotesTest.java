package com.example.ekta.notes_taking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by EKTA on 29/03/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class NotesTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test

    /*public void delete(Notes notes){
// will check that the note that is clicked is the only note that is deleted by checking the id is the database
        Notes notes1 = new Notes();
       assertEquals(notes1.getId(), notes.getId());



    }*/


    public void runTestIdGenerated(){
        AddNoteActivity object = new AddNoteActivity();
        long temp=object.generateId();

        assertTrue(temp>0);
    }



    @After
    public void tearDown() throws Exception {

    }
}
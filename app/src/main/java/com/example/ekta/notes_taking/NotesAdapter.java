package com.example.ekta.notes_taking;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by EKTA on 22/03/16.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    ArrayList<Notes> noteItems;

    public NotesAdapter(ArrayList<Notes> noteItems){
        this.noteItems =  noteItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.details.setText(noteItems.get(position).detail);

    }

    @Override
    public int getItemCount() {
        return noteItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView details;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageview);
            details = (TextView)itemView.findViewById(R.id.note_detail);
        }

    }

}
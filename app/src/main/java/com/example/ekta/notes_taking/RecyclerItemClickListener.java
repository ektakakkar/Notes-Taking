package com.example.ekta.notes_taking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by EKTA on 23/03/16.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    public interface OnItemClickListener{

        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View childView = rv.findChildViewUnder(e.getX(), e.getY());

        return childView != null && mGestureDetector.onTouchEvent(e);

    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {



    }

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView ,OnItemClickListener listener) {

        mListener = listener;

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//Recyclerview Gesture Detection Library
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());         // gets the card that is clicked
                mListener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                return true;

            }

            @Override
            public void onLongPress(MotionEvent e) {

                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

                if (childView != null && mListener != null) {
                    mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                }

            }
        });
    }

}

package com.example.armion.spellbook.hud.viewHolder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class MyViewHolder<I> extends RecyclerView.ViewHolder {

    protected List<Integer> selectedList;
    protected final AppCompatActivity parent;
    protected String type;

    public MyViewHolder(final View view, List<Integer> selectedList, AppCompatActivity parent){

        super(view);
        this.selectedList = selectedList;
        this.parent = parent;
    }




    public abstract void display(I item);

}

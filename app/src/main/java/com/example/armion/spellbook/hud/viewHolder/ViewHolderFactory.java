package com.example.armion.spellbook.hud.viewHolder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public final class ViewHolderFactory {


    private static volatile ViewHolderFactory instance = null;

    private ViewHolderFactory(){

    }

    public final static ViewHolderFactory getInstance(){


        if(ViewHolderFactory.instance == null){

            synchronized (ViewHolderFactory.class) {
                if(ViewHolderFactory.instance == null) {
                    ViewHolderFactory.instance = new ViewHolderFactory();
                }
            }
        }

        return  ViewHolderFactory.instance;
    }


    public RecyclerView.ViewHolder create(Class typeViewHolder, View itemView, List<Integer> selectedList, AppCompatActivity parent) throws ViewHolderCreationException {

        if(typeViewHolder.equals(MetamagicViewHolder.class)){
            return new MetamagicViewHolder(itemView, selectedList, parent);
        }
        else if (typeViewHolder.equals(SpellSlotViewHolder.class)){
            return  new SpellSlotViewHolder(itemView, selectedList, parent);
        }
        else if (typeViewHolder.equals(SpellViewHolder.class)){
            return  new SpellViewHolder(itemView, selectedList, parent);
        }
        else if (typeViewHolder.equals(ItemViewHolder.class)){
            return  new ItemViewHolder(itemView, selectedList, parent);
        }

        throw new ViewHolderCreationException();

    }
}

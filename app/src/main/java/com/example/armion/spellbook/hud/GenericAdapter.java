package com.example.armion.spellbook.hud;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.armion.spellbook.entity.Entity;
import com.example.armion.spellbook.entity.EntityCreationException;
import com.example.armion.spellbook.entity.EntityFactory;
import com.example.armion.spellbook.hud.viewHolder.MyViewHolder;
import com.example.armion.spellbook.hud.viewHolder.ViewHolderCreationException;
import com.example.armion.spellbook.hud.viewHolder.ViewHolderFactory;

import java.util.List;

public class GenericAdapter<I extends Entity, VH extends MyViewHolder<I>> extends RecyclerView.Adapter<VH>{


    private List<Integer> selectedList;
    private List<I> itemList;
    private int viewID;

    private Class vhClass;
    private Class iClass;

    private final AppCompatActivity parent;


    public GenericAdapter(AppCompatActivity parent, List<Integer> selectedList, List<I> itemList, int viewID, Class vhClass, Class iClass){
        super();

        this.parent = parent;
        this.selectedList = selectedList;
        this.itemList = itemList;
        this.viewID = viewID;

        this.vhClass = vhClass;
        this.iClass = iClass;
    }



    @Override
    public int getItemCount(){
        return itemList.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(viewID, parent, false);

        try {

            return (VH) ViewHolderFactory.getInstance().create(vhClass, view, selectedList, this.parent);

        } catch (ViewHolderCreationException e) {

        }

        return null;

    }


    /**
     * @param holder the older which display
     * @param position the position of the item to display from the list
     */
    @Override
    public void onBindViewHolder(VH holder, int position){

        I item = itemList.get(position);
        holder.display(item);

    }

    /**
     * a method tu delete an item from the list and update the adapter
     * @param index the index of the item to edit
     */
    public List<I> deleteItem(int index){

        //let's be safe !
        if(index >= 0 && index < itemList.size())
        {
            this.itemList.remove(index);
            this.notifyItemRemoved(index);
            this.notifyItemRangeChanged(0, itemList.size());
        }

        return itemList;

    }

    /**
     * a method to edit an object from the list and update the adapter
     * @param index the index of the item to edit
     */
    public List<I> editItem(int index, I item){

        //let's be safe
        if(index >= 0 && index < itemList.size()) {
            this.itemList.set(index, item);
            this.notifyItemChanged(index);
        }

        return itemList;
    }


    public List<I> addItem(I item){

        this.itemList.add(item);
        this.notifyDataSetChanged();

        return itemList;
    }

    public void saveList(){



       // FileStream.saveMetamagic((List<Metamagic>) itemList, parent.getBaseContext(), "Elyndil");
       try
       {
           EntityFactory.getInstance().create(iClass).save(itemList, parent.getBaseContext(), "Elyndil");
       }
       catch (EntityCreationException e){

       }

    }

}
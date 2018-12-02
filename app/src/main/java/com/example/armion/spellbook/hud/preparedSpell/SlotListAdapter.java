package com.example.armion.spellbook.hud.preparedSpell;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.armion.spellbook.FileStream;
import com.example.armion.spellbook.R;
import com.example.armion.spellbook.SpellSlot;

import java.util.List;

public class SlotListAdapter  extends RecyclerView.Adapter<SlotListAdapter.MyViewHolder>{


    private List<Integer> selectedList;
    private List<SpellSlot> spellSlotList ;


    private final AppCompatActivity parent;


    SlotListAdapter(AppCompatActivity parent, List<Integer> selectedList, List<SpellSlot> spellSlotList){
        super();

        this.parent = parent;
        this.selectedList = selectedList;
        this.spellSlotList = spellSlotList;
    }



    @Override
    public int getItemCount(){
        return spellSlotList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.slot_list, parent, false);

        return new MyViewHolder(view);

    }

    /**
     * @param holder the older which display
     * @param position the position of the item to display from the list
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        SpellSlot spellSlot = spellSlotList.get(position);
        holder.display(spellSlot);

    }

    /**
     * a method tu delete an item from the list and update the adapter
     * @param index the index of the item to edit
     */
    public List<SpellSlot> deleteItem(int index){

        //let's be safe !
        if(index >= 0 && index < spellSlotList.size())
        {
            this.spellSlotList.remove(index);
            this.notifyItemRemoved(index);
            this.notifyItemRangeChanged(0, spellSlotList.size());
        }

        return spellSlotList;

    }

    /**
     * a method to edit an object from the list and update the adapter
     * @param index the index of the item to edit
     */
    public List<SpellSlot> editItem(int index, SpellSlot spellSlot){

        //let's be safe
        if(index >= 0 && index < spellSlotList.size()) {
            this.spellSlotList.set(index, spellSlot);
            this.notifyItemChanged(index);
        }

        return spellSlotList;
    }


    public List<SpellSlot> addItem(SpellSlot spellSlot){

        this.spellSlotList.add(spellSlot);
        this.notifyDataSetChanged();

        return spellSlotList;

    }

    public void saveSpellSlot(){

        FileStream.saveSpellSlot(spellSlotList, parent.getBaseContext(), "Elyndil");

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView spellName;
        private final TextView metamagicList;
        private final TextView slotLevel;
        private final TextView spellLevel;
        private final Switch used;



        private SpellSlot currentSpellSlot;

        /**
         * create the view holder and binding his field to the variables
         * @param itemView
         */
        public MyViewHolder(final View itemView) {

            super(itemView);

            //the fields for the view
             spellName = itemView.findViewById(R.id.spellName);
             metamagicList = itemView.findViewById(R.id.metamagicList);
             slotLevel = itemView.findViewById(R.id.slotLevel);
             spellLevel = itemView.findViewById(R.id.spellLevel);
             used = itemView.findViewById(R.id.usedSwitch);





             used.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     if(isChecked){
                         ((View) buttonView.getParent()).setBackgroundColor(Color.RED);
                     }
                     else{
                         ((View) buttonView.getParent()).setBackgroundColor(Color.WHITE);
                     }

                 }
             });



            //when the user click on the item we mark the item as selected and add him in the selected item list
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    //let's update the selectedList
                    if(view.isSelected()){
                        view.setSelected(false);
                        selectedList.remove((Integer) getAdapterPosition());
                    }
                    else{
                        view.setSelected(true);
                        selectedList.add((Integer) getAdapterPosition());
                    }

                    //when a meta magic is selected we allow the modifications
                    if(! (selectedList == null) && ! selectedList.isEmpty()){
                        parent.findViewById(R.id.buttonDelete).setEnabled(true);

                        //we allow modification only if there is one item selected
                        if(selectedList.size() == 1)
                        {
                            parent.findViewById(R.id.buttonEdit).setEnabled(true);
                        }
                        else
                        {
                            parent.findViewById(R.id.buttonEdit).setEnabled(false);
                        }

                    }
                    else{
                        parent.findViewById(R.id.buttonDelete).setEnabled(false);
                        parent.findViewById(R.id.buttonEdit).setEnabled(false);
                    }

                }
            });
        }


        /**
         * binding our class to the view
         * @param spellSlot the metamagic to display
         */
        public void display(SpellSlot spellSlot) {


            //we actualize the selected metamagic
            currentSpellSlot = spellSlot;

            spellName.setText(spellSlot.getPreparedSpell().getSpell().getName());
            metamagicList.setText(spellSlot.getPreparedSpell().getMetamagicList().toString());
            slotLevel.setText(spellSlot.getLevel() + "");
            spellLevel.setText(spellSlot.getPreparedSpell().getLevel() + "");

        }
    }
}

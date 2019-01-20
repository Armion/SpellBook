package com.example.armion.spellbook.hud.viewHolder;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.armion.spellbook.R;
import com.example.armion.spellbook.entity.SpellSlot;

import java.util.List;

public class SpellSlotViewHolder extends MyViewHolder<SpellSlot> {

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
    public SpellSlotViewHolder(final View itemView, final List<Integer> selectedList, final AppCompatActivity parent) {

        super(itemView, selectedList, parent);

        //the fields for the view
        spellName = itemView.findViewById(R.id.spellName);
        metamagicList = itemView.findViewById(R.id.metamagicList);
        slotLevel = itemView.findViewById(R.id.slotLevel);
        spellLevel = itemView.findViewById(R.id.spellLevel);
        used = itemView.findViewById(R.id.usedSwitch);


        used.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                if(currentSpellSlot.getLevel() < currentSpellSlot.getPreparedSpell().getLevel()){
                    ((View)used).setBackgroundColor(Color.DKGRAY);
                }
                else
                {
                    if(isChecked){
                        ((View) buttonView).setBackgroundColor(Color.RED);
                        currentSpellSlot.setUsed(true);
                    }
                    else{
                        ((View) buttonView).setBackgroundColor(Color.WHITE);
                        currentSpellSlot.setUsed(false);
                    }

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

        if(currentSpellSlot.isUsed()){
            used.setChecked(true);
            System.out.println("checked !");
        }

        spellName.setText(spellSlot.getPreparedSpell().getSpell().getName());
        metamagicList.setText(spellSlot.getPreparedSpell().getMetamagicList().toString());
        slotLevel.setText(spellSlot.getLevel() + "");
        spellLevel.setText(spellSlot.getPreparedSpell().getLevel() + "");

        if(spellSlot.getLevel() < spellSlot.getPreparedSpell().getLevel()){
            ((View)used).setBackgroundColor(Color.DKGRAY);

        }

    }
}

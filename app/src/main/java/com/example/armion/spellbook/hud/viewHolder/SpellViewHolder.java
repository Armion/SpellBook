package com.example.armion.spellbook.hud.viewHolder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.armion.spellbook.R;
import com.example.armion.spellbook.entity.spell.Spell;

import java.util.List;

public class SpellViewHolder extends MyViewHolder<Spell> {

    private final TextView name;
    private final TextView description;
    private final TextView level;
    private final TextView school;
    private final TextView descriptors;
    private final TextView range;
    private final TextView area;
    private final TextView duration;
    private final TextView castingTime;
    private final TextView link;


    private Spell currentSpell;

    /**
     * create the view holder and binding his field to the variables
     * @param itemView
     */
    public SpellViewHolder(final View itemView, final List<Integer> selectedList, final AppCompatActivity parent) {

        super(itemView, selectedList, parent);

        //the field for the view
        name = itemView.findViewById(R.id.spellName);
        description =  itemView.findViewById(R.id.spellDescription);
        level =  itemView.findViewById(R.id.spellLevel);
        school = itemView.findViewById(R.id.schoolText);
        descriptors = itemView.findViewById(R.id.descriptorsText);
        range = itemView.findViewById(R.id.rangeText);
        area = itemView.findViewById(R.id.areaText);
        duration = itemView.findViewById(R.id.durationText);
        castingTime = itemView.findViewById(R.id.castingText);
        link = itemView.findViewById(R.id.spellLink);








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
     * @param spell the metamagic to display
     */
    public void display(Spell spell) {


        //we actualize the selected metamagic
        currentSpell = spell;

        name.setText(spell.getName());
        level.setText(Integer.toString(spell.getLevel()));
        description.setText(spell.getDescription());
        school.setText(spell.getSchool().toString());
        descriptors.setText(spell.getDescriptorList().toString());
        range.setText(spell.getRange());
        area.setText(spell.getArea());
        duration.setText(spell.getDuration());
        castingTime.setText(spell.getCastingTime());
        link.setText(spell.getLink());

    }
}

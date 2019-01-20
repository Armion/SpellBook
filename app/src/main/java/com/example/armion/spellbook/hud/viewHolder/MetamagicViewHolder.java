package com.example.armion.spellbook.hud.viewHolder;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.armion.spellbook.R;
import com.example.armion.spellbook.entity.spell.Metamagic;

import java.util.List;

public class MetamagicViewHolder  extends MyViewHolder<Metamagic> {

    private final TextView name;
    private final TextView description;
    private final TextView level;



    private Metamagic currentMetamagic;

    /**
     * create the view holder and binding his field to the variables
     * @param itemView
     */
    public MetamagicViewHolder(final View itemView, final List<Integer> selectedList, final AppCompatActivity parent) {

        super(itemView, selectedList, parent);

        this.type = "Metamagic";


        //the field for the view
        name = itemView.findViewById(R.id.metamagicName);
        description =  itemView.findViewById(R.id.metaSpellDescription);
        level =  itemView.findViewById(R.id.metaSpellLevel);





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
     * @param metamagic the metamagic to display
     */
    public void display(Metamagic metamagic) {

        //we actualize the selected metamagic
        currentMetamagic = metamagic;
        name.setText(metamagic.getName());
        level.setText(Integer.toString(metamagic.getLevel()));
        description.setText(metamagic.getDescription());
    }

    public static String getType(){

        return "Metamagic";
    }
}
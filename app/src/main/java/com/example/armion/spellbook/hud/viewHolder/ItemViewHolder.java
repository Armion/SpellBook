package com.example.armion.spellbook.hud.viewHolder;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.armion.spellbook.R;
import com.example.armion.spellbook.entity.Item;

import java.util.List;

public class ItemViewHolder extends MyViewHolder<Item>{
    private final TextView name;
    private final TextView description;
    private final TextView weight;
    private final TextView value;
    private final TextView durability;


    private Item currentItem;

    /**
     * create the view holder and binding his field to the variables
     * @param itemView
     */
    public ItemViewHolder(final View itemView, final List<Integer> selectedList, final AppCompatActivity parent) {

        super(itemView, selectedList, parent);

        //the field for the view
        name = itemView.findViewById(R.id.itemNameInput);
        description =  itemView.findViewById(R.id.descriptionInput);
        weight = itemView.findViewById(R.id.weightInput);
        value = itemView.findViewById(R.id.valueInput);
        durability = itemView.findViewById(R.id.durabilityInput);









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
     * @param item the metamagic to display
     */
    public void display(Item item) {


        //we actualize the selected metamagic
        currentItem = item;
        name.setText(item.getName());
        description.setText(item.getDescription());
        value.setText(item.getValue().toString());
        weight.setText(Float.toString(item.getWeight()));
        durability.setText(Integer.toString(item.getDurability()));
    }
}


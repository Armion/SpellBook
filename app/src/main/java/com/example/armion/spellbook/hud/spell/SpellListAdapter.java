package com.example.armion.spellbook.hud.spell;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.armion.spellbook.R;
import com.example.armion.spellbook.spell.Metamagic;
import com.example.armion.spellbook.spell.Spell;

import java.util.List;


public class SpellListAdapter extends RecyclerView.Adapter<SpellListAdapter.MyViewHolder>{





    private List<Integer> selectedList;
    private List<Spell> spellList;


    private final AppCompatActivity parent;


    public SpellListAdapter(AppCompatActivity parent, List<Integer> selectedList, List<Spell> metamagicList){
        super();

        this.parent = parent;
        this.selectedList = selectedList;
        this.spellList = metamagicList;
    }



    @Override
    public int getItemCount(){
        return spellList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.meta_spell_list, parent, false);

        return new MyViewHolder(view);

    }

    /**
     * @param holder the older which display
     * @param position the position of the item to display from the list
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        Spell spell = spellList.get(position);
        holder.display(spell);

    }

    /**
     * a method tu delete an item from the list and update the adapter
     * @param index the index of the item to edit
     */
    public List<Spell> deleteItem(int index){

        //let's be safe !
        if(index >= 0 && index < spellList.size())
        {
            this.spellList.remove(index);
            this.notifyItemRemoved(index);
            this.notifyItemRangeChanged(0, spellList.size());
        }

        return spellList;

    }

    /**
     * a method to edit an object from the list and update the adapter
     * @param index the index of the item to edit
     */
    public List<Spell> editItem(int index, Spell spell){

        //let's be safe
        if(index >= 0 && index < spellList.size()) {
            System.out.println(spell.getName());
            this.spellList.set(index, spell);
            this.notifyItemChanged(index);
        }

        return spellList;
    }


    public List<Spell> addItem(Spell spell){

        this.spellList.add(spell);
        this.notifyDataSetChanged();

        return spellList;

    }

    public void saveSpell(){



    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private final TextView level;


        private Spell currentMetamagic;

        /**
         * create the view holder and binding his field to the variables
         * @param itemView
         */
        public MyViewHolder(final View itemView) {

            super(itemView);

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
         * @param spell the metamagic to display
         */
        public void display(Spell spell) {

            //we actualize the selected metamagic
            currentMetamagic = spell;
            name.setText(spell.getName());
            level.setText(Integer.toString(spell.getLevel()));
            description.setText(spell.getDescription());
        }
    }
}

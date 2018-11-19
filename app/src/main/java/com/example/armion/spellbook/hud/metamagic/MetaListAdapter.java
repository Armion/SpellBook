package com.example.armion.spellbook.hud.metamagic;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.armion.spellbook.R;
import com.example.armion.spellbook.spell.Metamagic;

import java.util.List;


public class MetaListAdapter extends RecyclerView.Adapter<MetaListAdapter.MyViewHolder>{





    private List<Integer> selectedList;
    private List<Metamagic> metamagicList;


     private final AppCompatActivity parent;


    public MetaListAdapter(AppCompatActivity parent, List<Integer> selectedList, List<Metamagic> metamagicList){
        super();

        this.parent = parent;
        this.selectedList = selectedList;
        this.metamagicList = metamagicList;
    }



    @Override
    public int getItemCount(){
        return metamagicList.size();
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

        Metamagic metamagic = metamagicList.get(position);
        holder.display(metamagic);

    }

    /**
     * a method tu delete an item from the list and update the adapter
     * @param index the index of the item to edit
     */
    public void deleteItem(int index){
        //let's be safe !
        if(index >= 0 && index < metamagicList.size())
        {
            this.metamagicList.remove(index);
            this.notifyItemRemoved(index);
            this.notifyItemRangeChanged(0, metamagicList.size());
        }

    }

    /**
     * a method to edit an object from the list and update the adapter
     * @param index the index of the item to edit
     */
    public void editItem(int index){

        //let's be safe
        if(index >= 0 && index < metamagicList.size()) {
            this.metamagicList.set(index, new Metamagic("nom de meta magie au hasard", 99, "bravo le veau !"));
            this.notifyItemChanged(index);
        }
    }

    public void addItem(){

        this.metamagicList.add(new Metamagic("ajout", 42, "la bonne farce ..."));
        this.notifyDataSetChanged();

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private final TextView level;


        private Metamagic currentMetamagic;

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
         * @param metamagic the metamagic to display
         */
        public void display(Metamagic metamagic) {

            //we actualize the selected metamagic
            currentMetamagic = metamagic;
            name.setText(metamagic.getName());
            level.setText(Integer.toString(metamagic.getLevel()));
            description.setText(metamagic.getDescription());
        }
    }
}

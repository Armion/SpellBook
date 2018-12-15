package com.example.armion.spellbook.hud.Summary;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.armion.spellbook.FileStream;
import com.example.armion.spellbook.Item;
import com.example.armion.spellbook.R;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder>{





    private List<Integer> selectedList;
    private List<Item> itemList;


    private final AppCompatActivity parent;


    ItemListAdapter(AppCompatActivity parent, List<Integer> selectedList, List<Item> itemList){
        super();

        this.parent = parent;
        this.selectedList = selectedList;
        this.itemList = itemList;
    }



    @Override
    public int getItemCount(){
        return itemList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.items_list, parent, false);

        return new MyViewHolder(view);

    }

    /**
     * @param holder the older which display
     * @param position the position of the item to display from the list
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        Item item = itemList.get(position);
        holder.display(item);

    }

    /**
     * a method tu delete an item from the list and update the adapter
     * @param index the index of the item to edit
     */
    public List<Item> deleteItem(int index){

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
    public List<Item> editItem(int index, Item item){

        //let's be safe
        if(index >= 0 && index < itemList.size()) {
            this.itemList.set(index, item);
            this.notifyItemChanged(index);
        }

        return itemList;
    }


    public List<Item> addItem(Item item){

        this.itemList.add(item);
        this.notifyDataSetChanged();

        return itemList;

    }

    public void saveItems(){

        FileStream.saveBag(itemList, parent.getBaseContext(), "Elyndil");

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

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
        public MyViewHolder(final View itemView) {

            super(itemView);

            //the field for the view
            name = itemView.findViewById(R.id.itemNameInput);
            description =  itemView.findViewById(R.id.descriptionInput);
            weight = itemView.findViewById(R.id.descriptionInput);
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
            weight.setText(Integer.toString(item.getDurability()));
            durability.setText(Float.toString(item.getDurability()));
        }
    }
}

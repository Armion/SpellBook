/**
 * @author Armion
 * @version 0.01
 */

package com.example.armion.spellbook.hud.Summary;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.armion.spellbook.entity.Character;
import com.example.armion.spellbook.FileStream;
import com.example.armion.spellbook.entity.Item;
import com.example.armion.spellbook.entity.Money;
import com.example.armion.spellbook.R;
import com.example.armion.spellbook.hud.GenericAdapter;
import com.example.armion.spellbook.hud.StatisticsActivity;
import com.example.armion.spellbook.hud.preparedSpell.PreparedSpellsActivity;
import com.example.armion.spellbook.hud.viewHolder.ItemViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SummaryActivity extends AppCompatActivity implements View.OnClickListener, CreateItemDialog.NoticeDialogListener{


    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    private Button reduceButton;
    private Button increaseButton;
    private TextView pvText;
    private TextView amount;

    private TextView platinum;
    private TextView gold;
    private  TextView silver;
    private  TextView copper;

    private GenericAdapter<Item, ItemViewHolder> itemListAdapter;
    private List<Integer> selectedItems;
    private RecyclerView itemsRecycler;

    private Character character;

    private CreateItemDialog createItemDialog = new CreateItemDialog();
    private  CreateItemDialog editItemDialog = new CreateItemDialog();

    private Button addButton;
    private Button deleteButton;
    private Button editButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        this.setTitle("Summary");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        selectedItems = new ArrayList<>();



        reduceButton = findViewById(R.id.reduceLife);
        reduceButton.setOnClickListener(this);

        increaseButton = findViewById(R.id.increaseLife);
        increaseButton.setOnClickListener(this);

        character = FileStream.getCharacter("Elyndil", getBaseContext());


        pvText = findViewById(R.id.pvInput);
        pvText.setText( character.getHp() + "/" + character.getMaxHP());

        platinum = findViewById(R.id.platinumInput);
        platinum.setText(character.getMoney().getPlatinumPieces() + "");

        gold = findViewById(R.id.goldInput);
        gold.setText(character.getMoney().getGoldPieces() + "");

        silver = findViewById(R.id.silverInput);
        silver.setText(character.getMoney().getSilverPieces() + "");

        copper = findViewById(R.id.copperInput);
        copper.setText(character.getMoney().getCopperPieces() + "");

        amount = findViewById(R.id.amountInput);

        //The list implementation

        addButton = findViewById(R.id.buttonCreate);
        deleteButton = findViewById(R.id.buttonDelete);
        editButton = findViewById(R.id.buttonEdit);


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //to avoid to delete an item after the indice of on already deleted item
                Collections.sort(selectedItems);
                Collections.reverse(selectedItems);

                for(Integer i : selectedItems){
                    character.getBag().setItems(itemListAdapter.deleteItem(i));
                }

                buttonClicked();

            }
        });

        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //getting the selected metamagic
                Item item = character.getBag().getItems().get(selectedItems.get(0));

                //passing the metamagic to the editDialog
                Bundle bundle = new Bundle();
                bundle.putString("name", item.getName());
                bundle.putString("description", item.getDescription());
                bundle.putString("platinium", Integer.toString( item.getValue().getPlatinumPieces()));
                bundle.putString("gold", Integer.toString( item.getValue().getGoldPieces()));
                bundle.putString("silver", Integer.toString( item.getValue().getSilverPieces()));
                bundle.putString("copper", Integer.toString( item.getValue().getCopperPieces()));

                editItemDialog.setArguments(bundle);
                editItemDialog.show(getSupportFragmentManager(), "edit item");

            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createItemDialog.show(getSupportFragmentManager(), "create item");
                //not really need, but it's easier to update the viewer
                buttonClicked();

            }

        });



        itemListAdapter = new GenericAdapter(this, selectedItems, character.getBag().getItems(), R.layout.items_list, ItemViewHolder.class, Item.class);

        itemsRecycler = findViewById(R.id.recyclerViewItems);

        if(itemsRecycler != null)
        {
            itemsRecycler.setLayoutManager(new LinearLayoutManager(this));
            itemsRecycler.setAdapter(itemListAdapter);
        }



    }

    public void buttonClicked(){

        for(int i = 0; i < 0 ; itemsRecycler.getChildCount()){
            itemsRecycler.getChildAt(i).setSelected(false);
        }

        selectedItems.clear();
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        FileStream.saveLife(character.getHp(), getBaseContext(), "Elyndil");
                        FileStream.saveMoney(
                                getBaseContext(),
                                new Money(
                                        Integer.valueOf(copper.getText().toString()),
                                        Integer.valueOf(silver.getText().toString()),
                                        Integer.valueOf(gold.getText().toString()),
                                        Integer.valueOf(platinum.getText().toString())
                                ),
                                "Elyndil");
                        startActivity(new Intent(this, StatisticsActivity.class));
                    }

                    // Right to left swipe action
                    else
                    {
                        FileStream.saveLife(character.getHp(), getBaseContext(), "Elyndil");
                        FileStream.saveMoney( getBaseContext(),
                                new Money(
                                        Integer.valueOf(copper.getText().toString()),
                                        Integer.valueOf(silver.getText().toString()),
                                        Integer.valueOf(gold.getText().toString()),
                                        Integer.valueOf(platinum.getText().toString())
                                ),
                                "Elyndil");
                        startActivity(new Intent(this, PreparedSpellsActivity.class));

                    }

                    FileStream.saveItems(character.getBag().getItems(), this.getBaseContext(), "Elyndil");

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {

        int life = Integer.valueOf(amount.getText().toString());

        if(v == reduceButton){
            character.setHp(character.getHp() - life);
        }
        else{
            character.setHp(character.getHp() + life);
        }

        pvText.setText(character.getHp() + "/" + character.getMaxHP());

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Item item) {

        if(dialog == createItemDialog){
            character.getBag().setItems(itemListAdapter.addItem(item));
        }
        else if(dialog == editItemDialog){
            character.getBag().setItems(itemListAdapter.editItem(selectedItems.get(0), item));
            buttonClicked();
        }


    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}

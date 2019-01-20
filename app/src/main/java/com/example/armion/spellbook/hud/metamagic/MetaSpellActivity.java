package com.example.armion.spellbook.hud.metamagic;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.armion.spellbook.FileStream;
import com.example.armion.spellbook.R;
import com.example.armion.spellbook.hud.GenericAdapter;
import com.example.armion.spellbook.hud.spell.SpellBookActivity;
import com.example.armion.spellbook.hud.viewHolder.MetamagicViewHolder;
import com.example.armion.spellbook.entity.spell.Metamagic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MetaSpellActivity extends AppCompatActivity implements CreateMetamagicDialog.NoticeDialogListener{


    // a simple list for test waiting for the loading system for real metamagic spells
    private List<Metamagic> metamagicList = new ArrayList<>();

    //list of the item selected to keep them in mind
    private List<Integer> metamagicSelected = new ArrayList<>();

    private CreateMetamagicDialog editDialog = new CreateMetamagicDialog();
    private CreateMetamagicDialog createDialog = new CreateMetamagicDialog();


    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private GenericAdapter<Metamagic, MetamagicViewHolder> metaListAdapter ;
    private Button addButton;
    private Button deleteButton;
    private Button editButton;
    private RecyclerView metaMagicRecycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        metamagicList = FileStream.getCharacter("Elyndil", this.getBaseContext()).getMetamagicList();

        metaListAdapter = new GenericAdapter(this, metamagicSelected, metamagicList, R.layout.meta_spell_list, MetamagicViewHolder.class, Metamagic.class);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_spell);
        setTitle(R.string.meta_spell_name);

        addButton = findViewById(R.id.buttonCreate);
        deleteButton = findViewById(R.id.buttonDelete);
        editButton = findViewById(R.id.buttonEdit);

        metaMagicRecycler = findViewById(R.id.recyclerViewMetamagic);

        findViewById(R.id.buttonDelete).setEnabled(false);
        findViewById(R.id.buttonEdit).setEnabled(false);


        metaMagicRecycler.setLayoutManager(new LinearLayoutManager(this));
        metaMagicRecycler.setAdapter(metaListAdapter);


        //let's take care of the buttons !
       deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //to avoid to delete an item after the indice of on already deleted item
                Collections.sort(metamagicSelected);
                Collections.reverse(metamagicSelected);

                for(Integer i : metamagicSelected){
                 metamagicList =  metaListAdapter.deleteItem(i);
                }

                buttonClicked();

            }
        });

       editButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {

               //getting the selected metamagic
               Metamagic metamagic = metamagicList.get(metamagicSelected.get(0));

               //passing the metamagic to the editDialog
               Bundle bundle = new Bundle();
               bundle.putString("name", metamagic.getName());
               bundle.putString("level", metamagic.getLevel() + "");
               bundle.putString("description", metamagic.getDescription());

               editDialog.setArguments(bundle);
               editDialog.show(getSupportFragmentManager(), "editMetamagic");

           }
       });

       addButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               createDialog.show(getSupportFragmentManager(), "createMetamagic");
               //not really need, but it's easier to update the viewer
               buttonClicked();


           }

       });

    }

    /**
     * method called when a button is clicked to clear the list and reset the button states
     */
    public void buttonClicked(){

        for(int i = 0; i < 0 ; metaMagicRecycler.getChildCount()){
            metaMagicRecycler.getChildAt(i).setSelected(false);
        }


        metamagicSelected.clear();
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

                if (Math.abs(deltaX) > MIN_DISTANCE && x2 > x1)
                {
                    metaListAdapter.saveList();
                    startActivity(new Intent(this, SpellBookActivity.class));

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
    public void onDialogPositiveClick(DialogFragment dialog, Metamagic metamagic) {

        if(metamagic != null){
            if(dialog == createDialog){
                metamagicList =  metaListAdapter.addItem(metamagic);
            }
            if(dialog == editDialog){
                metamagicList = metaListAdapter.editItem(metamagicSelected.get(0), metamagic);
                buttonClicked();
            }

        }


    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

}

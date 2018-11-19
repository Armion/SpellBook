package com.example.armion.spellbook.hud.metamagic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.armion.spellbook.R;
import com.example.armion.spellbook.hud.SpellBookActivity;
import com.example.armion.spellbook.spell.Metamagic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MetaSpellActivity extends AppCompatActivity {


    // a simple list for test waiting for the loading system for real metamagic spells
    private List<Metamagic> metamagicList = new ArrayList<>();

    //list of the item selected to keep them in mind
    private List<Integer> metamagicSelected = new ArrayList<>();


    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private MetaListAdapter metaListAdapter = new MetaListAdapter(this, metamagicSelected, metamagicList);
    private Button addButton;
    private Button deleteButton;
    private Button editButton;
    private RecyclerView metaMagicRecycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        metamagicList.add(new Metamagic("empowered", 2, "increase the spell lvl by 2"));
        metamagicList.add(new Metamagic("piercing", 1, "increase the DD by 2"));
        metamagicList.add(new Metamagic("versatile", 1, "change one descriptor of the spell"));
        metamagicList.add(new Metamagic("bended", 1, "bend the sharp of the spell"));



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
                    metaListAdapter.deleteItem(i);
                }

                buttonClicked();

            }
        });

       editButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               metaListAdapter.editItem(metamagicSelected.get(0));

               buttonClicked();

           }
       });

       addButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               metaListAdapter.addItem();

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

    public void saveMetamagic(){


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




}

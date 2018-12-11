package com.example.armion.spellbook.hud.spell;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.armion.spellbook.FileStream;
import com.example.armion.spellbook.R;
import com.example.armion.spellbook.hud.preparedSpell.PreparedSpellsActivity;
import com.example.armion.spellbook.hud.metamagic.MetaSpellActivity;
import com.example.armion.spellbook.spell.Spell;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellBookActivity extends AppCompatActivity implements CreateSpellDialog.NoticeDialogListener{


    // a simple list for descriptorMultiSpinnerList waiting for the loading system for real metamagic spells
    private List<Spell> spellList = new ArrayList<>();

    //list of the item selected to keep them in mind
    private List<Integer> spellSelected = new ArrayList<>();

    private CreateSpellDialog editDialog = new CreateSpellDialog();
    private CreateSpellDialog createDialog = new CreateSpellDialog();


    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private SpellListAdapter spellListAdapter;
    private Button addButton;
    private Button deleteButton;
    private Button editButton;
    private RecyclerView spellRecycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        spellList = FileStream.getCharacter("Elyndil", this.getBaseContext()).getSpellList();

        spellListAdapter = new SpellListAdapter(this, spellSelected, spellList);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_spell);
        setTitle(R.string.spell_book_name);

        addButton = findViewById(R.id.buttonCreate);
        deleteButton = findViewById(R.id.buttonDelete);
        editButton = findViewById(R.id.buttonEdit);

        spellRecycler = findViewById(R.id.recyclerViewMetamagic);

        findViewById(R.id.buttonDelete).setEnabled(false);
        findViewById(R.id.buttonEdit).setEnabled(false);


        spellRecycler.setLayoutManager(new LinearLayoutManager(this));
        spellRecycler.setAdapter(spellListAdapter);


        //let's take care of the buttons !
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //to avoid to delete an item after the indice of on already deleted item
                Collections.sort(spellSelected);
                Collections.reverse(spellSelected);

                for(Integer i : spellSelected){
                    spellList =  spellListAdapter.deleteItem(i);
                }

                buttonClicked();

            }
        });

        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //getting the selected spell
                Spell spell = spellList.get(spellSelected.get(0));

                //passing the spell to the editDialog
                Bundle bundle = new Bundle();
                bundle.putString("name", spell.getName());
                bundle.putString("level", spell.getLevel() + "");
                bundle.putString("description", spell.getDescription());
                bundle.putString("area", spell.getArea());
                bundle.putString("castingTime", spell.getCastingTime());
                bundle.putString("duration", spell.getDuration());
                bundle.putString("range", spell.getRange());
                bundle.putString("dice", spell.getDice().name());
                bundle.putString("", spell.getSchool().name());

                editDialog.setArguments(bundle);
                editDialog.show(getSupportFragmentManager(), "editSpells");

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog.show(getSupportFragmentManager(), "createSpells");
                //not really need, but it's easier to update the viewer
                buttonClicked();


            }

        });

    }

    /**
     * method called when a button is clicked to clear the list and reset the button states
     */
    public void buttonClicked(){

        for(int i = 0; i < 0 ; spellRecycler.getChildCount()){
            spellRecycler.getChildAt(i).setSelected(false);
        }


        spellSelected.clear();
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
                        Toast.makeText(this, "Left to Right swipe", Toast.LENGTH_SHORT).show();
                        spellListAdapter.saveSpell();
                        startActivity(new Intent(this, PreparedSpellsActivity.class));
                    }

                    // Right to left swipe action
                    else
                    {
                        Toast.makeText(this, "Right to Left swipe", Toast.LENGTH_SHORT).show ();
                        spellListAdapter.saveSpell();
                        startActivity(new Intent(this, MetaSpellActivity.class));

                    }

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
    public void onDialogPositiveClick(DialogFragment dialog, Spell spell) {

        if(spell != null){
            if(dialog == createDialog){
                spellList =  spellListAdapter.addItem(spell);
            }
            if(dialog == editDialog){
                spellList = spellListAdapter.editItem(spellSelected.get(0), spell);
                buttonClicked();
            }

        }


    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}

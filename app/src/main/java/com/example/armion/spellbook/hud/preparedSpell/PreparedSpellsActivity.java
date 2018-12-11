package com.example.armion.spellbook.hud.preparedSpell;

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
import com.example.armion.spellbook.SpellSlot;
import com.example.armion.spellbook.hud.Summary.SummaryActivity;
import com.example.armion.spellbook.hud.spell.SpellBookActivity;
import com.example.armion.spellbook.spell.Metamagic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreparedSpellsActivity extends AppCompatActivity implements CreateSlotDialog.NoticeDialogListener {

    // a simple list for test waiting for the loading system for real metamagic spells
    private List<SpellSlot> spellSlotList = new ArrayList<>();

    //list of the item selected to keep them in mind
    private List<Integer> spellSelected = new ArrayList<>();

    private CreateSlotDialog editDialog = new CreateSlotDialog();
    private CreateSlotDialog createDialog = new CreateSlotDialog();


    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private SlotListAdapter spellListAdapter;
    private Button addButton;
    private Button deleteButton;
    private Button editButton;
    private RecyclerView spellRecycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

       spellSlotList = FileStream.getCharacter("Elyndil", this.getBaseContext()).getSpellSlotList();

        spellListAdapter = new SlotListAdapter(this, spellSelected, spellSlotList);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_spell);
        setTitle(R.string.prepared_spell_name);

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
                    spellSlotList =  spellListAdapter.deleteItem(i);
                }

                buttonClicked();

            }
        });

        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //getting the selected spellSlot
                SpellSlot spellSlot = spellSlotList.get(spellSelected.get(0));
                ArrayList<String> metaMagicsID = new ArrayList<>();

                for(Metamagic m : spellSlot.getPreparedSpell().getMetamagicList()){
                    metaMagicsID.add(m.getId().toString());
                }

                //passing the spellSlot to the editDialog
                Bundle bundle = new Bundle();
                bundle.putString("level", spellSlot.getLevel() + "");
                bundle.putString("spellSlotId", spellSlot.getPreparedSpell().getSpell().getId().toString());
                bundle.putStringArrayList("metaMagicsId", metaMagicsID);

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
                        spellListAdapter.saveSpellSlot();
                        startActivity(new Intent(this, SummaryActivity.class));
                    }

                    // Right to left swipe action
                    else
                    {
                        Toast.makeText(this, "Right to Left swipe", Toast.LENGTH_SHORT).show ();
                        spellListAdapter.saveSpellSlot();
                        startActivity(new Intent(this, SpellBookActivity.class));

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
    public void onDialogPositiveClick(DialogFragment dialog, SpellSlot slot) {

        if(slot != null){
            if(dialog == createDialog){

                spellSlotList = spellListAdapter.addItem(slot);

            }
            if(dialog == editDialog){

                spellSlotList = spellListAdapter.editItem(spellSelected.get(0), slot);
                buttonClicked();
            }

        }


    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}

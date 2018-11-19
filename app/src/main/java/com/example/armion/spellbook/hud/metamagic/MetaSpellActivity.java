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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetaSpellActivity extends AppCompatActivity {


    // a simple list for test waiting for the loading system for real metamagic spells
    private List<Metamagic> metamagicList = Arrays.asList(

            new Metamagic("empowered", 2, "increase the spell lvl by 2"),
            new Metamagic("piercing", 1, "increase the DD by 2"),
            new Metamagic("versatile", 1, "change one descriptor of the spell"),
            new Metamagic("bended", 1, "bend the sharp of the spell"),
            new Metamagic("quicken cast", 2, "cast the spell as a simple action"),
            new Metamagic("no more ideas", 2, "do nothing"),
            new Metamagic("no more ideas", 2, "do nothing"),
            new Metamagic("no more ideas", 2, "do nothing"),
            new Metamagic("no more ideas", 2, "do nothing"),
            new Metamagic("no more ideas", 2, "do nothing"),
            new Metamagic("no more ideas", 2, "do nothing"),
            new Metamagic("no more ideas", 2, "do nothing"),
            new Metamagic("useless", 0, "useless")
    );

    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private MetaListAdapter metaListAdapter = new MetaListAdapter(this, new ArrayList<Metamagic>(), metamagicList);




    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_spell);
        setTitle(R.string.meta_spell_name);

        final Button deleteButton = findViewById(R.id.buttonDelete);

        final RecyclerView metaMagicList = findViewById(R.id.recyclerViewMetamagic);

        findViewById(R.id.buttonDelete).setEnabled(false);
        findViewById(R.id.buttonEdit).setEnabled(false);


        metaMagicList.setLayoutManager(new LinearLayoutManager(this));
        metaMagicList.setAdapter(metaListAdapter);


       deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               metaListAdapter.notifyItemRemoved(0);
               metaListAdapter.notifyItemRangeChanged(0, metamagicList.size());
               //metamagicList.remove(1);

            }
        });



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

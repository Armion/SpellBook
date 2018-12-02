/**
 * @author Armion
 * @version 0.01
 */

package com.example.armion.spellbook.hud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.armion.spellbook.R;
import com.example.armion.spellbook.hud.preparedSpell.PreparedSpellsActivity;
import com.example.armion.spellbook.spell.School;


import java.util.Arrays;

import java.util.List;


public class SummaryActivity extends AppCompatActivity {



    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    List<School> list = Arrays.asList(School.values());




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
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
                        startActivity(new Intent(this, StatisticsActivity.class));
                    }

                    // Right to left swipe action
                    else
                    {
                        Toast.makeText(this, "Right to Left swipe", Toast.LENGTH_SHORT).show ();
                        startActivity(new Intent(this, PreparedSpellsActivity.class));

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
}

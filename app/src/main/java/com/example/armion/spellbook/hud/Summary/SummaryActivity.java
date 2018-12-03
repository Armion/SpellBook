/**
 * @author Armion
 * @version 0.01
 */

package com.example.armion.spellbook.hud.Summary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.armion.spellbook.FileStream;
import com.example.armion.spellbook.R;
import com.example.armion.spellbook.hud.StatisticsActivity;
import com.example.armion.spellbook.hud.preparedSpell.PreparedSpellsActivity;



public class SummaryActivity extends AppCompatActivity implements View.OnClickListener {



    private float x1,x2;
    static final int MIN_DISTANCE = 150;


    private Button reduceButton;
    private Button increaseButton;
    private TextView pvText;
    private TextView amount;

    private int pv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        this.setTitle("Summary");

        reduceButton = findViewById(R.id.reduceLife);
        reduceButton.setOnClickListener(this);

        increaseButton = findViewById(R.id.increaseLife);
        increaseButton.setOnClickListener(this);

        pv = FileStream.getCharacter("Elyndil", getBaseContext()).getHp();

        pvText = findViewById(R.id.pvInput);
        pvText.setText(pv + "");

        amount = findViewById(R.id.amountInput);


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
                        FileStream.saveLife(pv, getBaseContext(), "Elyndil");
                        startActivity(new Intent(this, StatisticsActivity.class));
                    }

                    // Right to left swipe action
                    else
                    {
                        Toast.makeText(this, "Right to Left swipe", Toast.LENGTH_SHORT).show ();
                        FileStream.saveLife(pv, getBaseContext(), "Elyndil");
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

    @Override
    public void onClick(View v) {

        int life = Integer.valueOf(amount.getText().toString());

        if(v == reduceButton){
            pv -= life;
        }
        else{
            pv += life;
        }

        pvText.setText(pv + "");

    }
}

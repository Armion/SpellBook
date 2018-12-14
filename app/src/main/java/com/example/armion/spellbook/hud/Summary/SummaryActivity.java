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

import com.example.armion.spellbook.Character;
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

    private TextView platinum;
    private TextView gold;
    private  TextView silver;
    private  TextView copper;

    private Character character;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        this.setTitle("Summary");

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
                        startActivity(new Intent(this, StatisticsActivity.class));
                    }

                    // Right to left swipe action
                    else
                    {
                        FileStream.saveLife(character.getHp(), getBaseContext(), "Elyndil");
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
            character.setHp(character.getHp() - life);
        }
        else{
            character.setHp(character.getHp() + life);
        }

        pvText.setText(character.getHp() + "/" + character.getMaxHP());

    }
}

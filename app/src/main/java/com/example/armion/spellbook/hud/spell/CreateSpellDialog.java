package com.example.armion.spellbook.hud.spell;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;



import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;



import com.androidbuts.multispinnerfilter.MultiSpinner;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.example.armion.spellbook.entity.Dice;
import com.example.armion.spellbook.R;
import com.example.armion.spellbook.entity.spell.Descriptor;
import com.example.armion.spellbook.entity.spell.School;
import com.example.armion.spellbook.entity.spell.Spell;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;


public class CreateSpellDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {


    private ArrayAdapter<School> schoolArrayAdapter;
    private ArrayAdapter<Dice> diceArrayAdapter;
    LinkedHashMap<String, Boolean> descriptorMultiSpinnerList = new LinkedHashMap<>();

    private Spinner schoolSpinner;
    private MultiSpinner descriptorSpinner;
    private Spinner diceSpinner;

    private School schoolselected = School.abjuration;
    private List<Descriptor> descriptorsSelected = new ArrayList<>();
    private  Dice diceSelected = Dice.d4;
    private List<Descriptor> descriptorList;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



        if(parent.getId() == R.id.schoolSpinner)
        {
             schoolselected = ((School) parent.getItemAtPosition(position));

        }
        else if(parent.getId() == R.id.diceSpinner){

            diceSelected = ((Dice) parent.getItemAtPosition(position));

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //implementation of the listener interface
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, Spell spell);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    CreateSpellDialog.NoticeDialogListener mListener;



    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);





        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(this.toString()
                    + " must implement NoticeDialogListener");
        }
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater li = getActivity().getLayoutInflater();
        final View view = li.inflate(R.layout.create_spell, null);



        schoolArrayAdapter = new ArrayAdapter<>(
                getActivity().getBaseContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList(School.values())
        );

        diceArrayAdapter = new ArrayAdapter<>(
                getActivity().getBaseContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList(Dice.values())
        );



        schoolArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        schoolSpinner = view.findViewById(R.id.schoolSpinner);
        descriptorSpinner =  view.findViewById(R.id.descriptorsSpinner);
        diceSpinner = view.findViewById(R.id.diceSpinner);

        schoolSpinner.setAdapter(schoolArrayAdapter);
        schoolSpinner.setOnItemSelectedListener(this);


        descriptorList = Arrays.asList(Descriptor.values());



         for(Descriptor d : descriptorList){
             descriptorMultiSpinnerList.put(d.toString(), false);
         }


        descriptorSpinner.setItems(descriptorMultiSpinnerList, new MultiSpinnerListener() {

            @Override
            public void onItemsSelected(boolean[] selected) {

                descriptorsSelected.clear();

                // your operation with code...
                for(int i=0; i<selected.length; i++) {
                    if(selected[i]) {
                        descriptorsSelected.add(descriptorList.get(i));
                    }
                }
            }
        });

        diceSpinner.setAdapter(diceArrayAdapter);
        diceSpinner.setOnItemSelectedListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //if this is called with args for the metamagic
        try{
            ((EditText)view.findViewById(R.id.inputName)).setText(getArguments().getString("name"));
            ((EditText)view.findViewById(R.id.inputLevel)).setText(getArguments().getString("level"));
            ((EditText)view.findViewById(R.id.inputDescription)).setText(getArguments().getString("description"));
            ((EditText)view.findViewById(R.id.inputArea)).setText(getArguments().getString("area"));
            ((EditText)view.findViewById(R.id.inputCasting)).setText(getArguments().getString("castingTime"));
            ((EditText)view.findViewById(R.id.inputDuration)).setText(getArguments().getString("duration"));
            ((EditText)view.findViewById(R.id.inputRange)).setText(getArguments().getString("range"));

        }
        catch (NullPointerException e){

        }



        builder.setMessage(R.string.create_spell).setView(view);

        builder.setPositiveButton("ok !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int level = 1;




                try{

                    level = Integer.parseInt((( EditText)view.findViewById(R.id.inputLevel)).getText().toString());
                }
                catch (NumberFormatException e){
                }
                finally {
                    mListener.onDialogPositiveClick(
                            CreateSpellDialog.this,
                            new Spell(schoolselected,
                                    ((EditText)view.findViewById(R.id.inputRange)).getText().toString(),
                                    descriptorsSelected,
                                    diceSelected,
                                    ((EditText)view.findViewById(R.id.inputCasting)).getText().toString(),
                                    ((EditText)view.findViewById(R.id.inputArea)).getText().toString(),
                                    ((EditText)view.findViewById(R.id.inputDuration)).getText().toString(),
                                    ((EditText)view.findViewById(R.id.inputDescription)).getText().toString(),
                                    ((EditText)view.findViewById(R.id.inputName)).getText().toString(),
                                    level,
                                    ((EditText)view.findViewById(R.id.linkInput)).getText().toString()

                            )
                    );
                }



            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogNegativeClick(CreateSpellDialog.this);

            }
        });


        return builder.create();

    }
}

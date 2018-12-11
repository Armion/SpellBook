package com.example.armion.spellbook.hud.preparedSpell;

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
import com.example.armion.spellbook.Character;
import com.example.armion.spellbook.FileStream;
import com.example.armion.spellbook.R;
import com.example.armion.spellbook.SpellSlot;
import com.example.armion.spellbook.spell.Metamagic;
import com.example.armion.spellbook.spell.PreparedSpell;
import com.example.armion.spellbook.spell.Spell;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;


public class CreateSlotDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private ArrayAdapter<Spell> spellArrayAdapter;
    LinkedHashMap<String, Boolean> metaMultiSpinnerList = new LinkedHashMap<>();

    private List<Spell> spellList;
    private List<Metamagic> metamagicList;
    private List<Metamagic> metamagicsSelected = new ArrayList<>();


    NoticeDialogListener mListener;

    private Spinner spellSpinner;
    private MultiSpinner metaSpinner;

    private Spell spellSelected = new Spell();

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getId() == R.id.spellSpinner){
            spellSelected =  (Spell)parent.getItemAtPosition(position);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //implementation of the listener interface
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, SpellSlot slot);
        public void onDialogNegativeClick(DialogFragment dialog);
    }






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

        Character character = FileStream.getCharacter("Elyndil", getContext());

        spellList = character.getSpellList();
        metamagicList = character.getMetamagicList();
        int i = 0;



        LayoutInflater li = getActivity().getLayoutInflater();
        final View view = li.inflate(R.layout.create_slot, null);


        spellArrayAdapter = new ArrayAdapter<>(
                getActivity().getBaseContext(),
                android.R.layout.simple_spinner_item,
                spellList);

        for(Metamagic m : metamagicList){
            metaMultiSpinnerList.put(m.getName(), false);
        }

        metaSpinner = view.findViewById(R.id.metaSpinner);

        metaSpinner.setItems(metaMultiSpinnerList, new MultiSpinnerListener() {

            @Override
            public void onItemsSelected(boolean[] selected) {

                metamagicsSelected.clear();


                for(int i=0; i<selected.length; i++) {
                    if(selected[i]) {
                        metamagicsSelected.add(metamagicList.get(i));
                    }
                }
            }
        });

        spellArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spellSpinner = view.findViewById(R.id.spellSpinner);

        spellSpinner.setAdapter(spellArrayAdapter);

        spellSpinner.setOnItemSelectedListener(this);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //if this is called with args for the metamagic
        try{


            ((EditText)view.findViewById(R.id.inputLevel)).setText(getArguments().getString("level"));

            while(i < spellList.size()  && ! spellList.get(i).getId().toString().equals(getArguments().getString("spellSlotId"))){
                i ++;
            }
            //if the item have not been found then i = spellList.size()
            if( i < spellList.size() ){
                spellSpinner.setSelection(i);
                spellSelected = spellList.get(i);
            }



                for(i = 0; i < metaMultiSpinnerList.size(); i++){

                    for(String id : getArguments().getStringArrayList("metaMagicsId")){
                        if(metamagicList.get(i).getId().equals(UUID.fromString(id))){
                            metamagicsSelected.add(metamagicList.get(i));
                        }
                    }

                }




        }
        catch (NullPointerException e){

        }



        builder.setMessage(R.string.create_metamagic).setView(view);

        builder.setPositiveButton("ok !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int level = 1;
                PreparedSpell preparedSpell = new PreparedSpell(spellSelected, metamagicsSelected);

                try{

                    level = Integer.parseInt((( EditText)view.findViewById(R.id.inputLevel)).getText().toString());
                }
                catch (NumberFormatException e){
                }
                finally {
                    mListener.onDialogPositiveClick(CreateSlotDialog.this,
                            new SpellSlot(preparedSpell, level)
                    );
                }



            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogNegativeClick(CreateSlotDialog.this);

            }
        });


        return builder.create();

    }
}

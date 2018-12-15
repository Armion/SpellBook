package com.example.armion.spellbook.hud.Summary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.armion.spellbook.Item;
import com.example.armion.spellbook.Money;
import com.example.armion.spellbook.R;


public class CreateItemDialog extends DialogFragment {



    //implementation of the listener interface
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, Item item);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    CreateItemDialog.NoticeDialogListener mListener;



    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (CreateItemDialog.NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(this.toString()
                    + " must implement NoticeDialogListener");
        }
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater li = getActivity().getLayoutInflater();
        final View view = li.inflate(R.layout.create_item, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //if this is called with args for the metamagic
        try{
            ((EditText)view.findViewById(R.id.inputName)).setText(getArguments().getString("name"));
            ((EditText)view.findViewById(R.id.inputDescription)).setText(getArguments().getString("description"));
        }
        catch (NullPointerException e){

        }



        builder.setMessage(R.string.create_item).setView(view);

        builder.setPositiveButton("ok !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                Money value = new Money();
                int durability = 0;
                float weight = 0;

                try{

                    value.setPlatinumPieces(Integer.parseInt( (( EditText)view.findViewById(R.id.platinumInput)).getText().toString()) );
                    value.setGoldPieces(Integer.parseInt( (( EditText)view.findViewById(R.id.goldInput)).getText().toString()) );
                    value.setSilverPieces(Integer.parseInt( (( EditText)view.findViewById(R.id.silverInput)).getText().toString()) );
                    value.setCopperPieces(Integer.parseInt( (( EditText)view.findViewById(R.id.copperInput)).getText().toString()) );

                    durability = Integer.parseInt( (( EditText)view.findViewById(R.id.durabilityInput)).getText().toString());
                    weight = Float.parseFloat((( EditText)view.findViewById(R.id.weightInput)).getText().toString());
                }
                catch (NumberFormatException e){
                }
                finally {
                    mListener.onDialogPositiveClick(CreateItemDialog.this,
                            new Item(
                                    (( EditText)view.findViewById(R.id.inputName)).getText().toString(),
                                    value,
                                    (( EditText)view.findViewById(R.id.inputDescription)).getText().toString(),
                                    durability,
                                    0

                            )
                    );
                }



            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogNegativeClick(CreateItemDialog.this);

            }
        });


        return builder.create();

    }
}


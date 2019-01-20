package com.example.armion.spellbook.hud.metamagic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


import com.example.armion.spellbook.R;
import com.example.armion.spellbook.entity.spell.Metamagic;



public class CreateMetamagicDialog extends DialogFragment {



    //implementation of the listener interface
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, Metamagic metamagic);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener mListener;



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
       final View view = li.inflate(R.layout.create_metamagic, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //if this is called with args for the metamagic
        try{
            ((EditText)view.findViewById(R.id.inputName)).setText(getArguments().getString("name"));
            ((EditText)view.findViewById(R.id.inputLevel)).setText(getArguments().getString("level"));
            ((EditText)view.findViewById(R.id.inputDescription)).setText(getArguments().getString("description"));
        }
        catch (NullPointerException e){

        }



        builder.setMessage(R.string.create_metamagic).setView(view);

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
                    mListener.onDialogPositiveClick(CreateMetamagicDialog.this,
                            new Metamagic(
                                    (( EditText)view.findViewById(R.id.inputName)).getText().toString(),
                                    level,
                                    (( EditText)view.findViewById(R.id.inputDescription)).getText().toString()
                            )
                    );
                }



            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogNegativeClick(CreateMetamagicDialog.this);

            }
        });


        return builder.create();

    }
}

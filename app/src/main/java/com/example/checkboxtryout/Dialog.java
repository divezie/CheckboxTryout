package com.example.checkboxtryout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.checkboxtryout.DB.Checkbox_Item;
import com.example.checkboxtryout.DB.Checkbox_ItemDao;
import com.example.checkboxtryout.DB.DbManager;

public class Dialog extends AppCompatDialogFragment {

    EditText editText;
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater  = getActivity().getLayoutInflater();
        View view  = inflater.inflate(R.layout.dialog_fragment,null);
        editText = view.findViewById(R.id.editText);
        DbManager.setConfig(view.getContext());

        builder.setView(view)
                .setTitle("Add task")
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                String getContent = editText.getText().toString();
                                Checkbox_Item spin = new Checkbox_Item();

                                spin.setID(Checkbox_ItemDao.maxId());
                                spin.setAction(getContent);
                                Checkbox_ItemDao.insertRecord(spin);
                                MainActivity activity = (MainActivity) getActivity();
                                activity.refreshMe();

                    }
                })
                .setNegativeButton("nevermind", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
                    }
                });

    return builder.create();

    }



}

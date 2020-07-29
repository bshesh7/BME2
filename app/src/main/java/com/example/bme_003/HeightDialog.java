package com.example.bme_003;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class HeightDialog extends AppCompatDialogFragment {
    private EditText editft;
    private EditText editin;
    private HeightDialogListner listner;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_height,null);
        builder.setView(view)
                .setTitle("Height")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String feet = editft.getText().toString();
                        String password = editin.getText().toString();
                        listner.applyheight(feet, password);
                    }
                });

        editin = view.findViewById(R.id.edit_heightft);
        editft = view.findViewById(R.id.edit_heightin);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listner = (HeightDialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListner");
        }

    }

    public interface HeightDialogListner{
        void applyheight(String feet, String inch);
    }
}

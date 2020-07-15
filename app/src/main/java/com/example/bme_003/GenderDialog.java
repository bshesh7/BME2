package com.example.bme_003;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class GenderDialog extends AppCompatDialogFragment {

    private EditText editTextGender;
    private GenderDialogListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_genderdialog, null);
        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String gender = editTextGender.getText().toString();
                        listener.applyText(gender);
                    }
                });
        editTextGender = view.findViewById(R.id.edit_gender);


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (GenderDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+ "must implement Ecmape ia s sda");
        }
    }

    public interface GenderDialogListener{
        void applyText(String gender);

    }

}

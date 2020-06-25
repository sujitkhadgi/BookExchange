package com.example.bookexchange1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.bookexchange1.R;

public class AddBookDialog extends AppCompatDialogFragment {
    private EditText etBookName, etAuthorName;
    private Button btnAddBooks;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate((R.layout.layout_add_book),null);

        builder.setView(view)
                .setTitle("Add Book")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        etAuthorName=view.findViewById(R.id.etAuthorName);
        etBookName=view.findViewById(R.id.etBookName);
        btnAddBooks=view.findViewById(R.id.btnAddBooks);
        return  builder.create();
    }
}

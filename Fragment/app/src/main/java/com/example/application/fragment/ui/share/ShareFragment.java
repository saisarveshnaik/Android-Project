package com.example.application.fragment.ui.share;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.application.fragment.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    EditText txtname,txtmiddlename,txtsurname,txtphone,txtemail,txtaddress,txtnotes;
    Button btnsave;
    DatabaseReference reff;
    Contacts contacts;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        //final TextView textView = root.findViewById(R.id.text_share);
        // shareViewModel.getText().observe(this, new Observer<String>() {
        //@Override
        // public void onChanged(@Nullable String s) {
        //  textView.setText(s);
        // }
        // });

        txtname=root.findViewById(R.id.txtname);
        txtmiddlename=root.findViewById(R.id.txtmiddlename);
        txtsurname=root.findViewById(R.id.txtmiddlename);
        txtphone=root.findViewById(R.id.txtphone);
        txtemail=root.findViewById(R.id.txtemail);
        txtaddress=root.findViewById(R.id.txtaddress);
        txtnotes=root.findViewById(R.id.txtnotes);
        btnsave=root.findViewById(R.id.btnsave);
        contacts=new Contacts();
        reff= FirebaseDatabase.getInstance().getReference().child("Contacts");


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Long phn=Long.parseLong(txtphone.getText().toString().trim());

                contacts.setName(txtname.getText().toString().trim());
                contacts.setMiddlename(txtmiddlename.getText().toString().trim());
                contacts.setSurname(txtsurname.getText().toString().trim());
                contacts.setPh(phn);
                contacts.setEmail(txtemail.getText().toString().trim());
                contacts.setAddress(txtaddress.getText().toString().trim());
                contacts.setNotes(txtnotes.getText().toString().trim());
                reff.push().setValue(contacts);
                Toast.makeText(getActivity(),"Contact Inserted Successfully",Toast.LENGTH_LONG).show();

            }
        });
        return root;


    }
}
package com.example.application.fragment.ui.home;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.application.fragment.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private Button loadcontacts;
    private TextView listContacts;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        listContacts =root.findViewById(R.id.listContacts);
        loadcontacts =root.findViewById(R.id.loadcontacts);

        loadcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadcontact();
            }
        });

       // final TextView textView = root.findViewById(R.id.text_home);
       // homeViewModel.getText().observe(this, new Observer<String>() {
         //   @Override
           // public void onChanged(@Nullable String s) {
               // textView.setText(s);
          //  }
       // });



        return root;
    }

    public void loadcontact()


    {




        StringBuilder builder = new StringBuilder();
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

        if (cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0)
                {
                    Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                            new String[]{id}, null);

                    while (cursor2.moveToNext())
                    {

                        String phoneNumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        builder.append(name).append(", Phone Number : ").append(phoneNumber).append("\n\n");
                    }
                    cursor2.close();

                }
            }
        }
        cursor.close();
        listContacts.setText(builder.toString());










    }
}
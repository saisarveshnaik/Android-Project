package com.example.application.fragment.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.application.fragment.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private EditText displayNameEditor;

    private EditText phoneNumberEditor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
       // final TextView textView = root.findViewById(R.id.text_gallery);
      //  galleryViewModel.getText().observe(this, new Observer<String>() {
           // @Override
          //  public void onChanged(@Nullable String s) {
               // textView.setText(s);
         //   }
       // });

        displayNameEditor = root.findViewById(R.id.add_phone_contact_display_name);

        phoneNumberEditor = root.findViewById(R.id.add_phone_contact_number);






        return root;
    }
}
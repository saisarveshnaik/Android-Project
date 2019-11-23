package com.example.application.fragment.ui.tools;

import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class ToolsFragment extends Fragment {


    private ToolsViewModel toolsViewModel;
    private EditText etname, etphon,etmail;
    private Button btn;
    private static final String VCF_DIRECTORY = "/vcf_demonut";
    private File vcfFile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
       // final TextView textView = root.findViewById(R.id.text_tools);
      //  toolsViewModel.getText().observe(this, new Observer<String>() {
           // @Override
          //  public void onChanged(@Nullable String s) {
              //  textView.setText(s);
         //   }
      //  });

        etname = root.findViewById(R.id.etname);
        etphon = root.findViewById(R.id.etphon);
        etmail = root.findViewById(R.id.etmail);
        btn = root.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    // File vcfFile = new File(this.getExternalFilesDir(null), "generated.vcf");
                    File vdfdirectory = new File(
                            Environment.getExternalStorageDirectory() + VCF_DIRECTORY);
                    // have the object build the directory structure, if needed.
                    if (!vdfdirectory.exists()) {
                        vdfdirectory.mkdirs();
                    }

                    vcfFile = new File(vdfdirectory, "android_" + Calendar.getInstance().getTimeInMillis() + ".vcf");

                    FileWriter fw = null;
                    fw = new FileWriter(vcfFile);
                    fw.write("BEGIN:VCARD\r\n");
                    fw.write("VERSION:3.0\r\n");
                    // fw.write("N:" + p.getSurname() + ";" + p.getFirstName() + "\r\n");
                    fw.write("FN:" + etname.getText().toString() + "\r\n");
                    //  fw.write("ORG:" + p.getCompanyName() + "\r\n");
                    //  fw.write("TITLE:" + p.getTitle() + "\r\n");
                    fw.write("TEL;TYPE=WORK,VOICE:" + etphon.getText().toString() + "\r\n");
                    //   fw.write("TEL;TYPE=HOME,VOICE:" + p.getHomePhone() + "\r\n");
                    //   fw.write("ADR;TYPE=WORK:;;" + p.getStreet() + ";" + p.getCity() + ";" + p.getState() + ";" + p.getPostcode() + ";" + p.getCountry() + "\r\n");
                    fw.write("EMAIL;TYPE=PREF,INTERNET:" + etmail.getText().toString() + "\r\n");
                    fw.write("END:VCARD\r\n");
                    fw.close();

                   /*Intent i = new Intent(); //this will import vcf in contact list
                    i.setAction(android.content.Intent.ACTION_VIEW);
                    i.setDataAndType(Uri.fromFile(vcfFile), "text/x-vcard");
                    startActivity(i);  */


                    Toast.makeText(getActivity(), "Created!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();


                }
            }






        });

        return root;
    }
}
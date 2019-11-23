package com.example.application.fragment.ui.slideshow;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.application.fragment.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
     //   final TextView textView = root.findViewById(R.id.text_slideshow);
       // slideshowViewModel.getText().observe(this, new Observer<String>() {
          //  @Override
           // public void onChanged(@Nullable String s) {



        TextView textView =root.findViewById(R.id.textView);
        textView.setText(getCallDetails());
              //  textView.setText(s);
         //   }
    //    });
        return root;
    }

    private String getCallDetails(){
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null,null,null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Details:\n\n");
        while (managedCursor.moveToNext()){
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
            String dateString = formatter.format(callDayTime);
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode){
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }

            sb.append("\nPhone Number: " + phNumber + " \nCallType: " + dir + " \nCall Date: " +dateString +
                    " \nCall Duration in sec: " + callDuration);

            sb.append("\n--------------------------------------------------------------------------------------------------");


        }
        managedCursor.close();
        return sb.toString();

    }
}
package com.example.android;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatePicker#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    public DatePicker() {
//        // Required empty public constructor
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatePicker.
     */
    // TODO: Rename and change types and number of parameters
    public static DatePicker newInstance(String param1, String param2) {
        DatePicker fragment = new DatePicker();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @NonNull //Indicates return value cannot be null
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);

        //return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
        //Invoke processDatePickerResult() in OrderActivity and pass it the year, month, and day
        OrderActivity activity = (OrderActivity) getActivity(); //We use getActivity(), which is used in a fragment, return the Activity the Fragment is currently associated with
        //We need this because we can't call a method in OrderActivity without context of OrderActivity
        //We would have to use intent instead otherwise

        //The Activity inherits the content, so we can use it as the context for calling the method
        activity.processDatePickerResult(year, month, day);
    }
}
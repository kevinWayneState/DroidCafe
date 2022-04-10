package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity shows the order chosen.  The order is sent as data
 * with the intent to launch this activity
 */

/*
The reason why we need the AdapterView is because we need an adapter - specifically an Array Adapter to assign the array to the spinner.
An Adapter connects the data, in this case, the array of spinner items, to the spinner
 */
public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Get the intent and its data
        Intent intent = getIntent();
        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);

        //Create the spinner
        Spinner spinner = findViewById(R.id.label_spinner);
        if(spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        //Create an arrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.labels_array,
                android.R.layout.simple_spinner_item);

        //Specify the layout to use when the list of choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply the adapter to the spinner
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    public void onRadioButtonClicked(View view) {
        //Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        //Check which radio button was clicked
        switch(view.getId()) {
            case R.id.sameday:
                if(checked)
                    //Same day service
                    displayToast(getString(R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if(checked)
                    //Next day delivery
                    displayToast(getString(R.string.next_day_ground_service));
                break;
            case R.id.pickup:
                if(checked)
                    //Pickup
                    displayToast(getString(R.string.pickup));
                break;
            default:
                //Do nothing
                break;
        }

    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    //Interface callback for when any spinner item is selected
    //Both methods use the parameter AdapterView<?>.  The <?> is a java wildcard
    //which enables the method to be flexible enough to accept any type of Adapterview as an argument
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Code to respond to spinner selections
        //When the user selects an item in the spinner, the spinner receives an on-item select event.
        //To handle the event, we already implemented this interface method earlier, so we just need to
        //retrieve the selected item in the Spinner, using getItemAtPosition() and assign the item to the spinnerLabel
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    //Interface callback for when no spinner is selected
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePicker();
        newFragment.show(getSupportFragmentManager(), getString(R.string.date_picker)); //Manage the Fragment automatically and show the picker
    }

    //Pass the date back here and convert the date to a string that we can show through a toast message
    public void processDatePickerResult(int year, int month, int day) {
        //Convert the month, day, and year to separate strings and concatenate the 3 strings with slash marks for the U.S. date format
        String month_string = Integer.toString(month+1); //The month integer returned by the date picker starts counting at 0 for January, so we need to add 1 to it to show months starting at 1
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        Toast.makeText(this, getString(R.string.date) + dateMessage, Toast.LENGTH_SHORT).show();
    }
}
package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateBuisnessDataAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, address, province, primaryBuisness, buisnessNumber;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        buisnessNumber = (EditText) findViewById(R.id.buisnessnNumber);
        province = (EditText) findViewById(R.id.province);
        primaryBuisness = (EditText) findViewById(R.id.primarybuisness);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String entryID = appState.firebaseReference.push().getKey();
        BuisnessData entry = new BuisnessData(buisnessNumber.getText().toString(), nameField.getText().toString(), primaryBuisness.getText().toString(), address.getText().toString(), province.getText().toString(), entryID);
        appState.firebaseReference.child(entryID).setValue(entry);

        finish();

    }
}

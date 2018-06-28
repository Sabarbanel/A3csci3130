package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    public EditText nameField, address, province,primaryBuisness,buisnessNumber;
    BuisnessData entryInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        // when you select a name, it searches it up and populates the fields to that you can modify them
        entryInfo = (BuisnessData) getIntent().getSerializableExtra("BuisnessData");
        appState = ((MyApplicationData) getApplicationContext());



        nameField = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        buisnessNumber = (EditText) findViewById(R.id.buisnessnNumber);
        province = (EditText) findViewById(R.id.province);
        primaryBuisness = (EditText) findViewById(R.id.primarybuisness);
//what I learned is that this part isn't working period

        if(entryInfo != null){
            nameField.setText(entryInfo.name);
            address.setText(entryInfo.address);
            buisnessNumber.setText(entryInfo.businessNumber);
            province.setText(entryInfo.provinceOrTerritory);
            primaryBuisness.setText(entryInfo.primaryBusiness);
        }
    }

    public void updateContact(View v){
        //TODO: Update contact funcionality
        BuisnessData updatedEntry = new BuisnessData( buisnessNumber.getText().toString(),  nameField.getText().toString(),  primaryBuisness.getText().toString(),  address.getText().toString(), province.getText().toString(), entryInfo.entryID);
        appState.firebaseReference.child(entryInfo.entryID).setValue(updatedEntry);


    }

    public void eraseContact(View v) {
        appState.firebaseReference.child(entryInfo.entryID).removeValue();
    }

}

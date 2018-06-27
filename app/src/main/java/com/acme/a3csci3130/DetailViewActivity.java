package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, address, province,primaryBuisness,buisnessNumber;
    BuisnessData entryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        // when you select a name, it searches it up and populates the fields to that you can modify them
        entryInfo = (BuisnessData) getIntent().getSerializableExtra("BuisnessData");

        BuisnessData entry = (BuisnessData) firebaseAdapter.getItem(position);


        nameField = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        buisnessNumber = (EditText) findViewById(R.id.buisnessNumber);
        province = (EditText) findViewById(R.id.province);
        primaryBuisness = (EditText) findViewById(R.id.primarybuisness);

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

    }

    public void eraseContact(View v)
    {
        Firebase firebase=new Firebase("..address..");
        firebase.child(id).removeValue();
    }
}

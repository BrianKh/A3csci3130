package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, businessNoField, primaryBusinessField, addressField,
            provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    /**
     * initializes fields
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        businessNoField = (EditText) findViewById(R.id.businessNumber);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            businessNoField.setText(receivedPersonInfo.businessNumber);
            primaryBusinessField.setText(receivedPersonInfo.primaryType);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    /**
     * updates the strings
     * @param v
     */
    public void updateContact(View v){
        String personID = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String businessNo = businessNoField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Contact person = new Contact(personID, name, email, businessNo, primaryBusiness, address,
                province);
        appState = ((MyApplicationData) getApplicationContext());

        appState.firebaseReference.child(personID).setValue(person);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * erases the uid from the database
     * @param v
     */
    public void eraseContact(View v) {

        appState = ((MyApplicationData) getApplicationContext());
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

package com.svnb2.name;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //TextInputLayout variables
    private TextInputLayout textInputLayoutfName;
    private TextInputLayout textInputLayoutlName;
    private TextInputLayout textInputLayoutPhone;
    private TextInputLayout textInputLayoutEmail;

    //EditText variables
    private EditText editTextfName;
    private EditText editTextlName ;
    private EditText editTextPhone;
    private EditText editTextEmail;

    //Button
    private Button continueBtn;

    private ValidationHelper validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("Registration");
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        initViews();
        initListeners();
    }

    /**
     * method to initialize views objects
     */
    private void initViews() {
        textInputLayoutfName = (TextInputLayout) findViewById(R.id.fNameTIL);
        textInputLayoutlName = (TextInputLayout) findViewById(R.id.lNameTIL);
        textInputLayoutPhone = (TextInputLayout) findViewById(R.id.phoneTIL);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.eMailTIL);

        editTextfName = (EditText) findViewById(R.id.fNameET);
        editTextlName = (EditText) findViewById(R.id.lNameET);
        editTextPhone = (EditText) findViewById(R.id.phoneET);
        editTextEmail = (EditText) findViewById(R.id.eMailET);

        continueBtn = (Button) findViewById(R.id.continueBtn);

        validation = new ValidationHelper(this);
    }

    /**
     * method to initialize listeners
     */
    private void initListeners() {
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
    }

    /**
     * method for validation of form on sign up button click
     */
    private void checkValidation() {
        if (!validation.isEditTextFilled(editTextfName, textInputLayoutfName, getString(R.string.err_msg_fName))) {
            return;
        }

        if (!validation.isEditTextFilled(editTextlName, textInputLayoutlName, getString(R.string.err_msg_lName))) {
            return;
        }

        if (!validation.isEditTextFilled(editTextPhone, textInputLayoutPhone, getString(R.string.err_msg_phone))) {
            return;
        }
        if (!validation.isEditTextEmail(editTextEmail, textInputLayoutEmail, getString(R.string.err_msg_email))) {
            return;
        }

        //Toast.makeText(getApplicationContext(),getString(R.string.success_message),Toast.LENGTH_LONG).show();


    }



}
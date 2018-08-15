package com.svnb2.name;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.svnb2.name.R;
import com.svnb2.name.ValidationHelper;

public class Registration1 extends Fragment {


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

  //  MoveToOtherFragement moveToOtherFragement;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
        R.layout.fragment_registration1,container,false);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initListeners();
    }

    /**
     * method to initialize views objects
     */
    private void initViews() {
        textInputLayoutfName = (TextInputLayout) getView().findViewById(R.id.fNameTIL);
        textInputLayoutlName = (TextInputLayout) getView().findViewById(R.id.lNameTIL);
        textInputLayoutPhone = (TextInputLayout) getView().findViewById(R.id.phoneTIL);
        textInputLayoutEmail = (TextInputLayout) getView().findViewById(R.id.eMailTIL);

        editTextfName = (EditText) getView().findViewById(R.id.fNameET);
        editTextlName = (EditText) getView().findViewById(R.id.lNameET);
        editTextPhone = (EditText) getView().findViewById(R.id.phoneET);
        editTextEmail = (EditText) getView().findViewById(R.id.eMailET);

        continueBtn = (Button) getView().findViewById(R.id.continueBtn);

        validation = new ValidationHelper(getActivity());
    }

    /**
     * method to initialize listeners
     */
    private void initListeners() {
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();

               FragmentTransaction trans = getFragmentManager().beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
                 * "root_fragment.xml" as the reference to replace fragment
                 */
                trans.replace(R.id.fragment_container, new Registration2());

                /*
                 * IMPORTANT: The following lines allow us to add the fragment
                 * to the stack and return to it later, by pressing back
                 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
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

    }

}
package com.svnb2.name;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.constraint.Guideline;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.svnb2.name.Phone.PhoneRecord;
import com.svnb2.name.Phone.PhoneRecordAdapter;

import java.util.ArrayList;
import java.util.List;

public class Registration1 extends Fragment {

    private int flag = 0;
    //TextInputLayout variables
    private TextInputLayout textInputLayoutfName;
    private TextInputLayout textInputLayoutlName;
    private TextInputLayout textInputLayoutPhone;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutMidName;
    private TextInputLayout textInputLayoutPrefix;
    private TextInputLayout textInputLayoutSuffix;

    //EditText variables
    private EditText editTextfName;
    private EditText editTextlName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextMidName;
    private EditText editTextPrefix;
    private EditText editTextSuffix;

    //Button
    private Button continueBtn;
    private Button addName;
    private Button addPhone;
    private Button removePhone;

    private Spinner phoneTypes;
    private Guideline guidelinePhoneTypes;
    private ValidationHelper validation;
    private Group phoneGroup;
    private List<PhoneRecord> phoneRecordsList= new ArrayList<>();
    private RecyclerView phoneList;
    private PhoneRecordAdapter phoneRecordAdapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_registration1, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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
        textInputLayoutlName.setVisibility(View.GONE);
        //textInputLayoutPhone = (TextInputLayout) getView().findViewById(R.id.phoneTIL);
        textInputLayoutEmail = (TextInputLayout) getView().findViewById(R.id.eMailTIL);
        textInputLayoutMidName = (TextInputLayout) getView().findViewById(R.id.midNameTIL);
        textInputLayoutMidName.setVisibility(View.GONE);
        textInputLayoutPrefix = (TextInputLayout) getView().findViewById(R.id.prefixTIL);
        textInputLayoutPrefix.setVisibility(View.GONE);
        textInputLayoutSuffix = (TextInputLayout) getView().findViewById(R.id.suffixTIL);
        textInputLayoutSuffix.setVisibility(View.GONE);

        editTextfName = (EditText) getView().findViewById(R.id.fNameET);
        editTextlName = (EditText) getView().findViewById(R.id.lNameET);
       // editTextPhone = (EditText) getView().findViewById(R.id.phoneET);
        editTextEmail = (EditText) getView().findViewById(R.id.eMailET);
        editTextMidName = (EditText) getView().findViewById(R.id.midNameET);
        editTextPrefix = (EditText) getView().findViewById(R.id.prefixET);
        editTextSuffix = (EditText) getView().findViewById(R.id.suffixET);



        continueBtn = (Button) getView().findViewById(R.id.continueBtn);
        addName = (Button) getView().findViewById(R.id.addName);
      /*  addPhone = (Button) getView().findViewById(R.id.addPhone);
       removePhone = (Button) getView().findViewById(R.id.removePhobe);

        phoneTypes=(Spinner)getView().findViewById(R.id.phoneTypes);
        guidelinePhoneTypes=(Guideline)getView().findViewById(R.id.guidelinePhoneTypes);
        phoneGroup= getView().findViewById(R.id.groupPhone);
        phoneGroup.setVisibility(View.GONE);*/
        validation = new ValidationHelper(getActivity());


       phoneRecordAdapter=new PhoneRecordAdapter(phoneRecordsList);
        phoneList=(RecyclerView)getView().findViewById(R.id.phoneRecycleView);
        phoneList.setLayoutManager(new LinearLayoutManager(getContext()));
        phoneList.setItemAnimator(new DefaultItemAnimator());
        phoneList.setAdapter(phoneRecordAdapter);
        PhoneRecord p1= new PhoneRecord();
        phoneRecordsList.clear();
        phoneRecordsList.add(p1);
        //phoneList.setAccessibilityDelegateCompat(phoneRecordAdapter);
       // phoneList=new ArrayList<>();
        //phoneList.setAdapter();
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

        addName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    textInputLayoutlName.setVisibility(View.VISIBLE);
                    textInputLayoutMidName.setVisibility(View.VISIBLE);
                    textInputLayoutPrefix.setVisibility(View.VISIBLE);
                    textInputLayoutSuffix.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    textInputLayoutlName.setVisibility(View.GONE);
                    textInputLayoutMidName.setVisibility(View.GONE);
                    textInputLayoutPrefix.setVisibility(View.GONE);
                    textInputLayoutSuffix.setVisibility(View.GONE);
                    flag = 0;
                }

            }
        });


        editTextfName.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            public void onFocusChange(View v, boolean hasFocus) {
                if (!validation.isEditTextFilled(editTextfName, textInputLayoutfName, getString(R.string.err_msg_fName)))
                    return;
            }
        });

/*
        editTextlName.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            public void onFocusChange(View v, boolean hasFocus) {
                if (!validation.isEditTextFilled(editTextlName, textInputLayoutlName, getString(R.string.err_msg_lName)))
                    return;
            }
        });
*/
       /* editTextPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            public void onFocusChange(View v, boolean hasFocus) {
                if (!validation.isEditTextFilled(editTextPhone, textInputLayoutPhone, getString(R.string.err_msg_phone)))
                    return;
            }
        });

        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!validation.isEditTextEmail(editTextEmail, textInputLayoutEmail, getString(R.string.err_msg_email)))
                    return;
            }
        });*/
/*
        editTextMidName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!validation.isEditTextFilled(editTextMidName, textInputLayoutMidName, getString(R.string.err_msg_midName)))
                    return;
            }
        });

        editTextPrefix.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!validation.isEditTextFilled(editTextPrefix, textInputLayoutPrefix, getString(R.string.err_msg_midName)))
                    return;
            }
        });

        editTextSuffix.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!validation.isEditTextFilled(editTextSuffix, textInputLayoutSuffix, getString(R.string.err_msg_midName)))
                    return;
            }
        });

*/

     /*   editTextPhone.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                addPhone.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().isEmpty()){
                    phoneGroup.setVisibility(View.GONE);
                    phoneTypes.setRight(getId());
                } else {
                    phoneGroup.setVisibility(View.VISIBLE);
                    guidelinePhoneTypes.setGuidelinePercent((float) 0.55);
                    phoneTypes.setRight(R.id.guidelineAddButton);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().isEmpty()){
                   phoneGroup.setVisibility(View.GONE);
                    guidelinePhoneTypes.setGuidelinePercent((float) 0.70);
                    phoneTypes.setRight(getId());

                } else {
                    phoneGroup.setVisibility(View.VISIBLE);
                    guidelinePhoneTypes.setGuidelinePercent((float) 0.55);
                    phoneTypes.setRight(R.id.guidelineAddButton);

                }
            }


        });*/

   //     phoneTypes.setOnItemSelectedListener(new CustomSpinnerOnItemSelectedListener());

       /* phoneList.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), phoneList, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PhoneRecord phoneRecord = phoneRecordsList.get(position);
                Toast.makeText(getActivity(), phoneRecord.getPhoneNum() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        phoneRecordAdapter.setOnItemClickListener(new PhoneRecordAdapter.OnItemClickListener() {
            @Override
            public void onAddPhoneClick(int position) {
                int newPosition=position;
                addPhoneRow(newPosition);
            }

            @Override
            public void onRemovePhoneClick(int position) {
                removePhoneRow(position);
            }
        });

    }
/*
    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }
*/




    /**
     * method for validation of form on sign up button click
     */
    private void checkValidation() {
        /*if (!validation.isEditTextFilled(editTextfName, textInputLayoutfName, getString(R.string.err_msg_fName))) {
            return;
        }
*/

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

    public void addPhoneRow(int position){
        phoneRecordsList.add(position,new PhoneRecord());
        //phoneList.setAdapter(phoneRecordAdapter);
        phoneRecordAdapter.notifyDataSetChanged();

    }
    public void removePhoneRow(int position){
        phoneRecordsList.remove(position);
        phoneRecordAdapter.notifyDataSetChanged();
    }




}
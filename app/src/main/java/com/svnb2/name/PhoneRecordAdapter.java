package com.svnb2.name;

//import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;



public class PhoneRecordAdapter extends RecyclerView.Adapter<PhoneRecordAdapter.MyViewHolder>  {
    private List<PhoneRecord> phoneList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public EditText phoneNum;
        public Spinner phoneTypes;

        public MyViewHolder(View view) {
            super(view);
            phoneNum = (EditText) view.findViewById(R.id.phoneET);
            phoneTypes = (Spinner) view.findViewById(R.id.phoneTypes);
        }
    }


    public PhoneRecordAdapter(List<PhoneRecord> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PhoneRecord phoneRecord = phoneList.get(position);
        holder.phoneNum.setText(phoneRecord.getPhoneNum());
        holder.phoneTypes.setSelection(PhoneRecord.PhoneType.Mobile.ordinal());
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }
}

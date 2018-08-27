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

import java.security.acl.Group;
import java.util.List;



public class PhoneRecordAdapter extends RecyclerView.Adapter<PhoneRecordAdapter.MyViewHolder>  {
    private List<PhoneRecord> phoneList;
    private OnItemClickListener mlistener;
    public Group phoneButtonsGroup;

    public interface OnItemClickListener{
        void onAddPhoneClick(int position);
        void onRemovePhoneClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener=listener;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public EditText phoneNum;
        public Spinner phoneTypes;
        public  ImageView addPhoneImage;
        public  ImageView removePhoneRow;

        public MyViewHolder(View view,OnItemClickListener listener) {
            super(view);
            phoneNum = (EditText) view.findViewById(R.id.phoneET);
            //phoneTypes = (Spinner) view.findViewById(R.id.phoneTypes);
            addPhoneImage=(ImageView)view.findViewById(R.id.addPhoneImage);
            removePhoneRow=(ImageView)view.findViewById(R.id.removePhobeImage);
        //    phoneButtonsGroup=(Group) view.findViewById(R.id.group);


            addPhoneImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onAddPhoneClick(position);
                        }
                    }
                }
            });

            removePhoneRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onRemovePhoneClick(position);
                        }
                    }
                }
            });


        }
    }


    public PhoneRecordAdapter(List<PhoneRecord> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_row, parent, false);

        return new MyViewHolder(itemView,mlistener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PhoneRecord phoneRecord = phoneList.get(position);
        holder.phoneNum.setText(phoneRecord.getPhoneNum());
      //  holder.phoneTypes.setSelection(PhoneRecord.PhoneType.Mobile.ordinal());
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }
}

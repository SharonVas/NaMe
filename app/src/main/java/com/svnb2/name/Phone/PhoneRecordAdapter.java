package com.svnb2.name.Phone;

//import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.svnb2.name.R;

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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public EditText phoneNum;
        public Spinner phoneTypes;
        public  ImageView addPhoneImage;
        public  ImageView removePhoneRow;
        public TextView addPhoneLabel;


        public MyViewHolder(View view,OnItemClickListener listener) {
            super(view);
            this.phoneNum = (EditText) view.findViewById(R.id.phoneET);
            this.phoneTypes = (Spinner) view.findViewById(R.id.phoneTypes);
            this.addPhoneImage=(ImageView)view.findViewById(R.id.addPhoneImage);
            this.removePhoneRow=(ImageView)view.findViewById(R.id.removePhobeImage);
            this.addPhoneLabel=(TextView) view.findViewById(R.id.addPhone);

           // this.phoneGroup= (ViewGroup) view.findViewById(R.id.group);
            addPhoneImage.setOnClickListener(this);
            removePhoneRow.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.addPhoneImage:
                    if(mlistener !=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mlistener.onAddPhoneClick(position);
                        }
                    }
                    break;

                case R.id.removePhobeImage:
                    if(mlistener !=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mlistener.onRemovePhoneClick(position);
                        }
                    }
                    break;

            }
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
        ConstraintLayout.LayoutParams lp=new ConstraintLayout.LayoutParams(0,0);
        holder.addPhoneImage.setLayoutParams(lp);
        holder.removePhoneRow.setLayoutParams(lp);
        holder.addPhoneLabel.setVisibility(View.GONE);
        holder.addPhoneImage.setVisibility(View.GONE);
        holder.removePhoneRow.setVisibility(View.GONE);
        PhoneRecord phoneRecord = phoneList.get(position);
        holder.phoneNum.setText(phoneRecord.getPhoneNum());
        holder.phoneTypes.setSelection(PhoneRecord.PhoneType.Mobile.ordinal());
        holder.phoneTypes.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }
}

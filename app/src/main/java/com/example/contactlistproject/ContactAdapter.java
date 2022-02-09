package com.example.contactlistproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter{
    private ArrayList<Contact> contactData;
    private View.OnClickListener mOnItemClickListener;
    private boolean isDeleting;
    private Context parentContext;

    public ContactAdapter(ArrayList<Contact> arrayList, Context context) {
        contactData = arrayList;
        parentContext = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        cvh.getContactTextView().setText(contactData.get(position).getContactName());
        cvh.getPhoneTextView().setText(contactData.get(position).getPhoneNumber());
        if (isDeleting) {
            cvh.getDeleteButton().setVisibility(View.VISIBLE);
            cvh.getDeleteButton().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    deleteItem(position);
                }
            });

            else {
                cvh.getDeleteButton().setVisibility(View.INVISIBLE);
            }
        }
        public void setDelete(boolean b){
            isDeleting = b;
        }
        private void deleteItem(int position){
            Contact contact = contactData.get(position);
            ContactDataSource ds = new ContactDataSource(parentContext);
            try{
                ds.open();
                boolean didDelete = ds.deleteContact(contact.getContactID());
                ds.close();
                if (didDelete){
                    contactData.remove(position);
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG.show();
                }
            }
            catch (Exception e){
                Toast.makeText(parentContext,"Delete Failed!", Toast.LENGTH_LONG.show();
            }
            private void initDeleteSwitch(){
                Switch s = findViewById(R.id.switchDelete);
                s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                    @Override
                    public void OnCheckChanged (CompoundButton compoundButton, boolean b){
                        Boolean status=compoundButton.isChecked();
                        contactAdapter.setDelete(status);;
                        contactAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }
}




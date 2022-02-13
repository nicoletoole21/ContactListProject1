package com.example.contactlistproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    ArrayList<Contact> contacts;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {

        //PASSES THE CONTACTID TO THE NEXT SCREEN
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int contactID = contacts.get(position).getContactID();
            Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                intent.putExtra("contactID", contactID);
                startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        listButton();
        initSettingsButton();
        initMapButton();

        ContactDataSource ds = new ContactDataSource(this);

        try {
            ds.open();
            contacts = ds.getContacts();
            ds.close();
            RecyclerView contactList = findViewById(R.id.rvContacts);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            contactList.setLayoutManager(layoutManager);
            ContactAdapter contactAdapter = new ContactAdapter(contacts);
            contactList.setAdapter(contactAdapter);

            //Connect the adapter to the setOnItemClickListener
            contactAdapter.setOnItemClickListener(onItemClickListener);

        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }
    }


    private void listButton() {

        ImageButton ibSettings = findViewById(R.id.imageButtonList);

        ibSettings.setEnabled(false);
    }

    private void initSettingsButton() {
        ImageButton ibList = findViewById(R.id.imageButtonSettings);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //reference created for current activity and which activity to start)
                Intent intent = new Intent(ContactListActivity.this, ContactSettingsActivity.class);
                //intent flag set to alert the operating system to not make multiple copies of same activity

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initMapButton() {
        ImageButton ibList = findViewById(R.id.imageButtonSettings);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //reference created for current activity and which activity to start)
                Intent intent = new Intent(ContactListActivity.this, ContactMapActivity.class);
                //intent flag set to alert the operating system to not make multiple copies of same activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}

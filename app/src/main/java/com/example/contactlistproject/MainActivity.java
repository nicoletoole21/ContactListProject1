package com.example.contactlistproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener {

    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListButton();
        initMapButton();
        initSettingsButton();
        initToggleButton();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            initContact(extras.getInt("contactID"));
        }
        else {
            currentContact = new Contact();
        }

        setForEditing(false);
        initChangeDateButton();
        initTextChangedEvents();
        initSaveButton();

    }

    private void initListButton() {
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //reference created for current activity and which activity to start)
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                //intent flag set to alert the operating system to not make multiple copies of same activity



                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initMapButton() {
        ImageButton ibMap = findViewById(R.id.imageButtonMap);
        ibMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactMapActivity.class);
               if (currentContact.getContactID() == -1) {
            Toast.makeText(getBaseContext(), "Contact must be saved before it can be mapped", Toast.LENGTH_LONG).show();
                }
                else{
                    intent.putExtra("contactID", currentContact.getContactID());
                    }
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        }



    private void initSettingsButton() {
        ImageButton ibList = findViewById(R.id.imageButtonSettings);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //reference created for current activity and which activity to start)
                Intent intent = new Intent(MainActivity.this, ContactSettingsActivity.class);
                //intent flag set to alert the operating system to not make multiple copies of same activity

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initToggleButton() {
        final ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
        editToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setForEditing(editToggle.isChecked());


            }
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipcode = findViewById(R.id.editZipcode);
        EditText editPhone = findViewById(R.id.editHome);
        EditText editCell = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEMail);
        Button buttonChange = findViewById(R.id.buttonBirthday);
        Button buttonSave = findViewById(R.id.butttonSave);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipcode.setEnabled(enabled);
        editPhone.setEnabled(enabled);
        editCell.setEnabled(enabled);
        editEmail.setEnabled(enabled);
        buttonChange.setEnabled(enabled);
        buttonSave.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }


    }


    @Override
    public void didFinishDatePickerDialog(Calendar selectedTime) {
        TextView birthday =
                findViewById(R.id.textBirthday);
        birthday.setText(DateFormat.format("MM/dd/yyyy", selectedTime));
        currentContact.setBirthday(selectedTime);


    }

    private void initChangeDateButton() {
        Button changeDate = findViewById(R.id.buttonBirthday);
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.show(fm, "DatePick");
            }

        });

    }

    private void initTextChangedEvents() {
        final EditText etContactName = findViewById(R.id.editName);
        etContactName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setContactName(etContactName.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        final EditText etStreetAddress = findViewById(R.id.editAddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setStreetAddress(etStreetAddress.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        final EditText etCity = findViewById(R.id.editCity);
        etCity.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setCity(etCity.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


        });

        final EditText etState = findViewById(R.id.editState);
        etState.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setState(etState.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });

        final EditText etZipcode = findViewById(R.id.editZipcode);
        etZipcode.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setZipCode(etZipcode.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });

        final EditText etHomePhone = findViewById(R.id.editHome);
        etHomePhone.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setPhoneNumber(etHomePhone.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });

        final EditText etCellPhone = findViewById(R.id.editCell);

        etCellPhone.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setCellNumber(etCellPhone.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });

        final EditText eteMail = findViewById(R.id.editEMail);

        eteMail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.seteMail(eteMail.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });

        etHomePhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        etCellPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());


    }

    private void initSaveButton() {

        Button saveButton = findViewById(R.id.butttonSave);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                hideKeyboard();

                ContactDataSource ds = new ContactDataSource(MainActivity.this);
                try {
                    ds.open();

                    if (currentContact.getContactID() == -1) {

                        wasSuccessful = ds.insertContact(currentContact);

                        if (wasSuccessful) {

                            int newId = ds.getLastContactID();

                            currentContact.setContactID(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateContact(currentContact);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }

                if (wasSuccessful) {
                    ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editName);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);

        EditText editAddress = findViewById(R.id.editAddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);

        EditText editCity = findViewById(R.id.editCity);
        imm.hideSoftInputFromWindow(editCity.getWindowToken(), 0);

        EditText editState = findViewById(R.id.editState);
        imm.hideSoftInputFromWindow(editState.getWindowToken(), 0);

        EditText editZipCode = findViewById(R.id.editZipcode);
        imm.hideSoftInputFromWindow(editZipCode.getWindowToken(), 0);

        EditText editHomeNum = findViewById(R.id.editHome);
        imm.hideSoftInputFromWindow(editHomeNum.getWindowToken(), 0);

        EditText editCell = findViewById(R.id.editCell);
        imm.hideSoftInputFromWindow(editCell.getWindowToken(), 0);

        EditText editeMail = findViewById(R.id.editEMail);
        imm.hideSoftInputFromWindow(editeMail.getWindowToken(), 0);

    }
//Retrieves the contact inputs info in each respective area.
    private void initContact(int id) {

        ContactDataSource ds = new ContactDataSource(MainActivity.this);

        try{
            ds.open();
            currentContact = ds.getSpecificContact(id);
            ds.close();
        }
        catch (Exception e) {

            Toast.makeText(this, "Load Contact Failed", Toast.LENGTH_LONG).show();
        }

        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipCode = findViewById(R.id.editZipcode);
        EditText editPhone = findViewById(R.id.editHome);
        EditText editCell = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEMail);
        TextView birthDay = findViewById(R.id.textBirthday);

        editName.setText(currentContact.getContactName());
        editAddress.setText(currentContact.getStreetAddress());
        editCity.setText(currentContact.getCity());
        editState.setText(currentContact.getState());
        editZipCode.setText(currentContact.getZipCode());

        editPhone.setText(currentContact.getPhoneNumber());
        editCell.setText(currentContact.getCellNumber());
        editEmail.setText(currentContact.geteMail());
        birthDay.setText(DateFormat.format("MM/dd/yyyy", currentContact.getBirthday().getTimeInMillis()).toString());

    }

}





       /**6.3
ContactDataSource ds = new ContactDataSource(this);
ArrayList<String> names;

try{
    ds.open();
    names =ds.getContactName();
    ds.close();
    RecyclerView contactList = findViewById(R.id.rvContacts);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    contactList.setLayoutManager(layoutManager);
    ContactAdapter contactAdapter = new ContactAdapter(names);
        }
catch (Exception e) {
    Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }

6.4

public class ContactAdapter extends RecyclerView.Adapter{
    private ArrayList<String> contactData;
    private View.OnClickListener mOnItemClickListener;

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewContact;
        public ContactViewHolder(@NonNull View itemView){
            super(itemView);
            textViewContact = itemView.findViewById(R.id.textViewName;
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
        public TextView getContactTextView() {
            return textViewContact;
        }
        }
        public ContactAdapter(ArrayList<String> arrayList){
        contactData= arrayList;
         }
       public void setOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
        }

        @NonNull
        @Override
public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view, parent,false)}:
       return new ContactViewHolder(v);
}
@Override
public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ContactViewHolder cvh = (ContactViewHolder) holder;
    cvh.getContactTextView().setText(contactData.get(position));
        }
@Override
public int getItemCount(){
    return contactData.size();
        }

6.5
private View.OnClickListener onItemClickListener = new View.OnClickListener(){
    @Override
        public void onClick(View view){
        RecyclerView. ViewHolder viewHolder = (RecyclerView.ViewHolder)view.getTag();
        int position = viewHolder.getAdapterPosition();
        Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
        startActivity(intent);
        )
        };
        }

6.6
public ArrayList<Contact> getContacts() {
    ArrayList<Contact> contacts = new ArrayList<Contact>();
    try{
        String query ="SELECT * FROM contact";
        Cursor cursor = database.rawQuery(query,null);

        Contact newContact;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            newContact = new Contact();
            newContact.setContactId(cursor.getInt(0));
        newContact.setContactName(cursor.getString(1));
        newContact.setStreetAddress(cursor.getString(2));
        newContact.setCity(cursor.getString(3));
        newContact.setState(cursor.getString(4));
        newContact.setZipCode(cursor.getString(5));
        newContact.setPhoneNumber(cursor.getString(6))
        newContact.setCellNumber(cursor.getString(7));
        newContact.setEmail(cursor.getString(8));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(cursor.getString(9)));
        newContact.setBirthday(calendar)
        contacts.add(newContact);
        cursor.moveToNext();
        }
        cursor.close();
        }
    catch (Exception e){
        contacts = new ArrayList<Contact>();
        }
    return contacts;
        }

6.7
public class ContactAdapter extends RecyclerView.Adapter{
    private ArrayList<Contact> contactData;
    private View.OnClickListener mOnItemClickListener;

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewContact;
        public TextView textPhone;
        public Button deleteBotton;
        public ContactViewHolder(@NonNull View itemView){
            super(itemView);
            textViewContact = itemView.findViewById(R.id.textViewName);
        textPhone = itemView.findViewById(R.id.textPhoneNumber;
        deleteButton= itemView.findViewById(R.id.buttonDeleteContact);
        itemView.setTag(this);
        itemView.setClickListener(mOnItemClickListener);
        }
        public TextView getPhoneTextView(){
            reeturn textPhone;
        }
        public Button getDeleteButton() {
            return deleteButton;
        }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        ContactViewHolder cvh = (ContactViewHolder) holder;
        cvh.getContactTextView().setText(contactData.get(position).getContactName());
        cvh.getPhoneTextView().setText(contactData.get(position).getPhoneNumber);
        }

        6.8
@Override
public void onClick(View view){
    RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
    int position = viewHolder.getAdapterPosition();
    int contactid = contacts.get(position).getContactId();
    Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
    intent.putExtra("contactID", contactID);
    startActivity(intent);
    }

    6.9
        public Contact getSpecificContact(int contactId){
        Contact contact = new Contact();
        String query = "SELECT * FROM contact WHERE_id =" + contactID;
        Cursor cursor = database.rawQuery(query,null);

        if (cursor.moveToFirst()){
            contact.setContactID(cursor.getInt(0));
        contact.setContactName(cursor.getString(1));
        contact.setStreetAddress(cursor.getString(2));
        contact.setCity(cursor.getString(3));
        contact.setState(cursor.getString(4));
        contact.setZipCode(cursor.getString(5));
        contact.setPhoneNumber(cursor.getString(6));
        contact.setCellNumber(cursor.getString(7));
        contact.setEmail(cursor.getString(8));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeinMillis(Long.valueOf(cursor.getString(9)));
        contact.setBirthday(calendar);

        cursor.close();
        }
        return contact;
        }

        6.10
        private void initContact(int id) {
        ContactDataSource ds = new ContactDataSource(MainActivity.this);
        try{
            ds.open();
            currentContact = ds.getSpecificContact(id);
            ds.close();
        }
        catch (Exception e) {
            Toast.makeText(this, "Load Contact Failed", Toast.LENGTH_LONG).show();
        }

        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipCode= findViewById(R.id.editZipCode);
        EditText editPhone = findViewById(R.id.editHome);
        EditText editCell = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEmail);
        TextView birthDay = findViewById(R.id.textBirthday);

        editName.setText(currentContact.getContactName());
        editAddress.setText(currentContact.getAddress());
        editCity.setText(currentContact.getCity());
        editState.setText(currentContact.getState());
        editZipCode.setText(currentContact.getZipCode());

        editPhone.setText(currentContact.getPhoneNumber());
        editCell.setText(currentContact.getCellNumber());
        editEmail.setText(currentContact.getEMail());
birthDay.setText(DateFormat.format("MM/dd/yyyy", currentContact.getBirthday().getTimeInMillis()).toString());

    }

    6.11
        Bundle exras = getIntent().getExtras();
    if(extras != null){
        initContact(extras.getInt("contactid"));
             }
    else {
        currentContact = new Contact();
        }
    6.12
    private void initAddContactButton() {
        Button newContact = findViewById(R.id.buttonAddContact);
        newContact.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                startActivity(intent);
        }
        });
        }
        }
6.13
public boolean deleteContact(int contactId) {
    boolean didDelete = false;
    try{
        didDelete = database.delete("contact", "_id=" + contactId, null) > 0;
        }
    catch(Exception e) {

        }
    return didDelete;
        }

6.14
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
            else{
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
        }
        }
        6.15

        private void initDeleteSwitch(){
        Switch s = findViewById(R.id.switchDelete);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
        public void OnCheckChanged (CompoundButton compounfButton, boolean b){
        Boolean status=compoundButton.isChecked();
        contactAdapter.setDelete(status);;
        contactAdapter.notifyDataSetChanged();
        }
        });
        }
        }

6.16
        @Override
        public void onResume(){
    super.onResume();
        String sortBy = getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE).getString("sortfield","contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortorder", "ASC");
        ContactDataSource ds = new ContactDataSource(this);
        try{
        ds.open();
        contacts = ds.getContacts(sortBy, sortOrder);
        ds.close();
        contactList = findViewById(R.id.rvContacts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        contactList.setLayoutManager(layoutManager);
        contactAdapter = new ContactAdapter(names);
        contactList.setAdapter(contactAdapter);
        }
        catch(Exception e){
        Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();

        }
6.18
        try{
        ds.open();
        contacts = ds.getContacts(sortBy, sortOrder);
        ds.close();
        if (contacts.size() > 0){
        contactList = findViewByID(R.id.rvContacts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        contactList.setLayoutManager(layoutManager);
        contentAdapter= new ContactAdapter(names);
        contactList.setAdapter(contentAdapter);
        } else {
        Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
        startActivity(intent);
        }
        } catch (Exception e){
        Toast.makeText(this,  "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }
        }
*/
package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.edt);
        button=findViewById(R.id.btn);


    }

    public void btnClicked(View view) {
        String mimeString = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call";
    
        String displayName = null;
        String name="ABC"; // here you can give static name.
        Long _id;
        ContentResolver resolver = getApplicationContext().getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Data.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);
        while (cursor.moveToNext()) {
            _id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID));
            displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
            if (displayName.equals(name)) {
                if (mimeType.equals(mimeString)) {
                    String data = "content://com.android.contacts/data/" + _id;
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_VIEW);
                    sendIntent.setDataAndType(Uri.parse(data), mimeString);
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                }
            }
        }
}}
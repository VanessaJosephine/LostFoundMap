package com.example.lostfoundmap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lostfoundmap.sqlitehelper.DatabaseHelper;

public class ItemFragment extends Fragment {
    TextView name, description, date, location, phone;
    Button deleteBtn;
    private static final String NAME = "param1";
    private static final String TYPE = "param2";
    private static final String PHONE = "param3";
    private static final String DESCRIPTION = "param4";
    private static final String DATE = "param5";
    private static final String LOCATION = "param6";

    private String Name, Type, Phone, Description, Date, Location;

    public ItemFragment() {
        // Required empty public constructor
    }
    public static ItemFragment newInstance(String name, String type, String phone, String description, String date, String location) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(TYPE, type);
        args.putString(PHONE, phone);
        args.putString(DESCRIPTION, description);
        args.putString(DATE, date);
        args.putString(LOCATION, location);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Name = getArguments().getString(NAME);
            Type = getArguments().getString(TYPE);
            Phone = getArguments().getString(PHONE);
            Description = getArguments().getString(DESCRIPTION);
            Date = getArguments().getString(DATE);
            Location = getArguments().getString(LOCATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        name = view.findViewById(R.id.textView4);
        description = view.findViewById(R.id.textView5);
        date = view.findViewById(R.id.textView6);
        location = view.findViewById(R.id.textView7);
        phone = view.findViewById(R.id.textView8);
        deleteBtn = view.findViewById(R.id.button4);

        // Set values
        name.setText(Type + " " + Name);
        description.setText(description.getText().toString() + Description);
        date.setText(date.getText().toString() + Date);
        location.setText(location.getText().toString() + Location);
        phone.setText(phone.getText().toString() + Phone);

        // DeleteButton
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DatabaseHelper
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                databaseHelper.deleteAdvert(Name);
                Toast.makeText(getContext(), "Advert successfully deleted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
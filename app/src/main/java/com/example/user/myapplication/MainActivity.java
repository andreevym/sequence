package com.example.user.myapplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
    static ListView userList;
    UserCustomAdapter userAdapter;
    static ArrayList<User> userArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = (EditText) findViewById(R.id.name);
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(name.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        userAdapter = new UserCustomAdapter(MainActivity.this, R.layout.row, userArray);
        userList = (ListView) findViewById(R.id.listView);
        userList.setItemsCanFocus(false);
        userList.setAdapter(userAdapter);

        userList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
            }
        });

    }

    public void addUser(View view) {
        EditText name = (EditText) findViewById(R.id.name);
        String userName = name.getText().toString();
        if(name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Введите имя.", Toast.LENGTH_LONG).show();
            return;
        }
        for (User item: userArray) {
            if(userName.equals(item.getName())) {
                Toast.makeText(this, "У нас в компании нельзя два раза получать зарплату.", Toast.LENGTH_LONG).show();
                return;
            }
        }
        User user = new User(name.getText().toString(), new Date(), userArray.size());
        userArray.add(user);
        name.getText().clear();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(name.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        Toast.makeText(this, "Ваше место в очереди: №" + user.getId(), Toast.LENGTH_LONG).show();
    }
}
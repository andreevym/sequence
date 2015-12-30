package com.example.user.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class UserCustomAdapter extends ArrayAdapter<User> {
    Context context;
    int layoutResourceId;
    ArrayList<User> data = new ArrayList<User>();

    public UserCustomAdapter(Context context, int layoutResourceId,
                             ArrayList<User> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final UserHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.name = (TextView) row.findViewById(R.id.textView1);
            holder.date = (TextView) row.findViewById(R.id.textView2);
            holder.btnDelete = (Button) row.findViewById(R.id.button2);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        final User user = data.get(position);
        holder.name.setText(user.getName());
        holder.date.setText(new SimpleDateFormat().format(user.getDate()));
        holder.btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!MainActivity.userArray.isEmpty()) {
                    MainActivity.userArray.remove(user);
                    notifyDataSetChanged();
                }
            }
        });
        holder.imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getId() != 0) {
                    User prevUser = MainActivity.userArray.get(user.getId() - 1);
                    prevUser.setId(user.getId());
                    user.setId(user.getId() - 1);

                    Collections.swap(MainActivity.userArray, prevUser.getId(), user.getId());
                    notifyDataSetChanged();

                    Toast.makeText(getContext(), "Вы сделали правильный выбор.", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getContext(), "Вы и так первый, но от денег мы не отказались, спасибо.", Toast.LENGTH_LONG).show();
                }
            }
        });
        return row;
    }

    static class UserHolder {
        TextView name;
        TextView date;
        Button btnDelete;
        ImageView imageView;
    }
}
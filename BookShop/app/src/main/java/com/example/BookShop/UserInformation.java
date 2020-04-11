package com.example.BookShop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.BookShop.DBUser.User;

public class UserInformation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        final TextView infor_fullname = (TextView) findViewById(R.id.info_account1);
        final TextView infor_fullname1 = (TextView) findViewById(R.id.info_fullname);
        final TextView infor_email = (TextView) findViewById(R.id.info_email);
        final TextView infor_diachi = (TextView) findViewById(R.id.info_diachi);
        final TextView infor_dienthoai = (TextView) findViewById(R.id.info_dienthoai);

        User s = LayOutAndLisView.getUser();

        infor_fullname.setText(s.getAccount());
        infor_fullname1.setText(s.getFullname());
        infor_email.setText(s.getGmail());
        infor_diachi.setText(s.getAddress());
        infor_dienthoai.setText(String.valueOf(s.getTel()));

    }
}

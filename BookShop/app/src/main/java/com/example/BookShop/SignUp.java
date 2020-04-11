package com.example.BookShop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.BookShop.DBUser.User;
import com.example.BookShop.Database.SQLSever;

import java.util.ArrayList;

public class SignUp extends Activity {
    //DBManager dbManager = new DBManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dangky);

        final SQLSever sqlSever = new SQLSever(this);


        final EditText account = (EditText) findViewById(R.id.txt_account);
        final EditText fullname = (EditText) findViewById(R.id.txt_name);
        final EditText gmail = (EditText) findViewById(R.id.txt_mail);
        final EditText diachi = (EditText) findViewById(R.id.txt_dc);
        final EditText tel = (EditText) findViewById(R.id.txt_tel);
        final EditText password1 = (EditText) findViewById(R.id.txt_pw);
        final EditText password2 = (EditText) findViewById(R.id.txt_pw2);
        Button login = (Button) findViewById(R.id.btn_back);
        Button signup = (Button) findViewById(R.id.btn_dki);

        password1.setInputType(InputType.TYPE_CLASS_TEXT |//ẩn Text để làm mật khẩu
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password2.setInputType(InputType.TYPE_CLASS_TEXT |//ẩn Text để làm mật khẩu
                InputType.TYPE_TEXT_VARIATION_PASSWORD);

        final SQLSever sql_user = new SQLSever(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String acc= account.getText().toString();
                final String ten= fullname.getText().toString();
                final String Gmail = gmail.getText().toString();
                final String DiaChi = diachi.getText().toString();
                final String Tel = tel.getText().toString();
                final String mk1= password1.getText().toString();
                final String mk2= password2.getText().toString();
                if(ten.equals("") || Gmail.equals("")|| mk1.equals("") || mk2.equals("") || acc.equals("")){
                    Toast.makeText(SignUp.this, "Vui Lòng Điền Đủ Thông tin!!!", Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog.Builder b=new AlertDialog.Builder(SignUp.this);
                    b.setTitle("Sign Up");
                    b.setMessage("Bạn có muốn Đăng Ký?");
                    b.setIcon(R.drawable.icons8_adduser);
                    b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            if(mk1.equals(mk2)){
                                ArrayList<User> users= sqlSever.getArrayUser();
                                if(users != null){
                                    boolean ketqua = true;
                                    for(User x : users){
                                        if(x.getAccount().equals(acc)){
                                            ketqua = false;
                                        }
                                    }
                                    if(ketqua == true){
                                        User s = new User(acc, ten, Gmail, mk1, DiaChi, Tel);
                                        sqlSever.AddUser(s);
                                        if(s!=null){
                                            Toast.makeText(SignUp.this, "Đăng Ký Thành Công ^.^", Toast.LENGTH_SHORT).show();
                                            account.setText("");
                                            fullname.setText("");
                                            gmail.setText("");
                                            diachi.setText("");
                                            tel.setText("");
                                            password1.setText("");
                                            password2.setText("");
                                            Intent intent=new Intent(getApplicationContext(),Login.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(SignUp.this, "Đăng Ký Thất Bại ^.^", Toast.LENGTH_SHORT).show();
                                            account.setText("");
                                            fullname.setText("");
                                            gmail.setText("");
                                            diachi.setText("");
                                            tel.setText("");
                                            password1.setText("");
                                            password2.setText("");
                                        }
                                    }else{
                                        Toast.makeText(SignUp.this, "Account Đã Tồn tại!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else{
                                Toast.makeText(SignUp.this, "Mật Khẩu Nhập Lại Không đúng!!!", Toast.LENGTH_SHORT).show();
                                password2.setText("");
                                password1.setText("");
                            }
                        }});
                    b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                        }
                    });
                    b.create().show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLogin();
            }
        });
    }
    public void OpenLogin(){
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);

    }
}


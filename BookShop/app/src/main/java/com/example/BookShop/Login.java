package com.example.BookShop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.BookShop.DBBook.Book;
import com.example.BookShop.DBUser.User;
import com.example.BookShop.Database.SQLBook;
import com.example.BookShop.Database.SQLSever;

import java.util.ArrayList;

public class Login extends Activity {

    public static final String EXTRA_USER = "com.example.application.example.EXTRA_USER";
    public static  String tentk = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_new);

        final EditText password = (EditText) findViewById(R.id.pass);
        final EditText username = (EditText) findViewById(R.id.username);
        final Button login = (Button) findViewById(R.id.login);
        final Button signup = (Button) findViewById(R.id.SignUp);
        final Button quenmk = (Button) findViewById(R.id.quenmk);
//        final ImageView libraryicon= (ImageView) findViewById(R.id.iconlibrary);

//        libraryicon.setImageResource(R.drawable.icons8_library);

        final SQLSever sqlSever = new SQLSever(this);
        ArrayList<User> list = new ArrayList<>();
        //----------Tài Khoản Gốc-------------------
        User s = new User("admin", "ADMIN", "admin@gmail.com", "admin", "Quảng Nam","0898520250");
       sqlSever.AddUser(s);
        //-----------Add Sách ------------
        ArrayBook();

        //---------------------------------------------------------------------------
        password.setInputType(InputType.TYPE_CLASS_TEXT |//ẩn Text để làm mật khẩu
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUp();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                if(name.equals("") || pass.equals("")){
                    Toast.makeText( Login.this, "Vui Lòng Điền Đủ Thông tin!!!", Toast.LENGTH_SHORT).show();
                }else{
                    User s = sqlSever.getUser(name);
                    if(s != null){
                        if(s.getPassword().equals(pass)){
                            Toast.makeText( Login.this, "Đăng nhập thành công ^.^", Toast.LENGTH_SHORT).show();
                            password.setText("");
                            username.setText("");
                            tentk=s.getAccount();
                            Login(s);
                        }else {
                            password.setText("");
                            username.setText("");
                            Toast.makeText( Login.this, "Tài khoản hoặc mật khẩu không chính xác!!!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        password.setText("");
                        username.setText("");
                        Toast.makeText( Login.this, "Tài khoản Không Tồn tại!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenForgotPassword();
            }
        });
        ArrayBook();
    }
    public void ArrayBook(){
        SQLBook sqlBook = new SQLBook(this);
        ArrayList<Book> book = new ArrayList<>();
        book.add(new Book(1, "Thanh Xuân Có Cậu Là Đủ Rồi", "Truyện", "Văn học trẻ","2016",R.drawable.b1, 100,233000));
        book.add(new Book(2, "Bán Linh Hồn Cho Ác Quỷ", "Sách", "Thanh Niên","2015",R.drawable.b2, 100,203200));
        book.add(new Book(3, "Là Vì Con Tim Anh Rung Lên", "Sách tình cảm", "Quang Đạt","2008",R.drawable.b3, 100,220000));
        book.add(new Book(4, "Phái Đẹp - Elle - Số 110 (Tháng 12/2019)", "Tạp chí", "Sheryl Sandberg","2014",R.drawable.b4, 100,203000));
        book.add(new Book(5, "Trước Ngày Em Đến", "Văn học", "Vô Danh","TB-2018",R.drawable.b5, 100,22300));
        book.add(new Book(6, "Âm Mưu Thay Não", "Tryện", "Giản Tư Hải","2013",R.drawable.b6, 100,234000));
        book.add(new Book(7, "Cuộc Sống Thượng Lưu Của Hoàng Đế Mèo", "Tiểu Thuyết", "Paulo Coelho","2013",R.drawable.b7, 100,530000));
        for(Book x: book){
            sqlBook.AddBook(x);
        }
    }

    public void OpenSignUp(){
        Intent intent = new Intent( Login.this, SignUp.class);
        startActivity(intent);
    }

    public void Login(User s){
        Intent intent = new Intent( Login.this, LayOutAndLisView.class);
        intent.putExtra(EXTRA_USER, s.getAccount());
        startActivity(intent);
        finish();
    }

    public void OpenForgotPassword(){
        Intent intent = new Intent( Login.this, ForgotPassword.class);
        startActivity(intent);
    }


}

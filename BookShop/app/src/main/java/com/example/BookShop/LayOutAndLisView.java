package com.example.BookShop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.BookShop.DBBook.Book;
import com.example.BookShop.DBBook.BookAdapter;
import com.example.BookShop.DBCart.Cart;
import com.example.BookShop.DBUser.User;
import com.example.BookShop.Database.SQLBook;
import com.example.BookShop.Database.SQLSever;

import java.util.ArrayList;

public class LayOutAndLisView extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<Cart> manggiohang; // tạo mảng lưu item trong giỏ hàng

    private ListView lv;

    public static ArrayList<Book> Book_Deefault;


    private BookAdapter adapter;
    //---------------User hiện tại------------------------------------
    public static User user_pro;

    public static User getUser() {
        return user_pro;
    }
    public void setUser(User user) {
        this.user_pro = user;
    }

    //--------------Lấy Sách Đưa vào Book information---------------
    public static int Bookid;
    public static int getBookid() {
        return Bookid;
    }
    public static void setBookid(int bookid) {
        Bookid = bookid;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_out_and_lis_view);

        AnhXa();
        ArrayBook();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void ArrayBook(){
        SQLBook sqlBook = new SQLBook(this);
        ArrayList<Book> book = sqlBook.getAllBook();
        adapter = new BookAdapter(this, R.layout.elemen_book, book);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayOutAndLisView.setBookid(position+1);
                OpenThongTinSach();
            }
        });
    }

    public void OpenThongTinSach(){
        Intent intent = new Intent(this, BookInformation.class);
        startActivity(intent);
    }
    public void AnhXa(){
        lv= (ListView) findViewById(R.id.arraybook);
        if(manggiohang!=null){
        }
        else
        {
            manggiohang=new ArrayList<>();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // thông tin người dùng trên thanh menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lay_out_and_lis_view, menu);
        TextView ten = (TextView) findViewById(R.id.Text_Name);
        TextView email = (TextView) findViewById(R.id.Text_Gmail);
        TextView trangthai = (TextView) findViewById(R.id.Text_TrangThai);

        Intent intent = getIntent();
        String tt_acc = intent.getStringExtra(Login.EXTRA_USER);
        if(intent.getStringExtra(Login.EXTRA_USER)==null){
            tt_acc = intent.getStringExtra(ArrayCart.EXTRA_USER);
            final SQLSever sqlSever = new SQLSever(this);
            User s = sqlSever.getUser(tt_acc);
        }
        else
        {
            final SQLSever sqlSever = new SQLSever(this);
            User s = sqlSever.getUser(tt_acc);
        }

        final SQLSever sqlSever = new SQLSever(this);
        User s = sqlSever.getUser(tt_acc);

        ten.setText(s.getFullname());
        email.setText(s.getGmail());
        trangthai.setText(s.getTel());
        this.setUser(s);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_CartBook) {
            Intent intent = new Intent(this, ArrayCart.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_OrderBook) {
            Intent intent = new Intent(this, ArrayOrder.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, UserInformation.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, ChangPass.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            AlertDialog.Builder b=new AlertDialog.Builder(LayOutAndLisView.this);
            b.setTitle("Đăng Xuất");
            b.setMessage("Bạn có muốn đăng xuất?");
            b.setIcon(R.drawable.icons_out);
            b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    OpenLogin();
                    finish();
                }});
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });
            b.create().show();
        } else {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void OpenLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}

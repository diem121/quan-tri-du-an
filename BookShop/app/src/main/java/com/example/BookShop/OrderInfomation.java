package com.example.BookShop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.BookShop.DBBook.Book;
import com.example.BookShop.DBOrder.Order;
import com.example.BookShop.Database.SQLBook;

import java.util.ArrayList;

public class OrderInfomation extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_order_infomation);

            final ImageView img = (ImageView) findViewById(R.id.anhsach_order);
            final TextView booktitle = (TextView) findViewById(R.id.txt_tensach_order);
            final TextView orderid = (TextView) findViewById(R.id.txt_id_order);
            final TextView bookid = (TextView) findViewById(R.id.txt_idsach_order);
            final TextView ngaydat = (TextView) findViewById(R.id.txt_ngaydat_order);
            final TextView mota = (TextView) findViewById(R.id.txt_order_ghichu);
            SQLBook sqlBook = new SQLBook(this);
            Order order = ArrayOrder.getOrder();
            ArrayList<Book> books = sqlBook.getAllBook();
            Book book = new Book();
            for (Book x : books){
                if(x.getBookID() == order.getBookID()){
                    book = x;
                }
            }

            img.setImageResource(book.getImgBook());
            booktitle.setText(order.getBookTitle());
            orderid.setText(String.valueOf(order.getOrderID()));
            bookid.setText(String.valueOf(order.getBookID()));
            String ngay = order.getNgayDat().substring(6,8);
            String thang = order.getNgayDat().substring(4,6);
            String nam = order.getNgayDat().substring(0,4);
            ngaydat.setText(ngay + "-" + thang + "-" + nam);
            mota.setText("Tác giả là \"" + book.getTacGia() + "\"\n và thuộc thể loại \"" + book.getTheLoai()+"\"");
        }
    }


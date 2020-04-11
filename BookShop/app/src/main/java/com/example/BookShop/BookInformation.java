package com.example.BookShop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.BookShop.DBBook.Book;
import com.example.BookShop.DBCart.Cart;
import com.example.BookShop.DBUser.User;
import com.example.BookShop.Database.SQLBook;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookInformation extends AppCompatActivity {
    Button tt_mua;
    ImageView tt_imgSach ;
    TextView tt_tensach ;
    TextView tt_theloai;
    TextView tt_tacgia;
    TextView tt_namXB;
    TextView tt_gia;
    Spinner tt_soluong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        tt_imgSach = (ImageView) findViewById(R.id.tt_img_Anh);
        tt_tensach = (TextView) findViewById(R.id.tt_TenSach);
        tt_theloai = (TextView) findViewById(R.id.tt_TheLoai);
        tt_tacgia = (TextView) findViewById(R.id.tt_TacGia);
        tt_namXB = (TextView) findViewById(R.id.tt_NamXB);
        tt_gia = (TextView) findViewById(R.id.tt_giasach);
        tt_soluong = (Spinner) findViewById(R.id.tt_SoLuong);
//        tt_muon = (Button) findViewById(R.id.tt_btMuon);
        tt_mua = (Button) findViewById(R.id.tt_btnmua);
        ItemSpinner();

        final SQLBook sqlBook = new SQLBook(this);
        final int bookid = LayOutAndLisView.getBookid();
        final Book book = sqlBook.getBook(bookid);

        tt_imgSach.setImageResource(book.getImgBook());
        tt_tensach.setText(book.getTenSach());
        tt_theloai.setText("Thể Loại : " + book.getTheLoai());
        tt_tacgia.setText("Tác Giả : " + book.getTacGia());
        tt_namXB.setText("Năm Xuất Bản: " + book.getNamXB());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tt_gia.setText("Giá: "+decimalFormat.format(book.getGia()) +" VND");

        EventClickMua();
    }

    private void ItemSpinner() {
        Integer soluong[]=new Integer[]{1,2,3,4,5,6,7,8,9,10
        };
        ArrayAdapter<Integer> integerArrayAdapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        tt_soluong.setAdapter(integerArrayAdapter);

    }

    public void EventClickMua (){
        tt_mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User s = LayOutAndLisView.getUser();
                final SQLBook sqlBook = new SQLBook(getApplicationContext());
                final int bookid = LayOutAndLisView.getBookid();
                final Book book = sqlBook.getBook(bookid);
                boolean exists =false;
                if(LayOutAndLisView.manggiohang.size()>0){
                    int sl=Integer.parseInt(tt_soluong.getSelectedItem().toString());
                    for(int i=0;i<LayOutAndLisView.manggiohang.size();i++){
                        if(LayOutAndLisView.manggiohang.get(i).getBookID() ==bookid){
                            // cập nhật lại số lượng mới
                            LayOutAndLisView.manggiohang.get(i).setSoluong(LayOutAndLisView.manggiohang.get(i).getSoluong()+sl);
                            if(LayOutAndLisView.manggiohang.get(i).getSoluong()>=10){
                                LayOutAndLisView.manggiohang.get(i).setSoluong(10);
                            }
                            // set lại giá
                            LayOutAndLisView.manggiohang.get(i).setGia(book.getGia()*LayOutAndLisView.manggiohang.get(i).getSoluong());
                            exists =true;// nếu đúng
                        }
                    }
                    if(exists==false){
                        int so_lg=Integer.parseInt(tt_soluong.getSelectedItem().toString());
                        int giamoi= so_lg * book.getGia();
                        LayOutAndLisView.manggiohang.add(new Cart(s.getAccount(),bookid,so_lg,giamoi,book.getTenSach(),book.getImgBook()));
                    }
                }
                else{//nếu chưa ccos thì add vào dữ liệu mới
                    int so_lg=Integer.parseInt(tt_soluong.getSelectedItem().toString());
                    int giamoi= so_lg * book.getGia();
                    LayOutAndLisView.manggiohang.add(new Cart(s.getAccount(),bookid,so_lg,giamoi,book.getTenSach(),book.getImgBook()));
                }
                Intent intent=new Intent(getApplicationContext(),ArrayCart.class);// màn hình đang đứng, giỏ hàng
                startActivity(intent);
            }
        });
    }
    //lấy ngày hiện tại
    private String getDate(){
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String getdate = dateFormatter.format(today);
        return getdate;
    }
    public void Reset(){
        Intent intent = new Intent( BookInformation.this, BookInformation.class);
        startActivity(intent);
        finish();
    }

    }

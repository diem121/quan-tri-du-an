package com.example.BookShop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.BookShop.DBBook.Book;
import com.example.BookShop.DBCart.Cart;
import com.example.BookShop.DBCart.CartAdapter;
import com.example.BookShop.DBOrder.Order;
import com.example.BookShop.DBUser.User;
import com.example.BookShop.Database.SQLBook;
import com.example.BookShop.Database.SQLOrder;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArrayCart extends AppCompatActivity {
    public static final String EXTRA_USER = "com.example.application.example.EXTRA_USER";
    ListView lvgiohang;
    static TextView tonggia;
    TextView thongbao;
    Button btn_tieptucmua,btn_thanhtoan;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_cart);
        Anhxa();
        CheckDulieu();
        XoaDongCart();
        PullDuLieu();
        XoaDongCart();
        TieptucMua();
        ThanhToan();
    }

    private void ThanhToan() {
        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User s = LayOutAndLisView.getUser();
                final SQLBook sqlBook = new SQLBook(getApplicationContext());
                final SQLOrder sqlOrder = new SQLOrder(getApplicationContext());
//                final int bookid = LayOutAndLisView.getBookid();
                Book book;
                ArrayList<Cart> items = LayOutAndLisView.manggiohang;
                for(int i=0;i<LayOutAndLisView.manggiohang.size();i++){
                    Order order = new Order(items.get(i).getAccount(),items.get(i).getBookID(),items.get(i).getBookTitle(),getDate(),items.get(i).getSoluong(),items.get(i).getGia(),items.get(i).getImgBook());
                    sqlOrder.AddOrder(order);
                    book = sqlBook.getBook(items.get(i).getBookID());
                    int soluong=book.getSoLuong()-items.get(i).getSoluong();
                    sqlBook.UpdateSoLuongBook(soluong,items.get(i).getBookID());
                }
                LayOutAndLisView.manggiohang.clear();
                Toast.makeText(getApplicationContext(),"Thanh toán thành công rồi nè :>",Toast.LENGTH_LONG).show();
                ResetCart();


            }
        });
    }

    private void ResetCart() {
        Intent intent=new Intent(getApplicationContext(),ArrayCart.class);
        startActivity(intent);
    }

    private String getDate(){
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String getdate = dateFormatter.format(today);
        return getdate;
    }


    private void TieptucMua() {
        btn_tieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LayOutAndLisView.class);
                intent.putExtra(EXTRA_USER, Login.tentk.toString());
                startActivity(intent);
            }
        });
    }

    private void XoaDongCart() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ArrayCart.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(LayOutAndLisView.manggiohang.size()<=0){
                            thongbao.setVisibility(View.VISIBLE);
                        }
                        else{
                            LayOutAndLisView.manggiohang.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            PullDuLieu();
                            if(LayOutAndLisView.manggiohang.size()<=0){
                                thongbao.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                thongbao.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                PullDuLieu();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartAdapter.notifyDataSetChanged();
                        PullDuLieu();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void PullDuLieu() {
        long tongtien=0;
        for (int i=0;i<LayOutAndLisView.manggiohang.size();i++){
            tongtien+=LayOutAndLisView.manggiohang.get(i).getGia();
        }
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(tongtien);
        tonggia.setText(formattedNumber);
    }

    private void CheckDulieu() {
        if(LayOutAndLisView.manggiohang.size()<=0){
            cartAdapter.notifyDataSetChanged();// cập nhật lại giỏ hàng khi xoa
            thongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }
        else
        {
            cartAdapter.notifyDataSetChanged();// cập nhật lại giỏ hàng
            thongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }

    }

    public void Anhxa(){
        btn_tieptucmua=(Button)findViewById(R.id.btn_tieptuc);
        btn_thanhtoan=(Button)findViewById(R.id.btn_thanh_toan);
        tonggia=(TextView)findViewById(R.id.txt_thanhtien);
        thongbao=(TextView)findViewById(R.id.txt_thongbao_cart);
        lvgiohang=(ListView) findViewById(R.id.listview_cart);
        cartAdapter=new CartAdapter(ArrayCart.this,LayOutAndLisView.manggiohang);
        lvgiohang.setAdapter(cartAdapter);// đổ dữ liệu lên lv
}
}

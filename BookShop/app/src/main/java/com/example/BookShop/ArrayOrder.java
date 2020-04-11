package com.example.BookShop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.BookShop.DBBook.Book;
import com.example.BookShop.DBOrder.Order;
import com.example.BookShop.DBOrder.OrderAdapter;
import com.example.BookShop.DBUser.User;
import com.example.BookShop.Database.SQLBook;
import com.example.BookShop.Database.SQLOrder;

import java.util.ArrayList;

public class ArrayOrder extends AppCompatActivity {
    private OrderAdapter adapter;
    public static Book book_log;

    public static Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_order);

        ListView lvlog = (ListView) findViewById(R.id.arrayorder);

        final SQLOrder sqlOrder = new SQLOrder(this);
        final SQLBook sqlBook = new SQLBook(this);
        User s = LayOutAndLisView.getUser();
        final ArrayList<Order> orders = sqlOrder.getAllOders();
        if(orders != null){
            ArrayList<Order> bookOrders = new ArrayList<>();
            for(Order x: orders){
                if(x.getAccount().equals(s.getAccount())){
                    Order order= sqlOrder.getOrder(x.getOrderID());
                    bookOrders.add(order);
                }
            }
            if(bookOrders != null){
                adapter = new OrderAdapter(this, R.layout.element_book_order,bookOrders);
                lvlog.setAdapter(adapter);
            }else{
                Toast.makeText(ArrayOrder.this, "Bạn chưa đặt hàng!!!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(ArrayOrder.this, "Đơn hàng trống!!!", Toast.LENGTH_SHORT).show();
        }
        lvlog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OpenThongTinDonHang(position, orders);
            }
        });
        lvlog.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder b=new AlertDialog.Builder(ArrayOrder.this);
                b.setTitle("Delete");
                b.setMessage("Bạn có muốn Xóa Sách \"" + orders.get(position).getBookTitle() + "\" ?");
                b.setIcon(R.drawable.icon_delete);
                b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        sqlOrder.DeleteOrder(orders.get(position));
                        Toast.makeText(ArrayOrder.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        ResetSach();
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
                return true;
            }
        });
    }
    private void OpenThongTinDonHang(int position, ArrayList<Order> orders) {
        this.setOrder(orders.get(position));
        Intent intent = new Intent(ArrayOrder.this, OrderInfomation.class);
        startActivity(intent);
    }
    private void ResetSach(){
        Intent intent = new Intent(ArrayOrder.this, ArrayOrder.class);
        startActivity(intent);

    }


}

package com.example.BookShop.DBCart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.BookShop.ArrayCart;
import com.example.BookShop.LayOutAndLisView;
import com.example.BookShop.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Cart> list;

    public CartAdapter(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewAnhXa{
        TextView tengiohang, giagiohang;
        ImageView imgsach;
        Button soluonggiohang,btn_tru,btn_cong,btn_xoa;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewAnhXa viewAnhXa;
        if(view == null){
            viewAnhXa = new ViewAnhXa();
            // gán layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // gán dữ liệu cho view
            view = inflater.inflate(R.layout.element_book_cart,null);
            viewAnhXa.tengiohang=(TextView) view.findViewById(R.id.txt_tensach_cart);
            viewAnhXa.giagiohang=(TextView)view.findViewById(R.id.txt_gia_cart);
            viewAnhXa.imgsach=(ImageView) view.findViewById(R.id.img_view_cart);
            viewAnhXa.soluonggiohang=(Button)view.findViewById(R.id.btn_soluong_item);
            viewAnhXa.btn_tru=(Button)view.findViewById(R.id.btn_subtract);
            viewAnhXa.btn_cong=(Button)view.findViewById(R.id.btn_add);
            view.setTag(viewAnhXa);
        }else{
            viewAnhXa = (ViewAnhXa) view.getTag();
        }

       // lấy dữ liệu ra gán vào layout
        Cart cart= (Cart) getItem(position);
        viewAnhXa.tengiohang.setText(cart.getBookTitle());
        final DecimalFormat  decimalFormat=new DecimalFormat("###,###,###");
        viewAnhXa.giagiohang.setText(decimalFormat.format(cart.getGia())+"");
        viewAnhXa.imgsach.setImageResource(cart.getImgBook());
        viewAnhXa.soluonggiohang.setText(cart.getSoluong()+"");

        int sl= Integer.parseInt(viewAnhXa.soluonggiohang.getText().toString());
        if(sl>=10){
            viewAnhXa.btn_cong.setVisibility(View.INVISIBLE);
            viewAnhXa.btn_tru.setVisibility(View.VISIBLE);
        }
        else if(sl<=1){
            viewAnhXa.btn_cong.setVisibility(View.VISIBLE);
            viewAnhXa.btn_tru.setVisibility(View.INVISIBLE);
        }
        else if(sl>=1){
            viewAnhXa.btn_cong.setVisibility(View.VISIBLE);
            viewAnhXa.btn_tru.setVisibility(View.VISIBLE);
        }


        viewAnhXa.btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int so_lg_new= Integer.parseInt(viewAnhXa.soluonggiohang.getText().toString())+1;
                int slht= LayOutAndLisView.manggiohang.get(position).getSoluong();
                int giaht=LayOutAndLisView.manggiohang.get(position).getGia();
                LayOutAndLisView.manggiohang.get(position).setSoluong(so_lg_new);
                long giamoinhat=(giaht*so_lg_new)/slht;
                LayOutAndLisView.manggiohang.get(position).setGia((int) giamoinhat);
                NumberFormat formatter = new DecimalFormat("#,###");
                String formattedNumber = formatter.format(giamoinhat);
                viewAnhXa.giagiohang.setText(formattedNumber+"VNĐ");
                ArrayCart.PullDuLieu();// tổng tiền của tất cả item
                if(so_lg_new>9){
                    viewAnhXa.btn_cong.setVisibility(View.INVISIBLE);
                    viewAnhXa.btn_tru.setVisibility(View.VISIBLE);
                    viewAnhXa.soluonggiohang.setText(so_lg_new+"");
                }
                else {
                    viewAnhXa.btn_cong.setVisibility(View.VISIBLE);
                    viewAnhXa.btn_tru.setVisibility(View.VISIBLE);
                    viewAnhXa.soluonggiohang.setText(so_lg_new+"");
                }
            }
        });
        viewAnhXa.btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int so_lg_new= Integer.parseInt(viewAnhXa.soluonggiohang.getText().toString())-1;
                int slht= LayOutAndLisView.manggiohang.get(position).getSoluong();
                int giaht=LayOutAndLisView.manggiohang.get(position).getGia();
                LayOutAndLisView.manggiohang.get(position).setSoluong(so_lg_new);
                long giamoinhat=(giaht*so_lg_new)/slht;
                LayOutAndLisView.manggiohang.get(position).setGia((int) giamoinhat);
                DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                viewAnhXa.giagiohang.setText(giamoinhat+"VNĐ");
                ArrayCart.PullDuLieu();
                if(so_lg_new<2){
                    viewAnhXa.btn_cong.setVisibility(View.VISIBLE);
                    viewAnhXa.btn_tru.setVisibility(View.INVISIBLE);
                    viewAnhXa.soluonggiohang.setText(so_lg_new+"");
                }
                else {
                    viewAnhXa.btn_cong.setVisibility(View.VISIBLE);
                    viewAnhXa.btn_tru.setVisibility(View.VISIBLE);
                    viewAnhXa.soluonggiohang.setText(so_lg_new+"");
                }
            }
        });
        return view;
    }

}
package com.example.BookShop.DBOrder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.BookShop.R;

import java.text.DecimalFormat;
import java.util.List;

public class OrderAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<Order> list;

    public OrderAdapter(Context context, int layout, List<Order> list) {
        this.context = context;
        this.layout = layout;
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
            TextView tensach, giadonhang,ngaydat,soluong;
            ImageView imgsach;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            final OrderAdapter.ViewAnhXa viewAnhXa;
            if(view == null){
                viewAnhXa = new ViewAnhXa();
                // gán layout
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                // gán dữ liệu cho view
                view = inflater.inflate(R.layout.element_book_order,null);
                viewAnhXa.tensach=(TextView) view.findViewById(R.id.txt_tensach_book_order);
                viewAnhXa.giadonhang=(TextView)view.findViewById(R.id.txt_giasach_order);
                viewAnhXa.imgsach=(ImageView) view.findViewById(R.id.img_view_order);
                viewAnhXa.ngaydat=(TextView)view.findViewById(R.id.txt_ngaydat_order);
                viewAnhXa.soluong=(TextView)view.findViewById(R.id.txt_soluong_order);
                view.setTag(viewAnhXa);
            }else{
                viewAnhXa = (ViewAnhXa) view.getTag();
            }

            // lấy dữ liệu ra gán vào layout
            Order order= (Order) getItem(position);
            viewAnhXa.tensach.setText(order.getBookTitle());
            final DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
            viewAnhXa.giadonhang.setText(decimalFormat.format(order.getGia())+"");
            viewAnhXa.imgsach.setImageResource(order.getImgBook());
            viewAnhXa.soluong.setText(order.getSoluong()+"");
            String ngaydat = order.getNgayDat().substring(6,8);
            String thang = order.getNgayDat().substring(4,6);
            String nam = order.getNgayDat().substring(0,4);
            viewAnhXa.ngaydat.setText(ngaydat + "-" + thang + "-" + nam);

            return view;
        }
}

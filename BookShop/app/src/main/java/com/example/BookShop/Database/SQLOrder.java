package com.example.BookShop.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.BookShop.DBOrder.Order;

import java.util.ArrayList;

public class SQLOrder extends SQLiteOpenHelper {
    private static final String DatabaseName = "libraryorder";
    private static final String Table_Name4 = "orders";
    private static final String ID_order = "orderid";
    private static final String Account_order = "account";
    private static final String BookID_order = "bookid";
    private static final String BookTitle_order = "booktitle";
    private static final String NgatDat_oder = "Ngaydk";
    private static final String Soluong_oder = "Soluong";
    private static final String Gia_oder = "Gia";
    private static final String Hinhanh_oder = "Hinhanh";

    private static int version = 1;

    private SQLiteDatabase db;
    private Context context;
    private ContentValues values;
    public SQLOrder(Context context) {
        super(context, DatabaseName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table_log = " CREATE TABLE " + Table_Name4 + " ( " +
                ID_order + " integer primary key, " +
                Account_order + " TEXT, " +
                BookID_order + " integer, " +
                BookTitle_order + " TEXT, " +
                NgatDat_oder + " TEXT, " +
                Soluong_oder + " integer, " +
                Gia_oder + " integer, " +
                Hinhanh_oder + " integer)";
        db.execSQL(Create_Table_log);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name4);
        onCreate(db);
    }
    //----------------------------------------------------------------------------------------------------------------
    public void AddOrder(Order order) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(Account_order, order.getAccount());
        values.put(BookID_order, order.getBookID());
        values.put(BookTitle_order, order.getBookTitle());
        values.put(NgatDat_oder,order.getNgayDat());
        values.put(Soluong_oder,order.getSoluong());
        values.put(Gia_oder,order.getGia());
        values.put(Hinhanh_oder,order.getImgBook());
        db.insert(Table_Name4, null, values);
        db.close();
    }

    //------------xem nhật ký đăng ký mượn sách---------------------
    public ArrayList<Order> getAllOders(){
        ArrayList<Order> list = new ArrayList<>();
        String selectLog = "select * from " + Table_Name4;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectLog, null);
        if(cursor.moveToFirst()){
            do{
                list.add(new Order(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7)));
            }while (cursor.moveToNext());
        }else{
            list = null;
        }
        cursor.close();
        db.close();
        return list;
    }
    //------------Xóa nhật ký đăng ký mượn sách---------------------
    public void DeleteOrder(Order order) {
        db = this.getWritableDatabase();
        db.delete(Table_Name4, ID_order + " = ?",
                new String[] { String.valueOf(order.getOrderID()) });
        db.close();
    }
    //--- lấy order qua id
    public Order getOrder(int orderid){
        db = this.getWritableDatabase();
        Cursor cursor = db.query(Table_Name4, new String[]{ID_order,Account_order, BookID_order, BookTitle_order, NgatDat_oder, Soluong_oder, Gia_oder, Hinhanh_oder},
                ID_order + "=?", new String[]{String.valueOf(orderid)}, null, null, null,null);
        Order s = new Order();
        if(cursor.moveToFirst()){
            s.setOrderID(cursor.getInt(0));
            s.setAccount(cursor.getString(1));
            s.setBookID(cursor.getInt(2));
            s.setBookTitle(cursor.getString(3));
            s.setNgayDat(cursor.getString(4));
            s.setSoluong(cursor.getInt(5));
            s.setGia(cursor.getInt(6));
            s.setImgBook(cursor.getInt(7));
        }else{
            s = null;
        }
        cursor.close();
        db.close();
        return s;
    }
}

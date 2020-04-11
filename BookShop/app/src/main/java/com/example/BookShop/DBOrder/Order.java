package com.example.BookShop.DBOrder;

public class Order {
    private int OrderID;
    private String Account;
    private int BookID;
    private String BookTitle, NgayDat;
    private int Soluong,Gia, ImgBook;;

    public Order(){

    }
    public Order(String account, int bookID, String bookTitle, String ngayDat, int soluong, int gia, int imgBook) {
        Account = account;
        BookID = bookID;
        BookTitle = bookTitle;
        NgayDat = ngayDat;
        Soluong = soluong;
        Gia = gia;
        ImgBook = imgBook;
    }

    public Order(int orderID, String account, int bookID, String bookTitle, String ngayDat, int soluong, int gia, int imgBook) {
        OrderID = orderID;
        Account = account;
        BookID = bookID;
        BookTitle = bookTitle;
        NgayDat = ngayDat;
        Soluong = soluong;
        Gia = gia;
        ImgBook = imgBook;
    }

    public int getImgBook() {
        return ImgBook;
    }

    public void setImgBook(int imgBook) {
        ImgBook = imgBook;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
    }
}

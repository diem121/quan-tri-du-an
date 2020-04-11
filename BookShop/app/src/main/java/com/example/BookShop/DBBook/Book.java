package com.example.BookShop.DBBook;

public class Book {
    private int BookID;
    private String TenSach, TheLoai, TacGia, NamXB;
    private int ImgBook, SoLuong,Gia;


    public Book() {
    }

    public Book(int bookID, String tenSach, String theLoai, String tacGia, String namXB, int imgBook, int soLuong,int gia) {
        BookID = bookID;
        TenSach = tenSach;
        TheLoai = theLoai;
        TacGia = tacGia;
        NamXB = namXB;
        ImgBook = imgBook;
        SoLuong = soLuong;
        Gia=gia;
    }

    public Book(String tenSach, String theLoai, String tacGia, String namXB, int imgBook, int soLuong,int gia) {
        TenSach = tenSach;
        TheLoai = theLoai;
        TacGia = tacGia;
        NamXB = namXB;
        ImgBook = imgBook;
        SoLuong = soLuong;
        Gia=gia;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String theLoai) {
        TheLoai = theLoai;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getNamXB() {
        return NamXB;
    }

    public void setNamXB(String namXB) {
        NamXB = namXB;
    }

    public int getImgBook() {
        return ImgBook;
    }

    public void setImgBook(int imgBook) {
        ImgBook = imgBook;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}

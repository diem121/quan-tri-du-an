package com.example.BookShop.DBCart;

public class Cart {
    private String Account;
    private int BookID,Soluong,Gia;
    private String BookTitle;
    private int ImgBook;

    public Cart( String account, int bookID, int soluong, int gia, String bookTitle, int imgBook) {
        Account = account;
        BookID = bookID;
        Soluong = soluong;
        Gia = gia;
        BookTitle = bookTitle;
        ImgBook = imgBook;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public Cart() {
    }

    public int getImgBook() {
        return ImgBook;
    }

    public void setImgBook(int imgBook) {
        ImgBook = imgBook;
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

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }
}

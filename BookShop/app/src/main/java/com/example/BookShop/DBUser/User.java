package com.example.BookShop.DBUser;

public class User {
    private int UserID;
    private String Account, Fullname, Gmail, Password, Address , Tel;

    public User() {
    }

    public User(String account, String fullname, String gmail, String password, String address, String tel) {
        Account = account;
        Fullname = fullname;
        Gmail = gmail;
        Password = password;
        Address = address;
        Tel = tel;
    }

    public User(int userID, String account, String fullname, String gmail, String password, String address, String tel) {
        UserID = userID;
        Account = account;
        Fullname = fullname;
        Gmail = gmail;
        Password = password;
        Address = address;
        Tel = tel;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
}

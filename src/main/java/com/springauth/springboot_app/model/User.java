package com.springauth.springboot_app.model;

public class User {
    private String username;
    private String password;

    public User (String username,String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setUsername(String newUsername){
        this.username = newUsername;
    }
    public void setPassword(String newPassword){
        this.password= newPassword;
    }

    public String toString(){
        return "User{"+ "username:"+username + "password:"+password+"}";
    }

}
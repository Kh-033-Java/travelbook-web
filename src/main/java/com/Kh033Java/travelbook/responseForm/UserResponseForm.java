package com.Kh033Java.travelbook.responseForm;

import com.Kh033Java.travelbook.entity.Photo;

public class UserResponseForm {
    private String login;
    private Photo avatar;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Photo getAvatar() {
        return avatar;
    }

    public void setAvatar(Photo avatar) {
        this.avatar = avatar;
    }
}

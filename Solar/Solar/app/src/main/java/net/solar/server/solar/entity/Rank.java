package net.solar.server.solar.entity;

public class Rank {
    private String img;
    private String userName;
    private String time;

    public Rank(String img, String userName, String time) {
        this.img = img;
        this.userName = userName;
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

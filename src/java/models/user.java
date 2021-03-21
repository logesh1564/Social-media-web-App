/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Naveen Prasad
 */
public class user {
    private String username;
    private int userage;
    private Post posts[];
    private String profileimage;
    private String coverimage;
    private String about;
    private String address;
    private String phone;
    private String playerlevel;
    private String gamelevel;
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the userage
     */
    public int getUserage() {
        return userage;
    }

    /**
     * @param userage the userage to set
     */
    public void setUserage(int userage) {
        this.userage = userage;
    }

    /**
     * @return the posts
     */
    public Post[] getPosts() {
        return posts;
    }

    /**
     * @param posts the posts to set
     */
    public void setPosts(Post[] posts) {
        this.posts = posts;
    }

    /**
     * @return the profileimage
     */
    public String getProfileimage() {
        return profileimage;
    }

    /**
     * @param profileimage the profileimage to set
     */
    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    /**
     * @return the coverimage
     */
    public String getCoverimage() {
        return coverimage;
    }

    /**
     * @param coverimage the coverimage to set
     */
    public void setCoverimage(String coverimage) {
        this.coverimage = coverimage;
    }

    /**
     * @return the about
     */
    public String getAbout() {
        return about;
    }

    /**
     * @param about the about to set
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the playerlevel
     */
    public String getPlayerlevel() {
        return playerlevel;
    }

    /**
     * @param playerlevel the playerlevel to set
     */
    public void setPlayerlevel(String playerlevel) {
        this.playerlevel = playerlevel;
    }

    /**
     * @return the gamelevel
     */
    public String getGamelevel() {
        return gamelevel;
    }

    /**
     * @param gamelevel the gamelevel to set
     */
    public void setGamelevel(String gamelevel) {
        this.gamelevel = gamelevel;
    }
    
}

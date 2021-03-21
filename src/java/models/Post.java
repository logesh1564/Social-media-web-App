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
public class Post {
    private String username;
    private String caption;
    private byte byteArray[];
    private int likes;
    private int postNo;
    private String commentTableName;
    private String base64Image;
    private String profilepic;
    private Comment comments[];
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
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return the byteArray
     */
    public byte[] getByteArray() {
        return byteArray;
    }

    /**
     * @param byteArray the byteArray to set
     */
    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    /**
     * @return the likes
     */
    public int getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * @return the postNo
     */
    public int getPostNo() {
        return postNo;
    }

    /**
     * @param postNo the postNo to set
     */
    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    /**
     * @return the commentTableName
     */
    public String getCommentTableName() {
        return commentTableName;
    }

    /**
     * @param commentTableName the commentTableName to set
     */
    public void setCommentTableName(String commentTableName) {
        this.commentTableName = commentTableName;
    }

    /**
     * @return the base64Image
     */
    public String getBase64Image() {
        return base64Image;
    }

    /**
     * @param base64Image the base64Image to set
     */
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    /**
     * @return the comments
     */
    public Comment[] getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    /**
     * @return the profilepic
     */
    public String getProfilepic() {
        return profilepic;
    }

    /**
     * @param profilepic the profilepic to set
     */
    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }
    
}

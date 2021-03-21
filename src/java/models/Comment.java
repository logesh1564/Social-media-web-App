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
public class Comment {
    private String commentedUser;
    private String comment;
    private String authorname;
    private int postno;
    private String profileimg;
    /**
     * @return the commentedUser
     */
    public String getCommentedUser() {
        return commentedUser;
    }

    /**
     * @param commentedUser the commentedUser to set
     */
    public void setCommentedUser(String commentedUser) {
        this.commentedUser = commentedUser;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the authorname
     */
    public String getAuthorname() {
        return authorname;
    }

    /**
     * @param authorname the authorname to set
     */
    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    /**
     * @return the postno
     */
    public int getPostno() {
        return postno;
    }

    /**
     * @param postno the postno to set
     */
    public void setPostno(int postno) {
        this.postno = postno;
    }

    /**
     * @return the profileimg
     */
    public String getProfileimg() {
        return profileimg;
    }

    /**
     * @param profileimg the profileimg to set
     */
    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }
    
}

package model;

import Database.Const;

/**
 *
 * @author trham
 */
public class User {
    private int userId;
    private String fname;
    private String lname;
    private String username;
    private String password;

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public User(String fname, String lname, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId = Integer.parseInt(Const.USERS_ID);
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}

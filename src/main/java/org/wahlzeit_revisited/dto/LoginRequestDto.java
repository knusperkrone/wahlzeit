package org.wahlzeit_revisited.dto;

public class LoginRequestDto {

    /*
     * members
     */

    private String username;
    private String password;

    /*
     * constructor
     */

    public LoginRequestDto() {
        // default constructor
    }

    /*
     * getter/setter
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

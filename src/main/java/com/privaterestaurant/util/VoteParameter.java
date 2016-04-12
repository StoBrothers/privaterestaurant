package com.privaterestaurant.util;

import java.io.Serializable;

/**
 * VoteParameter use for store parameters in POST request with parameters:
 * {"login":"***","password":"***"}
 * 
 * @author Sergey Stotskiy
 *
 */
@SuppressWarnings("serial")
public class VoteParameter implements Serializable {

    private String logon;
    private String password;

    public String getLogon() {
        return logon;
    }

    public void setLogonName(String logon) {
        this.logon = logon;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordHash(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "VoteParameter [logonName=" + logon + ", passwordHash=" + password + "]";
    }

}

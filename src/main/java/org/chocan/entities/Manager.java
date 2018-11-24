package org.chocan.entities;

import org.chocan.common.AccountHelper;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private String username;
    private String password;
    private List<Provider> providers;

    public Manager(String username, String password, ArrayList<Provider> providers) {
        this.username = username;
        this.password = password;
        this.providers = providers;
    }

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

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> list){
        this.providers = list;
    }

    public boolean isValidPass(String Pass) {
        return password.equals(AccountHelper.generateHash( AccountHelper.KEY + Pass));
    }

}

package com.example.mertyemek.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MenuModel {

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public ArrayList<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<String> menuList) {
        this.menuList = menuList;
    }

    String MenuName;
    ArrayList<String> menuList;

}

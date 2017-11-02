package com.co.triblex.worldo_v01;

import java.util.ArrayList;

/**
 * Created by MSC95 on 02/11/2017.
 */

public class singleton {
    public static ArrayList<User> users = new ArrayList<>();


    singleton(){
        User admin = new User();
        admin.name = "Admin";
        admin.age = "100";
        admin.email = "Admin@admin.com";
        admin.username = "admin";
        admin.password = "1234";
        users.add(admin);
    }

}

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

        User temp = new User();
        admin.name = " ";
        admin.age = " ";
        admin.email = " ";
        admin.username = " ";
        admin.password = " ";

        users.add(admin);
        users.add(temp);
    }

}

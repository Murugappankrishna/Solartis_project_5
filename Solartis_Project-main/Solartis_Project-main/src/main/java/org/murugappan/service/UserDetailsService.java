package org.murugappan.service;
import org.murugappan.DAO.*;
import org.murugappan.model.UserCredentials;



import java.util.Scanner;

public class UserDetailsService {
    UserCredentials uc=new UserCredentials();

    Scanner ip=new Scanner(System.in);



    public String  fetchRole()  {
    	String userRoll;
        System.out.println("Enter Your Name");
        String name=ip.next();
        System.out.println("Enter Your Password");
        ip.next();
        System.out.println("ReEnter Your Password");
        String password=ip.next();
        System.out.println("Validating....");
        System.out.println("------------------");
        System.out.println("------------------");
        UserCredentialsImpl uci=new UserCredentialsImpl();
        userRoll= uci.fetchRole(name,Integer.toString(password.hashCode()));
        return userRoll;

    }
    public void createUser()  {

        HashService hs=new HashService(uc);
        System.out.println("Enter Your Name");
        uc.usercredentials.put("Name",ip.next());

        System.out.println("Enter Your Password");
        uc.usercredentials.put("Password",ip.next());
        System.out.println("Enter Your Roll");
        uc.usercredentials.put("Roll",ip.next());
        hs.hashPassword();
        UserCredentialsImpl uci=new UserCredentialsImpl();
        uci.createUserCredentials(uc.usercredentials.get("Name"),uc.usercredentials.get("Password"),uc.usercredentials.get("Roll"));
    }

}

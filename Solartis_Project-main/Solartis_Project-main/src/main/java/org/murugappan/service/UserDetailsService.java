package org.murugappan.service;

import org.murugappan.DAO.UserCredentialsImpl;
import org.murugappan.model.UserCredentials;

import java.util.Scanner;

public class UserDetailsService {
    UserCredentials credentials = new UserCredentials();
    HashService hashService = new HashService(credentials);
    UserCredentialsImpl userCredentialsImplementation = new UserCredentialsImpl();
    Scanner input = new Scanner(System.in);

    public String fetchRole() {
        String userRoll;
        System.out.println("Enter Your Name");
        String name = input.next();
        System.out.println("Enter Your Password");
        input.next();
        System.out.println("ReEnter Your Password");
        String password = input.next();
        System.out.println("Validating....");
        System.out.println("------------------");
        System.out.println("------------------");
        UserCredentialsImpl userCredentialImplementation = new UserCredentialsImpl();
        userRoll = userCredentialImplementation.fetchRole(name, Integer.toString(password.hashCode()));
        return userRoll;

    }

    public void createUser() {


        System.out.println("Enter Your Name");
        credentials.userCredentials.put("Name", input.next());

        System.out.println("Enter Your Password");
        credentials.userCredentials.put("Password", input.next());
        System.out.println("Enter Your Roll");
        credentials.userCredentials.put("Roll", input.next());
        hashService.hashPassword();

        userCredentialsImplementation.createUserCredentials(credentials.userCredentials.get("Name"), credentials.userCredentials.get("Password"), credentials.userCredentials.get("Roll"));
    }

}

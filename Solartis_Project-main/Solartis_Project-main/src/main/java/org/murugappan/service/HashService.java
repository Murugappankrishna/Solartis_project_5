package org.murugappan.service;
import org.murugappan.model.*;

public class HashService {
    UserCredentials uc;
    public HashService(UserCredentials uc) {
        this.uc = uc;
    }
    public void hashPassword(){
        uc.usercredentials.put("Password", Integer.toString((uc.usercredentials.get("Password")).hashCode()));
    }


}

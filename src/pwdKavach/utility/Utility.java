/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwdKavach.utility;

import java.util.Random;

/**
 *
 * @author Administrator
 */
public class Utility {

    //Generate password
    public static String generatePassword() {
        String SALTCHARS = "azertyuiopqsdfghjklmwxcvbnAZERTYUIOPQSDFGHJKLMWXCVBN0123456789+-*/=&\".-_+/*,'#";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
//    public static void showOrHidePassword(String password, ){
//        
//        .getIcon().toString();
//    }

}

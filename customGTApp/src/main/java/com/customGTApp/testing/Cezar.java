package com.customGTApp.testing;

import java.util.Date;

public class Cezar {
    private String pass;

    public Cezar(String pass){
        this.pass = pass;
    }

    public String encryptPass(PassType passType){
        char[] chars = this.pass.toCharArray();
        switch (passType){
            case WEAK -> {
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == 'z')
                        chars[i] = 'a';
                    if (chars[i] == 'Z')
                        chars[i] = 'A';
                    else chars[i] += 1;
                }
            }
            case AVERAGE -> {
                for(int i = 0; i < chars.length; i++){
                    if(chars[i] >= 'w' && Character.isLowerCase(chars[i]))
                        chars[i] = (char) (chars[i] - 22);
                    else if(chars[i] >= 'W' && Character.isUpperCase(chars[i]))
                        chars[i] = (char) (chars[i] - 22);
                    else chars[i] += 4;
                }

            }
            case COMPLEX -> {
                Date date = new Date();
                for(int i = 0; i < chars.length; i++){
                    for(int j = 0; j < date.getDay(); j++){
                        if(chars[i] + 1 > 'z' || chars[i] + 1 > 'Z')
                            chars[i] -= 26;
                        else chars[i] += 1;
                    }
                }
            }
        }
        return String.valueOf(chars);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }



}

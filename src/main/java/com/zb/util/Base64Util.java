package com.zb.util;

import java.util.Base64;

public class Base64Util {
    public static long decode(String s){
        return Long.parseLong(new String(Base64.getDecoder().decode(s)));
    }

    public static String encode(long l){

        return Base64.getEncoder().encodeToString(String.valueOf(l).getBytes());
    }
}

package com.baizhi.test;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by lenovo on 2017/6/20.
 */
public class TestShiro {
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("io8e","*9sj", 1024);
        //f1546e31924e82fa09f1372a43f9f809
        System.out.println(md5Hash.toHex());
    }
}

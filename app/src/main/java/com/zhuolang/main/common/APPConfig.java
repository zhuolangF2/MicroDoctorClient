package com.zhuolang.main.common;

/**
 * Created by jat on 2016/10/31.
 */

public class APPConfig {
    private static  String base_url="http://192.168.23.1:8080/MicroDoctorServer/";
    //    private static  String base_url="http://192.168.43.123:8080/MicroDoctorServer/";
//    private static  String base_url="http://192.168.168.102:8080/MicroDoctorServer/";
    public static  String login = base_url+"login_user";
    public static  String showDoctor = base_url+"findByType_user";
    public static  String register=base_url+"add_user";
}
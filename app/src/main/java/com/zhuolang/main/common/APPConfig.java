package com.zhuolang.main.common;

/**
 * Created by jat on 2016/10/31.
 */

public class APPConfig {
//    private static String base_url="http://192.168.23.1:8080/MicroDoctorServer/";
//    private static String base_url="http://192.168.43.123:8080/MicroDoctorServer/";
//    private static String base_url="http://192.168.168.102:8080/MicroDoctorServer/";
    private static String base_url = "http://27.45.40.22:80/MicroDoctorServer/";
    public static String addAppointment = base_url + "add_appointment";
    public static String login = base_url + "login_user";
    public static String showDoctor = base_url + "findByType_user";
    public static String register = base_url + "add_user";
    public static String findUserByPhone = base_url + "findByPhone_user";
    public static String updatePassword = base_url + "update_password";
    public static String updateUser=base_url+"update_user";

    public static String IS_LOGIN = "is_login";
    public static String ACCOUNT = "account";
    public static String USERDATA = "userData";//获取当前用户的key
}
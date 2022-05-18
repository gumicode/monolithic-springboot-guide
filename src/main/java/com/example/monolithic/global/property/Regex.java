package com.example.monolithic.global.property;

public class Regex {
    public static final String USERNAME = "^[a-zA-Z0-9]{6,30}$"; // 아이디 정규식 영어 포함 6~30 글자
    public static final String EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"; // EMAIL 정규식
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[-/:;()$&@\".,?!'#%^*+=_|~<>]).{8,30}$"; // 8~30 자리 영문자, 숫자, 특수 문자 1개 이상 반드시 포함
}
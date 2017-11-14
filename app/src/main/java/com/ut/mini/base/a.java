package com.ut.mini.base;

/* UTMCConstants */
public class a {
    public static String a = "http://adash.m.taobao.com/rest/sur";
    private static String b = "http://adash.m.taobao.com/rest/sur";

    public static synchronized String a() {
        String str;
        synchronized (a.class) {
            str = b;
        }
        return str;
    }

    public static synchronized void a(String str) {
        synchronized (a.class) {
            b = str;
        }
    }

    public static String b() {
        return "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK";
    }
}

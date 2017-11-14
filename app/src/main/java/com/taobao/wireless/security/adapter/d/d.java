package com.taobao.wireless.security.adapter.d;

public final class d {

    public enum a {
        ;
        
        public static int[] a;

        static {
            b = new int[]{1, 2, 3, 4};
            a = a();
        }

        public static int[] a() {
            return (int[]) b.clone();
        }
    }
}

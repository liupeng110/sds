package com.taobao.wireless.security.adapter.i;

public final class b {

    public enum a {
        ;
        
        public static int[] a;

        static {
            b = new int[]{1, 2, 3, 4, 5, 6};
            a = a();
        }

        public static int[] a() {
            return (int[]) b.clone();
        }
    }

    public enum b {
        ;
        
        public static int[] a;

        static {
            b = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
            a = a();
        }

        public static int[] a() {
            return (int[]) b.clone();
        }
    }
}

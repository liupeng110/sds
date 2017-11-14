package com.c.a;

import c.a.n;
import java.util.Locale;

/* Gender */
public enum b {
    Male(1) {
        public String toString() {
            return String.format(Locale.US, "Male:%d", new Object[]{Integer.valueOf(this.value)});
        }
    },
    Female(2) {
        public String toString() {
            return String.format(Locale.US, "Female:%d", new Object[]{Integer.valueOf(this.value)});
        }
    },
    Unknown(0) {
        public String toString() {
            return String.format(Locale.US, "Unknown:%d", new Object[]{Integer.valueOf(this.value)});
        }
    };
    
    public int value;

    private b(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static b getGender(int i) {
        switch (i) {
            case 1:
                return Male;
            case 2:
                return Female;
            default:
                return Unknown;
        }
    }

    public static n transGender(b bVar) {
        switch (a()[bVar.ordinal()]) {
            case 1:
                return n.MALE;
            case 2:
                return n.FEMALE;
            default:
                return n.UNKNOWN;
        }
    }
}

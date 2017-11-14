package com.b.a.b;

import java.math.BigDecimal;

/* LazilyParsedNumber */
public final class f extends Number {
    private final String a;

    public f(String str) {
        this.a = str;
    }

    public int intValue() {
        try {
            return Integer.parseInt(this.a);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.a);
            } catch (NumberFormatException e2) {
                return new BigDecimal(this.a).intValue();
            }
        }
    }

    public long longValue() {
        try {
            return Long.parseLong(this.a);
        } catch (NumberFormatException e) {
            return new BigDecimal(this.a).longValue();
        }
    }

    public float floatValue() {
        return Float.parseFloat(this.a);
    }

    public double doubleValue() {
        return Double.parseDouble(this.a);
    }

    public String toString() {
        return this.a;
    }
}

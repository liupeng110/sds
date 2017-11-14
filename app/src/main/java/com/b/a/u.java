package com.b.a;

/* LongSerializationPolicy */
public enum u {
    DEFAULT {
        public l serialize(Long l) {
            return new q((Number) l);
        }
    },
    STRING {
        public l serialize(Long l) {
            return new q(String.valueOf(l));
        }
    };

    public abstract l serialize(Long l);
}

package com.sds.android.sdk.lib.c;

import java.util.Map.Entry;

/* Pair */
class a<K, V> implements Entry<K, V> {
    private K a;
    private V b;

    public a(K k, V v) {
        this.a = k;
        this.b = v;
    }

    public K getKey() {
        return this.a;
    }

    public V getValue() {
        return this.b;
    }

    public V setValue(V v) {
        V v2 = this.b;
        this.b = v;
        return v2;
    }
}

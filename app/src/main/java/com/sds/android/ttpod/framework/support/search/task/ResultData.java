package com.sds.android.ttpod.framework.support.search.task;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.lang.reflect.Array;

public class ResultData implements Parcelable {
    public static final Creator<ResultData> CREATOR = new Creator<ResultData>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ResultData a(Parcel parcel) {
            ResultData resultData = new ResultData();
            resultData.a = parcel.readString();
            resultData.b = parcel.readString();
            resultData.c = parcel.readString();
            resultData.d = parcel.readString();
            if (parcel.readByte() != (byte) 0) {
                Parcelable[] readParcelableArray = parcel.readParcelableArray(Item.class.getClassLoader());
                resultData.e = (Item[]) ResultData.b(readParcelableArray, readParcelableArray.length, Item[].class);
            }
            return resultData;
        }

        public ResultData[] a(int i) {
            return new ResultData[i];
        }
    };
    protected String a;
    protected String b;
    protected String c;
    protected String d;
    protected Item[] e;

    public static class Item implements Parcelable, Comparable<Item> {
        public static final Creator<Item> CREATOR = new Creator<Item>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public Item a(Parcel parcel) {
                return new Item(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
            }

            public Item[] a(int i) {
                return new Item[i];
            }
        };
        protected String a;
        protected String b;
        protected String c;
        protected int d;

        public /* synthetic */ int compareTo(Object obj) {
            return a((Item) obj);
        }

        public Item(String str, String str2, String str3, int i) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public String b() {
            return this.a;
        }

        public String c() {
            return this.b;
        }

        public String d() {
            return this.c;
        }

        public int a(Item item) {
            return this.d - item.d;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a != null ? this.a : "");
            parcel.writeString(this.b != null ? this.b : "");
            parcel.writeString(this.c != null ? this.c : "");
            parcel.writeInt(this.d);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        if (!TextUtils.isEmpty(this.b)) {
            stringBuilder.append(this.b);
        }
        if (!TextUtils.isEmpty(this.a)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('-');
            }
            stringBuilder.append(this.a);
        }
        if (!TextUtils.isEmpty(this.c)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('/');
            }
            stringBuilder.append(this.c);
        }
        if (stringBuilder.length() == 0 && !TextUtils.isEmpty(this.d)) {
            stringBuilder.append(this.d);
        }
        if (this.e != null && 1 == this.e.length && "trc".equals(this.e[0].b())) {
            stringBuilder.append(" TRC");
        }
        return stringBuilder.toString();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public Item[] c() {
        return this.e;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a != null ? this.a : "");
        parcel.writeString(this.b != null ? this.b : "");
        parcel.writeString(this.c != null ? this.c : "");
        parcel.writeString(this.d != null ? this.d : "");
        parcel.writeByte((byte) (this.e != null ? 1 : 0));
        if (this.e != null) {
            parcel.writeParcelableArray(this.e, i);
        }
    }

    private static <T, U> T[] b(U[] uArr, int i, Class<? extends T[]> cls) {
        if (i >= 0) {
            return a(uArr, 0, i, cls);
        }
        throw new NegativeArraySizeException(Integer.toString(i));
    }

    private static <T, U> T[] a(U[] uArr, int i, int i2, Class<? extends T[]> cls) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = uArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        int min = Math.min(i3, length - i);
        Object[] objArr = (Object[]) Array.newInstance(cls.getComponentType(), i3);
        System.arraycopy(uArr, i, objArr, 0, min);
        return objArr;
    }
}

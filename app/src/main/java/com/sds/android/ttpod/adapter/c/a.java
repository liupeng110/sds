package com.sds.android.ttpod.adapter.c;

import android.app.Activity;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.BaseAdapter;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.PinyinUtils.Pinyin;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* BaseSearchAdapter */
public abstract class a extends BaseAdapter {
    protected static String c;
    protected static String d;
    protected static ForegroundColorSpan e;
    protected Activity b;
    protected List<a> f = new ArrayList();
    protected List<a> g = new ArrayList();
    protected List<a> h = new ArrayList();

    /* BaseSearchAdapter */
    public interface a {
        int[] c();
    }

    /* BaseSearchAdapter */
    private class b {
        final /* synthetic */ a a;
        private int b;
        private int c;
        private int d;

        public b(a aVar, int i, int i2, int i3) {
            this.a = aVar;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }
    }

    public a(Activity activity) {
        this.b = activity;
        e = new ForegroundColorSpan(this.b.getResources().getColor(R.color.text_search_highlight_color));
    }

    public void a(MediaItem mediaItem) {
    }

    public void e() {
        c = "";
        d = "";
        this.h = this.f;
        notifyDataSetChanged();
    }

    public int a(String str) {
        int i = 0;
        if (this.f == null) {
            return 0;
        }
        c = d;
        d = str;
        if (TextUtils.isEmpty(str)) {
            this.h.clear();
        } else {
            int length = d.length();
            if (c != null) {
                i = c.length();
            }
            if (length <= i || this.h != this.g) {
                this.h = this.g;
                this.h.clear();
                for (a aVar : this.f) {
                    if (aVar.c() != null) {
                        this.h.add(aVar);
                    }
                }
            } else {
                for (length = this.h.size() - 1; length >= 0; length--) {
                    if (((a) this.h.get(length)).c() == null) {
                        this.h.remove(length);
                    }
                }
            }
        }
        notifyDataSetChanged();
        return this.h.size();
    }

    private boolean a(char[] cArr, char[] cArr2, int i) {
        int i2 = i + 1;
        int i3 = 1;
        while (i3 < cArr.length && i2 < cArr2.length) {
            if (cArr[i3] != cArr2[i2]) {
                return false;
            }
            i3++;
            i2++;
        }
        return true;
    }

    private int a(char[] cArr, char[][] cArr2, int i, int[] iArr) {
        int length = cArr.length;
        Stack stack = new Stack();
        int i2 = 0;
        int i3 = 0;
        while (i < cArr2.length) {
            char[] cArr3 = cArr2[i];
            if (cArr3 == null) {
                i2 += iArr[i];
            } else if (cArr3[0] != cArr[i3]) {
                if (stack.empty()) {
                    i3 = 0;
                    break;
                }
                b bVar = (b) stack.pop();
                i = bVar.b;
                i3 = bVar.c + 1;
                i2 = bVar.d;
            } else if (!a(cArr3, cArr, i3)) {
                i3++;
                i2 += iArr[i];
            } else if (cArr3.length + i3 < length) {
                i2 += iArr[i];
                stack.push(new b(this, i, i3, i2));
                i3 += cArr3.length;
            } else if (iArr[i] > 1) {
                i2 += length - i3;
                i3 = 1;
            } else {
                i2++;
                i3 = 1;
            }
            i++;
        }
        i3 = 0;
        if (i3 != 0) {
            return i2;
        }
        return 0;
    }

    protected boolean a(Pinyin pinyin, int[] iArr) {
        if (TextUtils.isEmpty(d) || pinyin == null) {
            return false;
        }
        char[] toCharArray = d.toCharArray();
        char[][] t9Key = pinyin.getT9Key();
        int[] placeHolder = pinyin.getPlaceHolder();
        int i = 0;
        for (int i2 = 0; i2 < t9Key.length; i2++) {
            if (t9Key[i2] != null) {
                int a = a(toCharArray, t9Key, i2, placeHolder);
                if (a > 0) {
                    iArr[1] = i;
                    iArr[2] = a;
                    return true;
                }
            }
            i += placeHolder[i2];
        }
        return false;
    }

    protected boolean a(String str, int[] iArr) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(d)) {
            return false;
        }
        int indexOf = str.indexOf(d);
        if (indexOf >= 0) {
            iArr[1] = indexOf;
            iArr[2] = d.length();
        }
        if (indexOf < 0) {
            return false;
        }
        return true;
    }
}

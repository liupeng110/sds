package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.util.SparseArrayCompat;
import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.widget.CheckedTextView;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AutoScrollableTextView extends CheckedTextView {
    private boolean a = false;
    private String b = null;
    private SpannableStringBuilder c = new SpannableStringBuilder();
    private Map<String, int[]> d = new HashMap();
    private ArrayList<Object> e = new ArrayList();
    private SparseIntArray f = new SparseIntArray();
    private SparseArrayCompat<ArrayList<Object>> g = new SparseArrayCompat();

    public AutoScrollableTextView(Context context) {
        super(context);
    }

    public AutoScrollableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public AutoScrollableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AutoScrollableTextView);
        this.a = obtainStyledAttributes.getBoolean(R.styleable.AutoScrollableTextView_autoScrollEnable, this.a);
        obtainStyledAttributes.recycle();
    }

    public void a(CharSequence... charSequenceArr) {
        if (charSequenceArr != null && charSequenceArr.length != 0) {
            int i = 0;
            while (i < charSequenceArr.length - 1) {
                int i2 = i + 1;
                int[] iArr = (int[]) this.d.get(String.valueOf(charSequenceArr[i]));
                if (iArr != null) {
                    CharSequence charSequence = charSequenceArr[i2];
                    if (charSequence == null) {
                        charSequence = "";
                    }
                    this.c.replace(iArr[0], iArr[1], charSequence);
                    int length = charSequence.length() + iArr[0];
                    int i3 = length - iArr[1];
                    iArr[1] = length;
                    for (int[] iArr2 : this.d.values()) {
                        if (iArr2[0] > iArr[0]) {
                            iArr2[0] = iArr2[0] + i3;
                            iArr2[1] = iArr2[1] + i3;
                        }
                    }
                }
                i = i2 + 1;
            }
            setText(this.c);
        }
    }

    public boolean a(String str) {
        return this.d.containsKey(str);
    }

    public void setFormatString(String str) {
        if (!TextUtils.equals(str, this.b)) {
            this.d.clear();
            this.e.clear();
            this.f.clear();
            this.g.clear();
            this.b = str;
            SpannableStringBuilder spannableStringBuilder = this.c;
            spannableStringBuilder.clearSpans();
            spannableStringBuilder.clear();
            if (str != null) {
                int length = "$[".length();
                int i = 0;
                int i2 = 0;
                while (i2 < str.length()) {
                    i2 = str.indexOf("$[", i2);
                    if (i2 >= 0) {
                        spannableStringBuilder.append(str.substring(i, i2));
                        int i3 = i2 + length;
                        i = str.indexOf("]", i3);
                        if (i <= i3) {
                            break;
                        }
                        String trim;
                        i2 = i + 1;
                        Object trim2 = str.substring(i3, i).trim();
                        int indexOf = trim2.indexOf(58);
                        if (indexOf > 0) {
                            trim = trim2.substring(indexOf + 1).trim();
                            trim2 = trim2.substring(0, indexOf).trim();
                        } else {
                            trim = null;
                        }
                        indexOf = spannableStringBuilder.length();
                        if (trim != null) {
                            if ("Color".equals(trim2)) {
                                trim2 = new ForegroundColorSpan(m.c(trim, getCurrentTextColor()));
                            } else if ("Size".equals(trim2)) {
                                int textSize = (int) getTextSize();
                                AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(m.a(m.b(trim, textSize), textSize));
                            } else if ("Align".equals(trim2)) {
                                trim2 = new Standard(m.a(trim, Alignment.ALIGN_NORMAL));
                            } else {
                                trim2 = null;
                            }
                            if (trim2 != null) {
                                this.e.add(trim2);
                                this.f.put(trim2.hashCode(), indexOf);
                                ArrayList arrayList = (ArrayList) this.g.get(indexOf);
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                    this.g.put(indexOf, arrayList);
                                }
                                arrayList.add(trim2);
                            }
                        } else {
                            int[] iArr = (int[]) this.d.get(trim2);
                            if (iArr == null) {
                                iArr = new int[2];
                                this.d.put(trim2, iArr);
                            }
                            iArr[0] = indexOf;
                            iArr[1] = indexOf;
                        }
                        i = i2;
                        i2 = i3;
                    } else {
                        break;
                    }
                }
            }
            a();
            setText(this.c);
        }
    }

    private void a() {
        this.c.clearSpans();
        SparseIntArray sparseIntArray = new SparseIntArray();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            int i = this.f.get(next.hashCode());
            Class cls = next.getClass();
            int hashCode = cls.hashCode();
            int i2 = sparseIntArray.get(hashCode, -1);
            if (i2 >= 0) {
                sparseIntArray.delete(i2);
                ArrayList arrayList = (ArrayList) this.g.get(i2);
                if (arrayList != null) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        next = it2.next();
                        if (next.getClass() == cls) {
                            break;
                        }
                    }
                }
                next = null;
                if (next != null) {
                    this.c.setSpan(next, i2, i, 18);
                }
            }
            sparseIntArray.put(hashCode, i);
        }
        int size = sparseIntArray.size();
        for (int i3 = 0; i3 < size; i3++) {
            i = sparseIntArray.valueAt(i3);
            Iterator it3 = ((ArrayList) this.g.get(i)).iterator();
            while (it3.hasNext()) {
                Object next2 = it3.next();
                if (next2 != null) {
                    this.c.setSpan(next2, i, this.c.length(), 18);
                }
            }
        }
    }

    public boolean isFocused() {
        return this.a || super.isFocused();
    }

    public void setAutoScrollable(boolean z) {
        this.a = z;
    }
}

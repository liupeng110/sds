package com.sds.android.ttpod.component.emoticons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* EmoticonConversionUtil */
public final class b {
    private static b a;
    private HashMap<String, String> b = new HashMap();
    private List<a> c = new ArrayList();
    private List<List<a>> d = new ArrayList();
    private int e;

    private b() {
    }

    public List<List<a>> a() {
        return this.d;
    }

    public static b b() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public SpannableString a(Context context, CharSequence charSequence) {
        a(context);
        if (charSequence instanceof SpannableString) {
            charSequence = (SpannableString) charSequence;
        } else if (charSequence == null || charSequence.length() <= 0) {
            return null;
        } else {
            charSequence = new SpannableString(charSequence);
        }
        try {
            a(context, charSequence, Pattern.compile("\\[[^\\]]+\\]", 2), 0);
            return charSequence;
        } catch (Throwable th) {
            th.printStackTrace();
            return charSequence;
        }
    }

    public SpannableString a(Context context, int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeResource, 25, 25, true);
        decodeResource.recycle();
        createScaledBitmap.setDensity(240);
        ImageSpan imageSpan = new ImageSpan(context, createScaledBitmap, 1);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(imageSpan, 0, str.length(), 33);
        return spannableString;
    }

    private void a(Context context, SpannableString spannableString, Pattern pattern, int i) throws Exception {
        Matcher matcher = pattern.matcher(spannableString);
        while (matcher.find()) {
            String group = matcher.group();
            if (matcher.start() >= i) {
                String str = (String) this.b.get(group);
                if (TextUtils.isEmpty(str)) {
                    continue;
                } else {
                    int identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
                    if (identifier != 0) {
                        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), identifier);
                        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeResource, this.e, this.e, true);
                        decodeResource.recycle();
                        createScaledBitmap.setDensity(240);
                        ImageSpan imageSpan = new ImageSpan(createScaledBitmap);
                        int length = group.length() + matcher.start();
                        spannableString.setSpan(imageSpan, matcher.start(), length, 17);
                        if (length < spannableString.length()) {
                            a(context, spannableString, pattern, length);
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    public void a(Context context) {
        if (this.c.size() <= 0) {
            this.e = (int) (25.0f * context.getResources().getDisplayMetrics().density);
            a(b(context), context);
        }
    }

    private void a(List<String> list, Context context) {
        if (list != null) {
            try {
                for (String split : list) {
                    String[] split2 = split.split(SelectCountryActivity.SPLITTER);
                    String substring = split2[0].substring(0, split2[0].lastIndexOf("."));
                    this.b.put(split2[1], substring);
                    int identifier = context.getResources().getIdentifier(substring, "drawable", context.getPackageName());
                    if (identifier != 0) {
                        a aVar = new a();
                        aVar.a(identifier);
                        aVar.a(split2[1]);
                        aVar.b(substring);
                        this.c.add(aVar);
                    }
                }
                int ceil = (int) Math.ceil((double) (((float) this.c.size()) / 27.0f));
                for (int i = 0; i < ceil; i++) {
                    this.d.add(a(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<a> a(int i) {
        int i2 = i * 27;
        int i3 = i2 + 27;
        if (i3 > this.c.size()) {
            i3 = this.c.size();
        }
        List<a> arrayList = new ArrayList();
        arrayList.addAll(this.c.subList(i2, i3));
        if (arrayList.size() < 27) {
            for (i3 = arrayList.size(); i3 < 27; i3++) {
                arrayList.add(new a());
            }
        }
        if (arrayList.size() == 27) {
            a aVar = new a();
            aVar.a((int) R.drawable.xml_musiccircle_emoticons_delete);
            arrayList.add(aVar);
        }
        return arrayList;
    }

    public static List<String> b(Context context) {
        try {
            List<String> arrayList = new ArrayList();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open("emoticons.data"), "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return arrayList;
                }
                arrayList.add(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

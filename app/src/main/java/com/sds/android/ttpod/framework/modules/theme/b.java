package com.sds.android.ttpod.framework.modules.theme;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.ViewCompat;
import android.util.StateSet;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* ThemeFramework */
public class b {

    /* ThemeFramework */
    public static abstract class e {
        public static final String[] a = new String[]{"LEFT_RIGHT", "TOP_BOTTOM", "RIGHT_LEFT", "BOTTOM_TOP", "TL_BR", "BL_TR", "TR_BL", "BR_TL"};
        protected String b;
        protected Drawable c;
        protected Drawable d;
        protected Drawable e;
        protected int f;
        protected int g;
        protected int h;
        protected int i;

        public e(String str) {
            this.b = str;
        }

        public void a() {
            this.c = null;
            this.d = null;
            this.e = null;
        }

        public Drawable c() {
            return this.c;
        }

        public Drawable d() {
            return this.d;
        }

        public int e() {
            return this.f;
        }

        public int f() {
            return this.g;
        }

        public int g() {
            return this.h;
        }

        public int h() {
            return this.i;
        }

        public Object b() {
            if (this.e == null) {
                if (this.d == null || this.c == null) {
                    this.e = j();
                } else {
                    this.e = i();
                }
            }
            return this.e.getConstantState().newDrawable();
        }

        private Drawable i() {
            Drawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, this.d);
            stateListDrawable.addState(new int[]{16842913}, this.d);
            stateListDrawable.addState(StateSet.WILD_CARD, this.c);
            return stateListDrawable;
        }

        private Drawable j() {
            if (this.c != null) {
                return this.c;
            }
            if (this.d != null) {
                return this.d;
            }
            return null;
        }
    }

    /* ThemeFramework */
    public static class a extends e {
        private HashMap<String, e> j = new HashMap();

        public a(String str) {
            super(str);
        }

        public void a() {
            super.a();
            for (e eVar : this.j.values()) {
                if (eVar != null) {
                    eVar.a();
                }
            }
            this.j.clear();
            this.j = null;
        }

        public void a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            int eventType = xmlPullParser.getEventType();
            c cVar = null;
            do {
                String name = xmlPullParser.getName();
                String attributeValue = xmlPullParser.getAttributeValue(null, "ID");
                if (2 == eventType) {
                    if ("Color".equals(name)) {
                        cVar = new c(attributeValue);
                        cVar.a(xmlPullParser);
                    }
                } else if (3 == eventType) {
                    if (!"Color".equals(xmlPullParser.getName())) {
                        return;
                    }
                    if (cVar != null) {
                        this.j.put(cVar.b, cVar);
                    }
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
        }

        public Object a(String str) {
            e eVar = (e) this.j.get(str);
            if (eVar != null) {
                return eVar.b();
            }
            return null;
        }

        public e b(String str) {
            return (e) this.j.get(str);
        }
    }

    /* ThemeFramework */
    public static class b {
        private List<String> a = new ArrayList();
        private a b;
        private d c = new d();
        private j d;
        private HashMap<String, f> e = new HashMap();
        private com.sds.android.sdk.lib.util.b f = new com.sds.android.sdk.lib.util.b();

        b(j jVar) {
            this.d = jVar;
        }

        static b a(j jVar) {
            InputStream inputStream;
            b bVar = new b(jVar);
            if (jVar == null || !jVar.g()) {
                inputStream = null;
            } else {
                try {
                    inputStream = new ByteArrayInputStream(jVar.d("/theme.xml"));
                } catch (Exception e) {
                    inputStream = null;
                }
                bVar.f();
            }
            if (inputStream == null) {
                bVar.e("theme");
                inputStream = d("theme/theme.xml");
            }
            if (inputStream != null) {
                bVar.c.a(inputStream);
                try {
                    inputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return bVar;
        }

        private static InputStream d(String str) {
            try {
                return BaseApplication.e().getResources().getAssets().open(str);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void a() {
            if (this.b != null) {
                this.b.a();
                this.b = null;
            }
            if (this.c != null) {
                this.c.b();
                this.c = null;
            }
            this.a.clear();
            this.a = null;
            this.e.clear();
            this.e = null;
            this.d.i();
            this.d = null;
            System.gc();
        }

        Bitmap a(String str) {
            Bitmap c;
            Bitmap bitmap = null;
            if (this.d != null) {
                c = this.d.c('/' + str);
            } else {
                c = bitmap;
            }
            if (c != null) {
                return c;
            }
            InputStream d = d("theme/" + str);
            try {
                bitmap = this.f.a(d);
                if (d == null) {
                    return bitmap;
                }
                try {
                    d.close();
                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                    return bitmap;
                }
            } catch (Throwable th) {
                if (d != null) {
                    try {
                        d.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }

        Object a(String str, String str2) {
            a e = e();
            if (e == null) {
                return null;
            }
            return e.a(e(str, str2));
        }

        Drawable b(String str, String str2) {
            String f = f(str, str2);
            f fVar = (f) this.e.get(f);
            if (fVar == null) {
                fVar = f.a(this, f);
                if (fVar != null) {
                    this.e.put(f, fVar);
                }
            }
            if (fVar != null) {
                return fVar.i().getConstantState().newDrawable();
            }
            return null;
        }

        ColorDrawable b() {
            return this.c.c();
        }

        ColorDrawable c() {
            return this.c.d();
        }

        ColorDrawable d() {
            return this.c.e();
        }

        boolean b(String str) {
            return this.a != null && this.a.contains(str);
        }

        private String e(String str, String str2) {
            if (str.equals(ThemeElement.PANEL_HOME)) {
                if (str2.equals("Text")) {
                    return "ContentText";
                }
                if (str2.equals("SubText")) {
                    return "SubContentText";
                }
                if (str2.equals("Background")) {
                    return "Content";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_TOP_BAR)) {
                if (str2.equals("Text")) {
                    return "TitleText";
                }
                if (str2.equals("SubText")) {
                    return "SubTitleText";
                }
                if (str2.equals("Background")) {
                    return ThemeElement.PANEL_TOP_BAR;
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SUB_BAR)) {
                if (str2.equals("Text")) {
                    return "TitleText";
                }
                if (str2.equals("SubText")) {
                    return "SubTitleText";
                }
                if (str2.equals("Background")) {
                    return ThemeElement.PANEL_SUB_BAR;
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SONG_LIST_ITEM)) {
                if (str2.equals("Text")) {
                    return "ContentText";
                }
                if (str2.equals("SubText")) {
                    return "SubContentText";
                }
                if (str2.equals("Background")) {
                    return "Content";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_CARD)) {
                if (str2.equals("Text")) {
                    return "ContentText";
                }
                if (str2.equals("SubText")) {
                    return "SubContentText";
                }
                if (str2.equals("ControlText")) {
                    return "SubContentText";
                }
                if (str2.equals("Background")) {
                    return "Content";
                }
                return str2;
            } else if (!str.equals(ThemeElement.PANEL_PLAY_BAR)) {
                return str2;
            } else {
                if (str2.equals("Text")) {
                    return "TitleText";
                }
                if (str2.equals("SubText")) {
                    return "SubTitleText";
                }
                if (str2.equals("TimeText")) {
                    return "SubTitleText";
                }
                if (str2.equals("Background")) {
                    return "BottomBar";
                }
                return str2;
            }
        }

        private String f(String str, String str2) {
            if (str.equals(ThemeElement.PANEL_TOP_BAR)) {
                if (str2.equals("Background")) {
                    return "top_bar_bkg";
                }
                if (str2.equals("Indicator")) {
                    return "top_bar_indicator";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SUB_BAR)) {
                if (str2.equals("Background")) {
                    return "sub_bar_bkg";
                }
                if (str2.equals("Indicator")) {
                    return "sub_bar_indicator";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SONG_LIST_ITEM)) {
                if (str2.equals("Indicator")) {
                    return "song_list_item_indicator";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_PLAY_BAR)) {
                if (str2.equals("Background")) {
                    return "play_bar_bkg2";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SETTING) && str2.equals("Background")) {
                return "setting_background";
            } else {
                return str2;
            }
        }

        a c(String str) {
            return (a) this.c.a().get(str);
        }

        a e() {
            if (this.b == null) {
                this.b = c(ThemeElement.PANEL_COMMON);
            }
            return this.b;
        }

        Object c(String str, String str2) {
            a c = c(str);
            if (c != null) {
                return c.a(str2);
            }
            return null;
        }

        e d(String str, String str2) {
            a c = c(str);
            if (c != null) {
                return c.b(str2);
            }
            return null;
        }

        private void f() {
            Iterable<Object> e = this.d.e();
            if (e != null) {
                for (Object obj : e) {
                    Object obj2;
                    if (obj2.endsWith(".png")) {
                        if (obj2.startsWith("/")) {
                            obj2 = obj2.substring(1);
                        }
                        this.a.add(obj2);
                    }
                }
            }
        }

        private void e(String str) {
            try {
                for (String str2 : BaseApplication.e().getAssets().list(str)) {
                    if (str2.endsWith(".png")) {
                        this.a.add(str2);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* ThemeFramework */
    public static class c extends e {
        public c(String str) {
            super(str);
        }

        private int c(String str) {
            return d(str);
        }

        public static int a(String str) {
            int i = ViewCompat.MEASURED_STATE_MASK;
            try {
                i = b(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return i;
        }

        protected static int b(String str) {
            if (str == null) {
                throw new NumberFormatException();
            } else if (str.startsWith("#")) {
                String trim = str.trim();
                int indexOf = trim.indexOf(" ");
                int i = -1;
                if (indexOf > 0) {
                    i = Integer.parseInt(trim.substring(indexOf).trim());
                    trim = trim.substring(1, indexOf);
                } else {
                    trim = trim.substring(1);
                }
                if (trim.length() != 6) {
                    throw new NumberFormatException(String.format("Color value '%s' is incorrect. Format is either#RRGGBB Alpha", new Object[]{trim}));
                }
                String str2;
                trim = "FF" + trim;
                if (i < 0 || i > 100) {
                    str2 = trim;
                } else {
                    str2 = String.format("%02X", new Object[]{Integer.valueOf((int) (((((float) i) * 255.0f) / 100.0f) + TTFMImageUtils.Middle_Scale))}) + trim.substring(2);
                }
                return (int) Long.parseLong(str2, 16);
            } else {
                throw new NumberFormatException(String.format("Color value '%s' must start with #", new Object[]{str}));
            }
        }

        public void a(XmlPullParser xmlPullParser) {
            int attributeCount = xmlPullParser.getAttributeCount();
            Orientation orientation = Orientation.LEFT_RIGHT;
            List list = null;
            for (int i = 0; i < attributeCount; i++) {
                String attributeName = xmlPullParser.getAttributeName(i);
                String attributeValue = xmlPullParser.getAttributeValue(i);
                if ("ID".equals(attributeName)) {
                    this.b = attributeValue;
                } else if ("Normal".equals(attributeName)) {
                    list = e(attributeValue);
                } else if ("Selected".equals(attributeName)) {
                    this.d = new ColorDrawable(a(attributeValue));
                } else if ("Width".equals(attributeName)) {
                    this.f = d(attributeValue);
                } else if ("Height".equals(attributeName)) {
                    this.g = d(attributeValue);
                } else if ("Orientation".equals(attributeName)) {
                    orientation = Orientation.valueOf(a[Integer.parseInt(attributeValue)]);
                } else if ("CornerRadius".equals(attributeName)) {
                    this.h = d(attributeValue);
                } else if ("Padding".equals(attributeName)) {
                    this.i = c(attributeValue);
                }
            }
            this.c = a(list, orientation);
        }

        public Object b() {
            if (ThemeElement.isTextElementId(this.b)) {
                return i();
            }
            return super.b();
        }

        private ColorStateList i() {
            if (this.d != null && this.c != null) {
                r2 = new int[3][];
                r2[0] = new int[]{16842919};
                r2[1] = new int[]{16842913};
                r2[2] = StateSet.WILD_CARD;
                return new ColorStateList(r2, new int[]{c.a((ColorDrawable) this.d), c.a((ColorDrawable) this.d), c.a((ColorDrawable) this.c)});
            } else if (this.c == null) {
                return null;
            } else {
                return new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{c.a((ColorDrawable) this.c)});
            }
        }

        private int d(String str) {
            try {
                return com.sds.android.ttpod.common.c.a.a(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return -2;
            }
        }

        protected Drawable a(List<Integer> list, Orientation orientation) {
            if (list == null) {
                return null;
            }
            if (1 == list.size()) {
                return new ColorDrawable(((Integer) list.get(0)).intValue());
            }
            int[] iArr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                iArr[i] = ((Integer) list.get(i)).intValue();
            }
            return new GradientDrawable(orientation, iArr);
        }

        private List<Integer> e(String str) {
            String[] split = str.split(SelectCountryActivity.SPLITTER);
            List<Integer> arrayList = new ArrayList();
            for (String a : split) {
                arrayList.add(Integer.valueOf(a(a)));
            }
            return arrayList;
        }
    }

    /* ThemeFramework */
    static class d {
        protected String a;
        protected ColorDrawable b = null;
        protected String c;
        protected ColorDrawable d;
        protected ColorDrawable e;
        protected String f;
        private HashMap<String, a> g = new HashMap();

        d() {
        }

        public HashMap<String, a> a() {
            return this.g;
        }

        public void b() {
            this.g.clear();
            this.g = null;
        }

        public void a(InputStream inputStream) {
            try {
                XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                newInstance.setNamespaceAware(true);
                XmlPullParser newPullParser = newInstance.newPullParser();
                newPullParser.setInput(inputStream, null);
                int eventType = newPullParser.getEventType();
                do {
                    if (2 == eventType) {
                        String name = newPullParser.getName();
                        String attributeValue = newPullParser.getAttributeValue(null, "ID");
                        if (name.equals("View")) {
                            a(newPullParser);
                        } else if (name.equals("Panel")) {
                            a a = a(newPullParser, attributeValue);
                            if (a != null) {
                                this.g.put(attributeValue, a);
                            }
                        }
                    }
                    try {
                        eventType = newPullParser.next();
                        continue;
                    } catch (IOException e) {
                        try {
                            e.printStackTrace();
                            continue;
                        } catch (XmlPullParserException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                } while (1 != eventType);
            } catch (XmlPullParserException e22) {
                e22.printStackTrace();
            }
        }

        ColorDrawable c() {
            return this.b != null ? (ColorDrawable) this.b.getConstantState().newDrawable() : null;
        }

        ColorDrawable d() {
            return this.d != null ? (ColorDrawable) this.d.getConstantState().newDrawable() : null;
        }

        ColorDrawable e() {
            return this.e != null ? (ColorDrawable) this.e.getConstantState().newDrawable() : null;
        }

        private void a(XmlPullParser xmlPullParser) {
            int attributeCount = xmlPullParser.getAttributeCount();
            for (int i = 0; i < attributeCount; i++) {
                String attributeName = xmlPullParser.getAttributeName(i);
                String attributeValue = xmlPullParser.getAttributeValue(i);
                if (ThemeElement.STATUS_BAR_MODE.equals(attributeName)) {
                    this.a = attributeValue;
                } else if (ThemeElement.BACKGROUND_MASK.equals(attributeName)) {
                    this.b = new ColorDrawable(c.a(attributeValue));
                } else if ("BackgroundImage".equals(attributeName)) {
                    this.c = attributeValue;
                } else if ("HomeBackgroundBlur".equals(attributeName)) {
                    this.f = attributeName;
                } else if (ThemeElement.STATUS_BAR_BACKGROUND.equals(attributeName)) {
                    this.d = new ColorDrawable(c.a(attributeValue));
                } else if (ThemeElement.NAVIGATION_BAR_BACKGROUND.equals(attributeName)) {
                    this.e = new ColorDrawable(c.a(attributeValue));
                }
            }
        }

        private a a(XmlPullParser xmlPullParser, String str) {
            a aVar;
            XmlPullParserException e;
            IOException e2;
            try {
                aVar = new a(str);
                try {
                    aVar.a(xmlPullParser);
                } catch (XmlPullParserException e3) {
                    e = e3;
                    e.printStackTrace();
                    return aVar;
                } catch (IOException e4) {
                    e2 = e4;
                    e2.printStackTrace();
                    return aVar;
                }
            } catch (XmlPullParserException e5) {
                XmlPullParserException xmlPullParserException = e5;
                aVar = null;
                e = xmlPullParserException;
                e.printStackTrace();
                return aVar;
            } catch (IOException e6) {
                IOException iOException = e6;
                aVar = null;
                e2 = iOException;
                e2.printStackTrace();
                return aVar;
            }
            return aVar;
        }
    }

    /* ThemeFramework */
    public static class f extends e {
        private static String[] j = new String[]{j() + ".png", "_n.png", ".png", ".9.png"};
        private static String[] k = new String[]{"_h.png", "_h.9.png"};

        public /* synthetic */ Object b() {
            return i();
        }

        public f(String str, Bitmap bitmap) {
            this(str, bitmap, null);
        }

        public f(String str, Bitmap bitmap, Bitmap bitmap2) {
            super(str);
            this.c = a(str, bitmap);
            this.d = a(str, bitmap2);
        }

        public static f a(b bVar, String str) {
            Bitmap a = a(str, bVar, j);
            Bitmap a2 = a(str, bVar, k);
            if (a != null && a2 != null) {
                return new f(str, a, a2);
            }
            if (a != null) {
                return new f(str, a);
            }
            return null;
        }

        private static Bitmap a(String str, b bVar, String[] strArr) {
            for (String str2 : strArr) {
                Bitmap b = b(bVar, str + str2);
                if (b != null) {
                    return b;
                }
            }
            return null;
        }

        private static Bitmap b(b bVar, String str) {
            if (bVar.b(str)) {
                return bVar.a(str);
            }
            return null;
        }

        private static String j() {
            String h = com.sds.android.ttpod.common.c.a.h();
            if (!"_xhdpi".equals(h)) {
                return h;
            }
            return h + (((double) com.sds.android.ttpod.common.c.a.f()) > 2.0d ? FeedbackItem.STATUS_SOLVED : "1");
        }

        protected Drawable a(String str, Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            Resources resources = BaseApplication.e().getResources();
            if (a(bitmap)) {
                return new NinePatchDrawable(resources, new NinePatch(bitmap, bitmap.getNinePatchChunk(), str));
            }
            return new BitmapDrawable(resources, bitmap);
        }

        protected boolean a(Bitmap bitmap) {
            byte[] ninePatchChunk = bitmap.getNinePatchChunk();
            return ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk);
        }

        public Drawable i() {
            return (Drawable) super.b();
        }
    }
}

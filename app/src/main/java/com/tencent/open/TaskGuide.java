package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.wireless.security.SecExceptionCode;
import com.igexin.download.Downloads;
import com.igexin.sdk.PushConsts;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
public class TaskGuide extends BaseApi {
    private static int K = 3000;
    static long b = 5000;
    private static Drawable k;
    private static Drawable l;
    private static Drawable m;
    private static int n = 75;
    private static int o = 284;
    private static int p = 75;
    private static int q = 30;
    private static int r = 29;
    private static int s = 5;
    private static int t = 74;
    private static int u = 0;
    private static int v = 6;
    private static int w = 153;
    private static int x = 30;
    private static int y = 6;
    private static int z = 3;
    private int A = 0;
    private int B = 0;
    private float C = 0.0f;
    private Interpolator D = new AccelerateInterpolator();
    private boolean E = false;
    private boolean F = false;
    private boolean G = false;
    private long H;
    private int I;
    private int J;
    private Runnable L = null;
    private Runnable M = null;
    boolean a = false;
    IUiListener c;
    private LayoutParams d = null;
    private ViewGroup e = null;
    private WindowManager f;
    private Handler g = new Handler(Looper.getMainLooper());
    private i h;
    private d i = d.INIT;
    private d j = d.INIT;

    /* ProGuard */
    private abstract class g implements IRequestListener {
        final /* synthetic */ TaskGuide c;

        protected abstract void a(Exception exception);

        private g(TaskGuide taskGuide) {
            this.c = taskGuide;
        }

        public void onIOException(IOException iOException) {
            a(iOException);
        }

        public void onMalformedURLException(MalformedURLException malformedURLException) {
            a(malformedURLException);
        }

        public void onJSONException(JSONException jSONException) {
            a(jSONException);
        }

        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            a(connectTimeoutException);
        }

        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            a(socketTimeoutException);
        }

        public void onNetworkUnavailableException(NetworkUnavailableException networkUnavailableException) {
            a(networkUnavailableException);
        }

        public void onHttpStatusException(HttpStatusException httpStatusException) {
            a(httpStatusException);
        }

        public void onUnknowException(Exception exception) {
            a(exception);
        }
    }

    /* ProGuard */
    private class a extends g {
        int a = -1;
        final /* synthetic */ TaskGuide b;

        public a(TaskGuide taskGuide, int i) {
            this.b = taskGuide;
            super();
            this.a = i;
        }

        public void onComplete(JSONObject jSONObject) {
            String str = null;
            try {
                int i = jSONObject.getInt("code");
                str = jSONObject.getString("message");
                JSONObject jSONObject2;
                if (i == 0) {
                    this.b.a(this.a, d.REWARD_SUCCESS);
                    jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("result", "金券领取成功");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    this.b.c.onComplete(jSONObject2);
                    this.b.b(this.a);
                    this.b.d(2000);
                }
                this.b.a(this.a, d.NORAML);
                this.b.a(str);
                jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("result", "金券领取失败");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                this.b.c.onComplete(jSONObject2);
                this.b.b(this.a);
                this.b.d(2000);
            } catch (JSONException e22) {
                this.b.a(this.a, d.NORAML);
                this.b.a(str);
                e22.printStackTrace();
            }
        }

        protected void a(final Exception exception) {
            if (exception != null) {
                exception.printStackTrace();
            }
            this.b.c.onError(new UiError(101, "error ", "金券领取时出现异常"));
            if (this.b.g != null) {
                this.b.g.post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        d dVar = d.INIT;
                        if (this.b.a == 0) {
                            dVar = this.b.b.i;
                        } else {
                            dVar = this.b.b.j;
                        }
                        if (dVar == d.WAITTING_BACK_REWARD) {
                            this.b.b.a(this.b.a, d.NORAML);
                            this.b.b.a("领取失败 :" + exception.getClass().getName());
                        }
                        this.b.b.b(this.b.a);
                        this.b.b.d(2000);
                    }
                });
            }
        }
    }

    /* ProGuard */
    class b implements Runnable {
        boolean a = false;
        float b = 0.0f;
        final /* synthetic */ TaskGuide c;

        public b(TaskGuide taskGuide, boolean z) {
            this.c = taskGuide;
            this.a = z;
        }

        public void run() {
            Object obj = 1;
            SystemClock.currentThreadTimeMillis();
            this.b = (float) (((double) this.b) + 0.1d);
            float f = this.b;
            if (f > 1.0f) {
                f = 1.0f;
            }
            Object obj2 = f >= 1.0f ? 1 : null;
            int interpolation = (int) (this.c.D.getInterpolation(f) * ((float) this.c.I));
            if (this.a) {
                this.c.d.y = this.c.J + interpolation;
            } else {
                this.c.d.y = this.c.J - interpolation;
            }
            Log.d("TAG", "mWinParams.y = " + this.c.d.y + "deltaDistence = " + interpolation);
            if (this.c.E) {
                this.c.f.updateViewLayout(this.c.e, this.c.d);
                obj = obj2;
            }
            if (obj != null) {
                this.c.i();
            } else {
                this.c.g.postDelayed(this.c.L, 5);
            }
        }
    }

    /* ProGuard */
    private class c extends g {
        final /* synthetic */ TaskGuide a;

        private c(TaskGuide taskGuide) {
            this.a = taskGuide;
            super();
        }

        public void onComplete(JSONObject jSONObject) {
            try {
                this.a.h = i.a(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (this.a.h == null || !this.a.h.a()) {
                a(null);
                return;
            }
            this.a.showWindow();
            this.a.a(2, d.NORAML);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("result", "获取成功");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.a.c.onComplete(jSONObject2);
        }

        protected void a(Exception exception) {
            if (exception != null) {
                exception.printStackTrace();
            }
            if (exception == null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("result", "暂无任务");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.a.c.onComplete(jSONObject);
            } else {
                this.a.c.onError(new UiError(100, "error ", "获取任务失败"));
            }
            this.a.g.post(new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.a(2, d.INIT);
                }
            });
        }
    }

    /* ProGuard */
    private enum d {
        INIT,
        WAITTING_BACK_TASKINFO,
        WAITTING_BACK_REWARD,
        NORAML,
        REWARD_SUCCESS,
        REWARD_FAIL;

        public static d[] a() {
            return (d[]) g.clone();
        }
    }

    /* ProGuard */
    private static class e {
        int a;
        String b;
        String c;
        long d;
        int e;

        public e(int i, String str, String str2, long j, int i2) {
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = j;
            this.e = i2;
        }
    }

    /* ProGuard */
    class f implements OnClickListener {
        int a;
        final /* synthetic */ TaskGuide b;

        public f(TaskGuide taskGuide, int i) {
            this.b = taskGuide;
            this.a = i;
        }

        public void onClick(View view) {
            Button button = (Button) view;
            if (this.b.c(this.a) == d.NORAML) {
                this.b.e(this.a);
                this.b.b(this.a);
            }
            this.b.h();
        }
    }

    /* ProGuard */
    private class h extends LinearLayout {
        final /* synthetic */ TaskGuide a;
        private TextView b;
        private Button c;
        private e d;

        public h(TaskGuide taskGuide, Context context, e eVar) {
            this.a = taskGuide;
            super(context);
            this.d = eVar;
            setOrientation(0);
            a();
        }

        private void a() {
            this.b = new TextView(this.a.mContext);
            this.b.setTextColor(Color.rgb(MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK));
            this.b.setTextSize(15.0f);
            this.b.setShadowLayer(1.0f, 1.0f, 1.0f, Color.rgb(242, 211, SecExceptionCode.SEC_ERROR_INIT_UNKNOWN_ERROR));
            this.b.setGravity(3);
            this.b.setEllipsize(TruncateAt.END);
            this.b.setIncludeFontPadding(false);
            this.b.setSingleLine(true);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = 1.0f;
            layoutParams.leftMargin = this.a.a(4);
            addView(this.b, layoutParams);
            this.c = new Button(this.a.mContext);
            this.c.setPadding(0, 0, 0, 0);
            this.c.setTextSize(16.0f);
            this.c.setTextColor(Color.rgb(MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK));
            this.c.setShadowLayer(1.0f, 1.0f, 1.0f, Color.rgb(242, 211, SecExceptionCode.SEC_ERROR_INIT_UNKNOWN_ERROR));
            this.c.setIncludeFontPadding(false);
            this.c.setOnClickListener(new f(this.a, this.d.a));
            layoutParams = new LinearLayout.LayoutParams(this.a.a(TaskGuide.p), this.a.a(TaskGuide.q));
            layoutParams.leftMargin = this.a.a(2);
            layoutParams.rightMargin = this.a.a(8);
            addView(this.c, layoutParams);
        }

        public void a(d dVar) {
            if (!TextUtils.isEmpty(this.d.b)) {
                this.b.setText(this.d.b);
            }
            switch (dVar) {
                case INIT:
                    this.c.setEnabled(false);
                    return;
                case NORAML:
                    if (this.d.e == 1) {
                        this.c.setText(this.d.c);
                        this.c.setBackgroundDrawable(null);
                        this.c.setTextColor(Color.rgb(MotionEventCompat.ACTION_MASK, 246, 0));
                        this.c.setEnabled(false);
                        return;
                    } else if (this.d.e == 2) {
                        this.c.setText("领取奖励");
                        this.c.setTextColor(Color.rgb(MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK));
                        this.c.setBackgroundDrawable(this.a.f());
                        this.c.setEnabled(true);
                        return;
                    } else {
                        return;
                    }
                case WAITTING_BACK_REWARD:
                    this.c.setText("领取中...");
                    this.c.setEnabled(false);
                    return;
                case REWARD_SUCCESS:
                    this.c.setText("已领取");
                    this.c.setBackgroundDrawable(this.a.g());
                    this.c.setEnabled(false);
                    return;
                default:
                    return;
            }
        }
    }

    /* ProGuard */
    private static class i {
        String a;
        String b;
        e[] c;

        private i() {
        }

        public boolean a() {
            if (TextUtils.isEmpty(this.a) || this.c == null || this.c.length <= 0) {
                return false;
            }
            return true;
        }

        static i a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            i iVar = new i();
            JSONObject jSONObject2 = jSONObject.getJSONObject("task_info");
            iVar.a = jSONObject2.getString("task_id");
            iVar.b = jSONObject2.getString("task_desc");
            JSONArray jSONArray = jSONObject2.getJSONArray("step_info");
            int length = jSONArray.length();
            if (length > 0) {
                iVar.c = new e[length];
            }
            for (int i = 0; i < length; i++) {
                jSONObject2 = jSONArray.getJSONObject(i);
                iVar.c[i] = new e(jSONObject2.getInt("step_no"), jSONObject2.getString("step_desc"), jSONObject2.getString("step_gift"), jSONObject2.getLong("end_time"), jSONObject2.getInt(Downloads.COLUMN_STATUS));
            }
            return iVar;
        }
    }

    /* ProGuard */
    private class j extends RelativeLayout {
        int a = 0;
        final /* synthetic */ TaskGuide b;

        public j(TaskGuide taskGuide, Context context) {
            this.b = taskGuide;
            super(context);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            int y = (int) motionEvent.getY();
            Log.d("XXXX", "onInterceptTouchEvent-- action = " + motionEvent.getAction() + "currentY = " + y);
            this.b.d(3000);
            switch (motionEvent.getAction()) {
                case 0:
                    this.a = y;
                    return false;
                case 1:
                    if (this.a - y > ViewConfiguration.getTouchSlop() * 2) {
                        this.b.l();
                        return true;
                    }
                    break;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            super.onTouchEvent(motionEvent);
            int y = (int) motionEvent.getY();
            Log.d("XXXX", " onTouchEvent-----startY = " + this.a + "currentY = " + y);
            switch (motionEvent.getAction()) {
                case 0:
                    this.a = y;
                    break;
                case 1:
                    if (this.a - y > ViewConfiguration.getTouchSlop() * 2) {
                        this.b.l();
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    /* ProGuard */
    private class k implements Runnable {
        final /* synthetic */ TaskGuide a;

        private k(TaskGuide taskGuide) {
            this.a = taskGuide;
        }

        public void run() {
            this.a.l();
        }
    }

    public TaskGuide(Context context, QQToken qQToken) {
        super(context, qQToken);
        this.f = (WindowManager) context.getSystemService("window");
        c();
    }

    public TaskGuide(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(context, qQAuth, qQToken);
        this.f = (WindowManager) context.getSystemService("window");
        c();
    }

    private void c() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f.getDefaultDisplay().getMetrics(displayMetrics);
        this.A = displayMetrics.widthPixels;
        this.B = displayMetrics.heightPixels;
        this.C = displayMetrics.density;
    }

    private LayoutParams a(Context context) {
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.gravity = 49;
        this.f.getDefaultDisplay().getWidth();
        this.f.getDefaultDisplay().getHeight();
        layoutParams.width = a(o);
        layoutParams.height = a(n);
        layoutParams.windowAnimations = 16973826;
        layoutParams.format = 1;
        layoutParams.flags |= 520;
        layoutParams.type = 2;
        this.d = layoutParams;
        return layoutParams;
    }

    private void d() {
        if (this.d != null) {
            this.d.y = -this.d.height;
        }
    }

    private int a(int i) {
        return (int) (((float) i) * this.C);
    }

    private ViewGroup b(Context context) {
        ViewGroup jVar = new j(this, context);
        e[] eVarArr = this.h.c;
        View hVar;
        ViewGroup.LayoutParams layoutParams;
        if (eVarArr.length == 1) {
            hVar = new h(this, context, eVarArr[0]);
            hVar.setId(1);
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(15);
            jVar.addView(hVar, layoutParams);
        } else {
            hVar = new h(this, context, eVarArr[0]);
            hVar.setId(1);
            View hVar2 = new h(this, context, eVarArr[1]);
            hVar2.setId(2);
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(14);
            layoutParams.setMargins(0, a(6), 0, 0);
            ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(14);
            layoutParams2.setMargins(0, a(4), 0, 0);
            layoutParams2.addRule(3, 1);
            layoutParams2.addRule(5, 1);
            jVar.addView(hVar, layoutParams);
            jVar.addView(hVar2, layoutParams2);
        }
        jVar.setBackgroundDrawable(e());
        return jVar;
    }

    private Drawable e() {
        if (k == null) {
            k = a("background.9.png", this.mContext);
        }
        return k;
    }

    private Drawable f() {
        if (l == null) {
            l = a("button_green.9.png", this.mContext);
        }
        return l;
    }

    private Drawable g() {
        if (m == null) {
            m = a("button_red.9.png", this.mContext);
        }
        return m;
    }

    private void b(final int i) {
        if (this.g != null) {
            this.g.post(new Runnable(this) {
                final /* synthetic */ TaskGuide b;

                public void run() {
                    if (!this.b.E) {
                        return;
                    }
                    if (i == 0) {
                        ((h) this.b.e.findViewById(1)).a(this.b.i);
                    } else if (i == 1) {
                        ((h) this.b.e.findViewById(2)).a(this.b.j);
                    } else if (i == 2) {
                        ((h) this.b.e.findViewById(1)).a(this.b.i);
                        if (this.b.e.getChildCount() > 1) {
                            ((h) this.b.e.findViewById(2)).a(this.b.j);
                        }
                    }
                }
            });
        }
    }

    private void a(int i, d dVar) {
        if (i == 0) {
            this.i = dVar;
        } else if (i == 1) {
            this.j = dVar;
        } else {
            this.i = dVar;
            this.j = dVar;
        }
    }

    private d c(int i) {
        if (i == 0) {
            return this.i;
        }
        if (i == 1) {
            return this.j;
        }
        return d.INIT;
    }

    @SuppressLint({"ResourceAsColor"})
    public void showWindow() {
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ TaskGuide a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.e = this.a.b(this.a.mContext);
                this.a.d = this.a.a(this.a.mContext);
                this.a.d();
                WindowManager windowManager = (WindowManager) this.a.mContext.getSystemService("window");
                if (!((Activity) this.a.mContext).isFinishing()) {
                    if (!this.a.E) {
                        windowManager.addView(this.a.e, this.a.d);
                    }
                    this.a.E = true;
                    this.a.b(2);
                    this.a.k();
                }
            }
        });
        com.tencent.connect.a.a.a(this.mContext, this.mToken, "TaskApi", "showTaskWindow");
    }

    private void d(int i) {
        h();
        this.M = new k();
        this.g.postDelayed(this.M, (long) i);
    }

    private void h() {
        this.g.removeCallbacks(this.M);
        if (!j()) {
            this.g.removeCallbacks(this.L);
        }
    }

    private void i() {
        if (this.F) {
            d(3000);
        } else {
            removeWindow();
        }
        if (this.F) {
            LayoutParams layoutParams = this.d;
            layoutParams.flags &= -17;
            this.f.updateViewLayout(this.e, this.d);
        }
        this.F = false;
        this.G = false;
    }

    private void a(boolean z) {
        this.H = SystemClock.currentThreadTimeMillis();
        if (z) {
            this.F = true;
        } else {
            this.G = true;
        }
        this.I = this.d.height;
        this.J = this.d.y;
        LayoutParams layoutParams = this.d;
        layoutParams.flags |= 16;
        this.f.updateViewLayout(this.e, this.d);
    }

    private boolean j() {
        return this.F || this.G;
    }

    private void k() {
        if (!j()) {
            this.g.removeCallbacks(this.M);
            this.g.removeCallbacks(this.L);
            this.L = new b(this, true);
            a(true);
            this.g.post(this.L);
        }
    }

    private void l() {
        if (!j()) {
            this.g.removeCallbacks(this.M);
            this.g.removeCallbacks(this.L);
            this.L = new b(this, false);
            a(false);
            this.g.post(this.L);
        }
    }

    public void removeWindow() {
        if (this.E) {
            this.f.removeView(this.e);
            this.E = false;
        }
    }

    private Drawable a(String str, Context context) {
        IOException e;
        Drawable createFromStream;
        try {
            InputStream open = context.getApplicationContext().getAssets().open(str);
            if (open == null) {
                return null;
            }
            if (str.endsWith(".9.png")) {
                Bitmap decodeStream = BitmapFactory.decodeStream(open);
                if (decodeStream == null) {
                    return null;
                }
                byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
                NinePatch.isNinePatchChunk(ninePatchChunk);
                return new NinePatchDrawable(decodeStream, ninePatchChunk, new Rect(), null);
            }
            createFromStream = Drawable.createFromStream(open, str);
            try {
                open.close();
                return createFromStream;
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return createFromStream;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            createFromStream = null;
            e = iOException;
            e.printStackTrace();
            return createFromStream;
        }
    }

    private void a(final String str) {
        this.g.post(new Runnable(this) {
            final /* synthetic */ TaskGuide b;

            public void run() {
                Toast.makeText(this.b.mContext, "失败：" + str, 1).show();
            }
        });
    }

    public void showTaskGuideWindow(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.mContext = activity;
        this.c = iUiListener;
        if (this.i != d.WAITTING_BACK_TASKINFO && this.j != d.WAITTING_BACK_TASKINFO && !this.E) {
            Bundle bundle2;
            this.h = null;
            if (bundle != null) {
                bundle2 = new Bundle(bundle);
                bundle2.putAll(composeCGIParams());
            } else {
                bundle2 = composeCGIParams();
            }
            IRequestListener cVar = new c();
            bundle2.putString(PushConsts.CMD_ACTION, "task_list");
            bundle2.putString("auth", "mobile");
            bundle2.putString("appid", this.mToken.getAppId());
            HttpUtils.requestAsync(this.mToken, this.mContext, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", bundle2, Constants.HTTP_GET, cVar);
            a(2, d.WAITTING_BACK_TASKINFO);
        }
    }

    private void e(int i) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString(PushConsts.CMD_ACTION, "get_gift");
        composeCGIParams.putString("task_id", this.h.a);
        composeCGIParams.putString("step_no", new Integer(i).toString());
        composeCGIParams.putString("appid", this.mToken.getAppId());
        HttpUtils.requestAsync(this.mToken, this.mContext, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", composeCGIParams, Constants.HTTP_GET, new a(this, i));
        a(i, d.WAITTING_BACK_REWARD);
        com.tencent.connect.a.a.a(this.mContext, this.mToken, "TaskApi", "getGift");
    }
}

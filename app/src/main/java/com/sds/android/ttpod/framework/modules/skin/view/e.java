package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;
import com.igexin.sdk.PushConsts;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.modules.skin.d.b;
import java.util.ArrayList;
import java.util.Iterator;

/* TTImageSwitcher */
public class e extends ImageSwitcher implements ViewFactory {
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d = true;
    private boolean e = true;
    private final LayoutParams f = new LayoutParams(-1, -1);
    private ArrayList<Bitmap> g = new ArrayList();
    private int h;
    private Drawable i;
    private Drawable j;
    private Handler k = new Handler(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = -1;
            int i2 = message.what;
            g.a("TTImageSwitcher", "handleMessage lookLyricPic what=%d", Integer.valueOf(i2));
            if (i2 == 1) {
                this.a.c = true;
                this.a.b();
                this.a.c();
            } else if (i2 == 2) {
                this.a.b();
                this.a.c();
            } else if (i2 == 3) {
                Bitmap bitmap = (Bitmap) message.obj;
                int width = bitmap != null ? bitmap.getWidth() : -1;
                if (bitmap != null) {
                    i = bitmap.getHeight();
                }
                g.a("TTImageSwitcher", "handleMessage lookLyricPic USE_SINGLE_BITMAP bitmap width=%d height=%d", Integer.valueOf(width), Integer.valueOf(i));
                View nextView = this.a.getNextView();
                if (nextView instanceof ImageView) {
                    ImageView imageView = (ImageView) nextView;
                    if (imageView instanceof MaskImageView) {
                        ((MaskImageView) imageView).setMaskImageDrawable(this.a.j);
                    }
                    if (bitmap != null) {
                        imageView.setImageDrawable(new b(this.a.getResources(), bitmap));
                    } else {
                        imageView.setImageDrawable(this.a.i);
                    }
                }
                this.a.showNext();
            }
        }
    };
    private final BroadcastReceiver l = new BroadcastReceiver(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                this.a.d = false;
                this.a.a();
            } else if (PushConsts.ACTION_BROADCAST_USER_PRESENT.equals(action)) {
                this.a.d = true;
                this.a.a();
            } else if (Action.ACTION_AUTO_PLAY_ARTIST_IMAGE.equals(action)) {
                this.a.setAllowStart(intent.getBooleanExtra("state", true));
            }
        }
    };

    public e(Context context) {
        super(context);
    }

    public void setAllowStart(boolean z) {
        if (z != this.e) {
            this.e = z;
            if (this.e) {
                d();
                return;
            }
            this.k.removeCallbacksAndMessages(null);
            this.h = -1;
            b();
        }
    }

    private void a() {
        boolean z = this.b && this.d && this.c && this.e;
        if (z != this.a) {
            if (z) {
                c();
            } else {
                this.k.removeMessages(2);
            }
            this.a = z;
        }
    }

    private void b() {
        this.h++;
        int size = this.g.size();
        if (this.h >= size) {
            this.h = 0;
        }
        View nextView = getNextView();
        if (this.h < size) {
            nextView.setBackgroundDrawable(new b(getResources(), (Bitmap) this.g.get(this.h)));
        } else {
            nextView.setBackgroundDrawable(this.i);
        }
        showNext();
    }

    private void c() {
        Object obj = (this.b && this.d && this.c && this.e) ? 1 : null;
        if (obj != null && this.g.size() > 1) {
            this.k.sendMessageDelayed(this.k.obtainMessage(2), 15000);
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        int i = -1;
        int width = bitmap != null ? bitmap.getWidth() : -1;
        if (bitmap != null) {
            i = bitmap.getHeight();
        }
        g.a("TTImageSwitcher", "setImageBitmap lookLyricPic bitmap width=%d height=%d", Integer.valueOf(width), Integer.valueOf(i));
        this.k.removeCallbacksAndMessages(null);
        Message obtainMessage = this.k.obtainMessage(3);
        obtainMessage.obj = bitmap;
        this.k.sendMessageDelayed(obtainMessage, 200);
    }

    private void d() {
        this.c = false;
        this.h = -1;
        this.k.removeCallbacksAndMessages(null);
        this.k.sendMessageDelayed(this.k.obtainMessage(1), 200);
    }

    public View makeView() {
        View maskImageView = new MaskImageView(getContext());
        maskImageView.setLayoutParams(this.f);
        maskImageView.setScaleType(ScaleType.FIT_XY);
        return maskImageView;
    }

    public void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
            this.b = false;
            getContext().unregisterReceiver(this.l);
            a();
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                Bitmap bitmap = (Bitmap) it.next();
                if (!(bitmap == null || bitmap.isRecycled())) {
                    bitmap.recycle();
                }
            }
            this.g.clear();
            this.k.removeMessages(2);
            this.k.removeMessages(1);
            this.k.removeMessages(3);
        } catch (IllegalArgumentException e) {
            this.k.removeMessages(2);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction(PushConsts.ACTION_BROADCAST_USER_PRESENT);
        intentFilter.addAction(Action.ACTION_AUTO_PLAY_ARTIST_IMAGE);
        getContext().registerReceiver(this.l, intentFilter);
        a();
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.b = i == 0;
        a();
    }

    public void setDefaultImageDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            this.i = new b(getResources(), ((BitmapDrawable) drawable).getBitmap());
        } else {
            this.i = drawable;
        }
    }

    public void setMaskImageDrawable(Drawable drawable) {
        this.j = drawable;
    }

    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 8) {
                childAt.setVisibility(4);
            }
        }
        super.onMeasure(i, i2);
    }
}

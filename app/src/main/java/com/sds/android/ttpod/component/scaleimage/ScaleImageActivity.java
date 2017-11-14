package com.sds.android.ttpod.component.scaleimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.BaseActivity;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ScaleImageActivity extends BaseActivity implements OnClickListener {
    public static final String KEY_PIC_URL = "pic_url";
    public static final String KEY_START_INDEX = "start_index";
    private static final String LOG_TAG = "ScaleImageActivity";
    private static final float MIN_SPACE = 0.1f;
    private static final int PAGER_MARGIN_DP = 40;
    private static final int PIC_QUALITY = 80;
    private static final int REQUIRED_BITMAP_SIZE = 400;
    private static final int WIDE_BITMAP_SHOW = 50;
    private ImageButton mDownloadButton;
    private GestureDetector mGestureDetector;
    private boolean mOnPagerScoll = false;
    private boolean mOnScale = false;
    private com.sds.android.ttpod.component.scaleimage.ImageViewPager.c mPageChangeListener = new com.sds.android.ttpod.component.scaleimage.ImageViewPager.c(this) {
        final /* synthetic */ ScaleImageActivity a;

        {
            this.a = r1;
        }

        public void a(int i, int i2) {
            ImageViewTouch imageViewTouch = (ImageViewTouch) this.a.mPagerAdapter.b.get(i2);
            if (imageViewTouch != null) {
                imageViewTouch.a(imageViewTouch.d.b(), true);
            }
            this.a.mStartIndex = i;
            this.a.updateShowInfo();
        }

        public void a(int i, float f, int i2) {
            this.a.mOnPagerScoll = true;
        }

        public void a(int i) {
            if (i == 1) {
                this.a.mOnPagerScoll = true;
            } else if (i == 2) {
                this.a.mOnPagerScoll = false;
            } else {
                this.a.mOnPagerScoll = false;
            }
        }
    };
    private TextView mPageShow;
    private a mPagerAdapter;
    private boolean mPaused;
    private ArrayList<String> mPicList;
    private b mScaleGestureDetector;
    private int mStartIndex;
    private ImageViewPager mViewPager;
    private c myOnScaleGestureListener;

    private class a extends a {
        final /* synthetic */ ScaleImageActivity a;
        private SparseArrayCompat<ImageViewTouch> b;

        private a(ScaleImageActivity scaleImageActivity) {
            this.a = scaleImageActivity;
            this.b = new SparseArrayCompat();
        }

        public int a() {
            return this.a.mPicList.size();
        }

        public ImageViewTouch a(int i) {
            return (ImageViewTouch) this.b.get(i);
        }

        public Object a(View view, int i) {
            final View imageViewTouch = new ImageViewTouch(this.a);
            imageViewTouch.setLayoutParams(new LayoutParams(-1, -1));
            imageViewTouch.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            imageViewTouch.setFocusableInTouchMode(true);
            String str = (String) this.a.mPicList.get(i);
            imageViewTouch.setTag(str);
            g.b().a(str, imageViewTouch.getWidth(), imageViewTouch.getHeight(), new com.sds.android.sdk.core.a.b.a(this) {
                final /* synthetic */ a b;

                public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                    if (imageViewTouch.getTag().equals(str)) {
                        imageViewTouch.a(bitmap, true);
                    }
                }
            });
            ((ImageViewPager) view).addView(imageViewTouch);
            this.b.put(i, imageViewTouch);
            return imageViewTouch;
        }

        public void a(View view, int i, Object obj) {
            ImageViewTouch imageViewTouch = (ImageViewTouch) obj;
            imageViewTouch.c();
            ((ImageViewPager) view).removeView(imageViewTouch);
            this.b.remove(i);
        }

        public void a(View view) {
        }

        public void b(View view) {
        }

        public boolean a(View view, Object obj) {
            return view == ((ImageViewTouch) obj);
        }

        public Parcelable b() {
            return null;
        }

        public void a(Parcelable parcelable, ClassLoader classLoader) {
        }
    }

    private class b extends SimpleOnGestureListener {
        final /* synthetic */ ScaleImageActivity a;

        private b(ScaleImageActivity scaleImageActivity) {
            this.a = scaleImageActivity;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.a.mOnScale) {
                return true;
            }
            if (this.a.mPaused) {
                return false;
            }
            ImageViewTouch access$900 = this.a.getCurrentImageView();
            if (access$900 == null) {
                return true;
            }
            access$900.b(-f, -f2);
            access$900.a(true, true);
            access$900.a(true, true);
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            this.a.finish();
            return true;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (this.a.mPaused) {
                return false;
            }
            ImageViewTouch access$900 = this.a.getCurrentImageView();
            if (access$900 == null) {
                return true;
            }
            if (access$900.d() > (access$900.b() + access$900.a()) / 2.0f) {
                access$900.a(access$900.b());
                return true;
            }
            access$900.b(access$900.a(), motionEvent.getX(), motionEvent.getY());
            return true;
        }
    }

    private class c extends com.sds.android.ttpod.component.scaleimage.b.b {
        final /* synthetic */ ScaleImageActivity a;
        private float b;
        private float c;
        private float d;
        private boolean e;

        private c(ScaleImageActivity scaleImageActivity) {
            this.a = scaleImageActivity;
            this.e = false;
        }

        public void b(b bVar) {
            ImageViewTouch access$900 = this.a.getCurrentImageView();
            if (access$900 != null) {
                this.e = false;
                if (this.b > access$900.a()) {
                    access$900.a(this.b / access$900.a(), 1.0f, this.c, this.d);
                    this.b = access$900.a();
                    access$900.d(this.b, this.c, this.d);
                } else if (this.b < access$900.b()) {
                    access$900.a(this.b, access$900.b(), this.c, this.d);
                    this.b = access$900.b();
                    access$900.d(this.b, this.c, this.d);
                } else {
                    access$900.c(this.b, this.c, this.d);
                }
                access$900.a(true, true);
                access$900.postDelayed(new Runnable(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.mOnScale = false;
                    }
                }, 300);
            }
        }

        public boolean a(b bVar) {
            this.a.mOnScale = true;
            return true;
        }

        public boolean a(b bVar, float f, float f2) {
            ImageViewTouch access$900 = this.a.getCurrentImageView();
            if (access$900 != null) {
                this.e = true;
                float d = access$900.d() * bVar.b();
                this.b = d;
                this.c = f;
                this.d = f2;
                if (bVar.a()) {
                    access$900.c(d, f, f2);
                }
            }
            return true;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_show_images);
        this.mViewPager = (ImageViewPager) findViewById(R.id.viewPager);
        this.mPageShow = (TextView) findViewById(R.id.tv_page);
        this.mDownloadButton = (ImageButton) findViewById(R.id.btn_download);
        this.mDownloadButton.setOnClickListener(this);
        Intent intent = getIntent();
        this.mPicList = intent.getStringArrayListExtra(KEY_PIC_URL);
        this.mStartIndex = intent.getIntExtra(KEY_START_INDEX, 0);
        this.mPageShow.setText("1/" + this.mPicList.size());
        this.mViewPager.setPageMargin(Math.round(getResources().getDisplayMetrics().density * 40.0f));
        this.mViewPager.setPageMarginDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        this.mPagerAdapter = new a();
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setOnPageChangeListener(this.mPageChangeListener);
        this.mViewPager.setCurrentItem(this.mStartIndex);
        setupOnTouchListeners(this.mViewPager);
        this.mViewPager.a(this.mStartIndex, false);
    }

    private void updateShowInfo() {
        if (this.mPicList.size() > 0) {
            this.mPageShow.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.mStartIndex + 1), Integer.valueOf(this.mPicList.size())}));
        }
    }

    private Bitmap decodeFile(File file) {
        int i = 1;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            while (i2 / 2 >= 400 && i3 / 2 >= 400) {
                i2 /= 2;
                i3 /= 2;
                i *= 2;
            }
            options = new Options();
            options.inSampleSize = i;
            return BitmapFactory.decodeStream(new FileInputStream(file), null, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onStart() {
        super.onStart();
        this.mPaused = false;
    }

    public void onStop() {
        super.onStop();
        this.mPaused = true;
    }

    private void setupOnTouchListeners(View view) {
        if (j.d()) {
            this.myOnScaleGestureListener = new c();
            this.mScaleGestureDetector = new b(this, this.myOnScaleGestureListener);
        }
        this.mGestureDetector = new GestureDetector(this, new b());
        view.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ ScaleImageActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!(this.a.mOnScale || this.a.mOnPagerScoll)) {
                    this.a.mGestureDetector.onTouchEvent(motionEvent);
                }
                if (j.d() && (!this.a.mOnPagerScoll || this.a.myOnScaleGestureListener.e)) {
                    this.a.mScaleGestureDetector.a(motionEvent);
                }
                ImageViewTouch access$900 = this.a.getCurrentImageView();
                if (!(access$900 == null || this.a.mOnScale)) {
                    Matrix e = access$900.e();
                    c cVar = access$900.d;
                    if (cVar != null) {
                        Bitmap b = cVar.b();
                        if (b != null) {
                            RectF rectF = new RectF(0.0f, 0.0f, (float) b.getWidth(), (float) b.getHeight());
                            e.mapRect(rectF);
                            if (rectF.right <= ((float) access$900.getWidth()) + ScaleImageActivity.MIN_SPACE || rectF.left >= -0.1f) {
                                try {
                                    this.a.mViewPager.onTouchEvent(motionEvent);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    }
                }
                return true;
            }
        });
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mPaused) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    protected void onDestroy() {
        ImageViewTouch currentImageView = getCurrentImageView();
        if (currentImageView != null) {
            currentImageView.c();
        }
        super.onDestroy();
    }

    private ImageViewTouch getCurrentImageView() {
        return (ImageViewTouch) this.mPagerAdapter.b.get(this.mViewPager.getCurrentItem());
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_download) {
            String B = com.sds.android.ttpod.framework.a.B();
            if (!e.d(B)) {
                e.f(B);
            }
            final String str = com.sds.android.ttpod.framework.a.B() + File.separator + e.k((String) this.mPicList.get(this.mStartIndex)) + ".jpg";
            if (new File(str).exists()) {
                f.a("图片已存在");
                return;
            }
            ImageViewTouch a = this.mPagerAdapter.a(this.mStartIndex);
            g.b().a((String) this.mPicList.get(this.mStartIndex), a.getWidth(), a.getHeight(), new com.sds.android.sdk.core.a.b.a(this) {
                final /* synthetic */ ScaleImageActivity b;

                public void imageLoaded(String str, int i, int i2, final Bitmap bitmap) {
                    if (bitmap == null) {
                        f.a("请等待图片下载完成后再保存!");
                        return;
                    }
                    f.a("图片保存在:" + str);
                    com.sds.android.sdk.lib.e.a.a(this.b, new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 b;

                        public void run() {
                            BufferedOutputStream bufferedOutputStream;
                            FileNotFoundException e;
                            Throwable th;
                            BufferedOutputStream bufferedOutputStream2 = null;
                            try {
                                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
                                try {
                                    bitmap.compress(CompressFormat.JPEG, ScaleImageActivity.PIC_QUALITY, bufferedOutputStream);
                                    if (bufferedOutputStream != null) {
                                        try {
                                            bufferedOutputStream.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                } catch (FileNotFoundException e3) {
                                    e = e3;
                                    try {
                                        e.printStackTrace();
                                        if (bufferedOutputStream != null) {
                                            try {
                                                bufferedOutputStream.close();
                                            } catch (IOException e22) {
                                                e22.printStackTrace();
                                            }
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        bufferedOutputStream2 = bufferedOutputStream;
                                        if (bufferedOutputStream2 != null) {
                                            try {
                                                bufferedOutputStream2.close();
                                            } catch (IOException e4) {
                                                e4.printStackTrace();
                                            }
                                        }
                                        throw th;
                                    }
                                }
                            } catch (FileNotFoundException e5) {
                                e = e5;
                                bufferedOutputStream = null;
                                e.printStackTrace();
                                if (bufferedOutputStream != null) {
                                    bufferedOutputStream.close();
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                if (bufferedOutputStream2 != null) {
                                    bufferedOutputStream2.close();
                                }
                                throw th;
                            }
                        }
                    });
                    Uri fromFile = Uri.fromFile(new File(str));
                    Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    intent.setData(fromFile);
                    this.b.sendBroadcast(intent);
                }
            });
        }
    }
}

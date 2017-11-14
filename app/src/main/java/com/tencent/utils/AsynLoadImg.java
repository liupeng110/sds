package com.tencent.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* ProGuard */
public class AsynLoadImg {
    private static String d;
    Activity a;
    private String b;
    private AsynLoadImgBack c;
    private long e;
    private Handler f;
    private Runnable g = new Runnable(this) {
        final /* synthetic */ AsynLoadImg a;

        {
            this.a = r1;
        }

        public void run() {
            Log.v("AsynLoadImg", "saveFileRunnable:");
            String str = "share_qq_" + Util.encrypt(this.a.b) + ".jpg";
            String str2 = AsynLoadImg.d + str;
            File file = new File(str2);
            Message obtainMessage = this.a.f.obtainMessage();
            if (file.exists()) {
                obtainMessage.arg1 = 0;
                obtainMessage.obj = str2;
                Log.v("AsynLoadImg", "file exists: time:" + (System.currentTimeMillis() - this.a.e));
            } else {
                boolean saveFile;
                Bitmap bitmap = AsynLoadImg.getbitmap(this.a.b);
                if (bitmap != null) {
                    saveFile = this.a.saveFile(bitmap, str);
                } else {
                    Log.v("AsynLoadImg", "saveFileRunnable:get bmp fail---");
                    saveFile = false;
                }
                if (saveFile) {
                    obtainMessage.arg1 = 0;
                    obtainMessage.obj = str2;
                } else {
                    obtainMessage.arg1 = 1;
                }
                Log.v("AsynLoadImg", "file not exists: download time:" + (System.currentTimeMillis() - this.a.e));
            }
            this.a.f.sendMessage(obtainMessage);
        }
    };

    public AsynLoadImg(Activity activity) {
        this.a = activity;
        this.f = new Handler(this, activity.getMainLooper()) {
            final /* synthetic */ AsynLoadImg a;

            public void handleMessage(Message message) {
                Log.v("AsynLoadImg", "handleMessage:" + message.arg1);
                if (message.arg1 == 0) {
                    this.a.c.saved(message.arg1, (String) message.obj);
                } else {
                    this.a.c.saved(message.arg1, null);
                }
            }
        };
    }

    public void save(String str, AsynLoadImgBack asynLoadImgBack) {
        Log.v("AsynLoadImg", "--save---");
        if (str == null || str.equals("")) {
            asynLoadImgBack.saved(1, null);
        } else if (Util.hasSDCard()) {
            d = Environment.getExternalStorageDirectory() + "/tmp/";
            this.e = System.currentTimeMillis();
            this.b = str;
            this.c = asynLoadImgBack;
            new Thread(this.g).start();
        } else {
            asynLoadImgBack.saved(2, null);
        }
    }

    public boolean saveFile(Bitmap bitmap, String str) {
        String str2 = d;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            str2 = str2 + str;
            Log.v("AsynLoadImg", "saveFile:" + str);
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(str2)));
            bitmap.compress(CompressFormat.JPEG, 80, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("AsynLoadImg", "saveFile bmp fail---");
            return false;
        }
    }

    public static Bitmap getbitmap(String str) {
        Log.v("AsynLoadImg", "getbitmap:" + str);
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            Log.v("AsynLoadImg", "image download finished." + str);
            return decodeStream;
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        }
    }
}

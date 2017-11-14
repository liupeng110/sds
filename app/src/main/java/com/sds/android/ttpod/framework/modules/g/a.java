package com.sds.android.ttpod.framework.modules.g;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;

/* MediaPlayerWrapper */
final class a {
    private static a a;
    private MediaPlayer b;
    private a c;
    private OnCompletionListener d = new OnCompletionListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.a.b();
        }
    };
    private OnErrorListener e = new OnErrorListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            this.a.b();
            return true;
        }
    };
    private OnPreparedListener f = new OnPreparedListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
        }
    };

    /* MediaPlayerWrapper */
    interface a {
        void a();
    }

    private a() {
    }

    static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    synchronized void a(String str) {
        if (this.b == null) {
            this.b = new MediaPlayer();
        }
        try {
            this.b.setDataSource(str);
            this.b.prepareAsync();
            this.b.setOnErrorListener(this.e);
            this.b.setOnCompletionListener(this.d);
            this.b.setOnPreparedListener(this.f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized void b() {
        if (this.b != null) {
            this.b.stop();
            this.b.release();
            this.b = null;
            this.c.a();
        }
    }

    synchronized void a(float f, float f2) {
        if (this.b != null) {
            this.b.setVolume(0.0f, 0.0f);
        }
    }

    boolean c() {
        return this.b != null && this.b.isPlaying();
    }

    void a(a aVar) {
        this.c = aVar;
    }
}

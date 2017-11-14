package com.taobao.wireless.security.sdk.initialize;

import android.content.Context;
import android.content.ContextWrapper;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.initialize.IInitializeComponent.IInitFinishListener;

public final class a implements IInitializeComponent {
    private a a;
    private ContextWrapper b;
    private SecurityGuardManager c;

    class a implements IInitFinishListener {
        private IInitializeComponent.IInitFinishListener a;

        public a(IInitializeComponent.IInitFinishListener iInitFinishListener) {
            this.a = iInitFinishListener;
        }

        public final void onError() {
            this.a.onError();
        }

        public final void onSuccess() {
            this.a.onSuccess();
        }
    }

    public final int initialize(Context context) {
        try {
            this.b = (ContextWrapper) context;
            this.c = SecurityGuardManager.getInstance(this.b);
            return this.c != null ? 0 : 1;
        } catch (SecException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public final boolean isSoValid(Context context) {
        try {
            return SecurityGuardManager.getInitializer().isSoValid(context);
        } catch (SecException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final synchronized void loadLibraryAsync(Context context) {
        try {
            SecurityGuardManager.getInitializer().loadLibraryAsync(context);
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public final void loadLibraryAsync(Context context, String str) {
        try {
            SecurityGuardManager.getInitializer().loadLibraryAsync(context, str);
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public final synchronized int loadLibrarySync(Context context) {
        int loadLibrarySync;
        try {
            loadLibrarySync = SecurityGuardManager.getInitializer().loadLibrarySync(context);
        } catch (SecException e) {
            e.printStackTrace();
            loadLibrarySync = 1;
        }
        return loadLibrarySync;
    }

    public final int loadLibrarySync(Context context, String str) {
        try {
            return SecurityGuardManager.getInitializer().loadLibrarySync(context, str);
        } catch (SecException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final void registerInitFinishListener(IInitializeComponent.IInitFinishListener iInitFinishListener) {
        this.a = new a(iInitFinishListener);
        try {
            SecurityGuardManager.getInitializer().registerInitFinishListener(this.a);
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public final void unregisterInitFinishListener(IInitializeComponent.IInitFinishListener iInitFinishListener) {
        try {
            SecurityGuardManager.getInitializer().unregisterInitFinishListener(this.a);
        } catch (SecException e) {
            e.printStackTrace();
        }
    }
}

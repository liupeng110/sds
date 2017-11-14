package com.sds.android.ttpod.activities.mv;

import android.view.MotionEvent;
import com.sds.android.ttpod.component.danmaku.c.b.c;

/* IVideoController */
public interface a {
    void beforeHorizontalTouchMove();

    void beforeVerticalTouchMove();

    void fillStatisticProperty();

    void flushDanmaku(boolean z);

    boolean isCanDisplayDanmaku();

    void onBackPressed();

    void onEndGesture();

    void onEnteringSetBrightnessMode();

    void onEnteringSetProgressMode();

    void onEnteringSetVolumeMode();

    void onEnteringTouchMode();

    void onLeaveSetBrightnessMode(MotionEvent motionEvent, MotionEvent motionEvent2, int i);

    void onLeaveSetProgressMode(MotionEvent motionEvent, MotionEvent motionEvent2, int i);

    void onLeaveSetVolumeMode(MotionEvent motionEvent, MotionEvent motionEvent2, int i);

    void onLoadDanmakuParser(com.sds.android.ttpod.component.danmaku.c.c.a aVar);

    void onRequestedOrientation(boolean z);

    void performClicked();

    boolean performSetBrightness(MotionEvent motionEvent, MotionEvent motionEvent2, int i);

    boolean performSetProgress(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3, int i);

    boolean performSetVolume(MotionEvent motionEvent, MotionEvent motionEvent2, int i);

    void sendDanmaku(c cVar);

    void setLockView(boolean z);

    void switchOrientation();
}

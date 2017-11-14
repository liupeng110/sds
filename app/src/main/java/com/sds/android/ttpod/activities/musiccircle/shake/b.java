package com.sds.android.ttpod.activities.musiccircle.shake;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.baidu.location.BDLocation;
import com.sds.android.cloudapi.ttpod.data.AroundTTPodUser;
import com.sds.android.ttpod.R;
import java.util.List;

/* ShakeController */
public class b implements AnimationListener {
    private ImageView a;
    private ImageView b;
    private Animation c;
    private Animation d;
    private Context e;
    private a f;
    private List<AroundTTPodUser> g;
    private boolean h = false;
    private BDLocation i;

    /* ShakeController */
    public interface a {
        void onEndSearchAnimationFinished(List<AroundTTPodUser> list);

        void onEndSearchAnimationStart(List<AroundTTPodUser> list);

        void onSearchFail();

        void onSearchSuccess(List<AroundTTPodUser> list);

        void onStartSearchAnimationFinished(List<AroundTTPodUser> list);

        void onStartSearchAnimationStart(List<AroundTTPodUser> list);
    }

    public b(Context context, View view, a aVar) {
        this.e = context;
        this.f = aVar;
        this.a = (ImageView) view.findViewById(R.id.shake_icon);
        this.b = (ImageView) view.findViewById(R.id.shake_wave);
        this.c = AnimationUtils.loadAnimation(this.e, R.anim.musiccircle_shake_clockwise_60);
        this.d = AnimationUtils.loadAnimation(this.e, R.anim.musiccircle_shake_clockwise_360);
        this.c.setAnimationListener(this);
        this.d.setAnimationListener(this);
    }

    public void a(BDLocation bDLocation) {
        this.b.setVisibility(0);
        this.a.setVisibility(0);
        this.b.setImageResource(R.drawable.img_musiccircle_shake_wave);
        this.a.startAnimation(this.c);
        this.a.setVisibility(0);
        this.g = null;
        this.h = true;
        this.i = bDLocation;
    }

    public void a() {
        this.a.setVisibility(0);
        this.b.setVisibility(0);
        this.a.setImageResource(R.drawable.img_musiccircle_shake_animation_phone);
        this.b.setImageResource(R.drawable.img_musiccircle_shake_wave);
    }

    private void d() {
        float longitude;
        float latitude;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.e);
        if (this.i != null) {
            longitude = (float) this.i.getLongitude();
            latitude = (float) this.i.getLatitude();
            if (longitude != 0.0f) {
                defaultSharedPreferences.edit().putFloat("location_longitude", (float) this.i.getLongitude()).commit();
            }
            if (latitude != 0.0f) {
                defaultSharedPreferences.edit().putFloat("location_latitude", (float) this.i.getLatitude()).commit();
            }
        } else {
            latitude = 0.0f;
            longitude = 0.0f;
        }
        if (longitude == 0.0f) {
            longitude = defaultSharedPreferences.getFloat("location_longitude", 0.0f);
        }
        if (latitude == 0.0f) {
            latitude = defaultSharedPreferences.getFloat("location_latitude", 0.0f);
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_SHACK_USERS, Float.valueOf(longitude), Float.valueOf(latitude), "shake"));
    }

    public void a(List<AroundTTPodUser> list) {
        this.b.setVisibility(4);
        this.b.clearAnimation();
        this.b.setImageResource(R.drawable.img_musiccircle_shake_wave);
        this.h = false;
        if (list == null) {
            c();
            if (this.f != null) {
                this.g = null;
                this.f.onSearchFail();
            }
        } else if (this.f != null) {
            this.f.onSearchSuccess(list);
            this.g = list;
        }
    }

    public boolean b() {
        return this.h;
    }

    public void c() {
        this.a.setVisibility(4);
        this.b.setVisibility(0);
        this.b.setImageResource(R.drawable.img_musiccircle_shake_fail);
    }

    public void onAnimationStart(Animation animation) {
        if (animation == this.c) {
            if (this.f != null) {
                this.f.onStartSearchAnimationStart(this.g);
            }
        } else if (animation == this.d && this.f != null) {
            this.f.onEndSearchAnimationStart(this.g);
        }
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.c) {
            d();
            this.b.startAnimation(this.d);
            if (this.f != null) {
                this.f.onStartSearchAnimationFinished(this.g);
            }
        } else if (animation == this.d && this.f != null) {
            this.f.onEndSearchAnimationFinished(this.g);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}

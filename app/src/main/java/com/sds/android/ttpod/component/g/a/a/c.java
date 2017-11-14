package com.sds.android.ttpod.component.g.a.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.skin.c.a;
import com.sds.android.ttpod.framework.modules.skin.c.d;
import com.sds.android.ttpod.framework.modules.skin.c.f;
import com.sds.android.ttpod.framework.modules.skin.e.g;
import com.sds.android.ttpod.framework.modules.skin.n;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.HashMap;

/* ViewEventController */
public abstract class c extends b implements d {
    protected PlayStatus X = PlayStatus.STATUS_PAUSED;
    protected PlayStatus Y;
    private int Z;
    private final HashMap<String, f> a = new HashMap(10);
    private final Rect b = new Rect();
    private int c;

    public abstract int e();

    public abstract int f();

    public c(Context context, String str) {
        super(context, str);
    }

    public void a(String[] strArr, a aVar) {
        for (String a : strArr) {
            View a2 = a(a);
            if (a2 != null) {
                a2.setVisibility(0);
                a(a2, aVar);
            }
        }
    }

    public void b(String[] strArr, a aVar) {
        for (String a : strArr) {
            View a2 = a(a);
            if (a2 != null && a2.getVisibility() == 0) {
                a(a2, aVar);
                a2.setVisibility(4);
            }
        }
    }

    public void a(String[] strArr, boolean z, a aVar) {
        for (String a : strArr) {
            View a2 = a(a);
            if (a2 != null) {
                a2.setEnabled(z);
                a(a2, aVar);
            }
        }
    }

    public void a(String[] strArr, int i, int i2, int i3, a aVar) {
        int i4 = i + this.c;
        int i5 = i2 + this.Z;
        for (String a : strArr) {
            View a2 = a(a);
            if (a2 != null) {
                int paddingLeft;
                int paddingTop;
                ViewGroup e = e(a2);
                if (e != null) {
                    paddingLeft = e.getPaddingLeft() + i4;
                    paddingTop = e.getPaddingTop() + i5;
                } else {
                    paddingTop = i5;
                    paddingLeft = i4;
                }
                int[] iArr = (int[]) a2.getTag(R.id.tag_layout_offset);
                if (iArr != null) {
                    paddingLeft += iArr[0];
                    paddingTop += iArr[1];
                }
                int left = paddingLeft - a2.getLeft();
                int top = paddingTop - a2.getTop();
                if (left != 0 || top != 0) {
                    a(a2, left, top);
                    a(a2, -left, 0, -top, 0, i3, aVar);
                }
            }
        }
    }

    public void b(String[] strArr, int i, int i2, int i3, a aVar) {
        if (i != 0 || i2 != 0 || aVar.b() != 0) {
            for (String a : strArr) {
                View a2 = a(a);
                if (a2 != null) {
                    a(a2, i, i2);
                    a(a2, -i, 0, -i2, 0, i3, aVar);
                }
            }
        }
    }

    private void a(View view, int i, int i2, int i3, int i4, int i5, a aVar) {
        if (view.isShown()) {
            Animation a = aVar.a();
            if (i5 > 0) {
                Animation translateAnimation = new TranslateAnimation((float) i, (float) i2, (float) i3, (float) i4);
                translateAnimation.setDuration((long) i5);
                if (!view.getGlobalVisibleRect(this.b)) {
                    a = translateAnimation;
                } else if (a != null) {
                    ((AnimationSet) a).addAnimation(translateAnimation);
                } else {
                    a = translateAnimation;
                }
            }
            if (a != null) {
                view.startAnimation(a);
            } else {
                view.invalidate();
            }
        }
    }

    private void a(View view, a aVar) {
        if (view.isShown() && view.getGlobalVisibleRect(this.b)) {
            Animation a = aVar.a();
            if (a != null) {
                view.startAnimation(a);
            } else {
                view.invalidate();
            }
        }
    }

    public void c(String[] strArr, a aVar) {
        for (String a : strArr) {
            View a2 = a(a);
            if (a2 != null) {
                a(a2, aVar);
            }
        }
    }

    public void a(String[] strArr, int i, boolean z, a aVar) {
        if (VERSION.SDK_INT >= 11) {
            for (String a : strArr) {
                View a2 = a(a);
                if (a2 != null) {
                    a(a2, aVar);
                    if (a2.getTag(R.id.tag_view_animator) == null) {
                        float rotation = a2.getRotation();
                        String str = "rotation";
                        float[] fArr = new float[2];
                        fArr[0] = rotation;
                        fArr[1] = (z ? 360.0f : -360.0f) + rotation;
                        Animator ofFloat = ObjectAnimator.ofFloat(a2, str, fArr);
                        ofFloat.setDuration((long) i);
                        ofFloat.setInterpolator(null);
                        ofFloat.setRepeatCount(-1);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.play(ofFloat);
                        animatorSet.start();
                        a2.setTag(R.id.tag_view_animator, animatorSet);
                    }
                }
            }
        }
    }

    public void d(String[] strArr, a aVar) {
        if (VERSION.SDK_INT >= 11) {
            for (String a : strArr) {
                View a2 = a(a);
                if (a2 != null) {
                    a(a2, aVar);
                    Object tag = a2.getTag(R.id.tag_view_animator);
                    if (tag != null) {
                        if (tag instanceof AnimatorSet) {
                            ((AnimatorSet) tag).cancel();
                        }
                        a2.setTag(R.id.tag_view_animator, null);
                    }
                }
            }
        }
    }

    public void a(String[] strArr, int i, int i2, a aVar) {
        int i3 = i + this.c;
        int i4 = i2 + this.Z;
        for (String a : strArr) {
            View a2 = a(a);
            if (a2 != null) {
                int paddingLeft;
                int paddingTop;
                a2.setVisibility(0);
                ViewGroup e = e(a2);
                if (e != null) {
                    paddingLeft = e.getPaddingLeft() + i3;
                    paddingTop = e.getPaddingTop() + i4;
                } else {
                    paddingTop = i4;
                    paddingLeft = i3;
                }
                int[] iArr = (int[]) a2.getTag(R.id.tag_layout_offset);
                if (iArr != null) {
                    paddingLeft -= iArr[0];
                    paddingTop -= iArr[1];
                }
                a(a2, paddingLeft - a2.getLeft(), paddingTop - a2.getTop());
                a(a2, aVar);
                a2.invalidate();
            }
        }
    }

    private void a(View view, int i, int i2) {
        view.offsetLeftAndRight(i);
        view.offsetTopAndBottom(i2);
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof n) {
            ((n) layoutParams).a(i, i2);
        } else if (layoutParams instanceof MarginLayoutParams) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin += i;
            marginLayoutParams.topMargin += i2;
        }
    }

    private ViewGroup e(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup ? (ViewGroup) parent : null;
    }

    public void a(String[] strArr, f fVar) {
        if (strArr != null && fVar != null) {
            fVar.a(this);
            for (Object put : strArr) {
                this.a.put(put, fVar);
            }
        }
    }

    public void a(MediaItem mediaItem) {
        super.a(mediaItem);
        b("OnMetaChange");
    }

    public void u() {
        super.u();
        b("OnPanelShow");
    }

    public void t() {
        super.t();
        b("OnPanelDisappear");
    }

    public void a(com.sds.android.ttpod.framework.support.a.f fVar) {
        if (L() != fVar) {
            super.a(fVar);
            b("OnPlayModeChange");
        }
    }

    public void a(PlayStatus playStatus) {
        String str = null;
        super.a(playStatus);
        if (PlayStatus.STATUS_STOPPED != playStatus && this.Y != playStatus) {
            this.Y = playStatus;
            b("OnPlayStateChange");
            if (this.X == null || playStatus != this.X) {
                this.X = null;
                if (PlayStatus.STATUS_PLAYING == playStatus) {
                    str = "OnPlay";
                } else if (PlayStatus.STATUS_PAUSED == playStatus) {
                    str = "OnPause";
                }
                if (str != null) {
                    b(str);
                }
            }
        }
    }

    public void b(String str) {
        f fVar = (f) this.a.get(str);
        if (fVar != null) {
            fVar.a(e(), f());
        }
    }

    public void b(View view) {
        a(view, view.getTag(R.id.tag_event_on_click) != null);
        super.b(view);
    }

    public void a(View view) {
        super.a(view);
        Object tag = view.getTag(R.id.tag_event_on_click);
        if (tag != null) {
            b(tag.toString());
        }
    }

    public void a(Bitmap bitmap) {
        super.a(bitmap);
        b(bitmap == null ? "OnArtUnload" : "OnArtLoad");
    }

    public void a(g gVar) {
        super.a(gVar);
        b(gVar == null ? "OnLyricUnload" : "OnLyricLoad");
    }

    public void j(int i) {
        this.c = i;
    }
}

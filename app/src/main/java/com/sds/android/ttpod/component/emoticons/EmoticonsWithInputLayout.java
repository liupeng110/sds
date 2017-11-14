package com.sds.android.ttpod.component.emoticons;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.r;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.d.d;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;

public class EmoticonsWithInputLayout extends LinearLayout {
    private InputMethodManager a;
    private EmoticonsLayout b;
    private IconTextView c;
    private View d;
    private EditText e;
    private View f;
    private IconTextView g;
    private View h;
    private View i;
    private SlidingClosableRelativeLayout j;
    private boolean k;
    private boolean l;
    private String m;
    private long n;
    private TextWatcher o = new TextWatcher(this) {
        final /* synthetic */ EmoticonsWithInputLayout a;
        private int b;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (m.a(this.a.m) || !charSequence.toString().contains(this.a.m) || i >= this.a.m.length()) {
                this.b = 0;
                return;
            }
            if (i + i2 >= this.a.m.length()) {
                this.b = i;
            } else {
                this.b = (this.a.m.length() - i2) + i3;
            }
            this.a.m = null;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            try {
                if (this.b > 0) {
                    editable.delete(0, this.b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public interface a {
        void onSend(String str);
    }

    public EmoticonsWithInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setLayoutBottomBackgroundColor(int i) {
        this.i.setBackgroundColor(i);
    }

    public void a(SlidingClosableRelativeLayout slidingClosableRelativeLayout, View view, final a aVar) {
        this.k = true;
        this.a = (InputMethodManager) getContext().getSystemService("input_method");
        this.f = findViewById(R.id.layout_send);
        this.g = (IconTextView) findViewById(R.id.tv_send);
        this.h = findViewById(R.id.iv_circle);
        this.i = findViewById(R.id.layout_bottom);
        setSendEnable(true);
        this.j = slidingClosableRelativeLayout;
        this.e = (EditText) findViewById(R.id.et_comment);
        this.c = (IconTextView) findViewById(R.id.btn_emoctions);
        this.b = (EmoticonsLayout) findViewById(R.id.layout_emoticons);
        this.b.setInputEditText(this.e);
        this.b.a(this.j);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EmoticonsWithInputLayout b;

            public void onClick(View view) {
                String str;
                String trim = this.b.e.getText().toString().trim();
                if (!b.av()) {
                    this.b.b();
                }
                if (m.a(this.b.getReplyTo()) || m.a(trim)) {
                    str = trim;
                } else {
                    str = trim.substring(this.b.getReplyTo().length());
                }
                int length = str != null ? str.length() : 0;
                if (length == 0) {
                    f.a("请输入有效评论");
                } else if (length > 200) {
                    f.a(String.format("评论最多%d字", new Object[]{Integer.valueOf(200)}));
                } else if (r.a(this.b.getContext()).a(str)) {
                    f.a("内容含有敏感词，提交失败");
                } else {
                    this.b.setSendEnable(false);
                    aVar.onSend(str);
                }
                d.a(this.b.n, this.b.b.a());
                this.b.b.setUseEmoticon(false);
            }
        });
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EmoticonsWithInputLayout a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.k = false;
                if (this.a.b.getVisibility() == 8) {
                    this.a.c.setText((int) R.string.icon_keyboard);
                    this.a.b.setVisibility(0);
                    this.a.a.hideSoftInputFromWindow(this.a.e.getWindowToken(), 0);
                    if (!this.a.l) {
                        this.a.l = true;
                        this.a.j.a(this.a.b.getRectEmoticons());
                    }
                } else {
                    this.a.c.setText((int) R.string.icon_emoji);
                    this.a.b.setVisibility(8);
                    this.a.l = false;
                    this.a.j.b(this.a.b.getRectEmoticons());
                    this.a.e.requestFocus();
                    this.a.a.showSoftInput(this.a.e, 2);
                }
                if (this.a.d != null) {
                    this.a.d.setVisibility(0);
                }
            }
        });
        this.e.addTextChangedListener(this.o);
        this.e.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ EmoticonsWithInputLayout a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.k = false;
                this.a.b.setVisibility(8);
                if (this.a.d != null) {
                    this.a.d.setVisibility(0);
                }
                return false;
            }
        });
        if (view != null) {
            this.d = view;
            this.d.setVisibility(8);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ EmoticonsWithInputLayout a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.d.setVisibility(8);
                    this.a.b();
                }
            });
        }
        c.a(this.i, ThemeElement.COMMON_BOTTOM_BAR);
        this.c.setText((int) R.string.icon_emoji);
    }

    public void setPostListId(long j) {
        this.n = j;
    }

    public void setSoftInputReplyTo(SpannableString spannableString) {
        this.e.setText(spannableString);
        Selection.setSelection(this.e.getText(), this.e.getText().length());
        this.e.requestFocus();
        this.a.toggleSoftInput(2, 0);
    }

    public void setIsShowCommentAvatarAnimation(boolean z) {
        this.k = z;
    }

    public void a() {
        this.e.setText("");
        setSendEnable(true);
        b();
        this.m = null;
    }

    public void b() {
        this.a.hideSoftInputFromWindow(this.e.getWindowToken(), 0);
        this.b.setVisibility(8);
        this.c.setText((int) R.string.icon_emoji);
    }

    public void setSendEnable(boolean z) {
        int i = 0;
        this.f.setEnabled(z);
        this.h.setVisibility(z ? 8 : 0);
        IconTextView iconTextView = this.g;
        if (!z) {
            i = 4;
        }
        iconTextView.setVisibility(i);
        if (z) {
            this.h.clearAnimation();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.h.getContext(), R.anim.unlimited_rotate);
        this.h.setAnimation(loadAnimation);
        loadAnimation.startNow();
    }

    public void c() {
        if (this.b.getVisibility() == 0) {
            this.e.clearFocus();
        }
        if (((Activity) this.e.getContext()).getWindow().getAttributes().softInputMode == 0 && this.d != null) {
            this.d.setVisibility(0);
        }
    }

    public void setmEmoticonsLayoutVisibility(int i) {
        this.b.setVisibility(i);
        if (this.d != null) {
            this.d.setVisibility(i);
        }
    }

    public boolean d() {
        this.k = false;
        this.a.hideSoftInputFromWindow(this.e.getWindowToken(), 0);
        if (this.b.getVisibility() != 0) {
            return false;
        }
        this.l = false;
        this.j.b(this.b.getRectEmoticons());
        this.b.setVisibility(8);
        return true;
    }

    public String getReplyTo() {
        return this.m;
    }

    public void setReplyTo(String str) {
        this.m = str;
    }

    public void e() {
        c.a((View) this, ThemeElement.COMMON_BOTTOM_BAR);
        v.a(this.c, ThemeElement.PLAY_BAR_TEXT);
        v.a(this.g, ThemeElement.PLAY_BAR_TEXT);
    }
}

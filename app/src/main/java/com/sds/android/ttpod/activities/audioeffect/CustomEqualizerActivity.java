package com.sds.android.ttpod.activities.audioeffect;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ActionBarActivity;
import com.sds.android.ttpod.component.d.a.b.a;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;
import com.sds.android.ttpod.widget.audioeffect.TTSeekBar;
import com.tencent.open.yyb.TitleBar;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomEqualizerActivity extends ActionBarActivity implements b {
    private static final int COUNT_EQU_BAND = 10;
    private static final int FACTOR = 100;
    private static final String GENRE_CUSTOM = "自定义/custom";
    public static final String KEY_CUSTOM_EQUALIZER = "KEY_CUSTOM_EQUALIZER";
    private static final int KNOB_HEIGHT = 42;
    private static final int KNOB_WIDTH = 26;
    private static final int SEEKBAR_MAX_VALUE = 30;
    private short[] mCustomData;
    private Map<String, Settings> mCustomEqualizerMap = new HashMap();
    private View mDefaultView;
    private TextView mEqualizerStyeName;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ CustomEqualizerActivity a;
        private com.sds.android.ttpod.component.d.a.b b = null;

        {
            this.a = r2;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_effect_custom_default:
                    this.a.startActivity(new Intent(this.a, EqualizerFragmentActivity.class));
                    f.e();
                    t.a("PAGE_CLICK", r.ACTION_EFFECT_EQULIZER_DEFAULT, s.PAGE_AUDIO_EQUALIZER, s.PAGE_AUDIO_DEFAULT);
                    return;
                case R.id.textview_effect_custom_save:
                    a();
                    f.j();
                    t.a("PAGE_CLICK", r.ACTION_EFFECT_EQULIZER_NEW, s.PAGE_AUDIO_EQUALIZER, s.PAGE_NONE);
                    return;
                case R.id.textview_effect_custom_reset:
                    Arrays.fill(this.a.mCustomData, (short) 0);
                    this.a.setEqualizer();
                    this.a.updateView();
                    this.a.mScrollView.scrollTo(0, 0);
                    f.l();
                    t.a("PAGE_CLICK", r.ACTION_EFFECT_EQULIZER_RESET, s.PAGE_AUDIO_EQUALIZER, s.PAGE_NONE);
                    return;
                default:
                    return;
            }
        }

        private void a() {
            this.b = new com.sds.android.ttpod.component.d.a.b(this.a, new a[]{new a(1, "", this.a.getNewCustomEqualizerName(), this.a.getString(R.string.effect_custom_equalizer_input_name))}, R.string.save, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                    String str = "";
                    if (bVar != null) {
                        a c = bVar.c(1);
                        if (c != null) {
                            str = c.e().toString();
                        }
                    }
                    if (m.a(str)) {
                        this.a.b.e(false);
                        com.sds.android.ttpod.component.d.f.a((int) R.string.effect_custom_equalizer_input_name);
                        return;
                    }
                    String access$600 = CustomEqualizerActivity.validateFileName(str);
                    if (access$600.equals(str)) {
                        this.a.b.e(true);
                        this.a.a.mCustomEqualizerMap.put(str, new Settings(access$600, (short) this.a.a.mCustomData.length, this.a.a.mCustomData));
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_CUSTOM_EQUALIZER, r2));
                        com.sds.android.ttpod.component.d.f.a((int) R.string.save_successfully);
                        t.a("PAGE_CLICK", r.ACTION_EFFECT_EQULIZER_NEW_OK, s.PAGE_AUDIO_EQUALIZER, s.PAGE_NONE);
                        f.k();
                        return;
                    }
                    this.a.b.e(false);
                    com.sds.android.ttpod.component.d.f.a((int) R.string.effect_custom_equalizer_input_name_invalid);
                }
            }, null);
            this.b.setTitle((int) R.string.save);
            this.b.show();
        }
    };
    private TTSeekBar.a mProgressEvent = new TTSeekBar.a(this) {
        final /* synthetic */ CustomEqualizerActivity a;
        private int b;

        {
            this.a = r1;
        }

        public void a(TTSeekBar tTSeekBar, int i) {
            for (int i2 = 0; i2 < 10; i2++) {
                if (this.a.mSeekBars[i2] == tTSeekBar) {
                    this.b = i2;
                    this.a.mSeekBarIndicators[i2].setPressed(true);
                    break;
                }
            }
            this.a.mWaveView.a(this.b, (float) this.a.mCustomData[this.b]);
        }

        public void b(TTSeekBar tTSeekBar, int i) {
            this.a.mCustomData[this.b] = (short) ((15 - i) * 100);
            this.a.mWaveView.a(this.b, (float) this.a.mCustomData[this.b]);
            com.sds.android.ttpod.framework.storage.environment.b.E(true);
            this.a.setEqualizer();
            ((Vibrator) this.a.getSystemService("vibrator")).vibrate(20);
        }

        public void c(TTSeekBar tTSeekBar, int i) {
            this.a.mWaveView.invalidate();
            this.a.mSeekBarIndicators[this.b].setPressed(false);
        }
    };
    private View mResetView;
    private View mSaveView;
    private HorizontalScrollView mScrollView;
    private ImageView[] mSeekBarIndicators;
    private TTSeekBar[] mSeekBars;
    private View mViewScrollBlock;
    private LinearLayout mViewScrollContent;
    private EqualizerAnimationWaveView mWaveView;
    private int mWidthScrollBlock;
    private int mWidthScrollContent;
    private int mWidthScrollView;
    private int mWidthWaveView;

    private static String validateFileName(String str) {
        return str == null ? null : str.replaceAll("([{/\\\\:*?\"<>|}\\u0000-\\u001f\\uD7B0-\\uFFFF]+)", "");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.effect_equalizer);
        setContentView((int) R.layout.activity_custom_equalizer);
        Settings settings = new Settings(getIntent().getStringExtra(KEY_CUSTOM_EQUALIZER));
        this.mCustomData = settings.getBandLevels();
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_CUSTOM_EQUALIZER_LIST, new Object[0]));
        initContentViews();
        updateView();
        int color = getResources().getColor(R.color.effect_dialog_background);
        View findViewById = findViewById(R.id.layout_root);
        View findViewById2 = findViewById(R.id.layout_equ_edit);
        findViewById.setBackgroundColor(color);
        getRootView().setBackgroundColor(color);
        a.a(getActionBarController());
        findViewById2.setBackgroundColor(color);
        this.mEqualizerStyeName = (TextView) findViewById(R.id.textview_effect_equalizer);
        this.mEqualizerStyeName.setText(settings.getName());
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_CUSTOM_EQUALIZER_LIST, i.a(cls, "updateCustomEqualizerList", List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_AUDIO_EFFECT_INFO, i.a(cls, "updateAudioEffectInfo", new Class[0]));
    }

    public void updateAudioEffectInfo() {
        if (!isFinishing()) {
            AudioEffectParam t = e.a(this).t();
            if (t != null) {
                Settings settings = new Settings(t.g());
                this.mCustomData = settings.getBandLevels();
                updateView();
                this.mEqualizerStyeName.setText(settings.getName());
            }
        }
    }

    protected void onResume() {
        super.onResume();
        updateAudioEffectInfo();
    }

    protected void onPause() {
        super.onPause();
        com.sds.android.ttpod.framework.storage.environment.b.k(new Settings("自定义/custom", (short) this.mCustomData.length, this.mCustomData).toString());
    }

    public void updateCustomEqualizerList(List<Settings> list) {
        if (!isFinishing() && list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                this.mCustomEqualizerMap.put(((Settings) list.get(i)).getName(), list.get(i));
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            computeViewWidths();
        }
    }

    private String getNewCustomEqualizerName() {
        String string = getString(R.string.my_preset);
        int i = 1;
        String str = string;
        while (this.mCustomEqualizerMap.containsKey(str)) {
            int i2 = i + 1;
            str = string + i;
            i = i2;
        }
        return str;
    }

    private void computeViewWidths() {
        this.mWidthScrollContent = this.mViewScrollContent.getWidth();
        this.mWidthScrollView = this.mScrollView.getWidth();
        float f = ((float) this.mWidthScrollView) / ((float) this.mWidthScrollContent);
        this.mWidthWaveView = this.mWaveView.getWidth();
        this.mWidthScrollBlock = (int) (f * ((float) ((this.mWidthWaveView - this.mWaveView.getPaddingLeft()) - this.mWaveView.getPaddingRight())));
        LayoutParams layoutParams = this.mViewScrollBlock.getLayoutParams();
        if (layoutParams.width != this.mWidthScrollBlock) {
            layoutParams.width = this.mWidthScrollBlock;
            this.mViewScrollBlock.setLayoutParams(layoutParams);
        }
    }

    private void initContentViews() {
        initWaveView();
        initEditView();
        initScrollBlock();
    }

    private void initWaveView() {
        this.mWaveView = (EqualizerAnimationWaveView) findViewById(R.id.equ_wave_custom);
        this.mWaveView.setWaveShadowColor(-66);
        this.mWaveView.setWaveShadowRadius(TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.mWaveView.setWaveColor(-9766146);
        this.mWaveView.setWaveStrokeWidth(2);
        this.mWaveView.setCoordinateVisible(true);
    }

    private void initEditView() {
        View findViewById = findViewById(R.id.layout_equ_edit);
        this.mSaveView = findViewById.findViewById(R.id.textview_effect_custom_save);
        this.mSaveView.setOnClickListener(this.mOnClickListener);
        this.mResetView = findViewById.findViewById(R.id.textview_effect_custom_reset);
        this.mResetView.setOnClickListener(this.mOnClickListener);
        this.mDefaultView = findViewById.findViewById(R.id.textview_effect_custom_default);
        this.mDefaultView.setOnClickListener(this.mOnClickListener);
        LinearLayout linearLayout = (LinearLayout) findViewById.findViewById(R.id.layout_scroll_frame);
        initSeekScrollView();
        linearLayout.addView(this.mScrollView);
    }

    private void initScrollBlock() {
        this.mViewScrollBlock = findViewById(R.id.view_scroll_block);
        this.mViewScrollBlock.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ CustomEqualizerActivity a;
            private float b = 0.0f;

            {
                this.a = r2;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float x = motionEvent.getX();
                switch (action) {
                    case 0:
                        this.b = x;
                        return true;
                    case 1:
                    case 3:
                        this.b = 0.0f;
                        return true;
                    case 2:
                        a(x - this.b);
                        return true;
                    default:
                        return false;
                }
            }

            private void a(float f) {
                this.a.mScrollView.scrollBy((int) (((float) this.a.mWidthScrollContent) * (f / ((float) this.a.mWidthWaveView))), 0);
            }
        });
    }

    private void setEqualizer() {
        Settings settings = new Settings("自定义/custom", (short) this.mCustomData.length, this.mCustomData);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_EQUALIZER, settings));
        this.mEqualizerStyeName.setText("自定义/custom");
    }

    private void initSeekScrollView() {
        initScrollContentView();
        this.mScrollView = new HorizontalScrollView(this, this) {
            final /* synthetic */ CustomEqualizerActivity a;
            private int[] b = new int[]{0, 0};

            protected void onScrollChanged(int i, int i2, int i3, int i4) {
                if (this.b[0] != i || this.b[1] != i3) {
                    this.b[0] = i;
                    this.b[1] = i3;
                    if (i == 0) {
                        a(0);
                    } else if (i == this.a.mWidthScrollContent - this.a.mWidthScrollView) {
                        a(100);
                    } else if (i != i3) {
                        b((int) ((((float) (i - i3)) / ((float) this.a.mWidthScrollContent)) * ((float) this.a.mWidthWaveView)));
                    }
                }
            }

            private void a(int i) {
                int paddingLeft;
                int i2 = 0;
                if (i == 0) {
                    paddingLeft = this.a.mWaveView.getPaddingLeft();
                    i2 = this.a.mWidthScrollBlock + paddingLeft;
                } else if (i == 100) {
                    i2 = this.a.mWaveView.getWidth() - this.a.mWaveView.getPaddingRight();
                    paddingLeft = i2 - this.a.mWidthScrollBlock;
                } else {
                    paddingLeft = 0;
                }
                this.a.mViewScrollBlock.layout(paddingLeft, this.a.mViewScrollBlock.getTop(), i2, this.a.mViewScrollBlock.getBottom());
            }

            private void b(int i) {
                int left = this.a.mViewScrollBlock.getLeft() + i;
                int right = this.a.mViewScrollBlock.getRight() + i;
                int paddingLeft = this.a.mWaveView.getPaddingLeft();
                if (left < paddingLeft) {
                    right = this.a.mWidthScrollBlock + paddingLeft;
                } else {
                    paddingLeft = left;
                }
                left = this.a.mWaveView.getPaddingRight();
                if (right > this.a.mWidthWaveView - left) {
                    right = this.a.mWidthWaveView - left;
                    paddingLeft = right - this.a.mWidthScrollBlock;
                }
                this.a.mViewScrollBlock.layout(paddingLeft, this.a.mViewScrollBlock.getTop(), right, this.a.mViewScrollBlock.getBottom());
            }
        };
        this.mScrollView.addView(this.mViewScrollContent);
    }

    private void initScrollContentView() {
        this.mViewScrollContent = new LinearLayout(this);
        this.mViewScrollContent.setOrientation(0);
        this.mViewScrollContent.setLayoutParams(new LayoutParams(-1, -2));
        this.mSeekBars = new TTSeekBar[10];
        this.mSeekBarIndicators = new ImageView[10];
        int i = (int) (getResources().getDisplayMetrics().density * 53.0f);
        int i2 = 9;
        int i3 = 16000;
        while (i2 >= 0) {
            CharSequence charSequence;
            View inflate = View.inflate(this, R.layout.audio_effect_equalizer_seekbar, null);
            this.mViewScrollContent.addView(inflate, 0, new LayoutParams(i, -1));
            this.mSeekBars[i2] = (TTSeekBar) inflate.findViewById(R.id.ttseekbar_effect_custom_equalizer);
            this.mSeekBars[i2].setKnob(R.drawable.xml_background_ttseekbar_knob);
            this.mSeekBars[i2].a(26, KNOB_HEIGHT);
            this.mSeekBars[i2].setOffset(25);
            this.mSeekBars[i2].setProgressMax(30);
            this.mSeekBars[i2].setProgressEvent(this.mProgressEvent);
            this.mSeekBarIndicators[i2] = (ImageView) inflate.findViewById(R.id.ttseekbar_effect_indicator);
            if (i3 >= 1000) {
                charSequence = String.valueOf(i3 / 1000) + 'k';
            } else {
                Object valueOf = String.valueOf(i3);
            }
            ((TextView) inflate.findViewById(R.id.textview_effect_equalizer_seekbar_title)).setText(charSequence);
            i2--;
            i3 /= 2;
        }
    }

    private void updateView() {
        this.mWaveView.setWaveValue(this.mCustomData);
        for (int i = 0; i < 10; i++) {
            this.mSeekBars[i].setProgress(15 - (this.mCustomData[i] / 100));
        }
    }

    public void onThemeLoaded() {
    }
}

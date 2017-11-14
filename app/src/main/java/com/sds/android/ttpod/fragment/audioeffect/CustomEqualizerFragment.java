package com.sds.android.ttpod.fragment.audioeffect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.audioeffect.EqualizerFragmentActivity;
import com.sds.android.ttpod.component.d.a.b.a;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.core.audioeffect.e;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;
import com.sds.android.ttpod.widget.audioeffect.TTSeekBar;
import com.tencent.open.yyb.TitleBar;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomEqualizerFragment extends BaseFragment implements b {
    private static final int COUNT_EQU_BAND = 10;
    private static final Settings DEFAULT_SETTINGS = new Settings(e.b(), (short) 10, e.b(e.b()));
    private static final int FACTOR = 100;
    private static final String GENRE_CUSTOM = "自定义/custom";
    private static final String GENRE_NORMAL = "普通/Normal";
    private static final int KNOB_HEIGHT = 42;
    private static final int KNOB_WIDTH = 26;
    private static final int SEEKBAR_MAX_VALUE = 30;
    private short[] mCustomData;
    private Map<String, Settings> mCustomEqualizerMap = new HashMap();
    private View mDefaultView;
    private String mEqualizerStyeName;
    private TextView mEqualizerStyeTextView;
    private View mFragmentView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ CustomEqualizerFragment a;
        private com.sds.android.ttpod.component.d.a.b b = null;

        {
            this.a = r2;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_effect_custom_default:
                    this.a.startActivity(new Intent(this.a.getActivity().getApplicationContext(), EqualizerFragmentActivity.class));
                    f.e();
                    t.a(r.ACTION_EFFECT_EQULIZER_DEFAULT, s.PAGE_AUDIO_DEFAULT);
                    return;
                case R.id.textview_effect_custom_save:
                    a();
                    f.j();
                    t.a(r.ACTION_EFFECT_EQULIZER_NEW, s.PAGE_NONE);
                    return;
                case R.id.textview_effect_custom_reset:
                    this.a.resetEqualizer();
                    this.a.updateView();
                    this.a.mScrollView.scrollTo(0, 0);
                    f.l();
                    t.a(r.ACTION_EFFECT_EQULIZER_RESET, s.PAGE_NONE);
                    return;
                default:
                    return;
            }
        }

        private void a() {
            this.b = new com.sds.android.ttpod.component.d.a.b(this.a.getActivity(), new a[]{new a(1, "", this.a.getNewCustomEqualizerName(), this.a.getString(R.string.effect_custom_equalizer_input_name))}, R.string.save, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
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
                    String access$500 = CustomEqualizerFragment.validateFileName(str);
                    if (access$500.equals(str)) {
                        this.a.b.e(true);
                        this.a.a.mCustomEqualizerMap.put(str, new Settings(access$500, (short) this.a.a.mCustomData.length, this.a.a.mCustomData));
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_CUSTOM_EQUALIZER, r2));
                        com.sds.android.ttpod.component.d.f.a((int) R.string.save_successfully);
                        f.k();
                        t.a("PAGE_CLICK", r.ACTION_EFFECT_EQULIZER_NEW_OK, s.PAGE_AUDIO_EQUALIZER, s.PAGE_NONE);
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
        final /* synthetic */ CustomEqualizerFragment a;
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
            this.a.setEqualizer("自定义/custom");
            ((Vibrator) this.a.getActivity().getSystemService("vibrator")).vibrate(20);
        }

        public void c(TTSeekBar tTSeekBar, int i) {
            this.a.mWaveView.invalidate();
            this.a.mSeekBarIndicators[this.b].setPressed(false);
        }
    };
    private View mResetView;
    private View mSaveView;
    private LinearLayout mScrollFrame;
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

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onThemeChanged() {
        super.onThemeChanged();
    }

    private static String validateFileName(String str) {
        return str == null ? null : str.replaceAll("([{/\\\\:*?\"<>|}\\u0000-\\u001f\\uD7B0-\\uFFFF]+)", "");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mFragmentView == null) {
            Settings settings;
            this.mFragmentView = layoutInflater.inflate(R.layout.activity_custom_equalizer, null);
            this.mEqualizerStyeTextView = (TextView) this.mFragmentView.findViewById(R.id.textview_effect_equalizer);
            AudioEffectParam t = com.sds.android.ttpod.framework.support.e.a(getActivity()).t();
            if (t != null) {
                settings = new Settings(t.g());
            } else {
                settings = new Settings(e.b(), (short) 10, e.b(e.b()));
            }
            this.mCustomData = settings.getBandLevels();
            this.mEqualizerStyeName = settings.getName();
            this.mEqualizerStyeTextView.setText(this.mEqualizerStyeName);
            d.b.a("audioeffect_balancer", e.b().equals(this.mEqualizerStyeName));
            d.b.a("audioeffect_balancer_setting", this.mEqualizerStyeName);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_CUSTOM_EQUALIZER_LIST, new Object[0]));
            initContentViews();
            updateView();
        }
        return this.mFragmentView;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_CUSTOM_EQUALIZER_LIST, i.a(cls, "updateCustomEqualizerList", List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_AUDIO_EFFECT_INFO, i.a(cls, "updateAudioEffectInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MANUAL_SETTING_EFFECT, i.a(cls, "updateAudioEffectInfo", new Class[0]));
    }

    public void updateAudioEffectInfo() {
        AudioEffectParam t = com.sds.android.ttpod.framework.support.e.a(getActivity()).t();
        if (t != null) {
            Settings settings = new Settings(t.g());
            short[] bandLevels = settings.getBandLevels();
            if (!Arrays.equals(bandLevels, this.mCustomData)) {
                this.mCustomData = bandLevels;
                updateView();
            }
            String name = settings.getName();
            if (name != null && !name.equals(this.mEqualizerStyeName)) {
                this.mEqualizerStyeName = name;
                this.mEqualizerStyeTextView.setText(settings.getName());
            }
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
        com.sds.android.ttpod.framework.storage.environment.b.k(new Settings("自定义/custom", (short) this.mCustomData.length, this.mCustomData).toString());
    }

    public void updateCustomEqualizerList(List<Settings> list) {
        if (!getActivity().isFinishing() && list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                this.mCustomEqualizerMap.put(((Settings) list.get(i)).getName(), list.get(i));
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
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
        this.mWaveView = (EqualizerAnimationWaveView) this.mFragmentView.findViewById(R.id.equ_wave_custom);
        this.mWaveView.setWaveShadowColor(-66);
        this.mWaveView.setWaveShadowRadius(TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.mWaveView.setWaveColor(-9766146);
        this.mWaveView.setWaveStrokeWidth(2);
        this.mWaveView.setCoordinateVisible(true);
    }

    private void initEditView() {
        View findViewById = this.mFragmentView.findViewById(R.id.layout_equ_edit);
        this.mSaveView = findViewById.findViewById(R.id.textview_effect_custom_save);
        this.mSaveView.setOnClickListener(this.mOnClickListener);
        this.mResetView = findViewById.findViewById(R.id.textview_effect_custom_reset);
        this.mResetView.setOnClickListener(this.mOnClickListener);
        this.mDefaultView = findViewById.findViewById(R.id.textview_effect_custom_default);
        this.mDefaultView.setOnClickListener(this.mOnClickListener);
        this.mScrollFrame = (LinearLayout) findViewById.findViewById(R.id.layout_scroll_frame);
        initSeekScrollView();
        this.mScrollFrame.addView(this.mScrollView);
    }

    public void onDestroyView() {
        this.mScrollFrame.removeView(this.mScrollView);
        super.onDestroyView();
    }

    private void initScrollBlock() {
        this.mViewScrollBlock = this.mFragmentView.findViewById(R.id.view_scroll_block);
        this.mViewScrollBlock.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ CustomEqualizerFragment a;
            private float b = 0.0f;

            {
                this.a = r2;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float x = motionEvent.getX();
                switch (action) {
                    case 0:
                        ViewParent parent = this.a.mViewScrollBlock.getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
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

    private void resetEqualizer() {
        Arrays.fill(this.mCustomData, (short) 0);
        setEqualizer(GENRE_NORMAL);
    }

    private void setEqualizer(String str) {
        Settings settings = new Settings(str, (short) this.mCustomData.length, this.mCustomData);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_EQUALIZER, settings));
        this.mEqualizerStyeTextView.setText(str);
    }

    private void initSeekScrollView() {
        initScrollContentView();
        this.mScrollView = new HorizontalScrollView(this, getActivity().getApplicationContext()) {
            final /* synthetic */ CustomEqualizerFragment a;
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

            public void onWindowFocusChanged(boolean z) {
                super.onWindowFocusChanged(z);
                if (z) {
                    this.a.computeViewWidths();
                }
            }
        };
        this.mScrollView.addView(this.mViewScrollContent);
    }

    private void initScrollContentView() {
        this.mViewScrollContent = new LinearLayout(getActivity().getApplicationContext());
        this.mViewScrollContent.setOrientation(0);
        this.mViewScrollContent.setLayoutParams(new LayoutParams(-1, -2));
        this.mSeekBars = new TTSeekBar[10];
        this.mSeekBarIndicators = new ImageView[10];
        int i = (int) (getResources().getDisplayMetrics().density * 53.0f);
        int i2 = 9;
        int i3 = 16000;
        while (i2 >= 0) {
            CharSequence charSequence;
            View inflate = View.inflate(getActivity().getApplicationContext(), R.layout.audio_effect_equalizer_seekbar, null);
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

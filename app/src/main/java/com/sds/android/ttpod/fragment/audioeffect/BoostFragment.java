package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;

public class BoostFragment extends BaseFragment {
    private static final int PADDING = 8;
    private static final float RATION_LEFT = 0.5f;
    private static final float RATION_LIMIT = 0.73f;
    private static final float RATION_RIGHT = 0.5f;
    private static final int SEEKBAR_MAX_VALUE = 10;
    private static int mBass;
    private static LinkedList<a> mBassQueue = new LinkedList();
    private static Handler mHandler = new Handler();
    private static int mTreble;
    private static LinkedList<a> mTrebleQueue = new LinkedList();
    private static int mVirtualizer;
    private static LinkedList<a> mVirtualizerQueue = new LinkedList();
    private float mBalance = -1.0f;
    private TextView mBassText;
    private boolean mBoostAjust = false;
    private a mBoostCommandRunable;
    private RadialProgressWidget mButtonBass;
    private RadialProgressWidget mButtonTreble;
    private RadialProgressWidget mButtonVirtualizer;
    private CheckedTextView mImageLimit;
    private boolean mIsEdit;
    private boolean mLimit;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ BoostFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.mImageLimit.toggle();
            this.a.judgeEffectIsEdit();
            this.a.mLimit = this.a.mImageLimit.isChecked();
            b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_BOOSTLIMIT_ENABLED, Boolean.valueOf(this.a.mLimit)));
            if (!this.a.mBoostAjust) {
                this.a.mBoostAjust = true;
                t.a("PAGE_CLICK", r.ACTION_EFFECT_BOOST_CHANNEL_LIMIT, s.PAGE_AUDIO_BOOST, s.PAGE_NONE);
                f.r();
            }
        }
    };
    private RadialProgressWidget.b mOnRotateChangeListener = new RadialProgressWidget.b(this) {
        final /* synthetic */ BoostFragment a;

        {
            this.a = r1;
        }

        public void a(RadialProgressWidget radialProgressWidget, int i) {
            if (radialProgressWidget == this.a.mButtonBass) {
                this.a.setBassValue(i);
            } else if (radialProgressWidget == this.a.mButtonTreble) {
                this.a.setTrebleValue(i);
            } else if (radialProgressWidget == this.a.mButtonVirtualizer) {
                this.a.setVirtualizerValue(i);
            }
            BoostFragment.mHandler.postDelayed(this.a.mBoostCommandRunable, 100);
        }
    };
    private OnSeekBarChangeListener mOnSeekBarChangeListener = new OnSeekBarChangeListener(this) {
        final /* synthetic */ BoostFragment a;

        {
            this.a = r1;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            float max = ((float) this.a.mSeekBarTreble.getMax()) / 2.0f;
            max = (((float) i) - max) / max;
            this.a.judgeEffectIsEdit();
            if (max != this.a.mBalance) {
                this.a.mBalance = max;
                b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_CHANNELBALANCE, Float.valueOf(max)), 10);
                this.a.judgeClickEffectBoostChanBalance();
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }
    };
    private View mRootView;
    private SeekBar mSeekBarTreble;
    private TextView mTrebleText;
    private TextView mVirtualizerText;

    private static class a implements Runnable {
        private com.sds.android.ttpod.framework.modules.a a;
        private int b;

        public a(com.sds.android.ttpod.framework.modules.a aVar, int i) {
            this.a = aVar;
            this.b = i;
        }

        public void run() {
            b.a().b(new com.sds.android.ttpod.framework.base.a.a(this.a, Integer.valueOf(RadialProgressWidget.c(this.b))));
            if (this.a == com.sds.android.ttpod.framework.modules.a.SET_BASSBOOST) {
                BoostFragment.mBass = this.b;
                BoostFragment.mBassQueue.remove(this);
            }
            if (this.a == com.sds.android.ttpod.framework.modules.a.SET_TREBLEBOOST) {
                BoostFragment.mTreble = this.b;
                BoostFragment.mTrebleQueue.remove(this);
            }
            if (this.a == com.sds.android.ttpod.framework.modules.a.SET_VIRTUALIZER) {
                BoostFragment.mVirtualizer = this.b;
                BoostFragment.mVirtualizerQueue.remove(this);
            }
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_AUDIO_EFFECT_INFO, i.a(getClass(), "updateAudioEffectInfo", new Class[0]));
    }

    public void updateAudioEffectInfo() {
        updateView(e.a(BaseApplication.e()).t());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mIsEdit = com.sds.android.ttpod.framework.storage.environment.b.an();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_audio_effect_boost_new, viewGroup, false);
            initViews();
            updateView(e.a(BaseApplication.e()).t(), true);
        }
        return this.mRootView;
    }

    private void initViews() {
        int d = com.sds.android.ttpod.common.c.a.d();
        int a = (int) (((float) (d - com.sds.android.ttpod.common.c.a.a(8))) * TTFMImageUtils.Middle_Scale);
        int a2 = (int) (((float) (d - com.sds.android.ttpod.common.c.a.a(8))) * TTFMImageUtils.Middle_Scale);
        this.mButtonBass = (RadialProgressWidget) this.mRootView.findViewById(R.id.rotatebutton_bass_boost);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a2, a2);
        this.mButtonBass.setLayoutParams(layoutParams);
        this.mButtonTreble = (RadialProgressWidget) this.mRootView.findViewById(R.id.rotatebutton_treble_boost);
        this.mButtonTreble.setLayoutParams(layoutParams);
        this.mButtonVirtualizer = (RadialProgressWidget) this.mRootView.findViewById(R.id.rotatebutton_surround);
        this.mButtonVirtualizer.setLayoutParams(layoutParams2);
        this.mImageLimit = (CheckedTextView) this.mRootView.findViewById(R.id.button_channel_limit);
        this.mImageLimit.setLayoutParams(new LinearLayout.LayoutParams((int) (((float) a2) * RATION_LIMIT), (int) (((float) a2) * RATION_LIMIT)));
        this.mSeekBarTreble = (SeekBar) this.mRootView.findViewById(R.id.seekbar_channel_balance);
        this.mBassText = (TextView) this.mRootView.findViewById(R.id.boost_bass_value);
        this.mTrebleText = (TextView) this.mRootView.findViewById(R.id.boost_treble_value);
        this.mVirtualizerText = (TextView) this.mRootView.findViewById(R.id.boost_virtualizer_value);
        this.mButtonBass.setOnRadialViewValueChanged(this.mOnRotateChangeListener);
        this.mButtonTreble.setOnRadialViewValueChanged(this.mOnRotateChangeListener);
        this.mButtonVirtualizer.setOnRadialViewValueChanged(this.mOnRotateChangeListener);
        this.mImageLimit.setOnClickListener(this.mOnClickListener);
        this.mSeekBarTreble.setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        this.mSeekBarTreble.setMax(10);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private void updateView(AudioEffectParam audioEffectParam, boolean z) {
        if (z) {
            mBass = 0;
            mTreble = 0;
            mVirtualizer = 0;
        }
        updateView(audioEffectParam);
    }

    public void updateView(AudioEffectParam audioEffectParam) {
        if (audioEffectParam != null) {
            int b = RadialProgressWidget.b(audioEffectParam.a());
            if (mBass != b) {
                mBass = b;
                this.mButtonBass.setCurrentValue(b);
                this.mBassText.setText(String.valueOf(b));
            }
            d.b.a("audioeffect_bass", this.mBassText.getText().equals(FeedbackItem.STATUS_WAITING));
            b = RadialProgressWidget.b(audioEffectParam.b());
            if (mTreble != b) {
                mTreble = b;
                this.mButtonTreble.setCurrentValue(b);
                this.mTrebleText.setText(String.valueOf(b));
            }
            d.b.a("audioeffect_high", this.mTrebleText.getText().equals(FeedbackItem.STATUS_WAITING));
            b = RadialProgressWidget.b(audioEffectParam.c());
            if (mVirtualizer != b) {
                mVirtualizer = b;
                this.mButtonVirtualizer.setCurrentValue(b);
                this.mVirtualizerText.setText(String.valueOf(b));
            }
            d.b.a("audioeffect_surround", this.mVirtualizerText.getText().equals(FeedbackItem.STATUS_WAITING));
            boolean f = audioEffectParam.f();
            boolean isChecked = this.mImageLimit.isChecked();
            if (!(this.mLimit == f && f == isChecked)) {
                this.mLimit = f;
                this.mImageLimit.setChecked(f);
                com.sds.android.ttpod.framework.storage.environment.b.F(f);
            }
            d.b.a("audioeffect_anti_broken", this.mImageLimit.isChecked());
            float e = audioEffectParam.e();
            if (this.mBalance != e) {
                this.mBalance = e;
                this.mSeekBarTreble.setProgress(Math.round((e + 1.0f) * 5.0f));
            }
            d.b.a("audioeffect_track", this.mSeekBarTreble.getProgress() == Math.round(5.0f));
        }
    }

    private void setVirtualizerValue(int i) {
        if (!this.mBoostAjust) {
            t.a("PAGE_CLICK", r.ACTION_EFFECT_BOOST_VIRTUALIZER, s.PAGE_AUDIO_BOOST, s.PAGE_NONE);
            f.q();
            this.mBoostAjust = true;
        }
        if (mVirtualizerQueue.size() > 0) {
            mHandler.removeCallbacks((Runnable) mVirtualizerQueue.remove(0));
        }
        this.mBoostCommandRunable = new a(com.sds.android.ttpod.framework.modules.a.SET_VIRTUALIZER, i);
        mVirtualizerQueue.add(this.mBoostCommandRunable);
        this.mVirtualizerText.setText(String.valueOf(i));
    }

    private void setTrebleValue(int i) {
        if (!this.mBoostAjust) {
            f.p();
            t.a("PAGE_CLICK", r.ACTION_EFFECT_BOOST_TREBLE, s.PAGE_AUDIO_BOOST, s.PAGE_NONE);
            this.mBoostAjust = true;
        }
        if (mTrebleQueue.size() > 0) {
            mHandler.removeCallbacks((Runnable) mTrebleQueue.remove(0));
        }
        this.mBoostCommandRunable = new a(com.sds.android.ttpod.framework.modules.a.SET_TREBLEBOOST, i);
        mTrebleQueue.add(this.mBoostCommandRunable);
        this.mTrebleText.setText(String.valueOf(i));
    }

    private void setBassValue(int i) {
        if (!this.mBoostAjust) {
            f.s();
            t.a("PAGE_CLICK", r.ACTION_EFFECT_BOOST_BASS, s.PAGE_AUDIO_BOOST, s.PAGE_NONE);
            this.mBoostAjust = true;
        }
        if (mBassQueue.size() > 0) {
            mHandler.removeCallbacks((Runnable) mBassQueue.remove(0));
        }
        this.mBoostCommandRunable = new a(com.sds.android.ttpod.framework.modules.a.SET_BASSBOOST, i);
        mBassQueue.add(this.mBoostCommandRunable);
        this.mBassText.setText(String.valueOf(i));
    }

    private void judgeEffectIsEdit() {
        if (!this.mIsEdit) {
            this.mIsEdit = true;
            com.sds.android.ttpod.framework.storage.environment.b.E(true);
        }
    }

    private void judgeClickEffectBoostChanBalance() {
        if (!this.mBoostAjust) {
            t.a("PAGE_CLICK", r.ACTION_EFFECT_BOOST_CHANNEL_BALANCE, s.PAGE_AUDIO_BOOST, s.PAGE_NONE);
            this.mBoostAjust = true;
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (!z) {
            this.mBoostAjust = z;
        }
    }
}

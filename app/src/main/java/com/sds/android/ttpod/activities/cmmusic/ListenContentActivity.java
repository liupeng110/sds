package com.sds.android.ttpod.activities.cmmusic;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.a.a.a.h;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.cmmusic.a.e;
import com.sds.android.ttpod.cmmusic.b.a;
import com.sds.android.ttpod.cmmusic.c.j;
import com.sds.android.ttpod.cmmusic.d.b;
import com.sds.android.ttpod.cmmusic.fragment.DjListenFragment;
import com.sds.android.ttpod.cmmusic.fragment.FunnyListenFragment;
import com.sds.android.ttpod.cmmusic.fragment.ListListenFragment;
import com.sds.android.ttpod.cmmusic.fragment.RecommendFragment;
import com.sds.android.ttpod.cmmusic.fragment.UnderlineIndicator;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ListenContentActivity extends SlidingClosableActivity implements OnPageChangeListener, OnClickListener {
    private static final int TIME_DELAY_TO_INIT = 10000;
    private Dialog mDialog;
    private OnKeyListener mDialogkey = new OnKeyListener(this) {
        final /* synthetic */ ListenContentActivity a;

        {
            this.a = r1;
        }

        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == 4 && keyEvent.getRepeatCount() == 0) {
                return true;
            }
            return false;
        }
    };
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ ListenContentActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    String str = (String) ((Hashtable) message.obj).get("code");
                    if (FeedbackItem.STATUS_WAITING.equals(str)) {
                        this.a.updateIMSI();
                        return;
                    } else if (FeedbackItem.STATUS_RECORDED.equals(str)) {
                        f.a((int) R.string.cardisnotcmcc);
                        return;
                    } else if (FeedbackItem.STATUS_SOLVED.equals(str)) {
                        f.a((int) R.string.pleasechecknetwork);
                        return;
                    } else if (b.a() && !FeedbackItem.STATUS_WAITING.equals(str)) {
                        a.a(this.a);
                        this.a.updateIMSI();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private String mImsi;
    private UnderlineIndicator mIndicator;
    private int mPageCode = s.PAGE_CMMUSIC_RECOMMEND_CODE.getValue();
    private ViewPager mViewPager;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cmmusic_sound_content_activity);
        setTitle((int) R.string.cailing);
        initActionBar();
        this.mImsi = com.sds.android.ttpod.framework.storage.environment.b.by();
        if (!m.a(this.mImsi, c.b())) {
            this.mDialog = new Dialog(this, R.style.DialogStyle);
            this.mDialog.setContentView(returnSendSmsView());
            this.mDialog.setOnKeyListener(this.mDialogkey);
            this.mDialog.show();
        }
        this.mIndicator = (UnderlineIndicator) findViewById(R.id.indicator);
        this.mIndicator.setPageCount(4);
        this.mIndicator.setCurrentItem(0);
        this.mIndicator.setSelectedColor(Color.parseColor("#9CDCFF"));
        this.mViewPager = (ViewPager) findViewById(R.id.viewPager);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        List arrayList = new ArrayList();
        RecommendFragment recommendFragment = new RecommendFragment();
        ListListenFragment listListenFragment = new ListListenFragment();
        FunnyListenFragment funnyListenFragment = new FunnyListenFragment();
        DjListenFragment djListenFragment = new DjListenFragment();
        arrayList.add(recommendFragment);
        arrayList.add(listListenFragment);
        arrayList.add(funnyListenFragment);
        arrayList.add(djListenFragment);
        this.mViewPager.setAdapter(new e(supportFragmentManager, arrayList));
        this.mViewPager.setCurrentItem(0);
        this.mViewPager.setOffscreenPageLimit(3);
        findViewById(R.id.recommend).setOnClickListener(this);
        findViewById(R.id.dj).setOnClickListener(this);
        findViewById(R.id.funny).setOnClickListener(this);
        findViewById(R.id.rank).setOnClickListener(this);
        this.mViewPager.setOnPageChangeListener(this);
    }

    private void updateIMSI() {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ ListenContentActivity a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    Thread.sleep(10000);
                    if (j.b()) {
                        com.sds.android.ttpod.framework.storage.environment.b.w(c.b());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initActionBar() {
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        actionBarController.d();
        actionBarController.c(true);
        actionBarController.d((int) R.drawable.cmmusic_img_mine).a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ ListenContentActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_PERSIONAL.getValue(), this.a.mPageCode, s.PAGE_CMMUSIC_PERSONAL_CODE.getValue()).post();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "PersionalListenControl");
                intent.putExtras(bundle);
                intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                this.a.startActivity(intent);
            }
        });
        actionBarController.d((int) R.drawable.cmmusic_search).a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ ListenContentActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_SEARCH.getValue(), this.a.mPageCode, s.PAGE_CMMUSIC_SEARCH_CODE.getValue()).post();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "SearchPage");
                intent.putExtras(bundle);
                intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                this.a.startActivity(intent);
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.btn_iknow_lintencontentpage == id) {
            com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
                final /* synthetic */ ListenContentActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    Hashtable a = h.a(this.a.getApplicationContext());
                    Message message = new Message();
                    message.what = 1;
                    message.obj = a;
                    this.a.mHandler.sendMessage(message);
                }
            });
            this.mDialog.dismiss();
        }
        if (R.id.recommend == id) {
            this.mIndicator.setCurrentItem(0);
            this.mViewPager.setCurrentItem(0);
        } else if (R.id.dj == id) {
            this.mIndicator.setCurrentItem(1);
            this.mViewPager.setCurrentItem(1);
        } else if (R.id.funny == id) {
            this.mIndicator.setCurrentItem(2);
            this.mViewPager.setCurrentItem(2);
        } else if (R.id.rank == id) {
            this.mIndicator.setCurrentItem(3);
            this.mViewPager.setCurrentItem(3);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.mIndicator.setCurrentItem(i);
        this.mViewPager.setCurrentItem(i);
        int value = s.PAGE_NONE.getValue();
        int value2 = r.ACTION_CMMUSIC_CLICK_RECOMMEND.getValue();
        switch (i) {
            case 0:
                value2 = r.ACTION_CMMUSIC_CLICK_RECOMMEND.getValue();
                value = s.PAGE_CMMUSIC_RECOMMEND_CODE.getValue();
                break;
            case 1:
                value2 = r.ACTION_CMMUSIC_CLICK_DJLISTEN.getValue();
                value = s.PAGE_CMMUSIC_DJLISTEN_CODE.getValue();
                break;
            case 2:
                value2 = r.ACTION_CMMUSIC_CLICK_FUNNY_LISTEN.getValue();
                value = s.PAGE_CMMUSIC_FUNNY_CODE.getValue();
                break;
            case 3:
                value2 = r.ACTION_CMMUSIC_CLICK_RANK.getValue();
                value = s.PAGE_CMMUSIC_RANK_CODE.getValue();
                break;
        }
        new SUserEvent("PAGE_CLICK", value2, this.mPageCode, value).post();
        this.mPageCode = value;
    }

    public void onPageScrollStateChanged(int i) {
    }

    private View returnSendSmsView() {
        View inflate = View.inflate(this, R.layout.cmmusic_sms_send_dialog, null);
        inflate.findViewById(R.id.btn_iknow_lintencontentpage).setOnClickListener(this);
        return inflate;
    }
}

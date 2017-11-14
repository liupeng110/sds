package com.sds.android.ttpod.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.b.g;
import com.sds.android.ttpod.component.g.a.a;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.PlayingFragment;
import com.sds.android.ttpod.framework.modules.skin.b.l;
import com.sds.android.ttpod.framework.modules.skin.b.u;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class PlayingListActivity extends SlidingClosableActivity implements b {
    private static final String TAG = "PlayingListActivity";
    private View mCloseView;
    private ImageView mImageViewPlayMode;
    private View mPlayModeView;
    private TextView mPlayingListTitle;
    private View mPlayingListTitleArea;
    private a mViewController;

    public static class MyPlayingFragment extends PlayingFragment {
        protected void onFlushMediaItemCountView() {
            FragmentActivity activity = getActivity();
            if (activity instanceof PlayingListActivity) {
                int size;
                List mediaItemList = getMediaItemList();
                PlayingListActivity playingListActivity = (PlayingListActivity) activity;
                Object[] objArr = new Object[1];
                if (mediaItemList != null) {
                    size = mediaItemList.size();
                } else {
                    size = 0;
                }
                objArr[0] = Integer.valueOf(size);
                playingListActivity.updateTitleText(getString(R.string.playlist_with_count, objArr));
            }
        }

        protected View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
            View mediaItemView = super.getMediaItemView(mediaItem, view, viewGroup, i);
            g gVar = (g) mediaItemView.getTag();
            gVar.b(true);
            gVar.a();
            return mediaItemView;
        }

        public void onThemeLoaded() {
            super.onThemeLoaded();
            v.a(this.mListView, ThemeElement.PLAYER_MUSIC_LIST_SEPARATOR, ThemeElement.COMMON_SEPARATOR);
            v.a(this.mListFooterView, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SUB_BAR_TEXT);
            v.a(this.mListFooterView, ThemeElement.PLAYER_MUSIC_LIST_BACKGROUND, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSlidingContainer().setEnableScrollingMask(false);
        getSlidingContainer().setShadowWidth(0);
        hideActionBar();
        initContent();
        this.mPlayingListTitleArea = findViewById(R.id.palying_list_title_area);
        if (this.mPlayingListTitleArea == null) {
            this.mPlayingListTitle = (TextView) findViewById(R.id.textview_playing);
        } else {
            this.mPlayingListTitle = (TextView) this.mPlayingListTitleArea.findViewById(R.id.textview_playing);
        }
        this.mImageViewPlayMode = (ImageView) findViewById(R.id.imagebutton_playmode);
        if (this.mImageViewPlayMode != null) {
            this.mPlayModeView = this.mImageViewPlayMode.findViewById(R.id.imagebutton_playmode);
            this.mPlayModeView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ PlayingListActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SWITCH_PLAY_MODE, new Object[0]));
                }
            });
        }
        setLaunchFragmentAttr(R.id.playing_list, R.anim.slide_in_right, R.anim.slide_out_right);
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        initViewController();
    }

    private void initViewController() {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (this.mViewController != null) {
            if (!M.isNull()) {
                this.mViewController.a(M, null, null);
                updatePlayMediaInfo();
            } else if (!this.mViewController.M()) {
                this.mViewController.a(M, null, null);
            }
        }
        updatePlayMode();
        updateSleepMode();
        updatePlayStatus(e.a(this).n());
    }

    private void initContent() {
        final OnClickListener anonymousClass2 = new OnClickListener(this) {
            final /* synthetic */ PlayingListActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        };
        View view = null;
        j l = com.sds.android.ttpod.framework.storage.a.a.a().l();
        if (l != null && l.d()) {
            l b = l.b().b(0);
            if (b != null) {
                View skinAbsoluteLayout = new SkinAbsoluteLayout(this);
                this.mViewController = new a(this, this, l, b) {
                    final /* synthetic */ PlayingListActivity b;

                    public void a(View view) {
                        super.a(view);
                        if (view != null && "CloseButton".equals(view.getTag())) {
                            anonymousClass2.onClick(view);
                        }
                    }
                };
                this.mViewController.a(new com.sds.android.ttpod.fragment.main.a(this, this.mViewController));
                View a = this.mViewController.a();
                view = a.findViewWithTag("WindowTitle");
                if (view instanceof TextView) {
                    view.setId(R.id.textview_playing);
                }
                if (a.findViewWithTag("CloseButton") == null) {
                    this.mCloseView = a.findViewById(R.id.main_content);
                    this.mCloseView.setOnClickListener(anonymousClass2);
                }
                view = a.findViewWithTag("List");
                if (view != null) {
                    view.setId(R.id.playing_list);
                } else {
                    a.setId(R.id.playing_list);
                }
                view = a.findViewWithTag("Title");
                if (view instanceof ViewGroup) {
                    view.setId(R.id.palying_list_title_area);
                    getLayoutInflater().inflate(R.layout.activity_playinglist_header, (ViewGroup) view);
                }
                skinAbsoluteLayout.setId(R.id.main_content);
                skinAbsoluteLayout.addView(a);
                setContentView(skinAbsoluteLayout);
                view = skinAbsoluteLayout;
            }
        }
        if (view == null) {
            getWindow().setWindowAnimations(16973827);
            setContentView((int) R.layout.activity_playinglist);
            this.mCloseView = findViewById(R.id.main_content).findViewById(R.id.main_content);
            this.mCloseView.setOnClickListener(anonymousClass2);
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.playing));
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, com.sds.android.ttpod.framework.storage.environment.b.m());
        getSupportFragmentManager().beginTransaction().replace(R.id.playing_list, Fragment.instantiate(this, MyPlayingFragment.class.getName(), bundle)).commitAllowingStateLoss();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mViewController != null) {
            this.mViewController.b();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.mViewController != null) {
            this.mViewController.u();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.mViewController != null) {
            this.mViewController.t();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.mViewController == null) {
            return;
        }
        if (z) {
            this.mViewController.B();
        } else {
            this.mViewController.A();
        }
    }

    public void finish() {
        super.finish();
        j l = com.sds.android.ttpod.framework.storage.a.a.a().l();
        if (l != null && l.d()) {
            u b = l.b().b(0);
            if (b != null) {
                overrideActivityOutAnimation(this, b.e());
            }
        }
    }

    private void updatePlayMode(f fVar) {
        if (this.mImageViewPlayMode != null) {
            int i;
            switch (fVar) {
                case REPEAT:
                    i = R.drawable.img_playmode_repeat_playinglist;
                    break;
                case REPEAT_ONE:
                    i = R.drawable.img_playmode_repeatone_playinglist;
                    break;
                case SHUFFLE:
                    i = R.drawable.img_playmode_shuffle_playinglist;
                    break;
                default:
                    i = R.drawable.img_playmode_sequence_playinglist;
                    break;
            }
            this.mImageViewPlayMode.setImageResource(i);
        }
        if (this.mViewController != null) {
            this.mViewController.a(fVar);
        }
    }

    public void onThemeLoaded() {
        if (this.mPlayingListTitleArea != null) {
            c.a(this.mPlayingListTitleArea, ThemeElement.SUB_BAR_BACKGROUND);
            if (this.mPlayingListTitle != null) {
                c.a(this.mPlayingListTitle, ThemeElement.SONG_LIST_ITEM_TEXT);
            }
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_MODE, i.a(cls, "updatePlayMode", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, i.a(cls, "updatePlayMediaInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
    }

    public void updatePlayMode() {
        f l = com.sds.android.ttpod.framework.storage.environment.b.l();
        updatePlayMode(l);
        if (this.mViewController != null) {
            this.mViewController.a(l);
        }
    }

    public void updateSleepMode() {
        if (this.mViewController != null) {
            this.mViewController.d(((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue());
        }
    }

    public void updatePlayMediaInfo() {
        if (this.mViewController != null) {
            this.mViewController.a(com.sds.android.ttpod.framework.storage.a.a.a().M());
            this.mViewController.a((long) e.a(this).l().intValue(), e.a(this).m());
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (this.mViewController != null) {
            this.mViewController.a(playStatus);
        }
    }

    private void updateTitleText(CharSequence charSequence) {
        if (this.mPlayingListTitle != null) {
            this.mPlayingListTitle.setText(charSequence);
        }
    }

    public static void overrideActivityInAnimation(Activity activity, int i) {
        switch (i) {
            case 1:
                activity.overridePendingTransition(R.anim.push_left_in, 0);
                return;
            case 2:
                activity.overridePendingTransition(R.anim.push_right_in, 0);
                return;
            case 3:
                activity.overridePendingTransition(R.anim.push_top_in, 0);
                return;
            case 4:
                activity.overridePendingTransition(R.anim.push_bottom_in, 0);
                return;
            case 5:
                activity.overridePendingTransition(R.anim.fade_in, 0);
                return;
            case 6:
                activity.overridePendingTransition(R.anim.scale_in, 0);
                return;
            default:
                return;
        }
    }

    public static void overrideActivityOutAnimation(Activity activity, int i) {
        switch (i) {
            case 1:
                activity.overridePendingTransition(0, R.anim.push_left_out);
                return;
            case 2:
                activity.overridePendingTransition(0, R.anim.push_right_out);
                return;
            case 3:
                activity.overridePendingTransition(0, R.anim.push_top_out);
                return;
            case 4:
                activity.overridePendingTransition(0, R.anim.push_bottom_out);
                return;
            case 5:
                activity.overridePendingTransition(0, R.anim.fade_out);
                return;
            case 6:
                activity.overridePendingTransition(0, R.anim.scale_out);
                return;
            default:
                return;
        }
    }

    protected boolean needFinishAnimation() {
        return false;
    }
}

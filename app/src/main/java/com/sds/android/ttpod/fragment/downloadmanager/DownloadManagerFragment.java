package com.sds.android.ttpod.fragment.downloadmanager;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.d.a.m;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.MVGuideFragment;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.findsong.DownloadedMVFragment;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.MediaListFragment;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.util.ArrayList;
import java.util.List;

public class DownloadManagerFragment extends SlidingClosableFragment {
    public static final String DOWNLOAD_TYPE = "download_type";
    public static final String FRAGMENT_TAB_ID = "fragment_tab_index";
    private static final int ID_FRAGMENT_COMPLETED_AUDIOS = 0;
    private static final int ID_FRAGMENT_COMPLETED_VIDEOS = 1;
    private static final int ID_FRAGMENT_UNCOMPLETED = 2;
    private static final String TAG = "DownloadManagerFragment";
    private static Boolean mIsShowDownloadMvGuideEnable = null;
    private int mCurrentItem;
    private Integer mDownloadType = DownloadTaskInfo.TYPE_AUDIO;
    private OnClickListener mNoDataOnClickListener = new OnClickListener(this) {
        final /* synthetic */ DownloadManagerFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.finish();
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.TOGGLE_FIND_SONG_FRAGMENT, new Object[0]), (int) SecExceptionCode.SEC_ERROR_STA_ENC);
        }
    };
    private OnPageChangeListener mOnPageChangeListener;
    private OnPageChangeListener mOnPageChangeListenerDefault = new OnPageChangeListener(this) {
        final /* synthetic */ DownloadManagerFragment a;

        {
            this.a = r1;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (this.a.mOnPageChangeListener != null) {
                this.a.mOnPageChangeListener.onPageSelected(i);
            }
            t.a(i == 0 ? r.ACTION_MY_DOWNLOAD_TO_DOWNLOADED : r.ACTION_MY_DOWNLOAD_TO_DOWNLOADING, i == 0 ? s.PAGE_MY_DOWNLOAD_DOWNLOADED : s.PAGE_MY_DOWNLOAD_DOWNLOADING);
            this.a.mCurrentItem = i;
            this.a.setCurrentPosition(i);
        }

        public void onPageScrollStateChanged(int i) {
        }
    };
    private e mPagerAdapter;
    private SlidingTabHost mTabHost;
    private int mTargetPage = 0;
    private View mTopView;
    private ViewPager mViewPager;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            if (getArguments().containsKey(FRAGMENT_TAB_ID)) {
                this.mTargetPage = getArguments().getInt(FRAGMENT_TAB_ID);
            }
            if (getArguments().containsKey(DOWNLOAD_TYPE)) {
                this.mDownloadType = Integer.valueOf(getArguments().getInt(DOWNLOAD_TYPE));
                setStatisticPageProperties(DOWNLOAD_TYPE, this.mDownloadType);
            }
        }
    }

    public void setPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    protected boolean needMenuAction() {
        return false;
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.a();
        }
        this.mTabHost.setOnPageChangeListener(null);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_downloadmanager, viewGroup, false);
        this.mTopView = inflate;
        getActionBarController().b((int) R.string.mine_my_download);
        getActionBarController().e(R.string.delete_all_download).a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ DownloadManagerFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                com.sds.android.ttpod.framework.a.b.b.a("my_download_delete_all");
                if (this.a.mCurrentItem == 0) {
                    this.a.deleteDownloadedAudio();
                } else if (1 == this.a.mCurrentItem) {
                    ((DownloadedMVFragment) this.a.mPagerAdapter.getItem(this.a.mCurrentItem)).deleteAllCompleted();
                } else if (2 == this.a.mCurrentItem) {
                    ((DownloadTaskListFragment) this.a.mPagerAdapter.getItem(this.a.mCurrentItem)).onDropDownMenuClicked(9);
                }
                t.a(r.ACTION_MY_DOWNLOAD_DELETE_ALL, s.PAGE_NONE);
            }
        });
        this.mTabHost = (SlidingTabHost) inflate.findViewById(R.id.tabhost);
        this.mTabHost.setTabLayoutAverageSpace(true);
        this.mTabHost.setTextSize(getResources().getDimensionPixelSize(R.dimen.sliding_tabhost_textsize));
        this.mViewPager = (ViewPager) inflate.findViewById(R.id.viewpager);
        this.mPagerAdapter = new e(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mTabHost.setViewPager(this.mViewPager);
        this.mTabHost.setOnPageChangeListener(this.mOnPageChangeListenerDefault);
        this.mViewPager.setOffscreenPageLimit(this.mPagerAdapter.getCount());
        setCurrentPage(this.mTargetPage);
        setCurrentPosition(this.mTargetPage);
        return inflate;
    }

    private void tryShowDownloadMvGuideView() {
        if (mIsShowDownloadMvGuideEnable == null) {
            mIsShowDownloadMvGuideEnable = Boolean.valueOf(com.sds.android.ttpod.framework.storage.environment.b.aS());
            if (mIsShowDownloadMvGuideEnable.booleanValue()) {
                new MVGuideFragment(getMyDownloadGuideRect(), R.drawable.mv_guide_my_download_description).show(getChildFragmentManager(), "MyDownload");
                com.sds.android.ttpod.framework.storage.environment.b.W(false);
            }
        }
    }

    private RectF getMyDownloadGuideRect() {
        int d = (int) (((float) com.sds.android.ttpod.common.c.a.d()) / 3.0f);
        int dimension = (int) getResources().getDimension(R.dimen.dialog_header_height);
        return new RectF((float) d, (float) dimension, (float) (d + d), (float) (dimension + ((int) getResources().getDimension(R.dimen.tab_label_height))));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        tryShowDownloadMvGuideView();
    }

    private void deleteDownloadedAudio() {
        f.a(getActivity(), (int) R.string.download_remove_file_message, getString(R.string.delete_all_download), getString(R.string.download_remove_all_songs_confirm_hint), new com.sds.android.ttpod.common.a.a.a<m>(this) {
            final /* synthetic */ DownloadManagerFragment a;

            {
                this.a = r1;
            }

            public void a(m mVar) {
                t.a(r.ACTION_MY_DOWNLOAD_DELETE_ALL_SURE, s.PAGE_NONE);
                l.aB();
                b.a().b(new a(com.sds.android.ttpod.framework.modules.a.DELETE_ALL_FINISHED_DOWNLOAD_TASK, this.a.mDownloadType, Boolean.valueOf(mVar.b())));
            }
        }, null);
    }

    public void onResume() {
        super.onResume();
        if (this.mDownloadType == DownloadTaskInfo.TYPE_AUDIO) {
            cancelCompletedAndErrorNotification();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mDownloadType == DownloadTaskInfo.TYPE_AUDIO) {
            com.sds.android.ttpod.framework.storage.environment.b.X(false);
            cancelCompletedAndErrorNotification();
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        v.a(this.mTabHost);
    }

    public void setCurrentPage(int i) {
        if (this.mViewPager != null && i != this.mCurrentItem) {
            this.mViewPager.setCurrentItem(i);
        }
    }

    private void cancelCompletedAndErrorNotification() {
        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.CLEAR_COMPLETE_TASK_COUNT, new Object[0]));
        com.sds.android.ttpod.framework.a.m.a(15121730);
        com.sds.android.ttpod.framework.a.m.a(15121740);
    }

    private List<e.a> buildFragmentBinders() {
        List<e.a> arrayList = new ArrayList();
        arrayList.add(new e.a(0, (int) R.string.downloaded_songs, 0, getCompletedSongDownloadFragment()));
        arrayList.add(new e.a(1, (int) R.string.downloaded_mv, 0, getCompletedMVDownloadFragment()));
        arrayList.add(new e.a(2, (int) R.string.download_running, 0, getUncompletedDownloadFragment()));
        return arrayList;
    }

    protected Fragment getCompletedSongDownloadFragment() {
        Bundle bundle = new Bundle(1);
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_DOWNLOAD);
        MediaListFragment mediaListFragment = (MediaListFragment) Fragment.instantiate(getActivity(), MediaListFragment.class.getName(), bundle);
        mediaListFragment.setStatisticPage(s.PAGE_MY_DOWNLOAD_DOWNLOADED);
        mediaListFragment.setStatisticPageProperties(DOWNLOAD_TYPE, this.mDownloadType);
        mediaListFragment.setNoDataViewMessage(R.string.icon_male, R.string.no_song_go_recommend);
        mediaListFragment.setNoDataViewOnClickListener(this.mNoDataOnClickListener);
        mediaListFragment.setOnTaskCountChangeListener(new DownloadTaskListFragment.b(this) {
            final /* synthetic */ DownloadManagerFragment a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (this.a.getActivity() != null) {
                    CharSequence string;
                    if (i == 0) {
                        string = this.a.getString(R.string.downloaded_songs);
                    } else {
                        string = this.a.getString(R.string.downloaded_songs_with_count, Integer.valueOf(i));
                    }
                    this.a.updateTabTitleAt(0, string);
                }
            }
        });
        return mediaListFragment;
    }

    protected Fragment getCompletedMVDownloadFragment() {
        DownloadedMVFragment downloadedMVFragment = (DownloadedMVFragment) Fragment.instantiate(getActivity(), DownloadedMVFragment.class.getName(), null);
        downloadedMVFragment.setOnTaskCountChangeListener(new DownloadTaskListFragment.b(this) {
            final /* synthetic */ DownloadManagerFragment a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (this.a.getActivity() != null) {
                    CharSequence string;
                    if (i == 0) {
                        string = this.a.getString(R.string.downloaded_mv);
                    } else {
                        string = this.a.getString(R.string.downloaded_mv_with_count, Integer.valueOf(i));
                    }
                    this.a.updateTabTitleAt(1, string);
                }
            }
        });
        return downloadedMVFragment;
    }

    protected Fragment getUncompletedDownloadFragment() {
        Bundle bundle = new Bundle(1);
        bundle.putInt(DOWNLOAD_TYPE, this.mDownloadType.intValue());
        DownloadTaskListFragment downloadTaskListFragment = (DownloadTaskListFragment) Fragment.instantiate(getActivity(), UncompletedDownloadFragment.class.getName(), bundle);
        downloadTaskListFragment.setStatisticPage(s.PAGE_MY_DOWNLOAD_DOWNLOADING);
        downloadTaskListFragment.setNoDataViewOnClickListener(this.mNoDataOnClickListener);
        downloadTaskListFragment.setOnTaskCountChangeListener(new DownloadTaskListFragment.b(this) {
            final /* synthetic */ DownloadManagerFragment a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (this.a.getActivity() != null) {
                    CharSequence string;
                    if (i == 0) {
                        string = this.a.getString(R.string.download_running);
                    } else {
                        string = this.a.getString(R.string.download_running_with_count, Integer.valueOf(i));
                    }
                    this.a.updateTabTitleAt(2, string);
                }
            }
        });
        return downloadTaskListFragment;
    }

    protected void updateTabTitleAt(int i, CharSequence charSequence) {
        this.mTabHost.a(i, charSequence);
    }

    private void setCurrentPosition(int i) {
        int i2 = 0;
        if (isSlidingAtTheLeftEdge(i)) {
            i2 = 2;
        } else if (isSlidingAtTheRightEdge(i)) {
            i2 = 1;
        }
        setSlidingCloseMode(i2);
    }

    private boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    private boolean isSlidingAtTheRightEdge(int i) {
        return i == this.mPagerAdapter.getCount() + -1;
    }
}

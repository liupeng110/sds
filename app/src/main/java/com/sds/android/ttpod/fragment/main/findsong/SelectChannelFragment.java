package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.ChannelOutline;
import com.sds.android.cloudapi.ttpod.data.Dimension;
import com.sds.android.cloudapi.ttpod.data.DimensionSelection;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.DimensionsResult;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SelectChannelFragment extends BaseFragment {
    private static final DimensionSelection NULL_SELECTION = new DimensionSelection(0, 0);
    private List<a> mAdapters = new ArrayList();
    private List<com.sds.android.ttpod.fragment.main.findsong.base.a> mDimenLayoutHolders = new ArrayList();
    private String mOldSelection = "";
    private ViewGroup mRootView;
    private View mSendSelectButton;
    private StateView mStateView;

    private final class a extends BaseAdapter {
        final /* synthetic */ SelectChannelFragment a;
        private final Dimension b;
        private final List<ChannelOutline> c;
        private int d = -1;

        public a(SelectChannelFragment selectChannelFragment, Dimension dimension) {
            this.a = selectChannelFragment;
            this.b = dimension;
            this.c = this.b.getChannelOutlines();
            for (ChannelOutline channelOutline : this.c) {
                if (channelOutline.isPrefered()) {
                    this.d = this.c.indexOf(channelOutline);
                    return;
                }
            }
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return this.c.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            boolean z = false;
            View inflate = this.a.getLayoutInflater(null).inflate(R.layout.layout_select_channel_grid_item, viewGroup, false);
            if (i == this.d) {
                z = true;
            }
            a(inflate, z);
            TextView textView = (TextView) inflate.findViewById(R.id.select_item_channel_name);
            textView.setText(((ChannelOutline) this.c.get(i)).getName());
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.d = i;
                    this.b.notifyDataSetChanged();
                }
            });
            return inflate;
        }

        private void a(View view, boolean z) {
            View view2 = (TextView) view.findViewById(R.id.select_item_channel_name);
            View findViewById = view.findViewById(R.id.select_item_channel_background);
            if (z) {
                int dimensionPixelSize = this.a.getResources().getDimensionPixelSize(R.dimen.select_channel_grid_item_height);
                c.a(view2, ThemeElement.COMMON_TITLE_TEXT);
                c.a(findViewById, -1, dimensionPixelSize, ThemeElement.TOP_BAR_BACKGROUND);
                return;
            }
            c.a(view2, ThemeElement.TILE_SUB_TEXT);
            c.a(findViewById, ThemeElement.TILE_MASK);
        }

        public DimensionSelection a() {
            if (this.d < 0) {
                return SelectChannelFragment.NULL_SELECTION;
            }
            return new DimensionSelection(this.b.getDimensionId(), ((ChannelOutline) this.c.get(this.d)).getChannelId());
        }

        public String b() {
            if (this.d < 0) {
                return FeedbackItem.STATUS_WAITING;
            }
            return String.valueOf(((ChannelOutline) this.c.get(this.d)).getChannelId());
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_CHANNEL_DIMENSIONS, i.a(getClass(), "updateChannelDimensions", DimensionsResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SELECT_CHANNEL, i.a(getClass(), "updateSelectChannel", Integer.class));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = (ViewGroup) layoutInflater.inflate(R.layout.fragment_select_channel, viewGroup, false);
        this.mStateView = new StateView(getActivity());
        this.mStateView.setLayoutParams(new LayoutParams(-1, -1));
        this.mStateView.setSuccessView(this.mRootView);
        this.mStateView.setFailedView(layoutInflater.inflate(R.layout.loadingview_failed, this.mStateView, false));
        this.mStateView.setOnRetryRequestListener(new com.sds.android.ttpod.widget.StateView.a(this) {
            final /* synthetic */ SelectChannelFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.mStateView.setState(b.LOADING);
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ACQUIRE_CHANNEL_DIMENSIONS, Long.valueOf(this.a.getArguments().getLong(StarCategory.KEY_STAR_CATEGORY_ID)), toString()));
            }
        });
        this.mSendSelectButton = this.mRootView.findViewById(R.id.find_song_select_channel_send);
        this.mSendSelectButton.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SelectChannelFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!y.a()) {
                    if (EnvironmentUtils.c.e()) {
                        this.a.statisticSendAction();
                        this.a.doAliStats();
                        List arrayList = new ArrayList();
                        String str = "";
                        for (a a : this.a.mAdapters) {
                            DimensionSelection a2 = a.a();
                            String str2 = str + a2.format();
                            if (a2 != SelectChannelFragment.NULL_SELECTION) {
                                arrayList.add(a2);
                            }
                            str = str2;
                        }
                        if (str.equals(this.a.mOldSelection)) {
                            this.a.finishSelf();
                            return;
                        } else if (arrayList.size() > 0) {
                            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SEND_SELECT_CHANNEL, arrayList));
                            return;
                        } else {
                            f.a((int) R.string.please_select);
                            return;
                        }
                    }
                    f.a((int) R.string.network_unavailable);
                }
            }
        });
        this.mStateView.setState(b.LOADING);
        return this.mStateView;
    }

    private void doAliStats() {
        if (this.mAdapters != null && this.mAdapters.size() >= 2) {
            d.c.b(((a) this.mAdapters.get(0)).b(), ((a) this.mAdapters.get(1)).b());
        }
    }

    private void statisticSendAction() {
        String str = "";
        int i = 0;
        while (i < this.mAdapters.size()) {
            if (i > 0) {
                str = str + SelectCountryActivity.SPLITTER;
            }
            String str2 = str + ((a) this.mAdapters.get(i)).b();
            i++;
            str = str2;
        }
        SEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_SEND_CHANNEL_SELECTION.getValue(), 0, s.PAGE_ONLINE_FIND_SONG.getValue());
        sUserEvent.append("channel_id", str);
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    public void onLoadFinished() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ACQUIRE_CHANNEL_DIMENSIONS, Long.valueOf(getArguments().getLong(StarCategory.KEY_STAR_CATEGORY_ID)), toString()));
    }

    public void updateChannelDimensions(DimensionsResult dimensionsResult, String str) {
        if (dimensionsResult.isSuccess() && j.b(dimensionsResult.getDataList())) {
            this.mOldSelection = "";
            this.mStateView.setState(b.SUCCESS);
            ViewGroup viewGroup = (ViewGroup) this.mRootView.findViewById(R.id.find_song_select_container);
            Iterator it = dimensionsResult.getDataList().iterator();
            while (it.hasNext()) {
                com.sds.android.ttpod.fragment.main.findsong.base.a makeSelectChannelView = makeSelectChannelView((Dimension) it.next());
                this.mDimenLayoutHolders.add(makeSelectChannelView);
                viewGroup.addView(makeSelectChannelView.b());
            }
            onThemeLoaded();
            return;
        }
        this.mStateView.setState(b.FAILED);
        f.a((int) R.string.network_unavailable);
    }

    private com.sds.android.ttpod.fragment.main.findsong.base.a makeSelectChannelView(Dimension dimension) {
        View inflate = getLayoutInflater(null).inflate(R.layout.layout_select_channel, this.mRootView, false);
        ((TextView) inflate.findViewById(R.id.select_dimension_name)).setText(dimension.getName());
        GridView gridView = (GridView) inflate.findViewById(R.id.select_dimension_grid);
        Object aVar = new a(this, dimension);
        this.mAdapters.add(aVar);
        gridView.setAdapter(aVar);
        this.mOldSelection += aVar.a().format();
        return new com.sds.android.ttpod.fragment.main.findsong.base.a(inflate);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateView.onThemeLoaded();
        for (com.sds.android.ttpod.fragment.main.findsong.base.a a : this.mDimenLayoutHolders) {
            a.a();
        }
        c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
    }

    public void updateSelectChannel(Integer num) {
        if (num.intValue() == 1) {
            finishSelf();
        } else {
            f.a((int) R.string.network_error);
        }
    }

    private void finishSelf() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof SlidingClosableFragment)) {
            ((SlidingClosableFragment) parentFragment).finish();
        }
    }
}

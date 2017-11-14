package com.ttfm.android.sdk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.RadioCategory;
import com.sds.android.cloudapi.ttpod.data.RadioCategoryChannel;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.a;
import com.sds.android.ttpod.widget.StateView.b;
import com.ttfm.android.sdk.utils.FastDoubleClick;
import java.util.ArrayList;
import java.util.List;

public class NewFMDetailFragment extends BaseFragment implements OnClickListener, OnScrollListener {
    private static final String TAG = "NewFMDetailFragment";
    private static String mActiveChannelTitle = "";
    private static int mCurrentChannelState;
    private NewFMChannelListAdapter mAdapter;
    protected ListView mListView;
    RadioCategory mRadioCategory;
    ArrayList<RadioCategoryChannel> mRadioCategoryChannels = new ArrayList();
    private StateView mStateView;

    class NewFMChannelListAdapter extends BaseAdapter {
        private ArrayList<RadioCategoryChannel> mChannelInfos = new ArrayList();
        private Context mContext;
        private OnClickListener mOnClickListener = null;

        class ViewHolder {
            ImageView imageView;
            TextView name;
            ImageView play;
            TextView songCount;

            ViewHolder() {
            }
        }

        public NewFMChannelListAdapter(Context context, OnClickListener onClickListener) {
            this.mContext = context;
            this.mOnClickListener = onClickListener;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            View view2;
            RadioCategoryChannel radioCategoryChannel = (RadioCategoryChannel) this.mChannelInfos.get(i);
            if (view == null) {
                view = LayoutInflater.from(this.mContext).inflate(R.layout.ttfm_channel_list_item, null);
                ViewHolder viewHolder2 = new ViewHolder();
                viewHolder2.imageView = (ImageView) view.findViewById(R.id.channel_item_img);
                viewHolder2.name = (TextView) view.findViewById(R.id.channel_item_name);
                viewHolder2.songCount = (TextView) view.findViewById(R.id.song_count);
                viewHolder2.play = (ImageView) view.findViewById(R.id.channel_item_play);
                view.setTag(viewHolder2);
                viewHolder = viewHolder2;
                view2 = view;
            } else {
                viewHolder = (ViewHolder) view.getTag();
                view2 = view;
            }
            if (radioCategoryChannel == null) {
                viewHolder.name.setText("");
                viewHolder.imageView.setImageResource(R.drawable.img_music_default_icon);
                view2.setTag(R.id.channel_obj, radioCategoryChannel);
                return view2;
            }
            viewHolder.play.setOnClickListener(this.mOnClickListener);
            viewHolder.imageView.setOnClickListener(this.mOnClickListener);
            viewHolder.imageView.setTag(R.id.channel_item_img, Integer.valueOf(i));
            viewHolder.play.setTag(R.id.channel_item_play, Integer.valueOf(i));
            int i2 = ((NewFMDetailFragment.mCurrentChannelState == 2 || NewFMDetailFragment.mCurrentChannelState == 4) && isPlayingItem(radioCategoryChannel)) ? 1 : 0;
            if (i2 != 0) {
                viewHolder.play.setImageResource(R.drawable.img_rank_play_paused);
            } else {
                viewHolder.play.setImageResource(R.drawable.img_rank_play_normal);
            }
            if (radioCategoryChannel.getCategoryChannelName() != null) {
                viewHolder.name.setText(radioCategoryChannel.getCategoryChannelName());
            } else {
                viewHolder.name.setText("");
            }
            viewHolder.songCount.setText("歌曲数量：" + radioCategoryChannel.getCount());
            g.a(viewHolder.imageView, radioCategoryChannel.get240X200PicUrl(), 0, 0, (int) R.drawable.img_music_default_icon);
            view2.setTag(R.id.channel_obj, radioCategoryChannel);
            c.a(viewHolder.songCount, ThemeElement.TILE_SUB_TEXT);
            c.a(viewHolder.name, ThemeElement.SONG_LIST_ITEM_TEXT);
            c.a(view2, ThemeElement.TILE_BACKGROUND);
            return view2;
        }

        private boolean isPlayingItem(RadioCategoryChannel radioCategoryChannel) {
            return m.a(NewFMDetailFragment.mActiveChannelTitle, com.sds.android.ttpod.component.c.c.a(radioCategoryChannel));
        }

        public int getCount() {
            if (this.mChannelInfos != null) {
                return this.mChannelInfos.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            if (i < 0 || i >= this.mChannelInfos.size()) {
                return null;
            }
            return this.mChannelInfos.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public void setListData(List<RadioCategoryChannel> list) {
            if (list != null) {
                if (this.mChannelInfos == null) {
                    this.mChannelInfos = new ArrayList();
                }
                this.mChannelInfos.clear();
                this.mChannelInfos.addAll(list);
                notifyDataSetChanged();
            }
        }

        public ArrayList<RadioCategoryChannel> getList() {
            return this.mChannelInfos;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mRadioCategory = (RadioCategory) arguments.getSerializable(NewFMMainFragment.KEY_FM_DATA);
            this.mRadioCategoryChannels = this.mRadioCategory.getRadioCategoryChannels();
        }
    }

    public void setCurrentChannelState(int i) {
        mCurrentChannelState = i;
    }

    public void setActiveChannelTitle(String str) {
        mActiveChannelTitle = str;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ttfm_base_list_view, viewGroup, false);
        this.mListView = (ListView) inflate.findViewById(R.id.ttfm_listview);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!FastDoubleClick.isFastDoubleClick()) {
                    NewFMDetailFragment.this.onListItemClick(adapterView, view, i, j);
                }
            }
        });
        this.mListView.setOnScrollListener(this);
        this.mStateView = (StateView) inflate.findViewById(R.id.ttfm_stateview);
        this.mStateView.setOnRetryRequestListener(new a() {
            public void onRetryRequested() {
                NewFMDetailFragment.this.onRetryRequested();
            }
        });
        this.mStateView.setState(b.SUCCESS);
        this.mStateView.setVisibility(8);
        this.mAdapter = new NewFMChannelListAdapter(getActivity(), this);
        this.mListView.setAdapter(this.mAdapter);
        return inflate;
    }

    private void onListItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        playChannel(i, "radio_list_item");
    }

    private void playChannel(int i, String str) {
        NewFMMainFragment newFMMainFragment = (NewFMMainFragment) getParentFragment();
        if (newFMMainFragment != null && this.mRadioCategoryChannels.size() > 0) {
            RadioCategoryChannel radioCategoryChannel = (RadioCategoryChannel) this.mRadioCategoryChannels.get(i);
            new com.sds.android.ttpod.framework.a.b.c("click").a(str).a("radio_id", String.valueOf(radioCategoryChannel.getCategoryChannelId())).a("radio_name", radioCategoryChannel.getCategoryChannelName()).a();
            newFMMainFragment.performItemClick(radioCategoryChannel);
        }
    }

    private void onRetryRequested() {
    }

    public void updateView() {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateView.onThemeLoaded();
        c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        updateView();
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        this.mAdapter.setListData(this.mRadioCategoryChannels);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.channel_item_img || id == R.id.channel_item_play) {
            playChannel(((Integer) view.getTag(id)).intValue(), "quick_play");
        }
    }
}

package com.sds.android.ttpod.fragment.main.list;

import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.Map;

public class PlayerMediaListFragment extends PlayingFragment {

    private class a extends com.sds.android.ttpod.framework.modules.theme.c.a {
        final /* synthetic */ PlayerMediaListFragment a;
        private View b;
        private TextView c;
        private TextView d;
        private Animation e;

        public a(PlayerMediaListFragment playerMediaListFragment, View view) {
            this.a = playerMediaListFragment;
            this.b = view;
            this.c = (TextView) view.findViewById(R.id.title_view);
            this.d = (TextView) view.findViewById(R.id.media_item_singer);
            this.e = (Animation) view.findViewById(R.id.play_icon);
        }

        public TextView b() {
            return this.c;
        }

        public TextView c() {
            return this.d;
        }

        public void a(boolean z, PlayStatus playStatus) {
            this.b.setSelected(z);
            if (z) {
                int i;
                this.e.setVisibility(0);
                AnimationDrawable e = v.e();
                if (e.getNumberOfFrames() == 0) {
                    e = (AnimationDrawable) this.a.getResources().getDrawable(R.drawable.xml_spectrum_animation);
                    int dimensionPixelSize = this.a.getResources().getDimensionPixelSize(R.dimen.spectrum_padding);
                    this.e.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
                } else {
                    this.e.setPadding(0, 0, 0, 0);
                }
                this.e.setAnimationDrawable(e);
                if (playStatus == PlayStatus.STATUS_PLAYING) {
                    i = 1;
                } else {
                    i = 0;
                }
                this.e.a();
                if (i == 0) {
                    this.e.b();
                    return;
                }
                return;
            }
            this.e.setVisibility(4);
            this.e.b();
        }

        public void a() {
            c.a(this.b, ThemeElement.PLAYER_MUSIC_LIST_BACKGROUND);
            c.a(this.c, ThemeElement.PLAYER_MUSIC_LIST_TEXT);
            c.a(this.d, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT);
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.ON_PLAYING_PANEL_SHOW, i.a(getClass(), "scrollToPlayingRow", new Class[0]));
    }

    protected boolean isAZSideBarEnable() {
        return false;
    }

    protected void configListFooterView(View view) {
    }

    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        return null;
    }

    public void updateAsyncloadMediaItemList(String str, AsyncLoadMediaItemList asyncLoadMediaItemList) {
        super.updateAsyncloadMediaItemList(str, asyncLoadMediaItemList);
        if (str.equals(this.mGroupID)) {
            this.mListView.setFastScrollEnabled(false);
        }
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        View onCreateFailedView = super.onCreateFailedView(layoutInflater);
        onCreateFailedView.setEnabled(false);
        return onCreateFailedView;
    }

    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        iconTextView.setText((int) R.string.icon_no_playing);
        textView.setText(R.string.no_song_playing);
    }

    protected View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
        if (view == null) {
            view = getLayoutInflater(null).inflate(R.layout.player_media_list_item, null);
            view.setTag(new a(this, view));
        }
        bindView((a) view.getTag(), mediaItem);
        return view;
    }

    protected void onMediaItemClicked(MediaItem mediaItem, int i) {
        super.onMediaItemClicked(mediaItem, i);
    }

    private void bindView(a aVar, MediaItem mediaItem) {
        aVar.b().setText(mediaItem.getTitle());
        aVar.c().setText(mediaItem.getArtist());
        boolean z = m.a(this.mGroupID, b.m()) && m.a(b.n(), mediaItem.getID());
        aVar.a(z, this.mPlayStatus);
        aVar.a(v.b());
    }

    public void onThemeLoaded() {
        v.a(this.mListView, ThemeElement.PLAYER_MUSIC_LIST_SEPARATOR, ThemeElement.COMMON_SEPARATOR);
        v.b(this.mFailedView);
    }

    protected void onMediaItemLongClicked(MediaItem mediaItem, int i) {
    }

    public void scrollToPlayingRow() {
        selectRow(e.a(BaseApplication.e()).s());
    }
}

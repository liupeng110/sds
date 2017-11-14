package com.sds.android.ttpod.activities.soundsearch;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.b.g;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SoundSearchMediaListActivity extends SlidingClosableActivity {
    private static final int MAX_RATING = 100;
    private a mAdapter;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener(this) {
        final /* synthetic */ SoundSearchMediaListActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (m.a(this.a.mRecognizeResultListView.getHeaderViewsCount(), i, this.a.mAdapter.getCount()) >= 0) {
                List arrayList = new ArrayList();
                for (SoundSearchInfo f : this.a.mAdapter.b) {
                    arrayList.add(f.f());
                }
                b.K(false);
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, arrayList));
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, arrayList.get(r2)));
            }
        }
    };
    private OnItemLongClickListener mOnItemLongClickListener = new OnItemLongClickListener(this) {
        final /* synthetic */ SoundSearchMediaListActivity a;

        {
            this.a = r1;
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.showContextMenu(this.a.mAdapter.a(i).f());
            return true;
        }
    };
    private long mPlayedSongId;
    private ListView mRecognizeResultListView;

    class a extends BaseAdapter {
        final /* synthetic */ SoundSearchMediaListActivity a;
        private SoundSearchInfo[] b;

        class a {
            final /* synthetic */ a a;
            private View b;
            private View c;
            private ImageView d;
            private View e;
            private TextView f;
            private TextView g;
            private TextView h;
            private TextView i;

            public a(a aVar, View view) {
                this.a = aVar;
                this.b = view.findViewById(R.id.recognizer_item);
                this.c = view.findViewById(R.id.active_flag);
                this.d = (ImageView) view.findViewById(R.id.toggle_favorite_imageview);
                this.e = view.findViewById(R.id.context_menu);
                this.f = (TextView) view.findViewById(R.id.textview_soundsearch_title);
                this.g = (TextView) view.findViewById(R.id.textview_soundsearch_album);
                this.h = (TextView) view.findViewById(R.id.textview_soundsearch_favorite_count);
                this.i = (TextView) view.findViewById(R.id.media_similarity);
            }

            public void a(SoundSearchInfo soundSearchInfo) {
                final MediaItem f = soundSearchInfo.f();
                this.f.setText(f.getTitle());
                if (f.getSongID().longValue() == this.a.a.mPlayedSongId) {
                    this.c.setVisibility(0);
                    this.b.setEnabled(false);
                    this.f.setEnabled(false);
                } else {
                    this.c.setVisibility(4);
                    this.b.setEnabled(true);
                    this.f.setEnabled(true);
                }
                String str = "";
                CharSequence artist = f.getArtist();
                if (!TTTextUtils.isValidateMediaString(artist)) {
                    Object obj = str;
                }
                str = f.getAlbum();
                if (TTTextUtils.isValidateMediaString(str)) {
                    artist = artist + (artist.length() > 0 ? "-" + str : str);
                }
                this.g.setText(artist);
                int b = (int) (soundSearchInfo.b() * 100.0d);
                if (b < 0) {
                    b = 100;
                }
                this.h.setVisibility(8);
                this.i.setText(this.a.a.getString(R.string.soundsearch_similarity) + " " + b + "%");
                this.d.setTag(Boolean.valueOf(f.getFav()));
                this.d.setImageResource(f.getFav() ? R.drawable.img_favourite_selected : R.drawable.img_favourite_normal);
                this.d.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        Boolean valueOf = Boolean.valueOf(!((Boolean) this.b.d.getTag()).booleanValue());
                        if (b.av()) {
                            f.setFav(valueOf.booleanValue());
                            this.b.d.setTag(valueOf);
                            this.b.d.setImageResource(valueOf.booleanValue() ? R.drawable.img_favourite_selected : R.drawable.img_favourite_normal);
                            if (valueOf.booleanValue()) {
                                g.a(f, true);
                                return;
                            } else {
                                g.b(f, false);
                                return;
                            }
                        }
                        this.b.d.setTag(Boolean.valueOf(false));
                        f.a(true);
                    }
                });
                this.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        this.b.a.a.showContextMenu(f);
                    }
                });
            }
        }

        a(SoundSearchMediaListActivity soundSearchMediaListActivity) {
            this.a = soundSearchMediaListActivity;
        }

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public void a(SoundSearchInfo[] soundSearchInfoArr) {
            this.b = soundSearchInfoArr;
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.b != null ? this.b.length : 0;
        }

        public SoundSearchInfo a(int i) {
            return (this.b == null || i < 0 || i >= this.b.length) ? SoundSearchInfo.a : this.b[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(this.a, R.layout.soundsearch_listview_item, null);
                view.setTag(new a(this, view));
            }
            a(i, view);
            return view;
        }

        private void a(int i, View view) {
            ((a) view.getTag()).a(this.b[i]);
        }
    }

    protected abstract void onAddHeaderView(ListView listView);

    protected abstract void onBindData(a aVar);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_soundsearch_media_list);
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (!M.isNull()) {
            this.mPlayedSongId = M.getSongID().longValue();
        }
        this.mRecognizeResultListView = (ListView) findViewById(R.id.soundsearch_list);
        this.mRecognizeResultListView.setOnItemClickListener(this.mOnItemClickListener);
        this.mRecognizeResultListView.setOnItemLongClickListener(this.mOnItemLongClickListener);
        onAddHeaderView(this.mRecognizeResultListView);
        this.mAdapter = new a(this);
        this.mRecognizeResultListView.setAdapter(this.mAdapter);
        onBindData(this.mAdapter);
    }

    protected void onResume() {
        super.onResume();
        this.mAdapter.notifyDataSetChanged();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, i.a(getClass(), "updatePlayMediaInfo", new Class[0]));
    }

    public void updatePlayMediaInfo() {
        if (!com.sds.android.ttpod.framework.storage.a.a.a().M().isNull()) {
            this.mPlayedSongId = com.sds.android.ttpod.framework.storage.a.a.a().M().getSongID().longValue();
            this.mAdapter.notifyDataSetChanged();
        }
    }

    private void showContextMenu(MediaItem mediaItem) {
        new com.sds.android.ttpod.component.c.b(this).a(mediaItem, "search");
    }
}

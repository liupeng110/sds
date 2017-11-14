package com.sds.android.ttpod.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.igexin.download.Downloads;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.PictureManagerAdapter;
import com.sds.android.ttpod.b.p;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.modules.search.SearchMediaLinkInfo;
import com.sds.android.ttpod.framework.storage.a.a;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.NetworkLoadView.b;
import com.tencent.open.SocialConstants;

public class PictureManagerActivity extends BaseActivity implements OnClickListener {
    private static final int ARTIST_RATIO_HEIGHT = 128;
    private static final int ARTIST_RATIO_WIDTH = 96;
    private PictureManagerAdapter mAdapter;
    private EditText mArtistEditText;
    private View mClearTextView;
    private View mContentView;
    private InputMethodManager mInputMgr;
    private ListView mListView;
    private NetworkLoadView mLoadView;
    private String mOriginArtist;
    private View mRootView;
    private View mSearchView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_song_picture_search");
        MediaItem M = a.a().M();
        if (M.isNull()) {
            f.a("没有播放的歌曲信息");
            finish();
            return;
        }
        setContentView((int) R.layout.picture_manager);
        this.mArtistEditText = (EditText) findViewById(R.id.edittext_search_input);
        this.mArtistEditText.setFocusable(false);
        this.mArtistEditText.setFocusableInTouchMode(false);
        this.mSearchView = findViewById(R.id.imageview_search);
        this.mClearTextView = findViewById(R.id.imageview_search_clear);
        this.mListView = (ListView) findViewById(R.id.picture_manager_listview);
        this.mContentView = findViewById(R.id.layout_content);
        this.mSearchView.setOnClickListener(this);
        this.mClearTextView.setOnClickListener(this);
        this.mRootView = findViewById(R.id.layout_root);
        this.mRootView.setOnClickListener(this);
        this.mLoadView = (NetworkLoadView) findViewById(R.id.layout_loading);
        this.mLoadView.setLoadState(NetworkLoadView.a.IDLE);
        this.mLoadView.setOnStartLoadingListener(new b(this) {
            final /* synthetic */ PictureManagerActivity a;

            {
                this.a = r1;
            }

            public void a() {
                if (!this.a.mAdapter.c()) {
                    this.a.doSearchArtistPicture();
                }
            }
        });
        this.mAdapter = new PictureManagerAdapter(this, this.mLoadView);
        this.mListView.setAdapter(this.mAdapter);
        String artist = M.getArtist();
        SearchMediaLinkInfo a = com.sds.android.ttpod.framework.storage.database.a.a(getContentResolver(), M.getID());
        if (!(a == null || m.a(a.getArtist()))) {
            artist = a.getArtist();
        }
        artist = e.o(artist);
        this.mOriginArtist = artist;
        this.mArtistEditText.setText(artist);
        this.mArtistEditText.setHint("请输入歌手名");
        this.mAdapter.a(M, artist);
        this.mArtistEditText.postDelayed(new Runnable(this) {
            final /* synthetic */ PictureManagerActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.mArtistEditText != null) {
                    this.a.mArtistEditText.clearFocus();
                    this.a.mArtistEditText.setFocusable(true);
                    this.a.mArtistEditText.setFocusableInTouchMode(true);
                }
            }
        }, 200);
        this.mArtistEditText.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ PictureManagerActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 3 && i != 2 && i != 0 && i != 4) {
                    return false;
                }
                this.a.doSearchArtistPicture();
                return true;
            }
        });
        this.mArtistEditText.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ PictureManagerActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (this.a.mClearTextView != null) {
                    this.a.mClearTextView.setVisibility(editable.length() > 0 ? 0 : 8);
                }
            }
        });
        int dimension = (int) getResources().getDimension(R.dimen.artist_manager_margin);
        dimension = (dimension + ((((getResources().getDisplayMetrics().widthPixels - (dimension << 2)) / 3) * 128) / ARTIST_RATIO_WIDTH)) << 1;
        View findViewById = findViewById(R.id.layout_list);
        LayoutParams layoutParams = findViewById.getLayoutParams();
        layoutParams.height = dimension;
        findViewById.setLayoutParams(layoutParams);
        this.mInputMgr = (InputMethodManager) getSystemService("input_method");
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        if (this.mAdapter != null && this.mAdapter.a() && m.a(this.mAdapter.b().getID(), a.a().M().getID())) {
            localSearchArtist();
        }
        super.onDestroy();
    }

    private void localSearchArtist() {
        startService(new Intent(this, SupportService.class).putExtra("command", "search_lyric_pic_command").putExtra(SocialConstants.PARAM_TYPE, "picture_type").putExtra("only_local_search_parameter", true));
    }

    public void onClick(View view) {
        if (view == this.mClearTextView) {
            this.mArtistEditText.setText("");
            this.mClearTextView.setVisibility(8);
        } else if (view == this.mSearchView) {
            doSearchArtistPicture();
        } else {
            finish();
        }
    }

    private void doSearchArtistPicture() {
        final String obj = this.mArtistEditText.getText().toString();
        if (m.a(obj)) {
            f.a("歌手名不能为空");
            return;
        }
        this.mInputMgr.hideSoftInputFromWindow(this.mArtistEditText.getWindowToken(), 0);
        p.a((Context) this, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ PictureManagerActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    this.b.mAdapter.a(obj);
                    a(obj);
                }
            }

            private void a(String str) {
                int i = 1;
                if (m.a(this.b.mOriginArtist, str)) {
                    i = 0;
                }
                new com.sds.android.ttpod.framework.a.b.b().b("portrait_menu_search_pic").a(Downloads.COLUMN_STATUS, String.valueOf(i)).a();
            }
        });
    }
}

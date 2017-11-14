package com.sds.android.ttpod.fragment.search;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

public class AlbumIntroduceFragment extends SlidingClosableFragment {
    private View mRootView;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_album_indroduce, null);
            initActionBar();
            initView();
        }
        return this.mRootView;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mRootView.findViewById(R.id.album_name), ThemeElement.CARD_TEXT);
        c.a(this.mRootView.findViewById(R.id.album_artist), ThemeElement.CARD_SUB_TEXT);
        c.a(this.mRootView.findViewById(R.id.title_area), ThemeElement.CARD_BACKGROUND);
        c.a(this.mRootView.findViewById(R.id.album_lang), ThemeElement.CARD_SUB_TEXT);
        c.a(this.mRootView.findViewById(R.id.album_publish_time), ThemeElement.CARD_SUB_TEXT);
        c.a(this.mRootView.findViewById(R.id.album_introduce), ThemeElement.CARD_TEXT);
    }

    private void initView() {
        AlbumItem albumItem = (AlbumItem) getArguments().getSerializable("search_word");
        TextView textView = (TextView) this.mRootView.findViewById(R.id.album_artist);
        TextView textView2 = (TextView) this.mRootView.findViewById(R.id.album_lang);
        TextView textView3 = (TextView) this.mRootView.findViewById(R.id.album_publish_time);
        TextView textView4 = (TextView) this.mRootView.findViewById(R.id.album_introduce);
        ((TextView) this.mRootView.findViewById(R.id.album_name)).setText(albumItem.getName());
        textView.setText(albumItem.getSingerName());
        textView2.setText(getString(R.string.album_lang, albumItem.getLanguage()));
        textView3.setText(getString(R.string.album_publish_time, albumItem.getPublishYear() + ""));
        if (albumItem.getDescription() != null) {
            textView4.setText(Html.fromHtml(albumItem.getDescription()));
        }
    }

    private void initActionBar() {
        getActionBarController().b((int) R.string.album_introduce);
    }

    protected boolean needSearchAction() {
        return false;
    }
}

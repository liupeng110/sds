package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumData;
import com.sds.android.cloudapi.ttpod.data.Tag;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.modules.skin.view.ModifyFitCenterImageView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.HotwordView;
import java.util.ArrayList;
import java.util.List;

public class AlbumDescriptionFragment extends SlidingClosableFragment {
    private AlbumData mAlbumData;
    private View mAlbumMessageView;
    private View mAlbumRootView;
    private View mHotWordLayout;
    private HotwordView mHotwordView;
    private TextView mIntroTitleView;
    private View mTagTitleView;
    private View mTweetLayout;
    private TextView mTweetTextView;

    public static AlbumDescriptionFragment instantiate(AlbumData albumData) {
        AlbumDescriptionFragment albumDescriptionFragment = new AlbumDescriptionFragment();
        albumDescriptionFragment.mAlbumData = albumData;
        Bundle bundle = new Bundle();
        bundle.putString("title", albumData.getName());
        albumDescriptionFragment.setArguments(bundle);
        return albumDescriptionFragment;
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            getActionBarController().a(arguments.getString("title"));
        }
        this.mAlbumRootView = layoutInflater.inflate(R.layout.fragment_post_introduction, null);
        if (this.mAlbumData != null) {
            bindView();
        }
        setStatisticPage(s.PAGE_ALBUM_DESCRIPTION);
        return this.mAlbumRootView;
    }

    private void bindView() {
        initTweetView(this.mAlbumData.getDescription());
        initHeaderImage(this.mAlbumData.getPicUrl());
        initTagView(this.mAlbumData.getTags());
        initAlbumMessageView(this.mAlbumData);
    }

    private void initAlbumMessageView(AlbumData albumData) {
        this.mAlbumMessageView = this.mAlbumRootView.findViewById(R.id.layout_mesasge);
        this.mAlbumMessageView.setVisibility(0);
        bindAlbumTextView((TextView) this.mAlbumRootView.findViewById(R.id.textview_name), getString(R.string.album_name_text), albumData.getName());
        bindAlbumTextView((TextView) this.mAlbumRootView.findViewById(R.id.textview_alias), getString(R.string.album_alias_text), albumData.getAlias());
        bindAlbumTextView((TextView) this.mAlbumRootView.findViewById(R.id.textview_date), getString(R.string.album_date_text), albumData.getPublishDate());
        bindAlbumTextView((TextView) this.mAlbumRootView.findViewById(R.id.textview_language), getString(R.string.album_lang), albumData.getLang());
        bindAlbumTextView((TextView) this.mAlbumRootView.findViewById(R.id.textview_type), getString(R.string.album_type_text), albumData.getTypeName());
        bindAlbumTextView((TextView) this.mAlbumRootView.findViewById(R.id.textview_genre), getString(R.string.album_genre_text), albumData.getGenres().getTag());
        bindAlbumTextView((TextView) this.mAlbumRootView.findViewById(R.id.textview_company), getString(R.string.album_company_text), albumData.getCompanyName());
    }

    private void bindAlbumTextView(TextView textView, String str, String str2) {
        if (str2 == null || "".equals(str2.trim())) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(String.format(str, new Object[]{str2}));
        c.a((View) textView, ThemeElement.SONG_LIST_ITEM_TEXT);
    }

    private void initHeaderImage(String str) {
        ImageView imageView = (ModifyFitCenterImageView) this.mAlbumRootView.findViewById(R.id.tv_post_introduction_pic);
        if (str != null) {
            g.a(imageView, str, (int) Entry.MAX_LIMIT, (int) Entry.MAX_LIMIT, (int) R.drawable.img_musiccircle_post_pic_default);
        }
    }

    private void initTweetView(String str) {
        this.mTweetTextView = (TextView) this.mAlbumRootView.findViewById(R.id.tv_post_introduction_tweet);
        this.mIntroTitleView = (TextView) this.mAlbumRootView.findViewById(R.id.tv_title_intro);
        this.mIntroTitleView.setText(R.string.album_message_intro);
        this.mTweetLayout = this.mAlbumRootView.findViewById(R.id.layout_introduction);
        if (m.a(str)) {
            this.mTweetTextView.setText(getString(R.string.post_no_description));
        } else {
            this.mTweetTextView.setText(str);
        }
    }

    private void initTagView(List<Tag> list) {
        this.mHotwordView = (HotwordView) this.mAlbumRootView.findViewById(R.id.hotword_view);
        this.mHotWordLayout = this.mAlbumRootView.findViewById(R.id.layout_tags);
        this.mHotWordLayout.setVisibility(8);
        this.mTagTitleView = this.mAlbumRootView.findViewById(R.id.tv_album_tags);
        TextView textView = (TextView) this.mAlbumRootView.findViewById(R.id.tv_no_tag);
        List albumTagString = getAlbumTagString(list);
        if (j.a(albumTagString)) {
            this.mHotwordView.setVisibility(8);
            textView.setVisibility(0);
            textView.setText(R.string.post_no_tag);
            return;
        }
        this.mHotwordView.setStringList(albumTagString);
    }

    private List<String> getAlbumTagString(List<Tag> list) {
        if (list == null) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (Tag tagData : list) {
            arrayList.add(tagData.getTagData().getTag());
        }
        return arrayList;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mAlbumRootView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mTweetTextView, ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mAlbumRootView.findViewById(R.id.textview_name), ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mAlbumRootView.findViewById(R.id.textview_alias), ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mAlbumRootView.findViewById(R.id.textview_date), ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mAlbumRootView.findViewById(R.id.textview_language), ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mAlbumRootView.findViewById(R.id.textview_type), ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mAlbumRootView.findViewById(R.id.textview_genre), ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mAlbumRootView.findViewById(R.id.textview_company), ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mAlbumRootView.findViewById(R.id.v_post_introduction_indicator1), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        c.a(this.mAlbumRootView.findViewById(R.id.v_post_introduction_indicator2), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        c.a(this.mAlbumRootView.findViewById(R.id.v_post_introduction_indicator3), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        c.a(this.mTagTitleView, ThemeElement.TILE_TEXT);
        c.a(this.mIntroTitleView, ThemeElement.TILE_TEXT);
    }

    public void onDestroyView() {
        super.onDestroyView();
        y.a(this.mAlbumRootView);
        y.a(getRootView());
    }
}

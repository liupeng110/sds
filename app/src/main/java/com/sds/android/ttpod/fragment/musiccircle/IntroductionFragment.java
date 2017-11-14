package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.modules.skin.view.ModifyFitCenterImageView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.HotwordView;
import com.tencent.open.SocialConstants;
import java.util.Arrays;

public class IntroductionFragment extends SlidingClosableFragment {
    private View mContent;
    private View mHotWordLayout;
    private HotwordView mHotwordView;
    private View mIntroTitleView;
    private View mTagTitleView;
    private View mTweetLayout;
    private TextView mTweetTextView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_post_info");
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        this.mContent = layoutInflater.inflate(R.layout.fragment_post_introduction, null);
        CharSequence string = getArguments().getString("name");
        getActionBarController().a(string);
        initTweetView(getArguments().getString(SocialConstants.PARAM_APP_DESC));
        initHeaderImage(getArguments().getString(User.KEY_AVATAR));
        initTagView(getArguments().getString("tags"));
        String string2 = getArguments().getString(StarCategory.KEY_STAR_CATEGORY_ID);
        if (!m.a(string2)) {
            updateAlibabaProperty(StarCategory.KEY_STAR_CATEGORY_ID, string2);
        }
        updateAlibabaProperty("name", string);
        return this.mContent;
    }

    private void initHeaderImage(String str) {
        ImageView imageView = (ModifyFitCenterImageView) this.mContent.findViewById(R.id.tv_post_introduction_pic);
        if (str != null) {
            g.a(imageView, str, (int) Entry.MAX_LIMIT, (int) Entry.MAX_LIMIT, (int) R.drawable.img_musiccircle_post_pic_default);
        }
    }

    private void initTweetView(String str) {
        this.mTweetTextView = (TextView) this.mContent.findViewById(R.id.tv_post_introduction_tweet);
        this.mIntroTitleView = this.mContent.findViewById(R.id.tv_title_intro);
        this.mTweetLayout = this.mContent.findViewById(R.id.layout_introduction);
        if (m.a(str)) {
            this.mTweetTextView.setText(getString(R.string.post_no_description));
        } else {
            this.mTweetTextView.setText(str);
        }
    }

    private void initTagView(String str) {
        this.mHotwordView = (HotwordView) this.mContent.findViewById(R.id.hotword_view);
        this.mHotWordLayout = this.mContent.findViewById(R.id.layout_tags);
        if (getArguments().getBoolean("key_show_tags", true)) {
            this.mTagTitleView = this.mContent.findViewById(R.id.tv_title_tags);
            TextView textView = (TextView) this.mContent.findViewById(R.id.tv_no_tag);
            if (m.a(str)) {
                this.mHotwordView.setVisibility(8);
                textView.setVisibility(0);
                textView.setText(R.string.post_no_tag);
                return;
            }
            this.mHotwordView.setStringList(Arrays.asList(str.split(SelectCountryActivity.SPLITTER)));
            return;
        }
        this.mHotWordLayout.setVisibility(8);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mContent, ThemeElement.BACKGROUND_MASK);
        c.a(this.mTweetTextView, ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mContent.findViewById(R.id.v_post_introduction_indicator1), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        c.a(this.mContent.findViewById(R.id.v_post_introduction_indicator2), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        c.a(this.mTagTitleView, ThemeElement.TILE_TEXT);
        c.a(this.mIntroTitleView, ThemeElement.TILE_TEXT);
    }

    public void onDestroyView() {
        super.onDestroyView();
        y.a(this.mContent);
        y.a(getRootView());
    }
}

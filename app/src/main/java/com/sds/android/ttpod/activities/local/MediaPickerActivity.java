package com.sds.android.ttpod.activities.local;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ActionBarActivity;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.MediaListFragment;
import com.sds.android.ttpod.fragment.main.list.a.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MediaPickerActivity extends SlidingClosableActivity implements b {
    private View mConfirmView;
    private List<MediaItem> mMediaItemsInGroup;
    private MediaPickerFragment mMediaPickerFragment;
    private String mPickerObjectGroupId;

    public static class MediaPickerFragment extends MediaListFragment implements a {
        private com.sds.android.ttpod.component.a.a mSelectAction;

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setAutoSelectPlayingMedia(false);
            setEditRequestListener(this);
            this.mSelectAction = getActionBarController().a(null);
            this.mSelectAction.a(new com.sds.android.ttpod.component.a.b(this) {
                final /* synthetic */ MediaPickerFragment a;

                {
                    this.a = r1;
                }

                public void a(com.sds.android.ttpod.component.a.a aVar) {
                    if (this.a.mSelectAction.g() == null) {
                        this.a.selectAll();
                        this.a.setSelectAllAction();
                        return;
                    }
                    this.a.selectNone();
                    this.a.setSelectNoneAction();
                }
            });
        }

        public void onLoadFinished() {
            super.onLoadFinished();
            startEdit();
        }

        protected void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            resetActionBar();
        }

        private com.sds.android.ttpod.component.a getActionBarController() {
            FragmentActivity activity = getActivity();
            if (activity instanceof ActionBarActivity) {
                return ((ActionBarActivity) activity).getActionBarController();
            }
            return null;
        }

        private void resetActionBar() {
            if (getActionBarController() != null) {
                int totalCount = totalCount();
                int selectedCount = selectedCount();
                if (selectedCount <= 0 || totalCount <= 0 || totalCount > selectedCount) {
                    setSelectNoneAction();
                } else {
                    setSelectAllAction();
                }
            }
        }

        private void setSelectAllAction() {
            this.mSelectAction.a(this.mSelectAction);
            v.a(this.mSelectAction, (int) R.string.icon_checked, ThemeElement.TOP_BAR_TEXT);
        }

        private void setSelectNoneAction() {
            this.mSelectAction.a(null);
            v.a(this.mSelectAction, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
        }

        protected void configFailedView(View view) {
            super.configFailedView(view);
            view.findViewById(R.id.no_data_action_view).setVisibility(8);
        }

        public void onRemoveRequested() {
        }

        public void onAddToRequested() {
        }

        public void onSendToRequested() {
        }

        public void onStopEditRequested() {
        }

        public void onSelectedCountChanged() {
            getActionBarController().a(getResources().getString(R.string.select_media_with_count, new Object[]{Integer.valueOf(selectedCount())}));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_media_picker);
        setTitle((int) R.string.choose_music);
        Intent intent = getIntent();
        if (intent != null) {
            this.mPickerObjectGroupId = intent.getStringExtra(AbsMediaListFragment.KEY_GROUP_ID);
        }
        if (TextUtils.isEmpty(this.mPickerObjectGroupId)) {
            finish();
        } else {
            addContentFragment();
        }
    }

    private void addContentFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.playing));
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_ALL_LOCAL);
        this.mMediaPickerFragment = (MediaPickerFragment) Fragment.instantiate(this, MediaPickerFragment.class.getName(), bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, this.mMediaPickerFragment).commitAllowingStateLoss();
        this.mMediaItemsInGroup = MediaStorage.queryMediaItemList(this, this.mPickerObjectGroupId, MediaStorage.MEDIA_ORDER_BY_CUSTOM);
        this.mMediaPickerFragment.putSelectedMediaItem(this.mMediaItemsInGroup);
        this.mConfirmView = findViewById(R.id.button_confirm);
        this.mConfirmView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MediaPickerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Collection selectedMediaItems = this.a.mMediaPickerFragment.getSelectedMediaItems();
                Iterator it = selectedMediaItems.iterator();
                while (it.hasNext()) {
                    MediaItem mediaItem = (MediaItem) it.next();
                    if (this.a.mMediaItemsInGroup.contains(mediaItem)) {
                        it.remove();
                        this.a.mMediaItemsInGroup.remove(mediaItem);
                    }
                }
                if (this.a.mMediaItemsInGroup.size() > 0) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM_LIST, this.a.mPickerObjectGroupId, this.a.mMediaItemsInGroup, Boolean.valueOf(false)));
                }
                if (selectedMediaItems.size() > 0) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM_LIST, this.a.mPickerObjectGroupId, selectedMediaItems));
                }
                this.a.finish();
            }
        });
    }

    public void onThemeLoaded() {
        c.a(getRootView(), v.a());
        c.a(findViewById(R.id.layout_background), c.a());
        getActionBarController().onThemeLoaded();
    }
}

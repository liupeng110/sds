package com.sds.android.ttpod.fragment.skinmanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.BackgroundManagementActivity;
import com.sds.android.ttpod.activities.user.utils.c;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.soundsearch.b;
import com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.widget.StateView;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MyBackgroundFragment extends BackgroundBaseFragment implements com.sds.android.ttpod.component.soundsearch.a, com.sds.android.ttpod.component.soundsearch.b.a, b {
    private static final String KEY_REQUEST_CODE = "request_code";
    public static final String LOCAL_BACKGROUND_IMAGE_PATH_FORMAT = (com.sds.android.ttpod.framework.a.p() + File.separator + "users-%d.jpg");
    private static final int REQUEST_IMAGE_BACKGROUND = 1;
    private Activity mActivity;
    private int mCachedRequestCode;
    private com.sds.android.ttpod.component.soundsearch.b.a mEditRequestListener;
    private View mHeaderView;
    private OnLongClickListener mOnBackgroundLongClickListener = new OnLongClickListener(this) {
        final /* synthetic */ MyBackgroundFragment a;

        {
            this.a = r1;
        }

        public boolean onLongClick(View view) {
            if (!this.a.mInEditMode) {
                if (this.a.mParentEditToggle != null) {
                    this.a.mParentEditToggle.toggleEditMode();
                } else {
                    this.a.toggleEditMode();
                }
            }
            return true;
        }
    };
    private com.sds.android.ttpod.component.soundsearch.b.a mParentEditRequestListener;
    private com.sds.android.ttpod.component.soundsearch.a mParentEditToggle;
    private c mPickImageHelper;
    private Queue<com.sds.android.ttpod.framework.modules.theme.a> mReadOnlyItems = new LinkedList();
    private Map<Integer, com.sds.android.ttpod.framework.modules.theme.a> mSelectItemHashMap = new LinkedHashMap();
    private String mUserBackgroundName;

    class a extends com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment.a {
        final /* synthetic */ MyBackgroundFragment a;

        public a(MyBackgroundFragment myBackgroundFragment, LayoutInflater layoutInflater, String str) {
            this.a = myBackgroundFragment;
            super(myBackgroundFragment, layoutInflater, str);
        }

        protected void a(com.sds.android.ttpod.framework.modules.theme.a aVar, b bVar, boolean z) {
            super.a(aVar, bVar, z);
            View h = bVar.h();
            h.setOnLongClickListener(this.a.mOnBackgroundLongClickListener);
            h.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    com.sds.android.ttpod.framework.modules.theme.a aVar = (com.sds.android.ttpod.framework.modules.theme.a) view.getTag();
                    if (aVar != null && !aVar.toString().equals(this.a.a.mBackgroundAdapter.c())) {
                        if (!this.a.a.mInEditMode) {
                            this.a.a.checkNormalStateItem(aVar);
                        } else if (this.a.a.isLocalUnSelectedBackgroundItem(aVar)) {
                            int hashCode = aVar.hashCode();
                            if (this.a.a.mSelectItemHashMap.containsKey(Integer.valueOf(hashCode))) {
                                this.a.a.mSelectItemHashMap.remove(Integer.valueOf(hashCode));
                            } else {
                                this.a.a.mSelectItemHashMap.put(Integer.valueOf(hashCode), aVar);
                            }
                            this.a.notifyDataSetChanged();
                            this.a.a.tryNotifySelectedCountChanged();
                        } else {
                            this.a.a.promptInEditMode();
                        }
                    }
                }
            });
        }

        protected void a(com.sds.android.ttpod.framework.modules.theme.a aVar, ImageView imageView) {
            int i = 0;
            if (imageView != null && aVar != null) {
                if (c().equals(aVar.toString())) {
                    c(aVar);
                } else if (!this.a.mInEditMode) {
                    i = 4;
                } else if (this.a.mSelectItemHashMap.get(Integer.valueOf(aVar.hashCode())) == null) {
                    i = 4;
                }
                imageView.setVisibility(i);
            }
        }
    }

    protected com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment.a initAdapter() {
        return new a(this, getActivity().getLayoutInflater(), com.sds.android.ttpod.framework.storage.environment.b.aa());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_MY_BACKGROUND);
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEditRequestListener = this;
        FragmentActivity activity = getActivity();
        if (activity instanceof BackgroundManagementActivity) {
            this.mParentEditToggle = (com.sds.android.ttpod.component.soundsearch.a) activity;
            this.mParentEditRequestListener = (com.sds.android.ttpod.component.soundsearch.b.a) activity;
            v.a(view);
        }
    }

    public void onStop() {
        if (isInEditMode()) {
            toggleEditMode();
        }
        super.onStop();
    }

    protected void initListViewHeader() {
        this.mHeaderView = View.inflate(getActivity(), R.layout.fragment_background_header_layout, null);
        this.mBackgroundListView.addHeaderView(this.mHeaderView);
        ((Button) this.mHeaderView.findViewById(R.id.button_from_gallery)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyBackgroundFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.isInEditMode()) {
                    this.a.promptInEditMode();
                } else {
                    this.a.getPickImageHelper().a(this.a, this.a.getString(R.string.add_background), 1, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e());
                    x.k();
                    new SUserEvent("PAGE_CLICK", r.ACTION_SELECT_BKG_FROM_GALLERY.getValue(), s.PAGE_MY_BACKGROUND.getValue(), s.PAGE_NONE.getValue()).post();
                }
                com.sds.android.ttpod.framework.a.b.b.a("select_picture");
            }
        });
        ((Button) this.mHeaderView.findViewById(R.id.button_take_picture)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyBackgroundFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.isInEditMode()) {
                    this.a.promptInEditMode();
                } else {
                    this.a.getPickImageHelper().b(this.a, this.a.getString(R.string.add_background), 1, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e());
                    x.l();
                    new SUserEvent("PAGE_CLICK", r.ACTION_SELECT_BKG_FROM_CAMERA.getValue(), s.PAGE_MY_BACKGROUND.getValue(), s.PAGE_NONE.getValue()).post();
                }
                com.sds.android.ttpod.framework.a.b.b.a("take_photo");
            }
        });
    }

    protected void initListViewFooter() {
    }

    public void updateBackground(Drawable drawable) {
        if (getActivity() instanceof BackgroundManagementActivity) {
            this.mBackgroundAdapter.d();
            notifyDataSetChanged();
            return;
        }
        super.updateBackground(drawable);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_LOCAL_BACKGROUND_LIST, i.a(cls, "updateBackgroundList", ArrayList.class));
        map.put(com.sds.android.ttpod.framework.modules.a.LOAD_SKIN_FINISHED, i.a(cls, "loadSkinFinished", j.class));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mPickImageHelper = new c(getActivity());
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.mBackgroundAdapter != null && this.mBackgroundAdapter.getCount() > 0 && this.mBackgroundAdapter.d()) {
            this.mBackgroundAdapter.notifyDataSetChanged();
        }
        if (!z && isInEditMode()) {
            toggleEditMode();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            getPickImageHelper().a(bundle);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 1:
                    this.mUserBackgroundName = String.format(LOCAL_BACKGROUND_IMAGE_PATH_FORMAT, new Object[]{Integer.valueOf(getValidUserBackgroundNum())});
                    cropPhoto(i, intent);
                    this.mCachedRequestCode = i;
                    return;
                case 3:
                    if (this.mCachedRequestCode == 1 && isAdded()) {
                        setAddedBackground(this.mUserBackgroundName);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.mCachedRequestCode = bundle.getInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            getPickImageHelper().b(bundle);
        }
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        loadBackgroundList();
    }

    private void loadBackgroundList() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.LOAD_BACKGROUND_LIST, Boolean.valueOf(true)));
    }

    public void updateBackgroundList(ArrayList<com.sds.android.ttpod.framework.modules.theme.a> arrayList) {
        if (this.mBackgroundAdapter != null && arrayList != null) {
            this.mBackgroundAdapter.b().clear();
            this.mBackgroundAdapter.e(new com.sds.android.ttpod.framework.modules.theme.a(null, com.sds.android.ttpod.framework.modules.theme.a.a.FOLLOW_SKIN));
            this.mBackgroundAdapter.a((ArrayList) arrayList);
            if (isInEditMode()) {
                tryNotifySelectedCountChanged();
            }
            this.mStateView.setState(StateView.b.SUCCESS);
        } else if (this.mStateView != null) {
            this.mStateView.setState(StateView.b.FAILED);
        }
    }

    public void loadSkinFinished(j jVar) {
        super.onThemeChanged();
        if (this.mBackgroundAdapter != null && this.mBackgroundAdapter.b().size() > 0) {
            ((com.sds.android.ttpod.framework.modules.theme.a) this.mBackgroundAdapter.b().get(0)).a(null);
            this.mBackgroundAdapter.notifyDataSetChanged();
        }
    }

    protected void refreshEditButton() {
        FragmentActivity activity = getActivity();
        if (activity instanceof BackgroundManagementActivity) {
            ((BackgroundManagementActivity) activity).refreshEditButton();
        }
    }

    public boolean isInEditMode() {
        return this.mInEditMode;
    }

    public void toggleEditMode() {
        this.mInEditMode = !this.mInEditMode;
        if (this.mInEditMode) {
            removeReadOnlyItem();
            startEdit();
        } else {
            restoreReadOnlyItem();
            stopEdit();
        }
        this.mBackgroundAdapter.notifyDataSetChanged();
    }

    private void removeReadOnlyItem() {
        ArrayList b = this.mBackgroundAdapter.b();
        this.mReadOnlyItems.offer(b.remove(0));
        com.sds.android.ttpod.framework.modules.theme.a a = this.mBackgroundAdapter.a();
        if (!this.mReadOnlyItems.contains(a)) {
            b.remove(a);
            this.mReadOnlyItems.offer(a);
        }
    }

    private void restoreReadOnlyItem() {
        ArrayList b = this.mBackgroundAdapter.b();
        int i = 0;
        while (true) {
            com.sds.android.ttpod.framework.modules.theme.a aVar = (com.sds.android.ttpod.framework.modules.theme.a) this.mReadOnlyItems.poll();
            if (aVar != null) {
                int i2 = i + 1;
                b.add(i, aVar);
                i = i2;
            } else {
                return;
            }
        }
    }

    public boolean hasEditableContent() {
        if (this.mBackgroundAdapter == null) {
            return false;
        }
        Iterator it = this.mBackgroundAdapter.b().iterator();
        while (it.hasNext()) {
            com.sds.android.ttpod.framework.modules.theme.a aVar = (com.sds.android.ttpod.framework.modules.theme.a) it.next();
            if (!this.mBackgroundAdapter.a(aVar) && isLocalBackground(aVar.a())) {
                return true;
            }
        }
        return false;
    }

    public void setAttachedActivity(Activity activity) {
        this.mActivity = activity;
    }

    private c getPickImageHelper() {
        if (this.mPickImageHelper == null) {
            this.mPickImageHelper = new c(this.mActivity);
        }
        return this.mPickImageHelper;
    }

    private void setAddedBackground(String str) {
        this.mUserBackgroundName = "file://" + str;
        com.sds.android.ttpod.framework.modules.theme.a aVar = new com.sds.android.ttpod.framework.modules.theme.a(this.mUserBackgroundName);
        this.mBackgroundAdapter.e(aVar);
        this.mBackgroundAdapter.b(aVar);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_BACKGROUND, this.mUserBackgroundName));
    }

    private void cropPhoto(int i, Intent intent) {
        if (i == 1) {
            getPickImageHelper().a(this, intent == null ? null : intent.getData(), this.mUserBackgroundName);
        }
    }

    private int getValidUserBackgroundNum() {
        int i = 0;
        while (true) {
            if (!new File(String.format(LOCAL_BACKGROUND_IMAGE_PATH_FORMAT, new Object[]{Integer.valueOf(i)})).exists()) {
                return i;
            }
            i++;
        }
    }

    private void promptInEditMode() {
        f.a((int) R.string.in_edit_mode);
    }

    public boolean isSupportOfflineMode() {
        return false;
    }

    public void updateBkgDownloadingState(DownloadTaskInfo downloadTaskInfo) {
        if (DownloadTaskInfo.TYPE_BACKGROUND.equals(downloadTaskInfo.getType()) && downloadTaskInfo.getState().intValue() == 4) {
            com.sds.android.ttpod.framework.modules.theme.a aVar = (com.sds.android.ttpod.framework.modules.theme.a) downloadTaskInfo.getTag();
            if (!this.mBackgroundAdapter.b().contains(aVar)) {
                if (this.mInEditMode) {
                    this.mReadOnlyItems.offer(aVar);
                    return;
                }
                this.mBackgroundAdapter.e(aVar);
                this.mBackgroundAdapter.notifyDataSetChanged();
            }
        }
    }

    public void selectAll() {
        ArrayList b = this.mBackgroundAdapter.b();
        this.mSelectItemHashMap.clear();
        Iterator it = b.iterator();
        while (it.hasNext()) {
            com.sds.android.ttpod.framework.modules.theme.a aVar = (com.sds.android.ttpod.framework.modules.theme.a) it.next();
            if (isLocalUnSelectedBackgroundItem(aVar)) {
                this.mSelectItemHashMap.put(Integer.valueOf(aVar.hashCode()), aVar);
            }
        }
        notifyDataSetChanged();
    }

    public void selectNone() {
        this.mSelectItemHashMap.clear();
        notifyDataSetChanged();
    }

    public void startEdit() {
        View findViewById = getActivity().findViewById(R.id.view_immersive_observer);
        f.a(getView(), findViewById == null ? 0 : findViewById.getPaddingBottom(), this.mEditRequestListener);
        ((MarginLayoutParams) this.mBackgroundListView.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height);
        this.mBackgroundListView.removeHeaderView(this.mHeaderView);
        notifyDataSetChanged();
    }

    public void stopEdit() {
        if (isViewAccessAble()) {
            f.c();
            ((MarginLayoutParams) this.mBackgroundListView.getLayoutParams()).bottomMargin = 0;
            this.mSelectItemHashMap.clear();
            this.mBackgroundListView.setAdapter(null);
            this.mBackgroundListView.addHeaderView(this.mHeaderView);
            this.mBackgroundListView.setAdapter(this.mBackgroundAdapter);
            notifyDataSetChanged();
        }
    }

    public int selectedCount() {
        return this.mSelectItemHashMap.size();
    }

    public int totalCount() {
        return this.mBackgroundAdapter == null ? 0 : getLocalItemCount();
    }

    private int getLocalItemCount() {
        Iterator it = this.mBackgroundAdapter.b().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            if (isLocalUnSelectedBackgroundItem((com.sds.android.ttpod.framework.modules.theme.a) it.next())) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public void remove() {
        if (selectedCount() > 0) {
            showConfirmDeleteDialog();
        }
    }

    private void showConfirmDeleteDialog() {
        h hVar = new h(getActivity(), getString(R.string.confirm_delete_background, Integer.valueOf(this.mSelectItemHashMap.size())), new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ MyBackgroundFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                int selectedCount = this.a.selectedCount();
                if (selectedCount > 0) {
                    Iterator it = this.a.mSelectItemHashMap.keySet().iterator();
                    while (it.hasNext()) {
                        this.a.deleteItem((com.sds.android.ttpod.framework.modules.theme.a) this.a.mSelectItemHashMap.get(it.next()));
                        it.remove();
                    }
                    x.b(selectedCount);
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_BKD_DELETE.getValue(), s.PAGE_MY_BACKGROUND.getValue(), s.PAGE_NONE.getValue()).append("background_count", Integer.valueOf(selectedCount)).post();
                }
                this.a.tryNotifyStopEditRequested();
            }
        }, null);
        hVar.setTitle((int) R.string.delete);
        hVar.show();
    }

    private void tryNotifySelectedCountChanged() {
        if (this.mParentEditRequestListener != null) {
            this.mParentEditRequestListener.onSelectedCountChanged();
        }
    }

    private void tryNotifyStopEditRequested() {
        if (this.mParentEditRequestListener != null) {
            this.mParentEditRequestListener.onStopEditRequested();
        }
    }

    public void onRemoveRequested() {
        remove();
    }

    public void onSelectedCountChanged() {
    }

    public void onStopEditRequested() {
        toggleEditMode();
    }

    private void deleteItem(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_BACKGROUND, aVar.toString()));
        this.mBackgroundAdapter.d(aVar);
        performBkgDeleted(aVar);
    }
}

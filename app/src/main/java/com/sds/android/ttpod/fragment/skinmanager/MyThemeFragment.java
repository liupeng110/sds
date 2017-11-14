package com.sds.android.ttpod.fragment.skinmanager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.ThemeManagementActivity;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.soundsearch.b;
import com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.modules.skin.m;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MyThemeFragment extends BaseThemeFragment implements com.sds.android.ttpod.component.soundsearch.b.a, b {
    private com.sds.android.ttpod.component.soundsearch.b.a mEditRequestListener;
    private com.sds.android.ttpod.component.soundsearch.b.a mParentEditRequestListener;
    private Queue<m> mReadOnlyItems = new LinkedList();
    private Map<Integer, m> mSelectItemHashMap = new LinkedHashMap();

    private class a extends com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.a {
        final /* synthetic */ MyThemeFragment a;

        private a(MyThemeFragment myThemeFragment) {
            this.a = myThemeFragment;
            super(myThemeFragment);
        }

        protected void a(m mVar, ImageView imageView) {
            Bitmap a = g.a(mVar.b(), this.b, this.c);
            if (a != null) {
                imageView.setImageBitmap(a);
                return;
            }
            imageView.setImageResource(R.drawable.img_skin_default_thumbnail);
            d(mVar);
        }

        private void d(m mVar) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DECODE_SKIN_THUMBNAIL, mVar));
        }

        protected void b(m mVar, ImageView imageView) {
            int i = 0;
            if (imageView != null && mVar != null) {
                if (this.e.equals(mVar.b())) {
                    this.d = mVar;
                } else if (!this.a.mInEditMode) {
                    i = 4;
                } else if (this.a.mSelectItemHashMap.get(Integer.valueOf(mVar.hashCode())) == null) {
                    i = 4;
                }
                imageView.setVisibility(i);
            }
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.LOAD_SKIN_ERROR, i.a(cls, "onLoadSkinError", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.DECODE_SKIN_THUMBNAIL_FINISHED, i.a(cls, "onSkinThumbnailCreated", m.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ALL_LOCAL_SKIN_LIST, i.a(cls, "updateDataListForAdapter", ArrayList.class));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setForLocal();
        this.mSubClassTag = MyThemeFragment.class.getSimpleName();
        setStatisticPage(s.PAGE_MY_THEME);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEditRequestListener = this;
        FragmentActivity activity = getActivity();
        if (activity instanceof ThemeManagementActivity) {
            this.mParentEditRequestListener = (com.sds.android.ttpod.component.soundsearch.b.a) activity;
        }
    }

    public void onPause() {
        super.onPause();
        com.sds.android.ttpod.framework.storage.a.a.a().c(getThemeDataList());
    }

    public void onStop() {
        if (isInEditMode()) {
            toggleEditMode();
        }
        super.onStop();
    }

    protected ArrayList<m> loadDataFromCache() {
        return com.sds.android.ttpod.framework.storage.a.a.a().t();
    }

    protected com.sds.android.ttpod.framework.modules.a getLoadDataCommandID() {
        return com.sds.android.ttpod.framework.modules.a.LOAD_ALL_LOCAL_SKIN_LIST;
    }

    protected String getStatisticOrigin() {
        return "my";
    }

    public void onLoadSkinError() {
        f.a((int) R.string.load_theme_error_tip);
        com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.a adapter = getAdapter();
        if (adapter != null) {
            adapter.a();
            adapter.notifyDataSetChanged();
        }
    }

    public void updateDataListForAdapter(ArrayList<m> arrayList) {
        this.mCacheMode = false;
        if (checkIfReloadData(arrayList)) {
            setAdapterDataSource(arrayList);
            refreshEditButton();
        }
    }

    public void onSkinThumbnailCreated(m mVar) {
        if (mVar != null) {
            this.mThemeAdapter.notifyDataSetChanged();
        }
    }

    protected void onThemeItemSelected(m mVar) {
        if (!this.mInEditMode) {
            checkSkinItem(mVar);
        } else if (isLocalUnSelectedSkinItem(mVar)) {
            int hashCode = mVar.hashCode();
            if (this.mSelectItemHashMap.containsKey(Integer.valueOf(hashCode))) {
                this.mSelectItemHashMap.remove(Integer.valueOf(hashCode));
            } else {
                this.mSelectItemHashMap.put(Integer.valueOf(hashCode), mVar);
            }
            notifyDataSetChanged();
            tryNotifySelectedCountChanged();
        } else {
            f.a((int) R.string.in_edit_mode);
        }
    }

    private void showConfirmDeleteDialog() {
        h hVar = new h(getActivity(), getString(R.string.confirm_delete_skin, Integer.valueOf(this.mSelectItemHashMap.size())), new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ MyThemeFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                int selectedCount = this.a.selectedCount();
                if (selectedCount > 0) {
                    Iterator it = this.a.mSelectItemHashMap.keySet().iterator();
                    while (it.hasNext()) {
                        this.a.deleteItem((m) this.a.mSelectItemHashMap.get(it.next()));
                        it.remove();
                    }
                    x.a(selectedCount);
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_THEME_DELETE.getValue(), s.PAGE_MY_THEME.getValue(), s.PAGE_NONE.getValue()).append("skin_count", Integer.valueOf(selectedCount)).post();
                }
                this.a.tryNotifyStopEditRequested();
            }
        }, null);
        hVar.setTitle((int) R.string.delete_theme);
        hVar.show();
    }

    private void deleteItem(m mVar) {
        if (deleteSkin(mVar.b(), mVar.a()).booleanValue()) {
            this.mThemeAdapter.c(mVar);
            performSkinItemStateChange(mVar.g(), 4);
        }
        performSkinDeleted(mVar);
    }

    private Boolean deleteSkin(String str, int i) {
        return (Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_SKIN, str, Integer.valueOf(i)), Boolean.class);
    }

    protected void initThemeAdapter() {
        this.mThemeAdapter = new a();
    }

    public void selectAll() {
        ArrayList c = this.mThemeAdapter.c();
        this.mSelectItemHashMap.clear();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            m mVar = (m) it.next();
            if (isLocalUnSelectedSkinItem(mVar)) {
                this.mSelectItemHashMap.put(Integer.valueOf(mVar.hashCode()), mVar);
            }
        }
        tryNotifySelectedCountChanged();
        notifyDataSetChanged();
    }

    public void selectNone() {
        this.mSelectItemHashMap.clear();
        tryNotifySelectedCountChanged();
        notifyDataSetChanged();
    }

    public void startEdit() {
        View findViewById = getActivity().findViewById(R.id.view_immersive_observer);
        f.a(getView(), findViewById == null ? 0 : findViewById.getPaddingBottom(), this.mEditRequestListener);
        ((MarginLayoutParams) this.mThemeListView.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height);
        notifyDataSetChanged();
    }

    public void stopEdit() {
        if (isViewAccessAble()) {
            f.c();
            ((MarginLayoutParams) this.mThemeListView.getLayoutParams()).bottomMargin = 0;
            this.mSelectItemHashMap.clear();
            notifyDataSetChanged();
        }
    }

    public int selectedCount() {
        return this.mSelectItemHashMap.size();
    }

    public int totalCount() {
        return this.mThemeAdapter == null ? 0 : getLocalItemCount();
    }

    private int getLocalItemCount() {
        Iterator it = this.mThemeAdapter.c().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            if (isLocalUnSelectedSkinItem((m) it.next())) {
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
        if (selectedCount() > 0) {
            remove();
        }
    }

    public void onSelectedCountChanged() {
    }

    public void onStopEditRequested() {
        toggleEditMode();
    }

    public void toggleEditMode() {
        super.toggleEditMode();
        this.mInEditMode = !this.mInEditMode;
        if (this.mInEditMode) {
            removeReadOnlyItem();
            startEdit();
            return;
        }
        restoreReadOnlyItem();
        stopEdit();
    }

    public boolean hasEditableContent() {
        if (this.mThemeData == null) {
            return false;
        }
        if (this.mThemeAdapter.e() > this.mInternalThemeCount + 1) {
            return true;
        }
        Iterator it = this.mThemeData.iterator();
        while (it.hasNext()) {
            m mVar = (m) it.next();
            if (mVar.a() != 1 && !this.mThemeAdapter.a(mVar)) {
                return true;
            }
        }
        return false;
    }

    protected void refreshEditButton() {
        this.mInternalThemeCount = v.d();
        FragmentActivity activity = getActivity();
        if (activity instanceof ThemeManagementActivity) {
            ((ThemeManagementActivity) activity).refreshEditButton();
        }
    }

    private void removeReadOnlyItem() {
        for (int i = 0; i < this.mInternalThemeCount; i++) {
            this.mReadOnlyItems.offer(this.mThemeData.remove(0));
        }
        m d = this.mThemeAdapter.d();
        if (!this.mReadOnlyItems.contains(d)) {
            this.mThemeData.remove(d);
            this.mReadOnlyItems.offer(d);
        }
    }

    private void restoreReadOnlyItem() {
        int i = 0;
        while (true) {
            m mVar = (m) this.mReadOnlyItems.poll();
            if (mVar != null) {
                int i2 = i + 1;
                this.mThemeData.add(i, mVar);
                i = i2;
            } else {
                return;
            }
        }
    }

    protected void updateSkinInfoForThemeName(String str, int i) {
        ArrayList themeDataList = getThemeDataList();
        if (themeDataList != null && str != null && i != 4) {
            m skinItemForSkinFileName = getSkinItemForSkinFileName(str);
            if (skinItemForSkinFileName == null && i == 0) {
                themeDataList.add(new m((m) sOnlineSkinInfoMap.get(str)));
            } else if (skinItemForSkinFileName != null) {
                skinItemForSkinFileName.a(i);
            }
        }
    }

    private m getSkinItemForSkinFileName(String str) {
        ArrayList themeDataList = getThemeDataList();
        if (themeDataList == null) {
            return null;
        }
        for (int i = 0; i < themeDataList.size(); i++) {
            m mVar = (m) themeDataList.get(i);
            if (mVar.h().equals(str)) {
                return mVar;
            }
        }
        return null;
    }
}

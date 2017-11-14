package com.sds.android.ttpod.activities.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup.MarginLayoutParams;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.soundsearch.a;
import com.sds.android.ttpod.component.soundsearch.b;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class ThemeManagementBaseActivity extends SlidingPagerActivity implements a, b.a, c.b {
    private static final long DELAY_SET_ACTIONBAR = 300;
    private com.sds.android.ttpod.component.a.a mEditAction;
    protected boolean mInEditMode = false;
    private com.sds.android.ttpod.component.a.a mSelectActionItem;

    protected abstract void clickStatistic(int i);

    protected abstract String getOnSelectedCountChangedString(int i);

    protected abstract String getTitleString();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setLaunchFragmentAttr(R.id.action_bar_activity_container, R.anim.slide_in_right, R.anim.slide_out_right);
        hideEditActionItem();
    }

    protected void onStop() {
        if (isInEditMode()) {
            toggleEditMode();
        }
        super.onStop();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.APP_THEME_CHANGED, i.a(cls, "onThemeLoaded", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_BACKGROUND, i.a(cls, "updateBackground", Drawable.class));
    }

    protected void onBuildActionBar(com.sds.android.ttpod.component.a aVar) {
        this.mEditAction = aVar.a(null, "editAction");
        this.mEditAction.a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ ThemeManagementBaseActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                this.a.toggleEditMode();
            }
        });
        this.mSelectActionItem = aVar.a(null);
        this.mSelectActionItem.a();
        setSelectAllAction();
        this.mSelectActionItem.a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ ThemeManagementBaseActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                if (aVar != this.a.mSelectActionItem) {
                    return;
                }
                if (aVar.g() == null) {
                    this.a.selectAll();
                } else {
                    this.a.selectNone();
                }
            }
        });
    }

    public void selectAll() {
        setUnSelectAllAction();
        b iThemeEditable = getIThemeEditable();
        if (iThemeEditable != null) {
            iThemeEditable.selectAll();
        }
        onSelectedCountChanged();
    }

    public void selectNone() {
        setSelectAllAction();
        b iThemeEditable = getIThemeEditable();
        if (iThemeEditable != null) {
            iThemeEditable.selectNone();
        }
        onSelectedCountChanged();
    }

    private void setUnSelectAllAction() {
        this.mSelectActionItem.a(this.mSelectActionItem);
        v.a(this.mSelectActionItem, (int) R.string.icon_checked, ThemeElement.TOP_BAR_TEXT);
    }

    private void setSelectAllAction() {
        this.mSelectActionItem.a(null);
        v.a(this.mSelectActionItem, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
    }

    public void onThemeLoaded() {
        v.a(this.mEditAction, ThemeElement.TOP_BAR_EDIT_IMAGE, (int) R.string.icon_edit, ThemeElement.TOP_BAR_TEXT);
        v.a(this.mPagerTitle);
        c.a(this.mPagerContent, ThemeElement.BACKGROUND_MASK);
        getActionBarController().onThemeLoaded();
        c.b(getRootView(), v.a());
        v.a(this.mEditAction, ThemeElement.TOP_BAR_EDIT_IMAGE, (int) R.string.icon_edit, ThemeElement.TOP_BAR_TEXT);
    }

    public void updateBackground(Drawable drawable) {
        if (drawable != null) {
            getRootView().setBackgroundDrawable(drawable);
        }
    }

    private void gotoSpecificPage(int i) {
        if (i != -1) {
            this.mPagerContent.setCurrentItem(i);
        }
    }

    public boolean isInEditMode() {
        a editModeToggle = getEditModeToggle();
        return editModeToggle != null && editModeToggle.isInEditMode();
    }

    public boolean hasEditableContent() {
        a editModeToggle = getEditModeToggle();
        return editModeToggle != null ? editModeToggle.hasEditableContent() : false;
    }

    protected a getEditModeToggle() {
        Fragment item = this.mPagerAdapter.getItem(this.mPagerContent.getCurrentItem());
        if (item instanceof a) {
            return (a) item;
        }
        return null;
    }

    protected b getIThemeEditable() {
        Fragment item = this.mPagerAdapter.getItem(this.mPagerContent.getCurrentItem());
        if (item instanceof b) {
            return (b) item;
        }
        return null;
    }

    protected b.a getOnEditRequestListener() {
        Fragment item = this.mPagerAdapter.getItem(this.mPagerContent.getCurrentItem());
        if (item instanceof b) {
            return (b.a) item;
        }
        return null;
    }

    public void showEditActionItem() {
        if (this.mInEditMode) {
            setSlidingCloseMode(0);
            getActionBarController().a(getOnSelectedCountChangedString(0));
            this.mSelectActionItem.b();
            setSelectAllAction();
            this.mEditAction.a();
            this.mPagerTitle.setVisibility(8);
            ((MarginLayoutParams) this.mPagerContent.getLayoutParams()).topMargin = 0;
            this.mPagerContent.setCanScroll(false);
            return;
        }
        setSlidingCloseMode(1);
        getActionBarController().a(getTitleString());
        this.mSelectActionItem.a();
        if (hasEditableContent()) {
            this.mEditAction.b();
        } else {
            this.mEditAction.a();
        }
        this.mPagerTitle.setVisibility(0);
        ((MarginLayoutParams) this.mPagerContent.getLayoutParams()).topMargin = getResources().getDimensionPixelSize(R.dimen.tab_label_height);
        this.mPagerContent.setCanScroll(true);
    }

    public void onRemoveRequested() {
    }

    public void onSelectedCountChanged() {
        b iThemeEditable = getIThemeEditable();
        if (iThemeEditable != null) {
            if (iThemeEditable.totalCount() != iThemeEditable.selectedCount()) {
                setSelectAllAction();
            } else {
                setUnSelectAllAction();
            }
            getActionBarController().a(getOnSelectedCountChangedString(iThemeEditable.selectedCount()));
        }
    }

    public void onStopEditRequested() {
        toggleEditMode();
    }

    public void onBackPressed() {
        if (f.b()) {
            toggleEditMode();
        } else {
            super.onBackPressed();
        }
    }

    public void hideEditActionItem() {
        this.mEditAction.c(false);
    }

    public void onPageSelected(int i) {
        super.onPageSelected(i);
        refreshEditButton();
        clickStatistic(i);
    }

    public void refreshEditButton() {
        this.mPagerContent.postDelayed(new Runnable(this) {
            final /* synthetic */ ThemeManagementBaseActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (!this.a.isFinishing()) {
                    if (this.a.hasEditableContent()) {
                        this.a.showEditActionItem();
                    } else {
                        this.a.hideEditActionItem();
                    }
                }
            }
        }, DELAY_SET_ACTIONBAR);
    }
}

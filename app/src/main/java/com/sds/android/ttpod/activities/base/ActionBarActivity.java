package com.sds.android.ttpod.activities.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.fragment.base.c;
import com.sds.android.ttpod.fragment.base.d;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.b;
import java.util.Collection;

public class ActionBarActivity extends ThemeActivity implements c, d {
    private a mActionBarController;
    private View mContentView;
    protected b mDropDownMenu;
    private com.sds.android.ttpod.fragment.base.a mImmersiveObserver;
    protected a.a mMenuAction;
    private View mRootView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View findViewById = findViewById(R.id.view_immersive_observer);
        ViewGroup viewGroup = (findViewById == null || !(findViewById.getParent() instanceof ViewGroup)) ? null : (ViewGroup) findViewById.getParent();
        this.mRootView = getLayoutInflater().inflate(R.layout.activity_actionbar, viewGroup, false);
        this.mActionBarController = a.a(this.mRootView.findViewById(R.id.action_bar_controller));
        bindActionBar();
    }

    public boolean needApplyNavigationBarStyle() {
        return true;
    }

    public boolean needApplyStatusBarStyle() {
        return true;
    }

    protected void onImmersiveViewObserverCreated(View view) {
        super.onImmersiveViewObserverCreated(view);
        this.mImmersiveObserver = new com.sds.android.ttpod.fragment.base.a(view);
        this.mImmersiveObserver.a(getActionBarView(), getContentContainer(), this.mRootView.findViewById(R.id.status_bar_background), this.mRootView.findViewById(R.id.navigate_bar_background));
        this.mImmersiveObserver.a((c) this);
        this.mImmersiveObserver.a();
    }

    protected View getActionBarView() {
        return this.mActionBarController.c() ? this.mRootView.findViewById(R.id.action_bar_controller) : null;
    }

    protected View getContentContainer() {
        if (this.mActionBarController.c()) {
            return this.mRootView.findViewById(R.id.activity_body);
        }
        return this.mContentView == null ? null : (View) this.mContentView.getParent();
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (needMenuAction()) {
            this.mMenuAction = this.mActionBarController.a(null);
            v.a(this.mMenuAction, ThemeElement.TOP_BAR_MORE_IMAGE, (int) R.string.icon_more_vertical, ThemeElement.TOP_BAR_TEXT);
            this.mMenuAction.a(new a.b(this) {
                final /* synthetic */ ActionBarActivity a;

                {
                    this.a = r1;
                }

                public void a(a.a aVar) {
                    this.a.onToggleMenuView(true);
                }
            });
        }
    }

    public void onToggleMenuView(boolean z) {
        if (this.mDropDownMenu != null && this.mDropDownMenu.isShowing()) {
            this.mDropDownMenu.dismiss();
            this.mDropDownMenu = null;
        } else if (needMenuAction() && this.mMenuAction.c()) {
            Collection onCreateDropDownMenu = onCreateDropDownMenu();
            if (onCreateDropDownMenu != null && !onCreateDropDownMenu.isEmpty()) {
                this.mDropDownMenu = ActionBarFragment.popupMenu(this, onCreateDropDownMenu, z, this);
            }
        }
    }

    protected void onStop() {
        super.onStop();
        f.a(this.mDropDownMenu);
    }

    protected Collection<com.sds.android.ttpod.component.b.a> onCreateDropDownMenu() {
        return null;
    }

    protected boolean needMenuAction() {
        return false;
    }

    public void setContentView(int i) {
        super.setContentView(buildContentView(getLayoutInflater().inflate(i, null)));
    }

    public void setContentView(View view) {
        super.setContentView(buildContentView(view));
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(buildContentView(view), layoutParams);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mActionBarController.a(charSequence);
    }

    public void setTitle(int i) {
        super.setTitle(i);
        this.mActionBarController.b(i);
    }

    protected View buildContentView(View view) {
        this.mContentView = view;
        if (!this.mActionBarController.c()) {
            return view;
        }
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) this.mRootView.findViewById(R.id.activity_body);
            viewGroup.addView(view);
            Drawable background = view.getBackground();
            if (background != null) {
                view.setBackgroundDrawable(null);
                viewGroup.setBackgroundDrawable(background);
            }
        }
        return this.mRootView;
    }

    public void hideActionBar() {
        this.mActionBarController.c(false);
        setActivityBodyMarginTop(0);
    }

    public void showActionBar() {
        this.mActionBarController.c(true);
        setActivityBodyMarginTop(getResources().getDimensionPixelOffset(R.dimen.dialog_header_height));
    }

    private void setActivityBodyMarginTop(int i) {
        ((MarginLayoutParams) ((ViewGroup) this.mRootView.findViewById(R.id.activity_body)).getLayoutParams()).setMargins(0, i, 0, 0);
    }

    public a getActionBarController() {
        return this.mActionBarController;
    }

    protected final View getRootView() {
        return this.mRootView;
    }

    private void bindActionBar() {
        this.mActionBarController.a(new a.c(this) {
            final /* synthetic */ ActionBarActivity a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                this.a.onActionBarBackPressed();
            }
        });
    }

    protected void onActionBarBackPressed() {
        onBackPressed();
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        this.mDropDownMenu = null;
    }

    protected void onDestroy() {
        if (this.mImmersiveObserver != null) {
            this.mImmersiveObserver.a(null);
            this.mImmersiveObserver.a(null);
            this.mImmersiveObserver.b();
        }
        super.onDestroy();
    }
}

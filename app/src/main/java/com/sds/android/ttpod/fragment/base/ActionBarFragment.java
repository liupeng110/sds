package com.sds.android.ttpod.fragment.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.component.a.c;
import com.sds.android.ttpod.fragment.search.OnlineSearchFragment;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.b;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public abstract class ActionBarFragment extends BaseFragment implements c, d {
    private static final String TAG = "ActionBarFragment";
    private a mActionBarController;
    private b mDropDownMenu;
    private a mImmersiveObserver;
    private a.a mMenuAction;
    private View mRootView;
    private a.a mSearchAction;

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.APP_THEME_CHANGED, i.a(getClass(), "onThemeChanged", new Class[0]));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.activity_actionbar, viewGroup, false);
        View findViewById = this.mRootView.findViewById(R.id.action_bar_controller);
        View findViewById2 = this.mRootView.findViewById(R.id.activity_body);
        this.mActionBarController = a.a(findViewById);
        if (j.i()) {
            this.mImmersiveObserver = new a(getActivity().findViewById(R.id.view_immersive_observer));
            this.mImmersiveObserver.a(findViewById, findViewById2, this.mRootView.findViewById(R.id.status_bar_background), this.mRootView.findViewById(R.id.navigate_bar_background));
            this.mImmersiveObserver.a((c) this);
            this.mImmersiveObserver.a();
        }
        this.mActionBarController.a(new c(this) {
            final /* synthetic */ ActionBarFragment a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                this.a.onTitleClicked();
            }
        });
        addCustomActions();
        if (needSearchAction()) {
            this.mSearchAction = this.mActionBarController.a(null, "searchAction");
            this.mSearchAction.a(new a.b(this) {
                final /* synthetic */ ActionBarFragment a;

                {
                    this.a = r1;
                }

                public void a(a.a aVar) {
                    this.a.onSearchActionClicked();
                }
            });
        }
        if (needMenuAction()) {
            this.mMenuAction = this.mActionBarController.a(null, "menuAction");
            this.mMenuAction.a(new a.b(this) {
                final /* synthetic */ ActionBarFragment a;

                {
                    this.a = r1;
                }

                public void a(a.a aVar) {
                    this.a.onToggleMenuView(true);
                }
            });
        }
        return buildContentView(onCreateContentView(layoutInflater, (ViewGroup) findViewById2, bundle));
    }

    public boolean needApplyStatusBarStyle() {
        return true;
    }

    public boolean needApplyNavigationBarStyle() {
        return true;
    }

    public static void applySystemBarBackground(View view) {
        if (view != null) {
            com.sds.android.ttpod.framework.modules.theme.c.a(view.findViewById(R.id.status_bar_background), com.sds.android.ttpod.framework.modules.theme.c.b(), true);
            com.sds.android.ttpod.framework.modules.theme.c.a(view.findViewById(R.id.navigate_bar_background), com.sds.android.ttpod.framework.modules.theme.c.c(), true);
        }
    }

    protected void addCustomActions() {
    }

    protected boolean needSearchAction() {
        return true;
    }

    protected boolean needMenuAction() {
        return false;
    }

    protected void onSearchActionClicked() {
        launchFragment((BaseFragment) Fragment.instantiate(getActivity(), OnlineSearchFragment.class.getName()));
        l.av();
    }

    protected void onTitleClicked() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    protected void hideFixedAction() {
        if (needMenuAction()) {
            this.mMenuAction.a();
        }
        if (needSearchAction()) {
            this.mSearchAction.a();
        }
    }

    protected void showFixedAction() {
        if (needMenuAction()) {
            this.mMenuAction.b();
        }
        if (needSearchAction()) {
            this.mSearchAction.b();
        }
    }

    protected void hideActionBar() {
        this.mActionBarController.c(false);
        setActivityBodyMarginTop(0);
    }

    protected void showActionBar() {
        this.mActionBarController.c(true);
        setActivityBodyMarginTop(getResources().getDimensionPixelOffset(R.dimen.dialog_header_height));
    }

    private void setActivityBodyMarginTop(int i) {
        ((MarginLayoutParams) ((ViewGroup) this.mRootView.findViewById(R.id.activity_body)).getLayoutParams()).setMargins(0, i, 0, 0);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        getActionBarController().onThemeLoaded();
        if (needSearchAction()) {
            v.a(this.mSearchAction, ThemeElement.TOP_BAR_SEARCH_IMAGE, (int) R.string.icon_search, ThemeElement.TOP_BAR_TEXT);
        }
        if (needMenuAction()) {
            v.a(this.mMenuAction, ThemeElement.TOP_BAR_MORE_IMAGE, (int) R.string.icon_more_horizontal, ThemeElement.TOP_BAR_TEXT);
        }
        applySystemBarBackground(getRootView());
    }

    public void onThemeChanged() {
        onThemeLoaded();
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mImmersiveObserver != null) {
            this.mImmersiveObserver.a(null);
            this.mImmersiveObserver.a(null);
            this.mImmersiveObserver.b();
        }
        if (this.mActionBarController != null) {
            this.mActionBarController.a(null);
        }
        if (this.mSearchAction != null) {
            this.mSearchAction.a(null);
        }
        if (this.mMenuAction != null) {
            this.mMenuAction.a(null);
        }
    }

    protected View buildContentView(View view) {
        ViewGroup viewGroup = (ViewGroup) this.mRootView.findViewById(R.id.activity_body);
        if (needApplyNavigationBarStyle() || needApplyStatusBarStyle()) {
            Drawable background = view.getBackground();
            if (background != null) {
                view.setBackgroundDrawable(null);
                viewGroup.setBackgroundDrawable(background);
            }
        }
        viewGroup.addView(view);
        return this.mRootView;
    }

    public a getActionBarController() {
        return this.mActionBarController;
    }

    protected final View getRootView() {
        return this.mRootView;
    }

    public static b popupMenu(Activity activity, Collection<com.sds.android.ttpod.component.b.a> collection, boolean z, d dVar) {
        int i;
        int i2;
        int i3 = 0;
        b createDropDownMenu = createDropDownMenu(activity, collection, dVar);
        Resources resources = activity.getResources();
        createDropDownMenu.setWidth((int) resources.getDimension(z ? R.dimen.drop_down_top_right_menu_width : R.dimen.drop_down_down_center_menu_width));
        Rect rect = new Rect();
        View decorView = activity.getWindow().getDecorView();
        decorView.getWindowVisibleDisplayFrame(rect);
        if (z) {
            i3 = ((int) (resources.getDimension(R.dimen.dialog_header_height) + resources.getDimension(R.dimen.dialog_header_shadow_height))) + rect.top;
            i = 53;
            i2 = i3;
            i3 = (int) resources.getDimension(R.dimen.drop_down_top_right_right_margin);
        } else {
            i2 = 0;
            i = 81;
        }
        createDropDownMenu.showAtLocation((ViewGroup) decorView.findViewById(16908290), i, i3, i2);
        return createDropDownMenu;
    }

    protected Collection<com.sds.android.ttpod.component.b.a> onCreateDropDownMenu() {
        return null;
    }

    @TargetApi(11)
    public static b createDropDownMenu(Activity activity, Collection<com.sds.android.ttpod.component.b.a> collection, final d dVar) {
        final b bVar = new b(activity, R.layout.dialog_page_choice_list, R.id.lst_content);
        final ListAdapter anonymousClass4 = new ArrayAdapter<com.sds.android.ttpod.component.b.a>(activity, R.layout.dialog_page_choice_item, R.id.txt_title) {
            public long getItemId(int i) {
                return (long) ((com.sds.android.ttpod.component.b.a) getItem(i)).g();
            }
        };
        if (!(collection == null || collection.isEmpty())) {
            if (j.c()) {
                anonymousClass4.addAll(collection);
            } else {
                for (com.sds.android.ttpod.component.b.a add : collection) {
                    anonymousClass4.add(add);
                }
            }
        }
        bVar.a(anonymousClass4);
        bVar.a(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                bVar.dismiss();
                dVar.onDropDownMenuClicked((int) j, (com.sds.android.ttpod.component.b.a) anonymousClass4.getItem(i));
            }
        });
        return bVar;
    }

    public static b popupMenu(Activity activity, Collection<com.sds.android.ttpod.component.b.a> collection, boolean z, d dVar, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        b createDropDownMenu = createDropDownMenu(activity, collection, dVar, i, i2, i3, i4);
        Resources resources = activity.getResources();
        createDropDownMenu.setWidth((int) resources.getDimension(z ? R.dimen.drop_down_top_right_menu_width : R.dimen.drop_down_down_center_menu_width));
        Rect rect = new Rect();
        View decorView = activity.getWindow().getDecorView();
        decorView.getWindowVisibleDisplayFrame(rect);
        if (i5 < 0) {
            i5 = 0;
        }
        if (z) {
            int dimension = rect.top + ((int) (resources.getDimension(R.dimen.dialog_header_shadow_height) + resources.getDimension(R.dimen.dialog_header_height)));
            i6 = 53;
            i7 = dimension;
        } else {
            i6 = 81;
            i7 = 0;
        }
        createDropDownMenu.showAtLocation((ViewGroup) decorView.findViewById(16908290), i6, i5, i7);
        return createDropDownMenu;
    }

    @TargetApi(11)
    public static b createDropDownMenu(Activity activity, Collection<com.sds.android.ttpod.component.b.a> collection, final d dVar, int i, int i2, int i3, int i4) {
        final b bVar = new b(activity, i, i2);
        final ListAdapter anonymousClass6 = new ArrayAdapter<com.sds.android.ttpod.component.b.a>(activity, i3, i4) {
            public long getItemId(int i) {
                return (long) ((com.sds.android.ttpod.component.b.a) getItem(i)).g();
            }
        };
        if (!(collection == null || collection.isEmpty())) {
            if (j.c()) {
                anonymousClass6.addAll(collection);
            } else {
                for (com.sds.android.ttpod.component.b.a add : collection) {
                    anonymousClass6.add(add);
                }
            }
        }
        bVar.a(anonymousClass6);
        bVar.a(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                bVar.dismiss();
                dVar.onDropDownMenuClicked((int) j, (com.sds.android.ttpod.component.b.a) anonymousClass6.getItem(i));
            }
        });
        return bVar;
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        this.mDropDownMenu = null;
        switch (i) {
            case 1:
                f.h(getActivity());
                return;
            case 2:
                f.c(getActivity());
                return;
            case 3:
                f.b();
                return;
            default:
                return;
        }
    }

    public void onToggleMenuView(boolean z) {
        if (this.mDropDownMenu != null && this.mDropDownMenu.isShowing()) {
            this.mDropDownMenu.dismiss();
            this.mDropDownMenu = null;
        } else if (needMenuAction() && this.mMenuAction.c()) {
            Collection onCreateDropDownMenu = onCreateDropDownMenu();
            if (!(onCreateDropDownMenu == null || onCreateDropDownMenu.isEmpty())) {
                this.mDropDownMenu = popupMenu(getActivity(), onCreateDropDownMenu, z, this);
            }
        }
        l.ag();
    }

    public void onStop() {
        super.onStop();
        com.sds.android.ttpod.component.d.f.a(this.mDropDownMenu);
    }
}

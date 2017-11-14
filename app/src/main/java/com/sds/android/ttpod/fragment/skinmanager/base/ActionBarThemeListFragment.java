package com.sds.android.ttpod.fragment.skinmanager.base;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.fragment.base.c;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class ActionBarThemeListFragment extends OnlineThemeFragment implements c {
    private a mActionBarController;
    private View mRootView;

    protected abstract String getTitle();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = View.inflate(getActivity(), R.layout.activity_actionbar, null);
        View findViewById = this.mRootView.findViewById(R.id.action_bar_controller);
        ViewGroup viewGroup2 = (ViewGroup) this.mRootView.findViewById(R.id.activity_body);
        this.mActionBarController = a.a(findViewById);
        this.mActionBarController.a(new a.c(this) {
            final /* synthetic */ ActionBarThemeListFragment a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                this.a.onTitleClicked();
            }
        });
        if (j.i()) {
            com.sds.android.ttpod.fragment.base.a aVar = new com.sds.android.ttpod.fragment.base.a(getActivity().findViewById(R.id.view_immersive_observer));
            aVar.a(findViewById, viewGroup2, this.mRootView.findViewById(R.id.status_bar_background), this.mRootView.findViewById(R.id.navigate_bar_background));
            aVar.a((c) this);
            aVar.a();
        }
        layoutInflater.inflate(R.layout.fragment_base_theme_layout, viewGroup2, true);
        return this.mRootView;
    }

    public boolean needApplyNavigationBarStyle() {
        return true;
    }

    public boolean needApplyStatusBarStyle() {
        return true;
    }

    private void onTitleClicked() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mActionBarController.a(getTitle());
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.APP_THEME_CHANGED, i.a(getClass(), "onThemeLoaded", new Class[0]));
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mActionBarController.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.b(this.mRootView, v.a());
        ActionBarFragment.applySystemBarBackground(this.mRootView);
    }

    public void updateBackground(Drawable drawable) {
        super.updateBackground(drawable);
        if (drawable != null) {
            com.sds.android.ttpod.framework.modules.theme.c.b(getRootView(), drawable);
        }
    }

    protected final View getRootView() {
        return this.mRootView;
    }
}

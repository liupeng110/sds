package com.sds.android.ttpod.activities.musiccircle.search;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.c;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;

public class SearchFriendsFragment extends SlidingClosableFragment {
    private EditText mSearchContentInput;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_music_circle_search");
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_search_layout, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        getActionBarController().b((int) R.string.musiccircle_search_title);
        this.mSearchContentInput = (EditText) view.findViewById(R.id.edittext_username);
        this.mSearchContentInput.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ SearchFriendsFragment a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                this.a.search();
                return true;
            }
        });
        view.findViewById(R.id.button_search).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchFriendsFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.search();
                c.n();
            }
        });
        c.m();
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void onBackPressed() {
        super.onBackPressed();
        c.o();
    }

    private void search() {
        if (EnvironmentUtils.c.e()) {
            String obj = this.mSearchContentInput.getText().toString();
            if (m.a(obj)) {
                f.a((int) R.string.social_search_empty);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("search_content", obj);
            BaseFragment searchFriendsResultFragment = new SearchFriendsResultFragment();
            searchFriendsResultFragment.setArguments(bundle);
            launchFragment(searchFriendsResultFragment);
            hideSoftInput();
            return;
        }
        f.a((int) R.string.network_error);
    }

    private void hideSoftInput() {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mSearchContentInput.getWindowToken(), 0);
    }

    protected void onSlidingClosed() {
        hideSoftInput();
        super.onSlidingClosed();
    }
}

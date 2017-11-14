package com.sds.android.ttpod.activities.local;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.b.g;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.a;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.util.Locale;

public abstract class BaseSearchActivity extends SlidingClosableActivity implements OnItemClickListener, b, a {
    private static final String LOG_TAG = "BaseSearchActivity";
    private com.sds.android.ttpod.adapter.c.a mAdapter;
    private EditText mEdtPrompt;
    private InputMethodManager mInputMgr;
    private String mOrigin;
    protected ActionExpandableListView mSearchResultListView;
    private boolean mSwitchToSystemKeyboard;
    private TextWatcher mTextWatcher = new TextWatcher(this) {
        final /* synthetic */ BaseSearchActivity a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            this.a.search(editable.toString());
        }
    };
    private TextView mTvPrompt;

    protected abstract com.sds.android.ttpod.adapter.c.a onCreateAdapter();

    public ActionExpandableListView getListView() {
        return this.mSearchResultListView;
    }

    public void onThemeLoaded() {
        getActionBarController().onThemeLoaded();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mInputMgr = (InputMethodManager) getSystemService("input_method");
        setContentView((int) R.layout.activity_local_search);
        getActionBarController().b((int) R.string.menu_find);
        this.mTvPrompt = (TextView) findViewById(R.id.tv_no_result);
        c.a(this.mTvPrompt, ThemeElement.SONG_LIST_ITEM_TEXT);
        this.mEdtPrompt = (EditText) findViewById(R.id.search_input_edittext);
        this.mSearchResultListView = (ActionExpandableListView) findViewById(R.id.search_result_listview);
        this.mAdapter = onCreateAdapter();
        this.mSearchResultListView.a(this.mAdapter, R.id.menu_view, R.id.expandable);
        this.mSearchResultListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ BaseSearchActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.mAdapter != null && this.a.mSearchResultListView != null) {
                    int a = m.a(this.a.mSearchResultListView.getHeaderViewsCount(), i, this.a.mAdapter.getCount());
                    if (a > -1) {
                        this.a.onItemClicked(adapterView, view, a, j);
                    }
                }
            }
        });
        this.mSearchResultListView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ BaseSearchActivity a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                int a = m.a(this.a.mSearchResultListView.getHeaderViewsCount(), i, this.a.mAdapter.getCount());
                if (a > -1) {
                    p.a(a + 1);
                    this.a.onMediaItemLongClicked(adapterView, view, a, j);
                }
                return true;
            }
        });
        this.mSearchResultListView.setItemExpandCollapseListener(this);
        c.a(this.mSearchResultListView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mSearchResultListView, ThemeElement.COMMON_SEPARATOR);
        c.a(findViewById(R.id.search_screen_layout), ThemeElement.BACKGROUND_MASK);
        getRootView().setBackgroundDrawable(v.a());
        this.mSearchResultListView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ BaseSearchActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.mInputMgr.hideSoftInputFromWindow(this.a.mEdtPrompt.getWindowToken(), 0);
                return false;
            }
        });
        this.mEdtPrompt.addTextChangedListener(this.mTextWatcher);
    }

    public void onExpand(View view, int i) {
        updateMenuArrowView(view, true);
    }

    public void onCollapse(View view, int i) {
        updateMenuArrowView(view, false);
    }

    private void updateMenuArrowView(View view, boolean z) {
        ((g) ((View) view.getParent()).getTag()).d().setText(z ? R.string.icon_arrow_top : R.string.icon_arrow_down);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mOrigin = intent.getStringExtra("origin");
    }

    protected void onPause() {
        super.onPause();
        this.mInputMgr.hideSoftInputFromWindow(this.mEdtPrompt.getWindowToken(), 0);
    }

    protected void onLoadDataFinished() {
        if (!this.mSwitchToSystemKeyboard) {
            this.mSwitchToSystemKeyboard = true;
            this.mEdtPrompt.postDelayed(new Runnable(this) {
                final /* synthetic */ BaseSearchActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.switchToSystemKeyboard();
                }
            }, 500);
        }
    }

    private void switchToSystemKeyboard() {
        if (this.mEdtPrompt != null) {
            this.mEdtPrompt.setVisibility(0);
            this.mEdtPrompt.requestFocus();
            this.mInputMgr.showSoftInput(this.mEdtPrompt, 2);
        }
    }

    private void search(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTvPrompt.setVisibility(8);
            this.mAdapter.e();
        } else if (this.mAdapter.a(str.toUpperCase(Locale.US)) > 0) {
            this.mTvPrompt.setVisibility(8);
        } else {
            this.mTvPrompt.setVisibility(0);
            this.mTvPrompt.setText(getString(R.string.search_result_no_result, new Object[]{this.mOrigin}));
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void onItemClicked(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void onMediaItemLongClicked(AdapterView<?> adapterView, View view, int i, long j) {
    }
}

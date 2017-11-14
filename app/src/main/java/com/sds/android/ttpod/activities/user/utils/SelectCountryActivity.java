package com.sds.android.ttpod.activities.user.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.widget.AZSideBar;
import java.util.ArrayList;
import java.util.List;

public class SelectCountryActivity extends SlidingClosableActivity {
    public static final String SPLITTER = ",";
    private AZSideBar mSideBar;

    private static class a extends BaseAdapter {
        private BaseActivity a;
        private List<String> b;
        private List<String> c;

        public a(BaseActivity baseActivity, List<String> list, List<String> list2) {
            this.a = baseActivity;
            this.b = list;
            this.c = list2;
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return this.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEnabled(int i) {
            return !a(i) && super.isEnabled(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            String str = (String) this.c.get(i);
            if (this.b.contains(str)) {
                View inflate = this.a.getLayoutInflater().inflate(R.layout.country_section, viewGroup, false);
                ((TextView) inflate.findViewById(R.id.tv_name)).setText(str);
                return inflate;
            }
            View inflate2 = this.a.getLayoutInflater().inflate(R.layout.country_item, viewGroup, false);
            String[] split = str.split(SelectCountryActivity.SPLITTER);
            if (split.length == 3) {
                ((TextView) inflate2.findViewById(R.id.tv_country_name)).setText(split[1]);
                ((TextView) inflate2.findViewById(R.id.tv_country_code)).setText(split[2]);
            }
            return inflate2;
        }

        private boolean a(int i) {
            return this.b.contains(this.c.get(i));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActionBarController().d();
        setContentView((int) R.layout.activity_select_country);
        setTitle((int) R.string.select_country);
        final ListView listView = (ListView) findViewById(R.id.list);
        this.mSideBar = (AZSideBar) findViewById(R.id.azsidebar);
        List arrayList = new ArrayList();
        final List arrayList2 = new ArrayList();
        initCountryList(arrayList, arrayList2);
        listView.setAdapter(new a(this, arrayList, arrayList2));
        listView.setOnScrollListener(this.mSideBar);
        this.mSideBar.a(arrayList);
        this.mSideBar.setOnMatchedPositionChangeListener(new com.sds.android.ttpod.widget.AZSideBar.a(this) {
            final /* synthetic */ SelectCountryActivity c;

            public void a(int i, String str) {
                int indexOf = arrayList2.indexOf(str);
                if (indexOf > 0) {
                    listView.setSelection(indexOf);
                }
            }
        });
        listView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SelectCountryActivity b;

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    Intent intent = new Intent();
                    intent.putExtra("region_code_result", (String) arrayList2.get(i));
                    this.b.setResult(-1, intent);
                    this.b.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.mSideBar.onThemeLoaded();
    }

    private void initCountryList(List<String> list, List<String> list2) {
        for (String str : getResources().getStringArray(R.array.country_code)) {
            String toUpperCase = str.split(SPLITTER)[0].toUpperCase();
            if (!list.contains(toUpperCase)) {
                list.add(toUpperCase);
                list2.add(toUpperCase);
            }
            list2.add(str);
        }
    }

    public void onThemeChanged() {
        super.onThemeChanged();
        this.mSideBar.onThemeLoaded();
    }
}

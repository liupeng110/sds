package com.sds.android.ttpod.activities.mediascan.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.mediascan.FilePickerActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediaScanAutoScanActivity extends SlidingClosableActivity {
    private static final int REQUEST_CODE_FILE_PICKER = 0;
    private static final String TAG = "MediaScanAutoScanActivity";
    private a mAdapter;
    private Set<String> mAutoScanFolderSet = new HashSet();
    private View mAutoScanView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ MediaScanAutoScanActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linearlayout_mediascan_autoscan_listview_footer:
                    Intent intent = new Intent(this.a, FilePickerActivity.class);
                    intent.putExtra(FilePickerActivity.KEY_EXTRA_SHOW_FILE_TYPE, 2);
                    intent.putExtra(FilePickerActivity.KEY_EXTRA_CONFIRMYPE, 1);
                    this.a.startActivityForResult(intent, 0);
                    return;
                case R.id.textview_mediascan_setting_auto_scan_start:
                    if (this.a.mAdapter.getCount() > 0) {
                        Intent intent2 = new Intent(this.a, MediaScanAnimationActivity.class);
                        intent2.putExtra(MediaScanAnimationActivity.KEY_SCAN_FILES, (String[]) this.a.mAutoScanFolderSet.toArray(new String[0]));
                        this.a.startActivity(intent2);
                        this.a.setResult(-1, null);
                        this.a.finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    private final class a extends BaseAdapter {
        final /* synthetic */ MediaScanAutoScanActivity a;
        private List<String> b;

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        private a(MediaScanAutoScanActivity mediaScanAutoScanActivity) {
            this.a = mediaScanAutoScanActivity;
            this.b = new ArrayList();
            this.b.addAll(mediaScanAutoScanActivity.mAutoScanFolderSet);
        }

        private void a(String[] strArr) {
            int length = strArr.length;
            if (strArr != null && length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!this.a.mAutoScanFolderSet.contains(strArr[i])) {
                        this.a.mAutoScanFolderSet.add(strArr[i]);
                        this.b.add(strArr[i]);
                    }
                }
                g.a(MediaScanAutoScanActivity.TAG, "setPaths=" + this.b);
                notifyDataSetChanged();
            }
        }

        public int getCount() {
            return this.b.size();
        }

        public String a(int i) {
            return (String) this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = LayoutInflater.from(this.a).inflate(R.layout.mediascan_auto_scan_item, viewGroup, false);
                b bVar2 = new b((TextView) view.findViewById(R.id.textview_mediascan_exclude_item_title), (TextView) view.findViewById(R.id.textview_mediascan_exclude_item_sub_title), (ImageView) view.findViewById(R.id.imageview_mediascan_auto_scan_item_remove));
                bVar2.c.setContentDescription("remove" + i);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            bVar.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (i < this.b.b.size()) {
                        this.b.a.mAutoScanFolderSet.remove(this.b.b.get(i));
                        this.b.b.remove(i);
                        this.b.notifyDataSetChanged();
                    }
                }
            });
            String a = a(i);
            bVar.a.setText(e.j(a));
            bVar.b.setText(e.l(a));
            return view;
        }
    }

    private static final class b {
        private TextView a;
        private TextView b;
        private ImageView c;

        private b(TextView textView, TextView textView2, ImageView imageView) {
            this.a = textView;
            this.b = textView2;
            this.c = imageView;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_mediascan_auto_scan);
        setTitle((int) R.string.mediascan_setting_choose_auto_scan_foders);
        this.mAutoScanFolderSet = com.sds.android.ttpod.framework.storage.environment.b.k() != null ? com.sds.android.ttpod.framework.storage.environment.b.k() : this.mAutoScanFolderSet;
        ListView listView = (ListView) findViewById(R.id.listview_mediascan_auto_scan);
        View inflate = LayoutInflater.from(this).inflate(R.layout.mediascan_auto_scan_listview_footer, null);
        inflate.setOnClickListener(this.mOnClickListener);
        listView.addFooterView(inflate);
        this.mAdapter = new a();
        listView.setAdapter(this.mAdapter);
        this.mAutoScanView = findViewById(R.id.textview_mediascan_setting_auto_scan_start);
        this.mAutoScanView.setOnClickListener(this.mOnClickListener);
    }

    protected void onPause() {
        g.a(TAG, "onPause");
        super.onPause();
        com.sds.android.ttpod.framework.storage.environment.b.b(this.mAutoScanFolderSet);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 0:
                if (i2 == -1) {
                    this.mAdapter.a(intent.getStringArrayExtra(FilePickerActivity.KEY_EXTRA_SELECTED_FILES));
                    return;
                }
                return;
            default:
                return;
        }
    }
}

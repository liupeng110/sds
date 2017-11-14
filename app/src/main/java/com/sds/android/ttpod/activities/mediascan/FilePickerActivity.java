package com.sds.android.ttpod.activities.mediascan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.EnvironmentUtils.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.s;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

public class FilePickerActivity extends SlidingClosableActivity {
    public static final int CHOICE_MODE_MULTI = 2;
    public static final int CHOICE_MODE_SINGLE = 1;
    public static final int CONFIRMYPE_ADD = 1;
    public static final int CONFIRMYPE_CONFIRM = 0;
    public static final int CONFIRMYPE_SCAN = 2;
    public static final int CONFIRMYPE_UNKNOWN = 3;
    public static final String DEFAULT_MEDIA_FILE_EXTENSIONS = "mp3|wma|aac|m4a|amr|ape|flac|awb|imy|mid|midi|oga|ogg|ota|rtttl|rtx|smf|wav|xmf|cue|";
    private static final List<String> EMPTY_STRING_LIST = new ArrayList();
    private static final File EXTERNAL_STORAGE_DIRECTORY = new File(d.b());
    public static final int FILE_TYPE_ALL = 0;
    public static final int FILE_TYPE_DIRECTORY = 2;
    public static final int FILE_TYPE_FILE = 1;
    public static final String KEY_EXTRA_CHOICE_MODE = "key_choice_mode";
    public static final String KEY_EXTRA_CONFIRMYPE = "confirm_text";
    public static final String KEY_EXTRA_EXCLUDED_EXTENSIONS = "key_extensions";
    public static final String KEY_EXTRA_INCLUDE_EXTENSIONS = "";
    public static final String KEY_EXTRA_ISSELECT_MEDIA = "isselect_media";
    public static final String KEY_EXTRA_NEW_FOLDER = "key_create_new_folder";
    public static final String KEY_EXTRA_PATH = "key_path";
    public static final String KEY_EXTRA_SELECTED_FILES = "selected_files";
    public static final String KEY_EXTRA_SHOW_FILE_TYPE = "key_file_or_directory";
    private static final String TAG = "FilePickerActivity";
    private b mAdapter;
    private int mChoiceMode = 2;
    private int mConfirmButtonTextId;
    private String mEntryPath;
    private String mExcludedExtensions = "";
    private String mIncludeExtensions = "";
    private boolean mIsNewFolderEnable;
    private ListView mListView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ FilePickerActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_filepicker_confirm:
                    this.a.saveSelectAndFinish();
                    return;
                case R.id.textview_mediascan_filepicker_item_folder:
                case R.id.textview_mediascan_filepicker_item_title:
                    this.a.doUpperPath();
                    return;
                default:
                    return;
            }
        }
    };
    private Stack<Integer> mPositionsStack = new Stack();
    private com.sds.android.ttpod.component.a.a mSelectAction;
    private int mShowFileType = 0;
    private TextView mTextViewHeader;
    private TextView mTextViewHeaderIcon;
    private int mToastId;

    private static final class a {
        private String a;
        private boolean b;

        private a(String str) {
            this(str, false);
        }

        private a(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public static a[] a(File[] fileArr) {
            if (fileArr == null || fileArr.length == 0) {
                return null;
            }
            int length = fileArr.length;
            a[] aVarArr = new a[length];
            for (int i = 0; i < length; i++) {
                aVarArr[i] = new a(fileArr[i].getAbsolutePath());
            }
            return aVarArr;
        }
    }

    private final class b extends BaseAdapter {
        final /* synthetic */ FilePickerActivity a;
        private a[] b = new a[0];
        private int c = 0;
        private String d = "";
        private FileFilter e = new FileFilter(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean accept(File file) {
                boolean z = true;
                if (file.isDirectory()) {
                    if (this.a.a.mShowFileType == 1) {
                        z = false;
                    }
                    return z;
                } else if (!file.isFile() || this.a.a.mShowFileType == 2) {
                    return false;
                } else {
                    String m = e.m(file.getName());
                    if (m.length() <= 0 || !this.a.a.mIncludeExtensions.contains(m.toLowerCase(Locale.US) + "|")) {
                        return false;
                    }
                    return true;
                }
            }
        };
        private final Comparator<File> f = new Comparator<File>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((File) obj, (File) obj2);
            }

            public int a(File file, File file2) {
                if (file.isDirectory()) {
                    if (!file2.isDirectory()) {
                        return -1;
                    }
                } else if (file2.isDirectory()) {
                    return 1;
                }
                return file.getName().compareToIgnoreCase(file2.getName());
            }
        };

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public b(FilePickerActivity filePickerActivity, String str) {
            this.a = filePickerActivity;
            this.d = str;
            if (filePickerActivity.isMultiMode()) {
                a(str, false, 0, FilePickerActivity.EMPTY_STRING_LIST);
                return;
            }
            List arrayList = new ArrayList(1);
            arrayList.add(str);
            a(e.l(str), false, 0, arrayList);
        }

        private void a(a[] aVarArr) {
            this.b = aVarArr;
            this.c = 0;
            notifyDataSetChanged();
        }

        private void a(boolean z) {
            int i;
            int i2 = 0;
            int count = getCount();
            if (z) {
                i = count;
            } else {
                i = 0;
            }
            this.c = i;
            if (count > 0) {
                while (i2 < count) {
                    a(i2).b = z;
                    i2++;
                }
                b(z);
                this.a.mAdapter.notifyDataSetChanged();
            }
        }

        private void b(boolean z) {
            if (!this.a.isMultiMode()) {
                return;
            }
            if (z) {
                this.a.setSelectAllAction();
            } else {
                this.a.setSelectNoneAction();
            }
        }

        private String[] a() {
            int count = getCount();
            if (count <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < count; i++) {
                if (a(i).b) {
                    arrayList.add(a(i).a);
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        private void a(String str) {
            boolean z = true;
            for (a aVar : this.b) {
                if (!this.a.isMultiMode()) {
                    aVar.b = str.equals(aVar.a);
                } else if (str.equals(aVar.a)) {
                    if (aVar.b) {
                        this.c--;
                        aVar.b = false;
                    } else {
                        this.c++;
                        aVar.b = true;
                    }
                }
            }
            if (this.a.isMultiMode()) {
                if (this.c != getCount()) {
                    z = false;
                }
                b(z);
            }
            this.a.mAdapter.notifyDataSetChanged();
        }

        private void a(String str, boolean z, int i, List<String> list) {
            File access$1600 = TextUtils.isEmpty(str) ? FilePickerActivity.EXTERNAL_STORAGE_DIRECTORY : new File(str);
            if (!access$1600.exists() && !access$1600.mkdirs()) {
                g.a(FilePickerActivity.TAG, "create " + access$1600 + " failed.");
            } else if (access$1600.isDirectory()) {
                if (this.a.isMultiMode() && this.a.mSelectAction.g() != null) {
                    this.a.setSelectNoneAction();
                }
                final File file = access$1600;
                final List<String> list2 = list;
                final boolean z2 = z;
                final int i2 = i;
                com.sds.android.sdk.lib.e.a.a(this.a, new com.sds.android.sdk.lib.e.a.a<File, a[]>(this, access$1600) {
                    final /* synthetic */ b e;

                    protected /* synthetic */ Object onDoInBackground(Object obj) {
                        return a((File) obj);
                    }

                    protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                        a((a[]) obj);
                    }

                    protected a[] a(File file) {
                        if (file == null || !file.isDirectory()) {
                            return null;
                        }
                        File[] listFiles = file.listFiles(this.e.e);
                        if (listFiles != null && listFiles.length > 0) {
                            Arrays.sort(listFiles, this.e.f);
                        }
                        return a.a(listFiles);
                    }

                    protected void a(a[] aVarArr) {
                        if (aVarArr == null || aVarArr.length == 0) {
                            this.e.a.mTextViewHeader.setText(this.e.d);
                            this.e.a(file.getAbsolutePath());
                            return;
                        }
                        int i;
                        for (a aVar : aVarArr) {
                            aVar.b = list2.contains(aVar.a);
                        }
                        this.e.d = file.getAbsolutePath();
                        this.e.a.mTextViewHeader.setText(this.e.d);
                        TextView access$2000 = this.e.a.mTextViewHeaderIcon;
                        if (file.getParentFile() == null) {
                            i = 4;
                        } else {
                            i = 0;
                        }
                        access$2000.setVisibility(i);
                        this.e.a.mAdapter.a(aVarArr);
                        if (!z2) {
                            this.e.a.mPositionsStack.push(Integer.valueOf(i2));
                            this.e.a.mListView.setSelection(0);
                        } else if (!this.e.a.mPositionsStack.isEmpty()) {
                            this.e.a.mListView.setSelection(((Integer) this.e.a.mPositionsStack.pop()).intValue());
                        }
                    }
                });
            }
        }

        public int getCount() {
            return this.b == null ? 0 : this.b.length;
        }

        public a a(int i) {
            return this.b[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            c cVar;
            if (view == null) {
                view = LayoutInflater.from(this.a).inflate(R.layout.mediascan_filepicker_item, viewGroup, false);
                c cVar2 = new c((TextView) view.findViewById(R.id.textview_mediascan_filepicker_item_folder), (TextView) view.findViewById(R.id.textview_mediascan_filepicker_item_title), (IconTextView) view.findViewById(R.id.itv_mediascan_filepicker_item));
                view.setTag(cVar2);
                cVar = cVar2;
            } else {
                cVar = (c) view.getTag();
            }
            a a = a(i);
            cVar.c.setOnCheckedChangeListener(null);
            cVar.c.setChecked(a.b);
            cVar.c.setContentDescription("" + i);
            cVar.c.setOnCheckedChangeListener(new com.sds.android.ttpod.common.widget.IconTextView.a(this) {
                final /* synthetic */ b b;

                public void a(IconTextView iconTextView, boolean z, boolean z2) {
                    if (i < this.b.b.length && i >= 0) {
                        this.b.a(this.b.a(i).a);
                    }
                }
            });
            cVar.b.setText(e.j(a.a));
            if (new File(a.a).isDirectory()) {
                cVar.a.setBackgroundResource(R.drawable.img_mediascan_item_folder);
                cVar.a.setText("");
            } else {
                cVar.a.setBackgroundResource(R.drawable.img_mediascan_item_file);
                cVar.a.setText(e.m(a.a.toUpperCase(Locale.US)));
            }
            return view;
        }
    }

    private static final class c {
        private TextView a;
        private TextView b;
        private IconTextView c;

        private c(TextView textView, TextView textView2, IconTextView iconTextView) {
            this.a = textView;
            this.b = textView2;
            this.c = iconTextView;
            this.c.a((int) R.string.icon_unchecked, (int) R.string.icon_checked);
            this.c.setCheckable(true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_mediascan_filepicker);
        setStatisticPage(s.PAGE_SCAN_MUSIC_CUSTOM);
        onNewIntent(getIntent());
        initActionBar();
        this.mListView = (ListView) findViewById(R.id.listview_mediascan_filepicker);
        this.mListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ FilePickerActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int a = m.a(this.a.mListView.getHeaderViewsCount(), i, this.a.mAdapter.getCount());
                if (a <= -1) {
                    return;
                }
                if (e.d(this.a.mAdapter.a(a).a)) {
                    this.a.mAdapter.a(this.a.mAdapter.a(a).a, false, a, FilePickerActivity.EMPTY_STRING_LIST);
                } else {
                    this.a.mAdapter.a(this.a.mAdapter.a(a).a);
                }
            }
        });
        View inflate = getLayoutInflater().inflate(R.layout.mediascan_filepicker_header, this.mListView, false);
        this.mListView.addHeaderView(inflate);
        this.mTextViewHeaderIcon = (TextView) inflate.findViewById(R.id.textview_mediascan_filepicker_item_folder);
        this.mTextViewHeaderIcon.setOnClickListener(this.mOnClickListener);
        this.mTextViewHeader = (TextView) inflate.findViewById(R.id.textview_mediascan_filepicker_item_title);
        this.mTextViewHeader.setOnClickListener(this.mOnClickListener);
        Button button = (Button) findViewById(R.id.button_filepicker_confirm);
        button.setOnClickListener(this.mOnClickListener);
        button.setText(this.mConfirmButtonTextId);
        this.mAdapter = new b(this, this.mEntryPath);
        this.mListView.setAdapter(this.mAdapter);
    }

    private void setSelectAllAction() {
        this.mSelectAction.a(this.mSelectAction);
        this.mSelectAction.e(R.string.icon_checked);
    }

    private void setSelectNoneAction() {
        this.mSelectAction.a(null);
        this.mSelectAction.e(R.string.icon_unchecked);
    }

    private void initActionBar() {
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        if (isMultiMode()) {
            this.mSelectAction = actionBarController.a(null, "selectAction");
            this.mSelectAction.b(getResources().getColorStateList(R.color.default_action_bar_color));
            setSelectNoneAction();
            this.mSelectAction.a(new com.sds.android.ttpod.component.a.b(this) {
                final /* synthetic */ FilePickerActivity a;

                {
                    this.a = r1;
                }

                public void a(com.sds.android.ttpod.component.a.a aVar) {
                    if (aVar.g() == null) {
                        this.a.mAdapter.a(true);
                        this.a.setSelectAllAction();
                        return;
                    }
                    this.a.mAdapter.a(false);
                    this.a.setSelectNoneAction();
                }
            });
        } else {
            actionBarController.d((int) R.drawable.img_mediascan_filepicker_new_folder).a(new com.sds.android.ttpod.component.a.b(this) {
                final /* synthetic */ FilePickerActivity a;

                {
                    this.a = r1;
                }

                public void a(com.sds.android.ttpod.component.a.a aVar) {
                    com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(this.a, new com.sds.android.ttpod.component.d.a.b.a[]{new com.sds.android.ttpod.component.d.a.b.a(1, this.a.getString(R.string.folder), "", this.a.getString(R.string.new_folder_hint))}, R.string.create, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                            String charSequence = bVar.c(1).e().toString();
                            String charSequence2 = this.a.a.mTextViewHeader.getText().toString();
                            f.a(new File(charSequence2.endsWith("/") ? new StringBuilder().append(charSequence2).append(charSequence).toString() : new StringBuilder().append(charSequence2).append("/").append(charSequence).toString()).mkdir() ? this.a.a.getString(R.string.new_folder_success) : this.a.a.getString(R.string.new_folder_fail));
                            this.a.a.mAdapter.a(charSequence2, false, 0, FilePickerActivity.EMPTY_STRING_LIST);
                        }
                    }, null);
                    bVar.setTitle(this.a.getString(R.string.new_folder_title));
                    bVar.show();
                }
            });
        }
        actionBarController.d();
    }

    protected void onNewIntent(Intent intent) {
        int intExtra;
        int i;
        boolean z = false;
        super.onNewIntent(intent);
        if (intent != null) {
            String stringExtra = intent.getStringExtra(KEY_EXTRA_EXCLUDED_EXTENSIONS);
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = "";
            }
            this.mExcludedExtensions = stringExtra;
            stringExtra = intent.getStringExtra("");
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = DEFAULT_MEDIA_FILE_EXTENSIONS;
            }
            this.mIncludeExtensions = stringExtra;
            this.mShowFileType = intent.getIntExtra(KEY_EXTRA_SHOW_FILE_TYPE, 0);
            this.mEntryPath = intent.getStringExtra(KEY_EXTRA_PATH);
            if (!e.d(this.mEntryPath)) {
                this.mEntryPath = d.b();
            }
            this.mChoiceMode = intent.getIntExtra(KEY_EXTRA_CHOICE_MODE, 2);
            this.mIsNewFolderEnable = intent.getBooleanExtra(KEY_EXTRA_NEW_FOLDER, false);
            boolean booleanExtra = intent.getBooleanExtra(KEY_EXTRA_ISSELECT_MEDIA, true);
            intExtra = intent.getIntExtra(KEY_EXTRA_CONFIRMYPE, 0);
            z = booleanExtra;
        } else {
            intExtra = 0;
        }
        switch (this.mShowFileType) {
            case 1:
                this.mToastId = z ? R.string.filepicker_notify_select_media : R.string.filepicker_notify_select_file;
                if (!z) {
                    i = R.string.filepicker_select_file;
                    break;
                } else {
                    i = R.string.filepicker_select_media;
                    break;
                }
            case 2:
                this.mToastId = R.string.filepicker_notify_select_folder;
                i = R.string.filepicker_select_folder;
                break;
            default:
                this.mToastId = z ? R.string.filepicker_notify_select_folder_or_media : R.string.filepicker_notify_select_folder_or_file;
                if (!z) {
                    i = R.string.filepicker_select_file_or_folder;
                    break;
                } else {
                    i = R.string.filepicker_select_media_or_folder;
                    break;
                }
        }
        setTitle(getString(i));
        switch (intExtra) {
            case 1:
                this.mConfirmButtonTextId = R.string.filepicker_confirm_add;
                return;
            case 2:
                this.mConfirmButtonTextId = R.string.filepicker_confirm_sacan;
                return;
            case 3:
                this.mConfirmButtonTextId = R.string.unknown;
                return;
            default:
                this.mConfirmButtonTextId = R.string.filepicker_confirm_confirm;
                return;
        }
    }

    public void onBackPressed() {
        if (!doUpperPath()) {
            setResult(0);
            finish();
        }
    }

    protected void onActionBarBackPressed() {
        setResult(0);
        finish();
    }

    private void saveSelectAndFinish() {
        Intent intent = new Intent();
        String[] a = this.mAdapter.a();
        if (a == null || a.length == 0) {
            f.a(this.mToastId);
            return;
        }
        if (isMultiMode()) {
            intent.putExtra(KEY_EXTRA_SELECTED_FILES, a);
        } else {
            intent.putExtra(KEY_EXTRA_SELECTED_FILES, a[0]);
        }
        setResult(-1, intent);
        finish();
    }

    private boolean doUpperPath() {
        if (com.sds.android.sdk.lib.util.m.a(this.mAdapter.d)) {
            return false;
        }
        File parentFile = new File(this.mAdapter.d).getParentFile();
        if (parentFile == null) {
            return false;
        }
        this.mAdapter.a(parentFile.getAbsolutePath(), true, 0, EMPTY_STRING_LIST);
        return true;
    }

    private boolean isMultiMode() {
        return this.mChoiceMode == 2;
    }
}

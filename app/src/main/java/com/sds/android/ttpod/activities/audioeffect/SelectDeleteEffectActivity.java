package com.sds.android.ttpod.activities.audioeffect;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.a.b;
import com.sds.android.ttpod.fragment.audioeffect.BaseEffectListFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.core.audioeffect.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.List;

public class SelectDeleteEffectActivity extends SlidingClosableActivity {
    public static final String KEY_EFFECT_LIST = "key_effect_list";
    private static List<f> sEffectList;
    private View mDeleteView;
    private SelectDeleteEffectListFragment mFragment;
    private boolean mIsSelected = false;
    private List<f> mList = new ArrayList();
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ SelectDeleteEffectActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.button_delete) {
                this.a.performDeleteClick();
            }
        }
    };
    private com.sds.android.ttpod.component.a.a mSelectionAction;

    public class SelectDeleteEffectListFragment extends BaseEffectListFragment {
        private SparseBooleanArray mSelectedArray = new SparseBooleanArray();

        protected View getEffectItem(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater(null).inflate(R.layout.effect_list_item, null);
                view.setTag(new a(SelectDeleteEffectActivity.this, view));
            }
            a aVar = (a) view.getTag();
            aVar.a().setText(((f) this.mMyEffectItemList.get(i)).a());
            aVar.b().setText(getString(R.string.effect_provide_by, r1.b()));
            aVar.d().setVisibility(8);
            aVar.c().setVisibility(0);
            aVar.c().setChecked(this.mSelectedArray.get(i));
            aVar.c().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SelectDeleteEffectListFragment b;

                public void onClick(View view) {
                    this.b.mSelectedArray.put(i, !this.b.mSelectedArray.get(i));
                    SelectDeleteEffectActivity.this.updateAction();
                }
            });
            return view;
        }

        public void updateData(List<f> list) {
            super.updateData(list);
            this.mSelectedArray = new SparseBooleanArray(list.size());
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            super.onItemClick(adapterView, view, i, j);
            this.mSelectedArray.put(i, !this.mSelectedArray.get(i));
            notifyDataSetChanged();
            SelectDeleteEffectActivity.this.updateAction();
            if (!SelectDeleteEffectActivity.this.mIsSelected) {
                com.sds.android.ttpod.framework.a.b.f.y();
                t.a("PAGE_CLICK", r.ACTION_EFFECT_DEL_SELECTED, s.PAGE_NONE, s.PAGE_AUDIO_MY_CLOUD_EFFECT);
                SelectDeleteEffectActivity.this.mIsSelected = true;
            }
        }

        public void selectAll() {
            int count = this.mAdapter.getCount();
            for (int i = 0; i < count; i++) {
                this.mSelectedArray.put(i, true);
            }
            notifyDataSetChanged();
        }

        public void selectNone() {
            this.mSelectedArray.clear();
            notifyDataSetChanged();
        }

        public List<f> getSelectItems() {
            int size = this.mSelectedArray.size();
            List<f> arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                if (this.mSelectedArray.valueAt(i)) {
                    arrayList.add(this.mMyEffectItemList.get(this.mSelectedArray.keyAt(i)));
                }
            }
            return arrayList;
        }

        public int getSelectedCount() {
            int i = 0;
            int size = this.mSelectedArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mSelectedArray.valueAt(i2)) {
                    i++;
                }
            }
            return i;
        }
    }

    public class a {
        final /* synthetic */ SelectDeleteEffectActivity a;
        private TextView b;
        private TextView c;
        private TextView d;
        private CheckBox e;

        public a(SelectDeleteEffectActivity selectDeleteEffectActivity, View view) {
            this.a = selectDeleteEffectActivity;
            this.b = (TextView) view.findViewById(R.id.textview_title);
            this.c = (TextView) view.findViewById(R.id.textview_author);
            this.d = (TextView) view.findViewById(R.id.textview_style);
            this.e = (CheckBox) view.findViewById(R.id.check_view);
        }

        public TextView a() {
            return this.b;
        }

        public TextView b() {
            return this.c;
        }

        public CheckBox c() {
            return this.e;
        }

        public TextView d() {
            return this.d;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getString(R.string.select_delete_effects));
        setContentView((int) R.layout.activity_select_delete_effect);
        initViews();
        initMyEffectFragment();
        initActionBar();
        initData();
    }

    public static void setEffectList(List<f> list) {
        sEffectList = list;
    }

    public void updateQueryPrivateEffect(List<f> list, List<MediaItem> list2) {
        if (!isFinishing()) {
            this.mList = list;
            this.mFragment.updateData(list);
            this.mSelectionAction.c(this.mList.size() > 0);
        }
    }

    private void initActionBar() {
        this.mSelectionAction = getActionBarController().d((int) R.drawable.img_checkbox_multiselect_unchecked);
        this.mSelectionAction.a(new b(this) {
            final /* synthetic */ SelectDeleteEffectActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                if (aVar.g() == null) {
                    aVar.a((Object) aVar);
                    aVar.d(R.drawable.img_checkbox_multiselect_checked);
                    this.a.mFragment.selectAll();
                    return;
                }
                aVar.a(null);
                aVar.d(R.drawable.img_checkbox_multiselect_unchecked);
                this.a.mFragment.selectNone();
            }
        });
        this.mSelectionAction.c(false);
        a.a(getActionBarController());
    }

    private void updateAction() {
        if (this.mFragment.getSelectedCount() == this.mList.size()) {
            this.mSelectionAction.a(this.mSelectionAction);
            this.mSelectionAction.d(R.drawable.img_checkbox_multiselect_checked);
            return;
        }
        this.mSelectionAction.a(null);
        this.mSelectionAction.d(R.drawable.img_checkbox_multiselect_unchecked);
    }

    private void initMyEffectFragment() {
        this.mFragment = new SelectDeleteEffectListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_myeffect, this.mFragment).commit();
    }

    private void initViews() {
        this.mDeleteView = findViewById(R.id.button_delete);
        this.mDeleteView.setOnClickListener(this.mOnClickListener);
    }

    private void performDeleteClick() {
        boolean z = true;
        Object selectItems = this.mFragment.getSelectItems();
        this.mList.removeAll(selectItems);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_PRIVATE_EFFECT_LIST, selectItems));
        this.mFragment.updateData(this.mList);
        com.sds.android.ttpod.component.a.a aVar = this.mSelectionAction;
        if (this.mList.size() <= 0) {
            z = false;
        }
        aVar.c(z);
        this.mIsSelected = false;
        com.sds.android.ttpod.framework.a.b.f.z();
        t.a("PAGE_CLICK", r.ACTION_EFFECT_DEL_SELECTED_OK, s.PAGE_NONE, s.PAGE_AUDIO_MY_CLOUD_EFFECT);
        if (!(selectItems == null || selectItems.isEmpty())) {
            com.sds.android.ttpod.component.d.f.a((int) R.string.string_del_audio);
        }
        finish();
    }

    private void initData() {
        updateQueryPrivateEffect(sEffectList, null);
    }

    protected void onDestroy() {
        sEffectList = null;
        super.onDestroy();
    }
}

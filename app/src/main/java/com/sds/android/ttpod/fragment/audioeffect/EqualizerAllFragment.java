package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.d.a.e;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualizerAllFragment extends BaseEqualizerFragment {
    private static final String TAG = "EqualizerAllFragment";
    private Map<String, Settings> mCustomEqualizerMap = new HashMap();
    private a mEqualizerAllAdapter;
    private List<String> mEqualizerAllList = new ArrayList();
    private boolean mIsSelected = false;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener(this) {
        final /* synthetic */ EqualizerAllFragment a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.performItemClick((String) this.a.mEqualizerAllList.get(i));
        }
    };
    private OnItemLongClickListener mOnItemLongClickListener = new OnItemLongClickListener(this) {
        final /* synthetic */ EqualizerAllFragment a;

        {
            this.a = r1;
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            a((String) this.a.mEqualizerAllList.get(i));
            return true;
        }

        private void a(final String str) {
            if (this.a.mCustomEqualizerMap.containsKey(str)) {
                final e eVar = new e(this.a.getActivity(), Arrays.asList(new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(0, (int) R.string.icon_equalizer_rename, (int) R.string.rename), new com.sds.android.ttpod.component.b.a(1, (int) R.string.icon_equalizer_remove, (int) R.string.remove), new com.sds.android.ttpod.component.b.a(2, (int) R.string.icon_equalizer_edit, (int) R.string.edit)}), (int) R.string.cancel, null);
                eVar.setTitle((CharSequence) str);
                eVar.a(new b(this) {
                    final /* synthetic */ AnonymousClass2 c;

                    public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                        switch (aVar.g()) {
                            case 0:
                                this.c.b(str);
                                break;
                            case 1:
                                this.c.c(str);
                                break;
                            case 2:
                                this.c.a.startCustomEqualizerActivity((Settings) this.c.a.mCustomEqualizerMap.get(str));
                                break;
                        }
                        eVar.dismiss();
                    }
                });
                eVar.show();
            }
        }

        private void b(final String str) {
            com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(this.a.getActivity(), new com.sds.android.ttpod.component.d.a.b.a[]{new com.sds.android.ttpod.component.d.a.b.a(1, "", str, this.a.getString(R.string.effect_custom_equalizer_input_name))}, R.string.save, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
                final /* synthetic */ AnonymousClass2 b;

                public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                    String charSequence = bVar.c(1).e().toString();
                    if (!m.a(charSequence)) {
                        Settings settings = (Settings) this.b.a.mCustomEqualizerMap.get(str);
                        this.b.d(str);
                        Settings settings2 = new Settings(charSequence, settings.getNumberOfBands(), settings.getBandLevels());
                        this.b.a(settings2);
                        if (str.equals(this.b.a.mEqualizerSettings.getName())) {
                            this.b.a.mEqualizerSettings = settings2;
                            this.b.a.setEqualizer(this.b.a.mEqualizerSettings);
                        }
                    }
                }
            }, null);
            bVar.setTitle((int) R.string.rename);
            bVar.show();
        }

        private void c(final String str) {
            h hVar = new h(this.a.getActivity(), this.a.getString(R.string.media_delete_single, str), new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ AnonymousClass2 b;

                public void a(h hVar) {
                    this.b.d(str);
                    if (str.equals(this.b.a.mEqualizerSettings.getName())) {
                        this.b.a.mEqualizerSettings = BaseEqualizerFragment.DEFAULT_SETTINGS;
                        this.b.a.setEqualizer(BaseEqualizerFragment.DEFAULT_SETTINGS);
                    }
                    this.b.a.mEqualizerAllAdapter.notifyDataSetChanged();
                }
            }, null);
            hVar.setTitle((int) R.string.remove);
            hVar.show();
        }

        private void d(String str) {
            this.a.mCustomEqualizerMap.remove(str);
            this.a.mEqualizerAllList.remove(str);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_CUSTOM_EQUALIZER, str));
        }

        private void a(Settings settings) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_CUSTOM_EQUALIZER, settings));
        }
    };
    private View mRootView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_equalizer_all, null);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_CUSTOM_EQUALIZER_LIST, new Object[0]));
            AudioEffectParam t = com.sds.android.ttpod.framework.support.e.a(getActivity()).t();
            initAllData(t != null ? t.g() : "");
            initListView();
        }
        return this.mRootView;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_CUSTOM_EQUALIZER_LIST, i.a(getClass(), "updateCustomEqualizerList", List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_CUSTOM_EQUALIZER, i.a(getClass(), "updateSaveCustomEqualizer", Settings.class));
    }

    public void updateCustomEqualizerList(List<Settings> list) {
        g.a(TAG, " updateCustomEqualizerList " + list);
        if (isAdded() && list != null) {
            int size = list.size();
            this.mEqualizerAllList.clear();
            this.mCustomEqualizerMap.clear();
            for (int i = 0; i < size; i++) {
                Settings settings = (Settings) list.get(i);
                this.mEqualizerAllList.add(settings.getName());
                this.mCustomEqualizerMap.put(settings.getName(), settings);
            }
            this.mEqualizerAllList.addAll(com.sds.android.ttpod.framework.modules.core.audioeffect.e.a());
            this.mEqualizerAllAdapter.notifyDataSetChanged();
        }
    }

    public void updateSaveCustomEqualizer(Settings settings) {
        if (isAdded()) {
            this.mEqualizerAllList.add(0, settings.getName());
            this.mCustomEqualizerMap.put(settings.getName(), settings);
            this.mEqualizerAllAdapter.notifyDataSetChanged();
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (!isViewAccessAble()) {
            this.mEqualizerAllAdapter = null;
            this.mEqualizerAllList.clear();
            this.mCustomEqualizerMap.clear();
            this.mRootView = null;
        }
    }

    private void initListView() {
        ListView listView = (ListView) this.mRootView.findViewById(R.id.listview_equalizer_all);
        listView.setAdapter(this.mEqualizerAllAdapter);
        listView.setOnItemClickListener(this.mOnItemClickListener);
        listView.setOnItemLongClickListener(this.mOnItemLongClickListener);
    }

    private void performItemClick(String str) {
        if (this.mCustomEqualizerMap.containsKey(str)) {
            this.mEqualizerSettings = (Settings) this.mCustomEqualizerMap.get(str);
        } else {
            this.mEqualizerSettings = getEqualizerSettingsByName(str);
        }
        setEqualizer(this.mEqualizerSettings);
        com.sds.android.ttpod.framework.storage.environment.b.E(true);
        this.mEqualizerAllAdapter.a(str);
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_EFFECT_EQULIZER_ALL_SELECTED.getValue(), 0, 0);
        sUserEvent.setPageParameter(true);
        sUserEvent.append(SocialConstants.PARAM_TYPE, str);
        sUserEvent.post();
        if (!this.mIsSelected) {
            this.mIsSelected = true;
            f.i();
        }
    }

    private void initAllData(String str) {
        if (this.mEqualizerAllList.size() == 0) {
            this.mEqualizerAllList.addAll(com.sds.android.ttpod.framework.modules.core.audioeffect.e.a());
        }
        this.mEqualizerAllAdapter = new a(getActivity(), this.mEqualizerAllList, str);
    }

    protected void updateListView(String str) {
        this.mEqualizerAllAdapter.a(str);
    }
}

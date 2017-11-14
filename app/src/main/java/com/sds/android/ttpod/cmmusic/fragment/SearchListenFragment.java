package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.ttpod.cmmusic.R;
import com.sds.android.ttpod.cmmusic.a.a;
import com.sds.android.ttpod.cmmusic.a.d;
import com.sds.android.ttpod.cmmusic.b.b;
import com.sds.android.ttpod.cmmusic.c.g;
import com.sds.android.ttpod.cmmusic.c.h;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchListenFragment extends BaseFragment implements OnClickListener {
    private a mAdapterbinding;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ SearchListenFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            ArrayList arrayList;
            switch (message.what) {
                case 1:
                    arrayList = (ArrayList) message.obj;
                    if (arrayList != null) {
                        this.a.mSearchKeyArray.addAll(arrayList);
                        this.a.mRecommendSearchKeyAdapter.notifyDataSetChanged();
                        arrayList.clear();
                        return;
                    }
                    return;
                case 2:
                    this.a.mSearchKeyArray.removeAll(this.a.mSearchKeyArray);
                    arrayList = (ArrayList) message.obj;
                    this.a.mSearchKeyArray.addAll(arrayList);
                    this.a.mRecommendSearchKeyAdapter.notifyDataSetChanged();
                    this.a.mListViewSearch.setAdapter(this.a.mRecommendSearchKeyAdapter);
                    this.a.mListViewSearch.setVisibility(0);
                    arrayList.clear();
                    return;
                case 3:
                    Toast.makeText(this.a.getActivity(), this.a.getResources().getString(R.string.searcherror), 0).show();
                    return;
                case 10:
                    if (this.a.mViewContentList != null) {
                        this.a.mViewContentList.addAll(this.a.mItemInfoListTemp);
                        this.a.mAdapterbinding.notifyDataSetChanged();
                        this.a.mIsNull = false;
                        this.a.mItemInfoListTemp.clear();
                        return;
                    }
                    return;
                case 11:
                    this.a.mViewContentList.addAll(this.a.mItemInfoListTemp);
                    this.a.mListView.setVisibility(0);
                    this.a.mAdapterbinding.notifyDataSetChanged();
                    this.a.mItemInfoListTemp.clear();
                    this.a.mIsNull = false;
                    return;
                default:
                    return;
            }
        }
    };
    private View mHeadViewRecommendList;
    private boolean mIsNull = false;
    private ArrayList<HashMap<String, String>> mItemInfoListTemp = new ArrayList();
    private ListView mListView;
    private ListView mListViewSearch;
    private d mRecommendSearchKeyAdapter;
    private String mSearchKey = null;
    private ArrayList<HashMap<String, String>> mSearchKeyArray = new ArrayList();
    private EditText mSearchName;
    private ArrayList<HashMap<String, String>> mViewContentList = new ArrayList();
    private Thread mWebDataThread;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cmmusic_search_listen_activity, viewGroup, false);
        recomedKey();
        listViewInit(inflate);
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    private void listViewInit(View view) {
        view.findViewById(R.id.btn_submit_searchpage_submit).setOnClickListener(this);
        this.mSearchName = (EditText) view.findViewById(R.id.edit_searchkey_searchpage);
        this.mListView = (ListView) view.findViewById(R.id.list_searchpage);
        this.mHeadViewRecommendList = LayoutInflater.from(getActivity()).inflate(R.layout.cmmusic_recommend_search_key_list_head, null);
        this.mListViewSearch = (ListView) view.findViewById(R.id.list_recommend_searchpage);
        this.mListViewSearch.addHeaderView(this.mHeadViewRecommendList, null, false);
        this.mRecommendSearchKeyAdapter = new d(this.mSearchKeyArray, getActivity());
        this.mListViewSearch.setAdapter(this.mRecommendSearchKeyAdapter);
        this.mListViewSearch.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SearchListenFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.mSearchName.setText((CharSequence) ((HashMap) this.a.mRecommendSearchKeyAdapter.getItem(i - 1)).get("recommendKey"));
            }
        });
        this.mSearchName.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ SearchListenFragment a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.mListViewSearch.setVisibility(0);
                this.a.mListView.setVisibility(8);
                return false;
            }
        });
        this.mSearchName.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ SearchListenFragment a;

            {
                this.a = r1;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.a.mListView.setVisibility(8);
                this.a.mSearchKeyArray.removeAll(this.a.mSearchKeyArray);
                com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        String replace = this.a.a.mSearchName.getText().toString().replace(" ", "");
                        if (replace != null) {
                            if (replace.equals("")) {
                                this.a.a.mSearchKeyArray.removeAll(this.a.a.mSearchKeyArray);
                                this.a.a.recomedKey();
                                return;
                            }
                            ArrayList a = h.a(replace);
                            if (a != null) {
                                Message message = new Message();
                                message.obj = a;
                                message.what = 2;
                                this.a.a.mHandler.sendMessage(message);
                            }
                        }
                    }
                });
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.a.mListView.setVisibility(8);
                this.a.mViewContentList.removeAll(this.a.mViewContentList);
            }

            public void afterTextChanged(Editable editable) {
                this.a.mSearchKeyArray.removeAll(this.a.mSearchKeyArray);
                this.a.mListView.setVisibility(8);
                this.a.mListViewSearch.setVisibility(8);
            }
        });
        this.mAdapterbinding = new a(getActivity(), this, this.mViewContentList, "SearchPage", s.PAGE_CMMUSIC_SEARCH_CODE.getValue());
        this.mListView.setAdapter(this.mAdapterbinding);
        this.mListView.setCacheColorHint(0);
        this.mListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ SearchListenFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.a.mIsNull || i != 0 || absListView.getLastVisiblePosition() != absListView.getCount() - 1) {
                    return;
                }
                if (this.a.mWebDataThread == null || this.a.mWebDataThread.isAlive()) {
                    this.a.mWebDataThread = new Thread(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.a.mListView.getCount() > 0) {
                                this.a.a.mIsNull = true;
                                this.a.a.onScrollAddData(this.a.a.mSearchKey, Integer.valueOf(this.a.a.mListView.getCount()));
                            }
                            Message message = new Message();
                            message.what = 1;
                            this.a.a.mHandler.sendMessage(message);
                        }
                    };
                    this.a.mWebDataThread.start();
                    this.a.mWebDataThread = null;
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        });
        this.mListViewSearch.setVisibility(0);
        this.mListView.setVisibility(8);
    }

    private void searchSubmit() {
        this.mListViewSearch.setVisibility(8);
        this.mViewContentList.clear();
        this.mAdapterbinding.notifyDataSetChanged();
        try {
            com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
                final /* synthetic */ SearchListenFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.mSearchName.getText().toString().replace(" ", "").equals("")) {
                        Message message = new Message();
                        message.what = 3;
                        this.a.mHandler.sendMessage(message);
                        return;
                    }
                    this.a.mSearchKey = this.a.mSearchName.getText().toString().replace(" ", "");
                    List<b> a = g.a(this.a.mSearchKey, c.a(), c.b());
                    if (a != null && a.size() > 0) {
                        for (b bVar : a) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("resource_name", bVar.a());
                            hashMap.put("resource_songer", bVar.b());
                            hashMap.put("music_id", bVar.f());
                            hashMap.put("cailing_id", bVar.d());
                            hashMap.put("zhenling_id", bVar.e());
                            hashMap.put("time_out", bVar.g());
                            this.a.mItemInfoListTemp.add(hashMap);
                        }
                        a.clear();
                        message = new Message();
                        message.what = 11;
                        this.a.mHandler.sendMessage(message);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onScrollAddData(String str, Integer num) {
        try {
            List<b> a = g.a(str, c.a(), c.b(), String.valueOf(num));
            if (a != null && a.size() > 0) {
                for (b bVar : a) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("resource_name", bVar.a());
                    hashMap.put("resource_songer", bVar.b());
                    hashMap.put("music_id", bVar.f());
                    hashMap.put("cailing_id", bVar.d());
                    hashMap.put("zhenling_id", bVar.e());
                    hashMap.put("time_out", bVar.g());
                    this.mItemInfoListTemp.add(hashMap);
                }
                Message message = new Message();
                message.what = 10;
                this.mHandler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recomedKey() {
        com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
            final /* synthetic */ SearchListenFragment a;

            {
                this.a = r1;
            }

            public void run() {
                ArrayList a = h.a();
                if (a != null) {
                    Message message = new Message();
                    message.obj = a;
                    message.what = 1;
                    this.a.mHandler.sendMessage(message);
                }
            }
        });
    }

    public void onClick(View view) {
        if (R.id.btn_submit_searchpage_submit == view.getId()) {
            try {
                searchSubmit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

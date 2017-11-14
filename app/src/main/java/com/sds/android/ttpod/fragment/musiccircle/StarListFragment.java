package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.LabeledTTPodUser;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.LabeledTTPodUserListResult;
import com.sds.android.ttpod.adapter.d.h;
import com.sds.android.ttpod.adapter.d.i;
import com.sds.android.ttpod.widget.StateView.b;
import java.util.List;

public abstract class StarListFragment extends UserListFragment<LabeledTTPodUser> implements com.sds.android.ttpod.adapter.d.i.a {
    private int mCategoryID;
    private a mStarAdapter;
    private String mTitle;

    class a extends i<LabeledTTPodUser> {
        final /* synthetic */ StarListFragment a;

        public a(StarListFragment starListFragment, Context context, List<LabeledTTPodUser> list) {
            this.a = starListFragment;
            super(context, list);
        }

        protected void a(h hVar, LabeledTTPodUser labeledTTPodUser) {
            super.a(hVar, (TTPodUser) labeledTTPodUser);
            hVar.d().setText(labeledTTPodUser.getLabel());
        }
    }

    public abstract void onRequestData(int i, int i2, int i3);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mCategoryID = arguments.getInt("category_id", 0);
            this.mTitle = arguments.getString("title");
            if (this.mTitle == null) {
                this.mTitle = "";
            }
        }
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (isLoadFinished()) {
            setLoadingState(getDataCount() <= 0 ? b.FAILED : b.SUCCESS);
        }
    }

    protected void onRequestDataFinished(LabeledTTPodUserListResult labeledTTPodUserListResult) {
        setPageTotal(labeledTTPodUserListResult.getExtra().b());
        addData(labeledTTPodUserListResult.getDataList(), true);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        setLoadingState(getDataCount() <= 0 ? b.LOADING : b.SUCCESS);
        setPageStart(1);
        setPageCurrent(1);
        reload();
    }

    protected i<LabeledTTPodUser> onCreateAdapter(List<LabeledTTPodUser> list) {
        this.mStarAdapter = new a(this, getActivity(), list);
        this.mStarAdapter.a((com.sds.android.ttpod.adapter.d.i.a) this);
        return this.mStarAdapter;
    }

    protected void onRequestData(int i, int i2) {
        onRequestData(this.mCategoryID, i, i2);
    }

    public void onFollow(long j) {
    }

    public void onUnFollow(long j) {
    }
}

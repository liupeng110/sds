package com.sds.android.ttpod.activities.user.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.MainActivity;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.user.utils.c;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.d.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.widget.RoundedImageView;
import com.tencent.open.SocialConstants;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTHitBuilders.UTCustomHitBuilder;
import java.lang.reflect.Method;
import java.util.Map;

public class RegisterCompleteProfileActivity extends SlidingClosableActivity {
    private static final int MODIFY_AVATAR_CALLBACK = 1;
    private static final int MODIFY_NICKNAME_CALLBACK = 2;
    private RoundedImageView mAvatarImageView;
    private boolean mIsAvatarDirty;
    private boolean mIsBackPressed;
    private boolean mIsUserDefaultNickname = true;
    private String mLocalAvatarImagePath;
    private EditText mNickNameView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ RegisterCompleteProfileActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.finish:
                    this.a.onClickFinish();
                    return;
                case R.id.avatar:
                    this.a.selectAvatar();
                    return;
                default:
                    return;
            }
        }
    };
    private c mPickImageHelper;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();
        setStatisticPage(s.PAGE_PHONE_REGISTER_COMPLETE_PROFILE);
    }

    private void initView() {
        this.mLocalAvatarImagePath = buildLocalAvatarPath();
        setTitle((int) R.string.complete_profile);
        setContentView((int) R.layout.activity_user_register_complete_profile);
        this.mNickNameView = (EditText) findViewById(R.id.nickName);
        this.mPickImageHelper = new c(this);
        this.mAvatarImageView = (RoundedImageView) findViewById(R.id.avatar);
        this.mAvatarImageView.setOnClickListener(this.mOnClickListener);
        findViewById(R.id.finish).setOnClickListener(this.mOnClickListener);
        getActionBarController().d();
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.MODIFY_NICKNAME_FINISHED, i.a(getClass(), "modifyNickNameFinished", d.class));
        map.put(a.MODIFY_AVATAR_FINISHED, i.a(getClass(), "modifyAvatarFinished", d.class));
    }

    public void modifyNickNameFinished(d dVar) {
        this.mIsUserDefaultNickname = false;
        handleModifyCallback(dVar, 2);
    }

    public void modifyAvatarFinished(d dVar) {
        handleModifyCallback(dVar, 1);
        doModifyAvatarStatistic(dVar);
    }

    private void doModifyAvatarStatistic(d dVar) {
        int i = 0;
        boolean z = dVar.a() == e.ErrNone;
        Object obj = this.mPickImageHelper.a() == 1 ? SocialConstants.PARAM_AVATAR_URI : "camera";
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_PHONE_REGISTER_PROFILE_MODIFY_AVATAR.getValue(), s.PAGE_NONE.getValue());
        String str = Downloads.COLUMN_STATUS;
        if (z) {
            i = 1;
        }
        sUserEvent.append(str, Integer.valueOf(i));
        sUserEvent.append("way", obj);
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void handleModifyCallback(d dVar, int i) {
        if (dVar.a() == e.ErrNone) {
            if (i == 1) {
                this.mIsAvatarDirty = false;
            }
            finishIfPossible();
            return;
        }
        f.a();
        if (i == 1) {
            f.a(getString(R.string.modify_avatar_failure, new Object[]{dVar.b()}));
            this.mIsAvatarDirty = true;
            return;
        }
        f.a(getString(R.string.modify_nickname_failure, new Object[]{dVar.b()}));
    }

    private void finishIfPossible() {
        if (isOkToFinish()) {
            f.a();
            gotoMainActivity();
        }
    }

    private boolean isOkToFinish() {
        return this.mIsBackPressed || !(isAvatarDirty() || isNickNameDirty());
    }

    private void gotoMainActivity() {
        UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("click");
        uTCustomHitBuilder.setEventPage(l.a().b());
        uTCustomHitBuilder.setProperty("ctrl_name", "confirm");
        uTCustomHitBuilder.setProperty(SocialConstants.PARAM_TYPE, this.mIsUserDefaultNickname ? FeedbackItem.STATUS_WAITING : "1");
        UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(268435456);
        intent.setFlags(67108864);
        startActivity(intent);
    }

    private boolean isAvatarDirty() {
        return this.mIsAvatarDirty;
    }

    private boolean isNickNameDirty() {
        return (m.a(getNickName()) || m.a(getNickName(), b.at().getNickName())) ? false : true;
    }

    private String buildLocalAvatarPath() {
        return g.a(b.at().getAvatarUrl());
    }

    private String getNickName() {
        return this.mNickNameView.getText().toString().trim();
    }

    private void selectAvatar() {
        this.mPickImageHelper.a(2, getString(R.string.userinfo_change_avatar_image), (int) getResources().getDimension(R.dimen.register_avatar_width), (int) getResources().getDimension(R.dimen.register_avatar_height));
        t.a(r.ACTION_PHONE_REGISTER_PROFILE_CLICK_AVATAR, s.PAGE_NONE);
    }

    private void showEmptyNickNameDialog(String str) {
        h hVar = new h((Context) this, str, null, null);
        hVar.setTitle((int) R.string.empty_nickname);
        hVar.a((int) R.string.modify, new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ RegisterCompleteProfileActivity a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.mIsBackPressed = false;
                t.a(r.ACTION_PHONE_REGISTER_PROFILE_MODIFY_DEFAULT_NICKNAME, s.PAGE_NONE);
            }
        }, (int) R.string.just_use_it, new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ RegisterCompleteProfileActivity a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.mIsUserDefaultNickname = true;
                if (this.a.isOkToFinish()) {
                    this.a.gotoMainActivity();
                } else {
                    this.a.modifyAvatar();
                }
                t.a(r.ACTION_PHONE_REGISTER_PROFILE_USE_DEFAULT_NICKNAME, s.PAGE_NONE);
            }
        });
        hVar.show();
    }

    private void onClickFinish() {
        if (m.a(getNickName())) {
            showEmptyNickNameDialog(getString(R.string.default_nickname, new Object[]{b.at().getNickName()}));
        } else if (!com.sds.android.ttpod.activities.user.utils.f.a(getNickName(), R.string.nickname_hint_text, R.string.nick_name_restriction, null, 0, com.sds.android.ttpod.activities.user.utils.f.e)) {
        } else {
            if (com.sds.android.ttpod.framework.a.t.a((Context) this).a(getNickName())) {
                f.a((int) R.string.contains_sensitive_words);
                return;
            }
            modifyNickName();
            modifyAvatar();
            t.a(r.ACTION_PHONE_REGISTER_PROFILE_FINISH, s.PAGE_NONE);
        }
    }

    private void modifyNickName() {
        if (isNickNameDirty()) {
            f.a((Context) this, (int) R.string.login_wait_message);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.MODIFY_NICKNAME, getNickName()));
        }
    }

    private void modifyAvatar() {
        if (isAvatarDirty()) {
            f.a((Context) this, (int) R.string.login_wait_message);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.MODIFY_AVATAR, this.mLocalAvatarImagePath, Integer.valueOf((int) getResources().getDimension(R.dimen.register_avatar_width)), Integer.valueOf((int) getResources().getDimension(R.dimen.register_avatar_height))));
        }
    }

    public void onBackPressed() {
        this.mIsBackPressed = true;
        showEmptyNickNameDialog(getString(R.string.default_nickname, new Object[]{b.at().getNickName()}));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 2:
                    cropPhoto(intent);
                    return;
                case 3:
                    setAvatar();
                    return;
                default:
                    return;
            }
        }
    }

    private void cropPhoto(Intent intent) {
        this.mPickImageHelper.a(intent == null ? null : intent.getData(), this.mLocalAvatarImagePath);
    }

    private void setAvatar() {
        this.mAvatarImageView.setImageBitmap(g.a(this.mLocalAvatarImagePath, (int) getResources().getDimension(R.dimen.register_avatar_width), (int) getResources().getDimension(R.dimen.register_avatar_height), false));
        this.mIsAvatarDirty = true;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            this.mPickImageHelper.a(bundle);
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null) {
            this.mPickImageHelper.b(bundle);
        }
    }
}

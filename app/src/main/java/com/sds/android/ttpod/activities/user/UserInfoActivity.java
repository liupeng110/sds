package com.sds.android.ttpod.activities.user;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.user.utils.c;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Map;

public class UserInfoActivity extends SlidingClosableActivity implements OnClickListener {
    private static final String AREA_SEPARATOR = " ";
    private static final String DEFAULT_AVATAR_URL = "http://3p.pic.ttdtweb.com/ttus.ttpod.com/avatar.png";
    private static final String DEFAULT_COVER_URL = "http://3p.pic.ttdtweb.com/ttus.ttpod.com/cover.png";
    private static final int REQUEST_IMAGE_AVATAR = 2;
    private static final int REQUEST_IMAGE_COVER = 1;
    private static final String TAG = UserInfoActivity.class.getSimpleName();
    private Button mButtonLogout;
    private int mCachedRequestCode;
    private ImageView mImageAvatar;
    private ImageView mImageCover;
    private String mLocalAvatarImagePath;
    private String mLocalCoverImagePath;
    private c mPickImageHelper;
    private TextView mTextArea;
    private TextView mTextBirthday;
    private TextView mTextGender;
    private TextView mTextNickName;
    private TextView mTextSignature;
    private View mViewLine;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_avatar:
                t.a(r.ACTION_MODIFY_AVATAR, s.PAGE_NONE);
                onClickAvatar();
                return;
            case R.id.layout_cover:
                onClickCover();
                t.a(r.ACTION_MODIFY_COVER, s.PAGE_NONE);
                return;
            case R.id.layout_nickname:
                onClickNickName();
                t.a(r.ACTION_MODIFY_NICKNAME, s.PAGE_NONE);
                return;
            case R.id.layout_signature:
                onClickSignature();
                t.a(r.ACTION_MODIFY_SIGNATURE, s.PAGE_NONE);
                return;
            case R.id.layout_gender:
                onClickGender();
                return;
            case R.id.layout_birthday:
                onClickBirthday();
                t.a(r.ACTION_MODIFY_BIRTHDAY, s.PAGE_NONE);
                return;
            case R.id.layout_area:
                onClickArea();
                t.a(r.ACTION_MODIFY_AREA, s.PAGE_NONE);
                return;
            case R.id.layout_password:
                onClickPassword();
                return;
            case R.id.button_userinfo_logout:
                logout();
                t.a(r.ACTION_LOGOUT, s.PAGE_NONE);
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.userinfo_my_profile);
        setContentView((int) R.layout.activity_userinfo);
        getActionBarController().d();
        findViewById(R.id.layout_avatar).setOnClickListener(this);
        findViewById(R.id.layout_nickname).setOnClickListener(this);
        findViewById(R.id.layout_birthday).setOnClickListener(this);
        findViewById(R.id.layout_gender).setOnClickListener(this);
        findViewById(R.id.layout_area).setOnClickListener(this);
        findViewById(R.id.layout_cover).setOnClickListener(this);
        findViewById(R.id.layout_signature).setOnClickListener(this);
        findViewById(R.id.layout_password).setOnClickListener(this);
        findViewById(R.id.button_userinfo_logout).setOnClickListener(this);
        this.mButtonLogout = (Button) findViewById(R.id.button_userinfo_logout);
        this.mImageCover = (ImageView) findViewById(R.id.imageview_background);
        this.mImageAvatar = (ImageView) findViewById(R.id.imageview_userinfo_avatar);
        this.mTextNickName = (TextView) findViewById(R.id.textview_nickname);
        this.mTextArea = (TextView) findViewById(R.id.textview_area);
        this.mTextBirthday = (TextView) findViewById(R.id.textview_birthday);
        this.mTextGender = (TextView) findViewById(R.id.textview_gender);
        this.mTextSignature = (TextView) findViewById(R.id.textview_signature);
        this.mViewLine = findViewById(R.id.line_area);
        this.mPickImageHelper = new c(this);
        onNewIntent(getIntent());
        bindData(b.at());
        if (!NewUser.LOCAL_LOGIN.equals(b.at().getVia())) {
            setPasswordViewVisible(false);
        }
        setTBSPage("tt_music_circle_myinfo");
        setStatisticPage(s.PAGE_USER_INFO);
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.REFRESH_INFORMATION, new Object[0]));
    }

    private void setPasswordViewVisible(boolean z) {
        if (z) {
            findViewById(R.id.title_account).setVisibility(0);
            findViewById(R.id.layout_password).setVisibility(0);
            this.mViewLine.setVisibility(0);
            return;
        }
        findViewById(R.id.title_account).setVisibility(8);
        findViewById(R.id.layout_password).setVisibility(8);
        this.mViewLine.setVisibility(8);
    }

    private void bindData(NewUser newUser) {
        if (b.av()) {
            this.mLocalAvatarImagePath = buildLocalAvatarPath(newUser);
            g.a(TAG, "mLocalAvatarImagePath=" + this.mLocalAvatarImagePath);
            this.mLocalCoverImagePath = buildLocalCoverPath(newUser);
            g.a(TAG, "mLocalCoverImagePath=" + this.mLocalCoverImagePath);
            bindAvatar(newUser.getAvatarUrl());
            bindCover(newUser.getCoverPic());
            this.mTextSignature.setText(newUser.getSignature());
            bindBirthday(newUser.getBirthday());
            this.mTextArea.setText(assemblyArea(newUser.getCity(), newUser.getRegion()));
            this.mTextGender.setText(getSexString(newUser.getSex()));
            this.mTextNickName.setText(newUser.getNickName());
            this.mTextSignature.setText(newUser.getSignature());
            return;
        }
        finish();
    }

    private String assemblyArea(String str, String str2) {
        if (m.a(str) || m.a(str2)) {
            return "";
        }
        return str + AREA_SEPARATOR + str2;
    }

    private String getSexString(int i) {
        switch (i) {
            case 0:
                return getString(R.string.female);
            case 1:
                return getString(R.string.male);
            default:
                return getString(R.string.confidential);
        }
    }

    private void bindBirthday(long j) {
        this.mTextBirthday.setVisibility(0);
        this.mTextBirthday.setText(com.sds.android.sdk.lib.util.c.a(1000 * j, 0, "-"));
    }

    private void popupModifyEditDialog(String str, String str2, com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b> aVar) {
        com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(this, new com.sds.android.ttpod.component.d.a.b.a[]{new com.sds.android.ttpod.component.d.a.b.a(0, "", str2, getString(R.string.please_input) + str)}, R.string.save, aVar, null);
        bVar.setTitle(getString(R.string.modify) + str);
        bVar.show();
    }

    private String buildLocalCoverPath(NewUser newUser) {
        String coverPic = newUser.getCoverPic();
        if (m.a(coverPic)) {
            coverPic = DEFAULT_COVER_URL;
        }
        return com.sds.android.ttpod.framework.a.g.a(coverPic);
    }

    private String buildLocalAvatarPath(NewUser newUser) {
        String avatarUrl = newUser.getAvatarUrl();
        if (m.a(avatarUrl)) {
            avatarUrl = DEFAULT_AVATAR_URL;
        }
        return com.sds.android.ttpod.framework.a.g.a(avatarUrl);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("logout_visible", true);
            this.mButtonLogout.setVisibility(booleanExtra ? 0 : 8);
            setPasswordViewVisible(booleanExtra);
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_NICKNAME_FINISHED, i.a(cls, "modifyNickNameFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_BIRTHDAY_FINISHED, i.a(cls, "modifyBirthdayFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_PASSWORD_FINISHED, i.a(cls, "modifyPasswordFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_SIGNATURE_FINISHED, i.a(cls, "modifySignatureFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_COVER_FINISHED, i.a(cls, "modifyCoverFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_AVATAR_FINISHED, i.a(cls, "modifyAvatarFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_SEX_FINISHED, i.a(cls, "modifySexFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_AREA_FINISHED, i.a(cls, "modifyAddressFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.REFRESH_INFORMATION_FINISHED, i.a(cls, "refreshInformationFinished", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.AUTHORIZED_INVALID, i.a(getClass(), "handleAuthorizeInvalid", Integer.class));
    }

    public void handleAuthorizeInvalid(Integer num) {
        finish();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 1:
                case 2:
                    cropPhoto(i, intent);
                    this.mCachedRequestCode = i;
                    return;
                case 3:
                    if (this.mCachedRequestCode == 1) {
                        this.mImageCover.setImageBitmap(com.sds.android.ttpod.framework.a.g.a(this.mLocalCoverImagePath, (int) getResources().getDimension(R.dimen.cover_width), (int) getResources().getDimension(R.dimen.cover_width), false));
                        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_COVER, this.mLocalCoverImagePath, Integer.valueOf(com.sds.android.ttpod.common.c.a.d()), Integer.valueOf((int) getResources().getDimension(R.dimen.cover_height))));
                        return;
                    } else if (this.mCachedRequestCode == 2) {
                        this.mImageAvatar.setImageBitmap(com.sds.android.ttpod.framework.a.g.a(this.mLocalAvatarImagePath, (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height), false));
                        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_AVATAR, this.mLocalAvatarImagePath, Integer.valueOf((int) getResources().getDimension(R.dimen.avatar_width)), Integer.valueOf((int) getResources().getDimension(R.dimen.avatar_height))));
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void cropPhoto(int i, Intent intent) {
        Uri uri = null;
        c cVar;
        if (i == 1) {
            cVar = this.mPickImageHelper;
            if (intent != null) {
                uri = intent.getData();
            }
            cVar.a(uri, this.mLocalCoverImagePath);
        } else if (i == 2) {
            cVar = this.mPickImageHelper;
            if (intent != null) {
                uri = intent.getData();
            }
            cVar.a(uri, this.mLocalAvatarImagePath);
        }
    }

    public void modifyBirthdayFinished(d dVar) {
        if (!handleModifyFail(dVar.a(), dVar.b())) {
            bindBirthday(b.at().getBirthday());
        }
    }

    private boolean handleModifyFail(e eVar, String str) {
        if (status() == 2) {
            f.a();
        }
        f.a(str);
        if (eVar == e.ErrNoLogin) {
            finish();
            return true;
        } else if (e.ErrNone == eVar) {
            return false;
        } else {
            return true;
        }
    }

    public void modifyNickNameFinished(d dVar) {
        if (!handleModifyFail(dVar.a(), dVar.b())) {
            this.mTextNickName.setText(b.at().getNickName());
        }
    }

    public void modifySignatureFinished(d dVar) {
        if (!handleModifyFail(dVar.a(), dVar.b())) {
            this.mTextSignature.setText(b.at().getSignature());
        }
    }

    public void modifyPasswordFinished(d dVar) {
        int i = 0;
        boolean handleModifyFail = handleModifyFail(dVar.a(), dVar.b());
        if (!handleModifyFail) {
            logout();
            com.sds.android.ttpod.b.f.a("", false);
        }
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_MODIFY_PASSWORD.getValue(), s.PAGE_NONE.getValue());
        String str = Downloads.COLUMN_STATUS;
        if (!handleModifyFail) {
            i = 1;
        }
        sUserEvent.append(str, Integer.valueOf(i));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    public void modifyCoverFinished(d dVar) {
        coverModifyEvent(dVar);
        if (!handleModifyFail(dVar.a(), dVar.b())) {
            bindCover(b.at().getCoverPic());
        }
    }

    public void modifySexFinished(d dVar) {
        if (!handleModifyFail(dVar.a(), dVar.b())) {
            this.mTextGender.setText(getSexString(b.at().getSex()));
        }
    }

    public void modifyAddressFinished(d dVar) {
        if (!handleModifyFail(dVar.a(), dVar.b())) {
            this.mTextArea.setText(assemblyArea(b.at().getCity(), b.at().getRegion()));
        }
    }

    public void modifyAvatarFinished(d dVar) {
        avatarModifyEvent(dVar);
        if (!handleModifyFail(dVar.a(), dVar.b())) {
            bindAvatar(b.at().getAvatarUrl());
        }
    }

    private void avatarModifyEvent(d dVar) {
        boolean z;
        int i = 0;
        Object obj = this.mPickImageHelper.a() == 1 ? SocialConstants.PARAM_AVATAR_URI : "camera";
        if (dVar.a() == e.ErrNone) {
            z = true;
        } else {
            z = false;
        }
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_MODIFY_AVATAR_FINISH.getValue(), s.PAGE_NONE.getValue());
        String str = Downloads.COLUMN_STATUS;
        if (z) {
            i = 1;
        }
        sUserEvent.append(str, Integer.valueOf(i));
        sUserEvent.append("way", obj);
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void coverModifyEvent(d dVar) {
        boolean z;
        int i = 0;
        Object obj = this.mPickImageHelper.a() == 1 ? SocialConstants.PARAM_AVATAR_URI : "camera";
        if (dVar.a() == e.ErrNone) {
            z = true;
        } else {
            z = false;
        }
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_MODIFY_COVER_FINISH.getValue(), s.PAGE_NONE.getValue());
        String str = Downloads.COLUMN_STATUS;
        if (z) {
            i = 1;
        }
        sUserEvent.append(str, Integer.valueOf(i));
        sUserEvent.append("way", obj);
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    public void refreshInformationFinished() {
        if (b.av()) {
            bindData(b.at());
        } else {
            finish();
        }
    }

    private void bindAvatar(String str) {
        if (!m.a(str)) {
            com.sds.android.ttpod.framework.a.g.a(this.mImageAvatar, str, (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height));
        }
    }

    private void bindCover(String str) {
        if (!m.a(str)) {
            com.sds.android.ttpod.framework.a.g.a(this.mImageCover, str, (int) getResources().getDimension(R.dimen.cover_width), (int) getResources().getDimension(R.dimen.cover_width));
        }
    }

    private void onClickAvatar() {
        this.mPickImageHelper.a(2, getString(R.string.userinfo_change_avatar_image), (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height));
        com.sds.android.ttpod.activities.musiccircle.c.c();
    }

    private void onClickCover() {
        int width = getWindow().getDecorView().getWidth();
        this.mPickImageHelper.a(1, getString(R.string.userinfo_change_background), width, width);
        com.sds.android.ttpod.activities.musiccircle.c.b();
    }

    private void logout() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.LOGOUT, new Object[0]));
        finish();
    }

    private void onClickSignature() {
        popupModifyEditDialog(getString(R.string.signature), b.at().getSignature(), new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
            final /* synthetic */ UserInfoActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                String charSequence = bVar.c(0).e().toString();
                String signature = b.at().getSignature();
                if (!m.a(charSequence) && !charSequence.equals(signature)) {
                    f.a(this.a, this.a.getString(R.string.loading));
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_SIGNATURE, charSequence));
                }
            }
        });
    }

    private void onClickPassword() {
        if (NewUser.LOCAL_LOGIN.equals(b.at().getVia())) {
            com.sds.android.ttpod.component.d.a.b.a aVar = new com.sds.android.ttpod.component.d.a.b.a(0, "", "", getString(R.string.userinfo_input_current_password));
            com.sds.android.ttpod.component.d.a.b.a aVar2 = new com.sds.android.ttpod.component.d.a.b.a(1, "", "", getString(R.string.userinfo_input_new_password));
            com.sds.android.ttpod.component.d.a.b.a aVar3 = new com.sds.android.ttpod.component.d.a.b.a(2, "", "", getString(R.string.userinfo_input_new_password_comfirm));
            aVar.a(true);
            aVar2.a(true);
            aVar3.a(true);
            aVar.a(-4934476);
            aVar2.a(-4934476);
            aVar3.a(-4934476);
            com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(this, new com.sds.android.ttpod.component.d.a.b.a[]{aVar, aVar2, aVar3}, R.string.save, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
                final /* synthetic */ UserInfoActivity a;

                {
                    this.a = r1;
                }

                public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                    String charSequence = bVar.c(0).e().toString();
                    String charSequence2 = bVar.c(1).e().toString();
                    String charSequence3 = bVar.c(2).e().toString();
                    if (com.sds.android.ttpod.activities.user.utils.f.a(charSequence, R.string.password_hint_text, R.string.password_length, null, 0, com.sds.android.ttpod.activities.user.utils.f.f)) {
                        if (!com.sds.android.ttpod.activities.user.utils.f.a(charSequence2, R.string.password_hint_text, R.string.password_length, null, 0, com.sds.android.ttpod.activities.user.utils.f.f)) {
                            f.a((int) R.string.invalid_password);
                            return;
                        } else if (!charSequence2.equals(charSequence3)) {
                            f.a((int) R.string.password_not_match);
                            return;
                        } else if (!charSequence.equals(charSequence2)) {
                            f.a(this.a, this.a.getString(R.string.loading));
                            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_PASSWORD, charSequence, charSequence2));
                            return;
                        } else {
                            return;
                        }
                    }
                    f.a((int) R.string.invalid_password);
                }
            }, null);
            bVar.setTitle((int) R.string.userinfo_change_password_title);
            bVar.show();
        }
    }

    private void onClickBirthday() {
        com.sds.android.ttpod.activities.user.utils.a aVar;
        com.sds.android.ttpod.activities.user.utils.a aVar2 = new com.sds.android.ttpod.activities.user.utils.a(b.at().getBirthday());
        if (validBirthdayDate(aVar2)) {
            aVar = aVar2;
        } else {
            Calendar instance = Calendar.getInstance();
            int i = instance.get(2);
            int i2 = instance.get(1);
            if (i >= 12) {
                i = 11;
            }
            aVar = new com.sds.android.ttpod.activities.user.utils.a(i2, i, instance.get(5));
        }
        Dialog anonymousClass4 = new DatePickerDialog(this, this, R.style.DatePickerStyle, new OnDateSetListener(this) {
            final /* synthetic */ UserInfoActivity a;

            {
                this.a = r1;
            }

            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                if (!new com.sds.android.ttpod.activities.user.utils.a(b.at().getBirthday()).equals(new com.sds.android.ttpod.activities.user.utils.a(i, i2, i3))) {
                    f.a(this.a, this.a.getString(R.string.loading));
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_BIRTHDAY, Long.valueOf(r0.d())));
                }
            }
        }, aVar.a(), aVar.b(), aVar.c()) {
            final /* synthetic */ UserInfoActivity a;

            protected void onStop() {
            }
        };
        anonymousClass4.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ UserInfoActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });
        anonymousClass4.show();
        com.sds.android.ttpod.activities.musiccircle.c.e();
    }

    private boolean validBirthdayDate(com.sds.android.ttpod.activities.user.utils.a aVar) {
        return aVar != null && aVar.a() > 1900 && aVar.a() < 2100 && aVar.b() >= 0 && aVar.b() < 12 && aVar.c() > 0 && aVar.c() <= 31;
    }

    private void onClickArea() {
        com.sds.android.ttpod.activities.user.a.c cVar = new com.sds.android.ttpod.activities.user.a.c(this, R.string.save, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.activities.user.a.c>(this) {
            final /* synthetic */ UserInfoActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.activities.user.a.c cVar) {
                String access$000 = this.a.assemblyArea(cVar.c(), cVar.d());
                if (!m.a(access$000) && !access$000.equals(this.a.mTextArea.getText())) {
                    f.a(this.a, this.a.getString(R.string.loading));
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_AREA, r0, r1));
                }
            }
        }, R.string.cancel, null);
        cVar.a(b.at().getCity());
        cVar.b(b.at().getRegion());
        cVar.setTitle((CharSequence) "选择城市");
        cVar.show();
    }

    private void onClickGender() {
        int i = 2;
        com.sds.android.ttpod.component.b.d[] dVarArr = new com.sds.android.ttpod.component.b.d[]{new com.sds.android.ttpod.component.b.d(0, R.string.female), new com.sds.android.ttpod.component.b.d(1, R.string.male), new com.sds.android.ttpod.component.b.d(2, R.string.confidential)};
        int sex = b.at().getSex();
        if (sex >= 0) {
            i = sex;
        }
        f.a((Context) this, getString(R.string.please_select), dVarArr, i, new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ UserInfoActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_MODIFY_GENDER.getValue(), s.PAGE_NONE.getValue());
                sUserEvent.append("gender", Integer.valueOf(i));
                sUserEvent.setPageParameter(true);
                sUserEvent.post();
                f.a(this.a, this.a.getString(R.string.loading));
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_SEX, new Integer(i)));
            }
        }, null);
    }

    private void onClickNickName() {
        popupModifyEditDialog(getString(R.string.nickname), b.at().getNickName(), new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
            final /* synthetic */ UserInfoActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                String charSequence = bVar.c(0).e().toString();
                if (!charSequence.equals(b.at().getNickName())) {
                    if (com.sds.android.ttpod.b.r.a(this.a).a(charSequence)) {
                        f.a((int) R.string.contains_sensitive_words);
                    } else if (com.sds.android.ttpod.activities.user.utils.f.a(charSequence, R.string.nickname_hint_text, R.string.nick_name_restriction, null, 0, com.sds.android.ttpod.activities.user.utils.f.e)) {
                        f.a(this.a, this.a.getString(R.string.loading));
                        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_NICKNAME, charSequence));
                    }
                }
            }
        });
        com.sds.android.ttpod.activities.musiccircle.c.d();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putInt("request_code", this.mCachedRequestCode);
            this.mPickImageHelper.a(bundle);
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null) {
            this.mCachedRequestCode = bundle.getInt("request_code", this.mCachedRequestCode);
            this.mPickImageHelper.b(bundle);
        }
    }
}

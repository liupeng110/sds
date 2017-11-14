package com.sds.android.ttpod.framework.modules.b;

import android.content.res.Resources;
import com.sds.android.cloudapi.ttpod.data.TagData;
import com.sds.android.cloudapi.ttpod.result.SingerDetailResult.SingerDetailData;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.util.ArrayList;
import java.util.List;

/* SingerInfo */
public class e extends BaseResult {
    private List<f> a;
    private String b;
    private String c;

    /* SingerInfo */
    enum a {
        CHINESE_NAME(R.string.chinese_name),
        FOREIGN_NAME(R.string.foreign_name),
        BIRTHDAY(R.string.birthday),
        COUNTRY(R.string.country),
        NATION(R.string.nation),
        BIRTH_PLACE(R.string.birth_place),
        HEIGHT(R.string.height),
        WEIGHT(R.string.weight),
        BLOOD_TYPE(R.string.blood_type),
        SCHOOL(R.string.school),
        COMPANY(R.string.company_name),
        STYLE(R.string.singer_style),
        GENRE(R.string.genre),
        CAREER(R.string.career),
        ALIAS(R.string.alias),
        HOROSCOPE(R.string.horoscope);
        
        private int mResId;

        private a(int i) {
            this.mResId = i;
        }

        public int getItemResId() {
            return this.mResId;
        }
    }

    /* SingerInfo */
    enum b {
        RESUME(R.string.singer_resume),
        PERSONAL_LIFE(R.string.personal_life),
        AWARDS(R.string.awards),
        EARLY_EXPERIENCE(R.string.early_experience),
        PIECES(R.string.pieces),
        ENTERTAINMENT_EXPERIENCE(R.string.entertainment_experience);
        
        private int mResId;

        private b(int i) {
            this.mResId = i;
        }

        int getItemResId() {
            return this.mResId;
        }
    }

    public List<f> a() {
        return this.a;
    }

    public void a(List<f> list) {
        this.a = list;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public static List<f> a(SingerDetailData singerDetailData) {
        List arrayList = new ArrayList();
        if (singerDetailData != null) {
            Resources resources = BaseApplication.e().getResources();
            a(resources, b.RESUME.getItemResId(), a(resources, singerDetailData), arrayList);
            a(resources, b.PERSONAL_LIFE.getItemResId(), singerDetailData.getPersonalLife(), arrayList);
            a(resources, b.AWARDS.getItemResId(), singerDetailData.getAwards(), arrayList);
            a(resources, b.EARLY_EXPERIENCE.getItemResId(), singerDetailData.getEarlyExperience(), arrayList);
            a(resources, b.ENTERTAINMENT_EXPERIENCE.getItemResId(), singerDetailData.getEntertainmentExperience(), arrayList);
        }
        return arrayList;
    }

    private static void a(Resources resources, int i, String str, List<f> list) {
        if (!m.a(str)) {
            list.add(new f(resources.getString(i), str));
        }
    }

    private static String a(Resources resources, SingerDetailData singerDetailData) {
        StringBuilder stringBuilder = new StringBuilder();
        a(resources, a.CHINESE_NAME.getItemResId(), singerDetailData.getChineseName(), stringBuilder);
        a(resources, a.ALIAS.getItemResId(), m.a(";", singerDetailData.getAlias()), stringBuilder);
        a(resources, a.FOREIGN_NAME.getItemResId(), singerDetailData.getEnglishName(), stringBuilder);
        a(resources, a.BIRTHDAY.getItemResId(), singerDetailData.getBirthDate(), stringBuilder);
        a(resources, a.COUNTRY.getItemResId(), singerDetailData.getCountry(), stringBuilder);
        a(resources, a.NATION.getItemResId(), singerDetailData.getNation(), stringBuilder);
        a(resources, a.BIRTH_PLACE.getItemResId(), singerDetailData.getBirthPlace(), stringBuilder);
        a(resources, a.HEIGHT.getItemResId(), singerDetailData.getHeight(), stringBuilder);
        a(resources, a.WEIGHT.getItemResId(), singerDetailData.getWeight(), stringBuilder);
        a(resources, a.BLOOD_TYPE.getItemResId(), singerDetailData.getBloodType(), stringBuilder);
        a(resources, a.SCHOOL.getItemResId(), singerDetailData.getSchool(), stringBuilder);
        a(resources, a.COMPANY.getItemResId(), singerDetailData.getCompanyName(), stringBuilder);
        a(resources, a.STYLE.getItemResId(), b(singerDetailData.getStyles()), stringBuilder);
        a(resources, a.GENRE.getItemResId(), b(singerDetailData.getGenres()), stringBuilder);
        a(resources, a.CAREER.getItemResId(), singerDetailData.getCareer(), stringBuilder);
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private static void a(Resources resources, int i, String str, StringBuilder stringBuilder) {
        if (!m.a(str)) {
            stringBuilder.append(resources.getString(i) + ":  " + str + "\n");
        }
    }

    private static String b(List<TagData> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (TagData tag : list) {
            stringBuilder.append(tag.getTag() + ";");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}

package com.sds.android.ttpod.activities.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import java.util.ArrayList;
import java.util.List;

public class EffectLevelActivity extends SlidingClosableActivity {

    private static final class a {
        private String a;
        private String b;
        private String c;

        private a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }
    }

    private static final class b {
        private String a;
        private int b;
        private String c;

        private b(String str, int i, String str2) {
            this.a = str;
            this.b = i;
            this.c = str2;
        }
    }

    private static class c {
        private TextView a;
        private TextView b;
        private TextView c;

        public c(View view) {
            this.a = (TextView) view.findViewById(R.id.effect_level_item_title);
            this.b = (TextView) view.findViewById(R.id.effect_level_item_icon);
            this.c = (TextView) view.findViewById(R.id.effect_level_item_score);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((CharSequence) "等级说明");
        setContentView((int) R.layout.activity_effect_level);
        a.a(getActionBarController());
        initLevelDescription();
        initBehavorDescription();
    }

    private void initBehavorDescription() {
        List arrayList = new ArrayList();
        arrayList.add(new a("分享音效", "+20", "分享音效每天增加的积分上限为500"));
        arrayList.add(new a("分享的音效被赞", "+5", ""));
        arrayList.add(new a("分享的音效被保存", "+5", ""));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_behavor_description);
        LayoutInflater from = LayoutInflater.from(this);
        addTitleView(from, linearLayout, new a("积分获取方式", "奖励分值", "备注"));
        addDivider(from, linearLayout);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            linearLayout.addView(getItemView(from, R.layout.effect_behavor_item_content, (a) arrayList.get(i)));
            if (i < size - 1) {
                addDivider(from, linearLayout);
            }
        }
    }

    private void initLevelDescription() {
        List arrayList = new ArrayList();
        arrayList.add(new b("普通音效达人", R.drawable.img_effect_level_normal, "0~999"));
        arrayList.add(new b("初级音效达人", R.drawable.img_effect_level_junior, "1000~1999"));
        arrayList.add(new b("高级音效达人", R.drawable.img_effect_level_senior, "2000~4999"));
        arrayList.add(new b("资深音效达人", R.drawable.img_effect_level_professional, "5000~9999"));
        arrayList.add(new b("音效发烧友", R.drawable.img_effect_level_fancier, "10000+"));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_level_description);
        LayoutInflater from = LayoutInflater.from(this);
        addTitleView(from, linearLayout, new a("称号", "图标", "积分"));
        addDivider(from, linearLayout);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            linearLayout.addView(getEffectLevelItemView(from, (b) arrayList.get(i)));
            if (i < size - 1) {
                addDivider(from, linearLayout);
            }
        }
    }

    private void addTitleView(LayoutInflater layoutInflater, ViewGroup viewGroup, a aVar) {
        viewGroup.addView(getItemView(layoutInflater, R.layout.effect_level_item_title, aVar));
    }

    private void addDivider(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        viewGroup.addView(getHorizontalDivider(layoutInflater), new LayoutParams(-1, 2));
    }

    private View getEffectLevelItemView(LayoutInflater layoutInflater, b bVar) {
        View inflate = layoutInflater.inflate(R.layout.effect_level_item_content, null);
        ((TextView) inflate.findViewById(R.id.effect_level_item_title)).setText(bVar.a);
        ((ImageView) inflate.findViewById(R.id.effect_level_item_icon)).setImageResource(bVar.b);
        ((TextView) inflate.findViewById(R.id.effect_level_item_score)).setText(bVar.c);
        return inflate;
    }

    private View getItemView(LayoutInflater layoutInflater, int i, a aVar) {
        View inflate = layoutInflater.inflate(i, null);
        c cVar = new c(inflate);
        cVar.a.setText(aVar.a);
        cVar.b.setText(aVar.b);
        cVar.c.setText(aVar.c);
        return inflate;
    }

    private View getHorizontalDivider(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.effect_level_item_horizontal_divider, null);
    }
}

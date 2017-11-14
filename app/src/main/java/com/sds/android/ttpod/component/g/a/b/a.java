package com.sds.android.ttpod.component.g.a.b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* LyricColorPanel */
public class a extends PopupWindow {
    private GridView a = null;
    private b b;
    private a c;
    private LayoutInflater d = null;

    /* LyricColorPanel */
    private class a extends BaseAdapter {
        final /* synthetic */ a a;
        private int[] b;
        private int[] c;
        private int[] d;
        private int e;

        /* LyricColorPanel */
        private class a {
            final /* synthetic */ a a;
            private ImageView b;
            private TextView c;
            private ImageView d;

            private a(a aVar) {
                this.a = aVar;
            }
        }

        private a(a aVar) {
            this.a = aVar;
            this.b = new int[]{R.drawable.img_lyric_color_0, R.drawable.img_lyric_color_1, R.drawable.img_lyric_color_2, R.drawable.img_lyric_color_3, R.drawable.img_lyric_color_4, R.drawable.img_lyric_color_5, R.drawable.img_lyric_color_6, R.drawable.img_lyric_color_7, R.drawable.img_lyric_color_8, R.drawable.img_lyric_color_9, R.drawable.img_lyric_color_10, R.drawable.img_lyric_color_11, R.drawable.img_lyric_color_12, R.drawable.img_lyric_color_13, R.drawable.img_lyric_color_14};
            this.c = new int[]{-1, -7085825, -2337006, -14374431, -6220011, -16351165, -48547, -18174, -6655249, -10253556, -1441626, -231769, -2, -13893882, ViewCompat.MEASURED_STATE_MASK};
            this.d = new int[]{R.string.defaultColor, R.string.cyan, R.string.october, R.string.blue, R.string.sangria, R.string.viridian, R.string.pink, R.string.lemon, R.string.amethyst, R.string.emerald, R.string.deeppink, R.string.lightpink, R.string.white, R.string.green, R.string.black};
            this.e = 0;
        }

        public void a(int i) {
            int i2 = 0;
            while (i2 < this.c.length) {
                if (this.c[i2] == i) {
                    break;
                }
                i2++;
            }
            i2 = 0;
            this.e = i2;
            notifyDataSetChanged();
        }

        public void a(Context context, int i) {
            this.e = i;
            if (this.e < 0 || this.e >= this.c.length) {
                this.e = 0;
            }
            com.sds.android.ttpod.framework.storage.environment.b.d(this.c[this.e]);
            notifyDataSetChanged();
        }

        public int b(int i) {
            return this.c[i];
        }

        public int getCount() {
            return this.b.length;
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view != null) {
                aVar = (a) view.getTag();
            } else {
                a aVar2 = new a();
                view = this.a.d.inflate(R.layout.popups_lyric_color_grid_item, null);
                aVar2.b = (ImageView) view.findViewById(R.id.lyric_color_imgview);
                aVar2.c = (TextView) view.findViewById(R.id.lyric_color_textview);
                aVar2.d = (ImageView) view.findViewById(R.id.lyric_color_check_imgview);
                view.setTag(aVar2);
                aVar = aVar2;
            }
            aVar.b.setImageResource(this.b[i]);
            aVar.c.setText(this.d[i]);
            aVar.d.setVisibility(this.e == i ? 0 : 4);
            return view;
        }
    }

    /* LyricColorPanel */
    public interface b {
        void g(int i);
    }

    public a(Context context, int i, int i2) {
        super(View.inflate(context, R.layout.popups_lyric_color_panel, null), i, i2, true);
        setAnimationStyle(16973826);
        setBackgroundDrawable(new ColorDrawable(0));
        this.d = (LayoutInflater) context.getSystemService("layout_inflater");
        View contentView = getContentView();
        if (contentView != null) {
            a(context, contentView);
        }
        this.c.a(com.sds.android.ttpod.framework.storage.environment.b.S());
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    private void a(final Context context, View view) {
        this.a = (GridView) view.findViewById(R.id.lyric_color_gridview);
        this.c = new a();
        this.a.setAdapter(this.c);
        this.a.setVisibility(0);
        this.a.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ a b;

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.b.b != null) {
                    this.b.b.g(this.b.c.b(i));
                    ImageView imageView = (ImageView) view.findViewById(R.id.lyric_color_check_imgview);
                    if (imageView != null) {
                        imageView.setVisibility(0);
                    }
                    this.b.c.a(context, i);
                }
            }
        });
    }
}

package com.ttfm.android.sdk.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RatingBar;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.tencent.open.yyb.TitleBar;
import com.ttfm.android.sdk.embed.TTFMPlayAdapter;
import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.entity.ChannelEntity.LabelInfo;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import com.ttfm.android.sdk.utils.TTFMUtils;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class ChannelListAdapter extends BaseAdapter {
    public static final int MAX_LABEL_NUM = 3;
    private final int MARGIN_LEFT_RIGHT = 15;
    private ArrayList<ChannelEntity> mChannelInfos = new ArrayList();
    private Context mContext;
    private Bitmap mImageViewDefault = null;
    private OnClickListener mOnClickListener = null;

    class ViewHolder {
        ImageView imageView;
        LinearLayout labellayout;
        TextView name;
        ImageView play;
        RatingBar ratingbar;

        ViewHolder() {
        }
    }

    public ChannelListAdapter(Context context, OnClickListener onClickListener) {
        this.mContext = context;
        this.mOnClickListener = onClickListener;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        ChannelEntity channelEntity = (ChannelEntity) this.mChannelInfos.get(i);
        if (view == null) {
            view2 = (View) new SoftReference(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ttfm_channel_list_item, null)).get();
            ViewHolder viewHolder2 = new ViewHolder();
            viewHolder2.imageView = (ImageView) view2.findViewById(R.id.channel_item_img);
            viewHolder2.name = (TextView) view2.findViewById(R.id.channel_item_name);
            viewHolder2.play = (ImageView) view2.findViewById(R.id.channel_item_play);
            view2.setTag(viewHolder2);
            viewHolder = viewHolder2;
        } else {
            viewHolder = (ViewHolder) view.getTag();
            view2 = view;
        }
        if (channelEntity == null) {
            viewHolder.name.setText("");
            viewHolder.imageView.setImageBitmap(this.mImageViewDefault);
            viewHolder.ratingbar.setRating(0.0f);
            viewHolder.labellayout.removeAllViews();
            view2.setTag(R.id.channel_obj, channelEntity);
            return view2;
        }
        viewHolder.play.setOnClickListener(this.mOnClickListener);
        ChannelEntity curPlayChannel = TTFMPlayAdapter.getInstance().getCurPlayChannel();
        if (curPlayChannel != null && curPlayChannel.getChannelId() == channelEntity.getChannelId() && TTFMPlayAdapter.getInstance().getCurrentPlayStatus() == PlayStatus.STATUS_PLAYING) {
            viewHolder.play.setImageResource(R.drawable.img_rank_play_paused);
        } else {
            viewHolder.play.setImageResource(R.drawable.img_rank_play_normal);
        }
        viewHolder.play.setTag(channelEntity);
        if (channelEntity.getChannelName() != null) {
            viewHolder.name.setText(channelEntity.getChannelName());
        } else {
            viewHolder.name.setText("");
        }
        TTFMImageUtils.setImageView(viewHolder.imageView, channelEntity.getChannelBackgroundImg(), 0.2f, this.mImageViewDefault);
        viewHolder.ratingbar.setRating(TTFMUtils.countRating(channelEntity.getCiPlayScore()));
        initLabelView(channelEntity.getLabelInfos(), viewHolder.labellayout);
        view2.setTag(R.id.channel_obj, channelEntity);
        c.a(viewHolder.name, ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(view2, ThemeElement.TILE_BACKGROUND);
        return view2;
    }

    public int getCount() {
        if (this.mChannelInfos != null) {
            return this.mChannelInfos.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (i < 0 || i >= this.mChannelInfos.size()) {
            return null;
        }
        return this.mChannelInfos.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void addListData(List<ChannelEntity> list) {
        if (list != null) {
            if (this.mChannelInfos == null) {
                this.mChannelInfos = new ArrayList();
            }
            this.mChannelInfos.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setListData(List<ChannelEntity> list) {
        if (list != null) {
            if (this.mChannelInfos == null) {
                this.mChannelInfos = new ArrayList();
            }
            this.mChannelInfos.clear();
            this.mChannelInfos.addAll(list);
            notifyDataSetChanged();
        }
    }

    private void initLabelView(ArrayList<LabelInfo> arrayList, LinearLayout linearLayout) {
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            if (arrayList != null) {
                LayoutParams layoutParams = new LayoutParams(-1, -2);
                TTFMUtils.measureView(linearLayout);
                int measuredWidth = linearLayout.getMeasuredWidth();
                LinearLayout linearLayout2 = null;
                int size = arrayList.size();
                int i = 0;
                float f = 0.0f;
                while (i < size) {
                    View createTextView = createTextView(((LabelInfo) arrayList.get(i)).getLiName(), i);
                    TTFMUtils.measureView(createTextView);
                    float measuredWidth2 = (float) createTextView.getMeasuredWidth();
                    float f2 = ((f + measuredWidth2) + 15.0f) + TitleBar.SHAREBTN_RIGHT_MARGIN;
                    if (i < 3 && f2 < ((float) measuredWidth)) {
                        LinearLayout horizontalLinearLayout;
                        float f3;
                        if (linearLayout2 == null) {
                            horizontalLinearLayout = getHorizontalLinearLayout(layoutParams);
                            linearLayout.addView(horizontalLinearLayout);
                            f3 = measuredWidth2;
                        } else {
                            LinearLayout linearLayout3 = linearLayout2;
                            f3 = f2;
                            horizontalLinearLayout = linearLayout3;
                        }
                        horizontalLinearLayout.addView(createTextView);
                        i++;
                        f = f3;
                        linearLayout2 = horizontalLinearLayout;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private TextView createTextView(String str, int i) {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.rightMargin = 15;
        View textView = new TextView(this.mContext);
        textView.setText(str);
        textView.setId(i);
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(this.mContext.getResources().getColor(R.color.fm_tag_item_text_color));
        textView.setTextSize(12.0f);
        textView.setPadding(8, 8, 8, 8);
        textView.setGravity(17);
        textView.setTag(str);
        c.a(textView, ThemeElement.TILE_MASK);
        textView.setClickable(false);
        textView.setFocusable(false);
        return textView;
    }

    private LinearLayout getHorizontalLinearLayout(LayoutParams layoutParams) {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(16);
        return linearLayout;
    }

    public ArrayList<ChannelEntity> getList() {
        return this.mChannelInfos;
    }
}

package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.data.AudioEffectItemData;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectCommResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectItemResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.m;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;

/* CloudAudioEffectAPI */
public class e {
    public static o<AudioEffectUserResult> a(String str) {
        return new i(AudioEffectUserResult.class, "http://ae.hotchanson.com/user/info/" + str, "eq");
    }

    public static o<AudioEffectItemResult> a(long j, int i, int i2) {
        return new i(AudioEffectItemResult.class, "http://ae.hotchanson.com/public/query_by_song_id", "eq").b(MediasColumns.SONG_ID, Long.valueOf(j)).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }

    public static o<AudioEffectItemResult> a(String str, String str2, int i, int i2) {
        return new i(AudioEffectItemResult.class, "http://ae.hotchanson.com/public/query_by_song_info", "eq").b("song_name", str).b("singer_name", str2).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }

    public static o<AudioEffectAddResult> a(String str, String str2, int i, long j, String str3, String str4, int i2, String str5, AudioEffectItemData audioEffectItemData) {
        String str6 = "http://ae.hotchanson.com/ae/create/" + str;
        return new m(AudioEffectAddResult.class, str6).a("_id", (Object) str2).a("style", Integer.valueOf(i)).a(MediasColumns.SONG_ID, Long.valueOf(j)).a("song_name", (Object) str3).a("singer_name", (Object) str4).a("output", Integer.valueOf(i2)).a("device", (Object) str5).a("audio_effect", f.a((Object) audioEffectItemData));
    }

    public static o<AudioEffectAddResult> a(String str, String str2, int i, String str3, String str4, int i2, String str5, AudioEffectItemData audioEffectItemData) {
        String str6 = "http://ae.hotchanson.com/ae/create/" + str;
        return new m(AudioEffectAddResult.class, str6, "eq").a("_id", (Object) str2).a("style", Integer.valueOf(i)).a("song_name", (Object) str3).a("singer_name", (Object) str4).a("output", Integer.valueOf(i2)).a("device", (Object) str5).a("audio_effect", f.a((Object) audioEffectItemData));
    }

    public static o<AudioEffectCommResult> a(String str, String str2) {
        return new i(AudioEffectCommResult.class, "http://ae.hotchanson.com/ae/pick/" + str + "/" + str2, "eq");
    }

    public static o<AudioEffectCommResult> b(String str, String str2) {
        return new i(AudioEffectCommResult.class, "http://ae.hotchanson.com/ae/bind/" + str + "/" + str2, "eq");
    }
}

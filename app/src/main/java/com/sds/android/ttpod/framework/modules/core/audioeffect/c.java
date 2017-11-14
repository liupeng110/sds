package com.sds.android.ttpod.framework.modules.core.audioeffect;

import android.os.Build;
import android.os.Handler;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.cloudapi.ttpod.a.r;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItemData;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectCommResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectItemResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.j;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@j(a = {com.sds.android.ttpod.framework.modules.a.AUDIOEFFECT_CHANGED})
/* AudioEffectModule */
public class c extends b {
    private static final ReentrantLock a = new ReentrantLock();
    private volatile List<String> b = new ArrayList();
    private volatile List<String> c = new ArrayList();
    private HashMap<String, Boolean> d;
    private Runnable e = new Runnable(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void run() {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_LOCAL, Boolean.TRUE), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
        }
    };
    private Handler f = new Handler();
    private a g = new a();

    /* AudioEffectModule */
    private class a implements Runnable {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        public void run() {
            this.a.h();
        }
    }

    protected com.sds.android.ttpod.framework.modules.c id() {
        return com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT;
    }

    public void onCreate() {
        super.onCreate();
        f();
        this.d = com.sds.android.ttpod.framework.storage.a.a.a().o();
    }

    private void f() {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                c.a.lock();
                if (MediaStorage.isGroupExisted(c.sContext, MediaStorage.GROUP_ID_EFFECT_LOCAL)) {
                    this.a.b = this.a.e(MediaStorage.GROUP_ID_EFFECT_LOCAL);
                } else {
                    MediaStorage.insertGroup(c.sContext, MediaStorage.GROUP_NAME_EFFECT_LOCAL, MediaStorage.GROUP_ID_EFFECT_LOCAL, GroupType.CUSTOM_LOCAL);
                }
                if (MediaStorage.isGroupExisted(c.sContext, MediaStorage.GROUP_ID_EFFECT_ONLINE)) {
                    this.a.c = this.a.e(MediaStorage.GROUP_ID_EFFECT_ONLINE);
                } else {
                    MediaStorage.insertGroup(c.sContext, MediaStorage.GROUP_NAME_EFFECT_ONLINE, MediaStorage.GROUP_ID_EFFECT_ONLINE, GroupType.CUSTOM_ONLINE);
                }
                c.a.unlock();
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        com.sds.android.ttpod.framework.storage.a.a.a().a(this.d);
    }

    public long timeOutInMills() {
        return 60000;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.SET_CLOUD_AUDIO_EFFECT_ENABLED, i.a(cls, "setCloudAudioEffectEnabled", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_CLOUD_AUDIO_EFFECT, i.a(cls, "applyCloudAudioEffect", AudioEffectItem.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_LOCAL_AUDIO_EFFECT, i.a(cls, "setLocalAudioEffectEnabled", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_EQUALIZER, i.a(cls, "setEqualizer", Settings.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_EQUALIZER, i.a(cls, "getEqualizer", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_BASSBOOST, i.a(cls, "setBassBoost", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_BOOSTLIMIT_ENABLED, i.a(cls, "setBoostLimitEnabled", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_TREBLEBOOST, i.a(cls, "setTrebleBoost", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_VIRTUALIZER, i.a(cls, "setVirtualizer", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_REVERB, i.a(cls, "setReverb", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_CHANNELBALANCE, i.a(cls, "setChannelBalance", Float.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SAVE_CUSTOM_EQUALIZER, i.a(cls, "saveCustomEqualizer", Settings.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_CUSTOM_EQUALIZER, i.a(cls, "deleteCustomEqualizer", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_CUSTOM_EQUALIZER_LIST, i.a(cls, "queryCustomEqualizerList", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_EFFECT_USERINFO, i.a(cls, "queryEffectUserInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_EFFECT, i.a(cls, "queryEffect", MediaItem.class, Integer.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PICK_EFFECT, i.a(cls, "pickEffect", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.BIND_EFFECT, i.a(cls, "bindEffect", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_PRIVATE_EFFECT, i.a(cls, "queryPrivateEffect", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_PRIVATE_EFFECT_LIST, i.a(cls, "deletePrivateEffectList", List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_EFFECT_ITEM, i.a(cls, "setEffectItem", AudioEffectItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SAVE_EFFECT_TO_LOCAL, i.a(cls, "saveEffectToLocal", MediaItem.class, a.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SAVE_EFFECT_TO_NETWORK, i.a(cls, "saveEffectToNetwork", MediaItem.class, AudioEffectItemData.class, Integer.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SAVE_EFFECT, i.a(cls, "saveEffect", MediaItem.class, a.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.IS_PICKED_EFFECT, i.a(cls, "isPickedEffect", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_AUDIO_EFFECT_TRY_MODE, i.a(cls, "setAudioEffectTryMode", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_AUDIO_EFFECT_RESET, i.a(cls, "setAudioEffectReset", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.AUDIOEFFECT_CHANGED, i.a(cls, "audioeffectChanged", new Class[0]));
    }

    public void setCloudAudioEffectEnabled(Boolean bool) {
        com.sds.android.ttpod.framework.storage.environment.b.C(bool.booleanValue());
    }

    public void setLocalAudioEffectEnabled(Boolean bool) {
        if (bool.booleanValue()) {
            e.a(sContext).v();
        } else {
            e.a(sContext).u();
        }
    }

    public void setAudioEffectTryMode(Boolean bool) {
        e.a(sContext).a(bool);
    }

    public void applyCloudAudioEffect(AudioEffectItem audioEffectItem, Boolean bool) {
        e.a(sContext).a(audioEffectItem, bool.booleanValue());
    }

    public void setEqualizer(Settings settings) {
        com.sds.android.ttpod.framework.storage.environment.b.j(settings.toString());
        a(7);
    }

    private void g() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MANUAL_SETTING_EFFECT, new Object[0]), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT, (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
    }

    private void a(int i) {
        e.a(sContext).a(i);
        g();
    }

    public Settings getEqualizer() {
        try {
            AudioEffectParam t = e.a(sContext).t();
            if (t == null) {
                return new Settings(e.b(), (short) 10, e.b(e.b()));
            }
            return new Settings(t.g());
        } catch (Exception e) {
            return null;
        }
    }

    public void setBassBoost(Integer num) {
        com.sds.android.ttpod.framework.storage.environment.b.l(num.intValue());
        a(1);
    }

    public void setAudioEffectReset() {
        e.a(sContext).p();
        g();
    }

    public void setBoostLimitEnabled(Boolean bool) {
        com.sds.android.ttpod.framework.storage.environment.b.F(bool.booleanValue());
        a(6);
    }

    public void setTrebleBoost(Integer num) {
        com.sds.android.ttpod.framework.storage.environment.b.m(num.intValue());
        a(2);
    }

    public void setVirtualizer(Integer num) {
        com.sds.android.ttpod.framework.storage.environment.b.n(num.intValue());
        a(3);
    }

    public void setReverb(Integer num) {
        com.sds.android.ttpod.framework.storage.environment.b.k(num.intValue());
        a(4);
    }

    public void setChannelBalance(Float f) {
        com.sds.android.ttpod.framework.storage.environment.b.a(f.floatValue());
        a(5);
    }

    public void saveCustomEqualizer(Settings settings) {
        d.a((Object) settings, "TTEqualizer.Settings");
        b(settings.getName());
        String a = a(settings.getName());
        com.sds.android.sdk.lib.util.e.h(a);
        com.sds.android.sdk.lib.util.e.a(settings.toString(), a);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_CUSTOM_EQUALIZER, settings), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
    }

    public void deleteCustomEqualizer(String str) {
        d.a((Object) str, "equalizerName");
        b(str);
        com.sds.android.sdk.lib.util.e.h(a(str));
    }

    private String a(String str) {
        return com.sds.android.ttpod.framework.a.w() + File.separator + str + ".tteq";
    }

    public void queryCustomEqualizerList() {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                List arrayList = new ArrayList();
                File[] listFiles = new File(com.sds.android.ttpod.framework.a.w()).listFiles();
                if (listFiles != null) {
                    Arrays.sort(listFiles, new Comparator<File>(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return a((File) obj, (File) obj2);
                        }

                        public int a(File file, File file2) {
                            long lastModified = file.lastModified() - file2.lastModified();
                            if (lastModified < 0) {
                                return 1;
                            }
                            if (lastModified > 0) {
                                return -1;
                            }
                            return 0;
                        }
                    });
                    for (File file : listFiles) {
                        if (file.getAbsolutePath().endsWith(".tteq")) {
                            try {
                                Settings settings = new Settings(m.a(new FileInputStream(file)));
                                settings.setName(com.sds.android.sdk.lib.util.e.k(file.getAbsolutePath()));
                                arrayList.add(settings);
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    String[] split = m.a(new FileInputStream(file)).split(" ");
                                    if (split != null && split.length == 10) {
                                        try {
                                            short[] sArr = new short[10];
                                            int length = split.length;
                                            int i = 0;
                                            int i2 = 0;
                                            while (i < length) {
                                                int i3 = i2 + 1;
                                                sArr[i2] = Short.parseShort(split[i]);
                                                i++;
                                                i2 = i3;
                                            }
                                            arrayList.add(new Settings(com.sds.android.sdk.lib.util.e.k(file.getAbsolutePath()), (short) 10, sArr));
                                        } catch (NumberFormatException e2) {
                                            e.printStackTrace();
                                        }
                                    }
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                    }
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_CUSTOM_EQUALIZER_LIST, arrayList), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
            }
        });
    }

    private void b(String str) {
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.j() && e.a().contains(str)) {
            throw new IllegalArgumentException(str + " is default preset!");
        }
    }

    public void queryEffectUserInfo() {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.cloudapi.ttpod.a.e.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken()).a(new p<AudioEffectUserResult>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                    b((AudioEffectUserResult) baseResult);
                }

                public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                    a((AudioEffectUserResult) baseResult);
                }

                public void a(AudioEffectUserResult audioEffectUserResult) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_EFFECT_USERINFO, audioEffectUserResult), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                }

                public void b(AudioEffectUserResult audioEffectUserResult) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_EFFECT_USERINFO, audioEffectUserResult), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                }
            });
        }
    }

    public void queryEffect(MediaItem mediaItem, Integer num, Integer num2) {
        o a;
        if (mediaItem.getSongID() == null || mediaItem.getSongID().longValue() == 0) {
            a = com.sds.android.cloudapi.ttpod.a.e.a(mediaItem.getTitle(), mediaItem.getArtist(), num.intValue(), num2.intValue());
        } else {
            a = com.sds.android.cloudapi.ttpod.a.e.a(mediaItem.getSongID().longValue(), num.intValue(), num2.intValue());
        }
        a.a(new p<AudioEffectItemResult>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((AudioEffectItemResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((AudioEffectItemResult) baseResult);
            }

            public void a(AudioEffectItemResult audioEffectItemResult) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_EFFECT, audioEffectItemResult), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
            }

            public void b(AudioEffectItemResult audioEffectItemResult) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_EFFECT, audioEffectItemResult), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
            }
        });
    }

    public void pickEffect(final String str) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.cloudapi.ttpod.a.e.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), str).a(new p<AudioEffectCommResult>(this) {
                final /* synthetic */ c b;

                public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                    b((AudioEffectCommResult) baseResult);
                }

                public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                    a((AudioEffectCommResult) baseResult);
                }

                public void a(AudioEffectCommResult audioEffectCommResult) {
                    this.b.d.put(str, Boolean.TRUE);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PICK_EFFECT, audioEffectCommResult, str), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                }

                public void b(AudioEffectCommResult audioEffectCommResult) {
                    this.b.d.put(str, Boolean.TRUE);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PICK_EFFECT, audioEffectCommResult, str), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                }
            });
        }
    }

    public void bindEffect(String str) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.cloudapi.ttpod.a.e.b(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), str).a(new p<AudioEffectCommResult>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                    b((AudioEffectCommResult) baseResult);
                }

                public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                    a((AudioEffectCommResult) baseResult);
                }

                public void a(AudioEffectCommResult audioEffectCommResult) {
                }

                public void b(AudioEffectCommResult audioEffectCommResult) {
                }
            });
        }
    }

    public void queryPrivateEffect() {
        com.sds.android.sdk.lib.e.a.a(this.g);
    }

    private void h() {
        i();
        File file = new File(com.sds.android.ttpod.framework.a.e());
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_PRIVATE_EFFECT, new ArrayList(), new ArrayList()), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
            return;
        }
        List<f> arrayList = new ArrayList();
        Collection arrayList2 = new ArrayList();
        Collection<f> arrayList3 = new ArrayList();
        for (File absolutePath : listFiles) {
            f d = d(absolutePath.getAbsolutePath());
            if (d != null) {
                long e = d.e();
                MediaItem a;
                if (e == 0 && !m.a(d.d())) {
                    a = k.a(d.d());
                    g.a("AudioEffectModule", "buildMediaItem from " + d.d() + ", mediaitem=" + a);
                    if (a != null) {
                        a.setSongID(Long.valueOf(e));
                        d.a(a);
                        arrayList.add(d);
                    }
                } else if (e > 0) {
                    String a2 = a(Long.valueOf(e));
                    a.lock();
                    if (this.c.contains(a2)) {
                        a.unlock();
                        a = MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_EFFECT_ONLINE, a2);
                        if (a != null) {
                            a.setLocalDataSource(d.d());
                            d.a(a);
                            arrayList.add(d);
                        } else {
                            arrayList2.add(Long.valueOf(e));
                            arrayList3.add(d);
                        }
                    } else {
                        a.unlock();
                        arrayList2.add(Long.valueOf(e));
                        arrayList3.add(d);
                    }
                }
            }
        }
        if (arrayList2.size() > 0) {
            OnlineMediaItemsResult onlineMediaItemsResult = (OnlineMediaItemsResult) r.a(arrayList2).g();
            if (onlineMediaItemsResult.getCode() == 1) {
                for (OnlineMediaItem onlineMediaItem : onlineMediaItemsResult.getDataList()) {
                    for (f fVar : arrayList3) {
                        if (fVar.e() == onlineMediaItem.getSongId()) {
                            MediaItem a3 = k.a(onlineMediaItem);
                            a3.setLocalDataSource(fVar.d());
                            fVar.a(a3);
                            g.a("AudioEffectModule", "insert MediaItem GROUP_ID_EFFECT_ONLINE " + a3.getTitle() + "-" + a3.getArtist());
                            MediaStorage.insertMediaItem(sContext, MediaStorage.GROUP_ID_EFFECT_ONLINE, a3);
                            a.lock();
                            if (!this.c.contains(a3.getID())) {
                                this.c.add(a3.getID());
                            }
                            a.unlock();
                        }
                    }
                }
                arrayList.addAll(arrayList3);
            }
        }
        Collections.sort(arrayList, new Comparator<f>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((f) obj, (f) obj2);
            }

            public int a(f fVar, f fVar2) {
                long g = fVar.g() - fVar2.g();
                if (g < 0) {
                    return 1;
                }
                if (g > 0) {
                    return -1;
                }
                return 0;
            }
        });
        List arrayList4 = new ArrayList(arrayList.size());
        for (f f : arrayList) {
            arrayList4.add(f.f());
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_PRIVATE_EFFECT, arrayList, arrayList4), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
    }

    private String a(Long l) {
        if (l != null) {
            return com.sds.android.sdk.lib.util.k.b.a(String.valueOf(l));
        }
        throw new IllegalArgumentException("SongId must not be null!");
    }

    private void i() {
        c(MediaStorage.GROUP_ID_EFFECT_LOCAL);
        c(MediaStorage.GROUP_ID_EFFECT_ONLINE);
    }

    private void c(String str) {
        Iterator it = MediaStorage.queryMediaItemList(sContext, str, com.sds.android.ttpod.framework.storage.environment.b.l(str)).iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = (MediaItem) it.next();
            if (!com.sds.android.sdk.lib.util.e.b(d.a(mediaItem))) {
                MediaStorage.deleteMediaItem(sContext, str, mediaItem.getID());
                it.remove();
            }
        }
    }

    private f d(String str) {
        a aVar = (a) f.a(com.sds.android.sdk.lib.util.e.i(str), a.class);
        if (aVar == null) {
            return null;
        }
        f fVar = new f(str, aVar);
        fVar.a(aVar.o());
        return fVar;
    }

    public void deletePrivateEffectList(final List list) {
        if (list != null && list.size() != 0) {
            if (list.get(0).getClass().equals(f.class)) {
                com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                    final /* synthetic */ c b;

                    public void run() {
                        for (f c : list) {
                            com.sds.android.sdk.lib.util.e.h(c.c());
                        }
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_DELETE_PRIVATE_EFFECT_LIST, new Object[0]), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                    }
                });
            } else if (list.get(0).getClass().equals(MediaItem.class)) {
                com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                    final /* synthetic */ c b;

                    public void run() {
                        File file = new File(com.sds.android.ttpod.framework.a.e());
                        if (file.exists()) {
                            int i;
                            ArrayList arrayList = new ArrayList();
                            for (File absolutePath : file.listFiles()) {
                                f b = this.b.d(absolutePath.getAbsolutePath());
                                if (b != null) {
                                    arrayList.add(b);
                                }
                            }
                            int size = arrayList.size();
                            if (size != 0) {
                                int size2 = list.size();
                                i = 0;
                                for (int i2 = 0; i2 < size2; i2++) {
                                    MediaItem mediaItem = (MediaItem) list.get(i2);
                                    int i3 = 0;
                                    while (i3 < size) {
                                        int i4;
                                        f fVar = (f) arrayList.get(i3);
                                        if (fVar != null) {
                                            if (fVar.d() == null) {
                                                i4 = i;
                                            } else if (m.a(fVar.d(), mediaItem.getLocalDataSource())) {
                                                com.sds.android.sdk.lib.util.e.h(fVar.c());
                                                i4 = i + 1;
                                            }
                                            i3++;
                                            i = i4;
                                        }
                                        i4 = i;
                                        i3++;
                                        i = i4;
                                    }
                                    com.sds.android.sdk.lib.util.e.h(d.a(mediaItem.getSongID(), mediaItem.getTitle(), mediaItem.getArtist()));
                                    if (com.sds.android.ttpod.framework.storage.a.a.a().M().equals(mediaItem)) {
                                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_LOCAL_AUDIO_EFFECT, Boolean.valueOf(true)));
                                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_AUDIO_EFFECT_INFO, new Object[0]), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT, (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
                                    }
                                }
                                if (i > 0) {
                                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_DELETE_PRIVATE_EFFECT_LIST, new Object[0]), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public void setEffectItem(AudioEffectItem audioEffectItem) {
        setEqualizer(new Settings("", (short) 10, audioEffectItem.getDataEqualizer()));
        setReverb(Integer.valueOf(audioEffectItem.getDataReverb()));
        setBassBoost(Integer.valueOf(audioEffectItem.getDataBass()));
        setTrebleBoost(Integer.valueOf(audioEffectItem.getDataTreble()));
        setVirtualizer(Integer.valueOf(audioEffectItem.getDataVirtualizer()));
        setChannelBalance(Float.valueOf(audioEffectItem.getDataBalance()));
        setBoostLimitEnabled(Boolean.valueOf(audioEffectItem.getDataIsLimit()));
    }

    public void saveEffectToLocal(MediaItem mediaItem, a aVar) {
        Throwable e;
        this.f.removeCallbacks(this.e);
        BufferedWriter bufferedWriter;
        try {
            new b().a(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId(), mediaItem.getTitle(), mediaItem.getArtist(), aVar);
            aVar.a(aVar.a());
            bufferedWriter = new BufferedWriter(new FileWriter(d.a(mediaItem), false));
            try {
                bufferedWriter.write(f.a((Object) aVar));
                bufferedWriter.flush();
                bufferedWriter.close();
                a(mediaItem);
                this.f.postDelayed(this.e, 160);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            bufferedWriter = null;
            g.b("AudioEffectModule", "saveEffect error", e);
            try {
                bufferedWriter.close();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_LOCAL, Boolean.FALSE), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
        }
    }

    public void saveEffect(final MediaItem mediaItem, final a aVar, final Boolean bool) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c d;

            public void run() {
                Throwable e;
                AudioEffectItemData audioEffectItemData;
                o a;
                AudioEffectAddResult audioEffectAddResult;
                String title = mediaItem.getTitle();
                String artist = mediaItem.getArtist();
                Long songID = mediaItem.getSongID();
                String str = Build.MODEL;
                Integer valueOf = Integer.valueOf(aVar.e());
                Integer valueOf2 = Integer.valueOf(aVar.f());
                b bVar = new b();
                bVar.a(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId(), title, artist, aVar);
                String a2 = bVar.a();
                BufferedWriter bufferedWriter;
                try {
                    aVar.a(a2);
                    bufferedWriter = new BufferedWriter(new FileWriter(d.a(mediaItem), false));
                    try {
                        bufferedWriter.write(f.a(aVar));
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        this.d.a(mediaItem);
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_LOCAL, Boolean.TRUE), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                        if (!bool.booleanValue()) {
                            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.EFFECT_SAVE_RESULT, Boolean.TRUE), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        g.b("AudioEffectModule", "saveEffect error", e);
                        try {
                            bufferedWriter.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_LOCAL, Boolean.FALSE), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                        if (!bool.booleanValue()) {
                            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.EFFECT_SAVE_RESULT, Boolean.FALSE), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                        }
                        if (!bool.booleanValue()) {
                            audioEffectItemData = new AudioEffectItemData();
                            audioEffectItemData.setEqualizer(aVar.n());
                            audioEffectItemData.setBalance(aVar.l());
                            audioEffectItemData.setBass(aVar.h());
                            audioEffectItemData.setTreble(aVar.i());
                            audioEffectItemData.setVirtualizer(aVar.j());
                            audioEffectItemData.setReverb(aVar.k());
                            audioEffectItemData.setIsLimit(aVar.m());
                            if (!com.sds.android.ttpod.framework.storage.environment.b.av()) {
                                if (songID != null) {
                                }
                                a = com.sds.android.cloudapi.ttpod.a.e.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), a2, valueOf.intValue(), title, artist, valueOf2.intValue(), str, audioEffectItemData);
                                audioEffectAddResult = (AudioEffectAddResult) a.g();
                                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_NETWORK, audioEffectAddResult), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                            }
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    bufferedWriter = null;
                    g.b("AudioEffectModule", "saveEffect error", e);
                    bufferedWriter.close();
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_LOCAL, Boolean.FALSE), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                    if (bool.booleanValue()) {
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.EFFECT_SAVE_RESULT, Boolean.FALSE), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                    }
                    if (!bool.booleanValue()) {
                        audioEffectItemData = new AudioEffectItemData();
                        audioEffectItemData.setEqualizer(aVar.n());
                        audioEffectItemData.setBalance(aVar.l());
                        audioEffectItemData.setBass(aVar.h());
                        audioEffectItemData.setTreble(aVar.i());
                        audioEffectItemData.setVirtualizer(aVar.j());
                        audioEffectItemData.setReverb(aVar.k());
                        audioEffectItemData.setIsLimit(aVar.m());
                        if (!com.sds.android.ttpod.framework.storage.environment.b.av()) {
                            if (songID != null) {
                            }
                            a = com.sds.android.cloudapi.ttpod.a.e.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), a2, valueOf.intValue(), title, artist, valueOf2.intValue(), str, audioEffectItemData);
                            audioEffectAddResult = (AudioEffectAddResult) a.g();
                            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_NETWORK, audioEffectAddResult), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                        }
                    }
                }
                if (!bool.booleanValue()) {
                    audioEffectItemData = new AudioEffectItemData();
                    audioEffectItemData.setEqualizer(aVar.n());
                    audioEffectItemData.setBalance(aVar.l());
                    audioEffectItemData.setBass(aVar.h());
                    audioEffectItemData.setTreble(aVar.i());
                    audioEffectItemData.setVirtualizer(aVar.j());
                    audioEffectItemData.setReverb(aVar.k());
                    audioEffectItemData.setIsLimit(aVar.m());
                    if (!com.sds.android.ttpod.framework.storage.environment.b.av()) {
                        if (songID != null || songID.longValue() == 0) {
                            a = com.sds.android.cloudapi.ttpod.a.e.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), a2, valueOf.intValue(), title, artist, valueOf2.intValue(), str, audioEffectItemData);
                        } else {
                            a = com.sds.android.cloudapi.ttpod.a.e.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), a2, valueOf.intValue(), songID.longValue(), title, artist, valueOf2.intValue(), str, audioEffectItemData);
                        }
                        audioEffectAddResult = (AudioEffectAddResult) a.g();
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_NETWORK, audioEffectAddResult), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                    }
                }
            }
        });
    }

    private void a(MediaItem mediaItem) {
        a.lock();
        if (mediaItem.isOnline()) {
            if (this.c.contains(mediaItem.getID())) {
                MediaStorage.deleteMediaItem(sContext, MediaStorage.GROUP_ID_EFFECT_ONLINE, mediaItem.getID());
            } else {
                this.c.add(mediaItem.getID());
            }
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_EFFECT_ONLINE, mediaItem));
        } else {
            if (this.b.contains(mediaItem.getID())) {
                MediaStorage.deleteMediaItem(sContext, MediaStorage.GROUP_ID_EFFECT_LOCAL, mediaItem.getID());
            } else {
                this.b.add(mediaItem.getID());
            }
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_EFFECT_LOCAL, mediaItem));
        }
        a.unlock();
    }

    public void saveEffectToNetwork(MediaItem mediaItem, AudioEffectItemData audioEffectItemData, Integer num, Integer num2) {
        if (mediaItem != null && com.sds.android.ttpod.framework.storage.environment.b.av()) {
            o a;
            String title = mediaItem.getTitle();
            String artist = mediaItem.getArtist();
            Long songID = mediaItem.getSongID();
            String a2 = new b(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId(), title, artist, audioEffectItemData.getEqualizer(), audioEffectItemData.getBass(), audioEffectItemData.getTreble(), audioEffectItemData.getVirtualizer(), audioEffectItemData.getReverb(), audioEffectItemData.getBalance(), audioEffectItemData.getIsLimit()).a();
            String str = Build.MODEL;
            if (songID == null || songID.longValue() == 0) {
                a = com.sds.android.cloudapi.ttpod.a.e.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), a2, num.intValue(), title, artist, num2.intValue(), str, audioEffectItemData);
            } else {
                a = com.sds.android.cloudapi.ttpod.a.e.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), a2, num.intValue(), songID.longValue(), title, artist, num2.intValue(), str, audioEffectItemData);
            }
            a.a(new p<AudioEffectAddResult>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                    b((AudioEffectAddResult) baseResult);
                }

                public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                    a((AudioEffectAddResult) baseResult);
                }

                public void a(AudioEffectAddResult audioEffectAddResult) {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_NETWORK, audioEffectAddResult), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                }

                public void b(AudioEffectAddResult audioEffectAddResult) {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_NETWORK, audioEffectAddResult), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
                }
            });
        }
    }

    public void audioeffectChanged() {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_AUDIO_EFFECT_INFO, new Object[0]), com.sds.android.ttpod.framework.modules.c.AUDIO_EFFECT);
    }

    private List<String> e(String str) {
        return MediaStorage.queryMediaIDs(sContext, str, com.sds.android.ttpod.framework.storage.environment.b.l(str));
    }

    public Boolean isPickedEffect(String str) {
        return (Boolean) this.d.get(str);
    }
}

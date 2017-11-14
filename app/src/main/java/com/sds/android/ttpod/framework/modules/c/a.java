package com.sds.android.ttpod.framework.modules.c;

import com.sds.android.cloudapi.ttpod.a.h;
import com.sds.android.cloudapi.ttpod.b.d;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.b.l;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.modules.c;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* FeedbackModule */
public class a extends b {
    private boolean a;
    private boolean b;

    protected c id() {
        return c.FEEDBACK;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.PROPOSAL_FEEDBACK, i.a(cls, "proposalFeedback", FeedbackItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.REQUEST_FEEDBACK_LIST, i.a(cls, "requestFeedbackList", Long.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.REQUEST_FEEDBACK_MESSAGES, i.a(cls, "requestFeedbackMessages", String.class, Long.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEND_FEEDBACK_MESSAGE, i.a(cls, "sendFeedbackMessage", FeedbackMessage.class));
        map.put(com.sds.android.ttpod.framework.modules.a.REQUEST_NEW_REPLYIED_FEEDBACKS, i.a(cls, "requestNewRepliedFeedbacks", Long.class));
    }

    public void proposalFeedback(final FeedbackItem feedbackItem) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                com.sds.android.sdk.lib.b.b dVar = new d(h.a(feedbackItem.getProposalContent(), EnvironmentUtils.c.f(), feedbackItem.getContactWay()).d());
                Object obj = null;
                if (dVar.isSuccess()) {
                    String location = dVar.getLocation();
                    String substring = location.substring(location.lastIndexOf(47) + 1);
                    obj = h.a(substring);
                    if (obj == null) {
                        obj = feedbackItem;
                        obj.setId(substring);
                        obj.setLastUpdated(System.currentTimeMillis());
                    }
                    Map p = com.sds.android.ttpod.framework.storage.a.a.a().p();
                    p.put(substring, obj);
                    com.sds.android.ttpod.framework.storage.a.a.a().a(p);
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PROPOSAL_FEEDBACK_FINISH, dVar, obj), c.FEEDBACK);
            }
        });
    }

    public void requestFeedbackList(final Long l, Integer num) {
        if (!this.a) {
            this.a = true;
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    l a = h.a(l, null);
                    long currentTimeMillis = System.currentTimeMillis();
                    com.sds.android.cloudapi.ttpod.b.b bVar = new com.sds.android.cloudapi.ttpod.b.b(a.d());
                    ArrayList arrayList = new ArrayList();
                    if (bVar.isSuccess() && !m.a(bVar.getContent())) {
                        List<FeedbackItem> a2 = bVar.a();
                        if (a2.size() > 0) {
                            long lastUpdated = ((FeedbackItem) a2.get(0)).getLastUpdated();
                            currentTimeMillis = lastUpdated;
                            for (FeedbackItem feedbackItem : a2) {
                                if (currentTimeMillis < feedbackItem.getLastUpdated()) {
                                    lastUpdated = feedbackItem.getLastUpdated();
                                } else {
                                    lastUpdated = currentTimeMillis;
                                }
                                currentTimeMillis = lastUpdated;
                            }
                        }
                        com.sds.android.ttpod.framework.storage.environment.b.e(currentTimeMillis);
                        this.b.a((List) a2, false);
                        arrayList = new ArrayList(com.sds.android.ttpod.framework.storage.a.a.a().p().values());
                    }
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_FEEDBACK_LIST_FINISH, bVar, arrayList, Boolean.valueOf(this.b.b)), c.FEEDBACK);
                    this.b.a = false;
                    this.b.b = false;
                }
            });
        }
    }

    public void requestFeedbackMessages(final String str, final Long l, Boolean bool) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                boolean z;
                com.sds.android.cloudapi.ttpod.b.a aVar = new com.sds.android.cloudapi.ttpod.b.a(h.a(str, l, null).d());
                ArrayList arrayList = new ArrayList();
                if (aVar.isSuccess() && !m.a(aVar.getContent())) {
                    arrayList = aVar.a();
                    Collections.sort(arrayList, new Comparator<FeedbackMessage>(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return a((FeedbackMessage) obj, (FeedbackMessage) obj2);
                        }

                        public int a(FeedbackMessage feedbackMessage, FeedbackMessage feedbackMessage2) {
                            return a(feedbackMessage.getTimestamp(), feedbackMessage2.getTimestamp());
                        }

                        private int a(long j, long j2) {
                            if (j > j2) {
                                return 1;
                            }
                            if (j == j2) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                }
                com.sds.android.ttpod.framework.base.a.b a = com.sds.android.ttpod.framework.base.a.b.a();
                com.sds.android.ttpod.framework.modules.a aVar2 = com.sds.android.ttpod.framework.modules.a.REQUEST_FEEDBACK_MESSAGES_FINISH;
                Object[] objArr = new Object[3];
                objArr[0] = aVar;
                objArr[1] = arrayList;
                if (l != null) {
                    z = true;
                } else {
                    z = false;
                }
                objArr[2] = Boolean.valueOf(z);
                a.b(new com.sds.android.ttpod.framework.base.a.a(aVar2, objArr), c.FEEDBACK);
            }
        });
    }

    public void sendFeedbackMessage(final FeedbackMessage feedbackMessage) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                com.sds.android.cloudapi.ttpod.b.c cVar = new com.sds.android.cloudapi.ttpod.b.c(h.b(feedbackMessage.getThreadId(), feedbackMessage.getType(), feedbackMessage.getContent()).d());
                feedbackMessage.setMsgSource(0);
                feedbackMessage.setTimestamp(System.currentTimeMillis());
                Map p = com.sds.android.ttpod.framework.storage.a.a.a().p();
                ((FeedbackItem) p.get(feedbackMessage.getThreadId())).setLastUpdated(feedbackMessage.getTimestamp());
                com.sds.android.ttpod.framework.storage.a.a.a().a(p);
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SEND_FEEDBACK_MESSAGE_FINISH, cVar, feedbackMessage), c.FEEDBACK);
            }
        });
    }

    public void requestNewRepliedFeedbacks(final Long l) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                com.sds.android.cloudapi.ttpod.b.b bVar = new com.sds.android.cloudapi.ttpod.b.b(h.a(l).d());
                if (bVar.isSuccess()) {
                    List a = bVar.a();
                    if (a.size() != 0) {
                        this.b.a(a, true);
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.NEW_REPLYIED_FEEDBACKS_RECEIVED, a), c.FEEDBACK);
                    }
                }
            }
        });
    }

    private void a(List<FeedbackItem> list, boolean z) {
        Map p = com.sds.android.ttpod.framework.storage.a.a.a().p();
        Map hashMap = p == null ? new HashMap() : p;
        for (FeedbackItem feedbackItem : list) {
            FeedbackItem feedbackItem2 = (FeedbackItem) hashMap.get(feedbackItem.getId());
            if (feedbackItem2 == null || feedbackItem.getLastUpdated() > feedbackItem2.getLastUpdated()) {
                this.b = true;
                if (z) {
                    feedbackItem.setUnread(true);
                }
            }
            hashMap.put(feedbackItem.getId(), feedbackItem);
        }
        com.sds.android.ttpod.framework.storage.a.a.a().a(hashMap);
    }
}

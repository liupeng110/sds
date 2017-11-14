package com.ut.mini;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.ut.mini.b.a;
import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.d.g;
import com.ut.mini.d.m;
import java.util.HashMap;
import java.util.Map;

public class UTHitBuilders {

    public static class UTHitBuilder {
        public static final String FIELD_ARG1 = "_field_arg1";
        public static final String FIELD_ARG2 = "_field_arg2";
        public static final String FIELD_ARG3 = "_field_arg3";
        public static final String FIELD_ARGS = "_field_args";
        public static final String FIELD_EVENT_ID = "_field_event_id";
        public static final String FIELD_PAGE = "_field_page";
        private Map<String, String> mHitMap = new HashMap();

        public UTHitBuilder() {
            if (!this.mHitMap.containsKey(FIELD_PAGE)) {
                this.mHitMap.put(FIELD_PAGE, "UT");
            }
        }

        public UTHitBuilder setProperty(String str, String str2) {
            if (m.a(str) || str2 == null) {
                a.c(1, "setProperty", "key is null or key is empty or value is null,please check it!");
            } else {
                if (this.mHitMap.containsKey(str)) {
                    this.mHitMap.remove(str);
                }
                this.mHitMap.put(str, str2);
            }
            return this;
        }

        public UTHitBuilder setProperties(Map<String, String> map) {
            if (map != null) {
                this.mHitMap.putAll(map);
            }
            return this;
        }

        public String getProperty(String str) {
            if (str == null || !this.mHitMap.containsKey(str)) {
                return null;
            }
            return (String) this.mHitMap.get(str);
        }

        public Map<String, String> build() {
            Map<String, String> hashMap = new HashMap();
            hashMap.putAll(this.mHitMap);
            if (!_checkIlleagleProperty(hashMap)) {
                return null;
            }
            _dropAllIllegalFields(hashMap);
            _translateFieldsName(hashMap);
            if (hashMap.containsKey(UTLogFieldsScheme.EVENTID.toString())) {
                return hashMap;
            }
            return null;
        }

        private static boolean _checkIlleagleProperty(Map<String, String> map) {
            if (map != null) {
                if (map.containsKey(null)) {
                    map.remove(null);
                }
                if (map.containsKey("")) {
                    map.remove("");
                }
                if (map.containsKey(UTLogFieldsScheme.PAGE.toString())) {
                    a.c(1, "checkIlleagleProperty", "IlleaglePropertyKey(PAGE) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                } else if (map.containsKey(UTLogFieldsScheme.EVENTID.toString())) {
                    a.c(1, "checkIlleagleProperty", "IlleaglePropertyKey(EVENTID) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                } else if (map.containsKey(UTLogFieldsScheme.ARG1.toString())) {
                    a.c(1, "checkIlleagleProperty", "IlleaglePropertyKey(ARG1) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                } else if (map.containsKey(UTLogFieldsScheme.ARG2.toString())) {
                    a.c(1, "checkIlleagleProperty", "IlleaglePropertyKey(ARG2) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                } else if (map.containsKey(UTLogFieldsScheme.ARG3.toString())) {
                    a.c(1, "checkIlleagleProperty", "IlleaglePropertyKey(ARG3) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                }
            }
            return true;
        }

        private static void _translateFieldsName(Map<String, String> map) {
            if (map != null) {
                String str;
                if (map.containsKey(FIELD_PAGE)) {
                    str = (String) map.get(FIELD_PAGE);
                    map.remove(FIELD_PAGE);
                    map.put(UTLogFieldsScheme.PAGE.toString(), str);
                }
                if (map.containsKey(FIELD_ARG1)) {
                    str = (String) map.get(FIELD_ARG1);
                    map.remove(FIELD_ARG1);
                    map.put(UTLogFieldsScheme.ARG1.toString(), str);
                }
                if (map.containsKey(FIELD_ARG2)) {
                    str = (String) map.get(FIELD_ARG2);
                    map.remove(FIELD_ARG2);
                    map.put(UTLogFieldsScheme.ARG2.toString(), str);
                }
                if (map.containsKey(FIELD_ARG3)) {
                    str = (String) map.get(FIELD_ARG3);
                    map.remove(FIELD_ARG3);
                    map.put(UTLogFieldsScheme.ARG3.toString(), str);
                }
                if (map.containsKey(FIELD_ARGS)) {
                    str = (String) map.get(FIELD_ARGS);
                    map.remove(FIELD_ARGS);
                    map.put(UTLogFieldsScheme.ARGS.toString(), str);
                }
                if (map.containsKey(FIELD_EVENT_ID)) {
                    str = (String) map.get(FIELD_EVENT_ID);
                    map.remove(FIELD_EVENT_ID);
                    map.put(UTLogFieldsScheme.EVENTID.toString(), str);
                }
            }
        }

        private static void _dropAllIllegalFields(Map<String, String> map) {
            if (map != null) {
                if (map.containsKey(UTLogFieldsScheme.PAGE.toString())) {
                    map.remove(UTLogFieldsScheme.PAGE.toString());
                }
                if (map.containsKey(UTLogFieldsScheme.EVENTID.toString())) {
                    map.remove(UTLogFieldsScheme.EVENTID.toString());
                }
                if (map.containsKey(UTLogFieldsScheme.ARG1.toString())) {
                    map.remove(UTLogFieldsScheme.ARG1.toString());
                }
                if (map.containsKey(UTLogFieldsScheme.ARG2.toString())) {
                    map.remove(UTLogFieldsScheme.ARG2.toString());
                }
                if (map.containsKey(UTLogFieldsScheme.ARG3.toString())) {
                    map.remove(UTLogFieldsScheme.ARG3.toString());
                }
                if (map.containsKey(UTLogFieldsScheme.ARGS.toString())) {
                    map.remove(UTLogFieldsScheme.ARGS.toString());
                }
            }
        }
    }

    public static class UTCustomHitBuilder extends UTHitBuilder {
        public UTCustomHitBuilder(String str) {
            if (!m.a(str)) {
                super.setProperty(UTHitBuilder.FIELD_ARG1, str);
            }
            super.setProperty(UTHitBuilder.FIELD_EVENT_ID, "19999");
            super.setProperty(UTHitBuilder.FIELD_ARG3, FeedbackItem.STATUS_WAITING);
        }

        public UTCustomHitBuilder setDurationOnEvent(long j) {
            if (j < 0) {
                j = 0;
            }
            super.setProperty(UTHitBuilder.FIELD_ARG3, "" + j);
            return this;
        }

        public UTCustomHitBuilder setEventPage(String str) {
            if (!m.a(str)) {
                super.setProperty(UTHitBuilder.FIELD_PAGE, str);
            }
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> build = super.build();
            if (build != null) {
                String str = (String) build.get(UTLogFieldsScheme.PAGE.toString());
                String str2 = (String) build.get(UTLogFieldsScheme.ARG1.toString());
                if (str2 != null) {
                    build.remove(UTLogFieldsScheme.ARG1.toString());
                    build.remove(UTLogFieldsScheme.PAGE.toString());
                    build = g.a(build);
                    build.put(UTLogFieldsScheme.ARG1.toString(), str2);
                    build.put(UTLogFieldsScheme.PAGE.toString(), str);
                    return build;
                }
            }
            return build;
        }
    }

    public static class UTPageHitBuilder extends UTHitBuilder {
        public UTPageHitBuilder(String str) {
            if (!m.a(str)) {
                super.setProperty(UTHitBuilder.FIELD_PAGE, str);
            }
            super.setProperty(UTHitBuilder.FIELD_EVENT_ID, "2001");
            super.setProperty(UTHitBuilder.FIELD_ARG3, FeedbackItem.STATUS_WAITING);
        }

        public UTPageHitBuilder setReferPage(String str) {
            if (!m.a(str)) {
                super.setProperty(UTHitBuilder.FIELD_ARG1, str);
            }
            return this;
        }

        public UTPageHitBuilder setDurationOnPage(long j) {
            if (j < 0) {
                j = 0;
            }
            super.setProperty(UTHitBuilder.FIELD_ARG3, "" + j);
            return this;
        }
    }
}

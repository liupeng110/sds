package com.ttfm.android.sdk.entity;

import com.ttfm.android.sdk.core.GlobalEnv;
import java.util.ArrayList;

public class DSDInfoResult {
    private int code;
    private DSDData data = new DSDData();
    private String msg;
    private long time;

    private class DSDData {
        private DSDServices FMCenterApiUrl;
        private DSDServices FMPlayStream;
        private DSDServices FMPushApiUrl;
        private DSDServices FMWeather;
        private DSDServices FMWebStationsApiUrl;
        private DSDServices SongSearchApiUrl;
        private DSDServices UserApiUrl;

        private DSDData() {
            this.SongSearchApiUrl = null;
            this.UserApiUrl = null;
            this.FMCenterApiUrl = null;
            this.FMPlayStream = null;
            this.FMWeather = null;
            this.FMWebStationsApiUrl = null;
            this.FMPushApiUrl = null;
        }
    }

    private class DSDServices {
        private String filter = "";
        private ArrayList<String> services = new ArrayList();

        private DSDServices() {
        }

        public String getUrl(long j) {
            String str = "";
            long size = (long) this.services.size();
            if (size <= 0) {
                return str;
            }
            if (this.filter.length() <= 0) {
                return (String) this.services.get(0);
            }
            return (String) this.services.get((int) (j % size));
        }

        public String getUrl() {
            String str = "";
            if (this.services.size() > 0) {
                return (String) this.services.get(0);
            }
            return str;
        }
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public boolean isSuccess() {
        return 200 == this.code;
    }

    public String getSongSearchApiUrl() {
        if (this.data.SongSearchApiUrl != null) {
            return this.data.SongSearchApiUrl.getUrl();
        }
        return null;
    }

    public String getUserApiUrl() {
        if (this.data.UserApiUrl != null) {
            return this.data.UserApiUrl.getUrl();
        }
        return null;
    }

    public String getFMCenterApiUrl() {
        String str = GlobalEnv.FMDNSCenterUrl;
        if (this.data.FMCenterApiUrl != null) {
            return this.data.FMCenterApiUrl.getUrl();
        }
        return str;
    }

    public String getFMPlayStreamUrl() {
        String str = GlobalEnv.FMDNSPlayStream;
        if (this.data.FMPlayStream != null) {
            return this.data.FMPlayStream.getUrl();
        }
        return str;
    }

    public String getFMWeatherUrl() {
        if (this.data.FMWeather != null) {
            return this.data.FMWeather.getUrl();
        }
        return null;
    }

    public String getFMWebStationUrl() {
        if (this.data.FMWebStationsApiUrl != null) {
            return this.data.FMWebStationsApiUrl.getUrl();
        }
        return null;
    }

    public String getFMPushApiUrl() {
        if (this.data.FMPushApiUrl != null) {
            return this.data.FMPushApiUrl.getUrl();
        }
        return null;
    }

    public String getFMPlayStreamUrl(long j) {
        String str = GlobalEnv.FMDNSPlayStream;
        if (this.data.FMPlayStream != null) {
            return this.data.FMPlayStream.getUrl(j);
        }
        return str;
    }

    public String getFMPlayStreamUrlFilter() {
        String str = "";
        if (this.data.FMPlayStream != null) {
            return this.data.FMPlayStream.filter;
        }
        return str;
    }
}

package com.taobao.wireless.security.adapter.datacollection;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;

@TargetApi(18)
public class DeviceInfoHelper {
    private DeviceInfoHelper() {
    }

    public static String a(CellInfo cellInfo) {
        String str;
        int basestationId;
        int latitude;
        int longitude;
        int systemId;
        if (cellInfo instanceof CellInfoCdma) {
            str = "CDMA";
            CellIdentityCdma cellIdentity = ((CellInfoCdma) cellInfo).getCellIdentity();
            if (cellIdentity == null) {
                return null;
            }
            basestationId = cellIdentity.getBasestationId();
            latitude = cellIdentity.getLatitude();
            longitude = cellIdentity.getLongitude();
            int networkId = cellIdentity.getNetworkId();
            systemId = cellIdentity.getSystemId();
            return String.format("{\"cellType\":\"%s\", \"param\":{\"lat\":%d, \"lon\":%d, \"bid\":%d, \"nid\":%d, \"sid\":%d}}", new Object[]{str, Integer.valueOf(latitude), Integer.valueOf(longitude), Integer.valueOf(basestationId), Integer.valueOf(networkId), Integer.valueOf(systemId)});
        } else if (cellInfo instanceof CellInfoWcdma) {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            str = "WCDMA";
            if (VERSION.SDK_INT < 18) {
                return null;
            }
            CellIdentityWcdma cellIdentity2 = cellInfoWcdma.getCellIdentity();
            if (cellIdentity2 == null) {
                return null;
            }
            basestationId = cellIdentity2.getCid();
            latitude = cellIdentity2.getLac();
            longitude = cellIdentity2.getMnc();
            systemId = cellIdentity2.getMcc();
            return String.format("{\"cellType\":\"%s\", \"param\":{\"lac\":%d, \"cid\":%d, \"mnc\":%d, \"mcc\":%d}}", new Object[]{str, Integer.valueOf(latitude), Integer.valueOf(basestationId), Integer.valueOf(longitude), Integer.valueOf(systemId)});
        } else if (cellInfo instanceof CellInfoGsm) {
            str = "GSM";
            CellIdentityGsm cellIdentity3 = ((CellInfoGsm) cellInfo).getCellIdentity();
            if (cellIdentity3 == null) {
                return null;
            }
            basestationId = cellIdentity3.getCid();
            latitude = cellIdentity3.getLac();
            longitude = cellIdentity3.getMnc();
            systemId = cellIdentity3.getMcc();
            return String.format("{\"cellType\":\"%s\", \"param\":{\"lac\":%d, \"cid\":%d, \"mnc\":%d, \"mcc\":%d}}", new Object[]{str, Integer.valueOf(latitude), Integer.valueOf(basestationId), Integer.valueOf(longitude), Integer.valueOf(systemId)});
        } else if (!(cellInfo instanceof CellInfoLte)) {
            return null;
        } else {
            str = "LTE";
            CellIdentityLte cellIdentity4 = ((CellInfoLte) cellInfo).getCellIdentity();
            if (cellIdentity4 == null) {
                return null;
            }
            basestationId = cellIdentity4.getCi();
            latitude = cellIdentity4.getTac();
            longitude = cellIdentity4.getMnc();
            systemId = cellIdentity4.getMcc();
            return String.format("{\"cellType\":\"%s\", \"param\":{\"tac\":%d, \"cid\":%d, \"mnc\":%d, \"mcc\":%d}}", new Object[]{str, Integer.valueOf(latitude), Integer.valueOf(basestationId), Integer.valueOf(longitude), Integer.valueOf(systemId)});
        }
    }
}

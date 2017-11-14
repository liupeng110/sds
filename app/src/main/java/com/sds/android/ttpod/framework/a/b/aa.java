package com.sds.android.ttpod.framework.a.b;

import com.alibaba.wireless.security.SecExceptionCode;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.KVStatisticEvent.ValueOperateMode;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import java.io.Serializable;

/* UnicomFlowStatistic */
public class aa {

    /* UnicomFlowStatistic */
    public enum a implements Serializable {
        TRIAL_DETAIL,
        ORDER_DETAIL
    }

    /* UnicomFlowStatistic */
    public enum b implements Serializable {
        POP_OPEN_DIALOG,
        POP_MONTH_DIALOG,
        ORDER_DETAIL,
        UNSUBSCRIBE_DETAIL,
        TRIAL_DETAIL,
        OPEN_AUTHORIZE
    }

    /* UnicomFlowStatistic */
    public enum c implements Serializable {
        TRIAL_DIALOG,
        ORDER_DETAIL
    }

    public static void a() {
        w.a(178, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void b() {
        w.a(180, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void c() {
        w.a(179, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void d() {
        w.a(181, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void a(com.sds.android.ttpod.framework.modules.h.b bVar) {
        w.a(182, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void e() {
        w.a(373, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void f() {
        w.a(374, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void g() {
        w.a(375, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void h() {
        w.a(376, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void i() {
        w.a(377, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void b(com.sds.android.ttpod.framework.modules.h.b bVar) {
        w.a(183, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void c(com.sds.android.ttpod.framework.modules.h.b bVar) {
        w.a(184, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void j() {
        w.a(185, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void k() {
        w.a(371, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void l() {
        w.a(372, (int) StatisticHelper.DELAY_SEND, "close_home_unicom_entry", ValueOperateMode.UNIQUE);
    }

    public static void m() {
        w.a(370, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void n() {
        w.a(187, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void o() {
        w.a(188, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void p() {
        w.a(189, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void q() {
        w.a(186, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void a(c cVar) {
        w.a((int) Downloads.STATUS_PENDING, (int) StatisticHelper.DELAY_SEND, (Enum) cVar, ValueOperateMode.UNIQUE);
    }

    public static void b(c cVar) {
        w.a((int) Downloads.STATUS_PENDING_PAUSED, (int) StatisticHelper.DELAY_SEND, (Enum) cVar, ValueOperateMode.UNIQUE);
    }

    public static void c(c cVar) {
        w.a((int) Downloads.STATUS_RUNNING, (int) StatisticHelper.DELAY_SEND, (Enum) cVar, ValueOperateMode.UNIQUE);
    }

    public static void d(c cVar) {
        w.a((int) Downloads.STATUS_RUNNING_PAUSED, (int) StatisticHelper.DELAY_SEND, (Enum) cVar, ValueOperateMode.UNIQUE);
    }

    public static void e(c cVar) {
        w.a(194, (int) StatisticHelper.DELAY_SEND, (Enum) cVar, ValueOperateMode.UNIQUE);
    }

    public static void f(c cVar) {
        w.a(195, (int) StatisticHelper.DELAY_SEND, (Enum) cVar, ValueOperateMode.UNIQUE);
    }

    public static void g(c cVar) {
        w.a(196, (int) StatisticHelper.DELAY_SEND, (Enum) cVar, ValueOperateMode.UNIQUE);
    }

    public static void a(a aVar) {
        w.a(197, (int) StatisticHelper.DELAY_SEND, (Enum) aVar, ValueOperateMode.UNIQUE);
    }

    public static void b(a aVar) {
        w.a(198, (int) StatisticHelper.DELAY_SEND, (Enum) aVar, ValueOperateMode.UNIQUE);
    }

    public static void c(a aVar) {
        w.a((int) SecExceptionCode.SEC_ERROR_INIT_UNKNOWN_ERROR, (int) StatisticHelper.DELAY_SEND, (Enum) aVar, ValueOperateMode.UNIQUE);
    }

    public static void a(b bVar) {
        w.a(200, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void b(b bVar) {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_STORE_INVALID_PARAM, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void c(b bVar) {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_STORE_DATA_FILE_MISMATCH, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void d(b bVar) {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_STORE_NO_DATA_FILE, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void e(b bVar) {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_STORE_INCORRECT_DATA_FILE, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void f(b bVar) {
        w.a(329, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void g(b bVar) {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_STORE_INCORRECT_DATA_FILE_DATA, (int) StatisticHelper.DELAY_SEND, (Enum) bVar, ValueOperateMode.UNIQUE);
    }

    public static void r() {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_STORE_KEY_NOT_EXSITED, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void s() {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_STORE_ILLEGEL_KEY, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void t() {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_STORE_NO_MEMORY, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void u() {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_STORE_INDEX_NOT_EXISTED, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void v() {
        w.a(210, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void w() {
        w.a(211, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void x() {
        w.a(212, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void y() {
        w.a(290, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void z() {
        w.a(291, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void A() {
        w.a(292, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void B() {
        w.a(293, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void C() {
        w.a(326, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void D() {
        w.a(328, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void E() {
        w.a(327, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void F() {
        w.a(331, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void G() {
        w.a(332, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void H() {
        w.a(336, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void I() {
        w.a(337, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void J() {
        w.a(338, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void K() {
        w.a(334, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void a(long j) {
        w.a(333, (int) StatisticHelper.DELAY_SEND, j, ValueOperateMode.ADD);
    }

    public static void L() {
        w.a(335, (int) StatisticHelper.DELAY_SEND, 1);
    }
}

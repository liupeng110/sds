package com.sds.android.ttpod.activities.musiccircle;

import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.ttpod.framework.a.b.w;
import com.sina.weibo.sdk.component.WidgetRequestParam;

/* MusicCircleStatistic */
public class c {
    public static void a() {
        w.a("musicCircle", "user-info", "entry");
    }

    public static void b() {
        w.a("musicCircle", "user-info", "cover");
    }

    public static void c() {
        w.a("musicCircle", "user-info", "avatar");
    }

    public static void d() {
        w.a("musicCircle", "user-info", "nickname");
    }

    public static void e() {
        w.a("musicCircle", "user-info", User.KEY_BIRTHDAY);
    }

    public static void a(long j, String str) {
        w.a("musicCircle", "my_space", "play", j, 0, str, null);
    }

    public static void b(long j, String str) {
        w.a("musicCircle", "my_space", "detail", j, 0, str, null);
    }

    public static void c(long j, String str) {
        w.a("musicCircle", "my_space", "collect", j, 0, str, null);
    }

    public static void f() {
        w.a("musicCircle", "social", "find_friend_entry");
    }

    public static void g() {
        w.a("musicCircle", "social", "radar_entry");
    }

    public static void h() {
        w.a("musicCircle", "social", "radar_back");
    }

    public static void i() {
        w.a("musicCircle", "social", "radar_restart");
    }

    public static void a(int i) {
        w.a("musicCircle", "social", "radar_success", (long) i, 0);
    }

    public static void j() {
        w.a("musicCircle", "social", "shake_entry");
    }

    public static void k() {
        w.a("musicCircle", "social", "shake_start");
    }

    public static void l() {
        w.a("musicCircle", "social", "shake_back");
    }

    public static void m() {
        w.a("musicCircle", "social", "search_entry");
    }

    public static void n() {
        w.a("musicCircle", "social", "search-button");
    }

    public static void o() {
        w.a("musicCircle", "social", "search_back");
    }

    public static void p() {
        w.a("musicCircle", "social", "recommend");
    }

    public static void b(int i) {
        w.a("musicCircle", "social", "recommend_followings", (long) i, 0);
    }

    public static void q() {
        w.a("musicCircle", "social", "recommend_userspace");
    }

    public static void r() {
        w.a("musicCircle", "social", "recommend_userspace_play");
    }

    public static void s() {
        w.a("musicCircle", "social", "rank");
    }

    public static void a(String str) {
        w.a("musicCircle", "social", "rank_sub", 0, 0, str, null);
    }

    public static void a(int i, String str) {
        w.a("musicCircle", "social", "rank_followings", (long) i, 0, str, null);
    }

    public static void b(String str) {
        w.a("musicCircle", "social", "rank_userspace", 0, 0, str, null);
    }

    public static void c(String str) {
        w.a("musicCircle", "social", "rank_userspace_play", 0, 0, str, null);
    }

    public static void t() {
        w.a("musicCircle", "social", WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY);
    }

    public static void d(String str) {
        w.a("musicCircle", "social", "category_sub", 0, 0, str, null);
    }

    public static void b(int i, String str) {
        w.a("musicCircle", "social", "category_followings", (long) i, 0, str, null);
    }

    public static void e(String str) {
        w.a("musicCircle", "social", "category_userspace", 0, 0, str, null);
    }

    public static void f(String str) {
        w.a("musicCircle", "social", "category_userspace_play", 0, 0, str, null);
    }
}

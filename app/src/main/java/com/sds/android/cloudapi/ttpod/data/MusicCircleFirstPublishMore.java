package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.util.ArrayList;

public class MusicCircleFirstPublishMore {
    private static final String KEY_ALBUMS = "albums";
    private static final String KEY_SINGLES = "singles";
    @c(a = "albums")
    private ArrayList<WeekAlbum> mAlbumList;
    @c(a = "singles")
    private ArrayList<MusicCircleFirstPublish> mSingleList;

    public static class WeekAlbum {
        private static final String KEY_ALBUM = "album";
        private static final String KEY_WEEK = "week";
        private static final String KEY_YEAR = "year";
        @c(a = "week")
        private int mWeek;
        @c(a = "album")
        private ArrayList<MusicCircleFirstPublish> mWeekAlbumList;
        @c(a = "year")
        private int mYear;

        public int getYear() {
            return this.mYear;
        }

        public int getWeek() {
            return this.mWeek;
        }

        public ArrayList<MusicCircleFirstPublish> getWeekAlbumList() {
            return this.mWeekAlbumList;
        }
    }

    public ArrayList<MusicCircleFirstPublish> getSingleList() {
        return this.mSingleList;
    }

    public ArrayList<WeekAlbum> getAlbumList() {
        return this.mAlbumList;
    }
}

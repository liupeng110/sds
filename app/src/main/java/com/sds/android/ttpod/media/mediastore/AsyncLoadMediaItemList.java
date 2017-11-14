package com.sds.android.ttpod.media.mediastore;

import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sds.android.sdk.lib.util.EnvironmentUtils.a;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class AsyncLoadMediaItemList implements List<MediaItem> {
    private static final String TAG = "AsyncLoadMediaItemList";
    private volatile Cursor mCursor;
    private final String mGroupID;
    private Handler mHandler;
    private long mLastAccessTimeStamp;
    private volatile boolean mLoadFinished = false;
    private volatile Set<OnLoadFinishedListener> mLoadFinishedListeners = new HashSet();
    private boolean mLoadingStarted = false;
    private final List<MediaItem> mMediaItemArray = new ArrayList();
    private final List<Integer> mMediaItemIndexArray = new ArrayList();
    private final String mOrderBy;
    private ReentrantLock mReentrantLock = new ReentrantLock();
    private int mRefCount;

    public interface OnLoadFinishedListener {
        void onLoadFinished();
    }

    AsyncLoadMediaItemList(Cursor cursor, String str, String str2) {
        int i = 0;
        this.mCursor = cursor;
        this.mGroupID = str;
        this.mOrderBy = str2;
        this.mRefCount = 1;
        if (this.mCursor != null) {
            int count = cursor.getCount();
            while (i < count) {
                this.mMediaItemArray.add(null);
                this.mMediaItemIndexArray.add(Integer.valueOf(i));
                i++;
            }
        }
        this.mHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                AsyncLoadMediaItemList.this.extractCursor();
            }
        };
    }

    public void addLoadFinishedListener(OnLoadFinishedListener onLoadFinishedListener) {
        if (onLoadFinishedListener != null && !this.mLoadFinishedListeners.contains(onLoadFinishedListener)) {
            this.mLoadFinishedListeners.add(onLoadFinishedListener);
        }
    }

    public void removeLoadFinishedListener(OnLoadFinishedListener onLoadFinishedListener) {
        if (this.mLoadFinishedListeners.contains(onLoadFinishedListener)) {
            this.mLoadFinishedListeners.remove(onLoadFinishedListener);
        }
    }

    public void add(int i, MediaItem mediaItem) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        this.mMediaItemArray.add(i, mediaItem);
        this.mReentrantLock.unlock();
    }

    public boolean add(MediaItem mediaItem) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        boolean add = this.mMediaItemArray.add(mediaItem);
        this.mReentrantLock.unlock();
        return add;
    }

    public boolean addAll(int i, Collection<? extends MediaItem> collection) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        boolean addAll = this.mMediaItemArray.addAll(i, collection);
        this.mReentrantLock.unlock();
        return addAll;
    }

    public boolean addAll(Collection<? extends MediaItem> collection) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        boolean addAll = this.mMediaItemArray.addAll(collection);
        this.mReentrantLock.unlock();
        return addAll;
    }

    public void clear() {
        d.a();
        int i = this.mRefCount - 1;
        this.mRefCount = i;
        if (i <= 0) {
            this.mLoadFinishedListeners.clear();
            closeCursor();
            this.mReentrantLock.lock();
            this.mMediaItemArray.clear();
            this.mReentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        this.mReentrantLock.lock();
        if (this.mMediaItemArray.contains(obj)) {
            this.mReentrantLock.unlock();
            return true;
        }
        this.mReentrantLock.unlock();
        waitForLoadFinished();
        this.mReentrantLock.lock();
        boolean contains = this.mMediaItemArray.contains(obj);
        this.mReentrantLock.unlock();
        return contains;
    }

    public boolean containsAll(Collection<?> collection) {
        this.mReentrantLock.lock();
        if (this.mMediaItemArray.contains(collection)) {
            this.mReentrantLock.unlock();
            return true;
        }
        this.mReentrantLock.unlock();
        waitForLoadFinished();
        this.mReentrantLock.lock();
        boolean contains = this.mMediaItemArray.contains(collection);
        this.mReentrantLock.unlock();
        return contains;
    }

    public boolean equals(Object obj) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        boolean equals = this.mMediaItemArray.equals(obj);
        this.mReentrantLock.unlock();
        return equals;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public int indexOf(Object obj) {
        this.mReentrantLock.lock();
        if (this.mMediaItemArray.contains(obj)) {
            int indexOf = this.mMediaItemArray.indexOf(obj);
            this.mReentrantLock.unlock();
            return indexOf;
        }
        this.mReentrantLock.unlock();
        waitForLoadFinished();
        this.mReentrantLock.lock();
        indexOf = this.mMediaItemArray.indexOf(obj);
        this.mReentrantLock.unlock();
        return indexOf;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterator<MediaItem> iterator() {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        Iterator<MediaItem> it = this.mMediaItemArray.iterator();
        this.mReentrantLock.unlock();
        return it;
    }

    public int lastIndexOf(Object obj) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        int lastIndexOf = this.mMediaItemArray.lastIndexOf(obj);
        this.mReentrantLock.unlock();
        return lastIndexOf;
    }

    public ListIterator<MediaItem> listIterator() {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        ListIterator<MediaItem> listIterator = this.mMediaItemArray.listIterator();
        this.mReentrantLock.unlock();
        return listIterator;
    }

    public ListIterator<MediaItem> listIterator(int i) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        ListIterator<MediaItem> listIterator = this.mMediaItemArray.listIterator(i);
        this.mReentrantLock.unlock();
        return listIterator;
    }

    public MediaItem remove(int i) {
        this.mReentrantLock.lock();
        this.mMediaItemIndexArray.remove(new Integer(i));
        MediaItem mediaItem = (MediaItem) this.mMediaItemArray.remove(i);
        this.mReentrantLock.unlock();
        return mediaItem;
    }

    public boolean remove(Object obj) {
        if (obj == null) {
            waitForLoadFinished();
        }
        this.mReentrantLock.lock();
        if (!this.mMediaItemArray.contains(obj)) {
            this.mReentrantLock.unlock();
            waitForLoadFinished();
            this.mReentrantLock.lock();
        }
        int indexOf = this.mMediaItemArray.indexOf(obj);
        if (indexOf >= 0) {
            this.mMediaItemIndexArray.remove(indexOf);
        }
        boolean remove = this.mMediaItemArray.remove(obj);
        this.mReentrantLock.unlock();
        return remove;
    }

    public boolean removeAll(Collection<?> collection) {
        this.mReentrantLock.lock();
        this.mMediaItemIndexArray.clear();
        boolean removeAll = this.mMediaItemArray.removeAll(collection);
        this.mReentrantLock.unlock();
        return removeAll;
    }

    public boolean retainAll(Collection<?> collection) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        boolean retainAll = this.mMediaItemArray.retainAll(collection);
        this.mReentrantLock.unlock();
        return retainAll;
    }

    public MediaItem set(int i, MediaItem mediaItem) {
        this.mReentrantLock.lock();
        MediaItem mediaItem2 = (MediaItem) this.mMediaItemArray.set(i, mediaItem);
        this.mReentrantLock.unlock();
        return mediaItem2;
    }

    public List<MediaItem> subList(int i, int i2) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        List<MediaItem> subList = this.mMediaItemArray.subList(i, i2);
        this.mReentrantLock.unlock();
        return subList;
    }

    public Object[] toArray() {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        Object[] toArray = this.mMediaItemArray.toArray();
        this.mReentrantLock.unlock();
        return toArray;
    }

    public <T> T[] toArray(T[] tArr) {
        waitForLoadFinished();
        this.mReentrantLock.lock();
        T[] toArray = this.mMediaItemArray.toArray(tArr);
        this.mReentrantLock.unlock();
        return toArray;
    }

    public void addRef() {
        this.mReentrantLock.lock();
        d.a();
        this.mRefCount++;
        this.mReentrantLock.unlock();
    }

    private void extractCursorDelay(long j) {
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, j);
    }

    private void extractCursor() {
        this.mHandler.removeMessages(0);
        if (!this.mLoadFinished && !this.mLoadingStarted) {
            this.mLoadingStarted = true;
            Thread anonymousClass2 = new Thread() {
                public void run() {
                    try {
                        int count = AsyncLoadMediaItemList.this.mCursor.getCount();
                        int i = 0;
                        while (i < count) {
                            if (System.currentTimeMillis() - AsyncLoadMediaItemList.this.mLastAccessTimeStamp > 1000) {
                                AsyncLoadMediaItemList.this.mReentrantLock.lock();
                                int i2 = i + 1;
                                AsyncLoadMediaItemList.this.loadMediaItemWithCursorIndex(i);
                                AsyncLoadMediaItemList.this.mReentrantLock.unlock();
                                i = i2;
                            } else {
                                try {
                                    AnonymousClass2.sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    } finally {
                        AsyncLoadMediaItemList.this.closeCursor();
                    }
                    AsyncLoadMediaItemList.this.mLoadFinished = true;
                    AsyncLoadMediaItemList.this.mHandler.post(new Runnable() {
                        public void run() {
                            for (OnLoadFinishedListener onLoadFinished : AsyncLoadMediaItemList.this.mLoadFinishedListeners) {
                                onLoadFinished.onLoadFinished();
                            }
                        }
                    });
                }
            };
            anonymousClass2.setPriority(10);
            anonymousClass2.start();
        }
    }

    public int size() {
        this.mReentrantLock.lock();
        int size = this.mMediaItemArray.size();
        this.mReentrantLock.unlock();
        return size;
    }

    public void preLoad() {
        extractCursor();
    }

    public MediaItem get(int i) {
        assertNotOutOfBound(i);
        this.mLastAccessTimeStamp = System.currentTimeMillis();
        MediaItem mediaItem = null;
        this.mReentrantLock.lock();
        if (this.mMediaItemArray.get(i) == null) {
            extractCursorDelay(1000);
        }
        try {
            mediaItem = readMediaItemWithArrayIndex(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mReentrantLock.unlock();
        return mediaItem == null ? MediaItem.MEDIA_ITEM_NULL : mediaItem;
    }

    public boolean isLoadFinished() {
        return this.mLoadFinished;
    }

    public String getOrderBy() {
        return this.mOrderBy;
    }

    private void assertNotOutOfBound(int i) {
        if (a.j() && i >= size()) {
            throw new IndexOutOfBoundsException("out of bound");
        }
    }

    private void waitForLoadFinished() {
        extractCursor();
        while (!this.mLoadFinished) {
            if (a.j()) {
                g.c(TAG, "WARNING AsyncLoadMediaItemList Sync Loading...");
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void closeCursor() {
        this.mReentrantLock.lock();
        try {
            if (this.mCursor != null) {
                this.mCursor.close();
                this.mCursor = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mReentrantLock.unlock();
    }

    private MediaItem readMediaItemWithArrayIndex(int i) {
        int map2CursorIndex = map2CursorIndex(i);
        if (map2CursorIndex < 0) {
            return null;
        }
        loadMediaItem(map2CursorIndex, i);
        return (MediaItem) this.mMediaItemArray.get(i);
    }

    private void loadMediaItemWithCursorIndex(int i) {
        int map2ArrayIndex = map2ArrayIndex(i);
        if (map2ArrayIndex >= 0) {
            loadMediaItem(i, map2ArrayIndex);
        }
    }

    private void loadMediaItem(int i, int i2) {
        if (this.mMediaItemArray.get(i2) == null && this.mCursor.moveToPosition(i)) {
            this.mMediaItemArray.set(i2, Builder.createMediaItem(this.mCursor, this.mGroupID));
        }
    }

    private int map2CursorIndex(int i) {
        this.mReentrantLock.lock();
        int intValue = (i < 0 || i >= this.mMediaItemIndexArray.size()) ? -1 : ((Integer) this.mMediaItemIndexArray.get(i)).intValue();
        this.mReentrantLock.unlock();
        return intValue;
    }

    private int map2ArrayIndex(int i) {
        this.mReentrantLock.lock();
        int indexOf = this.mMediaItemIndexArray.contains(new Integer(i)) ? this.mMediaItemIndexArray.indexOf(new Integer(i)) : -1;
        this.mReentrantLock.unlock();
        return indexOf;
    }
}

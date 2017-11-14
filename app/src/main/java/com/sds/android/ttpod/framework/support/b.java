package com.sds.android.ttpod.framework.support;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.sds.android.sdk.core.statistic.StatisticEvent;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.List;
import java.util.Map;

/* ISupportService */
public interface b extends IInterface {

    /* ISupportService */
    public static abstract class a extends Binder implements b {

        /* ISupportService */
        private static class a implements b {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public void a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeInt(i);
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float d() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int e() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean a(int[] iArr, int i) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (iArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(iArr.length);
                    }
                    obtain.writeInt(i);
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.readIntArray(iArr);
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean b(int[] iArr, int i) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (iArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(iArr.length);
                    }
                    obtain.writeInt(i);
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.readIntArray(iArr);
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public MediaItem f() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    MediaItem mediaItem;
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        mediaItem = (MediaItem) MediaItem.CREATOR.createFromParcel(obtain2);
                    } else {
                        mediaItem = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return mediaItem;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void g() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void h() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String i() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String j() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(StatisticEvent statisticEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (statisticEvent != null) {
                        obtain.writeInt(1);
                        statisticEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeMap(map);
                    this.a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (downloadTaskInfo != null) {
                        obtain.writeInt(1);
                        downloadTaskInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public DownloadTaskInfo b(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    DownloadTaskInfo downloadTaskInfo2;
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (downloadTaskInfo != null) {
                        obtain.writeInt(1);
                        downloadTaskInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        downloadTaskInfo2 = (DownloadTaskInfo) DownloadTaskInfo.CREATOR.createFromParcel(obtain2);
                    } else {
                        downloadTaskInfo2 = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return downloadTaskInfo2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public DownloadTaskInfo c(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    DownloadTaskInfo downloadTaskInfo2;
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (downloadTaskInfo != null) {
                        obtain.writeInt(1);
                        downloadTaskInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        downloadTaskInfo2 = (DownloadTaskInfo) DownloadTaskInfo.CREATOR.createFromParcel(obtain2);
                    } else {
                        downloadTaskInfo2 = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return downloadTaskInfo2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeString(str);
                    this.a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeString(str);
                    this.a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int d(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (downloadTaskInfo != null) {
                        obtain.writeInt(1);
                        downloadTaskInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, int i, int i2, String str2, String str3, boolean z) throws RemoteException {
                int i3 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (z) {
                        i3 = 1;
                    }
                    obtain.writeInt(i3);
                    this.a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long k() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeLong(j);
                    this.a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<DownloadTaskInfo> a(int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeIntArray(iArr);
                    this.a.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    List<DownloadTaskInfo> createTypedArrayList = obtain2.createTypedArrayList(DownloadTaskInfo.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeLong(j);
                    this.a.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void l() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.a.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.sds.android.ttpod.framework.support.ISupportService");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.sds.android.ttpod.framework.support.ISupportService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof b)) {
                return new a(iBinder);
            }
            return (b) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            DownloadTaskInfo downloadTaskInfo = null;
            boolean z = false;
            int b;
            int readInt;
            int[] iArr;
            boolean a;
            String readString;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    a();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    b = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(b);
                    return true;
                case 3:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    b = c();
                    parcel2.writeNoException();
                    parcel2.writeInt(b);
                    return true;
                case 4:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    float d = d();
                    parcel2.writeNoException();
                    parcel2.writeFloat(d);
                    return true;
                case 6:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    b = e();
                    parcel2.writeNoException();
                    parcel2.writeInt(b);
                    return true;
                case 7:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    readInt = parcel.readInt();
                    if (readInt >= 0) {
                        iArr = new int[readInt];
                    }
                    a = a(iArr, parcel.readInt());
                    parcel2.writeNoException();
                    if (a) {
                        readInt = 1;
                    } else {
                        readInt = 0;
                    }
                    parcel2.writeInt(readInt);
                    parcel2.writeIntArray(iArr);
                    return true;
                case 8:
                    int i3;
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    readInt = parcel.readInt();
                    if (readInt >= 0) {
                        iArr = new int[readInt];
                    }
                    a = b(iArr, parcel.readInt());
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    parcel2.writeIntArray(iArr);
                    return true;
                case 9:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    MediaItem f = f();
                    parcel2.writeNoException();
                    if (f != null) {
                        parcel2.writeInt(1);
                        f.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 10:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    g();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    h();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    a(readString, z);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    readString = i();
                    parcel2.writeNoException();
                    parcel2.writeString(readString);
                    return true;
                case 14:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    readString = j();
                    parcel2.writeNoException();
                    parcel2.writeString(readString);
                    return true;
                case 15:
                    StatisticEvent statisticEvent;
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    if (parcel.readInt() != 0) {
                        statisticEvent = (StatisticEvent) StatisticEvent.CREATOR.createFromParcel(parcel);
                    }
                    a(statisticEvent);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    a(parcel.readHashMap(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    a(z);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    if (parcel.readInt() != 0) {
                        downloadTaskInfo = (DownloadTaskInfo) DownloadTaskInfo.CREATOR.createFromParcel(parcel);
                    }
                    a(downloadTaskInfo);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    if (parcel.readInt() != 0) {
                        downloadTaskInfo = (DownloadTaskInfo) DownloadTaskInfo.CREATOR.createFromParcel(parcel);
                    }
                    downloadTaskInfo = b(downloadTaskInfo);
                    parcel2.writeNoException();
                    if (downloadTaskInfo != null) {
                        parcel2.writeInt(1);
                        downloadTaskInfo.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 20:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    if (parcel.readInt() != 0) {
                        downloadTaskInfo = (DownloadTaskInfo) DownloadTaskInfo.CREATOR.createFromParcel(parcel);
                    }
                    downloadTaskInfo = c(downloadTaskInfo);
                    parcel2.writeNoException();
                    if (downloadTaskInfo != null) {
                        parcel2.writeInt(1);
                        downloadTaskInfo.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 21:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    b(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    if (parcel.readInt() != 0) {
                        downloadTaskInfo = (DownloadTaskInfo) DownloadTaskInfo.CREATOR.createFromParcel(parcel);
                    }
                    b = d(downloadTaskInfo);
                    parcel2.writeNoException();
                    parcel2.writeInt(b);
                    return true;
                case 24:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    String readString2 = parcel.readString();
                    int readInt2 = parcel.readInt();
                    int readInt3 = parcel.readInt();
                    String readString3 = parcel.readString();
                    String readString4 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    a(readString2, readInt2, readInt3, readString3, readString4, z);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    long k = k();
                    parcel2.writeNoException();
                    parcel2.writeLong(k);
                    return true;
                case 26:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    a(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    List a2 = a(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(a2);
                    return true;
                case 28:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    b(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    l();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.sds.android.ttpod.framework.support.ISupportService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    List<DownloadTaskInfo> a(int[] iArr) throws RemoteException;

    void a() throws RemoteException;

    void a(int i) throws RemoteException;

    void a(long j) throws RemoteException;

    void a(StatisticEvent statisticEvent) throws RemoteException;

    void a(DownloadTaskInfo downloadTaskInfo) throws RemoteException;

    void a(String str) throws RemoteException;

    void a(String str, int i, int i2, String str2, String str3, boolean z) throws RemoteException;

    void a(String str, boolean z) throws RemoteException;

    void a(Map map) throws RemoteException;

    void a(boolean z) throws RemoteException;

    boolean a(int[] iArr, int i) throws RemoteException;

    int b() throws RemoteException;

    DownloadTaskInfo b(DownloadTaskInfo downloadTaskInfo) throws RemoteException;

    void b(long j) throws RemoteException;

    void b(String str) throws RemoteException;

    boolean b(int[] iArr, int i) throws RemoteException;

    int c() throws RemoteException;

    DownloadTaskInfo c(DownloadTaskInfo downloadTaskInfo) throws RemoteException;

    float d() throws RemoteException;

    int d(DownloadTaskInfo downloadTaskInfo) throws RemoteException;

    int e() throws RemoteException;

    MediaItem f() throws RemoteException;

    void g() throws RemoteException;

    void h() throws RemoteException;

    String i() throws RemoteException;

    String j() throws RemoteException;

    long k() throws RemoteException;

    void l() throws RemoteException;
}

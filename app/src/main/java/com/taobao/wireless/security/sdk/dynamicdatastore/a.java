package com.taobao.wireless.security.sdk.dynamicdatastore;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;

public final class a implements IDynamicDataStoreComponent {
    private ContextWrapper a;

    public a(ContextWrapper contextWrapper) {
        this.a = contextWrapper;
    }

    public final boolean getBoolean(String str) {
        boolean z = false;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                z = instance.getDynamicDataStoreComp().getBoolean(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return z;
    }

    public final byte[] getByteArray(String str) {
        byte[] bArr = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                bArr = instance.getDynamicDataStoreComp().getByteArray(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return bArr;
    }

    public final float getFloat(String str) {
        float f = 0.0f;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                f = instance.getDynamicDataStoreComp().getFloat(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return f;
    }

    public final int getInt(String str) {
        int i = 0;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                i = instance.getDynamicDataStoreComp().getInt(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return i;
    }

    public final long getLong(String str) {
        long j = 0;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                j = instance.getDynamicDataStoreComp().getLong(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return j;
    }

    public final String getString(String str) {
        String str2 = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                str2 = instance.getDynamicDataStoreComp().getString(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public final int putBoolean(String str, boolean z) {
        int i = 0;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                i = instance.getDynamicDataStoreComp().putBoolean(str, z);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return i;
    }

    public final int putByteArray(String str, byte[] bArr) {
        int i = 0;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                i = instance.getDynamicDataStoreComp().putByteArray(str, bArr);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return i;
    }

    public final int putFloat(String str, float f) {
        int i = 0;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                i = instance.getDynamicDataStoreComp().putFloat(str, f);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return i;
    }

    public final int putInt(String str, int i) {
        int i2 = 0;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                i2 = instance.getDynamicDataStoreComp().putInt(str, i);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return i2;
    }

    public final int putLong(String str, long j) {
        int i = 0;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                i = instance.getDynamicDataStoreComp().putLong(str, j);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return i;
    }

    public final int putString(String str, String str2) {
        int i = 0;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                i = instance.getDynamicDataStoreComp().putString(str, str2);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return i;
    }

    public final void removeBoolean(String str) {
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                instance.getDynamicDataStoreComp().removeBoolean(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public final void removeByteArray(String str) {
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                instance.getDynamicDataStoreComp().removeByteArray(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public final void removeFloat(String str) {
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                instance.getDynamicDataStoreComp().removeFloat(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public final void removeInt(String str) {
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                instance.getDynamicDataStoreComp().removeInt(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public final void removeLong(String str) {
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                instance.getDynamicDataStoreComp().removeLong(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public final void removeString(String str) {
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                instance.getDynamicDataStoreComp().removeString(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
    }
}

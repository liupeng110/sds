package com.sds.android.ttpod.framework.base.a;

/* Command */
public final class a {
    private com.sds.android.ttpod.framework.modules.a a;
    private Object[] b;

    public a(com.sds.android.ttpod.framework.modules.a aVar, Object... objArr) {
        if (objArr == null) {
            objArr = new Object[1];
        }
        a(aVar, objArr);
        this.b = (Object[]) objArr.clone();
        this.a = aVar;
    }

    public com.sds.android.ttpod.framework.modules.a a() {
        return this.a;
    }

    public Object[] b() {
        return this.b;
    }

    private void a(com.sds.android.ttpod.framework.modules.a aVar, Object... objArr) {
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.j()) {
            Class[] paramTypes = aVar.getParamTypes();
            if (paramTypes.length != objArr.length) {
                throw new IllegalArgumentException("Command(" + aVar.name() + ") Param Count is " + objArr.length + " while expect to be " + aVar.getParamTypes().length + "!");
            }
            int i = 0;
            while (i < objArr.length) {
                Class cls = paramTypes[i];
                Class cls2 = objArr[i] == null ? null : objArr[i].getClass();
                if (cls2 == null || cls.isAssignableFrom(cls2)) {
                    i++;
                } else {
                    throw new IllegalArgumentException("Command(" + aVar.name() + ") Param Type not match! it is " + cls2.getName() + " while except to be " + cls.getName());
                }
            }
        }
    }
}

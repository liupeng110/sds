package com.sds.android.sdk.lib.c.a;

import android.content.ContentValues;

/* MappingPolicy */
public class c {
    public static boolean a(Class cls) {
        return cls == String.class || cls == Long.class || cls == Integer.class || cls == Double.class || cls == Float.class || cls == Short.class || cls == Boolean.class || cls == Byte.class || cls == Character.class;
    }

    public static String b(Class cls) {
        if (cls == String.class) {
            return "TEXT";
        }
        if (cls == Long.class) {
            return "INT8";
        }
        if (cls == Integer.class) {
            return "INTEGER";
        }
        if (cls == Double.class) {
            return "DOUBLE";
        }
        if (cls == Float.class) {
            return "FLOAT";
        }
        if (cls == Short.class) {
            return "SHORT";
        }
        if (cls == Boolean.class) {
            return "BOOLEAN";
        }
        if (cls == Byte.class) {
            return "SHORT";
        }
        if (cls == Character.class) {
            return "SHORT";
        }
        return null;
    }

    private static Object a(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private static Object b(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Long) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).longValue());
        }
        if (obj instanceof Double) {
            return Long.valueOf(((Double) obj).longValue());
        }
        if (obj instanceof Float) {
            return Long.valueOf(((Float) obj).longValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf(((Short) obj).longValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Byte) {
            return Long.valueOf(((Byte) obj).longValue());
        } else {
            if (obj instanceof Character) {
                return Long.valueOf((long) ((Character) obj).charValue());
            }
            try {
                return Long.valueOf(Long.parseLong(obj.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static Object c(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return obj;
        }
        if (obj instanceof Long) {
            return Integer.valueOf(((Long) obj).intValue());
        }
        if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        }
        if (obj instanceof Float) {
            return Integer.valueOf(((Float) obj).intValue());
        }
        if (obj instanceof Short) {
            return Integer.valueOf(((Short) obj).intValue());
        }
        if (obj instanceof Boolean) {
            return Integer.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Byte) {
            return Integer.valueOf(((Byte) obj).intValue());
        } else {
            if (obj instanceof Character) {
                return Integer.valueOf(((Character) obj).charValue());
            }
            try {
                return Integer.valueOf(Integer.parseInt(obj.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static Object d(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Double) {
            return obj;
        }
        if (obj instanceof Long) {
            return Double.valueOf(((Long) obj).doubleValue());
        }
        if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if (obj instanceof Short) {
            return Double.valueOf(((Short) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return Double.valueOf(((Boolean) obj).booleanValue() ? 1.0d : 0.0d);
        } else if (obj instanceof Byte) {
            return Double.valueOf(((Byte) obj).doubleValue());
        } else {
            if (obj instanceof Character) {
                return Double.valueOf((double) ((Character) obj).charValue());
            }
            try {
                return Double.valueOf(Double.parseDouble(obj.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static Object e(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Float) {
            return obj;
        }
        if (obj instanceof Long) {
            return Float.valueOf(((Long) obj).floatValue());
        }
        if (obj instanceof Integer) {
            return Float.valueOf(((Integer) obj).floatValue());
        }
        if (obj instanceof Double) {
            return Float.valueOf(((Double) obj).floatValue());
        }
        if (obj instanceof Short) {
            return Float.valueOf(((Short) obj).floatValue());
        }
        if (obj instanceof Boolean) {
            return Float.valueOf(((Boolean) obj).booleanValue() ? 1.0f : 0.0f);
        } else if (obj instanceof Byte) {
            return Float.valueOf(((Byte) obj).floatValue());
        } else {
            if (obj instanceof Character) {
                return Float.valueOf((float) ((Character) obj).charValue());
            }
            try {
                return Float.valueOf(Float.parseFloat(obj.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static Object f(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Short) {
            return obj;
        }
        if (obj instanceof Long) {
            return Short.valueOf(((Long) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return Short.valueOf(((Integer) obj).shortValue());
        }
        if (obj instanceof Double) {
            return Short.valueOf(((Double) obj).shortValue());
        }
        if (obj instanceof Float) {
            return Short.valueOf(((Float) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Short.valueOf(((Boolean) obj).booleanValue() ? (short) 1 : (short) 0);
        } else if (obj instanceof Byte) {
            return Short.valueOf(((Byte) obj).shortValue());
        } else {
            if (obj instanceof Character) {
                return Short.valueOf((short) ((Character) obj).charValue());
            }
            try {
                return Short.valueOf(Short.parseShort(obj.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static Object g(Object obj) {
        boolean z = true;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return obj;
        }
        if (obj instanceof Long) {
            if (((Long) obj).longValue() == 0) {
                z = false;
            }
            return Boolean.valueOf(z);
        } else if (obj instanceof Integer) {
            if (((Integer) obj).intValue() == 0) {
                z = false;
            }
            return Boolean.valueOf(z);
        } else if (obj instanceof Double) {
            if (((Double) obj).doubleValue() == 0.0d) {
                z = false;
            }
            return Boolean.valueOf(z);
        } else if (obj instanceof Float) {
            if (((Float) obj).floatValue() == 0.0f) {
                z = false;
            }
            return Boolean.valueOf(z);
        } else if (obj instanceof Short) {
            if (((Short) obj).shortValue() == (short) 0) {
                z = false;
            }
            return Boolean.valueOf(z);
        } else if (obj instanceof Byte) {
            if (((Byte) obj).byteValue() == (byte) 0) {
                z = false;
            }
            return Boolean.valueOf(z);
        } else if (obj instanceof Character) {
            if (((Character) obj).charValue() == '\u0000') {
                z = false;
            }
            return Boolean.valueOf(z);
        } else {
            try {
                String obj2 = obj.toString();
                if (obj2.equalsIgnoreCase("true")) {
                    return Boolean.valueOf(true);
                }
                if (obj2.equalsIgnoreCase("false")) {
                    return Boolean.valueOf(false);
                }
                if (Double.valueOf(Double.parseDouble(obj2)).doubleValue() == 0.0d) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static Object h(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Byte) {
            return obj;
        }
        if (obj instanceof Long) {
            return Byte.valueOf(((Long) obj).byteValue());
        }
        if (obj instanceof Integer) {
            return Byte.valueOf(((Integer) obj).byteValue());
        }
        if (obj instanceof Double) {
            return Byte.valueOf(((Double) obj).byteValue());
        }
        if (obj instanceof Float) {
            return Byte.valueOf(((Float) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Byte.valueOf(((Short) obj).byteValue());
        }
        if (obj instanceof Boolean) {
            return Byte.valueOf(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
        } else if (obj instanceof Character) {
            return Byte.valueOf((byte) ((Character) obj).charValue());
        } else {
            try {
                return Byte.valueOf(Byte.parseByte(obj.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static Object i(Object obj) {
        char c = '\u0000';
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return obj;
        }
        if (obj instanceof Long) {
            return Character.valueOf((char) ((Long) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return Character.valueOf((char) ((Integer) obj).shortValue());
        }
        if (obj instanceof Double) {
            return Character.valueOf((char) ((Double) obj).shortValue());
        }
        if (obj instanceof Float) {
            return Character.valueOf((char) ((Float) obj).shortValue());
        }
        if (obj instanceof Short) {
            return Character.valueOf((char) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                c = '\u0001';
            }
            return Character.valueOf(c);
        } else if (obj instanceof Byte) {
            return Character.valueOf((char) ((Byte) obj).shortValue());
        } else {
            try {
                String obj2 = obj.toString();
                if (obj2.length() <= 0) {
                    return null;
                }
                return Character.valueOf(obj2.charAt(0));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static Object a(Object obj, Class cls) {
        if (cls == String.class) {
            return a(obj);
        }
        if (cls == Long.class) {
            return b(obj);
        }
        if (cls == Integer.class) {
            return c(obj);
        }
        if (cls == Double.class) {
            return d(obj);
        }
        if (cls == Float.class) {
            return e(obj);
        }
        if (cls == Short.class) {
            return f(obj);
        }
        if (cls == Boolean.class) {
            return g(obj);
        }
        if (cls == Byte.class) {
            return h(obj);
        }
        if (cls == Character.class) {
            return i(obj);
        }
        return null;
    }

    static void a(ContentValues contentValues, String str, Object obj) {
        if (obj == null) {
            contentValues.putNull(str);
        } else if (obj.getClass() == String.class) {
            contentValues.put(str, (String) obj);
        } else if (obj.getClass() == Long.class) {
            contentValues.put(str, (Long) obj);
        } else if (obj.getClass() == Integer.class) {
            contentValues.put(str, (Integer) obj);
        } else if (obj.getClass() == Double.class) {
            contentValues.put(str, (Double) obj);
        } else if (obj.getClass() == Float.class) {
            contentValues.put(str, (Float) obj);
        } else if (obj.getClass() == Short.class) {
            contentValues.put(str, (Short) obj);
        } else if (obj.getClass() == Boolean.class) {
            contentValues.put(str, (Boolean) obj);
        } else if (obj.getClass() == Byte.class) {
            contentValues.put(str, (Byte) obj);
        } else if (obj.getClass() == Character.class) {
            contentValues.put(str, Short.valueOf((short) ((Character) obj).charValue()));
        } else {
            throw new ClassCastException("");
        }
    }
}

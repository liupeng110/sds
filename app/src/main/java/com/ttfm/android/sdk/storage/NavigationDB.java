package com.ttfm.android.sdk.storage;

import android.content.Context;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class NavigationDB {
    public static final String NAV_FILE = "_fmnav";

    public static void save(Context context, String str) {
        ObjectOutputStream objectOutputStream;
        IOException e;
        Throwable th;
        try {
            objectOutputStream = new ObjectOutputStream(context.openFileOutput(NAV_FILE, 0));
            try {
                objectOutputStream.writeObject(str);
                objectOutputStream.flush();
                objectOutputStream.close();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            objectOutputStream = null;
            e22.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            throw th;
        }
    }

    public static String get(Context context) {
        ObjectInputStream objectInputStream;
        String str;
        FileNotFoundException fileNotFoundException;
        FileNotFoundException fileNotFoundException2;
        IOException e;
        Throwable th;
        StreamCorruptedException streamCorruptedException;
        StreamCorruptedException streamCorruptedException2;
        IOException iOException;
        ClassNotFoundException classNotFoundException;
        ClassNotFoundException classNotFoundException2;
        try {
            objectInputStream = new ObjectInputStream(context.openFileInput(NAV_FILE));
            try {
                str = (String) objectInputStream.readObject();
            } catch (FileNotFoundException e2) {
                fileNotFoundException = e2;
                str = null;
                fileNotFoundException2 = fileNotFoundException;
                try {
                    fileNotFoundException2.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (StreamCorruptedException e4) {
                streamCorruptedException = e4;
                str = null;
                streamCorruptedException2 = streamCorruptedException;
                streamCorruptedException2.printStackTrace();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
                return str;
            } catch (IOException e5) {
                iOException = e5;
                str = null;
                e322 = iOException;
                e322.printStackTrace();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e3222) {
                        e3222.printStackTrace();
                    }
                }
                return str;
            } catch (ClassNotFoundException e6) {
                classNotFoundException = e6;
                str = null;
                classNotFoundException2 = classNotFoundException;
                classNotFoundException2.printStackTrace();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e32222) {
                        e32222.printStackTrace();
                    }
                }
                return str;
            }
            try {
                objectInputStream.close();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e322222) {
                        e322222.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e7) {
                fileNotFoundException2 = e7;
                fileNotFoundException2.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return str;
            } catch (StreamCorruptedException e8) {
                streamCorruptedException2 = e8;
                streamCorruptedException2.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return str;
            } catch (IOException e9) {
                e322222 = e9;
                e322222.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return str;
            } catch (ClassNotFoundException e10) {
                classNotFoundException2 = e10;
                classNotFoundException2.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return str;
            }
        } catch (FileNotFoundException e22) {
            objectInputStream = null;
            fileNotFoundException = e22;
            str = null;
            fileNotFoundException2 = fileNotFoundException;
            fileNotFoundException2.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return str;
        } catch (StreamCorruptedException e42) {
            objectInputStream = null;
            streamCorruptedException = e42;
            str = null;
            streamCorruptedException2 = streamCorruptedException;
            streamCorruptedException2.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return str;
        } catch (IOException e52) {
            objectInputStream = null;
            iOException = e52;
            str = null;
            e322222 = iOException;
            e322222.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return str;
        } catch (ClassNotFoundException e62) {
            objectInputStream = null;
            classNotFoundException = e62;
            str = null;
            classNotFoundException2 = classNotFoundException;
            classNotFoundException2.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            objectInputStream = null;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw th;
        }
        return str;
    }
}

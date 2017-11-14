package com.ttfm.android.sdk.storage;

import android.content.Context;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class DSDServicesDB {
    public static final String DSD_FILE = "_fmdsd";

    public static void save(Context context, String str) {
        IOException e;
        Throwable th;
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(context.openFileOutput(DSD_FILE, 0));
            try {
                objectOutputStream.writeUTF(str);
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
        String readUTF;
        FileNotFoundException fileNotFoundException;
        FileNotFoundException fileNotFoundException2;
        IOException e;
        Throwable th;
        StreamCorruptedException streamCorruptedException;
        StreamCorruptedException streamCorruptedException2;
        IOException iOException;
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(context.openFileInput(DSD_FILE));
            try {
                readUTF = objectInputStream.readUTF();
            } catch (FileNotFoundException e2) {
                fileNotFoundException = e2;
                readUTF = null;
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
                    return readUTF;
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
                readUTF = null;
                streamCorruptedException2 = streamCorruptedException;
                streamCorruptedException2.printStackTrace();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
                return readUTF;
            } catch (IOException e5) {
                iOException = e5;
                readUTF = null;
                e322 = iOException;
                e322.printStackTrace();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e3222) {
                        e3222.printStackTrace();
                    }
                }
                return readUTF;
            }
            try {
                objectInputStream.close();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e32222) {
                        e32222.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e6) {
                fileNotFoundException2 = e6;
                fileNotFoundException2.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readUTF;
            } catch (StreamCorruptedException e7) {
                streamCorruptedException2 = e7;
                streamCorruptedException2.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readUTF;
            } catch (IOException e8) {
                e32222 = e8;
                e32222.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readUTF;
            }
        } catch (FileNotFoundException e22) {
            objectInputStream = null;
            fileNotFoundException = e22;
            readUTF = null;
            fileNotFoundException2 = fileNotFoundException;
            fileNotFoundException2.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readUTF;
        } catch (StreamCorruptedException e42) {
            objectInputStream = null;
            streamCorruptedException = e42;
            readUTF = null;
            streamCorruptedException2 = streamCorruptedException;
            streamCorruptedException2.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readUTF;
        } catch (IOException e52) {
            objectInputStream = null;
            iOException = e52;
            readUTF = null;
            e32222 = iOException;
            e32222.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readUTF;
        } catch (Throwable th3) {
            th = th3;
            objectInputStream = null;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw th;
        }
        return readUTF;
    }
}

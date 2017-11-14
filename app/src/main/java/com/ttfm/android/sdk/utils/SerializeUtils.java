package com.ttfm.android.sdk.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class SerializeUtils {
    public static void serializObjectTo(String str, Object obj) {
        ObjectOutputStream objectOutputStream;
        Exception e;
        Throwable th;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(str));
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                objectOutputStream.close();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
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
        } catch (Exception e5) {
            e = e5;
            objectOutputStream = null;
            e.printStackTrace();
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

    public static Object reserializeObjectBy(String str) {
        ObjectInputStream objectInputStream;
        Object readObject;
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        StreamCorruptedException e3;
        ClassNotFoundException e4;
        FileNotFoundException fileNotFoundException;
        StreamCorruptedException streamCorruptedException;
        IOException iOException;
        ClassNotFoundException classNotFoundException;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(str));
            try {
                readObject = objectInputStream.readObject();
                try {
                    objectInputStream.close();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e6) {
                    e2 = e6;
                    try {
                        e2.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e52) {
                                e52.printStackTrace();
                            }
                        }
                        return readObject;
                    } catch (Throwable th2) {
                        th = th2;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e522) {
                                e522.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (StreamCorruptedException e7) {
                    e3 = e7;
                    e3.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e5222) {
                            e5222.printStackTrace();
                        }
                    }
                    return readObject;
                } catch (IOException e8) {
                    e5222 = e8;
                    e5222.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e52222) {
                            e52222.printStackTrace();
                        }
                    }
                    return readObject;
                } catch (ClassNotFoundException e9) {
                    e4 = e9;
                    e4.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e522222) {
                            e522222.printStackTrace();
                        }
                    }
                    return readObject;
                }
            } catch (FileNotFoundException e10) {
                fileNotFoundException = e10;
                readObject = null;
                e2 = fileNotFoundException;
                e2.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readObject;
            } catch (StreamCorruptedException e11) {
                streamCorruptedException = e11;
                readObject = null;
                e3 = streamCorruptedException;
                e3.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readObject;
            } catch (IOException e12) {
                iOException = e12;
                readObject = null;
                e522222 = iOException;
                e522222.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readObject;
            } catch (ClassNotFoundException e13) {
                classNotFoundException = e13;
                readObject = null;
                e4 = classNotFoundException;
                e4.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readObject;
            }
        } catch (FileNotFoundException e102) {
            objectInputStream = null;
            fileNotFoundException = e102;
            readObject = null;
            e2 = fileNotFoundException;
            e2.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readObject;
        } catch (StreamCorruptedException e112) {
            objectInputStream = null;
            streamCorruptedException = e112;
            readObject = null;
            e3 = streamCorruptedException;
            e3.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readObject;
        } catch (IOException e122) {
            objectInputStream = null;
            iOException = e122;
            readObject = null;
            e522222 = iOException;
            e522222.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readObject;
        } catch (ClassNotFoundException e132) {
            objectInputStream = null;
            classNotFoundException = e132;
            readObject = null;
            e4 = classNotFoundException;
            e4.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readObject;
        } catch (Throwable th3) {
            th = th3;
            objectInputStream = null;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw th;
        }
        return readObject;
    }
}

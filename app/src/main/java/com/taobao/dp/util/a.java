package com.taobao.dp.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class a {
    public static int a() {
        return a("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
    }

    private static int a(String str) {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        int i = 0;
        try {
            fileReader = new FileReader(str);
            try {
                bufferedReader = new BufferedReader(fileReader);
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        i = Integer.parseInt(readLine.trim());
                    }
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                    }
                } catch (FileNotFoundException e3) {
                    fileReader2 = fileReader;
                    if (fileReader2 != null) {
                        try {
                            fileReader2.close();
                        } catch (IOException e4) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                        }
                    }
                    return i;
                } catch (IOException e6) {
                    bufferedReader2 = bufferedReader;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e7) {
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e8) {
                        }
                    }
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e9) {
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e10) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e11) {
                bufferedReader = null;
                fileReader2 = fileReader;
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return i;
            } catch (IOException e12) {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                return i;
            } catch (Throwable th3) {
                th = th3;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e13) {
            bufferedReader = null;
            if (fileReader2 != null) {
                fileReader2.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return i;
        } catch (IOException e14) {
            fileReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return i;
        } catch (Throwable th4) {
            th = th4;
            fileReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
        return i;
    }

    public static String b() {
        BufferedReader bufferedReader;
        Throwable th;
        String str = null;
        FileReader fileReader;
        try {
            fileReader = new FileReader("/proc/cpuinfo");
            try {
                bufferedReader = new BufferedReader(fileReader);
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        str = readLine.split(":\\s+", 2)[1];
                        try {
                            fileReader.close();
                        } catch (IOException e) {
                        }
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                        }
                    } else {
                        try {
                            fileReader.close();
                        } catch (IOException e3) {
                        }
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (FileNotFoundException e5) {
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e6) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e7) {
                        }
                    }
                    return str;
                } catch (IOException e8) {
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e9) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e10) {
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e11) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e12) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e13) {
                bufferedReader = null;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str;
            } catch (IOException e14) {
                bufferedReader = null;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bufferedReader = null;
                th = th4;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e15) {
            bufferedReader = null;
            fileReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return str;
        } catch (IOException e16) {
            bufferedReader = null;
            fileReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return str;
        } catch (Throwable th32) {
            fileReader = null;
            th = th32;
            bufferedReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return str;
    }
}

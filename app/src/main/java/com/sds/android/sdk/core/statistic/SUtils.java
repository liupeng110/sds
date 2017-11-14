package com.sds.android.sdk.core.statistic;

import com.sds.android.sdk.lib.a.a.a;
import com.sds.android.sdk.lib.util.g;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SUtils {

    public enum PostResult {
        POST_SUCCESSED,
        POST_FAILED,
        POST_ERROR
    }

    public static long initEventFiles(LinkedList<String> linkedList, String str, String str2, int i) {
        long j;
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                String name = file.getName();
                g.a("SUtils", "initEventFiles fileName: " + name);
                if (file.isFile() && name != null && name.startsWith(str2)) {
                    linkedList.add(file.getAbsolutePath());
                }
            }
            Collections.sort(linkedList);
            j = 0;
            while (linkedList.size() > i) {
                try {
                    String str3 = (String) linkedList.remove();
                    j += (long) getTotalLines(new File(str3));
                    deleteFile(str3);
                } catch (IOException e) {
                    IOException iOException = e;
                    long j2 = j;
                    iOException.printStackTrace();
                    j = j2;
                }
            }
        } else {
            j = 0;
        }
        g.a("SUtils", "initEventFiles local file count: " + linkedList.size() + "remove count: " + j);
        return j;
    }

    public static int getTotalLines(File file) throws IOException {
        Reader fileReader = new FileReader(file);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        int i = 0;
        for (String readLine = lineNumberReader.readLine(); readLine != null; readLine = lineNumberReader.readLine()) {
            i++;
        }
        lineNumberReader.close();
        fileReader.close();
        return i;
    }

    public static boolean deleteFile(String str) {
        return !"".equals(str) && deleteFile(new File(str));
    }

    public static boolean deleteFile(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File deleteFile : listFiles) {
                    if (!deleteFile(deleteFile)) {
                        return false;
                    }
                }
            }
        }
        if (!file.exists() || file.delete()) {
            return true;
        }
        return false;
    }

    public static File createFile(String str) {
        if (str != null && "".equals(str)) {
            return null;
        }
        File file = new File(str);
        if (file.isFile()) {
            return file;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return null;
        }
        if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
            return null;
        }
        try {
            if (file.createNewFile()) {
                return file;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildCurrentWirteFilePath(String str, String str2) {
        return str + File.separator + str2 + System.currentTimeMillis();
    }

    public static String buildJson(JSONArray jSONArray, HashMap<String, Object> hashMap) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("data", jSONArray);
            if (hashMap != null) {
                jSONObject.put(SService.STATISTIC_PARAM, new JSONObject(hashMap));
            } else {
                jSONObject.put(SService.STATISTIC_PARAM, "GeneralParameter is null!");
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildJson(JSONObject jSONObject, HashMap<String, Object> hashMap) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", jSONObject);
            if (hashMap != null) {
                jSONObject2.put(SService.STATISTIC_PARAM, new JSONObject(hashMap));
            } else {
                jSONObject2.put(SService.STATISTIC_PARAM, "General parameter is null!");
            }
            return jSONObject2.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildJson(List<SEvent> list, HashMap<String, Object> hashMap) {
        JSONArray jSONArray = new JSONArray();
        for (SEvent dataMap : list) {
            jSONArray.put(new JSONObject(dataMap.getDataMap()));
        }
        return buildJson(jSONArray, (HashMap) hashMap);
    }

    public static boolean httpPost(String str, String str2) {
        if (str == null) {
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(HttpClientProxy.HEADER_ACCEPT_GZIP, HttpClientProxy.CONTENT_ENCODING_GZIP);
        hashMap.put("Connection", "close");
        a a = com.sds.android.sdk.lib.a.a.a(str, hashMap, str2);
        if (a == null || 200 != a.c()) {
            return false;
        }
        return true;
    }

    public static boolean writeEventToFile(String str, SEvent sEvent) {
        IOException e;
        Throwable th;
        ArrayIndexOutOfBoundsException e2;
        if (str == null) {
            throw new NullPointerException("path should not be null.");
        }
        BufferedWriter bufferedWriter = null;
        BufferedWriter bufferedWriter2;
        try {
            File createFile = createFile(str);
            if (createFile != null) {
                bufferedWriter2 = new BufferedWriter(new FileWriter(createFile, true));
                try {
                    String jSONObject = new JSONObject(sEvent.getDataMap()).toString();
                    if (jSONObject == null) {
                        jSONObject = "";
                    }
                    bufferedWriter2.write(jSONObject);
                    bufferedWriter2.write(10);
                    bufferedWriter2.flush();
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return true;
                } catch (IOException e4) {
                    e = e4;
                    try {
                        e.printStackTrace();
                        if (bufferedWriter2 != null) {
                            return false;
                        }
                        try {
                            bufferedWriter2.close();
                            return false;
                        } catch (IOException e5) {
                            e5.printStackTrace();
                            return false;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e52) {
                                e52.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (ArrayIndexOutOfBoundsException e6) {
                    e2 = e6;
                    e2.printStackTrace();
                    if (bufferedWriter2 != null) {
                        return false;
                    }
                    try {
                        bufferedWriter2.close();
                        return false;
                    } catch (IOException e522) {
                        e522.printStackTrace();
                        return false;
                    }
                }
            } else if (bufferedWriter == null) {
                return false;
            } else {
                try {
                    bufferedWriter.close();
                    return false;
                } catch (IOException e5222) {
                    e5222.printStackTrace();
                    return false;
                }
            }
        } catch (IOException e7) {
            e5222 = e7;
            bufferedWriter2 = bufferedWriter;
            e5222.printStackTrace();
            if (bufferedWriter2 != null) {
                return false;
            }
            bufferedWriter2.close();
            return false;
        } catch (ArrayIndexOutOfBoundsException e8) {
            e2 = e8;
            bufferedWriter2 = bufferedWriter;
            e2.printStackTrace();
            if (bufferedWriter2 != null) {
                return false;
            }
            bufferedWriter2.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                bufferedWriter2.close();
            }
            throw th;
        }
    }

    public static JSONArray readEventFromFile(String str) {
        if (str == null) {
            throw new NullPointerException("path should not be null.");
        }
        try {
            JSONArray jSONArray = new JSONArray();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    try {
                        jSONArray.put(new JSONObject(readLine));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    bufferedReader.close();
                    return jSONArray;
                }
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static PostResult postFileEvent(HashMap<String, Object> hashMap, String str, String str2) {
        g.c("SUtils", "postFileEvent");
        PostResult postResult = PostResult.POST_ERROR;
        if (str2 == null) {
            return postResult;
        }
        JSONArray readEventFromFile = readEventFromFile(str2);
        if (readEventFromFile == null) {
            return postResult;
        }
        String buildJson = buildJson(readEventFromFile, (HashMap) hashMap);
        g.c("SUtils", "postFileEvent content: " + buildJson);
        if (buildJson == null || !httpPost(str, buildJson)) {
            return PostResult.POST_FAILED;
        }
        return PostResult.POST_SUCCESSED;
    }

    public static PostResult postListEvent(LinkedList<SEvent> linkedList, HashMap<String, Object> hashMap, String str) {
        g.c("SUtils", "postListEvent");
        PostResult postResult = PostResult.POST_ERROR;
        if (linkedList.size() > 0) {
            String buildJson = buildJson((List) linkedList, (HashMap) hashMap);
            g.c("SUtils", "postListEvent content: " + buildJson);
            if (buildJson == null || !httpPost(str, buildJson)) {
                postResult = PostResult.POST_FAILED;
            } else {
                postResult = PostResult.POST_SUCCESSED;
            }
            linkedList.clear();
        }
        return postResult;
    }

    public static PostResult postEvent(SEvent sEvent, HashMap<String, Object> hashMap, String str) {
        g.c("SUtils", "postEvent");
        PostResult postResult = PostResult.POST_ERROR;
        JSONObject jSONObject = new JSONObject(sEvent.getDataMap());
        if (jSONObject == null) {
            return postResult;
        }
        String buildJson = buildJson(jSONObject, (HashMap) hashMap);
        g.c("SUtils", "postEvent content: " + buildJson);
        if (buildJson == null || !httpPost(str, buildJson)) {
            return PostResult.POST_FAILED;
        }
        return PostResult.POST_SUCCESSED;
    }

    public static void writeDeep(String str, long j) {
        IOException e;
        Throwable th;
        BufferedWriter bufferedWriter = null;
        BufferedWriter bufferedWriter2;
        try {
            bufferedWriter2 = new BufferedWriter(new FileWriter(str));
            try {
                bufferedWriter2.write(String.valueOf(j));
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            bufferedWriter2 = null;
            e22.printStackTrace();
            if (bufferedWriter2 != null) {
                bufferedWriter2.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            throw th;
        }
    }

    public static long readDeep(String str) {
        Exception e;
        Throwable th;
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
            try {
                long longValue = Long.valueOf(bufferedReader2.readLine()).longValue();
                if (bufferedReader2 == null) {
                    return longValue;
                }
                try {
                    bufferedReader2.close();
                    return longValue;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return longValue;
                }
            } catch (Exception e3) {
                e = e3;
                bufferedReader = bufferedReader2;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return 0;
        }
    }
}

package com.sds.android.ttpod.framework.modules.core.audioeffect;

import com.sds.android.ttpod.fragment.audioeffect.EqualizerHandpickFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* EqualizerPreset */
public class e {
    private static final Map<String, short[]> a = new LinkedHashMap();
    private static final List<String> b = new ArrayList();

    static {
        a.put("普通/Normal", new short[]{(short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0});
        a.put("流行/Pop", new short[]{(short) 600, (short) 800, (short) 400, (short) 600, (short) 900, (short) 1000, (short) 300, (short) 600, (short) 300, (short) 900});
        a.put("R&B/R&B", new short[]{(short) 0, (short) 0, (short) 400, (short) 200, (short) -200, (short) 0, (short) 300, (short) 0, (short) 500, (short) 200});
        a.put("嘻哈/Hip-Hop", new short[]{(short) 600, (short) 400, (short) -400, (short) 600, (short) 100, (short) -500, (short) 600, (short) -400, (short) 400, (short) -100});
        a.put("说唱/Rap", new short[]{(short) 300, (short) 100, (short) 400, (short) 300, (short) 500, (short) -500, (short) 500, (short) -300, (short) 0, (short) 600});
        a.put("布鲁斯/Blues", new short[]{(short) -100, (short) 600, (short) 500, (short) 200, (short) 200, (short) 0, (short) 200, (short) 300, (short) 200, (short) 300});
        a.put("法国香颂/Chanson", new short[]{(short) 100, (short) 0, (short) 0, (short) 100, (short) 0, (short) 300, (short) 300, (short) 200, (short) 300, (short) 200});
        a.put("灵魂/Soul", new short[]{(short) -100, (short) 600, (short) 500, (short) 400, (short) -100, (short) 400, (short) 100, (short) -100, (short) 0, (short) 0});
        a.put("雷鬼/Reggae", new short[]{(short) 0, (short) 400, (short) 300, (short) 100, (short) 0, (short) 0, (short) 200, (short) 200, (short) 400, (short) 0});
        a.put("前卫/Progressive", new short[]{(short) 300, (short) 200, (short) 100, (short) 200, (short) 200, (short) -200, (short) -100, (short) 200, (short) 500, (short) 300});
        a.put("福音/Gospel", new short[]{(short) 0, (short) 200, (short) 200, (short) 100, (short) 100, (short) 200, (short) -100, (short) 200, (short) 300, (short) 0});
        a.put("拉丁/Latin Pop", new short[]{(short) 100, (short) 500, (short) 500, (short) 0, (short) -100, (short) 100, (short) 0, (short) 300, (short) 400, (short) 500});
        a.put("独立流行/Indie Pop", new short[]{(short) 400, (short) -300, (short) 0, (short) 400, (short) 100, (short) 200, (short) -500, (short) 500, (short) 600, (short) 500});
        a.put("网络流行/New Media", new short[]{(short) 0, (short) 200, (short) 300, (short) 200, (short) 100, (short) 200, (short) 100, (short) 200, (short) 100, (short) 200});
        a.put("另类流行/Alternative Pop", new short[]{(short) 200, (short) 300, (short) 300, (short) 100, (short) 200, (short) -200, (short) -200, (short) 300, (short) 100, (short) -100});
        a.put("Teen Pop/Teen-Pop", new short[]{(short) 100, (short) -100, (short) -100, (short) 200, (short) 0, (short) -100, (short) 200, (short) 300, (short) 200, (short) 300});
        a.put("Dance-Pop/Dance-Pop", new short[]{(short) 100, (short) 400, (short) 300, (short) 200, (short) 0, (short) -100, (short) -100, (short) 200, (short) 200, (short) 500});
        a.put("J-POP/J-POP", new short[]{(short) 0, (short) 0, (short) 0, (short) 200, (short) -100, (short) 0, (short) 100, (short) 300, (short) -200, (short) 200});
        a.put("K-POP/K-POP", new short[]{(short) 100, (short) -100, (short) 400, (short) 400, (short) -100, (short) -100, (short) 100, (short) 400, (short) 400, (short) 100});
        a.put("Dream-pop/Dream-pop", new short[]{(short) -100, (short) -200, (short) -200, (short) -300, (short) 0, (short) 200, (short) 400, (short) -100, (short) 200, (short) -300});
        a.put("Synthpop/Synthpop", new short[]{(short) -300, (short) -200, (short) 0, (short) -100, (short) 400, (short) 0, (short) 300, (short) 600, (short) 400, (short) 400});
        a.put("Urban/Urban", new short[]{(short) 0, (short) -100, (short) 0, (short) 100, (short) 0, (short) 100, (short) 100, (short) 100, (short) 100, (short) 100});
        a.put("摇滚/Rock", new short[]{(short) 800, (short) 1100, (short) 400, (short) 600, (short) -200, (short) 200, (short) 300, (short) 0, (short) 1200, (short) 1400});
        a.put("流行摇滚/Pop Rock", new short[]{(short) 200, (short) 300, (short) 400, (short) 300, (short) 100, (short) 100, (short) 200, (short) 400, (short) 300, (short) 200});
        a.put("英伦摇滚/Brit-pop", new short[]{(short) 100, (short) 300, (short) 300, (short) 300, (short) 200, (short) 100, (short) -100, (short) 400, (short) 400, (short) 100});
        a.put("民谣摇滚/FolkRock", new short[]{(short) 0, (short) 0, (short) 100, (short) 100, (short) 100, (short) 100, (short) 100, (short) 200, (short) 0, (short) 0});
        a.put("乡村摇滚/Country Rock", new short[]{(short) 0, (short) -100, (short) -100, (short) 0, (short) 100, (short) 0, (short) -100, (short) -100, (short) 0, (short) 0});
        a.put("独立摇滚/Indie Rock", new short[]{(short) 0, (short) 100, (short) 200, (short) 0, (short) -100, (short) -100, (short) 100, (short) 100, (short) 100, (short) -100});
        a.put("艺术摇滚/ArtRock", new short[]{(short) -100, (short) 200, (short) 200, (short) 200, (short) 100, (short) 100, (short) 100, (short) 100, (short) 200, (short) 200});
        a.put("硬摇滚/Hard Rock", new short[]{(short) 0, (short) 200, (short) -100, (short) 200, (short) -100, (short) 0, (short) 300, (short) 500, (short) 200, (short) 300});
        a.put("软摇滚/Soft Rock", new short[]{(short) -100, (short) 100, (short) 100, (short) 0, (short) 0, (short) 200, (short) 200, (short) 100, (short) 100, (short) 0});
        a.put("另类摇滚/Alternative Rock", new short[]{(short) 100, (short) 400, (short) 400, (short) 300, (short) -100, (short) 100, (short) 200, (short) 400, (short) 400, (short) 500});
        a.put("后摇/PostRock", new short[]{(short) 100, (short) 300, (short) -200, (short) -100, (short) -100, (short) 0, (short) 0, (short) 100, (short) 300, (short) 0});
        a.put("重金属/Heavy Metal", new short[]{(short) -100, (short) 100, (short) 200, (short) 0, (short) 0, (short) 400, (short) 300, (short) 200, (short) 300, (short) 300});
        a.put("金属/Metal", new short[]{(short) -100, (short) 200, (short) 300, (short) 200, (short) 300, (short) 100, (short) 200, (short) 200, (short) 100, (short) -100});
        a.put("流行金属/Pop Metal", new short[]{(short) -100, (short) 200, (short) 300, (short) 300, (short) 300, (short) 200, (short) 200, (short) 300, (short) 300, (short) -100});
        a.put("新金属/Nu-Metal", new short[]{(short) -200, (short) 300, (short) 100, (short) -100, (short) 0, (short) 0, (short) 300, (short) 300, (short) 200, (short) 100});
        a.put("死亡金属/Death Metal", new short[]{(short) 100, (short) 500, (short) 400, (short) 500, (short) -100, (short) 300, (short) 300, (short) 400, (short) 600, (short) 600});
        a.put("新古典金属/Neo - Classical Metal", new short[]{(short) 0, (short) 100, (short) 200, (short) 300, (short) -100, (short) -100, (short) 200, (short) 300, (short) 200, (short) 0});
        a.put("前卫金属/Progressive Metal", new short[]{(short) 0, (short) 100, (short) 0, (short) 200, (short) 100, (short) 0, (short) 300, (short) 200, (short) 300, (short) 200});
        a.put("工业金属/Industrial Metal", new short[]{(short) 0, (short) 0, (short) 0, (short) 100, (short) 100, (short) 0, (short) 200, (short) 300, (short) 300, (short) 300});
        a.put("黑金属/Black Metal", new short[]{(short) -100, (short) 300, (short) 200, (short) -100, (short) 0, (short) 0, (short) 300, (short) 300, (short) 200, (short) 100});
        a.put("歌特金属/Gothic Metal", new short[]{(short) 0, (short) -100, (short) 300, (short) 200, (short) 100, (short) 0, (short) 200, (short) 300, (short) 0, (short) 0});
        a.put("说唱金属/Rap Metal", new short[]{(short) 100, (short) 300, (short) 300, (short) 300, (short) 100, (short) 200, (short) 300, (short) 300, (short) 200, (short) -100});
        a.put("交响金属/Symphonic Metal", new short[]{(short) -100, (short) 100, (short) 200, (short) 100, (short) 200, (short) 100, (short) 200, (short) 100, (short) 100, (short) -100});
        a.put("硬核/Hardcore", new short[]{(short) 0, (short) 200, (short) 300, (short) 300, (short) 300, (short) 100, (short) 200, (short) 300, (short) 400, (short) -200});
        a.put("后硬核/Post Hardcore", new short[]{(short) -100, (short) 300, (short) 400, (short) 300, (short) 300, (short) 100, (short) 200, (short) 300, (short) 400, (short) -100});
        a.put("朋克/Punk", new short[]{(short) 0, (short) 200, (short) 300, (short) 200, (short) 0, (short) 400, (short) 600, (short) 400, (short) 0, (short) 500});
        a.put("后朋克/Post Punk", new short[]{(short) 100, (short) 300, (short) 200, (short) 400, (short) 200, (short) 400, (short) 0, (short) -100, (short) 200, (short) -100});
        a.put("流行朋克/Punk - Pop", new short[]{(short) -300, (short) -200, (short) 300, (short) -100, (short) -200, (short) 300, (short) 400, (short) 500, (short) 400, (short) 400});
        a.put("后垃圾/Post Grunge", new short[]{(short) 0, (short) 200, (short) 300, (short) 200, (short) -100, (short) -100, (short) 200, (short) 200, (short) 300, (short) 0});
        a.put("迷幻/Psychedelic", new short[]{(short) 100, (short) 300, (short) 300, (short) 400, (short) 200, (short) 100, (short) -100, (short) 300, (short) 200, (short) 100});
        a.put("哥特/Gothic", new short[]{(short) 300, (short) 300, (short) 300, (short) 300, (short) 200, (short) 100, (short) 300, (short) 400, (short) 400, (short) 300});
        a.put("车库/Garage", new short[]{(short) 100, (short) 200, (short) 300, (short) 200, (short) 200, (short) 100, (short) 100, (short) 200, (short) 400, (short) 400});
        a.put("实验音乐/Experimental", new short[]{(short) -100, (short) 0, (short) 100, (short) 0, (short) 0, (short) 0, (short) 0, (short) 100, (short) 100, (short) -100});
        a.put("新浪潮/New Wave", new short[]{(short) 100, (short) 200, (short) 200, (short) 300, (short) 200, (short) 300, (short) 0, (short) -100, (short) 100, (short) -100});
        a.put("垃圾乐/Grunge", new short[]{(short) -100, (short) 200, (short) 300, (short) 100, (short) 0, (short) 300, (short) 300, (short) 200, (short) 300, (short) 300});
        a.put("悲核/Sadcore", new short[]{(short) 0, (short) 200, (short) 100, (short) 0, (short) 0, (short) 100, (short) 200, (short) 200, (short) 100, (short) 0});
        a.put("EMO/EMO", new short[]{(short) 0, (short) 200, (short) 200, (short) 300, (short) 200, (short) 200, (short) 200, (short) 300, (short) 400, (short) 200});
        a.put("Blues Rock/Blues - Rock", new short[]{(short) -200, (short) 100, (short) 200, (short) 200, (short) 0, (short) 200, (short) 300, (short) 200, (short) 200, (short) 0});
        a.put("Funk/Funk", new short[]{(short) 400, (short) 600, (short) 600, (short) 300, (short) -100, (short) 0, (short) 0, (short) 400, (short) 300, (short) 0});
        a.put("民谣/Folk", new short[]{(short) -100, (short) -100, (short) 100, (short) -100, (short) 200, (short) -200, (short) 100, (short) 0, (short) 400, (short) 400});
        a.put("校园民谣/Campus Folk", new short[]{(short) -400, (short) -200, (short) -100, (short) -100, (short) -100, (short) -200, (short) 0, (short) 200, (short) -200, (short) 100});
        a.put("城市民谣/Urban Folk", new short[]{(short) -200, (short) -200, (short) -100, (short) -100, (short) 0, (short) 200, (short) 100, (short) 200, (short) 200, (short) -100});
        a.put("新民谣/Neo - Folk", new short[]{(short) -200, (short) -300, (short) 100, (short) -100, (short) 200, (short) 200, (short) 100, (short) 100, (short) 300, (short) 300});
        a.put("乡村/Country", new short[]{(short) -200, (short) -300, (short) -100, (short) -100, (short) 0, (short) 200, (short) -200, (short) 200, (short) 200, (short) -200});
        a.put("蓝草/Bluegrass", new short[]{(short) -200, (short) -100, (short) 0, (short) 100, (short) 0, (short) 200, (short) 200, (short) 200, (short) 200, (short) 100});
        a.put("凯尔特/Celtic", new short[]{(short) -100, (short) 0, (short) 100, (short) -100, (short) 200, (short) 100, (short) 300, (short) 300, (short) 300, (short) 200});
        a.put("Anti - folk/Anti - folk", new short[]{(short) -100, (short) 200, (short) 100, (short) 100, (short) 200, (short) 100, (short) 100, (short) 0, (short) 300, (short) 200});
        a.put("舞曲/Dance", new short[]{(short) 1000, (short) 1500, (short) 900, (short) 400, (short) 300, (short) -400, (short) 1300, (short) -500, (short) 1300, (short) 700});
        a.put("超重低音/Bass", new short[]{(short) 1000, (short) 1300, (short) 300, (short) -500, (short) -700, (short) 100, (short) 300, (short) 200, (short) 500, (short) 300});
        a.put("迪士高/Disco", new short[]{(short) 0, (short) 400, (short) 500, (short) 100, (short) 0, (short) 400, (short) 600, (short) 300, (short) -200, (short) 500});
        a.put("DJ/DJ", new short[]{(short) -200, (short) 0, (short) 500, (short) 600, (short) 0, (short) 200, (short) 100, (short) 300, (short) 600, (short) 400});
        a.put("电子舞曲/E-Dance", new short[]{(short) 100, (short) 400, (short) 200, (short) 400, (short) 0, (short) -200, (short) 400, (short) 200, (short) 500, (short) 500});
        a.put("混音/Remix", new short[]{(short) 100, (short) 200, (short) 100, (short) 300, (short) 0, (short) 200, (short) 200, (short) 0, (short) 300, (short) 200});
        a.put("碎拍/Breakbeat", new short[]{(short) -100, (short) 200, (short) 200, (short) 300, (short) 100, (short) 100, (short) 100, (short) 300, (short) 300, (short) 300});
        a.put("House/House", new short[]{(short) -100, (short) 0, (short) 300, (short) 400, (short) -100, (short) 100, (short) 200, (short) 400, (short) 500, (short) 300});
        a.put("Trance/Trance", new short[]{(short) 300, (short) 400, (short) 400, (short) 100, (short) -100, (short) 300, (short) 500, (short) 300, (short) 400, (short) 500});
        a.put("Progressive House/Progressive-House", new short[]{(short) -100, (short) 0, (short) 300, (short) 300, (short) 100, (short) 300, (short) 400, (short) 400, (short) 400, (short) 300});
        a.put("Progressive Trance/Progressive-Trance", new short[]{(short) 200, (short) 300, (short) 400, (short) 100, (short) -100, (short) 300, (short) 500, (short) 400, (short) 400, (short) 500});
        a.put("Drum & Bass/Drum & Bass", new short[]{(short) 200, (short) 400, (short) 400, (short) 400, (short) 0, (short) 100, (short) 300, (short) 0, (short) 200, (short) 0});
        a.put("Club Dance/Club-Dance", new short[]{(short) 100, (short) 300, (short) 300, (short) 400, (short) -200, (short) -200, (short) 300, (short) 100, (short) 500, (short) 300});
        a.put("电子/Electronica", new short[]{(short) -200, (short) -100, (short) -200, (short) -200, (short) -100, (short) 100, (short) 200, (short) 400, (short) 400, (short) 300});
        a.put("驰放/Chillout", new short[]{(short) -100, (short) -100, (short) -200, (short) -200, (short) -100, (short) 200, (short) 200, (short) 300, (short) 200, (short) 100});
        a.put("Trip-Hop/Trip-Hop", new short[]{(short) -200, (short) -100, (short) -100, (short) 0, (short) 0, (short) 100, (short) 100, (short) 200, (short) 100, (short) 0});
        a.put("沙发/Lounge", new short[]{(short) -200, (short) -200, (short) -100, (short) -200, (short) 100, (short) 200, (short) 200, (short) 100, (short) 100, (short) 0});
        a.put("Darkwave/Darkwave", new short[]{(short) 100, (short) 100, (short) 100, (short) 200, (short) 200, (short) 100, (short) 200, (short) 0, (short) -100, (short) -100});
        a.put("Ambient/Ambient", new short[]{(short) -200, (short) 100, (short) 200, (short) 0, (short) -100, (short) 100, (short) 200, (short) 200, (short) 400, (short) 400});
        a.put("古典/Classic", new short[]{(short) -300, (short) 100, (short) 700, (short) 800, (short) 500, (short) 400, (short) 300, (short) 700, (short) 200, (short) 500});
        a.put("室内乐/Chamber Music", new short[]{(short) 200, (short) 0, (short) 300, (short) -100, (short) 200, (short) 0, (short) 100, (short) 300, (short) 400, (short) 300});
        a.put("独奏/Solo", new short[]{(short) 200, (short) 0, (short) 400, (short) 0, (short) 0, (short) -100, (short) 0, (short) 500, (short) 500, (short) 0});
        a.put("歌剧/Opera", new short[]{(short) 300, (short) -100, (short) 200, (short) 200, (short) 100, (short) 300, (short) 300, (short) 500, (short) 400, (short) 200});
        a.put("艺术歌曲/Art Music", new short[]{(short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 300, (short) 400, (short) 400, (short) 300, (short) 100});
        a.put("交响乐/Symphony", new short[]{(short) 300, (short) 100, (short) 300, (short) 200, (short) 0, (short) 100, (short) 200, (short) 500, (short) 600, (short) 300});
        a.put("协奏曲/Concerto", new short[]{(short) 200, (short) 0, (short) 200, (short) 100, (short) 100, (short) 0, (short) 200, (short) 100, (short) 100, (short) 200});
        a.put("奏鸣曲/Sonata", new short[]{(short) 0, (short) 0, (short) 0, (short) 0, (short) 200, (short) 300, (short) 400, (short) 400, (short) 400, (short) 200});
        a.put("现代基督/Contemporary Christian", new short[]{(short) 400, (short) 400, (short) 200, (short) 100, (short) -200, (short) -100, (short) 200, (short) 200, (short) 400, (short) 400});
        a.put("爵士/Jazz", new short[]{(short) 200, (short) 500, (short) 400, (short) 0, (short) 0, (short) 300, (short) 300, (short) -100, (short) 400, (short) 300});
        a.put("人声爵士/Vocal Jazz", new short[]{(short) 100, (short) 200, (short) 200, (short) 0, (short) 0, (short) 500, (short) 500, (short) -100, (short) 400, (short) 300});
        a.put("器乐爵士/Jazz instrumental", new short[]{(short) 200, (short) 600, (short) 400, (short) 0, (short) 0, (short) 400, (short) 400, (short) -100, (short) 600, (short) 300});
        a.put("大乐队/BigBand", new short[]{(short) 200, (short) 500, (short) 300, (short) 100, (short) 100, (short) 400, (short) 400, (short) 0, (short) 300, (short) 300});
        a.put("波谱/Bop", new short[]{(short) 300, (short) 400, (short) 400, (short) 0, (short) 0, (short) 0, (short) -100, (short) -200, (short) 200, (short) 100});
        a.put("后波谱/HardBop", new short[]{(short) 200, (short) 300, (short) 300, (short) 100, (short) 0, (short) 0, (short) 200, (short) 200, (short) 100, (short) 0});
        a.put("冷爵士/Cool Jazz", new short[]{(short) 300, (short) 300, (short) -100, (short) 0, (short) 0, (short) 300, (short) 300, (short) -100, (short) 300, (short) 400});
        a.put("自由爵士/Free Jazz", new short[]{(short) 200, (short) 100, (short) 100, (short) 0, (short) 0, (short) 200, (short) 200, (short) 0, (short) 200, (short) 100});
        a.put("融合爵士/Fusion Jazz", new short[]{(short) 100, (short) 0, (short) 100, (short) 0, (short) 0, (short) 100, (short) 200, (short) 200, (short) 100, (short) 0});
        a.put("酸爵士/Acid Jazz", new short[]{(short) 200, (short) 400, (short) 300, (short) 100, (short) 100, (short) 300, (short) 300, (short) 0, (short) 300, (short) 200});
        a.put("Bossa Nova/Bossa - Nova", new short[]{(short) 100, (short) 300, (short) 400, (short) 100, (short) 0, (short) 500, (short) 500, (short) 0, (short) 300, (short) 200});
        a.put("人声/Vocal", new short[]{(short) -1100, (short) -500, (short) 0, (short) -600, (short) 0, (short) 600, (short) 0, (short) 600, (short) 400, (short) 1000});
        b.addAll(a.keySet());
    }

    public static List<String> a() {
        return b;
    }

    public static String a(int i) {
        if (i >= 1000) {
            return EqualizerHandpickFragment.KEY_CUSTOM;
        }
        List list = b;
        if (i < 0) {
            i = 0;
        }
        return (String) list.get(i);
    }

    public static int a(String str) {
        int indexOf = a().indexOf(str);
        if (indexOf == -1) {
            return 1000;
        }
        return indexOf;
    }

    public static short[] b(String str) {
        return (short[]) a.get(str);
    }

    public static String b() {
        return "普通/Normal";
    }
}

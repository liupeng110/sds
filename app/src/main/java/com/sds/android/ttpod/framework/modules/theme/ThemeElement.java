package com.sds.android.ttpod.framework.modules.theme;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ThemeElement {
    static final String ARTIST = "Artist";
    static final String ARTISTMASK = "ArtistMask";
    static final String AVATAR = "Avatar";
    static final String AVATAR_FRAME = "AvatarFrame";
    static final String AVATAR_MASK = "AvatarMask";
    static final String AVATAR_TEXT = "AvatarText";
    static final String AZBAR = "AZBar";
    static final String AZBAR_AREA = "AZBarArea";
    static final String AZBAR_TEXT = "AZBarText";
    static final String BACKGROUND = "Background";
    static final String BACKGROUND_IMAGE = "BackgroundImage";
    public static final String BACKGROUND_MASK = "BackgroundMaskColor";
    static final String BOTTOM_BAR = "BottomBar";
    public static final String CARD_BACKGROUND = buildPublicId(PANEL_CARD, BACKGROUND);
    public static final String CARD_CONTROL_BACKGROUND = buildPublicId(PANEL_CARD, CONTROL_BACKGROUND);
    public static final String CARD_CONTROL_TEXT = buildPublicId(PANEL_CARD, CONTROL_TEXT);
    public static final String CARD_SUB_TEXT = buildPublicId(PANEL_CARD, SUB_TEXT);
    public static final String CARD_TEXT = buildPublicId(PANEL_CARD, "Text");
    static final String CLEAR_IMG = "common_clear";
    public static final String COMMON_BOTTOM_BAR = buildPublicId(PANEL_COMMON, BOTTOM_BAR);
    public static final String COMMON_CLEAR_IMAGE = buildPublicId(PANEL_COMMON, CLEAR_IMG);
    public static final String COMMON_CONTENT = buildPublicId(PANEL_COMMON, CONTENT);
    public static final String COMMON_CONTENT_TEXT = buildPublicId(PANEL_COMMON, CONTENT_TEXT);
    public static final String COMMON_EMOTION_IMAGE = buildPublicId(PANEL_COMMON, INPUT_EMOTION_IMG);
    public static final String COMMON_INDICATOR = buildPublicId(PANEL_COMMON, INDICATOR);
    public static final String COMMON_KEYBOARD_IMAGE = buildPublicId(PANEL_COMMON, INPUT_KEYBOARD_IMG);
    public static final String COMMON_PROGRESS_BAR = buildPublicId(PANEL_COMMON, PROGRESS_BAR);
    public static final String COMMON_PROGRESS_DRAWABLE = buildPublicId(PANEL_COMMON, PROGRESS_DRAWABLE);
    public static final String COMMON_SECOND_PROGRESS_DRAWABLE = buildPublicId(PANEL_COMMON, SECOND_PROGRESS_DRAWABLE);
    public static final String COMMON_SEND_IMAGE = buildPublicId(PANEL_COMMON, INPUT_SEND_IMG);
    public static final String COMMON_SEPARATOR = buildPublicId(PANEL_COMMON, SEPARATOR);
    public static final String COMMON_SUB_BAR = buildPublicId(PANEL_COMMON, "SubBar");
    public static final String COMMON_SUB_CONTENT_TEXT = buildPublicId(PANEL_COMMON, SUB_CONTENT_TEXT);
    public static final String COMMON_SUB_TITLE_TEXT = buildPublicId(PANEL_COMMON, SUB_TITLE_TEXT);
    public static final String COMMON_TITLE_TEXT = buildPublicId(PANEL_COMMON, TITLE_TEXT);
    public static final String COMMON_TOP_BAR = buildPublicId(PANEL_COMMON, "TopBar");
    static final String CONTENT = "Content";
    static final String CONTENT_TEXT = "ContentText";
    static final String CONTROL_BACKGROUND = "ControlBackground";
    static final String CONTROL_TEXT = "ControlText";
    public static final String HOME_ALBUM_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_ALBUM);
    public static final String HOME_APP_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_APP);
    public static final String HOME_ARROW_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_ARROW);
    public static final String HOME_ARTIST_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_ARTIST);
    public static final String HOME_AVATAR_FRAME = buildPublicId(PANEL_HOME, AVATAR_FRAME);
    public static final String HOME_BACKGROUND = buildPublicId(PANEL_HOME, BACKGROUND);
    static final String HOME_BACKGROUND_BLUR = "HomeBackgroundBlur";
    public static final String HOME_CHANGE_BACKGROUND_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_CHANGE_BACKGROUND);
    public static final String HOME_DOWNLOAD_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_DOWNLOAD);
    public static final String HOME_FAVORITE_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_FAVORITE);
    public static final String HOME_FOLDER_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_FOLDER);
    public static final String HOME_IMG_ALBUM = "home_album";
    static final String HOME_IMG_APP = "home_app";
    static final String HOME_IMG_ARROW = "home_arrow";
    public static final String HOME_IMG_ARTIST = "home_artist";
    static final String HOME_IMG_CHANGE_BACKGROUND = "home_change_background";
    public static final String HOME_IMG_DOWNLOAD = "home_download";
    public static final String HOME_IMG_FAVORITE = "home_favorite";
    public static final String HOME_IMG_FOLDER = "home_folder";
    static final String HOME_IMG_MENU = "home_menu";
    static final String HOME_IMG_MUSIC = "home_music";
    static final String HOME_IMG_NEW = "home_new";
    static final String HOME_IMG_NEW_SONG_LIST = "home_new_song_list";
    static final String HOME_IMG_RANDOM_PLAY = "home_random_play";
    public static final String HOME_IMG_RECENT_ADD = "home_recent_add";
    public static final String HOME_IMG_RECENT_PLAY = "home_recent_play";
    static final String HOME_IMG_SCAN = "home_scan";
    public static final String HOME_IMG_SONG_LIST = "home_song_list";
    static final String HOME_IMG_WIFI_ONLY_OFF = "home_wifi_only_off";
    static final String HOME_IMG_WIFI_ONLY_ON = "home_wifi_only_on";
    public static final String HOME_MENU_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_MENU);
    public static final String HOME_MUSIC_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_MUSIC);
    public static final String HOME_NEW_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_NEW);
    public static final String HOME_NEW_SONG_LIST_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_NEW_SONG_LIST);
    public static final String HOME_RANDOM_PLAY_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_RANDOM_PLAY);
    public static final String HOME_RECENT_ADD_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_RECENT_ADD);
    public static final String HOME_RECENT_PLAY_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_RECENT_PLAY);
    public static final String HOME_SCAN_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_SCAN);
    public static final String HOME_SONG_LIST_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_SONG_LIST);
    public static final String HOME_SUB_TEXT = buildPublicId(PANEL_HOME, SUB_TEXT);
    public static final String HOME_TEXT = buildPublicId(PANEL_HOME, "Text");
    public static final String HOME_WIFI_ONLY_OFF_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_WIFI_ONLY_OFF);
    public static final String HOME_WIFI_ONLY_ON_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_WIFI_ONLY_ON);
    private static final String IMAGE_FLAG = "_IMG";
    static final String IMAGE_HIGHLIGHT_SUFFIX = "_h";
    static final String IMAGE_NORMAL_SUFFIX = "_n";
    static final String INDICATOR = "Indicator";
    static final String INPUT_EMOTION_IMG = "input_box_emotion";
    static final String INPUT_KEYBOARD_IMG = "input_box_keyboard";
    static final String INPUT_SEND_IMG = "input_box_send";
    static final String ITEM_BACKGROUND = "ItemBackground";
    static final String MASK = "Mask";
    public static final String NAVIGATION_BAR_BACKGROUND = "NavigationBarBackground";
    public static final String PANEL_CARD = "Card";
    public static final String PANEL_COMMON = "Common";
    public static final String PANEL_HOME = "Home";
    public static final String PANEL_PLAYER_MUSIC_LIST = "PlayerMusicList";
    public static final String PANEL_PLAY_BAR = "PlayBar";
    public static final String PANEL_SETTING = "Setting";
    public static final String PANEL_SONG_LIST_ITEM = "SongListItem";
    public static final String PANEL_SUB_BAR = "SubBar";
    public static final String PANEL_TILE = "Tile";
    public static final String PANEL_TOP_BAR = "TopBar";
    public static final String PLAYER_MUSIC_LIST_AZBAR = buildPublicId(PANEL_PLAYER_MUSIC_LIST, AZBAR_AREA);
    public static final String PLAYER_MUSIC_LIST_AZBAR_BACKGROUND = buildPublicId(PANEL_PLAYER_MUSIC_LIST, AZBAR);
    public static final String PLAYER_MUSIC_LIST_AZBAR_TEXT = buildPublicId(PANEL_PLAYER_MUSIC_LIST, AZBAR_TEXT);
    public static final String PLAYER_MUSIC_LIST_BACKGROUND = buildPublicId(PANEL_PLAYER_MUSIC_LIST, BACKGROUND);
    static final String PLAYER_MUSIC_LIST_IMG_INLOVE = "player_music_list_item_inlove";
    static final String PLAYER_MUSIC_LIST_IMG_MENU = "player_music_list_item_menu";
    static final String PLAYER_MUSIC_LIST_IMG_UNLOVE = "player_music_list_item_unlove";
    public static final String PLAYER_MUSIC_LIST_INDICATOR = buildPublicId(PANEL_PLAYER_MUSIC_LIST, INDICATOR);
    public static final String PLAYER_MUSIC_LIST_INLOVE_IMAGE = buildPublicId(PANEL_PLAYER_MUSIC_LIST, PLAYER_MUSIC_LIST_IMG_INLOVE);
    public static final String PLAYER_MUSIC_LIST_MENU_IMAGE = buildPublicId(PANEL_PLAYER_MUSIC_LIST, PLAYER_MUSIC_LIST_IMG_MENU);
    public static final String PLAYER_MUSIC_LIST_SEPARATOR = buildPublicId(PANEL_PLAYER_MUSIC_LIST, SEPARATOR);
    public static final String PLAYER_MUSIC_LIST_SUB_TEXT = buildPublicId(PANEL_PLAYER_MUSIC_LIST, SUB_TEXT);
    public static final String PLAYER_MUSIC_LIST_TEXT = buildPublicId(PANEL_PLAYER_MUSIC_LIST, "Text");
    public static final String PLAYER_MUSIC_LIST_UNLOVE_IMAGE = buildPublicId(PANEL_PLAYER_MUSIC_LIST, PLAYER_MUSIC_LIST_IMG_UNLOVE);
    public static final String PLAY_BAR_ARTIST = buildPublicId(PANEL_PLAY_BAR, ARTIST);
    public static final String PLAY_BAR_ARTISTMASK = buildPublicId(PANEL_PLAY_BAR, ARTISTMASK);
    public static final String PLAY_BAR_ARTIS_MASK_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_ARTIST_MASK);
    public static final String PLAY_BAR_BACKGROUND = buildPublicId(PANEL_PLAY_BAR, BACKGROUND);
    public static final String PLAY_BAR_DEF_ARTIST_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_DEF_ARTIST);
    static final String PLAY_BAR_IMG_ARTIST_MASK = "play_bar_artist_mask";
    static final String PLAY_BAR_IMG_BACKGROUND = "play_bar_bkg";
    static final String PLAY_BAR_IMG_BACKGROUND2 = "play_bar_bkg2";
    static final String PLAY_BAR_IMG_DEF_ARTIST = "play_bar_def_artist";
    static final String PLAY_BAR_IMG_PAUSE = "play_bar_pause";
    static final String PLAY_BAR_IMG_PLAY = "play_bar_play";
    static final String PLAY_BAR_IMG_PLAY_NEXT = "play_bar_play_next";
    static final String PLAY_BAR_IMG_SIDEBAR = "play_bar_sidebar";
    public static final String PLAY_BAR_NEXT_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_PLAY_NEXT);
    public static final String PLAY_BAR_PAUSE_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_PAUSE);
    public static final String PLAY_BAR_PLAY_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_PLAY);
    public static final String PLAY_BAR_SIDEBAR_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_SIDEBAR);
    public static final String PLAY_BAR_SUB_TEXT = buildPublicId(PANEL_PLAY_BAR, SUB_TEXT);
    public static final String PLAY_BAR_TEXT = buildPublicId(PANEL_PLAY_BAR, "Text");
    public static final String PLAY_BAR_TIME_TEXT = buildPublicId(PANEL_PLAY_BAR, TIME_TEXT);
    static final String POP_TEXT = "PopText";
    static final String PROGRESS_BAR = "ProgressBar";
    static final String PROGRESS_DRAWABLE = "Progress";
    static final String QUIT_BUTTON_BACKGROUND = "QuitButtonBackground";
    static final String QUIT_BUTTON_TEXT = "QuitButtonText";
    static final String SECOND_PROGRESS_DRAWABLE = "SecondProgress";
    static final String SEPARATOR = "Separator";
    private static final String SEPARATOR_TOKEN = ":";
    public static final String SETTING_ARROW_DOWN_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_ARROW_DOWN);
    public static final String SETTING_ARROW_UP_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_ARROW_UP);
    public static final String SETTING_AUDIO_EFFECT_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_AUDIO_EFFECT);
    public static final String SETTING_AVATAR = buildPublicId(PANEL_SETTING, AVATAR);
    public static final String SETTING_AVATAR_FRAME = buildPublicId(PANEL_SETTING, AVATAR_FRAME);
    public static final String SETTING_AVATAR_FRAME_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_AVATAR_FRAME);
    public static final String SETTING_AVATAR_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_AVATAR);
    public static final String SETTING_AVATAR_MASK = buildPublicId(PANEL_SETTING, AVATAR_MASK);
    public static final String SETTING_AVATAR_MASK_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_AVATAR_MASK);
    public static final String SETTING_AVATAR_TEXT = buildPublicId(PANEL_SETTING, AVATAR_TEXT);
    public static final String SETTING_BACKGROUND = buildPublicId(PANEL_SETTING, BACKGROUND);
    public static final String SETTING_CHANGE_BACKGROUND_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_CHANGE_BACKGROUND);
    public static final String SETTING_EXIT_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_EXIT);
    public static final String SETTING_HORIZONTAL_BACKGROUND_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_HORIZONTAL_BACKGROUND);
    public static final String SETTING_ICON_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_ICON);
    static final String SETTING_IMG_ARROW_DOWN = "setting_arrow_down";
    static final String SETTING_IMG_ARROW_UP = "setting_arrow_up";
    static final String SETTING_IMG_AUDIO_EFFECT = "setting_audio_effect";
    static final String SETTING_IMG_AVATAR = "avatar";
    static final String SETTING_IMG_AVATAR_FRAME = "avatar_frame";
    static final String SETTING_IMG_AVATAR_MASK = "avatar_mask";
    static final String SETTING_IMG_BACKGROUND = "setting_background";
    static final String SETTING_IMG_CHANGE_BACKGROUND = "setting_change_background";
    static final String SETTING_IMG_EXIT = "setting_exit";
    static final String SETTING_IMG_HORIZONTAL_BACKGROUND = "setting_horizontal_background";
    static final String SETTING_IMG_ICON = "setting_icon";
    static final String SETTING_IMG_KTV = "setting_ktv";
    static final String SETTING_IMG_MENU_INDICATOR_BACKGROUND = "setting_indicator_background";
    static final String SETTING_IMG_NEW = "setting_new";
    static final String SETTING_IMG_PLAY_LOOP = "setting_play_loop";
    static final String SETTING_IMG_PLAY_SEQUENCE = "setting_play_sequence";
    static final String SETTING_IMG_PLAY_SHUFFLE = "setting_play_shuffle";
    static final String SETTING_IMG_PLAY_SINGLE = "setting_play_single";
    static final String SETTING_IMG_RECOGNIZE = "setting_recognize";
    static final String SETTING_IMG_RECOMMEND = "setting_recommend";
    static final String SETTING_IMG_SCANNING = "setting_scanning";
    static final String SETTING_IMG_SHARE_SONG = "setting_pc_share_song";
    static final String SETTING_IMG_SHOW = "setting_show";
    static final String SETTING_IMG_SLEEP = "setting_sleep";
    static final String SETTING_IMG_THEME = "setting_theme";
    static final String SETTING_IMG_TRAFFIC = "setting_traffic";
    static final String SETTING_IMG_TTPOD_FM = "setting_ttpod_fm";
    public static final String SETTING_ITEM_BACKGROUND = buildPublicId(PANEL_SETTING, ITEM_BACKGROUND);
    public static final String SETTING_KTV_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_KTV);
    public static final String SETTING_MENU_INDICATOR_BACKGROUND_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_MENU_INDICATOR_BACKGROUND);
    public static final String SETTING_NEW_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_NEW);
    public static final String SETTING_PLAY_LOOP_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_PLAY_LOOP);
    public static final String SETTING_PLAY_SEQUENCE_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_PLAY_SEQUENCE);
    public static final String SETTING_PLAY_SHUFFLE_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_PLAY_SHUFFLE);
    public static final String SETTING_PLAY_SINGLE_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_PLAY_SINGLE);
    public static final String SETTING_QUIT_BUTTON_BACKGROUND = buildPublicId(PANEL_SETTING, QUIT_BUTTON_BACKGROUND);
    public static final String SETTING_QUIT_BUTTON_TEXT = buildPublicId(PANEL_SETTING, QUIT_BUTTON_TEXT);
    public static final String SETTING_RECOGNIZE_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_RECOGNIZE);
    public static final String SETTING_RECOMMEND_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_RECOMMEND);
    public static final String SETTING_SCANNING_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_SCANNING);
    public static final String SETTING_SEPARATOR = buildPublicId(PANEL_SETTING, SEPARATOR);
    public static final String SETTING_SHARE_SONG_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_SHARE_SONG);
    public static final String SETTING_SHOW_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_SHOW);
    public static final String SETTING_SLEEP_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_SLEEP);
    public static final String SETTING_TEXT = buildPublicId(PANEL_SETTING, "Text");
    public static final String SETTING_THEME_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_THEME);
    public static final String SETTING_TIME_BUTTON_BACKGROUND = buildPublicId(PANEL_SETTING, TIME_BUTTON_BACKGROUND);
    public static final String SETTING_TIME_BUTTON_TEXT = buildPublicId(PANEL_SETTING, TIME_BUTTON_TEXT);
    public static final String SETTING_TRAFFIC_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_TRAFFIC);
    public static final String SETTING_TTPOD_FM_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_TTPOD_FM);
    public static final String SONG_LIST_ITEM_AZBAR = buildPublicId(PANEL_SONG_LIST_ITEM, AZBAR);
    public static final String SONG_LIST_ITEM_AZBAR_AREA = buildPublicId(PANEL_SONG_LIST_ITEM, AZBAR_AREA);
    public static final String SONG_LIST_ITEM_AZBAR_TEXT = buildPublicId(PANEL_SONG_LIST_ITEM, AZBAR_TEXT);
    public static final String SONG_LIST_ITEM_BACKGROUND = buildPublicId(PANEL_SONG_LIST_ITEM, BACKGROUND);
    public static final String SONG_LIST_ITEM_DRAG_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_DRAG);
    static final String SONG_LIST_ITEM_IMG_DRAG = "song_list_item_drag";
    static final String SONG_LIST_ITEM_IMG_INDICATOR = "song_list_item_indicator";
    static final String SONG_LIST_ITEM_IMG_INLOVE = "song_list_item_inlove";
    static final String SONG_LIST_ITEM_IMG_MENU = "song_list_item_menu";
    static final String SONG_LIST_ITEM_IMG_PAUSE = "song_list_item_pause";
    static final String SONG_LIST_ITEM_IMG_PLAY = "song_list_item_play";
    static final String SONG_LIST_ITEM_IMG_UNLOVE = "song_list_item_unlove";
    public static final String SONG_LIST_ITEM_INDICATOR = buildPublicId(PANEL_SONG_LIST_ITEM, INDICATOR);
    public static final String SONG_LIST_ITEM_INLOVE_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_INLOVE);
    public static final String SONG_LIST_ITEM_MENU_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_MENU);
    public static final String SONG_LIST_ITEM_PAUSE_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_PAUSE);
    public static final String SONG_LIST_ITEM_PLAY_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_PLAY);
    public static final String SONG_LIST_ITEM_SUB_TEXT = buildPublicId(PANEL_SONG_LIST_ITEM, SUB_TEXT);
    public static final String SONG_LIST_ITEM_TEXT = buildPublicId(PANEL_SONG_LIST_ITEM, "Text");
    public static final String SONG_LIST_ITEM_UNLOVE_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_UNLOVE);
    public static final String SONG_LIST_POP_BACKGROUND = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_POP_BACKGROUND_IMG);
    static final String SONG_LIST_POP_BACKGROUND_IMG = "song_list_pop_bkg";
    public static final String SONG_LIST_POP_TEXT = buildPublicId(PANEL_SONG_LIST_ITEM, POP_TEXT);
    public static final String STATUS_BAR_BACKGROUND = "StatusBarBackground";
    public static final String STATUS_BAR_MODE = "StatusBar";
    static final String SUB_BAR = "SubBar";
    public static final String SUB_BAR_BACKGROUND = buildPublicId("SubBar", BACKGROUND);
    static final String SUB_BAR_IMG_BACKGROUND = "sub_bar_bkg";
    static final String SUB_BAR_IMG_INDICATOR = "sub_bar_indicator";
    public static final String SUB_BAR_INDICATOR = buildPublicId("SubBar", INDICATOR);
    public static final String SUB_BAR_TEXT = buildPublicId("SubBar", "Text");
    static final String SUB_CONTENT_TEXT = "SubContentText";
    static final String SUB_TEXT = "SubText";
    static final String SUB_TITLE_TEXT = "SubTitleText";
    static final String TEXT = "Text";
    private static final String TEXT_FLAG = "Text";
    public static final String TILE_ACTIVITY_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_ACTIVITY);
    public static final String TILE_BACKGROUND = buildPublicId(PANEL_TILE, BACKGROUND);
    public static final String TILE_CATEGORY_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_CATEGORY);
    public static final String TILE_DAILY_FIVE_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_DAILY_FIVE);
    public static final String TILE_DISCOVERY_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_DISCOVERY);
    public static final String TILE_EDITOR_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_EDITOR);
    public static final String TILE_EMOTION_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_EMOTION);
    public static final String TILE_EXPERT_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_EXPERT);
    public static final String TILE_HUMOR_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_HUMOR);
    static final String TILE_IMG_ACTIVITY = "tile_activity";
    static final String TILE_IMG_CATEGORY = "tile_category";
    static final String TILE_IMG_DAILY_FIVE = "tile_daily_five";
    static final String TILE_IMG_DISCOVERY = "tile_discovery";
    static final String TILE_IMG_EDITOR = "tile_editor";
    static final String TILE_IMG_EMOTION = "tile_emotion";
    static final String TILE_IMG_EXPERT = "tile_expert";
    static final String TILE_IMG_HUMOR = "tile_humor";
    static final String TILE_IMG_MUSIC_CIRCLE = "tile_music_circle";
    static final String TILE_IMG_MV = "tile_mv";
    static final String TILE_IMG_NET_RADIO = "tile_net_radio";
    static final String TILE_IMG_ORIGINAL = "tile_original";
    static final String TILE_IMG_POPULAR = "tile_popular";
    static final String TILE_IMG_RADIO = "tile_radio";
    static final String TILE_IMG_RANK = "tile_rank";
    static final String TILE_IMG_SINGER = "tile_singer";
    static final String TILE_IMG_STAR = "tile_star";
    static final String TILE_IMG_TV = "tile_tv";
    public static final String TILE_MASK = buildPublicId(PANEL_TILE, MASK);
    public static final String TILE_MUSIC_CIRCLE_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_MUSIC_CIRCLE);
    public static final String TILE_MV_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_MV);
    public static final String TILE_NET_RADIO_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_NET_RADIO);
    public static final String TILE_ORIGINAL_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_ORIGINAL);
    public static final String TILE_POPULAR_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_POPULAR);
    public static final String TILE_RADIO_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_RADIO);
    public static final String TILE_RANK_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_RANK);
    public static final String TILE_SINGER_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_SINGER);
    public static final String TILE_STAR_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_STAR);
    public static final String TILE_SUB_TEXT = buildPublicId(PANEL_TILE, SUB_TEXT);
    public static final String TILE_TEXT = buildPublicId(PANEL_TILE, "Text");
    public static final String TILE_TV_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_TV);
    static final String TIME_BUTTON_BACKGROUND = "TimeButtonBackground";
    static final String TIME_BUTTON_TEXT = "TimeButtonText";
    static final String TIME_TEXT = "TimeText";
    static final String TITLE_TEXT = "TitleText";
    static final String TOP_BAR = "TopBar";
    public static final String TOP_BAR_ADD_FRIENDS_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_ADD_FRIENDS);
    public static final String TOP_BAR_ADD_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_ADD);
    public static final String TOP_BAR_BACKGROUND = buildPublicId("TopBar", BACKGROUND);
    public static final String TOP_BAR_BACK_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_BACK);
    public static final String TOP_BAR_DOWNLOAD_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_DOWNLOAD);
    public static final String TOP_BAR_EDIT_DONE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_EDIT_DONE);
    public static final String TOP_BAR_EDIT_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_EDIT);
    static final String TOP_BAR_IMG_ADD = "top_bar_add";
    static final String TOP_BAR_IMG_ADD_FRIENDS = "top_bar_add_friends";
    static final String TOP_BAR_IMG_BACK = "top_bar_back";
    static final String TOP_BAR_IMG_BACKGROUND = "top_bar_bkg";
    static final String TOP_BAR_IMG_DOWNLOAD = "top_bar_download";
    static final String TOP_BAR_IMG_EDIT = "top_bar_edit";
    static final String TOP_BAR_IMG_EDIT_DONE = "top_bar_edit_done";
    static final String TOP_BAR_IMG_INDICATOR = "top_bar_indicator";
    static final String TOP_BAR_IMG_INFOMATION = "top_bar_information";
    static final String TOP_BAR_IMG_LIST_INLOVE = "top_bar_list_inlove";
    static final String TOP_BAR_IMG_LIST_UNLOVE = "top_bar_list_unlove";
    static final String TOP_BAR_IMG_MENU = "top_bar_menu";
    static final String TOP_BAR_IMG_MESSAGE = "top_bar_message";
    static final String TOP_BAR_IMG_MORE = "top_bar_more";
    static final String TOP_BAR_IMG_ORDER = "top_bar_order";
    static final String TOP_BAR_IMG_RECOGNIZE_HISTORY = "top_bar_recognize_history";
    static final String TOP_BAR_IMG_REFRESH = "top_bar_refresh";
    static final String TOP_BAR_IMG_SEARCH = "top_bar_search";
    static final String TOP_BAR_IMG_SHARE = "top_bar_share";
    public static final String TOP_BAR_INDICATOR = buildPublicId("TopBar", INDICATOR);
    public static final String TOP_BAR_INFORMATION_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_INFOMATION);
    public static final String TOP_BAR_INLOVE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_LIST_INLOVE);
    public static final String TOP_BAR_MENU_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_MENU);
    public static final String TOP_BAR_MESSAGE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_MESSAGE);
    public static final String TOP_BAR_MORE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_MORE);
    public static final String TOP_BAR_ORDER_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_ORDER);
    public static final String TOP_BAR_RECOGNIZE_HISTORY_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_RECOGNIZE_HISTORY);
    public static final String TOP_BAR_REFRESH_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_REFRESH);
    public static final String TOP_BAR_SEARCH_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_SEARCH);
    public static final String TOP_BAR_SEPARATOR = buildPublicId("TopBar", SEPARATOR);
    public static final String TOP_BAR_SHARE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_SHARE);
    public static final String TOP_BAR_TEXT = buildPublicId("TopBar", "Text");
    public static final String TOP_BAR_UNLOVE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_LIST_UNLOVE);
    private static ArrayList<String> sImageElement = new ArrayList();

    static {
        collectMembers();
    }

    private static String buildPublicId(String str, String str2) {
        return str + SEPARATOR_TOKEN + str2;
    }

    private static void collectMembers() {
        for (Field field : ThemeElement.class.getDeclaredFields()) {
            try {
                if (field.getName().contains(IMAGE_FLAG)) {
                    field.setAccessible(true);
                    sImageElement.add(field.get(null).toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    static boolean isTextElementId(String str) {
        return str != null && str.endsWith("Text");
    }

    static boolean isImageElementId(String str) {
        return sImageElement.contains(str);
    }

    public static String parsePanelId(String str) {
        int indexOf = str.indexOf(SEPARATOR_TOKEN);
        if (indexOf >= 0) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    public static String parseElementId(String str) {
        int indexOf = str.indexOf(SEPARATOR_TOKEN);
        if (indexOf >= 0) {
            return str.substring(indexOf + 1);
        }
        return str;
    }
}

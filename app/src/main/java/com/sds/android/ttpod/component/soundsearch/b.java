package com.sds.android.ttpod.component.soundsearch;

/* IThemeEditable */
public interface b {

    /* IThemeEditable */
    public interface a {
        void onRemoveRequested();

        void onSelectedCountChanged();

        void onStopEditRequested();
    }

    void selectAll();

    void selectNone();

    int selectedCount();

    int totalCount();
}

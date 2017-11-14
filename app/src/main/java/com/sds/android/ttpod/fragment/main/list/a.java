package com.sds.android.ttpod.fragment.main.list;

/* IEditAble */
public interface a {

    /* IEditAble */
    public interface a {
        void onAddToRequested();

        void onRemoveRequested();

        void onSelectedCountChanged();

        void onSendToRequested();

        void onStopEditRequested();
    }

    void addTo();

    boolean isEditing();

    void remove();

    void selectAll();

    void selectNone();

    int selectedCount();

    void sendTo();

    void startEdit();

    void stopEdit();

    int totalCount();
}

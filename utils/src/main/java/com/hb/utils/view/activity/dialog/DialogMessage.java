package com.hb.utils.view.activity.dialog;

import java.io.Serializable;

/**
 * 启动dialogActivity时需要传递的数据
 */
public class DialogMessage implements Serializable{
    private String title;
    private String message;
    private String cancelButText;
    private String okButText;
    private boolean cancelable = true;
    private boolean showCancelBut = true;
    private boolean showOkBut = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCancelButText() {
        return cancelButText;
    }

    public void setCancelButText(String cancelButText) {
        this.cancelButText = cancelButText;
    }

    public String getOkButText() {
        return okButText;
    }

    public void setOkButText(String okButText) {
        this.okButText = okButText;
    }



    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public boolean isShowCancelBut() {
        return showCancelBut;
    }

    public void setShowCancelBut(boolean showCancelBut) {
        this.showCancelBut = showCancelBut;
    }

    public boolean isShowOkBut() {
        return showOkBut;
    }

    public void setShowOkBut(boolean showOkBut) {
        this.showOkBut = showOkBut;
    }

    @Override
    public String toString() {
        return "DialogMessage{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", cancelButText='" + cancelButText + '\'' +
                ", okButText='" + okButText + '\'' +
                ", cancelable=" + cancelable +
                ", showCancelBut=" + showCancelBut +
                ", showOkBut=" + showOkBut +
                '}';
    }
}

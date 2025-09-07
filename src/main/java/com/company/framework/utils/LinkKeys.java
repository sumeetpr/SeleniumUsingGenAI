package com.company.framework.utils;

public enum LinkKeys {
    HOME("home"),
    RESOURCES("resources"),
    DYNAMIC_ID("dynamicId"),
    CLASS_ATTR("classAttr"),
    HIDDEN_LAYERS("hiddenLayers"),
    LOAD_DELAY("loadDelay"),
    AJAX_DATA("ajaxData"),
    CLIENT_DELAY("clientDelay"),
    CLICK("click"),
    TEXT_INPUT("textInput"),
    SCROLLBARS("scrollbars"),
    DYNAMIC_TABLE("dynamicTable"),
    VERIFY_TEXT("verifyText"),
    PROGRESS_BAR("progressBar"),
    VISIBILITY("visibility"),
    SAMPLE_APP("sampleApp"),
    MOUSE_OVER("mouseOver"),
    NBSP("nbsp"),
    OVERLAPPED("overlapped"),
    SHADOW_DOM("shadowDom"),
    ALERTS("alerts"),
    UPLOAD("upload"),
    ANIMATION("animation"),
    DISABLED_INPUT("disabledInput"),
    AUTO_WAIT("autoWait");

    private final String key;

    LinkKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
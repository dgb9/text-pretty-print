package com.test.textprint;

/**
 * Created by dgabrove on 05/31/2016.
 */
public class TextPrintColumnConfig {
    private String title;
    private String getter;
    private boolean rightAlign;
    private boolean emptyIfNull;

    public TextPrintColumnConfig(String title, String getter, boolean rightAlign, boolean emptyIfNull) {
        this.title = title;
        this.getter = getter;
        this.rightAlign = rightAlign;
        this.emptyIfNull = emptyIfNull;
    }

    public String getTitle() {
        return title;
    }

    public String getGetter() {
        return getter;
    }

    public boolean isEmptyIfNull() {
        return emptyIfNull;
    }

    public boolean isRightAlign() {
        return rightAlign;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextPrintColumnConfig{");
        sb.append("title='").append(title).append('\'');
        sb.append(", getter='").append(getter).append('\'');
        sb.append(", rightAlign=").append(rightAlign);
        sb.append(", emptyIfNull=").append(emptyIfNull);
        sb.append('}');
        return sb.toString();
    }
}

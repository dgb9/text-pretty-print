package com.test.textprint;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by dgabrove on 05/31/2016.
 */
public class TextPrint {
    public static final String GUTTER = "  ";

    private final List<?> items;
    private final List<TextPrintColumnConfig> config;
    private final PrintWriter writer;
    int[] widths;

    public TextPrint(List<?> items, List<TextPrintColumnConfig> config, PrintWriter writer) {
        this.items = items;
        this.config = config;
        this.writer = writer;
    }

    public void print() throws TextPrintException {
        try {
            analyzeData();

            printBar();
            printTitles();
            printBar();

            for(Object item : items){
                printData(item);
            }

            printBar();
        }
        catch(Exception e){
            throw new TextPrintException(e);
        }
    }

    /**
     * Check the provided stream and object list,
     */
    private void analyzeData() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // calculate all the widths; this adequately checks
        // whether all the items expose all the provided getters
        int nr = this.config.size();
        widths = new int[nr];

        for(int count = 0; count < nr; count ++) {
            // initialize the width with the title
            widths[count] = getWidth(count);
        }
    }

    private int getWidth(int count) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int res = 0;

        TextPrintColumnConfig cfg = this.config.get(count);

        String title = cfg.getTitle();
        res = title.length();

        String getter = cfg.getGetter();

        // for each object in the collection do reflection for getting out the similar
        // information under this title
        for(Object item : items){
            String val = getValue(item, getter, cfg.isEmptyIfNull());

            int length = val.length();

            if(length > res){
                res = length;
            }
        }

        return res;
    }

    private String getValue(Object item, String getter, boolean isEmptyIfNull) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String res = null;

        if(item != null){
            Class<?> cls = item.getClass();
            Method method = cls.getMethod(getter);

            if(method == null){
                res = "NO METHOD: " + getter;
            }
            else {
                Object val = method.invoke(item);

                if (val == null && isEmptyIfNull) {
                    res = "";
                }
                else {
                    res = "" + val;
                }
            }
        }

        return res;
    }

    private void printTitles() {
        boolean first = true;
        int index = 0;

        for(TextPrintColumnConfig c : config){
            if(first){
                first = false;
            }
            else {
                printGutter();
            }

            String title = c.getTitle();
            printPaddedChar(" ", title, widths[index], c.isRightAlign());

            index ++;
        }

        printEndOfLine();
    }

    private void printPaddedChar(String pad, String val, int width, boolean rightAlign) {
        boolean somethingAdded = false;

        StringBuilder buffer = new StringBuilder();
        buffer.append(val);

        while(buffer.length() < width){
            somethingAdded = true; // something was added; if nothing was added, you can not trim later

            if(rightAlign){
                buffer.insert(0, pad);
            }
            else {
                buffer.append(pad);
            }
        }

        String strVal = buffer.toString();

        // adjust the dimension to fit exactly the bill, just in case the padding string

        if(somethingAdded && strVal.length() > width){
            int diff = strVal.length() - width;

            if(rightAlign){
                strVal = strVal.substring(diff);
            }
            else {
                strVal = strVal.substring(0, width);
            }
        }

        writer.write(strVal);
    }

    private void printGutter() {
        writer.write(GUTTER);
    }

    private void printBar() {
        boolean first = true;

        int nr = widths.length;
        for(int count = 0; count < nr; count ++){
            if(first){
                first = false;
            }
            else {
                printGutter();
            }

            printPaddedChar("=", "", this.widths[count], false);
        }

        printEndOfLine();
    }

    private void printData(Object item) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // for each configuration, get the value from the item and proceed to write it
        int index = 0;
        boolean first = true;

        for(TextPrintColumnConfig cfg : config){

            if (first) {
                first = false;
            }
            else {
                printGutter();
            }

            String getter = cfg.getGetter();
            int width = widths[index];

            String val = getValue(item, getter, cfg.isEmptyIfNull());
            printPaddedChar(" ", val, width, cfg.isRightAlign());

            index ++;
        }

        printEndOfLine();

    }
    private void printEndOfLine() {
        writer.write(System.getProperty("line.separator"));
    }

}

package com.test.testdata;

import com.test.textprint.TextPrint;
import com.test.textprint.TextPrintColumnConfig;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dgabrove on 05/31/2016.
 */
public class Test {
    public static void main(String[] args) {
        try {
            new Test().start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void start() throws Exception {
        // create a collection with various objects of type TestData
        // feed it in a TextPrint object and proceed
        // print at the console level

        List<TestData> items = new ArrayList<TestData>();
        for(int count = 0; count < 10; count ++){
            TestData item = new TestData(20 + count, 30D + count, "name: " + count, "sur name " + count, new Date(), "other " + count);

            items.add(item);
        }

        // now get the TextPrint and proceed
        List<TextPrintColumnConfig> config = new ArrayList<TextPrintColumnConfig>();
        config.add(new TextPrintColumnConfig("Amount", "getAmount", true, false));
        config.add(new TextPrintColumnConfig("Total", "getTotal", true, false));
        config.add(new TextPrintColumnConfig("Name", "getName", false, false));
        config.add(new TextPrintColumnConfig("Surname", "getSurname", false, false));
        config.add(new TextPrintColumnConfig("Start Date", "getDtStart", true, false));
        config.add(new TextPrintColumnConfig("Other", "getOther", false, false));

        PrintWriter wrt = new PrintWriter(System.out);
        TextPrint printer = new TextPrint(items, config, wrt);
        printer.print();
        wrt.flush();
        wrt.close();
    }
}

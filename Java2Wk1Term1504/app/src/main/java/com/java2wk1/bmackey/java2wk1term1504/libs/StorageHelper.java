package com.java2wk1.bmackey.java2wk1term1504.libs;

import android.content.Context;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by brandonmackey on 4/10/15.
 */
public class StorageHelper {

    public static void SaveData(String _data, Context context) {

        try {
            FileOutputStream fos = context.openFileOutput("MyDataTemp.txt", Context.MODE_PRIVATE);

            fos.write(_data.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String LoadData(Context context) {

        String results = "";
        try {
            FileInputStream fis = context.openFileInput("MyDataTemp.txt");

            results = IOUtils.toString(fis);

            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

}
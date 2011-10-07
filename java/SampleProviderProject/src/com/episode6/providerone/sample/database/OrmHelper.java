package com.episode6.providerone.sample.database;

public class OrmHelper {
    public static int getIndexOfStringInArray(String key, String[] array) {
        if (key == null || array == null)
            return -1;
        for (int i = 0; i<array.length; i++) {
            if (key.equals(array[i]))
                return i;
        }
        return -1;
    }
}

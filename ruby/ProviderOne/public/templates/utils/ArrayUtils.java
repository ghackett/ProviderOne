package {PackageName}.database.autogen.util;

public class ArrayUtils {
    public static int getIndexOfStringInArray(String key, String[] array) {
        if (key == null || array == null)
            return -1;
        for (int i = 0; i<array.length; i++) {
            if (key.equals(array[i]))
                return i;
        }
        return -1;
    }

    public static boolean isStringInArray(String key, String[] array) {
        return getIndexOfStringInArray(key, array) != -1;
    }
}

package com.dgrissom.osbu.main.utilities;

import java.util.Arrays;

public class ArrayUtility extends OSBUUtility {
    private Object[] array;

    public ArrayUtility(Object object) {
        super(object);
        this.array = (Object[]) object;
    }

    public ArrayUtility subArray(int begin) {
        return subArray(begin, this.array.length);
    }
    public ArrayUtility subArray(int begin, int end) {
        Object[] subArray = new Object[end - begin];
        System.arraycopy(array, begin, subArray, 0, end - begin);
        return new ArrayUtility(subArray);
    }

    public String[] toStringArray() {
        String[] stringArray = new String[this.array.length];
        for (int i = 0; i < this.array.length; i++)
            stringArray[i] = String.valueOf(this.array[i]);
        return stringArray;
    }
    public String join(String elementSeparator) {
        String joined = "";
        for (Object e : this.array)
            joined += String.valueOf(e) + elementSeparator;
        if (joined.endsWith(elementSeparator))
            joined = joined.substring(0, joined.length() - elementSeparator.length());
        return joined;
    }

    @Override
    public Object[] getObject() {
        return this.array;
    }
    @Override
    public String toString() {
        return Arrays.toString(this.array);
    }
}

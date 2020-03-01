package com.dkatalis.APIDemo.TestInitializer;

import java.io.File;
import java.util.Map;

public interface IComparator {

    public boolean compare(Object x, Object y);
    public Map<Object, Object> getData(File file1, File file2);
}

package org.kezoo.store.utils;

import org.kezoo.store.model.Computer;
import org.kezoo.store.model.Hdd;
import org.kezoo.store.model.Laptop;
import org.kezoo.store.model.Monitor;

import java.util.HashMap;
import java.util.Map;

public final class FileUtils {

    private static final Map<String, Class> modelClassMap = new HashMap<>();

    static {
        modelClassMap.put("computer", Computer.class);
        modelClassMap.put("monitor", Monitor.class);
        modelClassMap.put("laptop", Laptop.class);
        modelClassMap.put("hdd", Hdd.class);
    }

    private FileUtils() {
        throw new RuntimeException();
    }

    public static Class getClass(String typeName) {
        return modelClassMap.get(typeName);
    }
}

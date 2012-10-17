package com.cgsolutions.security.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to provide base for static helper classes that return thread bound info
 */
@SuppressWarnings("unchecked")
public abstract class AbstractStaticThreadLocalDataFinder {
    protected static final ThreadLocal data = new ThreadLocal() {
        protected Object initialValue() {
            return new HashMap();
        }
    };
    
    public static void clear() {
        Map map = (Map)data.get();
        map.clear();
    }
}

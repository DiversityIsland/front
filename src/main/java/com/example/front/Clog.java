package com.example.front;

import org.slf4j.LoggerFactory;

@FunctionalInterface
public interface Clog {
    org.slf4j.Logger logger = LoggerFactory.getLogger("*.java");
    Clog
            TRACE = logger::trace,
            DEBUG = logger::debug,
            INFO = logger::info,
            WARN = logger::warn,
            ERROR = logger::error;

    Clog
            method_create = INFO,
            method_received = DEBUG,
            method_execute = INFO,
            method = INFO,
            status = INFO,
            status_minor = INFO,
            method_create_object = DEBUG,
            method_delete_object = DEBUG,
            method_log = DEBUG,
            method_trace = TRACE,
            method_close = INFO,
            method_result = INFO,
            method_result_uncommon = WARN,
            monitor = INFO,
            warning = WARN,
            err = ERROR,
            pool = DEBUG;

    void log(String text);

    static String deco(String logStr) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        int stackTraceDepth = st.length - 2;
        String tmpStr = logStr.replace("%", "#");
        return String.format("%s%d%s%s",'[', stackTraceDepth, "] ", tmpStr);
    }
}

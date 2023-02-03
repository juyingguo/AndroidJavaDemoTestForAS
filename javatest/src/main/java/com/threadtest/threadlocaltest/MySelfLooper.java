package com.threadtest.threadlocaltest;

public final class MySelfLooper {
    // sThreadLocal.get() will return null unless you've called prepare().
    static final ThreadLocalHashVarTest<MySelfLooper> sThreadLocal = new ThreadLocalHashVarTest<MySelfLooper>();
}
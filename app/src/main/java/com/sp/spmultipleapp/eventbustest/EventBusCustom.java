package com.sp.spmultipleapp.eventbustest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

/**
 * Date:2021/9/10,14:35
 * author:jy
 */
public class EventBusCustom {
    static final EventBus CUSTOM_DEFAULT_EVENT_BUS;
    static {
        CUSTOM_DEFAULT_EVENT_BUS = EventBus.builder()
                .strictMethodVerification(true)
                .build();
    }
}

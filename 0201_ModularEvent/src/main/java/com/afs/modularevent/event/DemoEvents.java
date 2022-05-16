package com.afs.modularevent.event;

import com.afs.modularevent.TestEventBean;
import com.afs.modularevent.anotation.EventType;
import com.afs.modularevent.anotation.ModuleEvents;

//可以指定module，若不指定，则使用包名作为module名
@ModuleEvents()
public class DemoEvents {

    //不指定消息类型，那么消息的类型默认为Object
    public static final String EVENT1 = "event1";

    //指定消息类型为自定义Bean
    @EventType(TestEventBean.class)
    public static final String EVENT2 = "event2";

    //指定消息类型为java原生类型
    @EventType(String.class)
    public static final String EVENT3 = "event3";
}
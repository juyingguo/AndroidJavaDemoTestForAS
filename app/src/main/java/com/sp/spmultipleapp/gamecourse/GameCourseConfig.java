package com.sp.spmultipleapp.gamecourse;

/**
 * Date:2019/8/13,16:22
 * author:jy
 */
public class GameCourseConfig {
    /**
     *1.2、	开启游戏课件广播
     */
    public final static String ACTION_ENTER_GAME_COURSE_MODE = "ACTION_ENTER_GAME_COURSE_MODE" ;
    /**
     *1.3、	退出游戏课件广播
     */
    public final static String ACTION_EXIT_GAME_COURSE_MODE = "ACTION_EXIT_GAME_COURSE_MODE";
    /**
     *1.4、	语音对话结果返回广播
     */
    public final static String ACTION_ASR_RESULT_FOR_GAME_COURSE_MODE = "ACTION_ASR_RESULT_FOR_GAME_COURSE_MODE";
    /**
     * 广播中获取内容的字段名称
     */
    public final static String EXTRA_ASR_RESULT = "EXTRA_ASR_RESULT";
    /**
     * 1为游戏课程模式；0普通模式。
     */
    public final static String GAME_COURSE_MODE = "GAME_COURSE_MODE";

    /** 游戏课件模式下的触摸广播事件 */
    public static final String ACTION_TOUCH_PAD_PRESSED_FOR_GAME_COURSE_MODE = "ACTION_TOUCH_PAD_PRESSED_FOR_GAME_COURSE_MODE";
    /**
     * 触摸检测区域。头前区域："REGION_FRONT_HEAD";肚子："REGION_BELLY";
     */
    public static final String EXTRA_TOUCH_PAD_DETECTED_REGION		= "EXTRA_TOUCH_PAD_DETECTED_REGION";

}

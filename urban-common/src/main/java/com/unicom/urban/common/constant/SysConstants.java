package com.unicom.urban.common.constant;

public class SysConstants {

    private SysConstants() {
    }

    public static final String BASE_PACKAGE = "com.unicom.urban";

    @Deprecated
    public static final String DELETED_FILED = "deleted";

    @Deprecated
    public static final long DELETED_VALUE = 0;

    @Deprecated
    public static final String WHERE_DELETE = DELETED_FILED + "=" + DELETED_VALUE;

    public static final String SNOW_CLASS = BASE_PACKAGE + ".common.id.SnowIdGenerator";

    /**
     * 当前登录人的主体信息
     */
    public static final String SESSION_CURRENT_PRINCIPAL = "principal";

}

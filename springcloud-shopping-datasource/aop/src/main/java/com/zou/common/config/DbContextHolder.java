package com.zou.common.config;

/**
 * @author : wh
 * @date : 2020/7/21 11:32
 */
public class DbContextHolder {

    private static final ThreadLocal<String> contextHolder = ThreadLocal.withInitial(String::new);

    /**
     * 设置数据源
     * @param dbType
     * @return void
     * @author wh
     * @date 2020/7/21
     */
    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    /**
     * 取得当前数据源
     * @param
     * @return java.lang.String
     * @author wh
     * @date 2020/7/21
     */
    public static String getDbType() {
        System.out.println("获取数据源为：" + contextHolder.get());
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}

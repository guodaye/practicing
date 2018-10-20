package com.github.guoyaohui.common;

import com.github.guoyaohui.mybatis.handler.IEnum;

/**
 * @author 郭垚辉
 * @date 2018/10/18
 */
public class EnumUtil {

    /**
     * 根据枚举常量的index获取枚举对象
     *
     * 注意：此处我们使用的是index，而不是e.ordinal()的原因是：e.ordinal()依赖于我们枚举常量声明的位置
     * 在后期维护中，可能由于有意/无意的修改，造成了e.ordinal()的改变，而导致数据不准确。因此使用的是自定义的index。
     *
     * @param clazz 枚举类型的Class对象
     * @param index 枚举常量的名称
     * @param <E> 标定
     * @return 枚举值
     */
    public static <E extends Enum<E> & IEnum> E getEnum(Class<E> clazz, int index) {
        E[] constants = clazz.getEnumConstants();
        for (E e : constants) {
            if (e.getIndex() == index) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据枚举常量的名称获取枚举对象
     *
     * @param clazz 枚举类型的Class对象
     * @param name 枚举常量的名称
     * @param <E> 标定
     * @return 枚举值
     */
    public static <E extends Enum<E> & IEnum> E getEnum(Class<E> clazz, String name) {
        E[] constants = clazz.getEnumConstants();
        for (E e : constants) {
            if (e.name().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static <E extends Enum<E> & IEnum> E getEnum(Class<E> clazz, int index, String name) {
        E[] constants = clazz.getEnumConstants();
        for (E e : constants) {
            if (e.getIndex() == index) {
                return e.name().equals(name) ? e : null;
            }
        }
        return null;
    }

    public static IEnum getIEnum(Class<? extends IEnum> dataSourceEnum, int index) {
        IEnum[] constants = dataSourceEnum.getEnumConstants();
        for (IEnum e : constants) {
            if (e.getIndex() == index) {
                return e;
            }
        }
        return null;
    }
}

package com.gosun.isap.dao.mapper.alert;

/**
 * 查询时的区间
 * 由一个上限值，一个下限值，组成一个查询开区间：
 * column > lowerLimit and column < upperLimit
 * <p>创建时间：2017/5/11 11:01</p>
 *
 * @param <T> 区间数据类型
 * @author 娄存银
 * @version 1.0
 */
public class Interval<T> {
    /**
     * 上限
     */
    private T upperLimit;
    /**
     * 下限
     */
    private T lowerLimit;

    /**
     * @param upperLimit 区间上限
     * @param lowerLimit 区间下限
     */
    public Interval(T upperLimit, T lowerLimit) {
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
    }

    public T getUpperLimit() {
        return upperLimit;
    }

    public T getLowerLimit() {
        return lowerLimit;
    }
}

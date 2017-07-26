package com.gosun.isap.warn.api.alert.model.statistics;

import com.gosun.isap.warn.api.alert.util.ListUtils;

import java.util.List;

/**
 * 统计信息图表数据项
 * <p>创建时间：2017-5-22 17:42</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public final class StatisticsDiagramData {
    private String title;
    private List<StatisticsItem> data;

    private StatisticsDiagramData(String title, List<StatisticsItem> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<StatisticsItem> getData() {
        return data;
    }

    public void setData(List<StatisticsItem> data) {
        this.data = data;
    }

    /**
     * 通过标题数据实例化一个对象
     *
     * @param title 标题
     * @param data  数据
     * @return 实例对象
     */
    public static StatisticsDiagramData getInstance(String title, List<StatisticsItem> data) {
        if (ListUtils.isEmpty(data)) {
            return null;
        }
        return new StatisticsDiagramData(title, data);
    }
}

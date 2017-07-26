package com.gosun.isap.warn.api.alert.model.statistics;

/**
 * 统计数据项
 * <p>创建时间：2017/4/18 14:45</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class StatisticsItem {
    private String name;
    private String description;
    private int number;
    private float ratio;

    public StatisticsItem() {

    }

    public StatisticsItem(String name,String description, int number, float ratio) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.ratio = ratio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StatisticsItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", ratio=" + ratio +
                '}';
    }
}

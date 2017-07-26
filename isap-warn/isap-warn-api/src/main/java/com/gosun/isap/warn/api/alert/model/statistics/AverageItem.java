package com.gosun.isap.warn.api.alert.model.statistics;

/**
 * 统计信息平均数据项。
 * <p>创建时间：2017-5-22 17:23</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AverageItem {
    private String name;
    private String description;
    private Double number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    /**
     * 获取一个实例对象
     *
     * @param name        名称
     * @param description 描述
     * @param number      数值
     * @return 对象
     */
    public static AverageItem getInstance(String name, String description, Double number) {
        if (number == null) {
            return null;
        }
        AverageItem item = new AverageItem();
        item.setName(name);
        item.setDescription(description);
        item.setNumber(number);
        return item;
    }
}

package com.gosun.isap.dao.mapper.alert;

import javax.validation.constraints.NotNull;

/**
 * 用于分页
 * <p>创建时间：2017/5/11 15:50</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class SqlLimit {
    /**
     * 默认列表长度
     */
    private static final int DEFAULT_LIMIT = 30;

    private Integer offset;
    private Integer length;
    private String limit;

    public SqlLimit(Integer length) {
        this(null,length);
    }

    public SqlLimit(Integer offset, @NotNull Integer length) {
        if(length == null){
            throw new RuntimeException("SqlLimit 中 length 不能为空");
        }
        this.offset = offset;
        this.length = length;
        limit = initLimit();
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLength() {
        return length;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLength(Integer length) {
        if(length != null) {
            if(length < 0){
                length = 0;
            }
            this.length = length;
        }
    }

    /**
     * 会在 SqlMapper 中用到，不可删除
     *
     * @return 拼接后的 limit 字符串
     */
    public String getLimit() {
        return limit;
    }

    private String initLimit(){
        if(offset != null && length != null){
            return offset+","+length;
        }

        if(length != null){
            return length.toString();
        }

        return null;
    }

    /**
     * 通过一个 start limit 初始化 SqlLimit
     * @param start 从第几条开始
     * @param limit 需要几条
     * @return SqlLimit
     */
    public static SqlLimit init(Integer start,Integer limit){
        if(limit == null){
            return new SqlLimit(0,DEFAULT_LIMIT);
        }
        return new SqlLimit(start,limit);
    }
}

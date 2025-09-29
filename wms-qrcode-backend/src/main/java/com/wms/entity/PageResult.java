package com.wms.entity;

import java.util.List;

/**
 * 分页结果实体类
 */
public class PageResult<T> {
    // 当前页数据列表
    private List<T> data;
    // 总记录数
    private long total;
    // 当前页码
    private int pageNum;
    // 每页显示条数
    private int pageSize;
    // 总页数
    private long totalPages;
    
    // 构造方法
    public PageResult(List<T> data, long total, int pageNum, int pageSize) {
        this.data = data;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        // 计算总页数
        this.totalPages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }
    
    // Getters and Setters
    public List<T> getData() {
        return data;
    }
    
    public void setData(List<T> data) {
        this.data = data;
    }
    
    public long getTotal() {
        return total;
    }
    
    public void setTotal(long total) {
        this.total = total;
        // 重新计算总页数
        this.totalPages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }
    
    public int getPageNum() {
        return pageNum;
    }
    
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        // 重新计算总页数
        this.totalPages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }
    
    public long getTotalPages() {
        return totalPages;
    }
    
    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}
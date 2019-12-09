package entity;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/19 19:36
 * 分页对象
 */
public class PageResult<T> {
    //总页数
    private long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

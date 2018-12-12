package org.patentminer.bean;

import lombok.Data;
import org.patentminer.model.User;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Data
public class PageResultBean<T> extends ResultBean<T> implements Serializable {

    // 总记录数
    private long totalRecord;

    // 总页数
    private int pageCount;

    // 当前页码
    private int pageNo;

    // 当前页的记录数量
    private int pageSize;

    public PageResultBean(Page<User> users, HttpServletResponse response) {
        super((T) users.get(), response);
        PageResultBean(users);
    }

    public void PageResultBean(Page<User> users) {
        int size = users.getPageable().getPageSize();
        long total = users.getTotalElements();
        this.setPageNo(users.getPageable().getPageNumber())
                .setPageSize(size)
                .setTotalRecord(total)
                .setPageCount(size == 0 ? 1 : (int) Math.ceil((double) total / (double) size));
    }

    @Override
    public String toString() {
        return "PageResulBean{" +
                "totalRecord=" + totalRecord +
                ", pageCount=" + pageCount +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                "}";
    }

    public PageResultBean setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
        return this;
    }

    public PageResultBean setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public PageResultBean setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public PageResultBean setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}

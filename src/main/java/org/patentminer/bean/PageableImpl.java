package org.patentminer.bean;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Optional;

@Data
public class PageableImpl implements Serializable, Pageable {

    private static final long serialVersionUID = 1;

    private int pageNo = 1;

    private int pageSize = 10;

    private Sort sort;

    public PageableImpl(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    @Override
    public boolean isPaged() {
        return false;
    }

    @Override
    public boolean isUnpaged() {
        return false;
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return null;
    }

    @Override
    public Optional<Pageable> toOptional() {
        return Optional.empty();
    }

    @Override
    public int getPageNumber() {
        return pageNo;
    }

    @Override
    public long getOffset() {
        return (getPageNumber() - 1) * getPageSize();
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }
}

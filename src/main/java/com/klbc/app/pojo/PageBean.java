package com.klbc.app.pojo;

import java.io.Serializable;
import java.util.List;

public class PageBean<E> implements Serializable{

    private List<E> list;     // ĳ����(����˵�̳���Ʒ)��ǰҳ������
    private Integer currPage; // ��ǰ���ڵ�ҳ��,���1ҳ,��2ҳ
    private Integer pageSize; // ÿҳ����������Ʒ������
    @SuppressWarnings("unused")
    private Integer totalPage;  // ��ҳ��
    private Integer totalCount; // ����Ʒ��

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return  (int) Math.ceil(totalCount * 1.0 / pageSize);
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public PageBean() {
        super();
    }

    public PageBean(List<E> list, Integer currPage, Integer pageSize, Integer totalCount) {
        super();
        this.list = list;
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }
}




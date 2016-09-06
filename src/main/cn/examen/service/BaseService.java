package cn.examen.service;

import java.util.List;

public interface BaseService<T> {

    String addOne(T param, Object...objects);

    Integer getTotalRecordByParam(T param);

    T getById(String id);

    List<T> getPageListByParam(T param, int offset, int rows);
}

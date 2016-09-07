package cn.examen.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**方法目录
一、增加
    public int insert(T entity);

二、删除
    public int deleteById(Object id);
    public int deleteByIds(List list);
    public int deleteByProperty(@Param("property") String property, @Param("value") Object value);
    public int deleteByCondition(Map<String, Object> condition);

三、更新
    public int update(T entity);

四、查询
    public T findById(String id);
    public T findOne(@Param("property") String property, @Param("value") Object value);
    public List<T> findList(@Param("property") String property, @Param("value") Object value, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);
    public List<T> findAll(@Param("orderBy") String orderBy, @Param("sortBy") String sortBy);
    public List<T> queryList(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    public int count(T param);
    public List<T> queryPage(@Param("condition") Map<String, Object> condition, @Param("offset") int offset, @Param("rows") int rows,@Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    public int countLike(T param);
    public List<T> like(@Param("condition") Map<String, Object> condition, @Param("offset") int offset, @Param("rows") int rows,@Param("orderBy") String orderBy, @Param("sortBy") String sortBy);
*/


public interface BaseMapper<T> {
    /********************************************************************增加（insert）*******/
    /**
     * 保存单一对象
     * @param entity
     */
    public int insert(T entity);

    /********************************************************************删除（delete）*******/
    /**
     * 根据id删除对象
     * @param id
     */
    public int deleteById(Object id);

    /**
     * 根据list(ids)删除对象
     * @param list
     */
    public int deleteByIds(List list);

    /**
     * 根据属性和属性值删除对象
     * @param property
     * @param value
     */
    public int deleteByProperty(@Param("property") String property, @Param("value") Object value);

    /**
     * 根据条件集合删除对象
     * @param condition
     */
    public int deleteByCondition(Map<String, Object> condition);

    /********************************************************************更新（update）*******/
    /**
     * 更新对象
     * @param entity
     */
    public int update(T entity);

    /********************************************************************查询（query）*******/
    /**
     * 根据主键Id查询
     * @param id
     * @return
     */
    public T findById(String id);

    /**
     * 根据属性和属性值查找对象
     * @param property
     * @param value
     * @return
     */
    public T findOne(@Param("property") String property, @Param("value") Object value);

    /**
     * 增加排序支持
     * @param property
     * @param value
     * @param orderBy
     * @param sortBy
     * @return
     */
    public List<T> findList(@Param("property") String property, @Param("value") Object value, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    /**
     * 增加排序支持
     * @param orderBy
     * @param sortBy
     * @return
     */
    public List<T> findAll(@Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    /**
     * 增加排序支持
     * @param condition 查询条件
     * @param offset 偏移
     * @param rows 查询条数
     * @param orderBy
     * @param sortBy
     * @return 返回Pager对象
     */
    public List<T> queryPage(@Param("condition") Map<String, Object> condition, @Param("offset") int offset, @Param("rows") int rows,
                             @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);
    /**
     * 模糊查询
     * @param condition 查询条件
     * @param orderBy
     * @param sortBy
     * @return
     */
    public List<T> like(@Param("condition") Map<String, Object> condition, @Param("offset") int offset, @Param("rows") int rows,
                        @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    /**
     * 根据条件集合进行指定类型对象集合查询
     * @param condition 进行查询的条件集合
     * @return 返回泛型参数类型的对象集合
     */
    public List<T> queryList(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    /**
     * 根据条件进行数量的查询
     * @param param
     * @return 返回符合条件的泛型参数对应表中的数量
     */
    public int count(T param);

    /**
     * 根据条件进行模糊数据数量的查询
     * @param param
     * @return 返回符合模糊查找条件的泛型参数对应表中的数量
     */
    public int countLike(T param);

    /**
     * 取得泛型类型
     * @return
     */
    public Class<T> getEntityClass();
}

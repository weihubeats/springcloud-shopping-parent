package elasticsearch.Dao;

import elasticsearch.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/6/17 23:16
 * @Description 继承 ElasticsearchRepository 获取基本的es操作
 */
public interface ProductMapper extends ElasticsearchRepository<Product, Long> {
    /**
     * 通过名字查询
     * @param name
     * @return
     */
    // Streamable<Product> findByName(String name);
    List<Product> findByName(String name);

    List<Product> findByDescribe(String describe);

    /**
     * 通过名字分页查询
     * @param name
     * @param pageable
     * @return
     */
    Page<Product> findByName(String name, Pageable pageable);

    /**
     * 通过名字排序查询
     * @param name
     * @param sort
     * @return
     */
    List<Product> findByName(String name, Sort sort);



    /**
     * 通过价格排序查询第一条
     * @param price
     * @return
     */
    Product findFirstByPrice(BigDecimal price);

    Product findTopByPrice(BigDecimal price);

    Page<Product> queryTop10ByName(String name, Pageable pageable);


}

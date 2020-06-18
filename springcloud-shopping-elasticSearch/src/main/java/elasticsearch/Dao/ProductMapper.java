package elasticsearch.Dao;

import elasticsearch.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author WH
 * @version 1.0
 * @date 2020/6/17 23:16
 * @Description 继承 ElasticsearchRepository 获取基本的es操作
 */
public interface ProductMapper extends ElasticsearchRepository<Product, Long> {
}

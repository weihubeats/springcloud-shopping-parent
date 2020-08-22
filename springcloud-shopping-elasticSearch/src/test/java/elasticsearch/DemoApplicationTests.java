package elasticsearch;

import elasticsearch.Dao.ProductMapper;
import elasticsearch.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    ProductMapper productMapper;



    @Autowired
    ElasticsearchRestTemplate template;


    @Autowired
    ElasticsearchOperations elasticsearchOperations;









    @Test
    void insert() {
        Product product = new Product();
        product.setId(4);
        product.setImg("图片四");
        product.setName("iphone plus");
        product.setDescribe("好好看的手机呢");
        product.setPrice(new BigDecimal("2999.3"));
        productMapper.save(product);
    }

    /**
     * 批量插入
     */
    @Test
    void inserts() {
        Product product = new Product();
        product.setImg("图片一");
        product.setName("iphone");
        product.setDescribe("打电话娱乐用的");
        product.setPrice(new BigDecimal("5666.3"));
        List<Product> list = new ArrayList();
        list.add(product);
        productMapper.saveAll(list);

    }

    /**
     * 更新
     */
    @Test
    void update() {
        Optional<Product> product = productMapper.findById(0l);
        Product product1 = product.orElseThrow( RuntimeException::new);
        productMapper.save(product1);

    }

    /**
     * 删除
     */
    @Test
    void delete() {
        productMapper.deleteById(0l);

    }

}

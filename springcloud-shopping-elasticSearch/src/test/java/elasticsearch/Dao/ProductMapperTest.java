package elasticsearch.Dao;

import elasticsearch.entity.Product;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/6/19 21:39
 * @Description TODO
 */
@SpringBootTest
class ProductMapperTest {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;



    @Test
    void findByName() {
        List<Product> list = productMapper.findByName("iphone");
        list.forEach(s -> System.out.println(s));
    }

    @Test
    void findByDescribe() {
        List<Product> list = productMapper.findByDescribe("打电话");
        list.forEach(s -> System.out.println(s));
    }

    /**
     * 分页
     */
    @Test
    void findByNmaePage() {
       Page<Product> list = productMapper.findByName("iphone", PageRequest.of(0, 20));
       list.get().forEach(s -> System.out.println(s));

    }

    /**
     * 排序 通过价格排序
     */
    @Test
    void findBYNameDesc() {
        Sort.TypedSort<Product> product = Sort.sort(Product.class);
        /**
         * 也可以使用这种写法
         * Sort sort = Sort.by("price").ascending()
         *   .and(Sort.by("name").descending());
         */
        List<Product> list = productMapper.findByName("iphone", product.by(Product::getPrice).descending());
        list.forEach(s -> System.out.println(s));

    }

    @Test
    void testQuery() {
        String name = "iphone";
        String price = "6666";
        String describe = "电话";
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        BoolQueryBuilder nameMatch = QueryBuilders.boolQuery().minimumShouldMatch(1)
                .should(QueryBuilders.matchQuery("name",name))
                .should(QueryBuilders.matchQuery("product",price));

        BoolQueryBuilder must = QueryBuilders.boolQuery().minimumShouldMatch(2)
                .should(nameMatch)
                .should(QueryBuilders.matchQuery("price",price))
                .should(QueryBuilders.matchQuery("describe",describe).boost(5));

        builder.must(must);
        NativeSearchQuery query = new NativeSearchQuery(builder);
        PageRequest request = PageRequest.of(0,1);
        query.setPageable(request);

        List<Product> list = elasticsearchOperations.queryForList(query,Product.class, IndexCoordinates.of("product"));
        list.forEach(s -> System.out.println(s));

    }


}
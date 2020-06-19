package elasticsearch.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * @author WH
 * @version 1.0
 * @date 2020/6/17 23:03
 * @Description TODO
 */
@Document(indexName = "product")
@Data
@ToString
public class Product {

    private long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    private BigDecimal price;

    private String img;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String describe;
}

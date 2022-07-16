package com.demo.searcher.domain;

import com.demo.searcher.vo.ProductInfoVo;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Getter
@Builder(builderMethodName = "ProductInfoBuilder")
@Table(name = "product_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String category;
    private String brand;
    private int price;


    public static ProductInfoBuilder productInfoBuilder(ProductInfoVo productInfoVo) {
        return ProductInfoBuilder()
                .category(productInfoVo.getCategory())
                .brand(productInfoVo.getBrand())
                .price(productInfoVo.getPrice());


    }

}

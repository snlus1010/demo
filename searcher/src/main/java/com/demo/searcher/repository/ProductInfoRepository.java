package com.demo.searcher.repository;

import com.demo.searcher.domain.ProductInfo;
import com.demo.searcher.vo.SearchLowestByBrandVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

    @Query("SELECT p.category " +
            "FROM ProductInfo p " +
            "GROUP BY p.category")
    List<String> getCategoryList();


    @Query("SELECT new com.demo.searcher.vo.SearchLowestByBrandVo(p.brand, SUM(p.price)) " +
            "FROM ProductInfo p " +
            "GROUP BY p.brand " +
            "ORDER BY SUM(p.price) asc")
    List<SearchLowestByBrandVo> searchLowestPriceByBrand();

    ProductInfo findTopByCategoryOrderByPriceAsc(String category);

    ProductInfo findTopByCategoryOrderByPriceDesc(String category);

    @Transactional
    void deleteProductInfoBySeq(int seq);


}

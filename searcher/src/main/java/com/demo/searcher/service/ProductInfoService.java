package com.demo.searcher.service;

import com.demo.searcher.domain.ProductInfo;
import com.demo.searcher.repository.ProductInfoRepository;
import com.demo.searcher.vo.ProductInfoVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductInfoService {

    private final ProductInfoRepository productInfoRepository;

    private final ObjectMapper objectMapper;

    public void addProduct(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = objectMapper.convertValue(productInfoVo, ProductInfo.class);

        productInfoRepository.save(productInfo);
    }

    public void removeProduct(int seq) {
        productInfoRepository.deleteProductInfoBySeq(seq);
    }

    public void updateProduct(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = objectMapper.convertValue(productInfoVo, ProductInfo.class);

        productInfoRepository.save(productInfo);
    }
}

package com.ecommerce.marketplace.Service;

import com.ecommerce.marketplace.Entity.Product;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;

public interface ISearchService {
    FullTextQuery getJpaQuery();

    QueryBuilder getQueryBuilder();

    List<Product> searchProductByKeyWordQuery();

    List<Product> searchProductByFuzzyQuery();

    List<Product> searchProductDescriptionByPhraseQuery();

    List<Product> searchProductNameByRangeQuery();

    List<Product> searchProductDescriptionAndNameBySimpleStringQuery();

    List<Product> searchProducts();

    List<Product> getSuggestions();

    List<Product> priceFacets();
}

package hu.zsoltii.jsf.db.repository;

import hu.zsoltii.jsf.db.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}

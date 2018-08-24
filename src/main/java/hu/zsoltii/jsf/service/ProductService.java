package hu.zsoltii.jsf.service;

import hu.zsoltii.jsf.data.Product;
import hu.zsoltii.jsf.db.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements Serializable {

    @Autowired
    private ProductRepository productRepository;

    public void save(Product product) {
        hu.zsoltii.jsf.db.model.Product product1 = new hu.zsoltii.jsf.db.model.Product();

        product1.setDescrption(product.getDescription());
        product1.setImage(product.getImage());
        product1.setName(product.getName());

        product1 = productRepository.save(product1);
        product.setId(product1.getId());
    }

    public void update(Product product) {
        Optional<hu.zsoltii.jsf.db.model.Product> productOptional = productRepository.findById(product.getId());

        if(!productOptional.isPresent()) {
            throw new IllegalStateException("not find product");
        }

        hu.zsoltii.jsf.db.model.Product product1 = productOptional.get();

        product1.setDescrption(product.getDescription());
        if(product.getImage() != null) {
            product1.setImage(product.getImage());
        }
        product1.setName(product.getName());

        product1 = productRepository.save(product1);
        product.setId(product1.getId());
    }

    public long countProcuct() {
        return productRepository.count();
    }

    public Product getProduct(long id) {
        Optional<hu.zsoltii.jsf.db.model.Product> productOptional = productRepository.findById(id);

        Product result = null;
        if (productOptional.isPresent()) {
            result = new Product();
            result.setId(productOptional.get().getId());
            result.setDescription(productOptional.get().getDescrption());
            result.setImage(productOptional.get().getImage());
            result.setName(productOptional.get().getName());
        } else {
            throw new IllegalStateException(new StringBuilder().append("product not find by id: ").append(id).toString());
        }

        return result;
    }

    public List<Product> list(int page, int size) {
        Page<hu.zsoltii.jsf.db.model.Product> products = productRepository.findAll(PageRequest.of(page, size));

        List<Product> result = new ArrayList<>();

        for (hu.zsoltii.jsf.db.model.Product product1 : products) {
            Product product = new Product();

            product.setDescription(product1.getDescrption());
            product.setId(product1.getId());
            product.setImage(product1.getImage());
            product.setName(product1.getName());

            result.add(product);
        }

        return result;
    }

    public Product findById(Long id) {
        Optional<hu.zsoltii.jsf.db.model.Product> productOptional = productRepository.findById(id);

        Product product = null;

        if(productOptional.isPresent()) {
            hu.zsoltii.jsf.db.model.Product product1 = productOptional.get();

            product = new Product();

            product.setDescription(product1.getDescrption());
            product.setId(product1.getId());
            product.setImage(product1.getImage());
            product.setName(product1.getName());
        } else {
            throw new IllegalStateException("not find product");
        }

        return product;
    }
}

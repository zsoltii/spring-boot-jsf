package hu.zsoltii.jsf.controller;

import hu.zsoltii.jsf.data.Product;
import hu.zsoltii.jsf.service.ProductService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class ProductLazyDataModel extends LazyDataModel<Product> {

    @Autowired
    private ProductService productService;

    private List<Product> products = Collections.emptyList();

    @Override
    public int getRowCount() {
        return ((Long)productService.countProcuct()).intValue();
    }

    @Override
    public int getPageSize() {
        return products.size();
    }

    @Override
    public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        products = productService.list(first/pageSize, pageSize);
        return products;
    }

    @Override
    public Product getRowData(String rowKey) {
        return productService.getProduct(Long.parseLong(rowKey));
    }

    @Override
    public Object getRowKey(Product object) {
        return object.getId();
    }
}

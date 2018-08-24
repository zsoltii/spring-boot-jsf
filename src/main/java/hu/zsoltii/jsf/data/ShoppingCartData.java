package hu.zsoltii.jsf.data;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import java.util.LinkedHashMap;
import java.util.Map;

@Named
@FlowScoped("shopping-cart")
public class ShoppingCartData {
    private Map<Long, ShoppingCart> products = new LinkedHashMap<>();

    public Map<Long, ShoppingCart> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, ShoppingCart> products) {
        this.products = products;
    }
}

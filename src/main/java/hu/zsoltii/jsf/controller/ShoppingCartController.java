package hu.zsoltii.jsf.controller;

import hu.zsoltii.jsf.data.Product;
import hu.zsoltii.jsf.data.ProductData;
import hu.zsoltii.jsf.data.ShoppingCart;
import hu.zsoltii.jsf.data.ShoppingCartData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class ShoppingCartController extends SpringBeanAutowiringSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

    @Autowired
    private ShoppingCartData shoppingCartData;

    @Autowired
    private ProductData productData;

    public String countShoppingCart() {
        int count = 0;

        for (Map.Entry<Long, ShoppingCart> shoppingCartEntry : shoppingCartData.getProducts().entrySet()) {
            count += shoppingCartEntry.getValue().getCount();
        }

        return Integer.toString(count);
    }

    public void addToShoppingCart() {
        try {
            Product product = productData.getProduct();

            ShoppingCart shoppingCart;
            if(shoppingCartData.getProducts().containsKey(product.getId())) {
                shoppingCart = shoppingCartData.getProducts().get(product.getId());
                shoppingCart.setCount(shoppingCart.getCount() + 1);
            } else {
                shoppingCart = new ShoppingCart();
                shoppingCart.setProduct(product);
                shoppingCart.setCount(1);
                shoppingCartData.getProducts().put(product.getId(), shoppingCart);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Shopping Cart:", product.getName() + " is added to Shopping Cart!"));
        } catch (Exception e) {
            LOGGER.error("addToShoppingCart", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shopping Cart error:", e.getMessage()));
        }
    }
}

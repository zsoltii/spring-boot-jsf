package hu.zsoltii.jsf.controller;

import hu.zsoltii.jsf.data.Product;
import hu.zsoltii.jsf.data.ProductData;
import hu.zsoltii.jsf.service.ProductService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.ByteArrayInputStream;

@Named
@RequestScoped
public class ProductController extends SpringBeanAutowiringSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductData productData;

    @Autowired
    private ProductService productService;

    public void preNewProduct() {
        productData.setProduct(new Product());
    }

    public void preEditProduct() {
        productData.setProduct(null);
    }

    public void imageUpload(FileUploadEvent fileUploadEvent) {
        UploadedFile uploadedFile = fileUploadEvent.getFile();
        productData.getProduct().setImage(uploadedFile.getContents());
    }

    public void save() {
        try {
            productService.save(productData.getProduct());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product:", "New product is saved!"));
        } catch (Exception e) {
            LOGGER.error("save", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product error:", e.getMessage()));
        }
    }

    public void update() {
        try {
            productService.update(productData.getProduct());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product:", "New product is updated!"));
        } catch (Exception e) {
            LOGGER.error("save", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product error:", e.getMessage()));
        }
    }

    public StreamedContent getImage() {
        return new DefaultStreamedContent(new ByteArrayInputStream(productData.getProduct().getImage()));
    }
}

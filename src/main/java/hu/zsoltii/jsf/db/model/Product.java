package hu.zsoltii.jsf.db.model;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Product extends AbstractEntity {
    private String name;
    @Lob
    private String descrption;
    @Lob
    private byte[] image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

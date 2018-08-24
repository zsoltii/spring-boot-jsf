package hu.zsoltii.jsf.data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

public class Product implements Serializable {
    private Long id;
    private String name;
    private String description;
    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getGraphicImage() throws IOException {
        if(image != null) {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            return "data:image/png;base64, " + Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } else {
            return null;
        }
    }
}

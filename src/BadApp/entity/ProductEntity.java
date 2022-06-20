package BadApp.entity;

import lombok.Data;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.Objects;

@Data
public class ProductEntity {
    int id;
    String title;
    String productType;
    String description;
    String imagePath;
    int cost;
    String regDate;
    ImageIcon image;

    public ProductEntity(int id, String title, String productType, String description, String imagePath, int cost, String regDate) {
        this.id = id;
        this.title = title;
        this.productType = productType;
        this.description = description;
        this.imagePath = imagePath;
        this.cost = cost;
        this.regDate = regDate;
        try {
            this.image = new ImageIcon(
                    ImageIO.read(ProductEntity.class.getClassLoader().getResource(imagePath)).getScaledInstance(60,60,Image.SCALE_DEFAULT)
            );
        } catch (Exception e) {
            try {
                this.image = new ImageIcon(
                        ImageIO.read(ProductEntity.class.getClassLoader().getResource("icon.png")).getScaledInstance(60,60,Image.SCALE_DEFAULT)
                );
            } catch (Exception ex) {

            }
        }
    }

    public ProductEntity(String title, String productType, String description, String imagePath, int cost, String regDate) {
        this(-1,title,productType,description,imagePath,cost,regDate);
    }
}

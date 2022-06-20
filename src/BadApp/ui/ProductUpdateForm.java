package BadApp.ui;

import BadApp.entity.ProductEntity;
import BadApp.manager.ProductEntityManager;
import BadApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class ProductUpdateForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleTextField;
    private JTextField imageTextField;
    private JTextField typeTextField;
    private JTextField descTextField;
    private JSpinner costSpinner;
    private JTextField regDateTextField;
    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField idTextField;
    private ProductEntity product;

    public ProductUpdateForm(ProductEntity product) {
        super(800,600);
        setContentPane(mainPanel);
        this.product = product;
        initFields();
        initButtons();
        setVisible(true);
    }
    private void initFields(){
        idTextField.setText(String.valueOf(product.getId()));
        titleTextField.setText(product.getTitle());
        typeTextField.setText(product.getProductType());
        descTextField.setText(product.getDescription());
        imageTextField.setText(product.getImagePath());
        costSpinner.setValue(product.getCost());
        regDateTextField.setText(product.getRegDate());
    }
    private void initButtons(){
        deleteButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,"del?","del",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                try {
                    ProductEntityManager.delete(product.getId());
                    JOptionPane.showMessageDialog(this,"good");
                    dispose();
                    new ProductTableForm();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        backButton.addActionListener(e -> {
            dispose();
            new ProductTableForm();
        });
        addButton.addActionListener(e -> {
            String title = titleTextField.getText();
            String productType = typeTextField.getText();
            String desc = descTextField.getText();
            String image = imageTextField.getText();
            int cost = (int) costSpinner.getValue();
            String regDate = regDateTextField.getText();

            product.setTitle(title);
            product.setProductType(productType);
            product.setDescription(desc);
            product.setImagePath(image);
            product.setCost(cost);
            product.setRegDate(regDate);

            try {
                ProductEntityManager.update(product);
                JOptionPane.showMessageDialog(this,"good");
                dispose();
                new ProductTableForm();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

    }
}

package BadApp.ui;

import BadApp.entity.ProductEntity;
import BadApp.manager.ProductEntityManager;
import BadApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class ProductCreateForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleTextField;
    private JTextField imageTextField;
    private JTextField typeTextField;
    private JTextField descTextField;
    private JSpinner costSpinner;
    private JTextField regDateTextField;
    private JButton backButton;
    private JButton addButton;


    public ProductCreateForm() {
        super(800,600);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }
    private void initButtons(){
        backButton.addActionListener(e -> {
            dispose();
            new ProductTableForm();
        });
        addButton.addActionListener(e -> {
            String title = titleTextField.getText();
            if (title.isEmpty()||title.length()>100){
                JOptionPane.showMessageDialog(this,"war","war",JOptionPane.ERROR_MESSAGE);
                return;
            }
            String productType = typeTextField.getText();
            String desc = descTextField.getText();
            String image = imageTextField.getText();
            int cost = (int) costSpinner.getValue();
            if (cost<0){
                JOptionPane.showMessageDialog(this,"war","war",JOptionPane.ERROR_MESSAGE);
                return;
            }
            String regDate = regDateTextField.getText();

            ProductEntity product = new ProductEntity(
              title,productType,desc,image,cost,regDate
            );
            try {
                ProductEntityManager.insert(product);
                JOptionPane.showMessageDialog(this,"good");
                dispose();
                new ProductTableForm();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

    }
}

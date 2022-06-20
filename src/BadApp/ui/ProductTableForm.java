package BadApp.ui;

import BadApp.App;
import BadApp.entity.ProductEntity;
import BadApp.manager.ProductEntityManager;
import BadApp.util.BaseForm;
import BadApp.util.CustomTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ProductTableForm extends BaseForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;
    private CustomTableModel<ProductEntity> model;

    public ProductTableForm() {
        super(1000,800);
        setContentPane(mainPanel);
        initTable();
        initButtons();
        setVisible(true);
    }
    private void initButtons(){
        addButton.addActionListener(e -> {
            dispose();
            new ProductCreateForm();
        });

    }
    private void initTable(){

        if (!App.IS_ADMIN){
            addButton.setEnabled(false);
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(60);
        try {
            model = new CustomTableModel<>(
                    ProductEntity.class,
                    new String[]{"1","2","3","4","5","6","7","8"},
                    ProductEntityManager.select()
            );
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (App.IS_ADMIN){
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount()==2){
                        int row = table.rowAtPoint(e.getPoint());
                        if (row!=-1){
                            new ProductUpdateForm(model.getRows().get(row));
                            dispose();
                        }
                    }
                }
            });
            System.out.println(model.getRows().get(2));

        }

    }
}

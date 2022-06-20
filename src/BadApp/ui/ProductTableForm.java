package BadApp.ui;

import BadApp.App;
import BadApp.entity.ProductEntity;
import BadApp.manager.ProductEntityManager;
import BadApp.util.BaseForm;
import BadApp.util.CustomTableModel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.*;

public class ProductTableForm extends BaseForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;
    private JButton upButton;
    private JButton downButton;
    private JComboBox comboBox;
    private JLabel countLabel;
    private CustomTableModel<ProductEntity> model;

    public ProductTableForm() {
        super(1000,800);
        setContentPane(mainPanel);
        initTable();
        initBox();
        initButtons();
        setVisible(true);
    }
    private void initLabel(int actual, int max){
        countLabel.setText("Отображено записей: "+actual+"/"+max);
    }
    private void initBox(){
        comboBox.addItem("All");
        try {
            List<ProductEntity> list = ProductEntityManager.select();
            Set<String> names = new HashSet<>();
            for (ProductEntity c:list){
                names.add(c.getProductType());
            }
            for (String s : names){
                comboBox.addItem(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    try {
                        List<ProductEntity> list = ProductEntityManager.select();
                        int max = model.getRows().size();
                        if (comboBox.getSelectedIndex()!=0){
                            list.removeIf(c->!c.getProductType().equals(comboBox.getSelectedItem()));
                        }
                        model.setRows(list);
                        model.fireTableDataChanged();
                        initLabel(model.getRows().size(),max);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    private void initButtons(){
        addButton.addActionListener(e -> {
            dispose();
            new ProductCreateForm();
        });
        upButton.addActionListener(e -> {
            Collections.sort(model.getRows(), new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    return Integer.compare(o1.getId(), o2.getId());
                }
            });
            model.fireTableDataChanged();
        });
        downButton.addActionListener(e -> {
            Collections.sort(model.getRows(), new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    return Integer.compare(o2.getId(), o1.getId());
                }
            });
            model.fireTableDataChanged();
        });
    }

    private void initTable(){
        table.setRowHeight(60);
        if (!App.IS_ADMIN){
            addButton.setEnabled(false);
        }
        table.getTableHeader().setReorderingAllowed(false);
        try {
            model = new CustomTableModel<>(
                    ProductEntity.class,
                    new String[]{"1","2","3","4","5","6","7","8"},
                    ProductEntityManager.select()
            );
            table.setModel(model);
            initLabel(model.getRows().size(),model.getRows().size());
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
        }

    }
}

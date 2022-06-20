package BadApp.ui;

import BadApp.App;
import BadApp.manager.UserEntityManager;
import BadApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class UserLoginForm extends BaseForm{
    private JPanel mainPanel;
    private JTextField loginTextField;
    private JPasswordField passwordField;
    private JButton guestButton;
    private JButton loginButton;

    public UserLoginForm() {
        super(600,400);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }
    private void initButtons(){
        guestButton.addActionListener(e -> {
            dispose();
            new ProductTableForm();
        });
        loginButton.addActionListener(e -> {
            String login = loginTextField.getText();
            String password = new String(passwordField.getPassword());
            try {
                String role = UserEntityManager.userRole(login,password);
                if (role==null){
                    JOptionPane.showMessageDialog(this,"","",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (role.equals("Администратор")){
                    App.IS_ADMIN=true;
                    JOptionPane.showMessageDialog(this,"","",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new ProductTableForm();
                }else {
                    JOptionPane.showMessageDialog(this,"","",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new ProductTableForm();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}

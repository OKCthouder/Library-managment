package 图书借阅管理系统;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UserDelete
    extends JFrame
    implements ActionListener {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2;
  Container c;
  JLabel userLabel, passwordLabel;
  JTextField userTextField;
  JPasswordField passwordTextField;
  JButton yesBtn, cancelBtn;
  public UserDelete() {
    super("删除用户");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    userLabel = new JLabel("用户名", JLabel.CENTER);
    passwordLabel = new JLabel("密码", JLabel.CENTER);
    userTextField = new JTextField(25);
    passwordTextField = new JPasswordField(25);
    yesBtn = new JButton("确定");
    cancelBtn = new JButton("取消");
    yesBtn.addActionListener(this);
    cancelBtn.addActionListener(this);
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(2, 2));
    panel1.add(userLabel);
    panel1.add(userTextField);
    panel1.add(passwordLabel);
    panel1.add(passwordTextField);
    panel2 = new JPanel();
    panel2.add(yesBtn);
    panel2.add(cancelBtn);
    c.add(panel1, BorderLayout.CENTER);
    c.add(panel2, BorderLayout.SOUTH);
    setSize(300, 300);
  }

  public void actionPerformed(ActionEvent e) {
    try {
      if (e.getSource() == cancelBtn) {
        db.closeConnection();
        this.dispose();
      }
      else if (e.getSource() == yesBtn) {
        char[] password = passwordTextField.getPassword();
        String passwordSTR = new String(password);
        String strSQL = "select * from userTable where userName='" +
            userTextField.getText().trim() + "' and password='" +
            passwordSTR + "'";
        if (userTextField.getText().trim().equals("")) {
          JOptionPane.showMessageDialog(null, "用户名不能为空！");
        }
        else if (passwordTextField.equals("")) {
          JOptionPane.showMessageDialog(null, "密码不能为空！");
        }
        else if (db.getResult(strSQL).first()) {
          strSQL = "delete userTable where username='" +
              userTextField.getText().trim() + "'";
         // if (db.updateSql(strSQL)>0) {
            db.getResult(strSQL);
            JOptionPane.showMessageDialog(null, "删除成功!");
            this.dispose();

        //  }
        //  else {
        //    JOptionPane.showMessageDialog(null, "删除失败！");
        //    this.dispose();
        //  }

          db.closeConnection();
        }
        else {
          JOptionPane.showMessageDialog(null, "不存在此用户或者密码错误！");
        }
      }
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }
}

package ͼ����Ĺ���ϵͳ;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UserAdd
    extends JFrame
    implements ActionListener {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  Container c;
  JPanel panel1, panel2;
  JLabel user_ID_Label,userLabel, passwordLabel, passwordConfirmLabel, loginPrivelegeLabel;
  JTextField user_ID_TextField,userTextField;
  JPasswordField passwordTextField, passwordConfirmTextField;
  JComboBox loginPrivelegeComboBox;
  JButton addBtn, cancelBtn;
  public UserAdd() {
    super("����û�");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    user_ID_Label = new JLabel("�û�ID", JLabel.CENTER);
    userLabel = new JLabel("�û���", JLabel.CENTER);
    passwordLabel = new JLabel("����", JLabel.CENTER);
    passwordConfirmLabel = new JLabel("ȷ������", JLabel.CENTER);
    loginPrivelegeLabel = new JLabel("��¼Ȩ��", JLabel.CENTER);
    user_ID_TextField = new JTextField(25);
    userTextField = new JTextField(25);
    passwordTextField = new JPasswordField(25);
    passwordConfirmTextField = new JPasswordField(25);
    loginPrivelegeComboBox = new JComboBox();
    loginPrivelegeComboBox.addItem("�鼮����Ա");
    loginPrivelegeComboBox.addItem("ѧ��");
    addBtn = new JButton("���");
    cancelBtn = new JButton("ȡ��");
    addBtn.addActionListener(this);
    cancelBtn.addActionListener(this);
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(5, 2));
    panel1.add(user_ID_Label);
    panel1.add(user_ID_TextField);
    panel1.add(userLabel);
    panel1.add(userTextField);
    panel1.add(passwordLabel);
    panel1.add(passwordTextField);
    panel1.add(passwordConfirmLabel);
    panel1.add(passwordConfirmTextField);
    panel1.add(loginPrivelegeLabel);
    panel1.add(loginPrivelegeComboBox);
    c.add(panel1, BorderLayout.CENTER);
    panel2 = new JPanel();
    panel2.add(addBtn);
    panel2.add(cancelBtn);
    c.add(panel2, BorderLayout.SOUTH);
    setSize(300, 300);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cancelBtn) {
      db.closeConnection();
      this.dispose();
    }
    else if (e.getSource() == addBtn) {
      try {
        String strSQL = "select * from userTable where userName='" +
            userTextField.getText().trim() + "'";
        if (userTextField.getText().trim().equals("")) {
          JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�");
        }
        else if (new String(passwordTextField.getPassword()).trim().equals("")) {
          JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
        }
        else if (!new String(passwordTextField.getPassword()).trim().equals(
            new String(passwordConfirmTextField.getPassword()).trim())) {
          JOptionPane.showMessageDialog(null, "������������벻һ�£�");
        }
        else {
          if (db.getResult(strSQL).first()) {
            JOptionPane.showMessageDialog(null, "���û��Ѿ����ڣ������������û�����");
          }
          else {
            strSQL = "insert into userTable values('" +
            	user_ID_TextField.getText().trim() + "','" +
                userTextField.getText().trim() + "','" +
                new String(passwordTextField.getPassword()).
                trim() + "','" + loginPrivelegeComboBox.getSelectedItem() +
                "')";
          //  if (db.updateSql(strSQL)>0) {
              this.dispose();
              rs = db.getResult(strSQL);
              JOptionPane.showMessageDialog(null, "����û��ɹ���");

          //  }
          //  else {
          //    JOptionPane.showMessageDialog(null, "����û�ʧ�ܣ�");
         //   }
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
}

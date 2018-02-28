package 图书借阅管理系统;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ReturnBook
    extends JFrame
    implements ActionListener {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2;
  Container c;
  JLabel returnedBookStudentLabel, returnedBookNameLabel,
      returnedDateLabel, returnedCommentLabel;
  JTextField returnedBookStudentTextField,
      returnedDateTextField, returnedCommentTextField;
  JButton clearBtn, yesBtn, cancelBtn;
  JComboBox bookNameComboBox = new JComboBox();
  public ReturnBook() {
    super("书籍还入");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    returnedBookStudentLabel = new JLabel("还书者姓名", JLabel.CENTER);
    returnedBookNameLabel = new JLabel("书名", JLabel.CENTER);
    returnedDateLabel = new JLabel("日期", JLabel.CENTER);
    returnedCommentLabel = new JLabel("备注", JLabel.CENTER);
    returnedBookStudentTextField = new JTextField(15);
    returnedDateTextField = new JTextField(15);
    returnedCommentTextField = new JTextField(15);
    try {
      String s = "";
      String strSQL = "select bookName from bookBrowse where is_returned='否'";
      rs = db.getResult(strSQL);
      while (rs.next()) {
        bookNameComboBox.addItem(rs.getString(1));
      }
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
    }
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(4, 2));
    panel1.add(returnedBookStudentLabel);
    panel1.add(returnedBookStudentTextField);
    panel1.add(returnedBookNameLabel);
    panel1.add(bookNameComboBox);
    panel1.add(returnedDateLabel);
    panel1.add(returnedDateTextField);
    panel1.add(returnedCommentLabel);
    panel1.add(returnedCommentTextField);
    c.add(panel1, BorderLayout.CENTER);
    panel2 = new JPanel();
    panel2.setLayout(new GridLayout(1, 3));
    clearBtn = new JButton("清空");
    yesBtn = new JButton("确定");
    cancelBtn = new JButton("取消");
    clearBtn.addActionListener(this);
    yesBtn.addActionListener(this);
    cancelBtn.addActionListener(this);
    panel2.add(clearBtn);
    panel2.add(yesBtn);
    panel2.add(cancelBtn);
    c.add(panel2, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cancelBtn) {
      this.dispose();
    }
    else if (e.getSource() == clearBtn) {
      returnedBookStudentTextField.setText("");
      returnedDateTextField.setText("");
      returnedCommentTextField.setText("");

    }
    else if (e.getSource() == yesBtn) {
      if (returnedBookStudentTextField.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "请输入还书者的姓名。。。");
      }
      else if (bookNameComboBox.getSelectedItem().equals("")) {
        JOptionPane.showMessageDialog(null, "图书馆没有出借过书！");
      }
      else {
        try {
          String strSQL = "update bookBrowse set returnDate='" +
              returnedDateTextField.getText().trim() + "',com='" +
              returnedCommentTextField.getText().trim() +
              "',is_returned='是' where studentName='" +
              returnedBookStudentTextField.getText().trim() + "'and bookName='" +
              bookNameComboBox.getSelectedItem() + "'";
          //if (db.updateSql(strSQL)>0) {
            rs = db.getResult(strSQL);
            JOptionPane.showMessageDialog(null, "还书完成！");
            strSQL =
                "update books set borrowed_count=borrowed_count-1 where bookname='" +
                bookNameComboBox.getSelectedItem() + "'";
            db.getResult(strSQL);
            this.dispose();
            db.closeConnection();            
          }
        //  else {
        //    JOptionPane.showMessageDialog(null, "还书失败！还书者姓名与书名不符合或数据库操作失败！");
        //    db.closeConnection();
        //    this.dispose();
        //  }
       // }
        catch (Exception ex) {
          System.out.println(ex.toString());
        }
      }
    }
  }
}

package ͼ����Ĺ���ϵͳ;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class BorrowBook
    extends JFrame
    implements ActionListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2;
  Container c;
  JLabel borrowedBook_Student_ID_Label,borrowedBookStudentLabel,
      borrowedBook_Book_ID_Label, borrowedBookNameLabel,
      borrowedDateLabel, borrowedCommentLabel;
  JTextField borrowedBook_Student_ID_TextField, borrowedBookStudentTextField,
  borrowedBook_Book_ID_TextField, borrowedDateTextField, borrowedCommentTextField;
  JButton clearBtn, yesBtn, cancelBtn;
  JComboBox bookNameComboBox = new JComboBox();
  public BorrowBook() {
    super("�鼮����");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    borrowedBook_Student_ID_Label = new JLabel("������ID", JLabel.CENTER);
    borrowedBookStudentLabel = new JLabel("����������", JLabel.CENTER);
    borrowedBook_Book_ID_Label = new JLabel("�鼮ID", JLabel.CENTER);
    borrowedBookNameLabel = new JLabel("����", JLabel.CENTER);
    borrowedDateLabel = new JLabel("����", JLabel.CENTER);
    borrowedCommentLabel = new JLabel("��ע", JLabel.CENTER);
    borrowedBookStudentTextField = new JTextField(25);
    borrowedDateTextField = new JTextField(25);
    borrowedBook_Student_ID_TextField = new JTextField(25);
    borrowedBook_Book_ID_TextField = new JTextField(25);
    borrowedCommentTextField = new JTextField(25);
    try {
      String strSQL =
          "select bookName from books where books_count>=borrowed_count";
      rs = db.getResult(strSQL);
      while (rs.next()) {
        bookNameComboBox.addItem(rs.getString(1).trim());
      }
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
    }
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(6, 2));
    panel1.add(borrowedBook_Student_ID_Label);
    panel1.add(borrowedBook_Student_ID_TextField);
    panel1.add(borrowedBookStudentLabel);
    panel1.add(borrowedBookStudentTextField);
    panel1.add(borrowedBook_Book_ID_Label);
    panel1.add(borrowedBook_Book_ID_TextField);
    panel1.add(borrowedBookNameLabel);
    panel1.add(bookNameComboBox);
    panel1.add(borrowedDateLabel);
    panel1.add(borrowedDateTextField);
    panel1.add(borrowedCommentLabel);
    panel1.add(borrowedCommentTextField);
    c.add(panel1, BorderLayout.CENTER);
    panel2 = new JPanel();
    panel2.setLayout(new GridLayout(1, 3));
    clearBtn = new JButton("���");
    yesBtn = new JButton("ȷ��");
    cancelBtn = new JButton("ȡ��");
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
      borrowedBookStudentTextField.setText("");
      borrowedBook_Student_ID_TextField.setText("");
      borrowedBook_Book_ID_TextField.setText("");
      borrowedDateTextField.setText("");
      borrowedCommentTextField.setText("");

    }
    else if (e.getSource() == yesBtn) {
      if (borrowedBookStudentTextField.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "����������ߵ�����������");
      }
      else if (bookNameComboBox.getSelectedItem().equals("")) {
        JOptionPane.showMessageDialog(null, "�Բ������������û���飬\n�����ڲ��ܽ���!");
      }
      else {
        try {
          String strSQL = "insert into bookBrowse(StudentID,studentname,BookID,bookname,borrowdate,returndate,com,Is_Returned) values('" +
        	  borrowedBook_Student_ID_TextField.getText().trim() + "','" +
        	  borrowedBookStudentTextField.getText().trim() + "','" +
        	  borrowedBook_Book_ID_TextField.getText().trim() + "','" +
              bookNameComboBox.getSelectedItem() + "','" +
              borrowedDateTextField.getText().trim() + "','','" +
              borrowedCommentTextField.getText().trim() + "','" +
              "��" + "')";
          if (db.updateSql(strSQL)>0) {
            //rs = db.getResult(strSQL);
            JOptionPane.showMessageDialog(null, "������ɣ�");
            strSQL =
                "update books set borrowed_count=borrowed_count+1 where bookname='" +
                bookNameComboBox.getSelectedItem() + "'";
            db.updateSql(strSQL);
            db.closeConnection();
            this.dispose();
          }
          else {
            JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
            db.closeConnection();
            this.dispose();
          }
        }
        catch (Exception ex) {
          System.out.println(ex.toString());
        }
      }
    }

  }
}

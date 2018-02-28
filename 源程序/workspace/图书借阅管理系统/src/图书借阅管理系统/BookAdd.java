package ͼ����Ĺ���ϵͳ;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;


import java.sql.*;

public class BookAdd
    extends JFrame
    implements ActionListener {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2;
  JLabel book_ID_Label,bookNameLabel, pressNameLabel,
      authorLabel, addressLabel,
      pressDateLabel, priceLabel, bookCountLabel, commentLabel;
  JTextField book_ID_TextField,bookNameTextField, pressNameTextField,
      authorTextField, addressTextField,
      pressDateTextField, priceTextField, bookCountTextField, commentTextField;
  Container c;
  JButton clearBtn, addBtn, exitBtn;
  public BookAdd() {
    super("����鼮��Ϣ");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    book_ID_Label = new JLabel("���", JLabel.CENTER);
    bookNameLabel = new JLabel("����", JLabel.CENTER);
    pressNameLabel = new JLabel("������", JLabel.CENTER);
    authorLabel = new JLabel("����", JLabel.CENTER);
    addressLabel = new JLabel("��ַ", JLabel.CENTER);
    pressDateLabel = new JLabel("��������", JLabel.CENTER);
    priceLabel = new JLabel("�۸�", JLabel.CENTER);
    bookCountLabel = new JLabel("������Ŀ", JLabel.CENTER);
    commentLabel = new JLabel("��ע", JLabel.CENTER);
    book_ID_TextField = new JTextField(25);
    bookNameTextField = new JTextField(25);
    pressNameTextField = new JTextField(25);
    authorTextField = new JTextField(25);
    addressTextField = new JTextField(25);
    pressDateTextField = new JTextField(25);
    priceTextField = new JTextField(25);
    bookCountTextField = new JTextField(25);
    commentTextField = new JTextField(25);
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(9, 2));
    panel1.add(book_ID_Label);
    panel1.add(book_ID_TextField);
    panel1.add(bookNameLabel);
    panel1.add(bookNameTextField);
    panel1.add(pressNameLabel);
    panel1.add(pressNameTextField);
    panel1.add(authorLabel);
    panel1.add(authorTextField);
    panel1.add(addressLabel);
    panel1.add(addressTextField);
    panel1.add(pressDateLabel);
    panel1.add(pressDateTextField);
    panel1.add(priceLabel);
    panel1.add(priceTextField);
    panel1.add(bookCountLabel);
    panel1.add(bookCountTextField);
    panel1.add(commentLabel);
    panel1.add(commentTextField);
    panel2 = new JPanel();
    panel2.setLayout(new GridLayout(1, 3));
    clearBtn = new JButton("���");
    clearBtn.addActionListener(this);
    addBtn = new JButton("���");
    addBtn.addActionListener(this);
    exitBtn = new JButton("�˳�");
    exitBtn.addActionListener(this);
    panel2.add(clearBtn);
    panel2.add(addBtn);
    panel2.add(exitBtn);
    c.add(panel1, BorderLayout.CENTER);
    c.add(panel2, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == exitBtn) {
      db.closeConnection();
      this.dispose();
    }
    else if (e.getSource() == clearBtn) {
      book_ID_TextField.setText("");
      bookNameTextField.setText("");
      pressNameTextField.setText("");
      authorTextField.setText("");
      addressTextField.setText("");
      pressDateTextField.setText("");
      priceTextField.setText("");
      bookCountTextField.setText("");
      commentTextField.setText("");
    }
    else if (e.getSource() == addBtn) {
      if (bookNameTextField.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
      }
      else if (pressNameTextField.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "�����粻��Ϊ�գ�");
      }
      else if (authorTextField.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "���߲���Ϊ�գ�");
      }
      else if (bookCountTextField.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "������Ŀ����Ϊ�գ�");
      }
      else {
        try {
          String strSQL = "insert into books(BookID,bookname,press,author,address,pressDate,price,books_count,borrowed_count,com) values('"+
        	  book_ID_TextField.getText().trim() + "','" +
        	  bookNameTextField.getText().trim() + "','" +
              pressNameTextField.getText().trim() + "','" +
              authorTextField.getText().trim() + "','" +
              addressTextField.getText().trim() + "','" +
              pressDateTextField.getText().trim() + "','" +
              priceTextField.getText().trim() + "','" +
              bookCountTextField.getText().trim() + "','" +
              0 + "','" +
              commentTextField.getText().trim() + "')";
         // if (db.updateSql(strSQL)>0) {
        //	  System.out.println("test");
            rs = db.getResult(strSQL);
            JOptionPane.showMessageDialog(null, "����鼮�ɹ���");
            this.dispose();
         // }
//          else {
//            JOptionPane.showMessageDialog(null, "����鼮ʧ�ܣ�");
//            this.dispose();
//          }
          db.closeConnection();
        }
        catch (Exception ex) {
          System.out.println(ex.toString());
        }
      }

    }
  }
}

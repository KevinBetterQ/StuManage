
package com.up.student.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.up.student.AppConstants;
import com.up.student.DAO;
import com.up.student.base.BaseDAO;
import com.up.student.dao.AdminDAO;
import com.up.student.model.Admin;

/**
 * 模块说明： 注册界面
 * 
 */
public class RegisterView extends JFrame {

	private static final long serialVersionUID = -5278598737087831336L;
	private JPanel jPanelCenter, jPanelSouth;
	private JTextField name;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField rpassword;
	private JButton regiButton, resetButton;

	public RegisterView() {
		init();
	}

	private void init() {
		this.setTitle("Register");

		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(5, 2));
		jPanelCenter.add(new JLabel(AppConstants.LOGIN_USERNAME));
		username = new JTextField();
		jPanelCenter.add(username);
		jPanelCenter.add(new JLabel("姓名"));
		name = new JTextField();
		jPanelCenter.add(name);
		
		jPanelCenter.add(new JLabel(AppConstants.LOGIN_PASSWORD));
		password = new JPasswordField();
		jPanelCenter.add(password);
		jPanelCenter.add(new JLabel(AppConstants.LOGIN_PASSWORD));
		rpassword = new JPasswordField();
		jPanelCenter.add(rpassword);
		// enter key listener
		password.addKeyListener(new LoginListener());
		
		
		jPanelCenter.add(new JLabel("----------------------------------------------"));
		jPanelCenter.add(new JLabel("----------------------------------------------"));

		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		regiButton = new JButton("老师注册");
		regiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				teachercheck();
			}
		});
		jPanelSouth.add(regiButton);
		resetButton = new JButton("学生注册");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				studentcheck();
			}
		});
		jPanelSouth.add(resetButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(450, 250, 375, 140);
		this.setResizable(false);
		this.setVisible(true);
	}

	private class LoginListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				teachercheck();
			}
		}
	}

	private void teachercheck() {
		AdminDAO adminDAO = (AdminDAO) BaseDAO.getAbilityDAO(DAO.AdminDAO);
		if (String.valueOf(rpassword.getPassword()).equals(String.valueOf(password.getPassword()))) {
			
			Admin teacher = new Admin();
			teacher.setName(name.getText());
			teacher.setUsername(username.getText());
			teacher.setPassword(String.valueOf(password.getPassword()));
			teacher.setPrivi("teacher");
			try {
				if(adminDAO.insert(teacher)){
					JOptionPane.showMessageDialog(this, "注册成功", "消息", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new LoginView();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(this, "密码不一致", "消息", JOptionPane.INFORMATION_MESSAGE);
			name.setText("");
			username.setText("");
			password.setText("");
			rpassword.setText("");
		}
	}
	
	private void studentcheck() {
		AdminDAO adminDAO = (AdminDAO) BaseDAO.getAbilityDAO(DAO.AdminDAO);
		if (String.valueOf(rpassword.getPassword()).equals(String.valueOf(password.getPassword()))) {
			JOptionPane.showMessageDialog(this, "注册成功", "消息", JOptionPane.INFORMATION_MESSAGE);
			Admin teacher = new Admin();
			teacher.setName(name.getText());
			teacher.setUsername(username.getText());
			teacher.setPassword(String.valueOf(password.getPassword()));
			teacher.setPrivi("student");
			try {
				if(adminDAO.insert(teacher)){
					dispose();
					new LoginView();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(this, "密码不一致", "消息", JOptionPane.INFORMATION_MESSAGE);
			name.setText("");
			username.setText("");
			password.setText("");
			rpassword.setText("");
		}
	}

}

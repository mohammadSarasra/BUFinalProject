package edu.bu.domain.CourseProject;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextField;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JTextField textFieldName;
	private JPasswordField passwordField;
	ObjectMapper om = new ObjectMapper();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textFieldName = new JTextField();
		textFieldName.setBounds(93, 39, 86, 20);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(93, 91, 86, 20);
		frame.getContentPane().add(passwordField);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(22, 42, 46, 14);
		frame.getContentPane().add(lblName);

		JLabel lblPass = new JLabel("Pass:");
		lblPass.setBounds(22, 94, 46, 14);
		frame.getContentPane().add(lblPass);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Adminstrator> admin = null;
				int i = 0;
				boolean accesslogin = false;

				try {

					admin = om.readValue(new File("Admins.txt"), new TypeReference<ArrayList<Adminstrator>>() {
					});
					while (i < admin.size()) {
						if (textFieldName.getText().equals(admin.get(i).getUserName())
								&& passwordField.getText().equals(admin.get(i).getPassword()))
							accesslogin = true;
						i++;
					}
					if (accesslogin
							|| (textFieldName.getText().equals("admin") && passwordField.getText().equals("admin"))) {
						frame.hide();
						App.main(null);

					} else
						JOptionPane.showMessageDialog(null, "Incorrect username or password");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		btnLogin.setBounds(269, 141, 89, 23);
		frame.getContentPane().add(btnLogin);
	}
}

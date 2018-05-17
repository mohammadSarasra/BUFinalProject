package edu.bu.domain.CourseProject;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class App {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldNameE;
	private JTextField textFieldNameS;
	private JTextField textFieldAge;
	private JTextField textFieldSalary;
	private JTextField textFieldSalaryE;
	private JTextField textFieldGrade;
	private JTextField textFieldStreetN;
	private JTextField textFieldStreetNE;
	private JTextField textFieldStreetNS;
	private JTextField textFieldStreetNum;
	private JTextField textFieldStreetNumE;
	private JTextField textFieldStreetNumS;
	private JTextField textFieldCity;
	private JTextField textFieldCityE;
	private JTextField textFieldCityS;
	private JTextField textFieldNameA;
	private JTextField textFieldUName;

	private JTextField textFieldStreetNA;
	private JTextField textFieldStreetNumA;
	private JTextField textFieldCityA;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textFieldSName;
	private JTextField textFieldSStreetN;
	private JTextField textFieldSHNum;
	private JTextField textFieldSCity;
	private JTextField textFieldSOG;
	private JTextField textFieldER;
	private JTextField textFieldSR;
	static ArrayList<Person> employeelist = new ArrayList<>();
	static ArrayList<Person> studentlist = new ArrayList<>();
	static ArrayList<Adminstrator> admins = new ArrayList<>();
	static File admintxt = new File("Admins.txt");
	static File employeetxt = new File("Employee.txt");
	static File studenttxt = new File("Students.txt");
	static ObjectMapper om = new ObjectMapper();
	static ArrayList<Person> searchlist = new ArrayList();
	static int i;
	List<Integer> agelist = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		if (admintxt.exists()) {
			try {
				admins = om.readValue(new File("Admins.txt"), new TypeReference<ArrayList>() {
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (employeetxt.exists()) {
			try {
				employeelist = om.readValue(new File("Employee.txt"), new TypeReference<List<Employee>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (studenttxt.exists()) {
			try {
				studentlist = om.readValue(new File("Students.txt"), new TypeReference<ArrayList<Student>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the application.
	 */

	public App() {
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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(tabbedPane);
		age(agelist);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Add", null, panel_2, null);
		panel_2.setLayout(null);

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 429, 233);
		panel_2.add(tabbedPane_2);

		JPanel paneAddEmployee = new JPanel();
		tabbedPane_2.addTab("Employee", null, paneAddEmployee, null);
		paneAddEmployee.setLayout(null);

		textFieldNameE = new JTextField();
		textFieldNameE.setBounds(65, 26, 86, 20);
		paneAddEmployee.add(textFieldNameE);
		textFieldNameE.setColumns(10);

		textFieldSalaryE = new JTextField();
		textFieldSalaryE.setBounds(65, 112, 86, 20);
		paneAddEmployee.add(textFieldSalaryE);
		textFieldSalaryE.setColumns(10);

		textFieldStreetNE = new JTextField();
		textFieldStreetNE.setBounds(275, 26, 86, 20);
		paneAddEmployee.add(textFieldStreetNE);
		textFieldStreetNE.setColumns(10);

		textFieldStreetNumE = new JTextField();
		textFieldStreetNumE.setBounds(275, 69, 86, 20);
		paneAddEmployee.add(textFieldStreetNumE);
		textFieldStreetNumE.setColumns(10);

		textFieldCityE = new JTextField();
		textFieldCityE.setBounds(275, 112, 86, 20);
		paneAddEmployee.add(textFieldCityE);
		textFieldCityE.setColumns(10);
		final JComboBox comboBoxAgeE = new JComboBox(agelist.toArray());
		comboBoxAgeE.setBounds(65, 69, 86, 20);
		paneAddEmployee.add(comboBoxAgeE);
		JButton btnNewButtonAdd = new JButton("Add");
		btnNewButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Address address = new Address(textFieldStreetNE.getText(), textFieldStreetNumE.getText(),
						textFieldCityE.getText());
				Employee employee = new Employee(textFieldNameE.getText(), (int) comboBoxAgeE.getSelectedItem(),
						address, Integer.parseInt(textFieldSalaryE.getText()));
				employeelist.add(employee);
				textFieldNameE.setText(null);
				textFieldSalaryE.setText(null);
				comboBoxAgeE.setSelectedItem(null);
				textFieldStreetNE.setText(null);
				textFieldStreetNumE.setText(null);
				textFieldCityE.setText(null);
			}
		});
		btnNewButtonAdd.setBounds(155, 161, 89, 23);
		paneAddEmployee.add(btnNewButtonAdd);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 29, 46, 14);
		paneAddEmployee.add(lblName);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(10, 72, 46, 14);
		paneAddEmployee.add(lblAge);

		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(9, 115, 46, 14);
		paneAddEmployee.add(lblSalary);

		JLabel lblStreetName = new JLabel("Street Name:");
		lblStreetName.setBounds(185, 29, 80, 14);
		paneAddEmployee.add(lblStreetName);

		JLabel lblHouseNumber = new JLabel("House Number:");
		lblHouseNumber.setBounds(185, 72, 80, 14);
		paneAddEmployee.add(lblHouseNumber);

		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(185, 115, 46, 14);
		paneAddEmployee.add(lblCity);

		JPanel panelAddStudent = new JPanel();
		tabbedPane_2.addTab("Student", null, panelAddStudent, null);
		panelAddStudent.setLayout(null);
		textFieldNameS = new JTextField();
		textFieldNameS.setBounds(65, 26, 86, 20);
		panelAddStudent.add(textFieldNameS);
		textFieldNameS.setColumns(10);

		textFieldGrade = new JTextField();
		textFieldGrade.setBounds(65, 112, 86, 20);
		panelAddStudent.add(textFieldGrade);
		textFieldGrade.setColumns(10);

		textFieldStreetNS = new JTextField();
		textFieldStreetNS.setBounds(275, 26, 86, 20);
		panelAddStudent.add(textFieldStreetNS);
		textFieldStreetNS.setColumns(10);

		textFieldStreetNumS = new JTextField();
		textFieldStreetNumS.setBounds(275, 69, 86, 20);
		panelAddStudent.add(textFieldStreetNumS);
		textFieldStreetNumS.setColumns(10);

		textFieldCityS = new JTextField();
		textFieldCityS.setBounds(275, 112, 86, 20);
		panelAddStudent.add(textFieldCityS);
		textFieldCityS.setColumns(10);
		final JComboBox comboBoxAgeS = new JComboBox(agelist.toArray());
		comboBoxAgeS.setBounds(65, 69, 86, 20);
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Address address = new Address(textFieldStreetNS.getText(), textFieldStreetNumS.getText(),
						textFieldCityS.getText());
				Student student = new Student(textFieldNameS.getText(), (int) comboBoxAgeS.getSelectedItem(), address,
						Integer.parseInt(textFieldGrade.getText()));
				studentlist.add(student);

				textFieldNameS.setText(null);
				textFieldGrade.setText(null);
				comboBoxAgeS.setSelectedItem(null);
				textFieldStreetNS.setText(null);
				textFieldStreetNumS.setText(null);
				textFieldCityS.setText(null);
			}
		});

		panelAddStudent.add(comboBoxAgeS);
		btnAdd.setBounds(150, 160, 89, 23);
		panelAddStudent.add(btnAdd);

		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setBounds(9, 29, 46, 14);
		panelAddStudent.add(lblName_1);

		JLabel lblAge_1 = new JLabel("Age:");
		lblAge_1.setBounds(9, 72, 46, 14);
		panelAddStudent.add(lblAge_1);

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(9, 115, 46, 14);
		panelAddStudent.add(lblGrade);

		JLabel lblStreetName_1 = new JLabel("Street Name:");
		lblStreetName_1.setBounds(183, 29, 82, 14);
		panelAddStudent.add(lblStreetName_1);

		JLabel lblHouseNumber_1 = new JLabel("House Number:");
		lblHouseNumber_1.setBounds(183, 72, 82, 14);
		panelAddStudent.add(lblHouseNumber_1);

		JLabel lblCity_1 = new JLabel("City:");
		lblCity_1.setBounds(183, 115, 82, 14);
		panelAddStudent.add(lblCity_1);

		JPanel panelAddAdmin = new JPanel();
		tabbedPane_2.addTab("Administrator", null, panelAddAdmin, null);
		panelAddAdmin.setLayout(null);

		textFieldNameA = new JTextField();
		textFieldNameA.setBounds(69, 27, 86, 20);
		panelAddAdmin.add(textFieldNameA);
		textFieldNameA.setColumns(10);

		final JComboBox comboBoxAgeA = new JComboBox(agelist.toArray());
		comboBoxAgeA.setBounds(69, 58, 86, 20);
		panelAddAdmin.add(comboBoxAgeA);

		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Address address = new Address(textFieldStreetNA.getText(), textFieldStreetNumA.getText(),
						textFieldCityA.getText());
				Adminstrator admin = new Adminstrator(textFieldNameA.getText(), (int) comboBoxAgeA.getSelectedItem(),
						address, textFieldUName.getText(), passwordField.getText());
				admins.add(admin);

				textFieldNameA.setText(null);
				comboBoxAgeA.setSelectedItem(null);
				textFieldStreetNA.setText(null);
				textFieldStreetNumA.setText(null);
				textFieldCityA.setText(null);
				textFieldUName.setText(null);
				passwordField.setText(null);
			}
		});
		btnAdd_1.setBounds(325, 170, 89, 23);
		panelAddAdmin.add(btnAdd_1);

		JLabel lblName_2 = new JLabel("Name:");
		lblName_2.setBounds(10, 30, 46, 14);
		panelAddAdmin.add(lblName_2);

		JLabel lblPass = new JLabel("Pass:");
		lblPass.setBounds(10, 144, 46, 14);
		panelAddAdmin.add(lblPass);

		textFieldUName = new JTextField();
		textFieldUName.setBounds(69, 112, 86, 20);
		panelAddAdmin.add(textFieldUName);
		textFieldUName.setColumns(10);

		JLabel lblAge_2 = new JLabel("Age:");
		lblAge_2.setBounds(10, 61, 46, 14);
		panelAddAdmin.add(lblAge_2);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 115, 65, 14);
		panelAddAdmin.add(lblUsername);

		textFieldStreetNA = new JTextField();
		textFieldStreetNA.setBounds(287, 27, 86, 20);
		panelAddAdmin.add(textFieldStreetNA);
		textFieldStreetNA.setColumns(10);

		textFieldStreetNumA = new JTextField();
		textFieldStreetNumA.setBounds(287, 58, 86, 20);
		panelAddAdmin.add(textFieldStreetNumA);
		textFieldStreetNumA.setColumns(10);

		textFieldCityA = new JTextField();
		textFieldCityA.setBounds(287, 89, 86, 20);
		panelAddAdmin.add(textFieldCityA);
		textFieldCityA.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(69, 142, 86, 17);
		panelAddAdmin.add(passwordField);

		JLabel lblStreetName_3 = new JLabel("Street Name:");
		lblStreetName_3.setBounds(188, 29, 65, 17);
		panelAddAdmin.add(lblStreetName_3);

		JLabel lblHouseNumbrr = new JLabel("House Numbrr:");
		lblHouseNumbrr.setBounds(188, 61, 89, 14);
		panelAddAdmin.add(lblHouseNumbrr);

		JLabel lblCity_3 = new JLabel("City:");
		lblCity_3.setBounds(188, 92, 46, 14);
		panelAddAdmin.add(lblCity_3);

		JPanel panelSearch = new JPanel();
		tabbedPane.addTab("Search", null, panelSearch, null);
		panelSearch.setLayout(null);

		JLabel lblName_3 = new JLabel("Name:");
		lblName_3.setBounds(10, 27, 46, 14);
		panelSearch.add(lblName_3);

		textField = new JTextField();
		textField.setBounds(63, 24, 124, 20);
		panelSearch.add(textField);
		textField.setColumns(10);

		final JCheckBox chckbxEmployee = new JCheckBox("Employee");
		chckbxEmployee.setBounds(20, 51, 97, 23);
		panelSearch.add(chckbxEmployee);

		final JCheckBox chckbxStudent = new JCheckBox("Student");
		chckbxStudent.setBounds(119, 51, 97, 23);
		panelSearch.add(chckbxStudent);

		age(agelist);
		final JComboBox comboBox = new JComboBox(agelist.toArray());
		comboBox.setBounds(333, 33, 86, 17);
		panelSearch.add(comboBox);

		JLabel lblName_4 = new JLabel("Name:");
		lblName_4.setBounds(260, 11, 46, 14);
		panelSearch.add(lblName_4);

		JLabel lblAge_3 = new JLabel("Age:");
		lblAge_3.setBounds(260, 36, 46, 14);
		panelSearch.add(lblAge_3);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(260, 55, 46, 14);
		panelSearch.add(lblAddress);

		JLabel lblStreetName_2 = new JLabel("Street Name:");
		lblStreetName_2.setBounds(260, 80, 64, 14);
		panelSearch.add(lblStreetName_2);

		JLabel lblHouseNumber_2 = new JLabel("House Number");
		lblHouseNumber_2.setBounds(260, 105, 75, 14);
		panelSearch.add(lblHouseNumber_2);

		JLabel lblCity_2 = new JLabel("City:");
		lblCity_2.setBounds(260, 130, 46, 14);
		panelSearch.add(lblCity_2);

		final JLabel lblSog = new JLabel("SOG");
		lblSog.setBounds(260, 178, 46, 14);
		panelSearch.add(lblSog);

		textFieldSName = new JTextField();
		textFieldSName.setBounds(333, 8, 86, 20);
		panelSearch.add(textFieldSName);
		textFieldSName.setColumns(10);

		textFieldSStreetN = new JTextField();
		textFieldSStreetN.setBounds(333, 77, 86, 20);
		panelSearch.add(textFieldSStreetN);
		textFieldSStreetN.setColumns(10);

		textFieldSHNum = new JTextField();
		textFieldSHNum.setBounds(333, 102, 86, 20);
		panelSearch.add(textFieldSHNum);
		textFieldSHNum.setColumns(10);

		textFieldSCity = new JTextField();
		textFieldSCity.setBounds(333, 130, 86, 20);
		panelSearch.add(textFieldSCity);
		textFieldSCity.setColumns(10);

		textFieldSOG = new JTextField();
		textFieldSOG.setBounds(333, 175, 86, 20);
		panelSearch.add(textFieldSOG);
		textFieldSOG.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				i = 0;
				if (chckbxEmployee.isSelected() && (!chckbxStudent.isSelected())) {
					searchlist = search(employeelist, textField.getText());
					Employee e = (Employee) searchlist.get(i);
					textFieldSName.setText(e.getName());
					comboBox.setSelectedItem(e.getAge());
					textFieldSStreetN.setText(e.getAddress().getCity());
					textFieldSHNum.setText(e.getAddress().getHouseNumber());
					textFieldSCity.setText(e.getAddress().getStreetName());
					lblSog.setText("Salary:");
					textFieldSOG.setText(String.valueOf(e.getSalary()));
					i++;
				} else if (!chckbxEmployee.isSelected() && (chckbxStudent.isSelected())) {
					searchlist = search(studentlist, textField.getText());
					Student s = (Student) searchlist.get(i);
					textFieldSName.setText(s.getName());
					comboBox.setSelectedItem(s.getAge());
					textFieldSStreetN.setText(s.getAddress().getCity());
					textFieldSHNum.setText(s.getAddress().getHouseNumber());
					textFieldSCity.setText(s.getAddress().getStreetName());
					lblSog.setText("Grade:");
					textFieldSOG.setText(String.valueOf(s.getGrade()));

					i++;
				} else {
					searchlist = search(employeelist, studentlist, textField.getText());
					Person person = searchlist.get(i);
					if ((person instanceof Employee)) {
						Employee e = (Employee) person;
						textFieldSName.setText(e.getName());
						comboBox.setSelectedItem(e.getAge());
						textFieldSStreetN.setText(e.getAddress().getCity());
						textFieldSHNum.setText(e.getAddress().getHouseNumber());
						textFieldSCity.setText(e.getAddress().getStreetName());
						lblSog.setText("Salary:");
						textFieldSOG.setText(String.valueOf(e.getSalary()));
						i++;
					} else {
						Student s = (Student) person;
						textFieldSName.setText(s.getName());
						comboBox.setSelectedItem(s.getAge());
						textFieldSStreetN.setText(s.getAddress().getCity());
						textFieldSHNum.setText(s.getAddress().getHouseNumber());
						textFieldSCity.setText(s.getAddress().getStreetName());
						lblSog.setText("Grade:");
						textFieldSOG.setText(String.valueOf(s.getGrade()));
						i++;
					}
				}

			}
		});
		btnSearch.setBounds(63, 94, 89, 23);
		panelSearch.add(btnSearch);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (i == searchlist.size())
					i = 0;
				if (i == 0)
					i = searchlist.size() - 1;
				Person person = searchlist.get(i);
				if ((person instanceof Employee)) {
					Employee e = (Employee) person;
					textFieldSName.setText(e.getName());
					comboBox.setSelectedItem(e.getAge());
					textFieldSStreetN.setText(e.getAddress().getCity());
					textFieldSHNum.setText(e.getAddress().getHouseNumber());
					textFieldSCity.setText(e.getAddress().getStreetName());
					lblSog.setText("Salary:");
					textFieldSOG.setText(String.valueOf(e.getSalary()));
					i++;
				} else {
					Student s = (Student) person;
					textFieldSName.setText(s.getName());
					comboBox.setSelectedItem(s.getAge());
					textFieldSStreetN.setText(s.getAddress().getCity());
					textFieldSHNum.setText(s.getAddress().getHouseNumber());
					textFieldSCity.setText(s.getAddress().getStreetName());
					lblSog.setText("Grade:");
					textFieldSOG.setText(String.valueOf(s.getGrade()));
					i++;
				}

			}
		});
		btnNext.setBounds(119, 159, 89, 23);
		panelSearch.add(btnNext);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 159, 89, 23);
		panelSearch.add(btnSave);

		JPanel panelReport = new JPanel();
		tabbedPane.addTab("Report", null, panelReport, null);
		panelReport.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 429, 233);
		panelReport.add(tabbedPane_1);

		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Salary", null, panel, null);
		panel.setLayout(null);

		textFieldER = new JTextField();
		textFieldER.setBounds(31, 63, 156, 23);
		panel.add(textFieldER);
		textFieldER.setColumns(10);

		JButton btnER = new JButton("Report");
		btnER.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldER.getText() == null)
					JOptionPane.showMessageDialog(null, "Please, Enter salary number");
				else {
					File salaryR = new File("SalaryReport.txt");
					ArrayList<Person> EReport = new ArrayList<>();
					for (int j = 0; j < employeelist.size(); j++)
						if (((Employee) employeelist.get(j)).getSalary() > (Integer.parseInt(textFieldER.getText())))
							EReport.add(employeelist.get(j));
					try {
						om.enable(SerializationFeature.INDENT_OUTPUT);
						om.writeValue(salaryR, EReport);
					} catch (IOException e) {
						e.printStackTrace();
					}
					textFieldER.setText(null);
				}
			}
		});
		btnER.setBounds(232, 63, 89, 23);
		panel.add(btnER);

		JLabel lblEnterSalaryNumber = new JLabel("Enter Salary Number:");
		lblEnterSalaryNumber.setBounds(31, 38, 135, 14);
		panel.add(lblEnterSalaryNumber);

		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("Grade", null, panel_1, null);
		panel_1.setLayout(null);

		textFieldSR = new JTextField();
		textFieldSR.setBounds(31, 63, 156, 23);
		panel_1.add(textFieldSR);
		textFieldSR.setColumns(10);

		JButton btnSR = new JButton("Report");
		btnSR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldSR.getText() == null)
					JOptionPane.showMessageDialog(null, "Please, Enter grade number");
				else {
					File gradeR = new File("GradeReport.txt");
					ArrayList<Person> GReport = new ArrayList<>();
					for (int j = 0; j < studentlist.size(); j++)
						if (((Student) studentlist.get(j)).getGrade() > (Integer.parseInt(textFieldSR.getText())))
							GReport.add(studentlist.get(j));
					try {
						om.enable(SerializationFeature.INDENT_OUTPUT);
						om.writeValue(gradeR, GReport);
					} catch (IOException e) {
						e.printStackTrace();
					}
					textFieldER.setText(null);
				}
			}
		});
		btnSR.setBounds(232, 63, 89, 23);
		panel_1.add(btnSR);

		JLabel lblEnterGradeNumber = new JLabel("Enter Grade Number:");
		lblEnterGradeNumber.setBounds(31, 36, 128, 14);
		panel_1.add(lblEnterGradeNumber);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				try {

					om.enable(SerializationFeature.INDENT_OUTPUT);
					om.writeValue(employeetxt, employeelist);

					om.enable(SerializationFeature.INDENT_OUTPUT);
					om.writeValue(studenttxt, studentlist);

					om.enable(SerializationFeature.INDENT_OUTPUT);
					om.writeValue(admintxt, admins);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}

	// generate numbers for ComboBox
	private void age(List<Integer> agelist) {
		for (int i = 1; i <= 100; ++i) {
			agelist.add(i);
		}

	}

	public static ArrayList search(ArrayList<Person> students, ArrayList<Person> employees, String name) {
		ArrayList<Person> person = new ArrayList();
		for (int i = 0; i < students.size(); i++) {
			if ((students.get(i)).getName().contains(name)) {
				person.add(students.get(i));
			}
		}

		for (int i = 0; i < employees.size(); i++) {
			if ((employees.get(i)).getName().contains(name)) {
				person.add(employees.get(i));
			}
		}

		return person;
	}

	public static ArrayList search(ArrayList<Person> personSlist, String name) {
		ArrayList<Person> person = new ArrayList<>();
		for (int i = 0; i < personSlist.size(); i++) {
			if ((personSlist.get(i)).getName().contains(name)) {
				person.add(personSlist.get(i));
			}
		}

		return person;
	}
}
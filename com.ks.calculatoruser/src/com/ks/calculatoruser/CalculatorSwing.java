package com.ks.calculatoruser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ks.converter.Converter;
import com.ks.converter.WordToNumberConv;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class CalculatorSwing extends JFrame {

	private JPanel contentPane;
	private JTextField result;
	private JTextField fn;
	private JTextField sn;
	private JButton btnkar;
	private JButton btnarp;
	private JButton btnBl;
	private JLabel lblfn;
	private JLabel lblsn;
	private JLabel lblSonu;
	private JButton btn;
	private CalculatorService clService;
	/**
	 * Launch the application.
	 */
	public static void main(Converter service) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorSwing frame = new CalculatorSwing(service);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public CalculatorSwing(Converter service) throws Exception {
		setForeground(Color.WHITE);

		clService = new CalculatorServiceImp();
		//change locale language to english
		//Locale.setDefault(Locale.US);
		ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle",  Locale.getDefault());
		System.out.println(Locale.getDefault().toString());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 285);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		result = new JTextField();
		result.setFont(new Font("Times New Roman", Font.BOLD, 16));
		result.setBackground(Color.LIGHT_GRAY);
		result.setBounds(127, 119, 424, 31);
		contentPane.add(result);
		result.setColumns(10);

		fn = new JTextField();
		fn.setBackground(Color.LIGHT_GRAY);
		fn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		result.setBackground(Color.LIGHT_GRAY);
		fn.setBounds(127, 35, 424, 31);
		contentPane.add(fn);
		fn.setColumns(10);

		sn = new JTextField();
		sn.setBackground(Color.GRAY);
		sn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		sn.setBackground(Color.LIGHT_GRAY);
		sn.setBounds(127, 77, 424, 31);
		contentPane.add(sn);
		sn.setColumns(10);


		JLabel lblError = new JLabel(); //$NON-NLS-1$

		btn = new JButton();
		btnkar = new JButton();
		btnarp = new JButton();
		btnBl = new JButton();


		lblfn = new JLabel();
		lblsn = new JLabel();
		lblSonu = new JLabel();

		
		
		btn.setText(bundle.getString("sum"));
		btnkar.setText(bundle.getString("substract"));
		btnarp.setText(bundle.getString("multiply"));
		btnBl.setText(bundle.getString("division"));

		
		lblfn.setText(bundle.getString("fn"));
		lblsn.setText(bundle.getString("sn"));
		lblSonu.setText(bundle.getString("result"));

		
		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					lblError.setText("");

					int num1 = service.convertToNum(fn.getText());
					int num2 = service.convertToNum(sn.getText());
					if(num1 == -1 || num2 == -1) {
						lblError.setText(bundle.getString("error"));
					} else {
						int sum = clService.add(num1, num2);
						
						String str = service.convertToWord(sum);
						result.setText(str);
					}


				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblError.setText(bundle.getString("error"));
				}
			}
		});
		btn.setBounds(127, 173, 101, 31);
		contentPane.add(btn);

		btnkar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblError.setText("");
					String str;
					int min = 0;

					
					int num1 = service.convertToNum(fn.getText());
					int num2 = service.convertToNum(sn.getText());
					
					if(num1 == -1 || num2 == -1) {
						lblError.setText(bundle.getString("error"));
					} else {
						min = clService.minus(num1, num2);
						
						if(min >= 0) {
						    str = service.convertToWord(min);						
						} else {
							min *= -1;
							str = bundle.getString("minus")+ " " + service.convertToWord(min);
						}
						
						result.setText(str);
					}			
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblError.setText(bundle.getString("error"));
				}
			}
		});
		btnkar.setBounds(238, 173, 101, 31);
		contentPane.add(btnkar);

		btnarp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblError.setText("");
					int num1 = service.convertToNum(fn.getText());
					int num2 = service.convertToNum(sn.getText());

					if(num1 == -1 || num2 == -1) {
						lblError.setText(bundle.getString("error"));
					} else {
						int mul = clService.multiply(num1, num2);
						
						String str = service.convertToWord(mul);
						result.setText(str);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblError.setText(bundle.getString("error"));

				}

			}
		});
		btnarp.setBounds(345, 173, 101, 31);
		contentPane.add(btnarp);

		btnBl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblError.setText("");
					String str;
					int div = 0;
					int num1 = service.convertToNum(fn.getText());
					int num2 = service.convertToNum(sn.getText());
					
					if((num1 == -1 || num2 == -1)) {
						lblError.setText(bundle.getString("error"));
					} else {
						div = clService.division(num1, num2);
						
						str = service.convertToWord(div);
						result.setText(str);
					}
					
					if (num1 < num2) {
						str = "zero";
					} else {
						str = service.convertToWord(div);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblError.setText(bundle.getString("error"));

				}

			}
		});
		btnBl.setBounds(456, 173, 95, 31);
		contentPane.add(btnBl);

		
		lblfn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblfn.setBounds(10, 37, 101, 24);
		contentPane.add(lblfn);

		lblsn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblsn.setBounds(10, 79, 101, 24);
		contentPane.add(lblsn);

		lblSonu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSonu.setBounds(10, 121, 101, 24);
		contentPane.add(lblSonu);
		
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblError.setBounds(127, 215, 424, 18);
		contentPane.add(lblError);

	}
}

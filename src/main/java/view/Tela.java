package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import model.dao.ClienteDAO;
import model.dao.TelefoneDAO;
import model.vo.Cliente;
import model.vo.Telefone;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

public class Tela {

	private JFrame frame;
	private JTextField textcpf;
	private JTextField textnome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela window = new Tela();
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
	public Tela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 99, 71));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("ENTER");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome,cpf;
				nome=textnome.getText();
				cpf=textcpf.getText();
				Cliente cliente = new Cliente();
				cliente.setCpf(cpf);
				cliente.setNome(nome);
				ClienteDAO clienteDAO = new ClienteDAO();
				clienteDAO.inserirCliente(cliente);
			}
		});
		frame.getContentPane().setLayout(new MigLayout("", "[43px][178px][65px]", "[14px][20px][14px][20px][23px]"));
		
		JLabel txtNome = new JLabel("NOME");
		txtNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(txtNome, "cell 0 0,alignx left,aligny center");
		
		textnome = new JTextField();
		textnome.setBackground(new Color(192, 192, 192));
		textnome.setColumns(10);
		frame.getContentPane().add(textnome, "cell 0 1 3 1,growx,aligny center");
		
		JLabel txtCpf = new JLabel("CPF");
		txtCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(txtCpf, "cell 0 2,alignx left,aligny center");
		
		textcpf = new JTextField();
		textcpf.setBackground(new Color(192, 192, 192));
		textcpf.setColumns(10);
		frame.getContentPane().add(textcpf, "cell 0 3 3 1,growx,aligny center");
		frame.getContentPane().add(btnNewButton, "cell 2 4,alignx right,aligny center");
	}
}

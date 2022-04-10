package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.dao.ClienteDAO;
import model.vo.Cliente;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class TelaCadastroUsuario {

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
					TelaCadastroUsuario window = new TelaCadastroUsuario();
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
	public TelaCadastroUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setOpacity(0.5f);
		frame.getContentPane().setBackground(new Color(32, 178, 170));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel txtNome = new JLabel("Nome ");
		txtNome.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		
		textnome = new JTextField();
		textnome.setBackground(new Color(255, 255, 255));
		textnome.setColumns(10);
		
		JLabel txtCpf = new JLabel("CPF");
		txtCpf.setFont(new Font("Yu Gothic Light", Font.PLAIN, 14));
		
		textcpf = new JTextField();
		textcpf.setBackground(new Color(255, 255, 255));
		textcpf.setColumns(10);
		
		JButton btnInseir = new JButton("INSERIR");
		btnInseir.setForeground(new Color(220, 20, 60));
		btnInseir.setBackground(new Color(255, 255, 255));
		btnInseir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome,cpf;
				nome=textnome.getText();
				cpf=textcpf.getText();
				Cliente cliente = new Cliente();
				cliente.setCpf(cpf);
				cliente.setNome(nome);
				ClienteDAO clienteDAO = new ClienteDAO();
				clienteDAO.inserirCliente(cliente);
				
				limpar();
			}

			private void limpar() {
				
			}
		});
		
		JComboBox cbEndereco = new JComboBox();
		
		JLabel lblNewLabel = new JLabel("Endere\u00E7o");
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(185)
					.addComponent(lblNewLabel)
					.addContainerGap(193, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(196)
					.addComponent(txtCpf)
					.addContainerGap(212, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textnome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
						.addComponent(textcpf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
						.addComponent(cbEndereco, 0, 339, Short.MAX_VALUE))
					.addContainerGap(49, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(149)
					.addComponent(btnInseir, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(160, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(187)
					.addComponent(txtNome)
					.addContainerGap(206, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtNome)
					.addGap(7)
					.addComponent(textnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(txtCpf)
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(textcpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(btnInseir, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}

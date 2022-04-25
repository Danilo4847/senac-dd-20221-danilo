package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.dao.ClienteDAO;
import model.vo.Cliente;
import model.vo.Endereco;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.ClienteController;
import controller.EnderecoController;

import javax.swing.JComboBox;

public class TelaCadastroUsuario extends JFrame {
	Endereco endereco= new Endereco();
	EnderecoController enderecoController= new EnderecoController();
	Cliente cliente = new Cliente();
	ClienteController clienteController= new ClienteController();
	private JTextField textcpf;
	private JTextField textnome;
	private JComboBox cbEndereco;
	/**
	 * Launch the application.
	 */



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroUsuario frame = new TelaCadastroUsuario(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param clienteSelecionado 
	 */
	public TelaCadastroUsuario(Cliente clienteSelecionado) {
		
		getContentPane().setBackground(new Color(255, 204, 0));
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel txtNome = new JLabel("Nome ");
		txtNome.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));

		textnome = new JTextField();
		textnome.setBackground(new Color(255, 255, 255));
		textnome.setColumns(10);

		JLabel txtCpf = new JLabel("CPF");
		txtCpf.setFont(new Font("Yu Gothic Light", Font.PLAIN, 14));

		textcpf = new JTextField();
		textcpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente=clienteController.consultarClienteCpf(textcpf.getText());
				preencherCliente();

			}
		});
		
		
		textcpf.setBackground(new Color(255, 255, 255));
		textcpf.setColumns(10);

		JButton btnInseir = new JButton("INSERIR");
		btnInseir.setForeground(new Color(0, 0, 0));
		btnInseir.setBackground(new Color(51, 102, 153));
		btnInseir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClienteDAO clienteDAO = new ClienteDAO();
				
				
				if(cliente.getId() > 0) {
					clienteDAO.atualizarCliente(cliente);
				}else {
					clienteDAO.inserirCliente(cliente);
					String nome,cpf;
					nome=textnome.getText();
					cpf=textcpf.getText();
					cliente.setCpf(cpf);
					cliente.setNome(nome);
				}
				limpar();

			}


		});
		ArrayList<Endereco>endereco=enderecoController.consultarTodos();
		cbEndereco = new JComboBox(endereco.toArray());
		cbEndereco.setSelectedIndex(-1);
		
		JLabel lblNewLabel = new JLabel("Endere\u00E7o");
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));

		JButton btnAlterar = new JButton("ALTERAR");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAlterar.setForeground(new Color(0, 0, 0));
		btnAlterar.setBackground(new Color(51, 102, 153));
		
		if(clienteSelecionado != null) {
			this.cliente = clienteSelecionado;
			this.preencherCliente();
		}
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCpf)
								.addComponent(txtNome)
								.addComponent(lblNewLabel))
						.addGap(14)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(cbEndereco, 0, 335, Short.MAX_VALUE)
								.addComponent(textcpf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addComponent(textnome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addGap(33)
										.addComponent(btnInseir, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnAlterar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(34)))
						.addGap(19))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addContainerGap(28, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCpf)
								.addComponent(textcpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNome)
								.addComponent(textnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(23)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(cbEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInseir, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGap(39))
				);
		getContentPane().setLayout(groupLayout);




	}
	protected void preencherCliente() {
		if(cliente!=null) {
			this.textcpf.setText(cliente.getCpf());
			this.textnome.setText(cliente.getNome());
			this.cbEndereco.getModel().setSelectedItem(cliente.getEndereco());
		}else {
			limpar();
		}
	}

	private void limpar() {
		this.textcpf.setText("");
		this.textnome.setText("");
		this.cbEndereco.setSelectedItem(null);
	}
}

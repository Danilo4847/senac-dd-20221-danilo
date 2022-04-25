package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.table.DefaultTableModel;

import controller.ClienteController;
import model.dao.ClienteDAO;
import model.vo.Cliente;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Toolkit;

public class TelaListagemClientes extends JFrame{
	ClienteController clienteController = new ClienteController();
	List<Cliente> clientes=new ArrayList<Cliente>();
	Cliente clienteSelecionado = new Cliente();
	private JTable tabela;
	private JButton btnNovoCliente;
	private JButton btnExcluir;
	private AbstractButton btnEditar;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemClientes frame = new TelaListagemClientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaListagemClientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\danilo.santos1\\Pictures\\transferir.jfif"));
		setBounds(100, 100, 800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(218, 165, 32));

		btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroUsuario(null).setVisible(true);
				setVisible(false);

			}
		});

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clienteController.excluirCliente(clienteSelecionado.getId());
				//ClienteDAO d= new ClienteDAO();
				//d.removerCliente(a);
				tabela.setVisible(true);

			}
		});

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroUsuario(clienteSelecionado).setVisible(true);
				setVisible(false);
			}
		});

		tabela = new JTable();
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int indiceSelecionado = tabela.getSelectedRow();
				clienteSelecionado = clientes.get(indiceSelecionado - 1);

				//tabela.setVisible(false);
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnNovoCliente.setEnabled(false);

			}
		});

		clientes=clienteController.clientes();
		atualizarTabela();
		JLabel lblNewLabel = new JLabel("LISTA DE CLIENTE");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientes=clienteController.clientes();
				atualizarTabela();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(tabela, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(20)
										.addComponent(btnNovoCliente, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
										.addGap(43)
										.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
										.addGap(43)
										.addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)))
						.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(339, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addGap(331))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(349, Short.MAX_VALUE)
						.addComponent(btnNewButton)
						.addGap(346))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(14)
						.addComponent(lblNewLabel)
						.addGap(18)
						.addComponent(tabela, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnNewButton)
						.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnNovoCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
										.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		getContentPane().setLayout(groupLayout);


	}

	private void atualizarTabela() {
		tabela.setModel(new DefaultTableModel(new String[][] {{"nome","cpf","id"},},new String[] {"nome","cpf","id"}));
		DefaultTableModel modelo=(DefaultTableModel)tabela.getModel();
		for(Cliente c: clientes) {
			String[] linha = new String[] {
					c.getNome(),
					c.getCpf(),
					c.getId()+"",

			};
			modelo.addRow(linha);
		}

	}
}

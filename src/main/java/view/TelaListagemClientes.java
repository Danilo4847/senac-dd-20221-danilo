package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
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
import model.vo.Cliente;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaListagemClientes extends JFrame{
	ClienteController clienteController = new ClienteController();
	List<Cliente> clientes=new ArrayList<Cliente>();
	Cliente c = new Cliente();
	private JFrame frame;
	private JTable tabela;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public TelaListagemClientes() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 204, 0));

		JButton btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroUsuario().setVisible(true);
				setVisible(false);
				
			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

			}
		});

		JButton btnEditar = new JButton("Editar");

		tabela = new JTable();
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		clientes=clienteController.clientes();
		tabela.setModel(new DefaultTableModel(new Object[][] {{"nome","cpf","id"},},new Object[] {"nome","cpf","id"}));
		DefaultTableModel modelo=(DefaultTableModel)tabela.getModel();
		for(Cliente c: clientes) {
			Object[] linha = new Object[] {
					c.getNome(),
					c.getCpf(),
					c.getId()+"",


			};
			modelo.addRow(linha);
		}


		JLabel lblNewLabel = new JLabel("Lista de Clientes");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(tabela, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnNovoCliente)
														.addGap(68)
														.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
														.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(164)
										.addComponent(lblNewLabel)))
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addGap(14)
						.addComponent(lblNewLabel)
						.addGap(18)
						.addComponent(tabela, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnEditar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnExcluir, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNovoCliente, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
						.addContainerGap())
				);
		getContentPane().setLayout(groupLayout);


	}
}

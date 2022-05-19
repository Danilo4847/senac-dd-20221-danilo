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
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class TelaListagemClientes extends JFrame{
	ClienteController clienteController = new ClienteController();
	List<Cliente> clientes=new ArrayList<Cliente>();
	Cliente clienteSelecionado = new Cliente();
	private JTable tabela;
	private JButton btnNovoCliente;
	private JButton btnExcluir; 
	private AbstractButton btnEditar;
	private JTextField textConsultaNome;
	private JTextField textConsultaCPF;
	private JTextField textConsultaID;


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
		setBounds(100, 100, 800, 422);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(218, 165, 32));

		btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PainelCadastroUsuario(null).setVisible(true);
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
				new PainelCadastroUsuario(clienteSelecionado).setVisible(true);
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
		JLabel lblNewLabel = new JLabel("LISTA DE CONSULTA DE CLIENTES");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				atualizarTabela();
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		
		textConsultaNome = new JTextField();
		textConsultaNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		
		JLabel lblNewLabel_2 = new JLabel("cpf");
		
		textConsultaCPF = new JTextField();
		textConsultaCPF.setColumns(10);
		
		textConsultaID = new JTextField();
		textConsultaID.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("id");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tabela, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNovoCliente, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(130)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textConsultaNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(87)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textConsultaCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGap(73)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addComponent(textConsultaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(236))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textConsultaCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textConsultaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textConsultaNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(68)
					.addComponent(tabela, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
						.addComponent(btnNovoCliente, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);


	}

	private void atualizarTabela() {
		clientes=clienteController.clientes();
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

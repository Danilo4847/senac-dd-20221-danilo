package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.ClienteController;
import controller.LinhaTelefonicaController;
import controller.TelefoneController;
import model.dao.LinhaTelefonicaDAO;
import model.dao.TelefoneDAO;
import model.vo.Cliente;
import model.vo.Telefone;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class LinhaTelefonica {
	ClienteController clienteController = new ClienteController();
	TelefoneController telefoneController = new TelefoneController();
	LinhaTelefonicaController linhaTelefonicaController = new LinhaTelefonicaController();
	LocalDateTime agora = LocalDateTime.now();


	private JFrame frame;
	private JLabel lblNewLabel_2;
	private JComboBox cbTelefone;
	private JComboBox cbUsuario;
	private ArrayList<Cliente> clientes;
	private JButton btnAssociar;
	private JButton btnLiberarLinha;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LinhaTelefonica window = new LinhaTelefonica();
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
	public LinhaTelefonica() {
		initialize();
	}

	/**
	 * Initialize the contents of th00, 149, 237*/
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\danil\\Downloads\\no-mundo-todo.png"));
		frame.getContentPane().setBackground(new Color(51, 153, 204));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		clientes=clienteController.clientes();
		ArrayList<Telefone> telefones=telefoneController.consultar();

		cbUsuario = new JComboBox(clientes.toArray());
		cbUsuario.setSelectedIndex(-1);

		cbTelefone = new JComboBox(telefones.toArray());
		cbTelefone.setSelectedIndex(-1);

		cbTelefone.addActionListener(new ActionListener() {
			
			/*validação de campos*/
			
			public void actionPerformed(ActionEvent e) {
				
				Telefone telefone = (Telefone)cbTelefone.getSelectedItem();
				
				if(telefone!=null) {
					if(telefone.isAtivo()) {
						
						btnAssociar.setEnabled(false);
						btnLiberarLinha.setEnabled(true);
						Cliente dono = linhaTelefonicaController.obterUltimoClienteLinha(telefone.getId());
						cbUsuario.getModel().setSelectedItem(dono);
				

					}else {
						//Buscar dono da linha atual

						btnAssociar.setEnabled(true);
						btnLiberarLinha.setEnabled(false);
						Cliente dono =linhaTelefonicaController.obterUltimoClienteLinha(telefone.getId());
				
						cbUsuario.getModel().setSelectedItem(dono);
	
					
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel lblNewLabel_1 = new JLabel("Telefone");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		btnAssociar = new JButton("Associar");
		
		/*Inserção de nova linha telefonica*/
		
		btnAssociar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente();
				cliente=(Cliente)cbUsuario.getModel().getSelectedItem();
				int id_cliente=cliente.getId();
				Telefone tel =(Telefone)cbTelefone.getModel().getSelectedItem();


				model.vo.LinhaTelefonica linha = new model.vo.LinhaTelefonica(tel,id_cliente,agora,null);
				LinhaTelefonicaDAO dao = new LinhaTelefonicaDAO();
				dao.inserir(linha);

			}
		});
		
		
		
		
		btnAssociar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLiberarLinha = new JButton("Liberar");
		
		/*Liberar Cliente*/
		
		
		btnLiberarLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Telefone tel =(Telefone)cbTelefone.getModel().getSelectedItem();
	
				model.vo.LinhaTelefonica linha = new model.vo.LinhaTelefonica();
				TelefoneDAO telDAO = new TelefoneDAO();
				int telefone_id=tel.getId();
				telDAO.consultar(telefone_id);
				
			}
		});
		btnLiberarLinha.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		lblNewLabel_2 = new JLabel("Linha Telefonica");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBackground(new Color(255, 0, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_1)
												.addComponent(lblNewLabel))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(cbTelefone, 0, 326, Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnAssociar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
														.addComponent(btnLiberarLinha, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
												.addComponent(cbUsuario, 0, 326, Short.MAX_VALUE))
										.addGap(45))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
										.addGap(142))))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(cbTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(lblNewLabel_1))
						.addGap(41)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addGap(45)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAssociar, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLiberarLinha, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGap(34))
				);
		frame.getContentPane().setLayout(groupLayout);
	}
}
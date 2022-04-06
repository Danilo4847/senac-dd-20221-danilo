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
import model.vo.Cliente;
import model.vo.Telefone;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class LinhaTelefonica {
	ClienteController clienteController = new ClienteController();
	TelefoneController telefoneController = new TelefoneController();
	
	private JFrame frame;

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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 153, 153));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ArrayList<Cliente> clientes=clienteController.clientes();
		ArrayList<Telefone> telefones=telefoneController.consultar();
		
		JComboBox CBusuario = new JComboBox(clientes.toArray());
		
		JComboBox CBtelefone = new JComboBox(telefones.toArray());
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Telefone");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		JButton btnAssociar = new JButton("Associar");
		btnAssociar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnAssociar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Cliente cliente = new Cliente();
				
				
			
				
				
			}
		});
		
		JButton btnLiberarLinha = new JButton("Liberar");
		btnLiberarLinha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Linha Telefonica");
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
								.addComponent(CBtelefone, 0, 326, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAssociar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
									.addComponent(btnLiberarLinha, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
								.addComponent(CBusuario, 0, 326, Short.MAX_VALUE))
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
							.addComponent(CBtelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(lblNewLabel_1))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(CBusuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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

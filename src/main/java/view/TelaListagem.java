package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.table.DefaultTableModel;

import controller.TelefoneController;
import model.vo.Telefone;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaListagem {
	private JFrame frame;
	private JTable tabela;
	TelefoneController telefoneController = new TelefoneController();
	List<Telefone>telefones= new ArrayList<Telefone>();
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagem window = new TelaListagem();
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
	public TelaListagem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 0));
		frame.getContentPane().setForeground(new Color(95, 158, 160));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabela = new JTable();
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"New column"
			}
		));
	
		
		JLabel lblNewLabel = new JLabel("Listagem");
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		
		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telefones=telefoneController.consultar();
				atualizar();
				
			}

			private void atualizar() {
			tabela.setModel(new DefaultTableModel(new String[][] {{"ddd","numero","tipo","ativo"},},new String[]{"DD", "Número", "Tipo","Ativo" }));
			DefaultTableModel modelo=(DefaultTableModel)tabela.getModel();
			for(Telefone t: telefones) {
				String[] novaLinha=new String[] {
					t.getDdd(), 
					t.getNumero(),
					t.getTipo() == Telefone.TIPO_FIXO ? "Fixo" : "Móvel",
					t.isAtivo() ? "Sim" : "Não"
				};
				modelo.addRow(novaLinha);
			}
				
			}
		});
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addComponent(tabela, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
					.addGap(48))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(169)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(186, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(171)
					.addComponent(btnAtualizar)
					.addContainerGap(174, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(tabela, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAtualizar)
					.addGap(16))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}

package view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;


import model.dao.EnderecoDAO;

import model.vo.Endereco;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JList;
import java.awt.Font;

public class TelaEndereco extends JFrame {

	private JPanel contentPane;
	private JTextField textRUA;
	private JTextField textCEP;
	private JTextField textNUMERO;
	private JTextField textUF;
	private JTextField textCIDADE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEndereco frame = new TelaEndereco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEndereco() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 140, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(127dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(129dlu;default):grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_3 = new JLabel("UF");
		lblNewLabel_3.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		contentPane.add(lblNewLabel_3, "2, 2");
		
		JLabel lblRUA = new JLabel("RUA");
		lblRUA.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		contentPane.add(lblRUA, "4, 2");
		
		textUF = new JTextField();
		contentPane.add(textUF, "2, 4, left, default");
		textUF.setColumns(10);
		
		textRUA = new JTextField();
		contentPane.add(textRUA, "4, 4, left, default");
		textRUA.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("CIDADE");
		lblNewLabel_4.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		contentPane.add(lblNewLabel_4, "2, 6");
		
		JLabel lblNewLabel_2 = new JLabel("NUMERO");
		lblNewLabel_2.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		contentPane.add(lblNewLabel_2, "4, 6");
		
		textCIDADE = new JTextField();
		contentPane.add(textCIDADE, "2, 8, left, default");
		textCIDADE.setColumns(10);
		
		textNUMERO = new JTextField();
		contentPane.add(textNUMERO, "4, 8, left, default");
		textNUMERO.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CEP");
		lblNewLabel_1.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		contentPane.add(lblNewLabel_1, "4, 10");
		
		textCEP = new JTextField();
		contentPane.add(textCEP, "4, 12, left, default");
		textCEP.setColumns(10);
		
		JButton btnNewButton = new JButton("ENTER");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("RomanT", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			
			
			/*não comente*/
			
			public void actionPerformed(ActionEvent arg0) {
				String numero,uf,cidade,cep,rua;
				Endereco endereco = new Endereco();
				numero=textNUMERO.getText();
				uf=textUF.getText();
				cidade=textCIDADE.getText();
				cep=textCEP.getText();
				rua=textRUA.getText();
				
				endereco.setCep(cep);
				endereco.setCidade(cidade);
				endereco.setNumero(numero);
				endereco.setRua(rua);
				endereco.setUf(uf);
				
				EnderecoDAO enderecoDAO = new EnderecoDAO();
			
				enderecoDAO.inserir(endereco);
			}
		});
		contentPane.add(btnNewButton, "4, 20");
	}

}

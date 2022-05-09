package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class TelaExecutavel {

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExecutavel window = new TelaExecutavel();
					window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public TelaExecutavel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		
		
		JMenu mnCliente = new JMenu("Clientes");
		mnCliente.setIcon(new ImageIcon(TelaExecutavel.class.getResource("/icon/Elegantthemes-Beautiful-Flat-Contacts.ico")));
		menuBar.add(mnCliente);
		
		JMenuItem mntmInserir = new JMenuItem("Inserir");
		mntmInserir.setIcon(new ImageIcon(TelaExecutavel.class.getResource("/icon/Elegantthemes-Beautiful-Flat-Contacts.ico")));
		mntmInserir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PainelCadastroUsuario painelUsuario= new PainelCadastroUsuario(null);
				frame.setContentPane(painelUsuario);
				frame.revalidate();
			}
		});
		mnCliente.add(mntmInserir);
	}

}

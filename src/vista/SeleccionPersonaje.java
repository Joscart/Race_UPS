package vista;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.Jugador;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionPersonaje extends JPanel implements Dimensionable{

	private static final long serialVersionUID = 1L;
	public JTextField txt_apuesta1;
	public JTextField txt_apuesta2;
	public JLabel lbl_imagen1;
	public JButton btn_jugador1;
	public JButton btn_jugador2;
	public JButton btn_jugador3;
	public JButton btn_jugador4;
	public JButton btn_jugador5;
	public JButton btn_jugador6;
	public JLabel lbl_Resultado;
	public JLabel lbl_imagen2;
	public JButton btn_Empezar;
	public JLabel lbl_apuestas;
	public JLabel lbl_Seleccione;
	public Jugador seleccionado = new Jugador();
	public JButton btn_apostar;
	public JLabel lbl_resultado;

	/**
	 * Create the panel.
	 */
	public SeleccionPersonaje() {
		setBackground(Color.gray);
		setSize(ANCHO, ALTO);
		setLayout(null);
		
		Jugador jugador = new Jugador ();
		
		jugador.setPersonaje(Jugador.Personajes.Jose);
		btn_jugador1 = new JButton("JOSE");
		btn_jugador1.setBounds(70, 469, 89, 23);
		btn_jugador1.setOpaque(false);
		btn_jugador1.setContentAreaFilled(false);
		btn_jugador1.setBorderPainted(true);
		add(btn_jugador1);
		
		jugador.setPersonaje(Jugador.Personajes.Andre);
		btn_jugador2 = new JButton("ANDRE");
		btn_jugador2.setBounds(216, 469, 89, 23);
		btn_jugador2.setOpaque(false);
		btn_jugador2.setContentAreaFilled(false);
		btn_jugador2.setBorderPainted(true);
		add(btn_jugador2);
		
		jugador.setPersonaje(Jugador.Personajes.Dylan);
		btn_jugador3 = new JButton("DYLAN");
		btn_jugador3.setBounds(363, 469, 89, 23);
		btn_jugador3.setOpaque(false);
		btn_jugador3.setContentAreaFilled(false);
		btn_jugador3.setBorderPainted(true);
		add(btn_jugador3);
		
		jugador.setPersonaje(Jugador.Personajes.Jose);
		btn_jugador4 = new JButton("JOSE");
		btn_jugador4.setBounds(598, 469, 89, 23);
		btn_jugador4.setOpaque(false);
		btn_jugador4.setContentAreaFilled(false);
		btn_jugador4.setBorderPainted(true);
		add(btn_jugador4);
		
		jugador.setPersonaje(Jugador.Personajes.Andre);
		btn_jugador5 = new JButton("ANDRE");
		btn_jugador5.setBounds(762, 469, 89, 23);
		btn_jugador5.setOpaque(false);
		btn_jugador5.setContentAreaFilled(false);
		btn_jugador5.setBorderPainted(true);
		add(btn_jugador5);
		
		jugador.setPersonaje(Jugador.Personajes.Dylan);
		btn_jugador6 = new JButton("DYLAN");
		btn_jugador6.setBounds(930, 469, 89, 23);
		btn_jugador6.setOpaque(false);
		btn_jugador6.setContentAreaFilled(false);
		btn_jugador6.setBorderPainted(true);
		add(btn_jugador6);
		
		lbl_imagen1 = new JLabel(new ImageIcon(seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		lbl_imagen1.setBounds(50, 120, 300, 300);
		add(lbl_imagen1);
		
		lbl_imagen2 = new JLabel(new ImageIcon(seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		lbl_imagen2.setBounds(745, 120, 300, 300);
		add(lbl_imagen2);
		
		txt_apuesta1 = new JTextField();
		txt_apuesta1.setBounds(378, 240, 96, 20);
		add(txt_apuesta1);
		txt_apuesta1.setColumns(10);
		
		txt_apuesta2 = new JTextField();
		txt_apuesta2.setBounds(578, 240, 96, 20);
		add(txt_apuesta2);
		txt_apuesta2.setColumns(10);
		
		lbl_Resultado = new JLabel("Apuesta de:");
		lbl_Resultado.setForeground(Color.WHITE);
		lbl_Resultado.setBackground(Color.BLACK);
		lbl_Resultado.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_Resultado.setBounds(448, 367, 109, 30);
		add(lbl_Resultado);
		
		btn_Empezar = new JButton("Emperzar");
		btn_Empezar.setBounds(504, 532, 89, 23);
		add(btn_Empezar);
		
		lbl_apuestas = new JLabel("Apuestas:");
		lbl_apuestas.setForeground(Color.WHITE);
		lbl_apuestas.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl_apuestas.setBounds(465, 161, 120, 30);
		add(lbl_apuestas);
		
		lbl_Seleccione = new JLabel("Seleccione su personaje");
		lbl_Seleccione.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl_Seleccione.setBounds(404, 56, 283, 31);
		add(lbl_Seleccione);
		
		btn_apostar = new JButton("Calcular");
		btn_apostar.setBounds(483, 297, 89, 23);
		add(btn_apostar);
		
		lbl_resultado = new JLabel("");
		lbl_resultado.setForeground(Color.WHITE);
		lbl_resultado.setBackground(Color.BLACK);
		lbl_resultado.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl_resultado.setBounds(554, 368, 120, 29);
		add(lbl_resultado);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(new ImageIcon("src/multimedia/FondoSeleccion.jpg").getImage(), 0, 0, ANCHO, ALTO, this);
		
		super.paintComponents(g);
		
	}
}

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import modelo.Jugador;
import vista.SeleccionPersonaje;

public class logic_SeleccionPersonaje implements ActionListener{
	
	SeleccionPersonaje lb;

	public logic_SeleccionPersonaje(SeleccionPersonaje lb) {
		// TODO Auto-generated constructor stub
		this.lb = lb;
		listener();
		lb.btn_Empezar.setEnabled(false);
	}
	
	private void listener() {
		lb.btn_Empezar.addActionListener(this);
		lb.btn_jugador1.addActionListener(this);
		lb.btn_jugador2.addActionListener(this);
		lb.btn_jugador3.addActionListener(this);
		lb.btn_jugador4.addActionListener(this);
		lb.btn_jugador5.addActionListener(this);
		lb.btn_jugador6.addActionListener(this);
		lb.btn_apostar.addActionListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Boton presionado");
		if (e.getSource() == lb.btn_Empezar) {
			System.out.println("Empiece el juego");
		}else if (e.getSource() == lb.btn_jugador1) {
			lb.seleccionado.setPersonaje(Jugador.Personajes.Jose);
			lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
			lb.btn_Empezar.setEnabled(true);
		}else if (e.getSource()== lb.btn_jugador2) {
			lb.seleccionado.setPersonaje(Jugador.Personajes.Andre);
			lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
			lb.btn_Empezar.setEnabled(true);
		}else if (e.getSource()== lb.btn_jugador3) {
			lb.seleccionado.setPersonaje(Jugador.Personajes.Dylan);
			lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
			lb.btn_Empezar.setEnabled(true);
		}else if (e.getSource() == lb.btn_jugador4) {
			lb.seleccionado.setPersonaje(Jugador.Personajes.Jose);
			lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
			lb.btn_Empezar.setEnabled(true);
		}else if (e.getSource() == lb.btn_jugador5) {
			lb.seleccionado.setPersonaje(Jugador.Personajes.Andre);
			lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
			lb.btn_Empezar.setEnabled(true);
		}else if (e.getSource() == lb.btn_jugador6) {
			lb.seleccionado.setPersonaje(Jugador.Personajes.Andre);
			lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
			lb.btn_Empezar.setEnabled(true);
		}else if (e.getSource()==lb.btn_apostar) {
			calcularApuesta();
		}
		
	}
	private void calcularApuesta() {

			double monto1 = Double.valueOf(lb.txt_apuesta1.getSelectedItem().toString());
            double monto2 = Double.valueOf(lb.txt_apuesta2.getSelectedItem().toString());
            double resultado = monto1 + monto2;
            lb.lbl_Resultado.setText("Apuesta de: " + resultado);

	}

}

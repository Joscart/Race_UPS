package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import modelo.Jugador;
import vista.SeleccionPersonaje;

public class logic_SeleccionPersonaje implements ActionListener{

	private SeleccionPersonaje lb;
	private boolean seleccionado = false;

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
	
	public boolean isSeleccionado() {
		return seleccionado;
	}
	
	public void reset() {
		seleccionado = false;
		lb.seleccionado.setPersonaje(Jugador.Personajes.Default);
		lb.seleccionado2.setPersonaje(Jugador.Personajes.Default);
		lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		lb.lbl_imagen2.setIcon(new ImageIcon(lb.seleccionado2.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		lb.btn_Empezar.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Boton presionado");
		if (e.getSource() == lb.btn_Empezar) {
			seleccionado = true;
		}else if (e.getSource() == lb.btn_jugador1) {
			lb.seleccionado2.setPersonaje(Jugador.Personajes.Jose);
			lb.lbl_imagen2.setIcon(new ImageIcon(lb.seleccionado2.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		}else if (e.getSource()== lb.btn_jugador2) {
			lb.seleccionado2.setPersonaje(Jugador.Personajes.Andre);
			lb.lbl_imagen2.setIcon(new ImageIcon(lb.seleccionado2.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		}else if (e.getSource()== lb.btn_jugador3) {
			lb.seleccionado2.setPersonaje(Jugador.Personajes.Dylan);
			lb.lbl_imagen2.setIcon(new ImageIcon(lb.seleccionado2.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		}else if (e.getSource() == lb.btn_jugador4) {
			lb.seleccionado.setPersonaje(Jugador.Personajes.Jose);
			lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		}else if (e.getSource() == lb.btn_jugador5) {
			lb.seleccionado.setPersonaje(Jugador.Personajes.Andre);
			lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		}else if (e.getSource() == lb.btn_jugador6) {
			lb.seleccionado.setPersonaje(Jugador.Personajes.Dylan);
			lb.lbl_imagen1.setIcon(new ImageIcon(lb.seleccionado.getImagenPersonaje().getScaledInstance(300, 300, 0)));
		}else if (e.getSource()==lb.btn_apostar) {
			calcularApuesta();
		}
		
		if (lb.seleccionado.getPersonaje() != Jugador.Personajes.Default && lb.seleccionado2.getPersonaje() != Jugador.Personajes.Default) {
			lb.btn_Empezar.setEnabled(true);
		}

	}
	private void calcularApuesta() {
		int resultado = (Integer) (lb.txt_apuesta1.getSelectedItem()) + (Integer) (lb.txt_apuesta2.getSelectedItem());
		lb.lbl_Resultado.setText("Apuesta de: " + resultado);
	}


}

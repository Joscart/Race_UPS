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
			lb.seleccionado2.setPersonaje(Jugador.Personajes.Jose);
			lb.lbl_imagen2.setIcon(new ImageIcon(lb.seleccionado2.getImagenPersonaje().getScaledInstance(300, 300, 0)));
			lb.btn_Empezar.setEnabled(true);
		}else if (e.getSource() == lb.btn_jugador5) {
			lb.seleccionado2.setPersonaje(Jugador.Personajes.Andre);
			lb.lbl_imagen2.setIcon(new ImageIcon(lb.seleccionado2.getImagenPersonaje().getScaledInstance(300, 300, 0)));
			lb.btn_Empezar.setEnabled(true);
		}else if (e.getSource() == lb.btn_jugador6) {
			lb.seleccionado2.setPersonaje(Jugador.Personajes.Andre);
			lb.lbl_imagen2.setIcon(new ImageIcon(lb.seleccionado2.getImagenPersonaje().getScaledInstance(300, 300, 0)));
			lb.btn_Empezar.setEnabled(true);
		}else if (e.getSource()==lb.btn_apostar) {
			calcularApuesta();
		}
		
	}
	private void calcularApuesta() {
	    try {
	        // Obtener valores seleccionados
	        String apuesta1 = lb.txt_apuesta1.getSelectedItem().toString();
	        String apuesta2 = lb.txt_apuesta2.getSelectedItem().toString();
	        
	        // Validar que los valores no sean nulos ni vacíos
	        if (apuesta1 != null && !apuesta1.isEmpty() && apuesta2 != null && !apuesta2.isEmpty()) {
	            try {
	                // Intentar convertir los valores a double
	                double monto1 = Double.parseDouble(apuesta1);
	                double monto2 = Double.parseDouble(apuesta2);
	                double resultado = monto1 + monto2;
	                
	                // Mostrar el resultado
	                lb.lbl_Resultado.setText("Apuesta de: " + resultado);
	            } catch (NumberFormatException e) {
	                // Capturar excepciones si los valores no son numéricos
	                lb.lbl_Resultado.setText("Los valores deben ser numéricos");
	            }
	        } else {
	            // Mensaje de error si los valores son nulos o vacíos
	            lb.lbl_Resultado.setText("Seleccione valores válidos en ambos comboboxes");
	        }
	    } catch (Exception e) {
	        // Captura cualquier otra excepción inesperada
	        lb.lbl_Resultado.setText("Ocurrió un error: " + e.getMessage());
	    }
	}


}

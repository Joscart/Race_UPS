package modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

import libreriaV2.generic;
import modelo.Jugador.Personajes;

public class Jugador {
	
	generic<Image,String> generica = new generic<>();
	private Personajes selec_personaje;
	
	public enum Personajes{
		Dylan, Jose, Andre, Default
	}
	
	public Jugador() {
		this.setPersonaje(Personajes.Default);		
	}
	
	public Jugador(Personajes personaje) {
		this.setPersonaje(personaje);
	}
	
	private String imagenJugador(Personajes personaje) {
		return switch (personaje) {
		case Dylan -> "src/multimedia/tulio.png";
		case Jose -> "src/multimedia/patana.png";
		case Andre -> "src/multimedia/juan_carlos.png";
		default -> "src/multimedia/default_player.png";
        };
	}
	
	public Image getImagenPersonaje() {
		return generica.getAtributo1();
	}

	public String getNombre() {
		return generica.getAtributo3();
	}
	
	public Personajes getPersonaje() {
		return selec_personaje;
	}
	
	public void setPersonaje(Personajes personaje) {
		this.selec_personaje = personaje;
		generica.setAtributo1(new ImageIcon(imagenJugador(selec_personaje)).getImage());
		generica.setAtributo3(selec_personaje.toString());
	}

}

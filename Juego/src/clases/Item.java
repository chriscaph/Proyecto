package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private String indiceImagen;
	private static int velocidad;
	private boolean capturado;
	public Item(int x, int y, int ancho, int alto, String indiceImagen, int velocidad) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.indiceImagen = indiceImagen;
		this.velocidad=velocidad;
	}
	
	public static int getVelocidad() {
		return velocidad;
	}

	public static void setVelocidad(int velocidad) {
		Item.velocidad = velocidad;
	}

	public void mover(){
		if (Juego.derecha)
			this.x-=velocidad;
	}
	
	public void pintar(GraphicsContext graficos) {
		if (!capturado)
			graficos.drawImage(Juego.imagenes.get(indiceImagen), this.x, this.y);
		//graficos.fillRect(this.x, this.y, 18, 18);
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, 18, 18);
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
}

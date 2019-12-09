package clases;

import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class ItemAnimado {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private String indiceImagen;
	private boolean capturado;
	private static int velocidad;
	public static String animacionActual;
	private HashMap<String, Animacion> animaciones;
	
	//Coordenadas para el fragmento de la imagen a pintar
		private int xImagen;
		private int yImagen;
		private int anchoImagen;
		private int altoImagen;	
		
	public ItemAnimado(int x, int y, int ancho, int alto, String indiceImagen, int velocidad, String animacionActual) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.indiceImagen = indiceImagen;
		this.capturado = capturado;
		this.velocidad = velocidad;
		this.animacionActual=animacionActual;
		inicializarAnimaciones();
	}

	public void actualizarAnimacion(double t) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionActual).calcularFrame(t);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();
		//System.out.println("Xim: "+this.xImagen+"/"+"Yim: "+this.yImagen+"/"+"Ancho: "+this.anchoImagen+"/"+"Alto: "+this.altoImagen+"/");
	}
	
	public void inicializarAnimaciones() {
			animaciones = new HashMap<String, Animacion>();
			Rectangle coordenadasMover[]= {
					new Rectangle(11, 12, 37, 37),
					new Rectangle(50, 12, 37, 37),
					new Rectangle(92, 12, 37, 37),
					new Rectangle(7, 56, 37, 37),
					new Rectangle(43, 56, 37, 37),
					new Rectangle(94, 56, 37, 37)
			};
			
			Animacion animacionMover = new Animacion("mover",coordenadasMover,0.1);
			animaciones.put("mover",animacionMover);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public String getIndiceImagen() {
		return indiceImagen;
	}

	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public static void setVelocidad(int _velocidad) {
		velocidad = _velocidad;
	}

	public void mover(){
		if (Juego.derecha)
			this.x-=velocidad;
	}
	
	public void pintar(GraphicsContext graficos) {
		if (!capturado)
			graficos.drawImage(
					Juego.imagenes.get(this.indiceImagen), 
					this.xImagen, this.yImagen, 
					this.anchoImagen, this.altoImagen,
					this.x, this.y,
					this.anchoImagen, this.altoImagen
			);
		//graficos.fillRect(this.x, this.y, 18, 18);
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
}

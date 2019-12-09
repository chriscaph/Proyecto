package clases;

import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class JugadorAnimado {
	public static int x;
	public static int y;
	private String indiceImagen;
	private int velocidad;
	private HashMap<String, Animacion> animaciones;
	public static String animacionActual;
	private int puntuacion = 0;
	private int lifes=0;
	
	//Coordenadas para el fragmento de la imagen a pintar
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	
	public JugadorAnimado(int x, int y, String indiceImagen, int velocidad, String animacionActual) {
		super();
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		this.velocidad = velocidad;
		this.animacionActual = animacionActual;
		inicializarAnimaciones();
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
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
	public String getIndiceImagen() {
		return indiceImagen;
	}
	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}
	
	//Obtener las coordenas del fragmento de la imagen a pintar
	public void actualizarAnimacion(double t) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionActual).calcularFrame(t);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();
	}
	
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(
				Juego.imagenes.get(this.indiceImagen), 
				this.xImagen, this.yImagen, 
				this.anchoImagen, this.altoImagen,
				this.x, this.y,
				this.anchoImagen, this.altoImagen
		);
		//graficos.fillRect(this.x, this.y, this.anchoImagen, this.altoImagen);
		graficos.setStroke(Color.BLACK);
		graficos.setFont(new Font("Comic Sans MS",16));
		graficos.strokeText("Puntuacion: " + puntuacion, 4, 15);
		graficos.strokeText("Vidas: " + lifes, 935, 15);
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	public void inicializarAnimaciones() {
			animaciones = new HashMap<String, Animacion>();
			Rectangle coordenadasCorrer[]= {
					new Rectangle(0, 3, 48, 65),
					new Rectangle(49, 3, 48, 65),
					new Rectangle(102, 3, 45, 66),
					new Rectangle(152, 3, 44, 66),
					new Rectangle(204, 4, 36, 66),
					new Rectangle(253, 4, 36, 66),
					new Rectangle(306, 4, 32, 66),
					new Rectangle(3, 89, 46, 69),
					new Rectangle(52, 89, 47, 69),
					new Rectangle(102, 90, 45, 64),
					new Rectangle(152, 90, 44, 64),
					new Rectangle(203, 91, 43, 63),
					new Rectangle(252, 91, 43, 63),
					new Rectangle(306, 91, 36, 66),
					new Rectangle(3, 177, 43, 66),
					new Rectangle(52, 177, 44, 67),
					new Rectangle(102, 178, 45, 63),
					new Rectangle(152, 177, 44, 64),
					new Rectangle(203, 178, 43, 63),
					new Rectangle(252, 178, 43, 63),
					new Rectangle(305, 178, 37, 65),
					new Rectangle(152, 90, 44, 64)
			};
			
			Animacion animacionCorrer = new Animacion("correr",coordenadasCorrer,0.05);
			animaciones.put("correr",animacionCorrer);
			
			Rectangle coordenadasDescanso[]= {
					new Rectangle(3, 309, 38, 68),
					new Rectangle(54, 309, 37, 68),
					new Rectangle(111, 309, 31, 68),
					new Rectangle(162, 309, 30, 68),
					new Rectangle(214, 309, 28, 69),
					new Rectangle(265, 309, 28, 69),
					new Rectangle(214, 309, 28, 69),
					new Rectangle(162, 309, 30, 68),
					new Rectangle(111, 309, 31, 68),
					new Rectangle(54, 309, 37, 68)
					
			};
			
			Animacion animacionDescanso = new Animacion("descanso",coordenadasDescanso,0.1);
			animaciones.put("descanso",animacionDescanso);
	}
	
	public void verificarColisiones(ItemAnimado itemAnimado) {
		if (this.obtenerRectangulo().intersects(itemAnimado.obtenerRectangulo().getBoundsInLocal())) {
				//System.out.println("Estan colisionando");
				if (!itemAnimado.isCapturado())
					this.puntuacion++;
				itemAnimado.setCapturado(true);				
		}
	}
	
	public void verificarColisiones2(Item item) {
		if (this.obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
				//System.out.println("Estan colisionando");
				if (!item.isCapturado())
					this.lifes++;
				item.setCapturado(true);				
		}
	}
	
	public void verificarColisiones3(EnemigoAnimado1 item) {
		if (this.obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
			if (!item.isCapturado())
				this.lifes--;
			item.setCapturado(true);				
		}
	}
	
	public void verificarColisiones4(EnemigoAnimado2 item) {
		if (this.obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
			if (!item.isCapturado())
				this.lifes--;
			item.setCapturado(true);				
		}
	}
	
	public void verificarColisiones5(EnemigoAnimado3 item) {
		if (this.obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
			if (!item.isCapturado())
				this.lifes--;
			item.setCapturado(true);				
		}
	}
}



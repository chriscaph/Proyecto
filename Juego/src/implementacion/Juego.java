package implementacion;

import java.util.ArrayList;
import java.util.HashMap;

import clases.Animacion;
import clases.EnemigoAnimado1;
import clases.EnemigoAnimado2;
import clases.EnemigoAnimado3;
import clases.Item;
import clases.ItemAnimado;
import clases.JugadorAnimado;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Juego extends Application{
	private Scene escena;
	private Group root;
	private Canvas canvas;
	private GraphicsContext graficos;
	private int puntuacion = 0;
	//public static int invertir = 1;
	//private Jugador jugador;
	private JugadorAnimado jugadorAnimado;
	public static boolean derecha=false;
	//public static boolean izquierda=false;
	public static boolean arriba=false;
	public static boolean abajo=false;
	public static HashMap<String, Image> imagenes; //Shift+Ctrl+O
	int randomx,randomy,randomyy;
	int randomxV,randomyV,randomyyV;
	
	private ArrayList<Tile> tiles;
	private ArrayList<ItemAnimado> coins;
	private ArrayList<Item> vidas;
	private ArrayList<EnemigoAnimado1> enemigos1;
	private ArrayList<EnemigoAnimado2> enemigos2;
	private ArrayList<EnemigoAnimado3> enemigos3;
	
	private int[][] mapa = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0},
			{6,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0},
			{6,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0},
			{6,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5}
	};
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage ventana) throws Exception {
		inicializarComponentes();
		graficos = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		ventana.setScene(escena);
		ventana.setTitle("Zombie World");
		gestionarEventos();
		ventana.show();
		cicloJuego();
	}
	
	public void inicializarComponentes() {
		jugadorAnimado = new JugadorAnimado(5,72,"personaje",4, "descanso");
		root = new Group();
		escena = new Scene(root,1000,630);
		canvas  = new Canvas(1000,630);
		imagenes = new HashMap<String,Image>();
		coins = new ArrayList<ItemAnimado>();
		int m=((int)(Math.random()*16+25));
		for (int z=0;z<m;z++) {
			randomyy=(int)(Math.random()*3+1);
			randomx=(int)(Math.random()*15000+1000);
			if (randomyy==1)
				randomy=70;
			if(randomyy==2)
				randomy=280;
			if(randomyy==3)
				randomy=490;
			coins.add(new ItemAnimado(randomx,randomy,0,0,"coin",4,"mover"));
		}
		
		vidas=new ArrayList<Item>();
		for (int z=0;z<3;z++) {
			randomyyV=(int)(Math.random()*3+1);
			randomxV=(int)(Math.random()*8000+3000);
			if (randomyyV==1)
				randomyV=70;
			if (randomyyV==2)
				randomyV=280;
			if (randomyyV==3)
				randomyV=490;
			vidas.add(new Item(randomxV,randomyV,0,0,"vida",4));
		}
		
		enemigos1=new ArrayList<EnemigoAnimado1>();
		for (int z=0;z<30;z++) {
			randomyyV=(int)(Math.random()*3+1);
			randomxV=(int)(Math.random()*26000+3000);
			if (randomyyV==1)
				randomyV=74;
			if (randomyyV==2)
				randomyV=284;
			if (randomyyV==3)
				randomyV=494;
			enemigos1.add(new EnemigoAnimado1(randomxV,randomyV,0,0,"enemigo1",4,"mover"));
		}
		
		enemigos2=new ArrayList<EnemigoAnimado2>();
		for (int z=0;z<30;z++) {
			randomyyV=(int)(Math.random()*3+1);
			randomxV=(int)(Math.random()*26000+3000);
			if (randomyyV==1)
				randomyV=74;
			if (randomyyV==2)
				randomyV=284;
			if (randomyyV==3)
				randomyV=494;
			enemigos2.add(new EnemigoAnimado2(randomxV,randomyV,0,0,"enemigo2",4,"mover"));
		}
		
		enemigos3=new ArrayList<EnemigoAnimado3>();
		for (int z=0;z<30;z++) {
			randomyyV=(int)(Math.random()*3+1);
			randomxV=(int)(Math.random()*26000+3000);
			if (randomyyV==1)
				randomyV=75;
			if (randomyyV==2)
				randomyV=285;
			if (randomyyV==3)
				randomyV=495;
			enemigos3.add(new EnemigoAnimado3(randomxV,randomyV,0,0,"enemigo3",4,"mover"));
		}
		
		cargarImagenes();
		cargarTiles();
	}
	
	public void cargarImagenes() {
		imagenes.put("tilemap", new Image("tilemap.png"));
		imagenes.put("personaje", new Image("personaje.png"));
		imagenes.put("coin", new Image("coin.png"));
		imagenes.put("fondo", new Image("fondo.png"));
		imagenes.put("vida", new Image("live.png"));
		imagenes.put("enemigo1", new Image("enemigo1.png"));
		imagenes.put("enemigo2", new Image("enemigo2.png"));
		imagenes.put("enemigo3", new Image("enemigo3.png"));
	}
	
	public void pintar() {
		graficos.setFill(Color.WHITE);
		graficos.fillRect(0, 0, 1000, 630);
		graficos.setFill(Color.BLACK);
		graficos.drawImage(imagenes.get("fondo"), 0, 0);
		//jugador.pintar(graficos);
		jugadorAnimado.pintar(graficos);
		///Pintar tiles
		for (int i=0;i<tiles.size();i++)
			tiles.get(i).pintar(graficos);
		
		for (int i=0;i<coins.size();i++)
			coins.get(i).pintar(graficos);
		
		for (int i=0;i<vidas.size();i++)
			vidas.get(i).pintar(graficos);
		
		for (int i=0;i<enemigos1.size();i++)
			enemigos1.get(i).pintar(graficos);
		
		for (int i=0;i<enemigos2.size();i++)
			enemigos2.get(i).pintar(graficos);
		
		for (int i=0;i<enemigos3.size();i++)
			enemigos3.get(i).pintar(graficos);
		
	}
	
	public void cargarTiles() {
		tiles = new ArrayList<Tile>();
		for(int i=0; i<mapa.length; i++) {
			for(int j=0; j<mapa[i].length; j++) {
				if (mapa[i][j]!=0)
				tiles.add(new Tile(mapa[i][j], j*70, i*70, "tilemap",4));
			}
		}
	}
	
	public void gestionarEventos() {
		//Evento cuando se presiona una tecla
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evento) {
					//Aqui tengo que poner el codigo para identificar cuando se presiono una tecla
					switch (evento.getCode().toString()) {
						case "RIGHT": //derecha
							derecha=true;
							//invertir=1;
							JugadorAnimado.animacionActual="correr";
							break;
						case "UP":
							arriba=true;
							if(JugadorAnimado.y!=72)
								JugadorAnimado.y-=210;
							break;
						case "DOWN":
							abajo=true;
							if(JugadorAnimado.y!=492)
								JugadorAnimado.y+=210;								
							break;
						case "SPACE":
							ItemAnimado.setVelocidad(8);
							Tile.setVelocidad(8);
							EnemigoAnimado1.setVelocidad(8);
							EnemigoAnimado2.setVelocidad(8);
							EnemigoAnimado3.setVelocidad(8);
							Animacion.setDuracion(0.05);
							Item.setVelocidad(8);
							break;
					}
			}			
		});
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				//Aqui tengo que poner el codigo para identificar cuando se soltó una tecla
				switch (evento.getCode().toString()) {
				case "RIGHT": //derecha
					derecha=false;
					JugadorAnimado.animacionActual="descanso";
					Animacion.setDuracion(0.1);
					break;
				/*case "LEFT": //derecha
					izquierda=false;
					JugadorAnimado.animacionActual="descanso";
				break;
				*/
				case "UP":
					arriba=false;
					break;
				case "DOWN":
					abajo=false;
					break;
				case "SPACE":
					ItemAnimado.setVelocidad(4);
					Tile.setVelocidad(4);
					Animacion.setDuracion(0.1);
					Item.setVelocidad(4);
					EnemigoAnimado1.setVelocidad(4);
					EnemigoAnimado2.setVelocidad(4);
					EnemigoAnimado3.setVelocidad(4);
					//jugador.setIndiceImagen("goku");
					break;
			}
				
			}
			
		});
		
	}
	
	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {
			//Esta rutina simula un ciclo de 60FPS
			@Override
			public void handle(long tiempoActualNanoSegundos) {
				double t = (tiempoActualNanoSegundos - tiempoInicial) / 1000000000.0;
				pintar();
				actualizar(t);
				
			}
			
		};
		animationTimer.start(); //Inicia el ciclo
	}
	
	public void actualizar(double t) {
		for(int z=0;z<coins.size();z++) {
			coins.get(z).mover();
			coins.get(z).actualizarAnimacion(t);
		}
		for(int z=0;z<enemigos1.size();z++) {
			enemigos1.get(z).mover();
			enemigos1.get(z).actualizarAnimacion(t);
		}
		for(int z=0;z<enemigos2.size();z++) {
			enemigos2.get(z).mover();
			enemigos2.get(z).actualizarAnimacion(t);
		}
		for(int z=0;z<enemigos3.size();z++) {
			enemigos3.get(z).mover();
			enemigos3.get(z).actualizarAnimacion(t);
		}
		for (int z=0;z<3;z++) 
			vidas.get(z).mover();
		
		for (int i=0;i<coins.size();i++)
			jugadorAnimado.verificarColisiones(coins.get(i));
		for (int i=0;i<vidas.size();i++)
			jugadorAnimado.verificarColisiones2(vidas.get(i));
		for (int i=0;i<enemigos1.size();i++)
			jugadorAnimado.verificarColisiones3(enemigos1.get(i));
		for (int i=0;i<enemigos2.size();i++)
			jugadorAnimado.verificarColisiones4(enemigos2.get(i));
		for (int i=0;i<enemigos3.size();i++)
			jugadorAnimado.verificarColisiones5(enemigos3.get(i));
		jugadorAnimado.actualizarAnimacion(t);
		//jugadorAnimado.verificarColisiones(coin1);
		//jugadorAnimado.verificarColisiones(coin2);
	}

}

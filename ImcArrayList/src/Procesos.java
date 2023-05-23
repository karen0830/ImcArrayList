import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Procesos {
	DecimalFormat df = new DecimalFormat("#.00");
	ArrayList<String> nombres =  new ArrayList<String>();
	ArrayList<String> tipo = new ArrayList<String>();
	ArrayList<Double> peso = new ArrayList<Double>();
	ArrayList<Double> talla = new ArrayList<Double>();
	
	public void inicio() {
		cicloM();
	}
	
	public void cicloM() {
		String menu = "", personaM = "1. Ingresar datos\n";
		int x = 0;
		do {
			menu = "IMC\n";
			menu+= personaM;
			menu+= "2. Imprimir listas\n";
			menu += "3. Consulta individual\n";
			menu += "4. Eliminar personan\n";
			menu += "5. Actualizar\n";
			menu += "6. Limpiar lista\n";
			menu+= "7. Salir\n";
			x = Integer.parseInt(JOptionPane.showInputDialog(menu));
			validarO(x);
			if(x < 1 || x > 7) {
				JOptionPane.showMessageDialog(null,  "Ingrese opcion valida","Error", JOptionPane.ERROR_MESSAGE);
			}
			personaM = "1. Registrar más Personas\n";
		}while(x != 7);
	}
	
	public void validarO(int opcion) {
		switch (opcion) {
		case 1:
			ingresarP();
			break;
		case 2: 
			if(nombres.size() != 0) {
				imprimirListas();
			}else System.out.println("Llene los datos");
			System.out.println("------");
			break;
		case 3: 
			if(nombres.size() != 0) {
				consultaPorNombre();
			}else System.out.println("Llene los datos");
			System.out.println("------");
			break;
		case 4: 
			if(nombres.size() != 0) {
				eliminarPersonas();
			}else System.out.println("Llene los datos");
			System.out.println("------");
			break;
		case 5: 
			if(nombres.size() != 0) {
				actualizarD();
			}else System.out.println("Llene los datos");
			System.out.println("------");
			break;
		case 6: 
			if(nombres.size() != 0) {
				limpiarLista();
			}else System.out.println("Llene los datos");
			System.out.println("------");
			break;
		case 7: 
			System.out.println("ADIOS");
		}
	}

	private void ingresarP() {
		int x = 0;
		double imc = 0;
		int i = nombres.size();
		String nombre = "";
		do {
			nombre = validarNombre(i);
			nombres.add(nombre);
			if(x > 2 && x < 1) {
				JOptionPane.showMessageDialog(null, "Error", "Ingrese dato valido", JOptionPane.ERROR_MESSAGE);
			};
			peso.add(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso de " + nombres.get(i))));
			talla.add(Double.parseDouble(JOptionPane.showInputDialog("Ingrese la estatura en metros de " + nombres.get(i))));
			imc = peso.get(i) / (talla.get(i) * talla.get(i));
			tipo.add(resultadoImc(imc));
			i++;
			x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese: \n1. para continuar \n2. Para salir"));
		}while(x != 2 || x == 1);
	}

	private void imprimirListas() {
		for(int i = 0; i < nombres.size(); i++) {
			System.out.println("Nombre: " + nombres.get(i) + " estatura: " + df.format(talla.get(i)) + " Peso: " + peso.get(i) + " Resultado: " + tipo.get(i));
		}
	}
	
	public String validarNombre(int j) {
		String nombre = "";
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona " + (j+1));
		for(int i = 0; i < nombres.size(); i++) {
			while(nombres.get(i).equalsIgnoreCase(nombre)) {
				JOptionPane.showMessageDialog(null, "Este nombre ya se encuentra registrado", "Error", JOptionPane.INFORMATION_MESSAGE);
				nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona " + (j+1));
				i = 0;
			}
		}
		return nombre;
	}

	private void consultaPorNombre() {
		String menu = "Ingrese opcion correcta para consultar el resultado\n";
		menu += 0 + ". Busqueda personalizada\n";
		int x = 0;
		int i;
		for(i = 0; i < nombres.size(); i++) {
			menu += (i+1) + " " + nombres.get(i) + "\n";
		}
		
		menu+= (i+1) + ". Salir";
		do {
			x = Integer.parseInt(JOptionPane.showInputDialog(menu));
			if(x >= 1 && x < i+1) {
				System.out.println("Nombre: " + nombres.get(x-1) + " estatura: " + df.format(talla.get(x-1)) + " Peso: " + peso.get(x-1) + " Resultado: " + tipo.get(x-1));
			}else if(x < 0 || x > i+1){
				JOptionPane.showMessageDialog(null,  "Ingrese opcion valida","Error", JOptionPane.ERROR_MESSAGE);
			}else if(x == 0) {
				busquedasPers();
			}
		}while(x != i+1);
	}

	
	
	private void busquedasPers() {
		String nombre = "";
		int x = 0;
			nombre = JOptionPane.showInputDialog("1. Ingrese el nombre a buscar");
			if(nombres.contains(nombre)) {
				x = nombres.indexOf(nombre);
				System.out.println("Nombre: " + nombres.get(x) + " estatura: " + df.format(talla.get(x)) + " Peso: " + peso.get(x) + " Resultado: " + tipo.get(x));
			}else System.out.println(nombre + " No fue registrado");		
	}

	private void eliminarPersonas() {
		String nombre = "";
		int opcion = 1;
		do {
			nombre = JOptionPane.showInputDialog("1. Ingrese el nombre a eliminar");
			System.out.println(nombres.contains(nombre));
			if(nombres.contains(nombre)) {
				for(int i = 0; i < nombres.size(); i++) {
					System.out.println();
					if(nombre.equalsIgnoreCase(nombres.get(i))) {
						nombres.remove(i);
						tipo.remove(i);
						peso.remove(i);
						talla.remove(i);
						System.out.println(nombre + " está eliminado corectamente");
					}
				}
			}else System.out.println(nombre + " No fue registrado");
			 opcion = JOptionPane.showOptionDialog(null, "¿Desea eliminar otro nombre?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Sí", "No"}, "Sí");
		} while (opcion == JOptionPane.YES_OPTION);
		System.out.println(nombres);
	}
	
	
	private void actualizarD() {
		String nombre = "";
		int x = 0, opcion = 1, m = 0;
		double imc;
		do {
			nombre = JOptionPane.showInputDialog("1. Ingrese el nombre de la persona a actualizar");
			if(nombres.contains(nombre)) {
				m = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tipo de actualizacion que \nquiere para " +nombre
				+ "\n1. peso\n2. nombre\n3. Estatura"));
				x = nombres.indexOf(nombre);
				System.out.println(x);
				if(m == 1) {
					peso.set(x, Double.parseDouble(JOptionPane.showInputDialog("Ingrese nuevo peso para " + nombre)));
				}else if(m == 2) {
					nombres.set(x, JOptionPane.showInputDialog("Ingrese nuevo nombre para " + nombre));
				}else if(m == 3) {
					talla.set(x, Double.parseDouble(JOptionPane.showInputDialog("Ingrese nueva estatura para " + nombre)));
				}
				imc = peso.get(x) / (talla.get(x) * talla.get(x));
				tipo.set(x, resultadoImc(imc));
			}else System.out.println(nombre + " No fue registrado");
			opcion = Integer.parseInt(JOptionPane.showInputDialog("1. Ingresar otro nombre para eliminar\n2. Salir"));
			while(opcion <= 0 || opcion > 2) {
				JOptionPane.showMessageDialog(null, "Ingrese opción valida", "!¡", JOptionPane.INFORMATION_MESSAGE);
				opcion = Integer.parseInt(JOptionPane.showInputDialog("1. Ingresar otro nombre\n2. Salir"));
			}
		}while(opcion != 2);
	}
	
	public String resultadoImc(double imc) {
		String resultado = ""; 
		if(imc < 18) {
			resultado = "Anorexia";
		}else if(imc >= 18 && imc< 20) {
			resultado = "Delgadez";
		}else if(imc >= 20 && imc < 27) {
			resultado = "Normalidad";
		}else if(imc >= 27 &&imc < 30) {
			resultado ="Obesidad (grado 1)";
		}else if(imc >= 30 && imc < 35) {
			resultado = "Obesidad (grado 2)";
		}else if(imc >= 35 && imc < 40) {
			resultado = "Obesidad (grado 3)";
		}else if(imc >= 40) {
			resultado = "Obesidad m�rbida";
		}
		return resultado;
	}

	private void limpiarLista() {
		// TODO Auto-generated method stub
		nombres.clear();
		talla.clear();
		peso.clear();
		tipo.clear();
		System.out.println("la lista está vacia");
	}
}
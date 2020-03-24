package org.pis2.serie;

import java.util.ArrayList;

public class Salida {

	private int N;
	private ArrayList<Integer> numeros;
	private ArrayList<Integer> incrementos;

	Entrada entrada = null;
	private ArrayList<Integer> ciclo;

	public Salida(Entrada entrada1){
		this.entrada = entrada1;

		N = entrada.getN();
		numeros = entrada.getNumeros();
		incrementos = entrada.getIncrementos();
		ciclo = new ArrayList<Integer>();
	}

	public ArrayList<Integer> calculaCiclo(){

		boolean repeat = mismoIncremento(incrementos);

		//Comprueba si el incremento es siempre el mismo
		if(repeat){
			ciclo.add(incrementos.get(0));
			return ciclo;
		}
		//cual es el ciclo?
		else{

			//dos valores
			ArrayList<Integer> c1 = new ArrayList<Integer>();
			ArrayList<Integer> c2 = new ArrayList<Integer>();

			int numCiclo = 2;
			int finCiclo = 0;
			boolean match = false;
			boolean cicle = false;

			//mientras no se conozca el ciclo
			while(!cicle){
				/*
				 * Valores del principio a buscar coincidencia
				 * m�s adelante en el array.
				 */
				for(int i = 0; i < numCiclo;i++){
					c1.add(incrementos.get(i));
				}

				/*
				 * Buscamos coincidencia
				 */
				for(int i = numCiclo;i<N-1;i++){//cada X sumados comprueba
					int cont = 0;//contador de ayuda

					// 1 2| 1 2| 1

					for(int j = 0; j<numCiclo;j++){//Comprueba coincidencias
						int incre1 = incrementos.get(i+cont);
						int varC1 = c1.get(cont);
						if(incre1 == varC1){
							match = true;
						}else{
							match = false;
							break;
						}
						cont++;
					}
					/*
					 * Si concuerdan: X Posibilidades
					 * 1. - El ciclo es mayor de numCiclo(ejemplo 2)
					 * 2. -
					 *
					 * Si no concuerdan: X Posibilidades
					 * 1. - Ah� no empieza de nuevo el ciclo
					 * 2. -
					 */
					if(match){
						finCiclo = i;
						for(int k = 0;k < finCiclo;k++){
							ciclo.add(incrementos.get(k));
							cicle = true;
						}
						break;
					}
				}
			}
			return ciclo;
		}
	}
	// 1 1 2 |  1 1 2
	public boolean mismoIncremento(ArrayList<Integer> incrementosMethod){
		boolean repeat = false;
		int helper = 0;
		for(int i = 0;i < incrementos.size();i++){
			if(i > 0){
				if(helper == incrementos.get(i))
					repeat = true;
				else{
					repeat = false;
					break;
				}
			}else{
				helper = incrementos.get(0);
			}
		}
		return repeat;
	}

	public int calculaSalida(){
		int finNum = numeros.get(numeros.size()-1);
		int finIncr = incrementos.get(incrementos.size()-1);
		int nextIncr = 0;
		int nextNum = 0;

		if(ciclo.size() == 1){
			nextNum = finNum + finIncr;
		}else{
			int befIncr = incrementos.get(incrementos.size()-2);


			boolean match = false;

			int ci1 = ciclo.indexOf(finIncr);
			int ci2 = ciclo.lastIndexOf(finIncr);

			if(ci1 == ci2){
				if(ci2 == ciclo.size()-1){
					ci2 = 0;
				}
				nextIncr = ciclo.get(ci2+1);
				nextNum = finNum + nextIncr;
			}else{
				// 1 1 2
				// b2 f1 Si el anterior es el ultimo entonces
				if(befIncr == ciclo.get(ciclo.size()-1)){
					nextIncr = ciclo.get(ci1+1);
					nextNum = finNum + nextIncr;
				}else{
					nextIncr = ciclo.get(befIncr+1);
					nextNum = finNum + nextIncr;
				}

			}
		}


		return nextNum;
	}
}
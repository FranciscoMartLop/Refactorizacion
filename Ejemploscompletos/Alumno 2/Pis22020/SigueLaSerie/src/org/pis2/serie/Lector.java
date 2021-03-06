package org.pis2.serie;

import java.io.*;
import java.util.*;


public class Lector {


	private LinkedList<String> text;
    private ListIterator<String> current;

    private boolean inserting;

    public Lector(){
    	text = new LinkedList<String>();
        current = text.listIterator();
        inserting = false;
    }


    public static ArrayList<Entrada> cargarArchivo(String file) {

        Scanner fileScanner = null;
        //PrintWriter printWriter = null;
        ArrayList<Entrada> listaEntradas = null;
        try {
			fileScanner = new Scanner(new File(file));
			listaEntradas = analizarArchivo(fileScanner);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
        fileScanner.close();
      //  editText(fileScanner, printWriter);
        return listaEntradas;

       // printWriter.close();

    }

    private static  ArrayList<Entrada> analizarArchivo(Scanner fileScanner) {
	    String line = "";
        String result = "";
        boolean inicio = false;
        boolean incluir = false;

        int n = 0;
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        ArrayList<Integer> incrementos = new ArrayList<Integer>();
        ArrayList<Entrada> entradas = new ArrayList<Entrada>();
        while (true) {
            try {
                line = fileScanner.nextLine();
                String[] com = line.split(" ");

              //----------------no hay nada acaba-----------------------------//
                if(Integer.parseInt(com[0]) == 0 & com.length == 1){
                	break;
                }
              //----------------Empieza entrada-----------------------------//
                if(com.length == 1 & !inicio){
                	inicio = true;
                	incluir = false;
                	n = Integer.parseInt(com[0]);
                	numeros = new ArrayList<Integer>();
                	incrementos = new ArrayList<Integer>();
                	continue;
                }
              //----------------numeros de entrada-----------------------------//
                if(com.length > 1 & inicio){
                	int bNumber = -1;
                	for(String str: com){
                		if(bNumber != -1){
                			int incremento = Integer.parseInt(str) - bNumber;
                			incrementos.add(incremento);
                		}
                		numeros.add(Integer.parseInt(str));
                		bNumber = Integer.parseInt(str);
                	}
                	if(numeros.size() == n){
                		incluir = true;
                		inicio = false;
                	}
                }
                /**
                 *
                 * //----------------fin de entrada-----------------------------//
                if(com.length == 1 & inicio){
                	inicio = false;
                }
                 *
                 */

              //-----------------Creo la entrada clase-----------------------------//
                if(!inicio & incluir){
                	Entrada entrada = new Entrada(n, numeros, incrementos);
                	entradas.add(entrada);
                }

             }
             catch (RuntimeException e) {
            	 System.out.println(e.getMessage());
             }
          }
        return entradas;

    }

}

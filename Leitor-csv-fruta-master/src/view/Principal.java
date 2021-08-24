package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;
public class Principal {

	public static void main(String[] args) {
		
		IArquivosController arqCont = new ArquivosController();
		String path = "C:\\";
		String nome = "Cópia de Engenharia de Software - Desafio Victor Zamora Semerano.xlsx";
		// O arquivo deve estar localizado no C:\\ com o nome acima para funcionar
		try {
			arqCont.readDir(path);
			arqCont.readFile(path,nome);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

package Main;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import Controller.FifaController;
import Controller.IFifaController;

public class Principal {

	public static void main(String[] args) {

		IFifaController fifa = new FifaController();
		String caminho = "C:\\Users\\User\\Desktop\\Exercicio\\1\\home\\leandro\\Downloads\\ExercicioSistemaArquivos";
		String nome = "data";
		
		try {

			Stack<String> pilha = fifa.empilhaBrasileiros(caminho, nome);
			List<String> lista = fifa.listaRevelacoes(caminho, nome);
			fifa.desmpilhaBonsBrasileiros(pilha);
			fifa.buscaListaBonsJovens(lista);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

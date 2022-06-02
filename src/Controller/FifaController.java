package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FifaController implements IFifaController{

	public FifaController () {
		super();
	}

	public Stack<String> empilhaBrasileiros(String caminho, String nome) throws IOException {

		Stack<String> pilha = new Stack<String>();
		File arq = new File(caminho, nome+".csv");
		if(arq.exists()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader Buffer = new BufferedReader(leitor);
			String linha = Buffer.readLine();
			linha = Buffer.readLine();
			
			while(linha != null){
				String linha_array[] = linha.split(",");
				if(linha_array[5].contains("Brazil")) {
					pilha.push(linha);
				}
				linha = Buffer.readLine();
			}
			Buffer.close();
			leitor.close();
			fluxo.close();
		}else {
			throw new IOException("Aquivo inválido");
		}
		
		
		return pilha;
	}

	public void desmpilhaBonsBrasileiros(Stack<String> pilha) throws IOException {

		System.out.println("Bons Brasileiros: ");
		while(!pilha.isEmpty()) {
			String bras = pilha.pop();
			String jogador[] = bras.split(",");
			
			int overall = Integer.parseInt(jogador[7]);
			if(overall >= 80) {
				String nome = jogador[2];
				System.out.println("Nome: " + nome + "\tOverall: " + overall);
			}
		}
		
	}


	public List<String> listaRevelacoes(String caminho, String nome) throws IOException {

		List<String> lista = new LinkedList<String>();
		File arq = new File(caminho, nome+ ".csv");
		if(!arq.exists() || !arq.isFile())
			throw new IOException("Arquivo não encontrado!");
		
		FileInputStream stream = new FileInputStream(arq);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);
		String linha = buffer.readLine();
		linha = buffer.readLine();
		
		while(linha != null) {
			String line_array[] = linha.split(",");
			int i = Integer.parseInt(line_array[3]);
			if(i <= 20) {
				lista.add(linha);
			}
			linha = buffer.readLine();
		}
		buffer.close();
		reader.close();
		stream.close();
		return lista;
	}

	public void buscaListaBonsJovens(List<String> lista) throws IOException {
		
		System.out.println("Revelações Jovens: ");
		for(int i = lista.size()-1; i >= 0; i--) {
			
			String jogador[] = (lista.get(i)).split(",");
			int overall = Integer.parseInt(jogador[7]);
			if(overall >= 80) {				
				System.out.println("Jogador: " + jogador[2] + " - Idade: " + jogador[3] + " - Overall: " + overall);
			}
		}	
	}
}

package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ArquivosController implements IArquivosController {

	public ArquivosController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void readDir(String path) throws IOException {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			System.out.println("Diretório valido");
		} else {
			throw new IOException("Diretório inválido");
		}
	}

	@Override
	public void readFile(String path, String nome) throws IOException {
		InputStream convertido = null;
		File arq = new File(path, nome);
		if (arq.exists() && arq.isFile()) {
			InputStream flux = new FileInputStream(arq);
			try {
				convertido = Conversor.convertxlstoCSV(flux);
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			InputStreamReader reader = new InputStreamReader(convertido);
			BufferedReader buffer = new BufferedReader(reader);
			String line = buffer.readLine();
			line = buffer.readLine();
			line = buffer.readLine();
			line = buffer.readLine();
			while (line != null) {

				String[] results = new String[2];
				String[] vet = line.split(",");
				Double n1 = Double.parseDouble(vet[3]);
				Double n2 = Double.parseDouble(vet[4]);
				Double n3 = Double.parseDouble(vet[5]);
				double m = (n1 + n2 + n3) / 30;
				double naf = 10 - m;
				if (Double.parseDouble(vet[2]) > 15) {
					results[0] = "Reprovado por Falta ";
					results[1] = " 0";
				} else if (m >= 7 || m + 0.45 >= 7) {
					results[0] = "Aprovado ";
					results[1] = " 0";
				} else if (m + 0.45 < 5) {
					results[0] = "Reprovado por Nota ";
					results[1] = " 0";
				} else {
					results[0] = "Exame Final";
					results[1] = " " + naf;
				}
				System.out.println(line + results[0] + results[1]);
				line = buffer.readLine();
			}
			buffer.close();
			reader.close();
			flux.close();

		}

		else {
			throw new IOException("Arquivo Inválido");

		}

	}

}

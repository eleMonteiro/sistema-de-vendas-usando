package fronteira;

import java.util.Scanner;

public class Console {

	private Scanner scanner;

	public Console() {
		scanner = new Scanner(System.in);
	}

	public String requisitarDado(String etiqueta) {
		System.out.println("$ " + etiqueta);

		return scanner.nextLine();
	}

}

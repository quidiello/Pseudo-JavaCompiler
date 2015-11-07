package miw;

import java.io.FileReader;
import java.io.IOException;

import miw.ast.tipos.ManejadorErrores;
import miw.generacion.VisitorOffset;
import miw.lexico.Lexico;
import miw.semantico.VisitorSemantico;
import miw.sintactico.Parser;
import miw.visitor.VisitorIdentificacion;

/*
 * Prueba del analizador sintáctico.
 */
public class Main {

	public static void main(String args[]) throws IOException {
	    if (args.length<1) {
	        System.err.println("Necesito el archivo de entrada.");
	        return;
	    }
	        
		FileReader fr=null;
		try {
			fr=new FileReader(args[0]);
		} catch(IOException io) {
			System.err.println("El archivo "+args[0]+" no se ha podido abrir.");
			return;
		}
		
		Lexico lexico = new Lexico(fr);
		Parser parser = new Parser(lexico);
		
		// Parseamos
		parser.run();

		if (! ManejadorErrores.getInstance().existeErrores() && parser.ast != null) {
			parser.ast.accept(new VisitorIdentificacion(), null);
		}

		if (! ManejadorErrores.getInstance().existeErrores() && parser.ast != null) {
			parser.ast.accept(new VisitorSemantico(), null);
		}

		if (! ManejadorErrores.getInstance().existeErrores() && parser.ast != null) {
			parser.ast.accept(new VisitorOffset(), null);
		}

		// Mostrar errores
		ManejadorErrores.getInstance().getErrores().forEach(System.err::println);
	}
}
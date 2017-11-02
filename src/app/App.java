package app;


import LinkedList.LinkedListD;
import node.node;

public class App {

	public static void main(String[] args) {
		LinkedListD<String> names = new LinkedListD<String>();
		
		names.addFirst("xavier");
		names.addFirst("miranda");
		names.addLast("gaby");
		names.addBefore("xavier", "carlos");
		names.addAfter("gaby", "daniela"); 
		names.addLast("fernando");
		
		if (names.isEmpty()) {
			System.out.println("Esta vacia");
			System.exit(0);
		}else {
			System.out.println("No esta vacia");
		}
		
		node<String> find = names.search("xavier");
		System.out.println();
		if (find!=null) {
			System.out.println("\nValor encontrado : " + find.getValue() + " con un index " + find.getIndex());
		}else {
			System.out.println("El elemento no se encontro en la lsita");
		}
		
		System.out.println();
		names.pronter();
		
		names.remove("daniela");
		names.remplse("fernando", "sigala");
		
		System.out.println();
		names.list();
		
		node<String> first=names.getFist();
		node<String> last=names.getLast();
		
		System.out.println();
		if (first != null) {
			System.out.println("El primer elemento es: " + first.getValue());
		}else {
			System.out.println("La lista esta vacia");
		}
		
		if (last!=null) {
			System.out.println("El ultimo elemento es: " + last.getValue());
		}else{
			System.out.println("La lista esta vacia");
		}
		
		names.removeFirst();
		names.removeLast();
		
		System.out.println();
		names.prenter();
		
		System.out.println("\n"+names.size()+" elementos");
		
		System.out.println("\n" + names.indexOf("gaby")+" aplicacion del metodo indexOf()");
		
		names.clear();
		System.out.println();
		if (names.isEmpty()) {
			System.out.println("Esta vacia");			
		}else {
			System.out.println("No esta vacia");
		}
	}

}

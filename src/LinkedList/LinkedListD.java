package LinkedList;

import java.util.Iterator;

import node.node;

public class LinkedListD<T extends Comparable<T>> implements Iterator<T>{
	private node<T> start = null, end = null,auxIt=null;
	
	public LinkedListD(){
		creatList();
	}
	
	public LinkedListD(T value){
		this();
		start.setNext(new node<T>(value));
		start.getNext().setIndex(0);
		end.setBack(start.getNext());
	}
	private void creatList() {
		start = new node<T>();
		start.setIndex(-1);
		end = new node<T>();
		end.setIndex(-1);
	}
	public void addFirst(T value){
		node<T> tmp = start.getNext();
		start.setNext(new node<T>(value));
		if (tmp == null){
			start.getNext().setIndex(0);
			end.setBack(start.getNext());
		} 
		else{
			start.getNext().setNext(tmp);
			tmp.setBack(start.getNext());
		}
		reindex();
	}
	
	public void addLast(T value){
		node<T> tmp = end.getBack();
		end.setBack(new node<T>(value));
		if (tmp == null){
			end.getBack().setIndex(0);
			start.setNext(end.getBack());
		} 
		else{
			end.getBack().setBack(tmp);
			tmp.setNext(end.getBack());
		}
		reindex();
	}
	
	public void addAfter(T value, T newValue) {
		node<T> found = search(value);
		if (found!=null) {
			node<T> _new=new node<T>(newValue);
			if (found.getNext() != null) {
				found.getNext().setBack(_new);
				_new.setBack(found);
				_new.setNext(found.getNext());
				found.setNext(_new);
			}else {
				end.setBack(_new);
				_new.setBack(found);
				found.setNext(_new);
			}
			reindex();
		}
	}
	
	public void addBefore(T value,T newValue) {
		node<T> found = search(value);
		if (found!=null) {
			node<T> _new=new node<T>(newValue);
			if (found.getBack() != null) {
				found.getBack().setNext(_new);
				_new.setNext(found);
				_new.setBack(found.getBack());
				found.setBack(_new);
			}else {
				start.setNext(_new);
				_new.setNext(found);
				found.setBack(_new);
			}
			reindex();
		}
	}
	
	public node<T> search (T value){
		return search (value, start,end);
	}
	private node<T> search (T value, node<T> start,node<T> end){
		if (!isEmpty()) {
			if(start.getNext().getValue().equals(value)){
				return start.getNext();
			}else if(end.getBack().getValue().equals(value)) {
				return end.getBack();
			}else if (start.getNext().equals(end) && end.getBack().equals(start)) {
				return null;
			}
			return search(value, start.getNext(),end.getBack());
		}
		return null;
		
	}
	
	private void reindex(){
		node<T> tmp = start;
		int i = 0;
		while (tmp.getNext()!=null) {
			tmp = tmp.getNext();
			tmp.setIndex(i);
			i++;
		}
	}
	
	public void pronter() {
		node<T> tmp = start;
		while (tmp.getNext()!=null) {
			tmp = tmp.getNext();
			System.out.println(tmp.getIndex()+" "+tmp.getValue());
		}
	}
	
	public void prenter() {
		node<T> tmp = end;
		while (tmp.getBack()!=null) {
			tmp = tmp.getBack();
			System.out.println(tmp.getIndex()+" "+tmp.getValue());
		}
	}
	public boolean isEmpty() {
		return (start.getNext()!=null && end.getBack()!=null)?false:true;
	}
	
	public int size() {
		node<T> tmp = this.start;
		int cont = 0;
		while (tmp.getNext() != null) {
			cont++;
			tmp = tmp.getNext();
		}
		return cont;
	}
	
	public long indexOf(T value) {
		node<T> found = search(value);
		if (found != null) {
			return found.getIndex();
		}
		return -1;
	}
	
	public void remplse(T value,T newValue) {
		node<T> found=search( value);
		found.setValue(newValue);
	}
	
	public void remove(T value) {
		node<T> found = search(value);
		if (found != null) {
			found.getBack().setNext(found.getNext());
			found.getNext().setBack(found.getBack());
			System.gc();
			reindex();
		}
	}
	
	public void removeAfter(T value) {
		node<T> found = search(value);
		if (found != null) { 
			if(found.getNext() != null) {
				if (found.getNext().getNext() != null) {
					found.getNext().getNext().setBack(found);
					found.setNext(found.getNext().getNext());
				}else {
					end.setBack(found);
					found.setNext(null);
				}
				System.gc();
				reindex();
			}else {
				System.out.println("No se puede eliminar despes de " + value + " porque" + value + " es el ultimo valor");
			}
		}
	}
	
	public void removeBefore(T value) {
		node<T> found = search(value);
		if (found!=null) {
			if (found.getBack() != null) {
				if (found.getBack().getBack() != null) {
					found.getBack().getBack().setNext(found);
					found.setBack(found.getBack().getBack());
				}else {
					start.setNext(found);
					found.setBack(null);
				}
				System.gc();
				reindex();
			}else {
				System.out.println("No se puede eliminar antes de " + value + " porque" + value + " es el primer valor");
			}
		}
	}
	
	public void removeFirst() {
		node<T> first=getFist();
		if (first!=null) {
			start.setNext(first.getNext());
			first.getNext().setBack(null);
			System.gc();
			reindex();
		}
	}
	
	public void removeLast() {
		node<T> last= getLast();
		if (last!=null) {
			end.setBack(last.getBack());
			last.getBack().setNext(null);
		}
	}
	
	public node<T> getFist(){
		if(!isEmpty())
			return start.getNext();
		
		return null;
	}
	
	public node<T> getLast(){
		if (!isEmpty())
			return end.getBack();
		
		return null;
	}
	
	public void clear() {
		start = null;
		end = null;
		System.gc();
		creatList();
	}
	
	public void list() {
		auxIt=start;
		if (!isEmpty()) {
			while(hasNext()) {
				System.out.println(next());
			}
		}
	}

	public node<T> getStart(){
		return this.start;
	}
	
	public node<T> getEnd(){
		return this.end;
	}
	
	@Override
	public boolean hasNext() {
		return auxIt.getNext()!=null;
	}

	@Override
	public T next() {
		T value = auxIt.getNext().getValue();
		auxIt=auxIt.getNext();
		return value;
	}
	
	public boolean sonIguales(LinkedListD<T> lista,LinkedListD<T> listaComparar) {
		if (!lista.isEmpty() && !listaComparar.isEmpty()) {
			if (lista.size() == listaComparar.size()) {
				return equals(lista.getStart(), listaComparar.getStart());
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	private boolean equals(node<T> sentinelList,node<T> sentinelListCompere) {
		if (sentinelList.getNext() != null && sentinelListCompere.getNext() != null) {
			if (sentinelList.getNext().getValue().equals(sentinelListCompere.getNext().getValue())) {
				return equals(sentinelList.getNext(), sentinelListCompere.getNext());
			}else {
				return false;
			}
		}
		return true;
	}
	public node<T> existeElemento(LinkedListD<T> lista,T value) {
		if (!lista.isEmpty())
			return serchRecElement(lista.getStart(),lista.getEnd(), value);
		return null;
	}
	private node<T> serchRecElement(node<T> sentinelStart,node<T> sentinelEnd,T value) {
			if(sentinelStart.getNext().getValue().equals(value)){
				return sentinelStart.getNext();
			}else if(sentinelEnd.getBack().getValue().equals(value)) {
				return sentinelEnd.getBack();
			}else if (sentinelStart.getNext().equals(sentinelEnd) && sentinelEnd.getBack().equals(sentinelStart)) {
				return null;
			}
			return serchRecElement(sentinelStart.getNext(), sentinelEnd.getBack(), value);
	}
	
	public int ocurrencia(LinkedListD<T> list,T value) {
		if (!list.isEmpty()) {
			return ocurrenciaRec(list.getStart(),value,0);
		}
		return -1;
	}
	private int ocurrenciaRec(node<T> sentinelStart,T value,int occ) {
		if (sentinelStart.getNext()!=null) {
			if (sentinelStart.getNext().getValue().equals(value)) {
				occ++;
				return ocurrenciaRec(sentinelStart.getNext(), value, occ);
			}else {
				return ocurrenciaRec(sentinelStart.getNext(), value, occ);
			}
		}
		return occ;
	}
	private boolean isInteger(node<T> data) {
		if (data.getValue() instanceof Integer) {
			return true;
		}else {
			return false;
		}
	}
	public int suma(LinkedListD<T> list) {
		if (!list.isEmpty()) {
			if (list.isInteger(list.getStart().getNext())) {
				return sumRec(list.getStart(), 0);
			}
		}
		return 0;
	}
	private int sumRec(node<T> data,int sum) {
		if (data.getNext()!=null) {
			sum+=(int)data.getNext().getValue();
			return sumRec(data.getNext(), sum);
		}
		return sum;
	}
	
	

}

package model;

public class ReservedRoom extends Room {

	private ReservedRoom previous;
	private ReservedRoom next;
	
	public ReservedRoom(String number, String id, int typeOfBeds, boolean availability, String hotel, ReservedRoom previous, ReservedRoom next) {
		super(number, id, typeOfBeds, availability, hotel);
		this.previous = previous;
		this.next = next;
	}
	
	public ReservedRoom getPrevious() {
		return previous;
	}
	public void setPrevious(ReservedRoom previous) {
		this.previous = previous;
	}
	public ReservedRoom getNext() {
		return next;
	}
	public void setNext(ReservedRoom next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "\nNumero de habitacion: " + getNumber() + "\nId: " + getId() + "\nCantidad de camas: " + getTypeOfBeds() + "\nDisponibilidad: " 
				+ getAvailability() +  "\nHotel: " + getHotel() +  "\n------------------------------";
	}
	
}

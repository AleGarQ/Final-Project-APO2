package model;

public class CustomList {

	private CustomList left;
	private CustomList rigth;
	
	public CustomList(CustomList left, CustomList rigth) {
		this.left = left;
		this.rigth = rigth;
	}
	public CustomList getLeft() {
		return left;
	}
	public void setLeft(CustomList left) {
		this.left = left;
	}
	public CustomList getRigth() {
		return rigth;
	}
	public void setRigth(CustomList rigth) {
		this.rigth = rigth;
	}
	
}

package mayinTarlasi;

import javax.swing.JButton;

public class Buton extends JButton {

	private static final long serialVersionUID = 1L;
	private int satir,sutun,sayac;
	private boolean mayin,bayrak;
	
	public Buton(int satir, int sutun) {
		super();
		this.satir=satir;
		this.sutun=sutun;
		this.sayac=0;
		this.mayin=false;
		this.bayrak=false;
	}
	
	public int getSatir() {
		return satir;
	}
	
	public void setSatir(int satir) {
		this.satir=satir;
	}
	
	public int getSutun() {
		return sutun;
	}
	
	public void setSutun(int sutun) {
		this.sutun=sutun;
	}

	public int getSayac() {
		return sayac;
	}
	
	public void setSayac(int sayac) {
		this.sayac=sayac;
	}
	
	public boolean isMayin() {
		return mayin;
	}
	
	public void setMayin(boolean mayin){
		this.mayin=mayin;
	}
	
	public boolean isBayrak() {
		return bayrak;
	}
	
	public void setBayrak(boolean bayrak) {
		this.bayrak=bayrak;
	}

}

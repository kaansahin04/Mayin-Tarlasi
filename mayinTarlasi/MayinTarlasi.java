package mayinTarlasi;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MayinTarlasi implements MouseListener{
	
	JFrame frame;
	Buton[][] tahta=new Buton[10][10];
	int acilan;
	
	public MayinTarlasi() {
		acilan=0;
		frame=new JFrame("Mayın Tarlası");
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(10,10));
		
		for(int satir=0; satir<tahta.length; satir++) {
			for(int sutun=0; sutun<tahta[0].length; sutun++) {
				Buton b=new Buton(satir,sutun);
				frame.add(b);
				b.addMouseListener(this);
				tahta[satir][sutun]=b;
			}
		}
		
		mayinDose();
		mayinSay();
		
		frame.setVisible(true);
	}
	
	public void mayinDose() {
		int i=0;
		while(i<10) {
			int rastgele1=(int)(Math.random()*tahta.length);
			int rastgele2=(int)(Math.random()*tahta[0].length);
			while(tahta[rastgele1][rastgele2].isMayin()) {
				rastgele1=(int)(Math.random()*tahta.length);
				rastgele2=(int)(Math.random()*tahta[0].length);
			}
			tahta[rastgele1][rastgele2].setMayin(true);
			i++;
		}
	}
	
	public void bastir() {
		for(int satir=0; satir<tahta.length; satir++) {
			for(int sutun=0; sutun<tahta[0].length; sutun++) {
				if(tahta[satir][sutun].isMayin()) {
					tahta[satir][sutun].setIcon(new ImageIcon("src/mayın.png"));
				}
			}
		}
	}
	
	public void mayinSay() {
		for(int satir=0; satir<tahta.length; satir++) {
			for(int sutun=0; sutun<tahta[0].length; sutun++) {
				if(tahta[satir][sutun].isMayin()) {
					saymaca(satir,sutun);
				}
			}
		}
	}
	
	public void saymaca(int satir, int sutun) {
		for(int i=satir-1; i<=satir+1; i++) {
			for(int j=sutun-1; j<=sutun+1; j++) {
				try {
					int deger=tahta[i][j].getSayac();
					tahta[i][j].setSayac(++deger);
				}catch(Exception e) {
						
				}
			}
		}
	}
	
	public void ac(int satir, int sutun){
		if(satir<0 || satir>=tahta.length || sutun<0 || sutun>=tahta[0].length
		   || tahta[satir][sutun].getText().length()>0 || tahta[satir][sutun].isEnabled()==false) {
			return;
		}else if(tahta[satir][sutun].getSayac()!=0) {
			tahta[satir][sutun].setText(String.valueOf(tahta[satir][sutun].getSayac()));
			tahta[satir][sutun].setEnabled(false);
			acilan++;
		}else {
			acilan++;
			tahta[satir][sutun].setEnabled(false);
			ac(satir-1, sutun);
			ac(satir+1, sutun);
			ac(satir, sutun-1);
			ac(satir, sutun+1);
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		//hangi butona tıklandığını getir
		Buton tiklananButon=(Buton)me.getComponent();
		//tıklama olayını getir; 1 olması sol tık olduğu anlamına gelir, 3 ise sağ tık
		if(me.getButton()==1) {
			//eğer o butonda mayın varsa oyun biter
			if(tiklananButon.isMayin() && !tiklananButon.isBayrak()) {
				bastir();
				JOptionPane.showMessageDialog(frame, "Maalesef, mayına bastınız.", "Kaybettiniz!", JOptionPane.ERROR_MESSAGE);
				frame.setVisible(false);
				MayinTarlasi yeniden=new MayinTarlasi();
			}else if(!tiklananButon.isMayin() && !tiklananButon.isBayrak()){
				ac(tiklananButon.getSatir(), tiklananButon.getSutun());
				if(acilan==(tahta.length*tahta[0].length)-10) {
					bastir();
					JOptionPane.showMessageDialog(frame, "Tebrikler, hiçbir mayına basmadınız.", "Kazandınız!", JOptionPane.PLAIN_MESSAGE);
					frame.setVisible(false);
					MayinTarlasi yeniden=new MayinTarlasi();
				}
			}
		}else if(me.getButton()==3) {
			//eğer o butonda bayrak yoksa bayrak koy, varsa kaldır
			if(!tiklananButon.isBayrak() && (tiklananButon.getText()=="")) {
				tiklananButon.setIcon(new ImageIcon("src/bayrak.png"));
				tiklananButon.setBayrak(true);
			}else {
				tiklananButon.setIcon(null);
				tiklananButon.setBayrak(false);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {}
	@Override
	public void mouseReleased(MouseEvent me) {}
	@Override
	public void mouseEntered(MouseEvent me) {}
	@Override
	public void mouseExited(MouseEvent me) {}
	
}

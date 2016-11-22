package downloader.ui;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;

import downloader.fc.Downloader;

public class ThreaDownload extends Thread implements PropertyChangeListener{

	Downloader donelaudeur;
	JProgressBar jpb;
	
	public ThreaDownload(Downloader dl, JProgressBar b)
	{
		donelaudeur = dl;
		donelaudeur.addPropertyChangeListener(this);
		jpb = b;
		jpb.setString("0%");
		jpb.setStringPainted(true);

	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		jpb.setValue(donelaudeur.getProgress());
		jpb.setString(donelaudeur.getProgress() + "%");
		
	}
	
	
	public void run()
	{
		try {
			donelaudeur.download();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			jpb.setBackground(Color.RED);
			e.printStackTrace();
		}
	}
	
}

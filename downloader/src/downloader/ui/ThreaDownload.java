package downloader.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import downloader.fc.Downloader;

public class ThreaDownload extends Thread implements PropertyChangeListener{

	class Listener_interne implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() instanceof JButton)
			{
				
				JButton b = (JButton) e.getSource();
				JPanel p = (JPanel) b.getParent().getParent();
				if(b.getText()== " || ")
				{
					b.setText(" > ");
					donelaudeur.get_lock().lock();
				}
				else if(b.getText() == " > ")
				{
					b.setText(" || ");
					donelaudeur.get_lock().unlock();
				}
				else if(b.getText() == " X ")
				{
					donelaudeur.cancel(true);
					b.getParent().getParent().removeAll();
				}
				p.repaint();

			}
			
		}
		
	}
	
	Downloader donelaudeur;
	JProgressBar jpb;
	Listener_interne li = new Listener_interne();
	
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
		
		donelaudeur.execute();
		
/*		try {
			donelaudeur.download();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			jpb.setBackground(Color.RED);
			e.printStackTrace();
		}
		*/
	}
	
}

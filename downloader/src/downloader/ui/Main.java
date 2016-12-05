package downloader.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTextField;


import downloader.fc.Downloader;

public class Main extends JFrame implements PropertyChangeListener, ActionListener{

//    ArrayList<JProgressBar> jpb;
	JPanel window_barres; //barres de chargement
	JPanel window_add; //ajout de chargements
	JSplitPane split;
//	ArrayList<Downloader> dl;
	ArrayList<ThreaDownload> l_threads;
	JButton add;
//	JButton play_pause;
	JTextField text;
	
	public Main(String title) {
		super(title);
		
		window_barres = new JPanel();
		window_barres.setLayout(new StackLayout());
		
		window_add = new JPanel();
		
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, window_barres, window_add);
		split.setResizeWeight(1);
		split.setDividerSize(0);
		add(split);
		
				
//		jpb = new ArrayList<JProgressBar>(); à del
		
	//	this.add(jpb);
	//	window_barres.add(jpb);

		//ajout de la zone de texte
		text = new JTextField("saisissez votre url de téléchargement ici");
		window_add.add(text);

		//ajout du bouton
		add = new JButton("ADD");		
		window_add.add(add);
		
		add.addActionListener(this);
		
		
		
		//dl = new Downloader("http://iihm.imag.fr/blanch/RICM4/IHM/tPs/3-notification/downloader.src.tgz");
/*		dl = new Downloader("http://iihm.imag.fr/blanch/RICM4/IHM/tps/1-grapher-swing/grapher.src.tgz");
		dl.addPropertyChangeListener(
				new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						jpb.setValue(dl.getProgress());
						jpb.setString(dl.getProgress() + "%");
						
					}
				});
*/
		
		
		
//		dl = new ArrayList<Downloader>();  à del

		
//		thread = new ThreaDownload(dl, jpb);
		l_threads = new ArrayList<ThreaDownload>();  //utilité d'enregistrer les threads dans une liste ?
		
/*		jpb.setString("0%");
		jpb.setStringPainted(true);
*/
		pack();
		
	}
	
	public static void main(String[] args) {
		Main main = new Main("jambon");
		main.setVisible(true);
		
//		main.dl.execute();
		//main.dl.addPropertyChangeListener(main);
//		try {
//			main.dl.download();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
//		jpb.setValue(dl.getProgress());
//		jpb.setString(dl.getProgress() + "%");
		
//		JProgressBar _jpb = evt.getSource();   à del
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		JPanel p_barre = new JPanel();
		JPanel p_boutons = new JPanel();
		JPanel p_barre_boutons = new JPanel();
		JLabel label = new JLabel(text.getText());
		
		JButton remove = new JButton(" X ");
		JButton play_pause = new JButton(" || ");
//		Layout layout = new Layout();

		
		JProgressBar new_jpb = new JProgressBar();
		new_jpb.setString("0%");
		new_jpb.setStringPainted(true);
		
		
		p_barre_boutons.setLayout(new BorderLayout());
		p_barre_boutons.add(p_barre, BorderLayout.CENTER);
		p_barre_boutons.add(p_boutons, BorderLayout.EAST);

		p_barre.setLayout(new BorderLayout());
		p_barre.add(label, BorderLayout.NORTH);
		p_barre.add(new_jpb, BorderLayout.SOUTH);
		
		p_boutons.setLayout(new BorderLayout());
		p_boutons.add(play_pause, BorderLayout.WEST);
		p_boutons.add(remove, BorderLayout.EAST);
		
//		window_barres.add(new_jpb);
		
		

		window_barres.add(p_barre_boutons);
		
		ThreaDownload new_thread = new ThreaDownload(new Downloader(text.getText()), new_jpb);
		l_threads.add(new_thread);

		
		play_pause.addActionListener(new_thread.li);
		remove.addActionListener(new_thread.li);
		
		new_thread.run();
		
		this.pack();
		this.repaint();
		

/*		JProgressBar new_jpb = new JProgressBar();
		new_jpb.setString("0%");
		new_jpb.setStringPainted(true);
		jpb.add(new_jpb);     à del*/   
	}

}

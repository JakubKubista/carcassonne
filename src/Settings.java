import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Třída Settings je potomkem třídy JFrame, kterou jsme si museli naimportovat. Obohacuje
 * aplikaci GUI rozhráním. Zde se tedy jedná o Boxy, ve kterých volíme počet hráčů, následně 
 * o jména hráčů, které jsou zprostředkovány za pomocí JTextFieldu a popsány JLabely a nakonec
 * pro potvrzování JButtony.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Settings  extends JFrame
{
    final JComboBox<String> playerList = new JComboBox<String>();
    private JLabel[] playerLabel = new JLabel[6];
    private JTextField[] playerField = new JTextField[6];
    private String[] players = new String[6];
    
    private javax.swing.JCheckBox jCheckBox1;
    private JButton apply;
    private JButton defaultSet;
    
    private JLabel player;
    
    private Box left;
    private Box right;
    private Box down;
    
    boolean turnOff;
    int index;
    /**
     * Constructor for objects of class Settings. Konstruktor nám zavolá playerType a vytvoří
     * Layout, neboli nám určí podle jakého měřítka budou jednotlivé GUI objekty rozděleny.
     */
    public Settings()
    {
        turnOff = false;
        playerType();
        setLayout(new GridLayout(0, 2, 10, 10));
        base();
    }
    /**
     * Do kontejneru hráčů uvedeme všechny možnosti počtů hráčů a označíme aktuální index.
     */
    public void playerType(){
    	playerList.addItem("    2 Players");
		playerList.addItem("    3 Players");
		playerList.addItem("    4 Players");
		playerList.addItem("    5 Players");
		playerList.setSelectedIndex(0);
		player = new JLabel("");
    }
    /**
     * Metoda, na které se Setting zakládá, zde deklarujeme proměnné, přidělujeme titulek,
     * nastavujeme kde bude JFrame umístěn a jeho možné ActionListener(), neboli naslouchače 
     * co za akci právě požadujeme. První ActionListener() pro kontejner hráčů nám označí aktuální
     * vybraný box. Druhý a třetí se spustí po potvrzení jejich příslušnými buttony.
     */
    public void base(){    
        super.setTitle("Settings");
        
        apply = new JButton("Apply");
        defaultSet = new JButton("Default");
        
        setLocation(333, 320);
		playerList.setEditable(false);
		JComboBoxPlayers(playerList);

		playerList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedPlayer = (String) combo.getSelectedItem();
				DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) combo.getModel();

				int selectedIndex = model.getIndexOf(selectedPlayer);
				if (selectedIndex < 0) {
					model.addElement(selectedPlayer);
				}
				index = selectedIndex;
				JComboBoxPlayers(playerList);
				selectedPlayer = (String) playerList.getSelectedItem();
			}
		});       
        
		apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
			    setPackNames();
				setNumberOfPlayers(index);
				setVisible(false);
				turnOff = true;
			}
		});

		defaultSet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				playerList.setSelectedIndex(0);
				index = 0;
				remove(playerList);
				JComboBoxPlayers(playerList);
			}
		});   
    }
    /**
     * Metoda main(String[] args) je také známá jako tělo JFramu. Zde máme vytvořenou výjmku pro
     * zvýšení bezpečnosti a vytvoření aktuální třídy.
     * 
     */
    public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Settings().setVisible(true);
			}
		});
    }
    /**
     * Metoda zavolá pole hráčů.
     */
    public String[] getPackNames(){
        return players;
    }

    /**
     * Metoda nastaví pole hráčů.
     */
    public void setPackNames(){
        int j = index + 3;
        for( int i = 1; i<j; i++){
                players[i] = playerField[i].getText();
         }
    }
    /**
     * Metoda nastaví počet hráčů.
     */
    public void setNumberOfPlayers(int index){
        this.index = index;
    }
    /**
     * Zavolá počet hráčů (je přidělena o 1, jelikož zde počítáme od jiné hodnoty než všude jinde).
     */
    public int getNumberOfPlayers(){
        return index+1;
    }
    /**
     * Metoda určená pro vypnutí JFrame.
     */
    public boolean getTurnOff(){
        return turnOff;
    }
    /**
     * Metoda pro údržbu boxu s uvedenými počty hráčů. Nejprvu odebere předchozí hráče a poté přidá
     * nové. Zároveň do framu přidělí všechny GUI objekty.
     * 
     */
    public void JComboBoxPlayers(JComboBox<String> playerList) {
      remove(playerList);
      add(playerList);
      add(player);
	  int j = index + 3;
		for(int i = 1;i<6; i++){
		    if(playerLabel[i] !=null){
			        remove(playerLabel[i] );
                    remove(playerField[i] );
            }
        }
	    for(int i = 1;i<j;i++){
			        playerLabel[i] = new JLabel("Player " + i + ". name: ");
			        playerField[i]  = new JTextField("Lord" + i);
			                add(playerLabel[i] );
                            add(playerField[i] );
        }
        add(apply);
        add(defaultSet);
        setVisible(true);
        pack();
    }
}

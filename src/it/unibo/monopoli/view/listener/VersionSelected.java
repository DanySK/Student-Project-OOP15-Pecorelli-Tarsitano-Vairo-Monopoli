package it.unibo.monopoli.view.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VersionSelected implements ItemListener {

	private static Object selectedItem;
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			//SelectedItem è l'Item selezionato
			selectedItem = e.getItem();
			System.out.println("Hai selezionato: " + selectedItem);
			System.out.println("get: " + getSelectedItem());
		}

	}
	
	public static String getSelectedItem(){
		return (String)selectedItem;
	}

}

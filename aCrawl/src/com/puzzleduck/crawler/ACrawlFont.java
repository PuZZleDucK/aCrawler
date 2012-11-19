/**
 * 
 */
package com.puzzleduck.crawler;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author puzzleduck
 *
 */
public class ACrawlFont implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ScanPanel.thisFont = new Font ( (String) ACrawler.fontComboBox.getSelectedItem() , 0 , (Integer)ACrawler.fontSizeComboBox.getSelectedItem() ); //new Font ("Liberation Mono", 0 , 8);
		
	}

}

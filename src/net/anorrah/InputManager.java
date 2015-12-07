package net.anorrah;

import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import org.newdawn.slick.Input;

public class InputManager implements KeyListener, MouseListener
{
//Key Events-------------------------------------------------------------------------------------------------
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		switch(key)
		{
		case KeyEvent.VK_W:
			if(Core.inGame && !Core.player.isMoving && !Core.itempicked)
			{
				Core.bW = true;
				//Core.t.WaitForPlayer=false;
				Core.t.setWaitForPlayerToFalse();
			}
			break;
			
		case KeyEvent.VK_A:
			if(Core.inGame && !Core.player.isMoving && !Core.itempicked)
			{
				Core.bA = true;
				//Core.t.WaitForPlayer=false;
				Core.t.setWaitForPlayerToFalse();
			}
			break;
			
		case KeyEvent.VK_S:
			if(Core.inGame && !Core.player.isMoving && !Core.itempicked)
			{
				Core.bS = true;
				//Core.t.WaitForPlayer=false;
				Core.t.setWaitForPlayerToFalse();
			}
			break;
			
		case KeyEvent.VK_D:
			if(Core.inGame && !Core.player.isMoving && !Core.itempicked)
			{
				Core.bD = true;
				//Core.t.WaitForPlayer=false;
				Core.t.setWaitForPlayerToFalse();
			}
			break;
			
		case KeyEvent.VK_SPACE:
			//Core.player.attack(null);
			if(Core.running)
			{
				Core.player.attack(Core.player.getlocationX(),5);
				Core.t.setWaitForPlayerToFalse();
			}
			break;
			
		case KeyEvent.VK_ESCAPE:
			Core.inGame = !Core.inGame;
			if(Core.inGame)
				System.out.println("unPaused");
			else
				System.out.println("Paused");
			break;
		
		case KeyEvent.VK_ENTER:
			if(Core.itempicked && Core.inGame)
				Core.equipitem();
			break;
		case KeyEvent.VK_BACK_SPACE:
			if(Core.itempicked && Core.inGame)
				System.out.println("pass");
				Core.itemclear();
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		
	}

	public void keyTyped(KeyEvent e) 
	{
		
	}
//--------------------------------------------------------------------------------------------------------
//Mouse Events--------------------------------------------------------------------------------------------
//these need to be here
	public void mouseClicked(MouseEvent e)//whenever a button has been pressed and Released 
	{	
		Core.t.setWaitForPlayerToFalse();
		System.out.println("attack at " + e.getID());
	}

	public void mouseEntered(MouseEvent e)//whenever cursor enters a component
	{
		
	}

	public void mouseExited(MouseEvent e)//when cursor exits a component
	{
		
	}

	public void mousePressed(MouseEvent e)//only when a mouse-button is pressed 
	{
		
		/*
		 * To check for the mouse buttons
		 * 
		 * SwingUtilities.isLeftMouseButton(MouseEvent e)
		 * SwingUtilities.isRightMouseButton(MouseEvent e)
		 * SwingUtilities.isMiddleMouseButton(MouseEvent e)
		 */
	}

	public void mouseReleased(MouseEvent e)//only when a mouse-button is released after pressing
	{
		
	}	
	
}

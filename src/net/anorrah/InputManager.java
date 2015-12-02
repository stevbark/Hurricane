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
		case KeyEvent.VK_UP:
			if(!Core.player.isMoving)
			{
				Core.bW = true;
			}
			break;
			
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:

			if(!Core.player.isMoving)
			{
				Core.bA = true;
			}
			break;
			
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			if(!Core.player.isMoving)
			{
				Core.bS = true;
			}
			break;
			
		case KeyEvent.VK_D:	
		case KeyEvent.VK_RIGHT:

			if(!Core.player.isMoving)
			{
				Core.bD = true;
			}
			break;
			
		case KeyEvent.VK_SPACE:
			
			//Core.player.attack();
			break;
			
		default:
			break;
			
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		/*
		int key = e.getKeyCode();
		
		switch(key)
		{
		case KeyEvent.VK_W:
			Core.player.isMoving = false;
			Core.bW = false;
			break;
			
		case KeyEvent.VK_A:
			Core.player.isMoving = false;
			Core.bA = false;
			break;
			
		case KeyEvent.VK_S:
			Core.player.isMoving = false;
			Core.bS = false;
			break;
			
		case KeyEvent.VK_D:
			Core.player.isMoving = false;
			Core.bD = false;
			break;
			
		default:
			break;
			
		}*/
	}

	public void keyTyped(KeyEvent e) 
	{
		
	}
//--------------------------------------------------------------------------------------------------------
//Mouse Events--------------------------------------------------------------------------------------------
//these need to be here
	public void mouseClicked(MouseEvent e)//whenever a button has been pressed and Released 
	{
		
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

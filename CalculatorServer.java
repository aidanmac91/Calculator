/**
 * @file CalculatorServer.java
 * @author Aidan McCarthy 20046537
 * @project Assignment 3 RMI Calculator
 * @brief The server class for the calculator
 */

/*
The Imports for the calculators
 */
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;


import java.awt.*;

import java.net.InetAddress;


public class CalculatorServer extends UnicastRemoteObject
	implements Calculator {

		public Frame serverGUI = new Frame("Server GUI"); //the gui for the server

		public TextArea information = new TextArea();//the text area showing information the server is recieving

		
	
	public CalculatorServer() throws RemoteException {
		super();
		buildGUI();//guilds the server gui
	}

	
	/**
	 * Builds the GUI for the server
	 */
	private void buildGUI()
	{

		serverGUI.setVisible(true);//sets it to visable
		serverGUI.setSize(360,360);//set size

		information.setBounds(25,230,250,100);
		information.setEditable(false);
		serverGUI.add(information);
	}

	/**
 	* is called when the calculator server is called
 	* @return a string that is displayed to the screen
 	*/
	public String calculator() {
		information.append("Invocation to calulator was succesful!");
		return "Calulator from RMI server!";
	}

	/**
	 * the function to add two numbers together
	 * @param  a the first number
	 * @param  b the second number
	 * @return   the sum of the first and second number
	 */
	public String addNumbers(int a,int b,int id)
	{
		
		try
		{
		information.append("\nClient ID: "+id+" \nIP address :" +InetAddress.getLocalHost().getHostAddress()+"\n\tAdding "+a+ " and "+b);//Displays to server's screen
		int answer=a+b;
		return (""+answer);
		}
		catch(Exception e)
		{
			information.append("\n"+e.getMessage());
			return "";
		}

	}

	/**
	 * the function to subtract two numbers
	 * @param  a the first number
	 * @param  b the second number
	 * @return   first number take away the second number
	 */
	public String takeNumbers(int a,int b,int id)
	{
		try
		{
		information.append("\nClient ID: "+id+" \nIP address :"+InetAddress.getLocalHost().getHostAddress()+"\n\tSubtracting "+b+" from "+a);//displays to server's screen
		int answer=a-b;
		return (""+answer);
		}
		catch(Exception e)
		{
			information.append("\n"+e.getMessage());
			return "";
		}
	}

	/**
	 * the function to multiply two numbers together
	 * @param  a the first number
	 * @param  b the second number
	 * @return   the product of the first number and the second number
	 */						
	public String multiplyNumbers(int a, int b,int id)
	{
		try
		{
		information.append("\nClient ID: "+id+" \nIP address :"+InetAddress.getLocalHost().getHostAddress()+"\n\tMultiplying "+a+" by "+b);//displays to server's screen
		int answer=a*b;
		return (""+answer);
		}
		catch(Exception e)
		{
			information.append("\n"+e.getMessage());
			return "";
		}
	}

	/**
	 * the function to divide two numbers
	 * @param  a the first number
	 * @param  b the second number
	 * @return   the first number divided by the second number
	 */
	public String divideNumbers(int a,int b,int id)
	{
		try
		{
		information.append("\nClient ID: "+id+" \nIP address :"+InetAddress.getLocalHost().getHostAddress()+"\n\tDividing "+a+" by "+b);//displays to server's screen
		float answer=(float)a/(float)b;
		return (""+answer);
		}
		catch(Exception e)
		{
			information.append("\n"+e.getMessage());
			return "";
		}
	}

	/**
	 * the run function of the server
	 * @param args[] [description]
	 */
	public static void main(String args[]) {
		try {
			CalculatorServer obj = new CalculatorServer();// Create an object of the CalculatorServer class.
			Naming.rebind("Calculator", obj); // Bind this object instance to the name "Calculator".
			obj.information.append("Calculator bound in registry");// replace Naming.rebind() with the next line registry.rebind( "HelloWorld", obj );
			
		}

		catch (Exception e) {
			//Catches any exception and displays to the screen
			//obj.information.append("\n"+e.getMessage());
			e.printStackTrace();
		}
}
}
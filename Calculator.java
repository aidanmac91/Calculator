/**
 * @file Calculator.java
 * @author Aidan McCarthy 20046537
 * @project Assignment 3 RMI Calculator
 * @brief The interface for the Calculator 
 */

/*
The imports for the Calculator interface
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
	String calculator() throws RemoteException;

	//All functions accept two numbers and the client id and return strings
	
	String addNumbers(int a,int b,int id) throws RemoteException;//function to add numbers
	String takeNumbers(int a,int b,int id) throws RemoteException;//function to take numbers
	String multiplyNumbers(int a, int b,int id) throws RemoteException;//function to multiply numbers
	String divideNumbers(int a, int b,int id) throws RemoteException;//function to divide numbers
}
/**
 * @file CalculatorClient.java
 * @author Aidan McCarthy 20046537
 * @project Assignment 3 RMI Calculator
 * @brief The Client class for the Calculator
 */


/*
The imports for the calculator client
 */
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CalculatorClient extends Frame  implements ActionListener{

	
	boolean box1=true;//flag to indicate what textfield is filled 

	Button addButton=new Button("+");// athe add button
	Button takeButton=new Button("-"); // the minus button 
	Button multipleButton=new Button("*"); // the multiple button
	Button divideButton=new Button("/"); //the divide button
	Button submitButton=new Button("SUBMIT"); //the equals button
	Button clearButton=new Button("CLEAR");//the clear button

	//the buttons for the numbers
	Button no1 = new Button("1"); 
	Button no2 = new Button("2");
	Button no3 = new Button("3");
	Button no4 = new Button("4");
	Button no5 = new Button("5");
	Button no6 = new Button("6");
	Button no7 = new Button("7");
	Button no8 = new Button("8");
	Button no9 = new Button("9");
	Button no0 = new Button("0");

	Label currentOperator = new Label();//shows the current operator

	String firstNumber="";//the first number in the calculation
	String secondNumber="";//the second number in the calculation
	String operator;//the operator

	TextField answerField=new TextField(200);//the answer field

	TextArea information = new TextArea();//the information text field

	int id;//the client id


/**
 * Draws the GUI and creates the action listeners for each button
 * @return [description]
 */
public CalculatorClient()
{

	Random rand = new Random();

	id = rand.nextInt(10) + 1;//randomly generates the id between 1-10

	
	setLayout(null);

	// Creates and places each button
	addButton.setBounds(5,175,75,25);
	add(addButton);
	no0.setBounds(74,175,75,25);
	add(no0);
	submitButton.setBounds(143,175,150,25);
	add(submitButton);

	takeButton.setBounds(5,150,75,25);
	add(takeButton);
	no1.setBounds(74,150,75,25);
	add(no1);
	no2.setBounds(143,150,75,25);
	add(no2);
	no3.setBounds(213,150,75,25);
	add(no3);

	multipleButton.setBounds(5,125,75,25);
	add(multipleButton);
	no4.setBounds(74,125,75,25);
	add(no4);
	no5.setBounds(143,125,75,25);
	add(no5);
	no6.setBounds(213,125,75,25);
	add(no6);

	divideButton.setBounds(5,100,75,25);
	add(divideButton);
	no7.setBounds(74,100,75,25);
	add(no7);
	no8.setBounds(143,100,75,25);
	add(no8);
	no9.setBounds(213,100,75,25);
	add(no9);

	currentOperator.setBounds(5,25,200,20);
	add(currentOperator);
	answerField.setBounds(5,50,280,50);
	add(answerField);
	information.setBounds(25,230,250,100);
	information.setColumns(5);
	add(information);
	clearButton.setBounds(25,202,250,28);
	add(clearButton);

	//Adds action listeners to each button

	addButton.addActionListener(this);
	takeButton.addActionListener(this);
	multipleButton.addActionListener(this);
	divideButton.addActionListener(this);
	submitButton.addActionListener(this);
	clearButton.addActionListener(this);
	no0.addActionListener(this);
	no1.addActionListener(this);
	no2.addActionListener(this);
	no3.addActionListener(this);
	no4.addActionListener(this);
	no5.addActionListener(this);
	no6.addActionListener(this);
	no7.addActionListener(this);
	no8.addActionListener(this);
	no9.addActionListener(this);

	//disables the textfields so it can't be edited
	//ensures that an incorrect number/letter cant be entered

	answerField.setEditable(false);
	submitButton.setEnabled(false);
	information.setEditable(false);

}

	/**
	 * Handles what happens when a button is pressed.
	 * @param x the event when a button is pressed
	 */
	public void actionPerformed(ActionEvent x)
	{
		if (x.getSource()==clearButton)//checks if the button clicked is the clear button
		{
				firstNumber="";//clears the first number variable
				secondNumber="";//clears the second number variable
				operator="";//clears the operator variable
				submitButton.setEnabled(false);//disables the submit button
				answerField.setText("");//clears the answer field
				currentOperator.setText("");
				box1=true;//ensures that the next number field is the first number 
		}
		else//
		{
		if(box1)//sets the first number
		{
			if ((x.getSource()==addButton||x.getSource()==takeButton
			||x.getSource()==multipleButton||x.getSource()==divideButton))
			//if the button is either + - * / 
			{
				if(firstNumber.length()>0)//if first number is not blank
				{
				operator=x.getActionCommand();//sets the operator to the button pressed
				currentOperator.setText("Current operator = "+operator);//sets the label to the operator
				

				box1=!box1;//the next number will be placed into the second number
				}
				else {//if there is no first number inputted first
				answerField.setText("Input first number");
				}

			}
			
			else //if the button pressed isnt an operator
				{
					firstNumber+=x.getActionCommand();//adds the button pressed to the first number variable
					answerField.setText(firstNumber);//displays the first number variable
				}
		
		}		
		else//sets the second number
		{
			if (x.getSource()==submitButton)//if the submit button is pressed
			{
				switch (operator) {//checks the operator and decides which method to call
					case "+" :
							information.append("\nPassing "+firstNumber+" and "+secondNumber+" to the server");
							addNumbers(Integer.parseInt(firstNumber),Integer.parseInt(secondNumber));
							break;
					case "-": 
							information.append("\nPassing "+firstNumber+" and "+secondNumber+" to the server");
							takeNumbers(Integer.parseInt(firstNumber),Integer.parseInt(secondNumber));
							break;
					case "*":
							information.append("\nPassing "+firstNumber+" and "+secondNumber+" to the server");
							multiplyNumbers(Integer.parseInt(firstNumber),Integer.parseInt(secondNumber));
							break;
					case "/":
							if(secondNumber.equalsIgnoreCase("0"))
							{
							answerField.setText("Can not divide by zero ");
							}
							else {
							information.append("\nPassing "+firstNumber+" and "+secondNumber+" to the server");
							divideNumbers(Integer.parseInt(firstNumber),Integer.parseInt(secondNumber));
							}
							break;
				}
				//clears all the varialbes and text fields
				box1=!box1;
				firstNumber="";
				secondNumber="";
				operator="";
				currentOperator.setText("");
				submitButton.setEnabled(false);
			}
			else if ((x.getSource()==addButton||x.getSource()==takeButton
			||x.getSource()==multipleButton||x.getSource()==divideButton)
			&& x.getSource()!=submitButton)//if the button is either + - * / 
			{
				currentOperator.setText("Current operator = "+operator);
				operator=x.getActionCommand();
			}
			else {
				if((x.getSource()=="0") && (secondNumber.length()==0))
				{	
					//does nothing if the number pressed is zero
				}
				else {//sets the second number to the button pressed
					secondNumber+=x.getActionCommand();
					answerField.setText(secondNumber);
					submitButton.setEnabled(true);//enables the submit button
				}
				
			}
		}
	}
		
		
	}
	
	/**
	 * Passes the number supplied to the server and sets the answer to the returned value
	 * @param a the first textfield number
	 * @param b the second textfield number
	 */
	public void addNumbers(int a, int b)
	{
		String answer;//the answer to a+b


		try
		{
			Calculator calc = (Calculator)Naming.lookup("//"+ "127.0.0.1"+ "/Calculator");//creates an connect to the server
			answer=calc.addNumbers(a,b,id);//passes the numbers and the id to the server
			information.append("\n Recieveing "+answer+" from the server");
			answerField.setText(answer);//set the answertextfield to the answer calculated
		}
		catch(Exception ex)//catches any errors
		{
			information.setText("Exception:"+ex);
		}		
	}

	/**
	 * Passes the number supplied to the server and sets the answer to the returned value
	 * @param a the first textfield number
	 * @param b the second textfield number
	 */
	public void takeNumbers(int a, int b)
	{
		String answer;//the answer a-b
		try
		{
			Calculator calc = (Calculator)Naming.lookup("//"+"127.0.0.1"+"/Calculator");//creates an connect to the server
			answer=calc.takeNumbers(a,b,id);//passes the numbers  and id to the server
			
			information.append("\n Recieveing "+answer+" from the server");

			answerField.setText(answer);//set the answertextfield to the answer calculated
		}
		catch(Exception ex)//catches any errors
		{
			information.setText("Exception:"+ex);
		}	
	}

	/**
	 * Passes the number supplied to the server and sets the answer to the returned value
	 * @param a the first textfield number
	 * @param b the second textfield number
	 */
	public void multiplyNumbers(int a, int b)
	{
		String answer;//the answer a*b
		try
		{
			Calculator calc = (Calculator)Naming.lookup("//"+"127.0.0.1"+"/Calculator");//creates an connect to the server
			answer=calc.multiplyNumbers(a,b,id);//passes the numbers and id to the server

			information.append("\n Recieveing "+answer+" from the server");

			answerField.setText(answer);//set the answertextfield to the answer calculated
		}
		catch(Exception ex)//catches any errors
		{
			information.setText("Exception:"+ex);
		}	
	}

	/**
	 * Passes the number supplied to the server and sets the answer to the returned value
	 * @param a the first textfield number
	 * @param b the second textfield number
	 */
	public void divideNumbers(int a, int b)
	{
		String answer;//the answer a/b
		try
		{
			Calculator calc = (Calculator)Naming.lookup("//"+"127.0.0.1"+"/Calculator");//creates an connect to the server
			answer=calc.divideNumbers(a,b,id);//passes the numbers and id to the server

			information.append("\n Recieveing "+answer+" from the server");
			
			answerField.setText(answer);//set the answertextfield to the answer calculated
		}
		catch(Exception ex)//catches any errors
		{
			information.setText("Exception:"+ex);

		}	
	}

// The Calculator object "obj" is the identifier that is
// used to refer to the remote object that implements
// the Calculator interface.

	/**
	 * the run function
	 * @param args[] [description]
	 */
	public static void main(String args[])
	{

		CalculatorClient calc=new CalculatorClient();//creates an instance of client
		calc.setVisible(true);//sets it to visable
		calc.setResizable(false);
		calc.setSize(300,360);//set size
	}
}
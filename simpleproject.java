package JavaTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class simpleproject {

	public static void main(String [] args) throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		//int pinNumber=1234;
		int deposit=0;
		int withdraw = 0;
		int balance = 10000;
	
			System.out.println("Welcome to ATM Services");
			
			System.out.println("Enter your pin number:");
				int pinNumber=sc.nextInt();
				if(pinNumber==1234)
				{
					System.out.println("1. Deposit amount:");
					System.out.println("2. Withdraw amount:");
					System.out.println("3. Receipt:");
					
					int userOpt=sc.nextInt();
					switch(userOpt) 
					{
					case 1:	
						System.out.println("Deposit amount");
						deposit=sc.nextInt();
						balance=balance+deposit;
						System.out.println("Current balance is: "+balance);
						break;
					case 2:
							if(balance>withdraw) {
								System.out.println("Enter withdraw amount");
								withdraw=sc.nextInt();
								balance=balance-withdraw;
								System.out.println("Current balance is: "+balance);
								}
							else
							{
								System.out.println("plz check your current balance");
							}
						break;
					case 3:
						System.out.println("Amount on your account: "+balance);
						break;
					default:
						System.out.println("Your entered wrong number,plz enter correct pin");
					}
				}	
				else
				{
					System.out.println("Your entered wrong pin number,plz enter correct pin");
				}
}
}
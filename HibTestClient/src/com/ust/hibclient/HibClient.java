package com.ust.hibclient;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ust.hibmain.model.Employee;

public class HibClient {

	static Client client = ClientBuilder.newClient();
	static WebTarget target = client.target("http://localhost:8080/Hibtest/");
	static Employee employee;
	
	public static void main(String[] args) {

		int c;
		do {
			System.out.println("****  WELCOME TO EMPLOYEE WEB SERVICE CLIENT  **** \n");

			System.out.println("1. Add employee");
			System.out.println("2. Remove employee");
			System.out.println("3. View all employee");
			System.out.println("4. Update employee");
			System.out.println("5. Search employee");
			System.out.println("6. Exit");

			System.out.println("\n Enter your choice: ");
			Scanner in = new Scanner(System.in);
			c = in.nextInt();

			switch (c) {
			case 1: {
				System.out.println("Enter the name");
				String eName = in.next();
				System.out.println("Enter the age");
				int eAge = in.nextInt();
				System.out.println("Enter the place");
				String ePlace = in.next();
				
				employee=new Employee(eName, eAge, ePlace);

				Response response = target.path("Add").request()
						.post(Entity.entity(employee, MediaType.APPLICATION_JSON), Response.class);
				System.out.println("response: " + response.getStatus() + "\t" + response.getEntity());
				break;
			}

			case 2: {
				System.out.println("Enter the employee id to remove");
				int EId = in.nextInt();

				String all = target.path("delete").request()
						.post(Entity.entity(EId, MediaType.APPLICATION_JSON), String.class);
				System.out.println(all);
				break;
			}
			case 3: {
				String all = target.path("all").request().post(Entity.entity(1, MediaType.APPLICATION_JSON),
						String.class);
				System.out.println(all);
				break;
			}
			case 4: {
				
				System.out.println("Select the field you want to edit: \n");
				
				System.out.println("1. Name");
				System.out.println("2. Age");
				System.out.println("3. Place");

				System.out.println("\n Enter your choice: ");
				int choice=in.nextInt();
				
				if(choice==1)
				{
					System.out.println("Enter the employee id");
					int EId = in.nextInt();
					System.out.println("Enter the new name");
					String eName = in.next();
					employee=new Employee(EId, eName);
					String all = target.path("update").request().post(Entity.entity(employee, MediaType.APPLICATION_JSON),
							String.class);
					System.out.println(all);
					break;
					
				}else if(choice==2)
				{
					System.out.println("Enter the employee id");
					int EId = in.nextInt();
					System.out.println("Enter the new age");
					int eAge = in.nextInt();
					employee=new Employee(EId, eAge);
					String all = target.path("update").request().post(Entity.entity(employee, MediaType.APPLICATION_JSON),
							String.class);
					System.out.println(all);
					break;
					
				}
				else if(choice==3) {
					System.out.println("Enter the employee id");
					int EId = in.nextInt();
					System.out.println("Enter the new place");
					String ePlace = in.next();
					employee=new Employee(ePlace, EId);
					String all = target.path("update").request().post(Entity.entity(employee, MediaType.APPLICATION_JSON),
							String.class);
					System.out.println(all);
					break;
				}
				else {
					System.out.println("please choose the option from 1-3");
					break;
				}			
				
//				System.out.println("Enter the new name");
//				String eName = in.next();
//				System.out.println("Enter the new age");
//				int eAge = in.nextInt();
//				System.out.println("Enter the new place");
//				String ePlace = in.next();			
//				employee=new Employee(EId, eName, eAge, ePlace);
//				String all = target.path("update").request().post(Entity.entity(employee, MediaType.APPLICATION_JSON),
//						String.class);
//				System.out.println(all);
//				break;
			}
			case 5: {
				System.out.println("Enter the employee id to search");
				int EId = in.nextInt();

				String all = target.path("search").request()
						.post(Entity.entity(EId, MediaType.APPLICATION_JSON), String.class);
				System.out.println(all);
				break;
			}
			case 6:{
				System.exit(0);
			}
			default:{
				System.out.println("please choose a correct option from 1-6");
			}
			}

		} while (c != 0);
		
	}

}

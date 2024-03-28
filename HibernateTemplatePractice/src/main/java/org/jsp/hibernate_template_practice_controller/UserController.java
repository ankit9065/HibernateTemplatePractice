package org.jsp.hibernate_template_practice_controller;

import java.util.List;
import java.util.Scanner;
import org.jsp.hibernate_template_practice.dao.UserDao;
import org.jsp.hibernate_template_practice_dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-orm.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);

			System.out.println("1. Save User");
			System.out.println("2. Update User");
			System.out.println("3. Find User by Id");
			System.out.println("4. Delete User by Id");
			System.out.println("5. Verify User by Phone & Password");
			System.out.println("6. Verify User by Email & Password");
			System.out.println("7. Verify User by Id & Password");
			System.out.println("8. Find User by Name");
			System.out.println("9. Find User by Phone");
			System.out.println("10. Find User by Email");
			System.out.println("=====Enter Choice=====");
		
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter the Details of User:");
			User user = new User();

			System.out.print("Enter Name: ");
			user.setName(sc.next());
			System.out.print("Enter Phone: ");
			user.setPhone(sc.nextLong());
			System.out.print("Enter Email: ");
			user.setEmail(sc.next());
			System.out.print("Enter Password: ");
			user.setPassword(sc.next());

			user = userDao.saveuser(user);
			System.out.println("User saved with Id: " + user.getId());
			break;
		}
		case 2: {
			System.out.println("Enter User Id to Update:");
			int userId = sc.nextInt();
			User user = userDao.findById(userId);

			if (user != null) {
				System.out.print("Enter Name: ");
				user.setName(sc.next());
				System.out.print("Enter Phone: ");
				user.setPhone(sc.nextLong());
				System.out.print("Enter Email: ");
				user.setEmail(sc.next());
				System.out.print("Enter Password: ");
				user.setPassword(sc.next());

				userDao.updateUser(user);
				System.out.println("User updated successfully.");
			} 
			else {
				System.err.println("User with ID " + userId + " not found.");
			}
			break;
		}

		case 3: {
			System.out.print("Enter User Id to find: ");
			int userId = sc.nextInt();
			User user = userDao.findById(userId);
			if (user != null) {
				System.out.println("User found: " + user);
			} 
			else {
				System.err.println("User with ID " + userId + " not found.");
			}
			break;
		}

		case 4: {
			System.out.print("Enter User Id to Delete: ");
	        int userId = sc.nextInt();
	        if (userDao.delete(userId)) {
	            System.out.println("User with ID " + userId + " deleted successfully.");
	        } 
	        else {
	            System.err.println("User with ID " + userId + " not found.");
	        }
			break;
		}
		case 5: {
			System.out.println("Verifying User by Phone & Password:");

			System.out.print("Enter Phone Number: ");
			long phoneNumber = sc.nextLong();

			System.out.print("Enter Password: ");
			String password = sc.next();

			User user = userDao.verifyUser(phoneNumber, password);
			if (user != null) {
				System.out.println("Verification Successfull " + user);
			} 
			else
				System.err.println("Invaldid Phone or Password");
			break;
		}

		case 6: {
			System.out.println("Verifying User by Email & Password:");
			System.out.print("Enter Email: ");
			String email = sc.next();
			System.out.print("Enter Password: ");
			String password = sc.next();
			User user = userDao.verifyUser(email, password);
			if (user != null) {
				System.out.println("Verification Successful " + user);
			} 
			else {
				System.err.println("Invalid Email or Password");
			}
			break;
		}

		case 7: {
			System.out.println("Verifying User by Id & Password:");
			System.out.print("Enter User Id: ");
			int userId = sc.nextInt();
			System.out.print("Enter Password: ");
			String password = sc.next();
			User user = userDao.verifyUser(userId, password);
			if (user != null) {
				System.out.println("Verification Successful " + user);
			} 
			else {
				System.err.println("Invalid User Id or Password");
			}
			break;
		}

		case 8: {
		    System.out.print("Enter User Name: ");
		    String name = sc.next();
		    List<User> users = userDao.findUserByName(name); 
		    if (!users.isEmpty()) { 
		        for (User user : users) { 
		            System.out.println(user);
		        }
		    } 
		    else { 
		        System.err.println("No users found with name '" + name + "'."); 
		    }
		    break; 
		}

		case 9: {
			System.out.print("Enter User Phone Number: ");
			long phone = sc.nextLong();
			List<User> users = userDao.findUserByPhone(phone);
			if (!users.isEmpty()) {
				for(User user : users) {
				System.out.println(users);
				}
			} 
			else {
				System.err.println("No users found with phone number '" + phone + "'.");
			}
			break;
		}

		case 10: {
			System.out.print("Enter User Email: ");
			String email = sc.next();
			List<User> users = userDao.findUserByEmail(email);
			if (!users.isEmpty()) {
				System.out.println("users: " + users);
			} 
			else {
				System.err.println("No users found with email '" + email + "'.");
			}
			break;
		}
		default: {
			sc.close();
			((ClassPathXmlApplicationContext) context).close();
			System.err.println("Invalid Choice");
			}
		}
	}
}

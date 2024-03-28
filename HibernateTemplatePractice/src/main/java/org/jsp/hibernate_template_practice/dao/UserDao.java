package org.jsp.hibernate_template_practice.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.jsp.hibernate_template_practice_dto.User;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class UserDao {
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public User saveuser(User user) {
		hibernateTemplate.save(user);
		return user;
	}
	
	@Transactional
	public User updateUser(User user) {
		User dbUser = hibernateTemplate.get(User.class, user.getId());
		if(dbUser != null) {
			dbUser.setEmail(user.getEmail());
			dbUser.setName(user.getName());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());
			hibernateTemplate.update(dbUser);
			return user;
		}
		return null;
	}
	
	public User findById(int id) {
		return hibernateTemplate.get(User.class, id);
	}
	
	@Transactional
	public boolean delete(int id) {
		User user = findById(id);
		if(user != null) {
			hibernateTemplate.delete(user);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings(value = {"deprecation", "unchecked"})
	public User verifyUser(long phone, String password) {
		List<User> users = (List<User>) hibernateTemplate.find("select u from User u where u.phone =?0 and u.password =?1", phone, password);
		if(users.isEmpty())
			return null;
		return users.get(0);
	}
	
	@SuppressWarnings(value = {"deprecation", "unchecked"})
	public User verifyUser(String email, String password) {
	    List<User> users = (List<User>) hibernateTemplate.find("select u from User u where u.email = ?0 and u.password = ?1", email, password);
	    if (users.isEmpty())
	        return null;
	    return users.get(0);
	}

	@SuppressWarnings(value = {"deprecation", "unchecked"})
    public User verifyUser(int id, String password) {
        List<User> users = (List<User>) hibernateTemplate.find("select u from User u where u.id = ?0 and u.password = ?1",id, password);
        if (users.isEmpty())
            return null;
        return users.get(0);
    }

	@SuppressWarnings(value = {"deprecation", "unchecked"})
	public List<User> findUserByName(String name) {
	    return (List<User>) hibernateTemplate.find("select u from User u where u.name = ?0", name);
	}

	@SuppressWarnings(value = {"deprecation", "unchecked"})
	public List<User> findUserByPhone(long phone) {
	    return (List<User>) hibernateTemplate.find("select u from User u where u.phone = ?0", phone);
	}

	@SuppressWarnings(value = {"deprecation", "unchecked"})
	public List<User> findUserByEmail(String email) {
	    return (List<User>) hibernateTemplate.find("select u from User u where u.email = ?0", email);
	}

	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
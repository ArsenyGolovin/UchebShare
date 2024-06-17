package ucheb_share.Repositories;

import org.springframework.data.repository.CrudRepository;

import ucheb_share.Entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findByName(String name);
	public User save(User user);
}

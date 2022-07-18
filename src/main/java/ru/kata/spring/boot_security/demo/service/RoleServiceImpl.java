package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class RoleServiceImpl implements Service<Role>{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findById(Long id) {
		return roleRepository.getById(id);
	}

	@Override
	public void save(Role user) {

	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public List<Role> getAll() {
		return roleRepository.findAll();
	}

	@Override
	public void update(Role user) {

	}
}

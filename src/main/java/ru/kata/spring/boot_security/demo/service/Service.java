package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface Service<T> {

	public T findById(Long id);

	public void save(T user);

	public void delete(Long id);

	public List<T> getAll();

	public void  update(T user);
}

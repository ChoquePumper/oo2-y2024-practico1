package oo2.practico1.ejercicio3.accesos;

import java.util.List;

public interface ObjetoDAO<E> {

	void create(E obj);

	void update(E obj);

	void remove(String id);

	void remove(E obj);

	E find(String id);

	List<E> findAll();
}

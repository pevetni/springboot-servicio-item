package com.formacionbdi.springboot.app.item.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.clientes.IProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.services.IItemService;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements IItemService{

	@Autowired
	private IProductoClienteRest productoClienteRest;
	
	@Override
	public List<Item> findAll() {
		return productoClienteRest.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(productoClienteRest.detalle(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return productoClienteRest.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return productoClienteRest.editar(producto, id);
	}

	@Override
	public void delete(Long id) {
		productoClienteRest.eliminar(id);
	}

}

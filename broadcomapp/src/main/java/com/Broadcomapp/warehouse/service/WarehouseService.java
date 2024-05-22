package com.Broadcomapp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Broadcomapp.warehouse.beans.Warehouse;
import com.Broadcomapp.warehouse.repository.WarehouseRepository;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseRepository wareRepo;

	public void createWareHouse(Warehouse warehouse) {
		wareRepo.save(warehouse);
	}
}

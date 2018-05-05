package com.lin.common.spi.service;

import com.lin.common.spi.HelloService;

public class DogService implements HelloService{

	@Override
	public void hello() {
		System.out.println("Hello Dog");
		
	}

}

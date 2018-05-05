package com.lin.common.spi;

import java.util.ServiceLoader;

public class SPIMain {
	
	public static void main(String[] args) {

        ServiceLoader<HelloService> loaders = 
              ServiceLoader.load(HelloService.class);

        for (HelloService in : loaders) {
            in.hello();
        }
    }

}

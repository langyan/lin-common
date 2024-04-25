package com.lin.common.spi;

import java.util.List;
import java.util.ServiceLoader;

import org.springframework.core.io.support.SpringFactoriesLoader;

public class SPIMain {

    public static void main(String[] args) {

        ServiceLoader<HelloService> loaders = ServiceLoader.load(HelloService.class);

        for (HelloService in : loaders) {
            in.hello();
        }

        List<HelloService> services = SpringFactoriesLoader.loadFactories(HelloService.class, null);
        for (HelloService service : services) {
            // in.hello();
        }
    }

}

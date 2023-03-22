package com.caito.inventarioservice;

import com.caito.inventarioservice.entity.Inventario;
import com.caito.inventarioservice.repository.InventarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioServiceApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner loadData(InventarioRepository inventarioRepository){
		return args -> {
			Inventario inventario = new Inventario();
			inventario.setCodigoSku("7898765544");
			inventario.setCantidad(100);
			Inventario inventario1 = new Inventario();
			inventario1.setCodigoSku("33445564322");
			inventario1.setCantidad(0);
			inventarioRepository.save(inventario);
			inventarioRepository.save(inventario1);
		};
	}*/
}

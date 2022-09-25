package com.gatway.jposclient;

import org.jpos.iso.ISOUtil;
import org.jpos.q2.Q2;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.jpos.iso.MUX;

@SpringBootApplication
public class JposClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JposClientApplication.class, args);
	}

	@Bean
	public Q2 q2(){
		Q2 q2 = new Q2();
		q2.start();
		return q2;
	}

	@Bean
	public MUX mux(Q2 q2) throws NameRegistrar.NotFoundException {
		while (!q2.ready()){
			ISOUtil.sleep(10);
		}
		return QMUX.getMUX("my-mux");
	}

}

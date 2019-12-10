package br.com.phgf.fiwapi;

import br.com.phgf.fiwapi.enumeration.EnumEnvironmentVariable;
import br.com.phgf.fiwapi.system.Version;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Base64;

@Configuration
@EnableAsync
@SpringBootApplication
public class App {

    public static final Version VERSION = new Version("1.0.0");
    public static String authorization;


    public static void main(String[] args) {
        EnumEnvironmentVariable.checkEnvironmentVariables();
        String notEncoded = EnumEnvironmentVariable.FIWAPI_USER.getValue() + ":" + EnumEnvironmentVariable.FIWAPI_PASSWORD.getValue();
        authorization = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes());
        SpringApplication.run(App.class, args);
    }

}

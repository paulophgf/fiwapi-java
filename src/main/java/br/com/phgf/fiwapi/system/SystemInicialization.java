package br.com.phgf.fiwapi.system;

import br.com.phgf.fiwapi.App;
import br.com.phgf.fiwapi.enumeration.EnumEnvironmentVariable;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Configuration
public class SystemInicialization {

    @PostConstruct
    public void inicialization() {
        Locale.setDefault(new Locale("pt", "BR"));
        showSystemInfo();
    }

    private void showSystemInfo() {
        System.out.println("  _____ _                     _ ");
        System.out.println(" |  ___(_)_      ____ _ _ __ (_)");
        System.out.println(" | |_  | \\ \\ /\\ / / _` | '_ \\| |");
        System.out.println(" |  _| | |\\ V  V / (_| | |_) | |");
        System.out.println(" |_|   |_| \\_/\\_/ \\__,_| .__/|_|");
        System.out.println("                       |_|      ");
        System.out.println("#-------- SYSTEM STARTED --------#");
        System.out.println("Current Date: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        System.out.println("Running on port: " + EnumEnvironmentVariable.FIWAPI_PORT.getValue());
        System.out.println("Version: " + App.VERSION.get());
        System.out.println("Service Enabled: " + EnumEnvironmentVariable.ENABLE_SERVICE_DISCOVERY.getValue());
    }

}

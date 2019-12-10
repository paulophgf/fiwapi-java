package br.com.phgf.fiwapi.business;

import br.com.phgf.fiwapi.system.FiwapiException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

@Service
public class OSBusiness {

    public String sendCommand(Integer timeout, String... command) {
        return sendCommand(true, timeout, command);
    }

    public String sendCommand(boolean waitToResponse, long timeout, String... command) {
        String result = null;
        try {
            ProcessBuilder ps = new ProcessBuilder(command);
            ps.redirectErrorStream(true);
            Process pr = ps.start();
            if(waitToResponse) {
                try (InputStream inputStream = pr.getInputStream(); InputStreamReader isr = new InputStreamReader(inputStream); BufferedReader in = new BufferedReader(isr)) {
                    StringBuilder content = new StringBuilder();

                    String line;
                    while ((line = in.readLine()) != null) {
                        if(line != null) {
                            content.append(line);
                        }
                    }
                    pr.waitFor(timeout, TimeUnit.SECONDS);

                    result = content.toString();
                    if (result == null || result.isEmpty()) {
                        result = "OK";
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new FiwapiException("Error to execute OS command");
        }

        return result;
    }

}


package br.com.phgf.fiwapi.business;

import br.com.phgf.fiwapi.dto.ParamDTO;
import br.com.phgf.fiwapi.dto.SubparamDTO;
import br.com.phgf.fiwapi.enumeration.EnumTable;
import br.com.phgf.fiwapi.system.FiwapiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IPv4RulesBusiness {

    @Autowired
    private OSBusiness osBusiness;


    public void addRule(EnumTable table, String chain, ParamDTO param, Boolean isAppend, Integer inputPosition) {
        ArrayList<String> command = new ArrayList<>();
        command.add("iptables");

        command.add("-t");
        command.add(table.getValue());

        if(isAppend) {
            command.add("-A");
            command.add(chain);
        } else {
            command.add("-I");
            command.add(chain);
            if(inputPosition != null && inputPosition != 0) {
                command.add(inputPosition.toString());
            }
        }

        createCommand(command, param);
    }

    public void checkRule(EnumTable table, String chain, ParamDTO param) {
        ArrayList<String> command = new ArrayList<>();
        command.add("iptables");

        command.add("-t");
        command.add(table.getValue());

        command.add("--check");
        command.add(chain);

        createCommand(command, param);
    }

    public void deleteRule(EnumTable table, String chain, ParamDTO param) {
        ArrayList<String> command = new ArrayList<>();
        command.add("iptables");

        command.add("-t");
        command.add(table.getValue());

        command.add("-D");
        command.add(chain);

        createCommand(command, param);
    }

    private void createCommand(ArrayList<String> command, ParamDTO param) {
        if(param.getAction() == null) {
            throw new FiwapiException("The parameter \"action\" is required");
        }
        command.add("-j");
        command.add(param.getAction());

        if(param.getInInterface() != null) {
            command.add("-i");
            command.add(param.getInInterface());
        }

        if(param.getOutInterface() != null) {
            command.add("-o");
            command.add(param.getOutInterface());
        }

        if(param.getProtocol() != null) {
            command.add("-p");
            command.add(param.getProtocol());
        }

        if(param.getSource() != null) {
            command.add("-s");
            command.add(param.getSource());
        }

        if(param.getSourcePorts() != null) {
            command.add("-m");
            command.add("multiport");
            command.add("--sports");
            command.add(param.getSourcePorts());
        }

        if(param.getDestination() != null) {
            command.add("-d");
            command.add(param.getDestination());
        }

        if(param.getDestinationPorts() != null) {
            command.add("-m");
            command.add("multiport");
            command.add("--dports");
            command.add(param.getDestinationPorts());
        }

        if(param.getSubparams() != null && !param.getSubparams().isEmpty()) {
            for(SubparamDTO subparam : param.getSubparams()) {
                command.add(subparam.getName());
                command.add(subparam.getValue());
            }
        }

        String[] commandArray = new String[command.size()];
        commandArray = command.toArray(commandArray);

        String response = osBusiness.sendCommand(5, commandArray);
        if(!"OK".equals(response)) {
            throw new FiwapiException(response);
        }
    }

}

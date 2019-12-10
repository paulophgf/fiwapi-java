package br.com.phgf.fiwapi.dto;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParamDTO implements Serializable {

    private static final long serialVersionUID = -9074528853828516688L;

    private String inInterface;
    private String outInterface;
    private String protocol;
    private String source;
    private String destination;
    private String sourcePorts;
    private String destinationPorts;
    private String action;
    private List<Map<String, String>> subparams;



    public String getInInterface() {
        return inInterface;
    }

    public void setInInterface(String inInterface) {
        this.inInterface = inInterface;
    }

    public String getOutInterface() {
        return outInterface;
    }

    public void setOutInterface(String outInterface) {
        this.outInterface = outInterface;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSourcePorts() {
        return sourcePorts;
    }

    public void setSourcePorts(String sourcePorts) {
        this.sourcePorts = sourcePorts;
    }

    public String getDestinationPorts() {
        return destinationPorts;
    }

    public void setDestinationPorts(String destinationPorts) {
        this.destinationPorts = destinationPorts;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Map<String, String>> getSubparams() {
        return subparams;
    }

    public void setSubparams(List<Map<String, String>> subparams) {
        this.subparams = subparams;
    }

    public void setSubparams(String subparamsJson) {
        if(subparamsJson != null && !subparamsJson.isEmpty()) {
            ArrayList<Map<String, String>> subparams = new Gson().fromJson(subparamsJson, ArrayList.class);
            this.subparams = subparams;
        }
    }

}

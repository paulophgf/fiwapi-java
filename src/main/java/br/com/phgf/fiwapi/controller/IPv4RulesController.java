package br.com.phgf.fiwapi.controller;

import br.com.phgf.fiwapi.business.IPv4RulesBusiness;
import br.com.phgf.fiwapi.dto.ParamDTO;
import br.com.phgf.fiwapi.enumeration.EnumTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("ipv4RulesController")
@RequestMapping("/api/v4/")
public class IPv4RulesController {

    @Autowired
    private IPv4RulesBusiness iPv4RulesBusiness;



    @ResponseBody
    @RequestMapping(value = "/check/{table}/{chain}", method = RequestMethod.POST)
    public String check(@PathVariable("table") String tableName, @PathVariable("chain") String chain, @RequestBody ParamDTO param) {
        EnumTable table = EnumTable.get(tableName);
        iPv4RulesBusiness.checkRule(table, chain, param);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/add/{table}/{chain}", method = RequestMethod.POST)
    public String addAppend(@PathVariable("table") String tableName, @PathVariable("chain") String chain, @RequestBody ParamDTO param) {
        EnumTable table = EnumTable.get(tableName);
        iPv4RulesBusiness.addRule(table, chain, param, true, null);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/add/{table}/{chain}/{position}", method = RequestMethod.POST)
    public String addInput(@PathVariable("table") String tableName, @PathVariable("chain") String chain, @PathVariable("position") Integer position, @RequestBody ParamDTO param) {
        EnumTable table = EnumTable.get(tableName);
        iPv4RulesBusiness.addRule(table, chain, param, false, position);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{table}/{chain}", method = RequestMethod.POST)
    public String delete(@PathVariable("table") String tableName, @PathVariable("chain") String chain, @RequestBody ParamDTO param) {
        EnumTable table = EnumTable.get(tableName);
        iPv4RulesBusiness.deleteRule(table, chain, param);
        return "OK";
    }

}

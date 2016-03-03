package com.teamtrade.rfp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/graph")
public class GraphController {
	
//    @Autowired
//    ActorService actorService;
//    
//    @Autowired
//    RelationService relationService;
    
    
    @RequestMapping(value = { "/graph/default" }, method = RequestMethod.GET)
    public String show(ModelMap model) { 
        return "default";
    }
    
    @RequestMapping(value = { "/graph/actor/new" }, method = RequestMethod.GET)
    public String newActor(ModelMap model) {
        return "actor/new";
    }
    
    @RequestMapping(value = { "/graph/relation/new" }, method = RequestMethod.POST)
    public String newRelation(ModelMap model) {
        return "relation/new";
    }
    
    @RequestMapping(value = { "/graph/actor/update" }, method = RequestMethod.GET)
    public String updateActor(ModelMap model) {
        return "actor/update";
    }
    
    @RequestMapping(value = { "/graph/relation/update" }, method = RequestMethod.POST)
    public String updateRelation(ModelMap model) {
        return "relation/update";
    }
    
    @RequestMapping(value = { "/graph/actor/delete" }, method = RequestMethod.GET)
    public String deleteActor(ModelMap model) {
        return "actor/delete";
    }
    
    @RequestMapping(value = { "/graph/relation/delete" }, method = RequestMethod.GET)
    public String deleteRelation(ModelMap model) {
        return "relation/delete";
    }
}
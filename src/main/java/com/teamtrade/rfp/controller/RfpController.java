package com.teamtrade.rfp.controller;

import static com.teamtrade.rfp.enums.ActorTypeEnum.COMPANY;
import static com.teamtrade.rfp.enums.ActorTypeEnum.PERSON;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.teamtrade.rfp.enums.ActorTypeEnum;
import com.teamtrade.rfp.model.Actor;
import com.teamtrade.rfp.model.ActorData;
import com.teamtrade.rfp.model.Person;
import com.teamtrade.rfp.model.Relation;
import com.teamtrade.rfp.model.Rfp;
import com.teamtrade.rfp.service.ActorService;
import com.teamtrade.rfp.service.RelationService;
import com.teamtrade.rfp.service.RfpService;

@Controller
@RequestMapping("/rfp")
public class RfpController {

    @Autowired
    RfpService rfpService;
    
    @Autowired
    ActorService actorService;
    
    @Autowired
    RelationService relationService;

    
    private static final String VIEW_GRAPH = "graph/index"; 
    
    private static final Gson gson = new Gson();
    
 
    @RequestMapping( value = {"/{id}"}, method = RequestMethod.GET)     
    public String get(@PathVariable String id, ModelMap model) {
    	Rfp rfp = rfpService.findById(Integer.parseInt(id));
    	Set<Actor> actors = rfp.getActors();
    	Set<Relation> relations = rfp.getRelations();
    	model.addAttribute("actors", gson.toJson(actors));
    	model.addAttribute("relations", gson.toJson(relations));
    	model.addAttribute("rfpId", id);
    	model.addAttribute("clientName", rfp.getClient().getCompany().getName());
    	model.addAttribute("rfpName", rfp.getName());
    	model.addAttribute("actorTypes", actorService.getAllActorTypes());
    	model.addAttribute("actorRoles", actorService.getAllActorRoles());
    	model.addAttribute("appreciations", actorService.getAllAppreciations());
    	model.addAttribute("relationTypes", relationService.getAllRelationTypes());
    	model.addAttribute("relationQualities", relationService.getAllRelationQualities());
    	model.addAttribute("civilities", actorService.getCivilities());
    	model.addAttribute("persons", actorService.getAllPersons());
    	return VIEW_GRAPH;
    }
    
    @RequestMapping( value = {"/{rfpId}/newActor"}, method = RequestMethod.POST)
    @ResponseBody
    public  String newActor(@PathVariable String rfpId, @RequestBody String data){
    	System.out.println("Asking for new id");
    	System.out.println(data);  
    	ActorData actorData = gson.fromJson(data, ActorData.class);
    	ActorTypeEnum type = actorData.getActorType().getActorTypeId() == PERSON.id ? PERSON: COMPANY;
    	
    	Actor actor = (Actor) gson.fromJson(data, type.model);
    	
    	Integer actorId = actorService.saveActor(actor);  
    	if(actorId != null){
    		actor.setActorId(actorId);
    		Rfp rfp = rfpService.findById(Integer.parseInt(rfpId));
    		if(rfp != null){
    			rfp.getActors().add(actor);
            	rfpService.saveRfp(rfp);
    		}
    	}else{
    		System.out.println("save actor failed");
    	}
    	    	
    	return  gson.toJson(actor);
    }
//    
//    @RequestMapping( value = {"/{id}/editActor/{actor_id}"}, method = RequestMethod.POST)
//    @ResponseBody
//    public  String editActor(@PathVariable String id, @PathVariable String actor_id,  @RequestBody String data){
//    	System.out.println("Editing" + actor_id);
//    	System.out.println(data);    	
//    	Actor a = actorService.findById(Integer.parseInt(actor_id));   	
//    	Actor updatingActor = gson.fromJson(data, Actor.class);
//    	System.out.println(updatingActor);  
//    	a.setName(updatingActor.getName());
//    	a.setTitle(updatingActor.getTitle());
//    	a.setType(updatingActor.getType());
//    	a.setNoteteamtrade(updatingActor.getNoteteamtrade());   	
//    	System.out.println(a);    	    	
//    	actorService.updateActor(a);
//    	return  a.getId().toString();
//    }
//    
//    
//    @RequestMapping( value = {"/{id}/deleteActor/{actor_id}"}, method = RequestMethod.GET)
//    public  String deleteActor(@PathVariable String id, @PathVariable String actor_id){
//    	System.out.println("Deleting" + actor_id);    	
//    	Rfp rfp  = rfpService.findById(Integer.parseInt(id));
//    	Actor a = actorService.findById(Integer.parseInt(actor_id));
//    	rfp.getActors().remove(a);    	
//    	rfpService.saveRfp(rfp);
//    	return "rfp.jsp";
//    }
//    
//    
//    @RequestMapping( value = {"/{id}/newRelation"}, method = RequestMethod.POST)
//    @ResponseBody
//    public  String newRelation(@PathVariable String id, @RequestBody String data){
//    	System.out.println("Creating for new relation");
//    	System.out.println(data);    	
//    	Relation r  = gson.fromJson(data, Relation.class);    	
//    	Integer id_r = relationService.save(r);
//    	r.setId(id_r);    	
//    	Rfp rfp = rfpService.findById(Integer.parseInt(id));
//    	rfp.getRelations().add(r);
//    	rfpService.saveRfp(rfp);
//    	return  id_r.toString();
//    }
//    
//    @RequestMapping( value = {"/{id}/editRelation/{relation_id}"}, method = RequestMethod.POST)
//    @ResponseBody
//    public  String editRelation(@PathVariable String id, @PathVariable String relation_id,  @RequestBody String data){
//    	System.out.println("Editing" + relation_id);
//    	System.out.println(data);    	
//    	Relation r = relationService.findById(Integer.parseInt(relation_id));   	
//    	Relation updatingRelation = gson.fromJson(data, Relation.class);
//    	System.out.println(updatingRelation);  
//    	r.setLabel(updatingRelation.getLabel());
//    	r.setTitle(updatingRelation.getTitle());
//    	r.setColor(updatingRelation.getColor());
//    	r.setDashes(updatingRelation.getDashes());
//    	r.setActor_from_id(updatingRelation.getActor_from_id());
//    	r.setActor_to_id(updatingRelation.getActor_to_id());
//    	r.setArrows(updatingRelation.getArrows());
//    	System.out.println(r);    	    	
//    	relationService.update(r);
//    	return  r.getId().toString();
//    }
//    
//    @RequestMapping( value = {"/{id}/deleteRelation/{relation_id}"}, method = RequestMethod.GET)
//    public  String deleteRelation(@PathVariable String id, @PathVariable String relation_id){
//    	System.out.println("Deleting" + relation_id);    	
//    	Rfp rfp  = rfpService.findById(Integer.parseInt(id));
//    	Relation r = relationService.findById(Integer.parseInt(relation_id));
//    	rfp.getRelations().remove(r);    	
//    	rfpService.saveRfp(rfp);
//    	return "rfp.jsp";
//    }
}  


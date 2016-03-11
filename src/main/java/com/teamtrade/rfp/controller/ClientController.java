package com.teamtrade.rfp.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamtrade.rfp.model.Client;
import com.teamtrade.rfp.service.ClientService;
import com.teamtrade.rfp.service.RfpService;

/**
 * Client controller
 * @author Khalid.ALIANNE
 *
 */
@Controller
@RequestMapping("/")
public class ClientController {
	
	private static final String VIEW_LIST = "client/list"; 
	private static final String VIEW_NEW = "client/new"; 
	private static final String IDICATOR_1 = "In Progress";
	private static final String IDICATOR_2 = "Short Listed";
	private static final String IDICATOR_3 = "Won";
	private static final String IDICATOR_4 = "Lost";
	//private static final String VIEW_EDIT = "client/edit"; 
 
    @Autowired
    ClientService clientService;
    
    @Autowired
    RfpService rfpService;
    /*
     * This method will list all existing clients.
     */
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String listClient(ModelMap model) { 
        Set<Client> clients = clientService.getAllClients();
        Map<String, Integer> indicators = rfpService.getIndicators();
        model.addAttribute("clients", clients);
        model.addAttribute("totalClients", clients.size());
        model.addAttribute("IDICATOR_1_NAME", IDICATOR_1);
        model.addAttribute("IDICATOR_2_NAME", IDICATOR_2);
        model.addAttribute("IDICATOR_3_NAME", IDICATOR_3);
        model.addAttribute("IDICATOR_4_NAME", IDICATOR_4);
        model.addAttribute("IDICATOR_1_VALUE", indicators.get(IDICATOR_1) != null ? indicators.get(IDICATOR_1) : 0);
        model.addAttribute("IDICATOR_2_VALUE", indicators.get(IDICATOR_2) != null ? indicators.get(IDICATOR_2) : 0);
        model.addAttribute("IDICATOR_3_VALUE", indicators.get(IDICATOR_3) != null ? indicators.get(IDICATOR_3) : 0);
        model.addAttribute("IDICATOR_4_VALUE", indicators.get(IDICATOR_4) != null ? indicators.get(IDICATOR_4) : 0);
        return VIEW_LIST;
    }
 
   	
    /*
     *  This method will provide the medium to add a new client.
     */
    @RequestMapping(value = { VIEW_NEW }, method = RequestMethod.GET)
    public String newClient(ModelMap model) {
        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("edit", false);
        return VIEW_NEW;
    }
 
    /*
     * This method will be called on form submission, handling POST request for
     * saving client in database. It also validates the user input
     */
//    @RequestMapping(value = { VIEW_NEW }, method = RequestMethod.POST)
//    public String saveClient(@Valid Client client, BindingResult result, ModelMap model) {
// 
//        if (result.hasErrors()) {
//            return VIEW_NEW;
//        }
// 
        /*
         * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
         * and applying it on field [ssn] of Model class [Employee].
         * 
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         * 
         
        if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
            FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
            result.addError(ssnError);
            return "registration";
        }
         
        service.saveEmployee(employee);
 
        model.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
        return "success";
    }
 
 
    /*
     * This method will provide the medium to update an existing employee.
     */
//     
//    @RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
//    public String updateClient(@PathVariable String ssn, ModelMap model) {
//        Employee employee = service.findEmployeeBySsn(ssn);
//        model.addAttribute("employee", employee);
//        model.addAttribute("edit", true);
//        return "registration";
//    }
//     
//   
//    /*
//     * This method will delete an employee by it's SSN value.
//     */
//    @RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
//    public String deleteClient(@PathVariable String ssn) {
//        service.deleteEmployeeBySsn(ssn);
//        return "redirect:/list";
//    }
  
}
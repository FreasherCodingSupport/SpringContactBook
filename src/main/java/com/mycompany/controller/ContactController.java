package com.mycompany.controller;

import com.mycompany.domain.Contact;
import com.mycompany.service.ContactService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @RequestMapping(value="/user/contact_form")
    public String contactForm(Model m){ 
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        return "contact_form";//jsp
    }
    
    @RequestMapping(value="/user/save_contact")
    public String saveOrUpdateContact(@ModelAttribute("command") Contact c,Model m,HttpSession session){
        Integer contactId = (Integer) session.getAttribute("aContactId");
        if(contactId == null){
            //save task
        try{
           Integer userId = (Integer) session.getAttribute("userId");
           c.setUserId(userId);//fk
           contactService.save(c);
           return "redirect:clist?act=sv";
        }catch(Exception e){
           e.printStackTrace();
           m.addAttribute("err","Failed to save contact.");
           return "contact_form";//jsp
        }
        }else{
               //update task
        try{ 
           c.setContactId(contactId);//pk
           contactService.update(c);
           return "redirect:clist?act=ed";
        }catch(Exception e){
           e.printStackTrace();
           //System.out.println(""+e.getMessage());
           m.addAttribute("err","Failed to edit contact.");
           return "contact_form";//jsp
           }
        }
    }
    
    @RequestMapping(value="/user/clist")
    public String contactList(Model m,HttpSession session){
         Integer userId = (Integer)session.getAttribute("userId");
         m.addAttribute("contactList",contactService.findUserContact(userId));
         return "clist";//jsp
    }
    
    @RequestMapping(value="/user/contact_search")
    public String contactSearch(Model m,HttpSession session,@RequestParam("freeText")String freeText){
         Integer userId = (Integer)session.getAttribute("userId");
         m.addAttribute("contactList",contactService.findUserContact(userId,freeText));
         return "clist";//jsp
    }
    
    @RequestMapping(value="/user/edit_contact")
    public String prepareEditForm(Model m,HttpSession session,@RequestParam("cid") Integer contactId){
        session.setAttribute("aContactId",contactId);
        Contact c = contactService.findById(contactId);
        m.addAttribute("command", c);
        return "contact_form";//jsp
    }
    
    @RequestMapping(value="/user/del_contact")
    public String delContact(@RequestParam("cid") Integer contactId){
         contactService.delete(contactId);
         return "redirect:clist?act=del";
    }
    
    @RequestMapping(value="/user/bulk_cdelete")
    public String deleteBulkContact(@RequestParam("cid") Integer[] contactIds){
         contactService.delete(contactIds);
         return "redirect:clist?act=del";
    }
}

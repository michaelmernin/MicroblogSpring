package com.theironyard;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by michaelmernin on 12/22/16.
 */

@org.springframework.stereotype.Controller
public class Controller {

    ArrayList<Message> submitted = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("submitted", submitted);
        model.addAttribute("userName", session.getAttribute("userName"));

        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String each(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
        return "redirect:/" ;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String hey(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String sub(String messageText) {
        //session.setAttribute("messageText", text);
        Message mess = new Message();

        mess.text = messageText;
        mess.id = submitted.size() + 1;
        submitted.add(mess);
        return "redirect:/";
    }


    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMess(Integer deleteMessage) {

        Message me = new Message();
        for(Message submit : submitted) {
            if (deleteMessage == submit.id) {
                me = submit;
            }

        }

        submitted.remove(me);


        return "redirect:/";
    }
}

package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.entity.Invite;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.responseForm.UserResponseForm;
import com.Kh033Java.travelbook.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/invite")
public class InviteController {

    private final InviteService inviteService;

    @Autowired
    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invite createInvite(@PathVariable("id") Long id, @RequestBody final List<UserResponseForm> users){
        return inviteService.createInvite(id,users);
    }
}

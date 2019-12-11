package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.Invite;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.responseForm.UserResponseForm;

import java.util.List;
import java.util.Set;

public interface InviteService {

    Invite createInvite(Long planId, List<UserResponseForm> users);

    List<User> getAllUsersForInvite(String login);

    String acceptInvite(String login, Long inviteId);

    String dismissInvite(String login, Long inviteId);

    boolean checkOnConfirm(Long planId, Invite invite);
}

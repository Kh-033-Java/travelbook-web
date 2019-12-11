package com.Kh033Java.travelbook.service.impl;

import com.Kh033Java.travelbook.entity.Invite;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.repository.InviteRepository;
import com.Kh033Java.travelbook.repository.PlanRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.responseForm.UserResponseForm;
import com.Kh033Java.travelbook.service.InviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InviteServiceImpl implements InviteService {

    private Logger logger = LoggerFactory.getLogger(InviteServiceImpl.class);
    private final InviteRepository inviteRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;


    @Autowired
    public InviteServiceImpl(InviteRepository inviteRepository, PlanRepository planRepository, UserRepository userRepository) {
        this.inviteRepository = inviteRepository;
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Invite createInvite(Long planId, List<UserResponseForm> users) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Such Plan not Found"));
        Invite invite = inviteRepository.findInviteByPlanId(planId).orElse(new Invite());
        List<User> invitedUsersList = new ArrayList<>();

        if (invite.getId() != null) {
            invitedUsersList = inviteRepository.findAllInvitedUsers(invite.getId());

            for (User alreadyInvitedUser : invitedUsersList) {
                users.removeIf(invitedUser -> invitedUser.getLogin().equals(alreadyInvitedUser.getLogin()));
            }

        }
        for (UserResponseForm user : users) {
            User invitedUser = userRepository.findByLogin(user.getLogin())
                    .orElseThrow(() -> new RuntimeException("Such user not found"));
            invitedUsersList.add(invitedUser);
        }

        invite.setInvited(invitedUsersList);

        invite.setPlan(plan);

        return inviteRepository.save(invite);
    }

    @Override
    public List<User> getAllUsersForInvite(String login) {
        return userRepository.getFriends(login);
    }

    @Override
    public String acceptInvite(String login, Long inviteId) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Such user not found"));
        Invite invite = inviteRepository.findById(inviteId)
                .orElseThrow(() -> new RuntimeException("This invite no longer available"));


        return null;
    }

    @Override
    public String dismissInvite(String login, Long inviteId) {
        return null;
    }

    @Override
    public boolean checkOnConfirm(Long planId, Invite invite) {
        Plan plan = planRepository.findById(planId).orElseThrow(()-> new RuntimeException("Such plan not found"));
        int currentCountOfCompanions = inviteRepository.countCompanions(invite.getId());

        if(plan.getAmountOfPeople() == currentCountOfCompanions){
            List<User> users = inviteRepository.findAllJoinedUsers(invite.getId());
            for(User user: users){
                planRepository.createCompanionRelationship(planId, user.getLogin());
            }
        }
        return false;
    }
}

package com.Kh033Java.travelbook.responseForm;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class UserResponseForm {
    private String login;
    private Photo avatar;
    private int sumOfLikes;
    private int sumOfPosts;
    private Country homeland;

    private static Logger log = LoggerFactory.getLogger(UserResponseForm.class);

    public int getSumOfPosts() {
        return sumOfPosts;
    }

    public void setSumOfPosts(int sumOfPosts) {
        this.sumOfPosts = sumOfPosts;
    }

    public int getSumOfLikes() {
        return sumOfLikes;
    }

    public void setSumOfLikes(int sumOfLikes) {
        this.sumOfLikes = sumOfLikes;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Photo getAvatar() {
        return avatar;
    }

    public void setAvatar(Photo avatar) {
        this.avatar = avatar;
    }

    public Country getHomeland() {
        return homeland;
    }

    public void setHomeland(Country homeland) {
        this.homeland = homeland;
    }

    public static final Comparator<UserResponseForm> COMPARE_BY_SUM_OF_LIKES = new Comparator<UserResponseForm>() {
        @Override
        public int compare(UserResponseForm lhs, UserResponseForm rhs) {
            log.info( "Sum of likes 1: {}", lhs.getSumOfLikes());
            log.info( "Sum of likes 2: {}", rhs.getSumOfLikes());
            if (lhs.getSumOfLikes() == rhs.getSumOfLikes()) {
                int firstSumOfLikedPosts = lhs.getSumOfPosts();
                int secondSumOfLikedPosts = rhs.getSumOfPosts();

                return firstSumOfLikedPosts - secondSumOfLikedPosts;
            } else if (lhs.getSumOfLikes() == 0 && rhs.getSumOfLikes() == 0) {
                return 0;
            }

            return  rhs.getSumOfLikes() - lhs.getSumOfLikes();
        }
    };
}

package com.service;

import com.object.userprofile.UserProfile;
import com.object.userprofile.UserProfileRepository;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class UserService {
    private final UserProfileRepository userProfileRepository;

    private final String[] userProfileStringField = {"Username", "Password", "FirstName", "LastName", "Email", "PhoneNumber"};
    private final String[] userProfileBooleanField = {"ShowName", "ShowEmail", "ShowPhoneNumber"};

    @Autowired
    public UserService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    private String[] getUserProfileStringField(){
        return this.userProfileStringField;
    }

    private String[] getuserProfileBooleanField(){
        return this.userProfileBooleanField;
    }

    public boolean checkUserNameExists(String username){
        return userProfileRepository.existsByUsername(username);
    }


    // UserProfile support
    public boolean checkUserLogin(String username, String password){
        return userProfileRepository.existsByUsernameAndPassword(username, password);
    }


    public void createUserProfile(String username, String password, String firstName, String lastName,  String email, String phoneNumber,
                                  boolean showName, boolean showEmail, boolean showPhoneNumber){
        userProfileRepository.save(new UserProfile(username, password, firstName, lastName, email,
                                                   phoneNumber, showName, showEmail, showPhoneNumber));
        userProfileRepository.flush();
    }

    public void createUserProfile(UserProfile userProfile){
        userProfileRepository.save(userProfile);
        userProfileRepository.flush();
    }

    public UserProfile getFullUserProfile(String username){
        return userProfileRepository.findUserProfileByUsername(username);
    }

    public UserProfile getPartUserProfile(String userName){
        UserProfile userProfile = userProfileRepository.findUserProfileByUsername(userName);
        if (!userProfile.getShowEmail()) {
            userProfile.setEmail("Not Shown!");
        }
        if (!userProfile.getShowPhoneNumber()) {
            userProfile.setPhoneNumber("Not Shown!");
        }
        if (!userProfile.getShowName()) {
            userProfile.setFirstName("Not Shown!");
            userProfile.setLastName("Not Shown!");
        }

        return userProfile;
    }
    private boolean checkUserProfileEmail(String email){
        return userProfileRepository.existsByEmail(email);
    }

    private boolean checkUserProfilePhoneNumber(String phoneNumber){
        return userProfileRepository.existsByPhoneNumber(phoneNumber);
    }

    public void updateUserProfile(int id, String username, String password, String firstName,
                                  String lastName, String email, String phoneNumber,
                                  boolean showName, boolean showEmail, boolean showPhoneNumber){
        UserProfile userProfile = userProfileRepository.getOne(id);
        userProfile.setUsername(username);
        userProfile.setPassword(password);
        userProfile.setFirstName(firstName);
        userProfile.setLastName(lastName);
        userProfile.setEmail(email);
        userProfile.setPhoneNumber(phoneNumber);
        userProfile.setShowName(showName);
        userProfile.setShowEmail(showEmail);
        userProfile.setShowPhoneNumber(showPhoneNumber);
        userProfileRepository.save(userProfile);
    }

    public void updateUserProfile(UserProfile oldUserProfile){
        userProfileRepository.save(oldUserProfile);
    }

    public void deleteUserProfile(String username){
        userProfileRepository.deleteByUsername(username);
    }

    public String getUsernameFromRequest(JSONObject jsonObject){
        if (jsonObject == null){
            throw new IllegalArgumentException();
        }

        Object usernameObject = jsonObject.get("username");

        if (usernameObject == null) {
            throw new IllegalArgumentException();
        }

        return usernameObject.toString();
    }

    public void getUserProfileFromRequest(JSONObject jsonObject, UserProfile userProfile){
        // Profile to update
        Object tempObject;
        String tempString;
        for(int i = 0; i < this.getUserProfileStringField().length; i++)
        {
            String s = this.getUserProfileStringField()[i];
            try {
                tempObject = jsonObject.get(s.toLowerCase());
                if(tempObject != null) {
                    tempString = tempObject.toString();
                    if (s.equals("Email")){
                        if (this.checkUserProfileEmail(tempString)){
                            throw new IllegalArgumentException();
                        }
                    }

                    if (s.equals("PhoneNumber")){
                        if (this.checkUserProfilePhoneNumber(tempString)){
                            throw new IllegalArgumentException();
                        }
                    }
                    Method m = UserProfile.class.getMethod("set" + s, String.class);
                    m.invoke(userProfile, jsonObject.get(tempObject).toString());
                }
            } catch (Exception e) {
                // NoSuchMethodException, InvocationTargetException, IllegalException
                e.printStackTrace();
            }
        }

        for(int i = 0; i < this.getuserProfileBooleanField().length; i++)
        {
            String s = this.getuserProfileBooleanField()[i];
            try {
                tempObject = jsonObject.get(s.toLowerCase());
                if(tempObject != null) {
                    tempString = tempObject.toString();
                    Method m = UserProfile.class.getMethod("set" + s, Boolean.class);
                    m.invoke(userProfile, jsonObject.get(tempObject) == "true");
                }
            } catch (Exception e) {
                // NoSuchMethodException, InvocationTargetException, IllegalException
                e.printStackTrace();
            }
        }
    }

}

package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }
    
    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        return existingUser;
    }
    
    /**
     * Checks if a password contains atleast 1 alphabet, 1 number & 1 special character
     *
     * @param password
     * @return boolean returns true if all conditions are passed
     */
    public boolean isValidPassword(String password) {
        // returns false if password is empty,null or contains whitespaces
        if(StringUtils.isBlank(password)) {
            return false;
        }
        char[] passwordArray = password.toCharArray();
        boolean alphabetCheck = false;
        boolean numberCheck = false;
        boolean specialCharacterCheck = false;
        for (char character : passwordArray) {
            if (CharUtils.isAsciiAlpha(character)) {
                alphabetCheck = true;
            } else if (CharUtils.isAsciiNumeric(character)) {
                numberCheck = true;
            } else {
                specialCharacterCheck = true;
            }
        }
        return alphabetCheck && numberCheck && specialCharacterCheck;
    }
    
}

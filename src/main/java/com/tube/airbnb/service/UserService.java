package com.tube.airbnb.service;

import com.tube.airbnb.dto.*;
import com.tube.airbnb.entity.Host;
import com.tube.airbnb.entity.User;
import com.tube.airbnb.exception.BaseException;
import com.tube.airbnb.repository.UserRepository;
import com.tube.airbnb.utils.JwtService;
import com.tube.airbnb.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tube.airbnb.config.BaseResponseStatus.*;

@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    UserRepository userRepository;

    JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public GetUserRes getUser(long id) throws BaseException {
        checkJwtByUserId(id);
        User user = findByUserId(id);
        return GetUserRes.from(user);
    }

    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        if(userRepository.findByEmail(postUserReq.getEmail()).isPresent())
            throw new BaseException(POST_USERS_EXISTS_EMAIL);

        try {
            String pwd = SHA256.encrypt(postUserReq.getPassword());
            postUserReq.setPassword(pwd);
        }
        catch (Exception e){
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            User user = userRepository.save(postUserReq.toEntity());
            long userId = user.getId();
            String jwt = jwtService.createJwt(userId);
            return new PostUserRes(jwt, userId);
        }
        catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public void deleteById(long id) throws BaseException{
        checkJwtByUserId(id);
        User user = findByUserId(id);
        user.setStatus(1);
        userRepository.save(user);
    }

    public void updateUser(long id, PatchUserReq patchUserReq) throws BaseException{
        checkJwtByUserId(id);

        User user = findByUserId(id);

        user.setName(patchUserReq.getName());
        user.setPhoneNumber(patchUserReq.getPhoneNumber());
        user.setAddress(patchUserReq.getAddress());
        user.setEmergencyContact(patchUserReq.getEmergencyContact());

        userRepository.save(user);
    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException{
        User user = userRepository.findByEmail(postLoginReq.getEmail())
                .orElseThrow(()->new BaseException(USERS_EMPTY_USER_EMAIL));

        String encryptPwd;
        try {
            encryptPwd= SHA256.encrypt(postLoginReq.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if(user.getPassword().equals(encryptPwd)){
            long userId = user.getId();
            String jwt = jwtService.createJwt(userId);
            return new PostLoginRes(jwt,userId);
        }
        else{
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

    public void checkJwtByUserId(long id) throws BaseException {
        long userIdxByJwt = jwtService.getUserIdx();
        if(id != userIdxByJwt){
            throw new BaseException(INVALID_USER_JWT);
        }
    }

    public User findByUserId(long userId) throws BaseException{
        return userRepository.findById(userId)
                .orElseThrow(()->new BaseException(USERS_EMPTY_USER_ID));
    }

    public User findByHostId(long hostId) throws BaseException{
        return userRepository.findByHostId(hostId)
                .orElseThrow(()->new BaseException(USERS_EMPTY_USER_ID));
    }

    public void checkAlreadyHost(long userId) throws BaseException{
        if(findByUserId(userId).getHost() != null)
            throw new BaseException(HOSTS_ALREADY_HOST);
    }

    public void setHost(User user, Host host){
        user.setHost(host);
        userRepository.save(user);
    }

}

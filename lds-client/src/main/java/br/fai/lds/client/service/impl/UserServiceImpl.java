package br.fai.lds.client.service.impl;

import br.fai.lds.client.service.RestService;
import br.fai.lds.client.service.UserService;
import br.fai.lds.model.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService<UserModel> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndPoint(String resource) {
        return  BASE_ENDPOINT + resource;
    }


    @Autowired
    private RestService restSerice;

    @Override
    public int craete(UserModel entity) {
        return 0;
    }

    @Override
    public List<UserModel> find() {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> httpEntity = new HttpEntity<>("");

        String resource = "user/find";

        try {
            ResponseEntity<UserModel[]> responseEntity = restTemplate.exchange(buildEndPoint(resource), HttpMethod.GET, httpEntity, UserModel[].class);

            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                return null;
            }
            return Arrays.asList(responseEntity.getBody());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel findById(int id) {
        return null;
    }

    @Override
    public boolean update(int id, UserModel entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public UserModel validateUserNameAndPassword(String username, String password) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> httpEntity = new HttpEntity<>("");

        String resource = "account/login?username=" + username + "&password="  + password;

        ResponseEntity<UserModel> responseEntity = restTemplate.exchange(buildEndPoint(resource), HttpMethod.POST, httpEntity, UserModel.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return null;
        }

        UserModel user = responseEntity.getBody();

        return user;
    }
}
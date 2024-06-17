package com.example.openfeignapi.user;

@org.springframework.web.bind.annotation.RestController()
@org.springframework.web.bind.annotation.RequestMapping(value = {"/v1"})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/openfeignapi/user/UserController;", "", "userRepository", "Lcom/example/openfeignapi/user/UserRepository;", "(Lcom/example/openfeignapi/user/UserRepository;)V", "getUsers", "", "Lcom/example/openfeignapi/user/UserEntity;", "open-feign-api"})
public class UserController {
    @org.jetbrains.annotations.NotNull()
    private final com.example.openfeignapi.user.UserRepository userRepository = null;
    
    public UserController(@org.jetbrains.annotations.NotNull()
    com.example.openfeignapi.user.UserRepository userRepository) {
        super();
    }
    
    @org.springframework.web.bind.annotation.GetMapping(value = {"users"})
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.example.openfeignapi.user.UserEntity> getUsers() {
        return null;
    }
}
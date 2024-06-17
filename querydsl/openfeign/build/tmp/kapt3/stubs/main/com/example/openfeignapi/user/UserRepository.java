package com.example.openfeignapi.user;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/example/openfeignapi/user/UserRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/example/openfeignapi/user/UserEntity;", "", "Lcom/example/openfeignapi/user/CustomUserRepository;", "open-feign-api"})
public abstract interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<com.example.openfeignapi.user.UserEntity, java.lang.Long>, com.example.openfeignapi.user.CustomUserRepository {
}
package com.snatch.dto;

import com.snatch.entity.User;

import javax.validation.constraints.*;

public class UserDTO {

    private String userId;
    @NotNull(message = "{username.must}")
    @Pattern(regexp = "[a-zA-Z ]{3,20}" ,message = "{user.name.invalid}")
    private String userName;
//    @Email(message = "{email.invalid}")
//    @NotNull(message = "{email.must}")
    private String email;
//    @NotNull(message = "{phoneNumber.must}")
//    @Min(value = 1000000000L, message = "{user.phone.invalid}")
//    @Max(value = 9999999999L, message = "{user.phone.invalid}")
    private Long phoneNo;
    @NotNull(message = "{password.must}")
    @NotBlank(message = "{password.must}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$", message = "{invalid.password}")
    private String password;

    public UserDTO(String userId, String userName, String email, Long phoneNo, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public UserDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", password='" + password + '\'' +
                '}';
    }



    public static User prepareUserEntity(UserDTO userDTO){
        return new User(userDTO.getUserId(),userDTO.getUserName(),userDTO.getEmail(),userDTO.getPhoneNo(),userDTO.getPassword());
    }

    public static UserDTO prepareUserDTO(User user){
        return new UserDTO(user.getUserId(),user.getUserName(),user.getEmail(),user.getPhoneNo(),user.getPassword());
    }
}

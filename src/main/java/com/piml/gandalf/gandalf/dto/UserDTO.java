package com.piml.gandalf.gandalf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.piml.gandalf.gandalf.entity.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull
    @NotEmpty
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 14, min = 11)
    private String cpf;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @NotNull
    @NotEmpty
    private String role;

    private Long warehouseId;

    public User map() {
        return User.builder()
                .name(this.name)
                .cpf(this.cpf)
                .username(this.username)
                .password(this.password)
                .username(this.username)
                .email(this.email)
                .role(this.role)
                .warehouseId(this.warehouseId)
                .build();
    }

    public static UserDTO map(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .cpf(user.getCpf())
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .warehouseId(user.getWarehouseId())
                .build();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", cpf='" + cpf + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", warehouseId=" + warehouseId +
                '}';
    }
}

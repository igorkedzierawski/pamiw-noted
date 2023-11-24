package zet.kedzieri.noted.user.entity.dto;

import zet.kedzieri.noted.user.entity.NotedUser;

public record NotedUserDTO(long id, String name, String surname, String username) {

    public static NotedUserDTO from(NotedUser user) {
        return new NotedUserDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername()
        );
    }

}

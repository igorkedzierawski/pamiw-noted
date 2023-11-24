package zet.kedzieri.noted.user.entity.dto;

import zet.kedzieri.noted.user.entity.NotedUser;

public record NotedUserExtendedDTO(long id, String name, String surname, String username, String avatarUrl) {

    public static NotedUserExtendedDTO from(NotedUser user, String avatarUrl) {
        return new NotedUserExtendedDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                avatarUrl
        );
    }

}

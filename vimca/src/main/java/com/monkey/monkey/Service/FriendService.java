 package com.monkey.monkey.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.monkey.monkey.Repository.FriendRepository;
import com.monkey.monkey.models.AppUser;
import com.monkey.monkey.models.Friend;

public class FriendService {

	@Autowired
	private FriendRepository friendRepository;

	public Friend getFriendByUserAndFriend(Long userId, Long friendId) {
		return friendRepository.getFriendByUserAndFriend(userId, friendId, false);
	}

	public void removeFriendship(AppUser user, AppUser blockedUser) {
		Friend friend = getFriendByUserAndFriend(user.getAppUserId(), blockedUser.getAppUserId());
		if (friend != null) {
			deleteFriendship(user.getAppUserId(), blockedUser.getAppUserId(), false);

			deleteFriendship(blockedUser.getAppUserId(), user.getAppUserId(), false);
		}
	}

//to check friend remove option
	public void deleteFriendship(Long userId, Long friendId, boolean isDeleted) {
		Friend friend = getFriendByUserAndFriend(userId, friendId);

		friendRepository.delete(friend);
	}

}
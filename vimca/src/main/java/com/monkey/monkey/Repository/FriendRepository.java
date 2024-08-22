package com.monkey.monkey.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monkey.monkey.models.Friend;

public interface FriendRepository  extends JpaRepository<Friend, Long>{

	@Query("select d from Friend d where  d.user.appUserId=:userId ANDd.friend.appUserId=:friendId  AND d.deleted=:isDeleted")
	Friend getFriendByUserAndFriend(Long userId, Long friendId, boolean isDeleted);



}

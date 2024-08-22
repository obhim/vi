package com.monkey.monkey.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monkey.monkey.models.FriendRequest;

public interface FriendRequestRepository  extends JpaRepository<FriendRequest, Long> {

	@Query("select d from FriendRequest d where d.sender.appUserId=:senderId AND d.receiver.appUserId=:reciverId AND d.deleted=:isDeleted")
	FriendRequest existsByUserAndFriend(Long senderId, Long reciverId, boolean isDeleted);

	@Query("select d from FriendRequest d where  d.receiver.appUserId=:reciverId ANDd.sender.appUserId=:senderId  AND d.deleted=:isDeleted")
	FriendRequest existsByFriendAndUser(Long reciverId, Long senderId, boolean isDeleted);

	@Query("select d from FriendRequest d where  d.receiver.appUserId=:reciverId AND d.deleted=:isDeleted")
	FriendRequest getPendingFriendRequestByReciverId(Long reciverId, boolean isDeleted);

}

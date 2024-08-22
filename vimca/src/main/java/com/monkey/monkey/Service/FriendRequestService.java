package com.monkey.monkey.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.monkey.GlobelException.MyException;
import com.monkey.monkey.Repository.FriendRepository;
import com.monkey.monkey.Repository.FriendRequestRepository;
import com.monkey.monkey.enums.FriendRequestStatus;
import com.monkey.monkey.models.AppUser;
import com.monkey.monkey.models.Friend;
import com.monkey.monkey.models.FriendRequest;

@Service
public class FriendRequestService {

	@Autowired
	private FriendRequestRepository friendRequestRepository;

	@Autowired
	private AppUserService userService;

	@Autowired
	private FriendRepository friendRepository;

	@Transactional
    public FriendRequest sendFriendRequest(Long senderId, Long receiverId) {
        AppUser sender = userService.getAppUserById(senderId, false);
        if (sender == null) {
            throw new MyException("Sender not found");
        }

        AppUser receiver = userService.getAppUserById(receiverId, false);
        if (receiver == null) {
            throw new MyException("Receiver not found");
        }

        FriendRequest existingFriendRequest = findExistingFriendRequest(sender, receiver);
        if (existingFriendRequest != null) {
            return existingFriendRequest;
        }

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        return friendRequestRepository.save(friendRequest);
    }

	 private FriendRequest findExistingFriendRequest(AppUser sender, AppUser receiver) {
	        FriendRequest existsFriendRequest = friendRequestRepository.existsByUserAndFriend(sender.getAppUserId(), receiver.getAppUserId(),false);
	        if (existsFriendRequest != null) {
	            return existsFriendRequest;
	        }

	        FriendRequest existsReceivedRequest = friendRequestRepository.existsByFriendAndUser(receiver.getAppUserId(), sender.getAppUserId(),false);
	        return existsReceivedRequest;
	    }

	public void acceptFriendRequest(Long requestId) {
		FriendRequest friendRequest = friendRequestRepository.findById(requestId)
				.orElseThrow(() -> new IllegalArgumentException("Friend request not found"));

		if (friendRequest.getStatus() != FriendRequestStatus.PENDING) {
			throw new IllegalArgumentException("This friend request has already been processed");
		}

		friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
		friendRequestRepository.save(friendRequest);

		Friend friendship = new Friend();
		friendship.setUser(friendRequest.getSender());
		friendship.setFriend(friendRequest.getReceiver());
		friendRepository.save(friendship);
	}

	public FriendRequest getPendingFriendRequestByReciverId(Long reciverId) {
		
		return friendRequestRepository.getPendingFriendRequestByReciverId(reciverId,false);
	}
}

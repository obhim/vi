package com.monkey.monkey.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.monkey.GlobelException.MyException;
import com.monkey.monkey.Repository.BlockedUserRepository;
import com.monkey.monkey.models.AppUser;
import com.monkey.monkey.models.BlockedUser;
import com.monkey.monkey.models.FriendRequest;

@Service
public class BlockedUserService {

	@Autowired
	private BlockedUserRepository blockedUserRepository;

	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private FriendService friendService;

	public List<BlockedUser> getAllBlockedUserByBlockerId(Long id, boolean isDeleted) {
		return blockedUserRepository.getAllBlockedUserByBlockerId(id, false);
	}

	 @Transactional
	    public void blockUser(Long userId, Long blockedUserId) {
	        AppUser user = appUserService.getAppUserById(userId, false);
	        if (user == null) {
	            throw new MyException("User not found");
	        }

	        AppUser blockedUser = appUserService.getAppUserById(blockedUserId, false);
	        if (blockedUser == null) {
	            throw new MyException("Blocked user not found");
	        }

	        // Check if either user is already blocked
	        if (isUserBlocked(user, blockedUser)) {
	            throw new MyException("User is already blocked");
	        }
//	        if (isUserBlocked(blockedUser, user)) {
//	            throw new MyException("Cannot block a user who has already blocked you");
//	        }

	        // Remove from friends if they are friends
	        friendService.removeFriendship(user, blockedUser);

	        // Add to block list
	        addToBlockList(user, blockedUser);
	    }

	    private boolean isUserBlocked(AppUser user, AppUser blockedUser) {
	        BlockedUser existingBlock = existByUserAndBlockedUser(user, blockedUser);
	        return existingBlock != null;
	    }

	    

	    private void addToBlockList(AppUser user, AppUser blockedUser) {
	        BlockedUser blockList = new BlockedUser();
	        blockList.setBlocker(user);
	        blockList.setBlocked(blockedUser);
	        blockedUserRepository.save(blockList);
	    }

	    private BlockedUser existByUserAndBlockedUser(AppUser user, AppUser blockedUser) {
	        return blockedUserRepository.findByUserAndBlockedUser(user.getAppUserId(), blockedUser.getAppUserId(),false);
	    }
	
}

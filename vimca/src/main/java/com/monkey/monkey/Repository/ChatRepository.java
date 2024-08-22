package com.monkey.monkey.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monkey.monkey.models.Chat;

public interface ChatRepository  extends JpaRepository<Chat, Long>{

}

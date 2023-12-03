package com.dgu.wantToGraduate.domain.chat.service;

import com.dgu.wantToGraduate.domain.chat.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatService {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;

    public static HashMap<String, List<Long>> matchedUser = new HashMap<>();

    public String findRoomIdByUserId(Long userId) {
        for (String roomId : matchedUser.keySet()) {
            for (Long findingId : matchedUser.get(roomId)) {
                if (userId == findingId) return roomId;
            }
        }
        return null;
    }

    public void createTestChatRoom() {
        for (String roomId : matchedUser.keySet()) {
            chatRoomRepository.createTestRoom(roomId);
        }
    }

    public void setTestUser() {
        String roomId = UUID.randomUUID().toString();
        List<Long> userIds = new ArrayList<>();
        for (Long i = 1L; i <= 3L; i++) {
            userIds.add(i);
        }
        boolean makeChat = true;
        for(Long id : userIds){
            for(String checkRoomId : matchedUser.keySet()) {
                for(Long checkId : matchedUser.get(checkRoomId)){
                    if(checkId == id) makeChat = false;
                }
            }
        }
        if(makeChat) matchedUser.put(roomId, userIds);

        roomId = UUID.randomUUID().toString();
        userIds = new ArrayList<>();
        for (Long i = 4L; i <= 6L; i++) {
            userIds.add(i);
        }
        makeChat = true;
        for(Long id : userIds){
            for(String checkRoomId : matchedUser.keySet()) {
                for(Long checkId : matchedUser.get(checkRoomId)){
                    if(checkId == id) makeChat = false;
                }
            }
        }
        if(makeChat) matchedUser.put(roomId, userIds);

        roomId = UUID.randomUUID().toString();
        userIds = new ArrayList<>();
        for (Long i = 7L; i <= 9L; i++) {
            userIds.add(i);
        }
        makeChat = true;
        for(Long id : userIds){
            for(String checkRoomId : matchedUser.keySet()) {
                for(Long checkId : matchedUser.get(checkRoomId)){
                    if(checkId == id) makeChat = false;
                }
            }
        }
        if(makeChat) matchedUser.put(roomId, userIds);
    }

}

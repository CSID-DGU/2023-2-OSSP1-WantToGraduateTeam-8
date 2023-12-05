package com.dgu.wantToGraduate.domain.chat.service;

import com.dgu.wantToGraduate.domain.chat.entity.ChatRoom;
import com.dgu.wantToGraduate.domain.chat.model.ChatRoomDto;
import com.dgu.wantToGraduate.domain.chat.repository.ChatRoomRepository;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public static HashMap<String, List<Long>> matchedUser = new HashMap<>();
    public List<Long> chattingUserId = new ArrayList<>();

    public ChatRoomDto convertToChatRoomDto(ChatRoom chatRoom) {
         return new ChatRoomDto(chatRoom.getRoomId());
    }
    public List<ChatRoomDto> findAllRoom() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return chatRooms.stream()
                .map(this::convertToChatRoomDto)
                .collect(Collectors.toList());
    }

    public ChatRoomDto findRoomById(String id) {
        return convertToChatRoomDto(chatRoomRepository.findByRoomId(id));

    }

    public void createChatRoom() {
        boolean chatRoomNoExist = false;
        for(String roomId : matchedUser.keySet()){
            List<User> matchUserList = new ArrayList<>();
            for(Long userId : matchedUser.get(roomId)) {
                if(chattingUserId.contains(userId)) {
                    chatRoomNoExist = true;
                    break;
                }
                chattingUserId.add(userId);
                matchUserList.add(userRepository.findById(userId).get());
            }
            if(chatRoomNoExist) break;
            ChatRoomDto chatRoomDto  = ChatRoomDto.create(roomId);
            ChatRoom chatRoom = ChatRoom.builder()
                    .roomId(chatRoomDto.getRoomId())
                    .users(matchUserList)
                    .build();;
            chatRoomRepository.save(chatRoom);
        }
    }

    public String findRoomIdByUserId(Long userId) {
        for (String roomId : matchedUser.keySet()) {
            for (Long findingId : matchedUser.get(roomId)) {
                if (userId == findingId) return roomId;
            }
        }
        return null;
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

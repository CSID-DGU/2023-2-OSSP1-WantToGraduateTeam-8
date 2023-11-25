package com.dgu.wantToGraduate.domain.matching.service;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.brand.repository.BrandRepository;
import com.dgu.wantToGraduate.domain.matching.dto.MatchDto;
import com.dgu.wantToGraduate.domain.matching.dto.MatchDto.RequestDto.Prefer;
import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import com.dgu.wantToGraduate.domain.matching.entity.PreferTable;
import com.dgu.wantToGraduate.domain.matching.repository.PreferBrandRepository;
import com.dgu.wantToGraduate.domain.matching.repository.PreferTableRepository;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.entity.WaitUserData;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import com.dgu.wantToGraduate.domain.user.repository.WaitUserDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchService {
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;
    private final PreferBrandRepository preferBrandRepository;
    private final WaitUserDataRepository waitUserDataRepository;
    private final PreferTableRepository preferTableRepository;
//    private static List<Long> waitRoom=new ArrayList<>();
    private static HashMap<Long, List<List<User>>> preferTable=new HashMap<>();

    //매칭 대기방 생성
    public void createWaitRoom(MatchDto.RequestDto req){


        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        brandRepository.searchBrandByName(req.getPreferList().get(0).getBrandName())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));



        for(Prefer prefer : req.getPreferList()){
            PreferBrand build = PreferBrand.builder()
                    .user(user)
                    .brand(brandRepository.findByBrandName(prefer.getBrandName()))
                    .priority(prefer.getPriority())
                    .build();
            preferBrandRepository.save(build);
        }
        WaitUserData addedWaitUser = waitUserDataRepository.save(
                WaitUserData.builder()
                        .user(user)
                        .build()
        );
//        waitRoom.add(req.getUserId());
        setPreferTable();
        showPreferTable();

    }
    public void setPreferTable(){

        int sameNum=0;
//        int waitRoomSize=waitRoom.size();
        int waitRoomSize = (int) waitUserDataRepository.count();
        for (int i = 0; i < waitRoomSize - 1; ++i) {
            for(int j = i + 1; j < waitRoomSize; ++j) {
                sameNum=0; //init

                //waitRoom.get(i)와 waitRoom.get(j)를 비교해서
                //같은 매장을 선호하는지 확인
                //만약 같은 매장을 선호한다면
                //waitRoom.get(i)와 waitRoom.get(j)를 매칭시킨다.

                List<WaitUserData> all = waitUserDataRepository.findAll();

                List<Brand> preferBrandList_i = preferBrandRepository.searchBrandListByUser(all.get(i).getUser());
                List<Brand> preferBrandList_j = preferBrandRepository.searchBrandListByUser(all.get(j).getUser());

                //아무것도 선택하지 않았다면, 3개를 선택한 것으로 간주
                if(preferBrandList_i.size()==0){
                    sameNum=3;
                }else {
                    if (preferBrandList_j.size() == 0) {
                        sameNum = 3;
                    } else {
                        for(Brand brand_i : preferBrandList_i){
                            for(Brand brand_j : preferBrandList_j){
                                if(brand_i.getId()==brand_j.getId()){
                                    sameNum++;
                                }
                            }
                        }
                    }
                }

                //TODO: user_i와 user_j의 sameNum번째의 인덱스에 삽입
                //preferTable의 user_i를 key값으로 가지는 리스트의 sameNum번째 인덱스에 user_j를 삽입
                //preferTable의 user_j를 key값으로 가지는 리스트의 sameNum번째 인덱스에 user_i를 삽입
                PreferTable build = PreferTable.builder()
                        .waitUserData(all.get(i))
                        .preferenceScore(sameNum)
//                        .userList()
                        .build();
//                                build.getUserList().add(all.get(j).getUser());
                build.addUserList(all.get(j).getUser());

                preferTableRepository.save(build);



                PreferTable build2 = PreferTable.builder()
                        .waitUserData(all.get(j))
                        .preferenceScore(sameNum)
//                        .userList(new ArrayList<>())
                        .build();
//                                build2.getUserList().add(all.get(i).getUser());
                build2.addUserList(all.get(i).getUser());
                preferTableRepository.save(build2);

//                if(preferTable.containsKey(user_i.getId())){
//                    preferTable.get(user_i.getId()).get(sameNum).add(user_j);
//                }else{
//                    List<List<User>> userList=new ArrayList<>();
//                    for(int k=0;k<4;k++){
//                        List<User> user=new ArrayList<>();
//                        userList.add(user);
//                    }
//                    userList.get(sameNum).add(user_j);
//                    preferTable.put(user_i.getId(),userList);
//                }
//
//                if(preferTable.containsKey(user_j.getId())){
//                    preferTable.get(user_j.getId()).get(sameNum).add(user_i);
//                }else{
//                    List<List<User>> userList=new ArrayList<>();
//                    for(int k=0;k<4;k++){
//                        List<User> user=new ArrayList<>();
//                        userList.add(user);
//                    }
//                    userList.get(sameNum).add(user_i);
//                    preferTable.put(user_j.getId(),userList);
//                }
            }
        }

    }



    public void showPreferTable(){
        //preferTable 내용물 출력

        System.out.println("            PreferTable 내용물 확인\n------------------------------------");
        /*
         * print format
         * [wait유저1's preferTable]
         * {
         * sameNume[0] : [유저2,유저3]
         * sameNume[1] : [유저2,유저3]
         * sameNume[2] : [유저2,유저3]
         * sameNume[3] : [유저2,유저3]
         * }
         */
        //TODO: 매칭이 된 사용자는 watiUserData에서 삭제
        //TODO: 매칭이 된 사용자는 preferTable에서 삭제
        HashMap<String,List<String>> result=new HashMap<>();
        List<String> userList=new ArrayList<>();
        List<WaitUserData> all = waitUserDataRepository.findAll();
        for(WaitUserData waitUserData : all){
            System.out.println("["+waitUserData.getUser().getNickname()+"'s preferTable]");
            List<PreferTable> preferTableList = preferTableRepository.searchPreferTableByWaitUserData(waitUserData);
            for(PreferTable preferTable : preferTableList){
                System.out.print("sameNum["+preferTable.getPreferenceScore()+"] : [");
                for(User user : preferTable.getUserList()){
                    System.out.print(user.getNickname()+",");
                }
                System.out.println("]");
            }
        }


    }
}

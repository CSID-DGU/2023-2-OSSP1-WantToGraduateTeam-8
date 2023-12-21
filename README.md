# 2023-2-OSSP1-Idle-3
2023년, 2학기, 공개SW프로젝트, 01분반, Idle팀, 3조

## 🧑🏻‍💻 팀 소개
|임주혁|이종혁|박주현|임현석|
|:-:|:-:|:-:|:-:|
|2019112406|2019112421|2021113404|2019112058|
|ovg07047@naver.com|iqbox2@naver.com|epahs1831@naver.com|sudden11y@naver.com|


## ✅ [배달 공동구매 매칭 웹앱 ‘DeliShare’]
```
 본 프로젝트는 2023-1학기의 ‘동국대 학생들끼리 먹고 싶은 음식을 같이 주문하여, 배달비 부담을 줄여주는 배달 음식 공동 구매 앱 DeliShare’을
 바탕으로 주제를 선정하였다. 본 프로젝트의 주제를 살펴보면, 주요 기능은 동국대학교 주변의 음식점을 대상으로 하여, 유저가 선호하는 음식점을
 위주로 사람을 3명씩 매칭하여 채팅방을 생성하고, 공동 구매를 할수 있도록 하는데 목표가 있다.

20대의 주 1회 이상 배달 음식 주문 비율은 약 69%에 달하는데 비해 최근 배달비는 지속적으로 상승하는 점을 문제로 삼고 문제해결을 목표로 한다.

 프로젝트의 결과물을 통해 동국대 학생들의 배달비 부담을 줄일 수 있다.
```

## 🛠️ 기술 스택
### Backend
- <img src="https://img.shields.io/badge/springboot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/><img src="https://img.shields.io/badge/springsecurity-6DB33F?style=flat-square&logo=springsecurity&logoColor=white"/>
- <img src="https://img.shields.io/badge/jsonwebtokens-000000?style=flat-square&logo=jsonwebtokens&logoColor=white"/>
- <img src="https://img.shields.io/badge/websocket-FE5F50?style=flat-square&logo=websocket&logoColor=white"/>
- <img src="https://img.shields.io/badge/amazonec2-FF9900?style=flat-square&logo=amazonec2&logoColor=white"/> <img src="https://img.shields.io/badge/amazonrds-527FFF?style=flat-square&logo=amazonrds&logoColor=white"/>

-<img src="https://img.shields.io/badge/intellijidea-000000?style=flat-square&logo=intellijidea&logoColor=white"/>

### Frontend
- <img src="https://img.shields.io/badge/react-61DAFB?style=flat-square&logo=react&logoColor=white"/> <img src="https://img.shields.io/badge/nodedotjs-339933?style=flat-square&logo=nodedotjs&logoColor=white"/> 
- <img src="https://img.shields.io/badge/vuedotjs-4FC08D?style=flat-square&logo=vuedotjs&logoColor=white"/> <img src="https://img.shields.io/badge/sock.js-FE5F50?style=flat-square&logo=sock.js&logoColor=white"/> 

-<img src="https://img.shields.io/badge/visualstudiocode-007ACC?style=flat-square&logo=visualstudiocode&logoColor=white"/> 

### Database

- <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>



## 개선점


1) 권한 설정
  Firebase Authentication을 사용하여 회원 가입과 로그인을 구현하였지만, 앱 내에서 특정 기능에 대한 권한을 부여하지 않았다. 이에 따라 JWT(JSON Web Token) 방식의 회원가입과 로그인을 구현하여, 토큰을 발급하고 인증하는 방식으로 앱 내에서 권한을 설정했다.

2) 매칭 알고리즘 ver.1
  개일-섀플리 알고리즘은 이미 많이 사용되는 안정적인 알고리즘이기에, 매칭 알고리즘 자체를 수정하기보다는 기존 프로젝트가 개일-섀플리 알고리즘을 통해 매칭을 수행하기 위한 조건을 맞추려는 목적의 선호도 테이블 생성하는 함수 부분은 기존 프로젝트에서 임의로 생성한 것이므로 이 부분을 수정하는 것이 합리적이라고 생각했고, 이에 따라 서로의 브랜드를 비교하기 위한 반복문을 교집합을 확인하는 방법으로 수정했다. 또 한, 선호도를 기반으로 매칭을 수행하는 matching 함수 부분에도 매칭하기 전 사용자들이 매칭 한 후 사용자들을 모으는 finishList에 포함되어있는지를 확인하는 부분에 finishList를 배열에서 Bloom Filter를 사용하면 효과적일 것이라고 생각하여 자료구조의 교체를 진행했다.

3) 매칭 알고리즘 ver.2
  위와 같은 선호도 테이블 생성 + 매칭과 같은 일련의 과정이 상당히 복잡하다고 생각하여 고안해낸 방법이다. 사용자들이 매칭을 시작하게 되면, 해당 사용자가 고른 브랜드 3개 전부 대기열을 생성하고, 그 대기열이 이미 생성되어 있으면, 그 대기열에 해당 사용자를 삽입하는 방식이다. 이에 특정 브랜드 큐에 3명이 삽입되면 3명을 pop 해내어 매칭을 완료하는 방식이다. 단순히 큐만 생성하고 사용자의 삽입과 삭제만을 구현하면 되기 때문에 아주 간단하고 속도의 향상도 기대할 수 있지만, 모든 브랜드에 대한 대기열의 생성으로 인해 많은 메모리 사용량도 예상했던 방식이다.



## 구현 방법

- 사용자의 이동 시간을 구하기 위해 길찾기 API를 사용하게 되면 모든 목적지마다 API를 호출해야 하므로 **지도 그래프를 만들고 다익스트라 알고리즘을 적용하여 이동시간**을 구한다.
  
- 그래프의 노드는 도보 노드, 지하철 노드, 버스 노드와 각각을 연결하는 엣지로 이루어져 있다(데이터 출처: 서울열린데이터광장)
  
- 사용자의 출발지 좌표와 가장 가까운 도보 노드를 다익스트라 알고리즘의 출발 노드로 설정하여 모든 노드에 대한 이동 시간을 구한다.
  
- 사용자별 **이동 시간을 바탕으로 이동 시간 고려 알고리즘, 편차 고려 알고리즘, 이동 시간과 편차에 가중치를 적용한 알고리즘 결과를 구하여 사용자에게 제시**한다.



## 평가 방법

- Jmeter를 통해서 매칭 알고리즘이 있는 로직의 응답 시간을 비교한다.


## 정량적 평가 지표

- 매칭 알고리즘의 응답시간을 최소 응답 시간, 최대 응답 시간 그리고 평균 응답 시간을 N명에 대해서 측정한다.


## 개선 결과
단위: ms

|N=1000 |최대 응답 시간|최소 응답 시간|평균 응답 시간|
|:-:|:-:|:-:|:-:|
|기존|3452|141|1757.113|
|개선|359|0|66|

|N=2000 |최대 응답 시간|최소 응답 시간|평균 응답 시간|
|:-:|:-:|:-:|:-:|
|기존|4234|161|1733.48|
|개선|442|0|90|

|N=3000 |최대 응답 시간|최소 응답 시간|평균 응답 시간|
|:-:|:-:|:-:|:-:|
|기존|3390|128|1744.265|
|개선|382|0|89|

## 결과 화면

로그인

![image](https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/41786e9a-b4ab-4a38-a577-025be6bd4c04)
![image](https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/c2b171a2-5d08-420f-a475-0f4061248761)


회원가입

![image](https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/597a1556-afa9-4058-ac53-e195cb7210c2)

카테고리 선택

![image](https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/3b1e6d4c-39dd-4395-ace2-971cf920d8ab)

매장 선택

![image](https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/0767ced8-4ffa-487b-ae2f-b5ffada118a8)

매칭 로딩

![image](https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/c4c73d2d-78ce-4540-92c7-56cae9662bd7)

매칭 성공

![image](https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/ac147229-d881-410c-91fc-7d5f21d5a90a)

채팅

![image](https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/d09d22c7-d4d8-48ca-ae8d-96d760284f98)

리뷰

![image](https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/ff5a28ad-4ecb-45c8-9d91-62191a19c66d)

## 시연영상
<p align="center">
  <img src = "https://github.com/CSID-DGU/2023-2-OSSP1-WantToGraduateTeam-8/assets/101847711/45445211-c6ed-41bd-93da-8f76a6229bc1">  
</p>

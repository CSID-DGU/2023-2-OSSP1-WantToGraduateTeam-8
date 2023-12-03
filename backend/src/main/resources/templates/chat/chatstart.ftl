<!DOCTYPE html>
<html>
<head>
    <title>입력 폼</title>
</head>
<body>
<div id="app">
    <form @submit.prevent="enterChatRoom">
        <label for="userId">사용자 ID 입력:</label>
        <input v-model="userId" type="text" id="userId" name="userId" required>
        <button type="submit">채팅하기</button>
    </form>

    <div v-if="roomInfo">
        <p>방 ID: {{ roomInfo.roomId }}</p>
        <p>닉네임: {{ roomInfo.nickName }}</p>
    </div>
</div>
<script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
<script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
<script src="/webjars/bootstrap/4.3.1/dist/js/bootstrap.min.js"></script>
<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            userId: '',
            roomInfo: null
        },
        methods: {
            enterChatRoom: function() {
                axios.get(`/chat/room/test/${this.userId!}`)
                    .then(response => {
                        console.log('Chat room entered successfully:', response.data);
                        this.roomInfo = response.data;
                        // 성공적으로 채팅 방에 참여한 경우에 필요한 동작 수행
                    })
                    .catch(error => {
                        console.error('Error entering chat room:', error);
                        // 에러 처리 로직 추가
                    });
            }
        }
    });
</script>
</body>
</html>
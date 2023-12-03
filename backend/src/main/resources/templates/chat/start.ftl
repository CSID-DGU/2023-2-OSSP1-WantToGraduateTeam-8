<!doctype html>
<html lang="en">
<head>
    <title>Websocket Chat</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css">
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<div class="container" id="app" v-cloak>
    <div class="input-group-prepend">
        <label class="input-group-text">사용자 ID</label>
    </div>
    <input type="text" class="form-control" v-model="user_id">
    <div class="input-group-append">
        <button class="btn btn-success" type="button" @click="startChat">채팅하기</button>
    </div>
</div>
<!-- JavaScript -->
<script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
<script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
<script src="/webjars/bootstrap/4.3.1/dist/js/bootstrap.min.js"></script>
<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            room_name : '',
            user_id: '',
            chatrooms: [
            ]
        },
        created() {
            this.findAllRoom();
        },
        methods: {
            findAllRoom: function() {
                axios.get('/chat/rooms').then(response => { this.chatrooms = response.data; });
            },
            startChat: function () {
                if(this.user_id === '') {
                    alert("사용자 Id를 입력해주세요");
                    return;
                }
                axios.get('/chat/main/' + this.user_id)
                    .then(response => {

                        var roomId = response.data.roomId;
                        var nickName = response.data.nickName;
                        localStorage.setItem("wschat.sender", nickName);
                        localStorage.setItem("wschat.roomId", roomId);
                        location.href = '/chat/room/enter/' + roomId;
                    })
            }
        }
    });
</script>
</body>
</html>
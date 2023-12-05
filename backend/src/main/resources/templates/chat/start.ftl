<!doctype html>
<html lang="en">
<head>
    <title>Websocket Chat</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css">
<#--    <style>-->
<#--        [v-cloak] {-->
<#--            display: none;-->
<#--        }-->
<#--    </style>-->
    <style>
        .MobileContainer{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .MobileWrapper{
            display : flex;
            flex-direction : column;
            align-items : center;
            width: 24.375rem;
            height: 52.75rem;
            flex-shrink: 0;
            background: #FFF;
            border : 1px solid black;
        }

        .ChatWrapper{
            display : flex;
            flex-direction : column;
            align-items : center;
            background: #FFF;
            margin-top : 12rem;
            color: #000;
            text-align: center;
            font-size: 1.40625rem;
            font-style: normal;
            font-weight: 800;


        }
        .form-control{
            margin-top : 1.5rem;
            width : 10rem;
            height: 2rem;
            border-radius : 0.8rem;
        }
        .btn{
            margin-top: 3rem;
            padding: 10px 20px;
            background-color: #FF7062;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
            font-style: normal;
            font-weight: 700;
        }
    </style>

</head>
<body>
<div class="MobileContainer" id="app" v-cloak>
    <div class="MobileWrapper">
        <div class="ChatWrapper">
            <label class="input-group-text">사용자 ID</label>
        </div>
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
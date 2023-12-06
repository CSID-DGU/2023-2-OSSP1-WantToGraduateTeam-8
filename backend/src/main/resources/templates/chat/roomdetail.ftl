<!doctype html>
<html lang="en">
<head>
    <title>Websocket ChatRoom</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style>
        .MobileContainer{
            display: flex;
            flex-direction: column;

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
        }
        .Header{
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .Room{
            display: flex;
            flex-direction: column;
            align-items: center;
            width : 11rem;
            border-bottom : 2px solid #7D7D7D;;
            color: #000;
            font-size: 1rem;
            font-style: normal;
            font-weight: 600;
        }
        .ChatWrapper {
            display: flex;
            flex-direction: column;
            position : relative;

            width: 20.8125rem;
            height: 40.5625rem;
            border-radius: 0.9375rem;
            border: 2px solid #FF7062;
            text-align: right;
            background: #FFF;
            margin-top: 1.25rem;
            overflow-y: auto; /* 스크롤 추가 */
        }
        .ButtonSection{
            display : flex;
            flex-direction: row;
            padding: 0.5rem;

            width: 20rem;
            height: 2.375rem;
            flex-shrink: 0;
            border-radius: 0.9375rem;
            border: 1px solid rgba(0, 0, 0, 0.20);
            background: #FF7062;
        }
        .form-control{
            width: 13.6875rem;
            height: 2.125rem;
            flex-shrink: 0;
            border-radius: 1.875rem;
        }
        .input-group-append{
            display: flex;
            border : 1px solid black;
            flex-direction: column;
        }
        .btn{
            margin-top: 0.2rem;
            display:flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            position : relative;
            justify-content: center;
            border-radius: 50%;
            background-color: #F1E050;
            border : 1.2px solid black;
            height : 2rem;
            width : 2em;
            cursor : pointer;
            font-size: 1.1rem;
            font-style: normal;
            font-weight: 600;
        }
        .exitButton{
            margin-top: 0.2rem;
            display:flex;
            padding-top:0.4rem;
            flex-direction: column;
            align-items: center;
            text-align: center;
            position : relative;
            border-radius: 0.5rem;
            background-color: #F1E050;
            border : 1.2px solid black;
            height : 2rem;
            width : 4.5em;
            cursor : pointer;
            font-size: 0.8rem;
            font-style: normal;
            font-weight: 600;
        }
        .list-group {
            display: flex;
            flex-direction: column;
            position: relative;
            padding: 0; /* 기본 패딩 제거 */
            margin: 0; /* 기본 마진 제거 */
        }
        .Sender {
            margin-left: auto;
            margin-top: 0.5rem; /* 이동 */
            font-size: 0.8rem; /* 이동 */
            font-weight: 600; /* 이동 */
        }
        .Message {
            display: inline-block;
            position : relative;
            padding: 0.5rem;
            margin-top: 0.25rem;
            border-radius: 1.875rem;
            font-size: 0.8rem;
            font-style: normal;
            font-weight: 600;
        }
        /*내 메세지*/
        .Message.mine {
            background-color: #F1E050;
        }
        /* 내 닉네임 */
        .Sender.mine {
            align-self: flex-end;
            margin-left: auto;
        }
        /* 다른 사용자의 메시지 */
        .Message.other {
            text-align: left;
            width:max-content;
            background-color: #BCF0A9;
            margin-right: 90% ; /* 오른쪽 여백 설정 */
        }
        /* 다른 사용자의 이름 */
        .Sender.other {
            text-align: left;
            margin-right: auto;
            margin-top: 0.5rem; /* 이동 */
            font-size: 0.8rem; /* 이동 */
            font-weight: 600; /* 이동 */
        }
    </style>
</head>
<body>
<div class="MobileContainer" id="app">
    <div class="MobileWrapper">
        <div class="ChatWrapper">
            <div class="Header">
                <div class="Room">
                    {{room.name}}
                </div>
            </div>

            <ul class="list-group">
                <li class="list-group-item" v-for="message in messages">
                    <div :class="[(message.sender === sender) ? 'Sender mine' : 'Sender other']">
                        {{message.sender}}
                    </div>
                    <div :class="[(message.sender === sender) ? 'Message mine' : 'Message other']">
                        {{message.message}}
                    </div>
                </li>
            </ul>
        </div>
        <div class="ButtonSection">
            <button class="exitButton" type="button">나가기</button>
            <input type="text" class="form-control" v-model="message" @keyup.enter="sendMessage">
            <button class="btn btn-primary" type="button" @click="sendMessage">&#10148</button>
        </div>
    </div>
</div>
<!-- JavaScript -->
<script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
<script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
<script src="/webjars/bootstrap/4.3.1/dist/js/bootstrap.min.js"></script>
<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>
<script>
    var sock = new SockJS("/ws-stomp");
    var ws = Stomp.over(sock);

    var vm = new Vue({
        el: '#app',
        data: {
            roomId: '',
            room: {},
            sender: '',
            message: '',
            messages: []
        },
        created() {
            this.roomId = localStorage.getItem('wschat.roomId');
            this.sender = localStorage.getItem('wschat.sender');
            this.findRoom();
        },
        methods: {
            findRoom: function () {
                axios.get('/chat/room/' + this.roomId)
                    .then(response => {
                        this.room = response.data;
                        axios.get('/chat/messages/' + this.roomId)
                            .then(response => {
                                this.messages = response.data;
                            })
                            .catch(error => {
                                console.error('Error fetching chat messages:', error);
                            });
                    })
                    .catch(error => {
                        console.error('Error fetching chat room:', error);
                    });
                this.$forceUpdate();
            },
            sendMessage: function () {
                ws.send("/pub/chat/message", {}, JSON.stringify({
                    type: 'TALK',
                    roomId: this.roomId,
                    sender: this.sender,
                    message: this.message
                }));
                this.message = '';
            },
            recvMessage: function (recv) {
                this.messages.push({
                    "type": recv.type,
                    "sender": recv.sender,
                    "message": recv.message
                });
                this.$forceUpdate();
            }
        }
    });

    ws.connect({}, function (frame) {
        ws.subscribe("/sub/chat/room/" + vm.$data.roomId, function (message) {
            var recv = JSON.parse(message.body);
            vm.recvMessage(recv);
        });
    }, function (error) {
        alert("error " + error);
    });
</script>
</body>
</html>
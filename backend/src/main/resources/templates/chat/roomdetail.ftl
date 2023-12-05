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
        .ChatWrapper{
            display : flex;
            flex-direction : column;
            width: 20.8125rem;
            height: 40.5625rem;
            border-radius: 0.9375rem;
            border: 1.5px solid #FF7062;
            align-items : center;
            background: #FFF;
            margin-top : 1.25rem;
        }
        .ButtonSection{
            display : flex;
            flex-direction: row;
            padding: 0.5rem;

            width: 20.8rem;
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
        .list-group{
            display: flex;
            flex-direction: column;
            position: relative;
        }
        .Sender{
            display: flex;
            flex-direction: column;
            display: inline-flex;
            border-radius: 1.875rem;
            justify-content: center;
            align-items: center;
            margin-right : 15rem;
            font-size: 0.4rem;
            font-style: normal;
            font-weight: 600;


        }
        .Message{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            display: inline-block;
            position: relative;
            padding: 0.2rem;
            border-radius: 1.875rem;
            background: #F1E050;
            font-size: 0.8rem;
            font-style: normal;
            font-weight: 600;
        }

    </style>
</head>
<body>
<div class="MobileContainer" id="app">
    <div class="MobileWrapper">
        <div class="ChatWrapper">
            <div class="Room">
                {{room.name}}
            </div>

            <ul class="list-group">
                <li class="list-group-item" v-for="message in messages">
                    <div class="Sender">
                        {{message.sender}}
                    </div>
                    <div class="Message">
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
                this.messages.unshift({
                    "type": recv.type,
                    "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender,
                    "message": recv.message
                })
            }
        }
    });

    ws.connect({}, function (frame) {
        ws.subscribe("/sub/chat/room/" + vm.$data.roomId, function (message) {
            var recv = JSON.parse(message.body);
            vm.recvMessage(recv);
        });
        ws.send("/pub/chat/message", {}, JSON.stringify({
            type: 'ENTER',
            roomId: vm.$data.roomId,
            sender: vm.$data.sender
        }));
    }, function (error) {
        alert("error " + error);
    });
</script>
</body>
</html>
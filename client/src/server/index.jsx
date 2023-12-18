const express = require('express')
const socketio = require('socket.io')
const http = require('http')

const PORT = process.env.PORT || 5000

const app = express();
const server = http.createServer(app)
const io = socketio(server)
server.listen(PORT,()=>console.log(`서버가 ${PORT} 에서 시작되었어요`))
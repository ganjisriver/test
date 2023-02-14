import express from "express"; // express를 사용한 일반적인 NodeJS
import http from "http";
import { Server } from "socket.io";
import cors from "cors";

const app = express();

app.use(cors());

// express를 이용해 http 서버를 만듦(노출 서버)
const httpServer = http.createServer(app);

// 로컬 / ec2서버
// cors: http://localhost:3000  /  [https://admin.socket.io]
// httpServer.listen: 3001  /  https://i8b307.p.ssafy.io

// // http 서버 위에 ws(webSocket) 서버를 만듦
const wsServer = new Server(httpServer, {
  cors: {
    origin: ["https://admin.socket.io"],
    credentials: true,
  },
});

wsServer.on("connection", (socket) => {
  socket.on("join_room", (roomName) => {
    console.log("브라우저에서 받은 roomName : ", roomName);
    socket.join(roomName);
    socket.to(roomName).emit("welcome");
  });
  socket.on("offer", (offer, roomName) => {
    socket.to(roomName).emit("offer", offer);
  });
  socket.on("answer", (answer, roomName) => {
    socket.to(roomName).emit("answer", answer);
  });
  socket.on("ice", (ice, roomName) => {
    socket.to(roomName).emit("ice", ice);
  });
  socket.on("leave-room", (roomName, done) => {
    socket.to(roomName).emit("peer-leaving");
    socket.leave(roomName);
    done();
  });
  socket.on("disconnecting", () => {
    socket.rooms.forEach((room) => {
      socket.to(room).emit("peer-leaving");
      socket.leave(room);
    });
  });
});
const handleListen = () => console.log(`Listening on https://i8b307.p.ssafy.io`);
httpServer.listen(process.env.REACT_APP_NODE, handleListen);

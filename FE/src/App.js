import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import React from "react";
import "./style/normalize.css";
import "./App.css";

import Start from "./pages/Start";
import Home from "./pages/Home";
import Meeting from "./pages/Meeting";
import MyInfo from "./pages/MyInfo";
import SocialSignInRedirect from "./pages/Start/SignIn/socialSignIn";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Start />}></Route>
          <Route path="/home" element={<Home />}></Route>
          <Route path="/meeting" element={<Meeting />}></Route>
          <Route path="/MyInfo" element={<MyInfo />}></Route>
          <Route
            path="/social/redirect"
            element={<SocialSignInRedirect />}
          ></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;

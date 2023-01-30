import "../../../App.css";
import "./Hash.css";
import React, { useState } from "react";

function Hash({ showHashModal }) {
  const data = [1, 2, 3, 4, 5];

  let [btnActive, setBtnActive] = useState(false);

  const toggleActive = (e) => {
    setBtnActive((prev) => {
      return e.target.vlaue;
    });
  };

  return (
    <div className="Hash">
      <div className="hashdiv">
        <div className="hashSerchDiv">
          <input type="text" className="hashinput" />
          <div className="hashVec" />
        </div>

        <div className="interestdiv">
          {data.map((item, idx) => {
            return (
              <div className="interestbox">
                <div
                  className={"intBack" + (idx == btnActive ? "active" : "")}
                  onClick={toggleActive}
                >
                  <span className={"intText" + (btnActive ? "active" : "")}>
                    gd
                  </span>
                </div>
              </div>
            );
          })}
        </div>

        <button className="hashEdit" onClick={showHashModal}>
          <span className="hashEditText">선호 정보 수정</span>
        </button>
      </div>
    </div>
  );
}

export default Hash;
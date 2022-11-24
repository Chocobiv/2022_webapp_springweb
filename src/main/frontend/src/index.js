import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

//1. 사용할 컴포넌트 호출
import Library from "./chapter3/Library";

//2. DOM 컨테이너 뿌릴 위치 [public-index.html 안에 있는 태그]
const root = ReactDOM.createRoot(document.getElementById('root'));
//3. DOM 컨테이너 렌더링
// 기본값 [App.js 컴포넌트를 root에 렌더링]
/*root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);*/
// 기본값 [Library 컴포넌트를 root에 렌더링]
root.render(
    <React.StrictMode>
        <Library />
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

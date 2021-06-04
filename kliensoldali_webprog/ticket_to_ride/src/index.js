import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'fomantic-ui-css/semantic.css';

import thunk from "redux-thunk";
import { reducers } from "./redux/store";
import { createStore, applyMiddleware, compose } from "redux";

//import { configureStore } from "./redux/store";
import { Provider } from "react-redux";

import { socket, roomId, MESSAGES } from "./socket/Context";

//const store = configureStore();

const afterStateManipulationMw = (store) => (next) => (action) => {
  
  if (action.disableSync === true) return next(action);

  if (!socket || !roomId) return;

  store.dispatch(async () => {
    socket.emit(
      MESSAGES.SYNC_STATE,
      roomId,
      store.getState(),
      true,
      (args) => {
        console.log("sync message sent.", args);
      }
    );
  });

  return next(action);
};

const middleware = window.__REDUX_DEVTOOLS_EXTENSION__
  ? compose(
      applyMiddleware(thunk, afterStateManipulationMw),
      window.__REDUX_DEVTOOLS_EXTENSION__ &&
        window.__REDUX_DEVTOOLS_EXTENSION__()
    )
  : applyMiddleware(thunk, afterStateManipulationMw);

const store = createStore(reducers, middleware);


ReactDOM.render(
  <Provider store={store}>
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </Provider>,
  document.getElementById("root")
);

reportWebVitals();

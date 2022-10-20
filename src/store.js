import { configureStore } from "@reduxjs/toolkit";
import reducer from './switchAccountSlice'
const store=configureStore({
    reducer : {
        reducer
    }    
})

export default store;
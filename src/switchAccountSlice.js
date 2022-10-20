import { createSlice } from "@reduxjs/toolkit";
const initialValue={
    "selectedAccount":0,
    "listOfAccount":[],
    "loginId":0,
    "balance":0,
    "userName":"",
    "listOfRequest":[],
    "UserRequestId":0,
    "description":"",
    "amount":0,
    "name":""
}

const slice = createSlice({
    name: "tempVal",
    initialState:initialValue,
    reducers : {
       updateSelectAccount(state,action) {
        return {
            ...state,
            selectedAccount:action.payload
        }
       },
       updateAccounts(state,action) {
        return {
            ...state,
            listOfAccount:action.payload
        }
       },
       loggedUserId(state,action) {
        return {
            ...state,
            loggedUserId:action.payload
        }
       },
       updateBalances(state,action){
        return{
            ...state,
            balance:action.payload
        }
       },
       updateUserName(state,action){
        return{
            ...state,
            userName:action.payload
        }
       },
       updateRequest(state,action){
        return{
            ...state,
            listOfRequest:action.payload
        }
       },
       updateSelectRequest(state,action){
        return{
            ...state,
            UserRequestId:action.payload
        }
       },
       updateRequestUserName(state,action){
        return{
            ...state,
            name:action.payload
        }
       }

    }
})

export const {updateAccounts,updateSelectAccount,loggedUserId,updateBalances,updateUserName,updateRequest,updateSelectRequest,updateRequestUserName} = slice.actions;

export default slice.reducer
import {createAction, props} from "@ngrx/store";

export enum usersActions {
    FETCH_ALL_USERS = '[Users Action] FetchAllUsers',
    FETCH_ALL_USERS_SUCCESS = '[Users Action] FetchAllUsersSuccess',
    FETCH_ALL_USERS_FAILURE = '[Users Action] FetchAllUsersFailure',
}

export const FetchAllUsers = createAction(usersActions.FETCH_ALL_USERS)
export const FetchAllUsersSuccess = createAction(usersActions.FETCH_ALL_USERS_SUCCESS,
    props<{ payload: any }>())
export const FetchAllUsersFailure = createAction(usersActions.FETCH_ALL_USERS_FAILURE,
    props<{ payload: any }>())

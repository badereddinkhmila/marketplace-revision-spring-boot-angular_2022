import {createAction, props} from "@ngrx/store";

export enum authActions {
  LOGIN_USER = '[Auth Action] LoginUser',
  LOGIN_SUCCESS = '[Auth Action] LoginSuccess',
  LOGIN_FAILURE = '[Auth Action] LoginFailure',
  FETCH_CURRENT_USER = '[Auth Action] FetchCurrentUser',
  SIGNUP_USER = '[Auth Action] SignUpUser',
  SIGNUP_SUCCESS = '[Auth Action] SignUpSuccess',
  SIGNUP_FAILURE = '[Auth Action] SignUpFailure',
  LOGOUT_USER = '[Auth Action] LogoutUser',
  REFRESH_ACCESS_TOKEN = '[Auth Action] RefreshAccessToken',
  REFRESH_TOKEN_SUCCESS = '[Auth Action] RefreshTokenSuccess',
  REFRESH_TOKEN_FAILURE = '[Auth Action] RefreshTokenFailure',
}

export const LoginUser = createAction(authActions.LOGIN_USER, props<{ payload: any }>());

export const LogInSuccess = createAction(authActions.LOGIN_SUCCESS, props<{ payload: any }>());

export const LogInFailure = createAction(authActions.LOGIN_FAILURE, props<{ payload: any }>());

export const FetchCurrentUser = createAction(authActions.FETCH_CURRENT_USER);

export const SignUpUser = createAction(authActions.SIGNUP_USER, props<{ payload: any }>());
export const SignUpUserSuccess = createAction(authActions.SIGNUP_SUCCESS);
export const SignUpUserFailure = createAction(authActions.SIGNUP_FAILURE, props<{ payload: any }>());

export const LogoutUser = createAction(authActions.LOGOUT_USER);

export const RefreshAccessToken = createAction(authActions.REFRESH_ACCESS_TOKEN, props<{ payload: any }>());

export const RefreshTokenSuccess = createAction(authActions.REFRESH_TOKEN_SUCCESS, props<{ payload: any }>());

export const RefreshTokenFailure = createAction(authActions.REFRESH_TOKEN_FAILURE, props<{ payload: any }>());

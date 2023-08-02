import User from "@src/app/user/model/User.model";
import {
  LogInFailure,
  LogInSuccess,
  LogoutUser,
  RefreshAccessToken,
  RefreshTokenFailure,
  RefreshTokenSuccess
} from "@src/store/actions/auth.actions";
import {createReducer, on} from "@ngrx/store";

export interface authState {
  isAuthenticated: boolean;
  isRefreshing: boolean;
  currentUser: User | null;
  accessToken: string | null;
  refreshToken: string | null;
  errorMessage: string | null;
}

export const initialState: authState = {
  isAuthenticated: false,
  isRefreshing: false,
  currentUser: null,
  accessToken: null,
  refreshToken: null,
  errorMessage: null
}

export const authReducer = createReducer(initialState,
  on(LogInSuccess, RefreshTokenSuccess, (state, {payload}) => {
    return {
      ...state,
      isAuthenticated: true,
      isRefreshing: false,
      accessToken: payload.accessToken,
      refreshToken: payload.refreshToken,
      errorMessage: null
    }
  }),
  on(LogInFailure, RefreshTokenFailure, (state, {payload}) => {
    return {
      ...state,
      isAuthenticated: false,
      isRefreshing: false,
      accessToken: null,
      refreshToken: null,
      errorMessage: payload
    }
  }),
  on(LogoutUser, (state) => {
    return {
      ...state,
      isAuthenticated: false,
      currentUser: null,
      accessToken: null,
      refreshToken: null,
    }
  }),
  on(RefreshAccessToken, (state) => {
    return {
      ...state,
      isRefreshing: true,
    }
  }),
)

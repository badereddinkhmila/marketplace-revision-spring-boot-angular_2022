import User from "@src/app/user/model/User.model";
import {FetchAllUsers, FetchAllUsersFailure, FetchAllUsersSuccess} from "@src/store/actions/users.actions";
import {createReducer, on} from "@ngrx/store";

export interface usersState {
  isFetching: boolean;
  users: User[] | [];
  errorMessage: string | null;
}

export const initialState: usersState = {
  isFetching: false,
  users: [],
  errorMessage: null
}

export const usersReducer = createReducer(initialState,
  on(FetchAllUsers, (state) => {
    return {
      ...state,
      isFetching: true,
    }
  }),
  on(FetchAllUsersSuccess, (state, {payload}) => {
    return {
      ...state,
      isFetching: false,
      users: payload,
      errorMessage: null as null
    }
  }),
  on(FetchAllUsersFailure, (state, {payload}) => {
    return {
      ...state,
      isFetching: false,
      users: [] as [],
      errorMessage: payload
    }
  })
);

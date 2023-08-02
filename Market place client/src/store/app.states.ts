import * as auth from './reducers/auth.reducer';
import * as users from './reducers/users.reducer';
import {ActionReducer, ActionReducerMap, createFeatureSelector, createSelector, MetaReducer} from "@ngrx/store";
import {localStorageSync} from "ngrx-store-localstorage";


export interface AppState {
    authState: auth.authState;
    usersState: users.usersState;
}

export const getAuthState = createFeatureSelector<auth.authState>('authState');
export const getAccessToken = createSelector(
    getAuthState,
    (state) => state.accessToken
);

export const reducers: ActionReducerMap<AppState> = {
    authState: auth.authReducer,
    usersState: users.usersReducer
};

export function localStorageSyncReducer(reducer: ActionReducer<any>): ActionReducer<any> {
    return localStorageSync({keys: ['authState', 'usersState'], rehydrate: true})(reducer);
}

export const metaReducers: Array<MetaReducer<any, any>> = [localStorageSyncReducer];

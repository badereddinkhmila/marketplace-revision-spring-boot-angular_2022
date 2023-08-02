import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {UsersService} from "@src/app/user/users.service";
import {catchError, exhaustMap, map, of} from "rxjs";
import {FetchAllUsersFailure, FetchAllUsersSuccess, usersActions} from "@src/store/actions/users.actions";


@Injectable()
export class UsersEffects {

    FetchAllUsers$ = createEffect(() => {
            return this.$actions.pipe(
                ofType(usersActions.FETCH_ALL_USERS),
                exhaustMap((action: any) => this.userService.getAllUsers().pipe(
                    map(response => {
                        console.log(response)
                        return FetchAllUsersSuccess({payload: response})
                    }),
                    catchError(error => {
                        console.log(error)
                        return of(FetchAllUsersFailure(error))
                    })
                ))
            )
        }
    );

    constructor(
        private $actions: Actions,
        private userService: UsersService,
    ) {
    }


}

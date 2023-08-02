import {Component, OnInit} from '@angular/core';
import User from '../model/User.model';
import {UsersService} from "@src/app/user/users.service";
import {Store} from "@ngrx/store";
import {AppState} from "@src/store/app.states";
import {FetchAllUsers} from "@src/store/actions/users.actions";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {
  users: User[];
  isFetching: boolean;

  constructor(private userService: UsersService, private store: Store<AppState>) {

  }

  ngOnInit(): void {
    this.store.dispatch(FetchAllUsers());
    this.store.select(state => state.usersState.isFetching)
      .subscribe(isFetching => this.isFetching = isFetching);
    this.store.select(state => state.usersState.users)
      .subscribe(users => this.users = users);
  }


}

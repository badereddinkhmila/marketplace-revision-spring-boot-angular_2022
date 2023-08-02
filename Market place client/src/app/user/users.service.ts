import {HttpClient} from "@angular/common/http";
import {Inject, Injectable} from "@angular/core";
import {Observable, of} from 'rxjs';
import {APP_CONFIG, IAppConfig} from "../app.config";
import User from './model/User.model';

@Injectable()
export class UsersService {

  usersList = [
    {
      id: 1,
      username: "badreddine",
      email: "test@test.fr",
      avatar: "https://i.pravatar.cc/150?img=3"
    },
    {
      id: 2,
      username: "ayoub",
      email: "test@test.com",
      avatar: "https://i.pravatar.cc/150?img=5"
    },
    {
      id: 3,
      username: "ibtihel",
      email: "test@test.tn",
      avatar: "https://i.pravatar.cc/150?img=50"
    }
  ];

  constructor(private http: HttpClient, @Inject(APP_CONFIG) private config: IAppConfig) {

  }

  getUsers(): Observable<User[]> {
    return of(this.usersList);
  }

  /**
   * get the list of all users
   * @returns Observable
   */
  getAllUsers() {
    return this.http.get(this.config.apiEndpoint + '/users');
  }

}

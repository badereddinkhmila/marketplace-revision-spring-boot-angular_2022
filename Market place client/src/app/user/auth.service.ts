import {HttpClient} from "@angular/common/http";
import {Inject, Injectable} from "@angular/core";
import Ilogin from "./model/Ilogin.model";
import {Observable, of} from 'rxjs';
import {APP_CONFIG, IAppConfig} from "../app.config";
import Isignup from "./model/Isignup.model";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient, @Inject(APP_CONFIG) private config: IAppConfig) {

  }

  getCurrentUser() {
    return this.http.get(this.config.apiEndpoint + '/users/me');
  }

  /**
   * login with username or email and get a valid token
   * @param {usernameOrEmail,password}
   * @returns Observable
   */
  login(loginForm: Ilogin) {
    return this.http.post(this.config.apiEndpoint + '/auth/signin', loginForm);
  }

  /**
   * check if the valid typed username is already available in the backend
   * @param username
   * @returns Observable
   */
  checkUsernameAvailability(username: string) {
    return this.http
      .get(this.config.apiEndpoint + `/users/checkUsernameAvailability?username=${username}`);
  }

  /**
   * check if the valid typed email is already available in the backend
   * @param email
   * @returns Observable
   */
  checkEmailAvailability(email: string) {
    return this.http.get(this.config.apiEndpoint + `/users/checkEmailAvailability?email=${email}`);
  }

  /**
   * register a new user with username, email
   * @param {username, email, password}
   * @returns Observable
   */
  register(signupForm: Isignup) {
    return this.http.post(this.config.apiEndpoint + '/auth/signup', signupForm);
  }

  /**
   * refresh the access token when it expires
   * @param {refreshToken}
   * @returns Observable
   */
  refreshToken(refreshToken: string) {
    return this.http.post(this.config.apiEndpoint + '/auth/refresh_token', {
      refreshToken: refreshToken
    });
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}

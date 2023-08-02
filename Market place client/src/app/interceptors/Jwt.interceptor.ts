import {
  HTTP_INTERCEPTORS,
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import {BehaviorSubject, catchError, filter, Observable, switchMap, take, throwError} from "rxjs";
import {Injectable} from "@angular/core";
import {ActionsSubject, Store} from "@ngrx/store";
import {AppState} from "@src/store/app.states";
import {RefreshAccessToken} from "@src/store/actions/auth.actions";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  isAuthenticated: boolean;
  isRefreshing: boolean;
  accessToken: string;
  refreshToken: string;
  refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(
    null
  );

  constructor(private store: Store<AppState>, private storeActions: ActionsSubject) {
    this.store.select((state) => state.authState.isAuthenticated).subscribe(
      isAuthenticated => this.isAuthenticated = isAuthenticated
    );
    this.store.select((state) => state.authState.accessToken).subscribe(
      accessToken => {
        if (this.accessToken == "" || this.accessToken != accessToken) {
          this.accessToken = accessToken;
          this.refreshTokenSubject.next(this.refreshToken);
        }
      }
    );

    this.store.select(state => state.authState.refreshToken).subscribe(
      refreshToken => this.refreshToken = refreshToken
    )
    this.store.select(state => state.authState.isRefreshing).subscribe(
      isRefreshing => this.isRefreshing = isRefreshing
    );

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.isAuthenticated && this.accessToken) {
      req = this.addTokenHeader(req, this.accessToken);
    }
    return next.handle(req).pipe(catchError(err => {
      if (err instanceof HttpErrorResponse && !req.url.includes('/auth/signin') && err.status === 401) {
        if (true || err.error?.message.includes("Refresh token was expired")) {
          return this.handle401Error(req, next);
        }
      }
      return throwError(err);
    }));
  }

  private isRefreshTokenInProgress(): boolean {
    this.store.dispatch(RefreshAccessToken({payload: this.refreshToken}));
    // Set the refreshTokenSubject to null so that subsequent API calls will wait until the new token has been retrieved
    if (this.isRefreshing) {
      this.refreshTokenSubject.next(null);
      return true;
    } else {
      return false;
    }
  }

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    if (this.isRefreshTokenInProgress()) {
      return this.refreshTokenSubject.pipe(
        filter(result => result !== null),
        take(1),
        switchMap(() => next.handle(this.addTokenHeader(request, this.accessToken)))
      );
    } else {
      this.refreshTokenSubject.next(null);
      return next.handle(this.addTokenHeader(request, this.accessToken));
    }
  }

  // add access token header to requests
  private addTokenHeader(request: HttpRequest<any>, token: any) {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return request.clone({headers});
  };

}

export const authInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}
]

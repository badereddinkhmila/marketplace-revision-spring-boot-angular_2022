import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {AppState} from "@src/store/app.states";
import {Injectable} from "@angular/core";

@Injectable()
export class IsSignedInGuard implements CanActivate {
  isAuthenticated: boolean;

  constructor(private _store: Store<AppState>, private _router: Router) {
    this._store.select(state => state.authState.isAuthenticated).subscribe(
      value => this.isAuthenticated = value
    )
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.isAuthenticated) {
      this._router.navigate(['/login']).then();
    }
    return this.isAuthenticated;
  }

}

import {BreakpointObserver} from '@angular/cdk/layout';
import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from '@angular/material/sidenav';
import {Store} from "@ngrx/store";
import {AppState} from "@src/store/app.states";
import {LogoutUser} from "@src/store/actions/auth.actions";

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  @ViewChild(MatSidenav)
  sidenav: MatSidenav;

  cartItemsCount: number = 0;

  isAuthenticated: boolean;

  constructor(private observer: BreakpointObserver, private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.store.select(state => state.authState.isAuthenticated).subscribe(auth => this.isAuthenticated = auth);
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.observer.observe(['(max-width: 756px)']).subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
        }
      });
    })
  }

  logout() {
    this.store.dispatch(LogoutUser());
  }

}

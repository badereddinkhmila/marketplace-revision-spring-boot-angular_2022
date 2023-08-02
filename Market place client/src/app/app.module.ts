import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ComponentsModule} from './components/components.module';
import {UserModule} from './user/user.module';
import {APP_CONFIG, AppConfig} from './app.config';
import {MatSidenavModule} from '@angular/material/sidenav';
import {StoreModule} from '@ngrx/store';
import {StoreRouterConnectingModule} from '@ngrx/router-store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {EffectsModule} from "@ngrx/effects";
import {AuthEffects} from "@src/store/effects/auth.effect";
import {authInterceptorProviders} from "@src/app/interceptors/Jwt.interceptor";
import {metaReducers, reducers} from "@src/store/app.states";
import {UsersEffects} from "@src/store/effects/users.effect";
import {IsSignedInGuard} from "@src/app/guards/IsSignedIn.gaurd";
import {ProductModule} from "@src/app/product/product.module";


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    ComponentsModule,
    UserModule,
    ProductModule,
    StoreModule.forRoot(reducers, {metaReducers}),
    EffectsModule.forRoot([AuthEffects, UsersEffects]),
    StoreRouterConnectingModule.forRoot(),
    StoreDevtoolsModule.instrument()
  ],
  providers: [{provide: APP_CONFIG, useValue: AppConfig},
    authInterceptorProviders, IsSignedInGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

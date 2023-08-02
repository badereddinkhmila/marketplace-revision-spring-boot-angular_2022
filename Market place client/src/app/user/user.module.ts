import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login/login.component';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import {UsersListComponent} from './users-list/users-list.component';
import {AuthService} from './auth.service';
import {HttpClientModule} from '@angular/common/http';
import {MatDividerModule} from '@angular/material/divider';
import {SignupComponent} from './signup/signup.component';
import {RouterModule} from '@angular/router';
import {LayoutModule} from '@angular/cdk/layout';
import {UsersService} from "@src/app/user/users.service";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";

@NgModule({
    declarations: [
        LoginComponent,
        UsersListComponent,
        SignupComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        RouterModule,
        LayoutModule,
        MatCardModule,
        MatInputModule,
        MatIconModule,
        MatGridListModule,
        MatButtonModule,
        MatDividerModule,
        MatProgressSpinnerModule
    ],
    exports: [
        LoginComponent,
        UsersListComponent,
        SignupComponent,
    ],
    providers: [AuthService, UsersService]
})
export class UserModule {
}

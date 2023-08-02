import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Store} from "@ngrx/store";
import {AppState} from "@src/store/app.states";
import {LoginUser} from "@src/store/actions/auth.actions";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

    loginForm: FormGroup;
    hide: boolean = true;

    constructor(private router: Router, private formBuilder: FormBuilder,
                private store: Store<AppState>) {
        this.loginForm = this.formBuilder.group({
            'usernameOrEmail': ['', [Validators.required]],
            'password': ['', Validators.required]
        });
    }

    get f() {
        return this.loginForm.controls;
    }

    ngOnInit(): void {
    }

    onSubmit() {
        if (this.loginForm.invalid) {
            return;
        }
        // ngrx implementation
        this.store.dispatch(LoginUser(this.loginForm.value))
        // classic angular implementation
        //this.userService.login(this.loginForm.value);
    }

    getErrorMessage() {
        if (this.f['usernameOrEmail'].hasError('required')) {
            return 'You must enter a username or a valid email';
        }
        return '';
    }
}

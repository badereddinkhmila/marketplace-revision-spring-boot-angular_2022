import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {map} from 'rxjs';
import {Store} from "@ngrx/store";
import {AppState} from "@src/store/app.states";
import {SignUpUser} from "@src/store/actions/auth.actions";
import {AuthService} from "@src/app/user/auth.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})

export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  hide: boolean = true;
  usernameTaken: boolean = true;
  emailTaken: boolean = true;

  constructor(private router: Router, private formBuilder: FormBuilder,
              private store: Store<AppState>, private authService: AuthService) {
    this.signupForm = this.formBuilder.group({
      'username': ['', Validators.compose([Validators.maxLength(20), Validators.minLength(5), Validators.required])],
      'email': ['', Validators.compose([Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$'), Validators.required])],
      'password': ['', Validators.compose([Validators.maxLength(20), Validators.minLength(6), Validators.required])]
    });
  }

  get f() {
    return this.signupForm.controls;
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      return;
    }
    this.store.dispatch(SignUpUser(this.signupForm.value));
  }

  getErrorMessage() {
    if (this.f['email'].hasError('required')) {
      return 'You must enter a value';
    }
    return this.f['email'].hasError('email') ? 'Not a valid email' : '';
  }

  checkUsernameAvailability(event: any) {
    if (this.f['username'].valid) {
      this.authService.checkUsernameAvailability(event.target.value)
        .pipe(map(resp => Object.values(resp)[0]))
        .subscribe(available => this.usernameTaken = !available);
    }
  }

  checkEmailAvailability(event: any) {
    if (this.f['email'].valid) {
      this.authService.checkEmailAvailability(event.target.value)
        .pipe(map(resp => Object.values(resp)[0]))
        .subscribe(available => this.emailTaken = !available);
    }
  }
}

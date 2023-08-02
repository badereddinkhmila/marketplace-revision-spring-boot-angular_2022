import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-notfound',
  templateUrl: './notfound.component.html',
  styleUrls: ['./notfound.component.scss']
})
export class NotFoundComponent implements OnInit {
  @Input() redirect:string = '/';

  constructor() { }

  ngOnInit(): void {
  }

}

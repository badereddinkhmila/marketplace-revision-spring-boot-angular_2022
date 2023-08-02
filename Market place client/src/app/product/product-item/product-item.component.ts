import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.scss']
})
export class ProductItemComponent implements OnInit {
  cellsToShow: number = 3;
  loop: boolean = true;

  constructor() {
  }

  ngOnInit(): void {
  }

}

import {Component, OnInit} from '@angular/core';
import {SwiperOptions} from "swiper";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {
  config: SwiperOptions = {
    slidesPerView: 3,
    spaceBetween: 50,
    navigation: true,
    pagination: {clickable: true},
    scrollbar: {draggable: true},
  };

  constructor() {
  }
  
  onSlideChange() {
    console.log('slide change');
  }

  ngOnInit(): void {
  }

}

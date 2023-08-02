import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-product-image-preview',
  templateUrl: './product-image-preview.component.html',
  styleUrls: ['./product-image-preview.component.scss']
})
export class ProductImagePreviewComponent implements OnInit {

  @Input() image:string;
  @Input() id:number;
  @Output() editEvent: EventEmitter<number> = new EventEmitter();
  @Output() deleteEvent: EventEmitter<number> = new EventEmitter();
  
  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    const card = document.getElementById('card-container');
    if (card != null) {
      card.style.backgroundImage = `url(${this.image})`;
    }
  }

  onDelete(id:number){
    this.deleteEvent.emit(id);
  }

  onEdit(id:number){
    this.editEvent.emit(id);
  }

}

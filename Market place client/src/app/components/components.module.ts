import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatExpansionModule} from '@angular/material/expansion';

import { SidenavComponent } from './sidenav/sidenav.component';
import {NotFoundComponent} from './errors/notfound/notfound.component';
import { SearchComponent } from './search/search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { ProductImagePreviewComponent } from './product-image-preview/product-image-preview.component';


@NgModule({
  declarations: [
    NotFoundComponent,
    SidenavComponent,
    SearchComponent,
    ProductImagePreviewComponent
  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatButtonModule,
    MatDividerModule,
    MatListModule,
    MatExpansionModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    MatInputModule
],
  exports: [
    NotFoundComponent,
    SidenavComponent,
    ProductImagePreviewComponent
  ],
})
export class ComponentsModule { }

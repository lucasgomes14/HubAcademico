import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './src/app/app.component';
import { SearchComponent } from './src/app/pages/search/search.component';

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    FormsModule,
    SearchComponent,
    HttpClientModule
  ],
  providers: [],
  bootstrap: []
})
export class AppModule { }

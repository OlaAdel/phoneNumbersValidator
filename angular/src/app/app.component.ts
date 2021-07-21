import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Customer } from './Customer';
import { CustomerService } from './customer.service';
import { CustomerFilter } from './CustomerFilter';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = "Validator";

  public filter: CustomerFilter = {countries:[],states:[]}
  public customers: Customer[] = [];

  public states = [ { value: "true", display: 'valid', isChecked: true },
                    { value: "false", display: 'Not Valid', isChecked: true}];


  public countries = [  { value: 'Cameroon', isChecked: true },
                        { value: 'Ethiopia', isChecked: true},
                        { value: 'Morocco', isChecked: true },
                        { value: 'Mozambique', isChecked: true},
                        { value: 'Uganda', isChecked: true }];

  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    this.getCustomers();
  }
  public getCustomers() :void {
    this.getParameters();

    this.customerService.findCustomers(this.filter).subscribe(
      (response: Customer[]) => {
        this.customers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }
  public getParameters()
  {
    this.filter.states = [];
    this.filter.countries = [];
    for(let i = 0; i < this.countries.length; ++i)
      if(this.countries[i].isChecked == true) 
        this.filter.countries.push(this.countries[i].value); 
      

    for(let i = 0; i < this.states.length; ++i)
      if(this.states[i].isChecked == true) 
        this.filter.states.push(this.states[i].value); 
     
  }

}

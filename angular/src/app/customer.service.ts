import { Injectable } from '@angular/core';

import { HttpClient , HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Customer } from './Customer';
import { environment } from 'src/environments/environment';
import { CustomerFilter } from './CustomerFilter';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiServerUrl = 'http://localhost:8080/customers';

  constructor(private http: HttpClient) { }

  public findCustomers(filter:CustomerFilter): Observable<Customer []> {

    let params = new HttpParams();
    for(let i = 0; i < filter.countries.length; ++i)
        params = params.append('country', filter.countries[i]);
    for(let i = 0; i < filter.states.length; ++i)
        params = params.append('state', filter.states[i]);
    console.log(params);
    return this.http.get<Customer []>(`${this.apiServerUrl}`, { params: params });
  }


}

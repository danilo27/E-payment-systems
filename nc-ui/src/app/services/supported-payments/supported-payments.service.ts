import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';


@Injectable()
export class SupportedPaymentsService {

  constructor(private http: HttpClient) { }

  all(){
    return this.http.get('api/nc/supported-payments/all') as Observable<any>;
  }
}

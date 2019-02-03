import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable()
export class MerchantService {

  constructor(private http: HttpClient) { }

  createMerchant(request){
    return this.http.post('api/merchant/new', request) as Observable<any>;
 }
}

import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';


@Injectable()
export class TransactionService {

   constructor(private http: HttpClient) { }

   proceedToPc(dto){
        return this.http.post("api/nc/transaction/proceedToPc", dto) as Observable<any>;
   }

   sendSubscription(data){
      return this.http.post("api/nc/transaction/sendSubscription", data) as Observable<any>;
   }

}

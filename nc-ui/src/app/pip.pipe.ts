   import { Injectable, Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'getValues', pure: true})
 
export class GetValuesPipe implements PipeTransform {
    transform(map: Map<any, any>): any[] {
        let ret = [];
        console.log('map',map)
        Object.keys(map).forEach(function (key){
            ret.push({
                key: key,
                val: map[key]
            });
        });
        console.log('ret',ret);
        return ret;
    }
}
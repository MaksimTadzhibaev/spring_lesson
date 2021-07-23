import { Injectable } from '@angular/core';
import {Product} from "./product";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private identity: number = 8;

  private products: {[key: number]: Product } = {
    1: new Product (1, "apricot",  200),
    2: new Product (2, "banana",  60),
    3: new Product (3, "cherry",  350),
    4: new Product (4,  "grape",  150),
    5: new Product (5,  "lemon",  100),
    6: new Product (6,  "pear",  160),
    7: new Product (7,  "melon",  550),
  };

  constructor(public http: HttpClient) { }

  public findAll(){
    return this.http.get<Product[]>('/api/v1/product/all').toPromise();
    // return new Promise<Product[]>((resolve, reject) =>
    // {
    //   resolve(
    //     Object.values(this.products)
    //   )
    // })
  }

  public findById(id:number){
    return this.http.get<Product>('/api/v1/product/${id}').toPromise();
  //   return new Promise<Product>((resolve, reject) =>
  //   {
  //     resolve(
  //       this.products[id]
  //     )
  // })
}

public save(product:Product){
  return new Promise<void>((resolve, reject) => {
    if (product.id = -1) {
      product.id = this.identity++;
    }
    this.products[product.id] = product;
    resolve();
})
}

public delete(id:number){
  return new Promise<void>((resolve, reject) => {
    delete this.products[id];
    resolve();
  })
}

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ConfigService } from './Config.service';


@Injectable({


    providedIn: 'root'

  })
  export class PortfolioService {




    constructor(private http:HttpClient,private config:ConfigService) { }

    url=this.config.url

    getDataPortfolio(){
        return this.http.get(this.url+"/portfolio/get-all-portfolios")
      }

    createPortfolio(){
        return this.http.post(this.url+"/portfolio/add-portfolio-fromAPI",null)
      }
      deletePortfolio(id){
        return this.http.delete(this.url+"/portfolio/remove-portfolio/"+id)
      }
  }

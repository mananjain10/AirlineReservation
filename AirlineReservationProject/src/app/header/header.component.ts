import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  public isVisibleUserDashboard=false;
  public isVisibleAdminDashboard=false;
  public isVisibleMainDashboard=true;

  constructor() { }

  ngOnInit(): void {
  }

}

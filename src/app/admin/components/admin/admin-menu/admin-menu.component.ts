import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  constructor(
  ) { }

  ngOnInit() {
    $(document).ready(function () {
      // $('#sidebarCollapse').on('click', function () {
      //   $('#sidebar').toggleClass('active');
      // });
      $("a.active").closest("div").prev().click();
    });
  }
}
